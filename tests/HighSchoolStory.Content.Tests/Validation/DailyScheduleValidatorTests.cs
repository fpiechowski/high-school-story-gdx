using HighSchoolStory.Content.Loading;
using HighSchoolStory.Content.Validation;
using Xunit;

namespace HighSchoolStory.Content.Tests.Validation;

public sealed class DailyScheduleValidatorTests
{
    [Fact]
    public void Loads_a_general_valid_school_day()
    {
        using var fixture = new TemporaryContent(ValidSchedule());

        var result = new DailyScheduleLoader().Load(fixture.Root);

        Assert.True(result.IsSuccess);
    }

    [Theory]
    [InlineData("\"kind\": \"lesson\"", "\"kind\": \"break\"", "schedule.missing-lesson-anchor", "requires at least one lesson anchor")]
    [InlineData("\"start\": \"08:00\"", "\"start\": \"08:05\"", "schedule.start-not-aligned", "starts at minute 485")]
    [InlineData("\"durationMinutes\": 45", "\"durationMinutes\": 46", "schedule.duration-not-aligned", "duration 46 minutes")]
    [InlineData("\"start\": \"08:45\"", "\"start\": \"08:30\"", "schedule.overlapping-hard-commitment", "overlaps")]
    [InlineData("\"start\": \"22:00\"", "\"start\": \"21:45\"", "schedule.latest-sleep-conflict", "ends after latest sleep")]
    [InlineData("\"start\": \"21:00\", \"durationMinutes\": 0, \"anchorLocationId\": \"dorm\"", "\"start\": \"20:45\", \"durationMinutes\": 0, \"anchorLocationId\": \"dorm\"", "schedule.boundary-chain-invalid", "extends past dorm return")]
    public void Rejects_a_semantically_invalid_school_day(string oldValue, string newValue, string ruleId, string messageFragment)
    {
        using var fixture = new TemporaryContent(ValidSchedule().Replace(oldValue, newValue, StringComparison.Ordinal));

        var result = new DailyScheduleLoader().Load(fixture.Root);

        Assert.False(result.IsSuccess);
        Assert.Null(result.Success);
        var issue = result.Failure!.Issues.First(issue => issue.RuleId.Value == ruleId);
        Assert.Equal(IssueSeverity.Error, issue.Severity);
        Assert.Equal(FailureCategory.Semantic, issue.FailureCategory);
        Assert.Equal("monday", issue.ContentId);
        Assert.Equal(fixture.SchedulePath, issue.SourcePath);
        Assert.Null(issue.CausalityTraceId);
        Assert.Contains(messageFragment, issue.Message, StringComparison.Ordinal);
        Assert.NotNull(issue.SuggestedFix);
    }

    [Theory]
    [InlineData("\"id\": \"before\", \"kind\": \"before-school-free\", \"start\": \"06:00\", \"durationMinutes\": 120", "\"id\": \"before\", \"kind\": \"before-school-free\", \"start\": \"06:00\", \"durationMinutes\": 135", "Before-school free time extends past the first lesson.")]
    [InlineData("\"id\": \"wind-down\", \"kind\": \"wind-down\", \"start\": \"21:00\", \"durationMinutes\": 60", "\"id\": \"wind-down\", \"kind\": \"wind-down\", \"start\": \"22:00\", \"durationMinutes\": 60", "Wind-down ends after latest sleep.")]
    public void Rejects_a_schedule_with_a_broken_boundary_chain(string oldValue, string newValue, string message)
    {
        using var fixture = new TemporaryContent(ValidSchedule().Replace(oldValue, newValue, StringComparison.Ordinal));

        var result = new DailyScheduleLoader().Load(fixture.Root);

        Assert.False(result.IsSuccess);
        var issue = result.Failure!.Issues.First(issue => issue.RuleId == ScheduleValidationRuleIds.BoundaryChainInvalid && issue.Message.Contains(message, StringComparison.Ordinal));
        Assert.Equal("monday", issue.ContentId);
        Assert.Equal(fixture.SchedulePath, issue.SourcePath);
        Assert.NotNull(issue.SuggestedFix);
    }

