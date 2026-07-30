using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;

namespace HighSchoolStory.Application.Features.DailyLoop;

internal static class DailyLoopScheduleQueries
{
    public static ScheduleEntry? FindEntry(DailySchedule schedule, ScheduleEntryId id) =>
        schedule.Entries.FirstOrDefault(x => x.Id == id);

    public static ScheduleEntry? FindNextLesson(DailySchedule schedule, GameState state) =>
        schedule.Entries
            .Where(x => x.Kind == ScheduleEntryKind.Lesson && !state.HonoredCommitments.Contains(x.Id))
            .OrderBy(x => x.Start.MinutesSinceMidnight)
            .FirstOrDefault(x => x.Start.MinutesSinceMidnight >= state.CurrentTime.MinutesSinceMidnight);

    public static ScheduleEntry? FindAfterSchool(DailySchedule schedule) =>
        schedule.Entries.FirstOrDefault(x => x.Kind == ScheduleEntryKind.AfterSchoolFree);

    public static ScheduleEntry? FindDormReturn(DailySchedule schedule) =>
        schedule.Entries.FirstOrDefault(x => x.Kind == ScheduleEntryKind.DormReturn);

    public static ScheduleEntry? FindLatestSleep(DailySchedule schedule) =>
        schedule.Entries.FirstOrDefault(x => x.Kind == ScheduleEntryKind.LatestSleep);

    public static ScheduleEntry? FindBreakAt(DailySchedule schedule, int minutes) =>
        schedule.Entries.FirstOrDefault(x =>
            x.Kind == ScheduleEntryKind.Break &&
            x.Start.MinutesSinceMidnight <= minutes &&
            minutes < x.Start.MinutesSinceMidnight + x.Duration.Minutes);

    public static int EndMinutes(ScheduleEntry entry) => entry.Start.MinutesSinceMidnight + entry.Duration.Minutes;

    public static ScheduleTime TimeFromMinutes(int minutes) => new(minutes);

    public static string FormatTime(ScheduleTime time) =>
        $"{time.MinutesSinceMidnight / 60:00}:{time.MinutesSinceMidnight % 60:00}";
}
