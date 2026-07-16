using HighSchoolStory.Content.Loading;
using HighSchoolStory.Content.Validation;
using Xunit;

namespace HighSchoolStory.Content.Tests.Loading;

public sealed class DailyScheduleLoaderTests
{
    [Fact]
    public void Loads_a_valid_schedule_into_an_atomic_catalog()
    {
        using var fixture = new TemporaryContent("""{ "schemaVersion": 1, "id": "monday", "dayOfWeek": "monday", "entries": [] }""");

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
        Assert.Contains(result.Failure!.Issues, issue => issue.RuleId == ContentLoadRuleIds.JsonInvalid);
    }

    private sealed class TemporaryContent : IDisposable
    {
        public TemporaryContent(string json)
        {
            Root = Path.Combine(Path.GetTempPath(), $"high-school-story-{Guid.NewGuid():N}");
            var calendar = Directory.CreateDirectory(Path.Combine(Root, "calendar"));
            File.WriteAllText(Path.Combine(calendar.FullName, "schedule.json"), json);
        }

        public string Root { get; }
        public void Dispose() => Directory.Delete(Root, true);
    }
}