    [Theory]
    [InlineData("{ \"from\": \"dorm\", \"to\": \"school\", \"minimumTravelMinutes\": 0 },", "", "no authored travel time exists")]
    [InlineData("{ \"from\": \"dorm\", \"to\": \"school\", \"minimumTravelMinutes\": 0 },", "{ \"from\": \"dorm\", \"to\": \"school\", \"minimumTravelMinutes\": 15 },", "within the authored time window")]
    public void Rejects_an_unreachable_required_commitment(string oldValue, string newValue, string message)
    {
        using var fixture = new TemporaryContent(ValidSchedule());
        File.WriteAllText(fixture.TravelTimesPath, TravelTimes().Replace(oldValue, newValue, StringComparison.Ordinal));

        var result = new DailyScheduleLoader().Load(fixture.Root);

        Assert.False(result.IsSuccess);
        var issue = result.Failure!.Issues.First(issue => issue.RuleId == ScheduleValidationRuleIds.UnreachableRequiredCommitment && issue.Message.Contains(message, StringComparison.Ordinal));
        Assert.Equal("monday", issue.ContentId);
        Assert.Equal(fixture.SchedulePath, issue.SourcePath);
        Assert.NotNull(issue.SuggestedFix);
    }

    private static string ValidSchedule() => """
        {
          "schemaVersion": 1,
          "id": "monday",
          "dayOfWeek": "monday",
          "entries": [
            { "id": "wake", "kind": "wake", "start": "06:00", "durationMinutes": 0, "anchorLocationId": "dorm" },
            { "id": "before", "kind": "before-school-free", "start": "06:00", "durationMinutes": 120, "anchorLocationId": "dorm" },
            { "id": "lesson", "kind": "lesson", "start": "08:00", "durationMinutes": 45, "anchorLocationId": "school" },
            { "id": "break", "kind": "break", "start": "08:45", "durationMinutes": 15, "anchorLocationId": "school" },
            { "id": "lunch", "kind": "lunch", "start": "09:00", "durationMinutes": 45, "anchorLocationId": "school" },
            { "id": "after", "kind": "after-school-free", "start": "09:45", "durationMinutes": 675, "anchorLocationId": "school" },
            { "id": "return", "kind": "dorm-return", "start": "21:00", "durationMinutes": 0, "anchorLocationId": "dorm" },
            { "id": "wind-down", "kind": "wind-down", "start": "21:00", "durationMinutes": 60, "anchorLocationId": "dorm" },
            { "id": "sleep", "kind": "latest-sleep", "start": "22:00", "durationMinutes": 0, "anchorLocationId": "dorm" }
          ]
        }
        """;

    private static string TravelTimes() => """
        {
          "schemaVersion": 1,
          "travelTimes": [
            { "from": "dorm", "to": "school", "minimumTravelMinutes": 0 },
            { "from": "school", "to": "dorm", "minimumTravelMinutes": 0 }
          ]
        }
        """;

    private sealed class TemporaryContent : IDisposable
    {
        public TemporaryContent(string json)
        {
            Root = Path.Combine(Path.GetTempPath(), $"high-school-story-{Guid.NewGuid():N}");
            var calendar = Directory.CreateDirectory(Path.Combine(Root, "calendar"));
            File.WriteAllText(Path.Combine(calendar.FullName, "schedule.json"), json);
            File.WriteAllText(Path.Combine(calendar.FullName, "travel-times.json"), TravelTimes());
        }

        public string Root { get; }
        public string SchedulePath => Path.Combine(Root, "calendar", "schedule.json");
        public string TravelTimesPath => Path.Combine(Root, "calendar", "travel-times.json");
        public void Dispose() => Directory.Delete(Root, true);
    }
}
