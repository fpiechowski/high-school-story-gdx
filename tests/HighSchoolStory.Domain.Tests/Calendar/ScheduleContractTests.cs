using HighSchoolStory.Domain.Calendar;
using Xunit;

namespace HighSchoolStory.Domain.Tests.Calendar;

public sealed class ScheduleContractTests
{
    [Theory]
    [InlineData(6, 0, true)]
    [InlineData(6, 5, false)]
    public void Schedule_time_exposes_quarter_hour_alignment(int hour, int minute, bool expected)
    {
        var time = ScheduleTime.FromHoursAndMinutes(hour, minute);

        Assert.Equal(expected, time.IsQuarterHourAligned);
    }

    [Fact]
    public void Schedule_time_rejects_invalid_clock_components()
    {
        Assert.Throws<ArgumentOutOfRangeException>(() => ScheduleTime.FromHoursAndMinutes(24, 0));
        Assert.Throws<ArgumentOutOfRangeException>(() => ScheduleTime.FromHoursAndMinutes(6, 60));
    }

    [Theory]
    [InlineData(0, true)]
    [InlineData(45, true)]
    [InlineData(20, false)]
    public void Schedule_duration_exposes_quarter_hour_alignment(int minutes, bool expected)
    {
        var duration = new ScheduleDuration(minutes);

        Assert.Equal(expected, duration.IsQuarterHourAligned);
    }

    [Theory]
    [InlineData(ScheduleEntryKind.Lesson, ScheduleEntrySemantics.HardCommitment)]
    [InlineData(ScheduleEntryKind.Lunch, ScheduleEntrySemantics.FixedWindow)]
    [InlineData(ScheduleEntryKind.BeforeSchoolFree, ScheduleEntrySemantics.AvailabilityWindow)]
    [InlineData(ScheduleEntryKind.DormReturn, ScheduleEntrySemantics.Boundary)]
    public void Entry_kind_determines_its_semantics(ScheduleEntryKind kind, ScheduleEntrySemantics expected)
    {
        var entry = new ScheduleEntry(
            new ScheduleEntryId("entry"),
            kind,
            ScheduleTime.FromHoursAndMinutes(6, 0),
            new ScheduleDuration(0),
            new AnchorLocationId("dorm"));

        Assert.Equal(expected, entry.Semantics);
    }

    [Fact]
    public void Daily_schedule_copies_entries_into_an_immutable_collection()
    {
        var source = new List<ScheduleEntry>
        {
            new(
                new ScheduleEntryId("wake"),
                ScheduleEntryKind.Wake,
                ScheduleTime.FromHoursAndMinutes(6, 0),
                new ScheduleDuration(0),
                new AnchorLocationId("dorm")),
        };

        var schedule = new DailySchedule(new ScheduleId("first-day"), DayOfWeek.Monday, source);
        source.Clear();

        Assert.Single(schedule.Entries);
        Assert.Equal("dorm", schedule.Entries[0].AnchorLocationId.Value);
    }
}
