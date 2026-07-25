using HighSchoolStory.Content.Loading;
using HighSchoolStory.Content.Validation;
using Xunit;

namespace HighSchoolStory.Content.Tests.Loading;

public sealed class DailyScheduleLoadDeterminismTests
{
    [Fact]
    public void Repeated_invalid_loads_preserve_issue_order_and_identity()
    {
        using var fixture = new TemporaryContent();
        var loader = new DailyScheduleLoader();

        var first = loader.Load(fixture.Root);
        var second = loader.Load(fixture.Root);

        Assert.False(first.IsSuccess);
        Assert.False(second.IsSuccess);
        var firstIssues = first.Failure!.Issues.Select(Fingerprint).ToArray();
        var secondIssues = second.Failure!.Issues.Select(Fingerprint).ToArray();
        Assert.Equal(firstIssues, secondIssues);
        Assert.Equal(firstIssues.OrderBy(x => x, StringComparer.Ordinal), firstIssues);
    }

    private static string Fingerprint(ContentIssue issue) => string.Join(
        "|",
        issue.SourcePath,
        issue.ContentId,
        issue.RuleId.Value,
        issue.Message);

    private sealed class TemporaryContent : IDisposable
    {
        public TemporaryContent()
        {
            Root = Path.Combine(Path.GetTempPath(), $"high-school-story-determinism-{Guid.NewGuid():N}");
            var calendar = Directory.CreateDirectory(Path.Combine(Root, "calendar"));
            File.WriteAllText(Path.Combine(calendar.FullName, "travel-times.json"), TravelTimesJson);
            File.WriteAllText(Path.Combine(calendar.FullName, "a-invalid.json"), ScheduleJson("a-invalid", "08:05", 45));
            File.WriteAllText(Path.Combine(calendar.FullName, "b-invalid.json"), ScheduleJson("b-invalid", "08:00", 46));
        }

        public string Root { get; }

        public void Dispose() => Directory.Delete(Root, true);

        private static string ScheduleJson(string id, string lessonStart, int lessonDuration) => $$"""
            {
              "schemaVersion": 1,
              "id": "{{id}}",
              "dayOfWeek": "monday",
              "entries": [
                { "id": "wake", "kind": "wake", "start": "06:00", "durationMinutes": 0, "anchorLocationId": "dorm" },
                { "id": "before", "kind": "before-school-free", "start": "06:00", "durationMinutes": 120, "anchorLocationId": "dorm" },
                { "id": "lesson", "kind": "lesson", "start": "{{lessonStart}}", "durationMinutes": {{lessonDuration}}, "anchorLocationId": "school" },
                { "id": "break", "kind": "break", "start": "08:45", "durationMinutes": 15, "anchorLocationId": "school" },
                { "id": "lunch", "kind": "lunch", "start": "09:00", "durationMinutes": 45, "anchorLocationId": "school" },
                { "id": "after", "kind": "after-school-free", "start": "09:45", "durationMinutes": 675, "anchorLocationId": "school" },
                { "id": "return", "kind": "dorm-return", "start": "21:00", "durationMinutes": 0, "anchorLocationId": "dorm" },
                { "id": "wind-down", "kind": "wind-down", "start": "21:00", "durationMinutes": 60, "anchorLocationId": "dorm" },
                { "id": "sleep", "kind": "latest-sleep", "start": "22:00", "durationMinutes": 0, "anchorLocationId": "dorm" }
              ]
            }
            """;

        private static string TravelTimesJson => """
            {
              "schemaVersion": 1,
              "travelTimes": [
                { "from": "dorm", "to": "school", "minimumTravelMinutes": 0 },
                { "from": "school", "to": "dorm", "minimumTravelMinutes": 0 }
              ]
            }
            """;
    }
}
