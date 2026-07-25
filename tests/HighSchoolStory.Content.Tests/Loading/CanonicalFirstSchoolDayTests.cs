using HighSchoolStory.Content.Loading;
using HighSchoolStory.Domain.Calendar;
using Xunit;

namespace HighSchoolStory.Content.Tests.Loading;

public sealed class CanonicalFirstSchoolDayTests
{
    [Fact]
    public void Loads_the_canonical_first_school_day_with_exact_shape_and_alignment()
    {
        var result = new DailyScheduleLoader().Load(Path.Combine(FindRepositoryRoot(), "content", "mvp"));

        Assert.True(result.IsSuccess);
        var schedule = result.Success!.FindDailySchedule(new ScheduleId("first-school-day"));
        Assert.NotNull(schedule);
        var expected = new[]
        {
            new ExpectedEntry("first-day-wake", ScheduleEntryKind.Wake, 360, 0, "dorm"),
            new ExpectedEntry("first-day-before-school", ScheduleEntryKind.BeforeSchoolFree, 360, 120, "dorm"),
            new ExpectedEntry("first-day-lesson-1", ScheduleEntryKind.Lesson, 480, 45, "school"),
            new ExpectedEntry("first-day-break-1", ScheduleEntryKind.Break, 525, 15, "school"),
            new ExpectedEntry("first-day-lesson-2", ScheduleEntryKind.Lesson, 540, 45, "school"),
            new ExpectedEntry("first-day-break-2", ScheduleEntryKind.Break, 585, 15, "school"),
            new ExpectedEntry("first-day-lesson-3", ScheduleEntryKind.Lesson, 600, 45, "school"),
            new ExpectedEntry("first-day-break-3", ScheduleEntryKind.Break, 645, 15, "school"),
            new ExpectedEntry("first-day-lesson-4", ScheduleEntryKind.Lesson, 660, 45, "school"),
            new ExpectedEntry("first-day-break-4", ScheduleEntryKind.Break, 705, 15, "school"),
            new ExpectedEntry("first-day-lunch", ScheduleEntryKind.Lunch, 720, 45, "school"),
            new ExpectedEntry("first-day-lesson-5", ScheduleEntryKind.Lesson, 765, 45, "school"),
            new ExpectedEntry("first-day-break-5", ScheduleEntryKind.Break, 810, 15, "school"),
            new ExpectedEntry("first-day-lesson-6", ScheduleEntryKind.Lesson, 825, 45, "school"),
            new ExpectedEntry("first-day-break-6", ScheduleEntryKind.Break, 870, 15, "school"),
            new ExpectedEntry("first-day-after-school", ScheduleEntryKind.AfterSchoolFree, 885, 375, "school"),
            new ExpectedEntry("first-day-dorm-return", ScheduleEntryKind.DormReturn, 1260, 0, "dorm"),
            new ExpectedEntry("first-day-wind-down", ScheduleEntryKind.WindDown, 1260, 60, "dorm"),
            new ExpectedEntry("first-day-latest-sleep", ScheduleEntryKind.LatestSleep, 1320, 0, "dorm"),
        };

        var actual = schedule!.Entries.Select(entry => new ExpectedEntry(
            entry.Id.Value,
            entry.Kind,
            entry.Start.MinutesSinceMidnight,
            entry.Duration.Minutes,
            entry.AnchorLocationId.Value));

        Assert.Equal(expected, actual);
        Assert.All(schedule.Entries, entry =>
        {
            Assert.True(entry.Start.IsQuarterHourAligned);
            Assert.True(entry.Duration.IsQuarterHourAligned);
        });
    }

    [Fact]
    public void Repeated_canonical_loads_produce_equivalent_typed_results()
    {
        var contentRoot = Path.Combine(FindRepositoryRoot(), "content", "mvp");
        var loader = new DailyScheduleLoader();

        var first = loader.Load(contentRoot);
        var second = loader.Load(contentRoot);

        Assert.True(first.IsSuccess);
        Assert.True(second.IsSuccess);
        Assert.Equal(
            Fingerprint(first.Success!.FindDailySchedule(new ScheduleId("first-school-day"))),
            Fingerprint(second.Success!.FindDailySchedule(new ScheduleId("first-school-day"))));
    }

    private static string Fingerprint(DailySchedule? schedule) => string.Join(
        "|",
        schedule?.Entries.Select(entry => $"{entry.Id.Value}:{entry.Kind}:{entry.Start.MinutesSinceMidnight}:{entry.Duration.Minutes}:{entry.AnchorLocationId.Value}") ?? []);

    private static string FindRepositoryRoot()
    {
        for (var directory = new DirectoryInfo(AppContext.BaseDirectory); directory is not null; directory = directory.Parent)
        {
            if (File.Exists(Path.Combine(directory.FullName, "High School Story.sln"))) return directory.FullName;
        }

        throw new InvalidOperationException("Repository root containing High School Story.sln was not found.");
    }

    private sealed record ExpectedEntry(string Id, ScheduleEntryKind Kind, int StartMinutes, int DurationMinutes, string LocationId);
}
