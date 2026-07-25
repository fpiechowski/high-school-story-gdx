using HighSchoolStory.Content.Loading;
using HighSchoolStory.Content.Validation;
using HighSchoolStory.Domain.Calendar;
using Xunit;

namespace HighSchoolStory.Content.Tests.Loading;

public sealed class DailyScheduleLoaderTests
{
    [Fact]
    public void Loads_a_valid_schedule_into_an_atomic_catalog()
    {
        using var fixture = new TemporaryContent("""
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
            """);

        var result = new DailyScheduleLoader().Load(fixture.Root);

        Assert.True(result.IsSuccess);
        Assert.NotNull(result.Success);
    }

    [Fact]
    public void Rejects_an_unknown_json_member_without_exposing_a_catalog()
    {
        using var fixture = new TemporaryContent("""{ "schemaVersion": 1, "id": "monday", "dayOfWeek": "monday", "entries": [], "unexpected": true }""");

        var result = new DailyScheduleLoader().Load(fixture.Root);

        Assert.False(result.IsSuccess);
        Assert.Null(result.Success);
        Assert.NotNull(result.Failure);
        var issue = Assert.Single(result.Failure!.Issues);
        Assert.Equal(IssueSeverity.Error, issue.Severity);
        Assert.Equal(FailureCategory.Shape, issue.FailureCategory);
        Assert.Null(issue.ContentId);
        Assert.Equal(fixture.SchedulePath, issue.SourcePath);
        Assert.Equal(ContentLoadRuleIds.JsonInvalid, issue.RuleId);
        Assert.Null(issue.CausalityTraceId);
        Assert.NotEmpty(issue.Message);
        Assert.Null(issue.SuggestedFix);
    }

    [Fact]
    public void Rejects_missing_travel_times_without_exposing_a_catalog()
    {
        using var fixture = new TemporaryContent(ValidSchedule());
        File.Delete(fixture.TravelTimesPath);

        var result = new DailyScheduleLoader().Load(fixture.Root);

        Assert.False(result.IsSuccess);
        Assert.Null(result.Success);
        Assert.Contains(result.Failure!.Issues, issue => issue.RuleId == ContentLoadRuleIds.ScheduleInvalid && issue.Message.Contains("travel-time", StringComparison.Ordinal));
    }

    [Fact]
    public void Rejects_invalid_travel_times_without_exposing_a_catalog()
    {
        using var fixture = new TemporaryContent(ValidSchedule());
        File.WriteAllText(fixture.TravelTimesPath, "{ \"schemaVersion\": 1, \"unexpected\": true }");

        var result = new DailyScheduleLoader().Load(fixture.Root);

        Assert.False(result.IsSuccess);
        Assert.Null(result.Success);
        Assert.Contains(result.Failure!.Issues, issue => issue.RuleId == ContentLoadRuleIds.JsonInvalid && issue.SourcePath == fixture.TravelTimesPath);
    }

    [Fact]
    public void Loads_two_schedules_against_one_shared_travel_times_document()
    {
        using var fixture = new TemporaryContent(ValidSchedule());
        File.WriteAllText(Path.Combine(fixture.Root, "calendar", "tuesday.json"), ValidSchedule().Replace("\"monday\"", "\"tuesday\"", StringComparison.Ordinal));

        var result = new DailyScheduleLoader().Load(fixture.Root);

        Assert.True(result.IsSuccess);
        Assert.NotNull(result.Success!.FindDailySchedule(new ScheduleId("monday")));
        Assert.NotNull(result.Success.FindDailySchedule(new ScheduleId("tuesday")));
    }

    [Fact]
    public void Loads_a_one_day_catalog_without_unrelated_content()
    {
        using var fixture = new TemporaryContent(ValidSchedule());

        var result = new DailyScheduleLoader().Load(fixture.Root);

        Assert.True(result.IsSuccess);
        Assert.NotNull(result.Success!.FindDailySchedule(new ScheduleId("monday")));
        Assert.Equal(["calendar"], Directory.GetDirectories(fixture.Root).Select(Path.GetFileName));
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

    private sealed class TemporaryContent : IDisposable
    {
        public TemporaryContent(string json)
        {
            Root = Path.Combine(Path.GetTempPath(), $"high-school-story-{Guid.NewGuid():N}");
            var calendar = Directory.CreateDirectory(Path.Combine(Root, "calendar"));
            File.WriteAllText(Path.Combine(calendar.FullName, "schedule.json"), json);
            File.WriteAllText(Path.Combine(calendar.FullName, "travel-times.json"), """
                {
                  "schemaVersion": 1,
                  "travelTimes": [
                    { "from": "dorm", "to": "school", "minimumTravelMinutes": 0 },
                    { "from": "school", "to": "dorm", "minimumTravelMinutes": 0 }
                  ]
                }
                """);
        }

        public string Root { get; }
        public string SchedulePath => Path.Combine(Root, "calendar", "schedule.json");
        public string TravelTimesPath => Path.Combine(Root, "calendar", "travel-times.json");
        public void Dispose() => Directory.Delete(Root, true);
    }
}
