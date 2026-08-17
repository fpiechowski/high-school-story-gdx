using System.Collections.Immutable;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;

namespace HighSchoolStory.Application.Features.DailyLoop;

internal static class DailyLoopReadModelMapper
{
    public static DailyLoopReadModel Map(GameState state, DailySchedule schedule)
    {
        var nextLesson = DailyLoopScheduleQueries.FindNextLesson(schedule, state);
        var afterSchool = DailyLoopScheduleQueries.FindAfterSchool(schedule);
        var dormReturn = DailyLoopScheduleQueries.FindDormReturn(schedule);
        var latestSleep = DailyLoopScheduleQueries.FindLatestSleep(schedule);
        var nextBoundary = state.DayEnded
            ? null
            : nextLesson?.Start ??
                (state.CurrentTime.MinutesSinceMidnight < (dormReturn?.Start.MinutesSinceMidnight ?? int.MaxValue)
                    ? dormReturn?.Start
                    : latestSleep?.Start);

        var availableMinutes = Math.Max(0, (nextBoundary?.MinutesSinceMidnight ?? state.CurrentTime.MinutesSinceMidnight) - state.CurrentTime.MinutesSinceMidnight);
        var feasibility = state.DayEnded || nextBoundary is null
            ? FeasibilityStatus.Fits
            : availableMinutes == 0
                ? FeasibilityStatus.Blocked
                : availableMinutes < 30
                    ? FeasibilityStatus.Warning
                    : FeasibilityStatus.Fits;
        var severity = feasibility switch
        {
            FeasibilityStatus.Blocked => DailyLoopSeverity.Blocked,
            FeasibilityStatus.Warning => DailyLoopSeverity.Warning,
            _ => DailyLoopSeverity.Informational,
        };
        var warningLabel = feasibility switch
        {
            FeasibilityStatus.Blocked => "A mandatory commitment is due now.",
            FeasibilityStatus.Warning => "The available window is narrow.",
            _ when nextLesson is not null => $"Next commitment: {nextLesson.Id.Value}.",
            _ when state.DayEnded => "The school day is complete.",
            _ => "The next boundary is known.",
        };
        var boundaryText = state.DayEnded
            ? "The school day is complete."
            : nextLesson is not null
            ? $"Next school commitment at {DailyLoopScheduleQueries.FormatTime(nextLesson.Start)}."
            : dormReturn is not null && state.CurrentTime.MinutesSinceMidnight < dormReturn.Start.MinutesSinceMidnight
                ? $"Dorm return boundary at {DailyLoopScheduleQueries.FormatTime(dormReturn.Start)}."
                : latestSleep is not null
                    ? $"Latest sleep boundary at {DailyLoopScheduleQueries.FormatTime(latestSleep.Start)}."
                    : "No further boundary is authored.";

        var snapshot = new DecisionSnapshot(
            state.CurrentTime,
            state.Context,
            nextLesson?.Id,
            availableMinutes,
            feasibility,
            boundaryText,
            warningLabel,
            severity,
            nextBoundary);

        return new DailyLoopReadModel(
            state.ScheduleId,
            state.DayOfWeek,
            state.Seed,
            state.CurrentTime,
            state.Context,
            state.Location,
            state.Wellbeing,
            state.HonoredCommitments.OrderBy(x => x.Value, StringComparer.Ordinal).ToImmutableArray(),
            state.VisibleConsequences,
            state.SocialClue,
            state.FutureHookCandidate,
            state.DayEnded,
            state.Fingerprint(),
            snapshot);
    }
}
