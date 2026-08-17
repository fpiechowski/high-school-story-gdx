using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;
using HighSchoolStory.Domain.Shared;

namespace HighSchoolStory.Application.Features.DailyLoop;

internal sealed record DailyLoopHandlerResult(GameState State, string? EvidenceId = null);

internal sealed class ReviewDayContextHandler
{
    public Result<DailyLoopHandlerResult, DailyLoopFailure> Handle(ReviewDayContextCommand command, GameState state)
    {
        if (state.DayEnded)
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(DailyLoopFailure.Blocked(DailyLoopFailureCode.DayAlreadyEnded, "The school day is already complete."));

        return Result<DailyLoopHandlerResult, DailyLoopFailure>.Ok(new(state));
    }
}

internal sealed class HonorMandatoryCommitmentHandler(DailySchedule schedule)
{
    public Result<DailyLoopHandlerResult, DailyLoopFailure> Handle(HonorMandatoryCommitmentCommand command, GameState state)
    {
        var entry = DailyLoopScheduleQueries.FindEntry(schedule, command.CommitmentId);
        var nextLesson = DailyLoopScheduleQueries.FindNextLesson(schedule, state);
        if (entry is null || entry.Semantics != ScheduleEntrySemantics.HardCommitment || entry.Kind != ScheduleEntryKind.Lesson || nextLesson?.Id != command.CommitmentId)
        {
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Blocked(DailyLoopFailureCode.MandatoryCommitment, "The next mandatory school commitment must be honored first."));
        }

        if (state.CurrentTime.MinutesSinceMidnight > entry.Start.MinutesSinceMidnight)
        {
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Blocked(DailyLoopFailureCode.MandatoryCommitment, "The school commitment was missed."));
        }

        var next = state.Apply(new DailyLoopTransition(
            entry.Start,
            DailyLoopContext.School,
            entry.AnchorLocationId,
            [entry.Id]));
        return Result<DailyLoopHandlerResult, DailyLoopFailure>.Ok(new(next, entry.Id.Value));
    }
}

internal sealed class ChooseLessonActionHandler(DailySchedule schedule)
{
    public Result<DailyLoopHandlerResult, DailyLoopFailure> Handle(ChooseLessonActionCommand command, GameState state)
    {
        var lesson = DailyLoopScheduleQueries.FindEntry(schedule, command.LessonId);
        if (lesson is null || lesson.Semantics != ScheduleEntrySemantics.HardCommitment || lesson.Kind != ScheduleEntryKind.Lesson || !state.HonoredCommitments.Contains(lesson.Id))
        {
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Invalid(DailyLoopFailureCode.InvalidLessonChoice, "An active lesson choice requires the honored lesson."));
        }

        if (state.CurrentTime.MinutesSinceMidnight != lesson.Start.MinutesSinceMidnight ||
            !string.Equals(command.ChoiceId, "participate", StringComparison.Ordinal))
        {
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Invalid(DailyLoopFailureCode.InvalidLessonChoice, "The active lesson choice is not available at the current lesson boundary."));
        }

        var next = state.Apply(new DailyLoopTransition(
            DailyLoopScheduleQueries.TimeFromMinutes(DailyLoopScheduleQueries.EndMinutes(lesson)),
            DailyLoopContext.School,
            lesson.AnchorLocationId,
            LessonChoiceId: command.ChoiceId,
            Wellbeing: state.Wellbeing.Adjust(-2, 0),
            Consequence: new VisibleConsequence("lesson-choice", "You stayed engaged through the first lesson.")));
        return Result<DailyLoopHandlerResult, DailyLoopFailure>.Ok(new(next, "lesson-choice"));
    }
}

internal sealed class ResolveWellbeingChoiceHandler(DailySchedule schedule)
{
    public Result<DailyLoopHandlerResult, DailyLoopFailure> Handle(ResolveWellbeingChoiceCommand command, GameState state)
    {
        var breakEntry = DailyLoopScheduleQueries.FindBreakAt(schedule, state.CurrentTime.MinutesSinceMidnight);
        if (breakEntry is null || !string.Equals(command.ChoiceId, "take-breath", StringComparison.Ordinal))
        {
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Invalid(DailyLoopFailureCode.InvalidWellbeingChoice, "The wellbeing choice is only available during a known school break."));
        }

        var next = state.Apply(new DailyLoopTransition(
            DailyLoopScheduleQueries.TimeFromMinutes(DailyLoopScheduleQueries.EndMinutes(breakEntry)),
            DailyLoopContext.School,
            breakEntry.AnchorLocationId,
            Wellbeing: state.Wellbeing.Adjust(5, -3),
            WellbeingChoiceId: command.ChoiceId,
            Consequence: new VisibleConsequence("wellbeing-trade-off", "You traded the full break for a steadier afternoon.")));
        return Result<DailyLoopHandlerResult, DailyLoopFailure>.Ok(new(next, "wellbeing-trade-off"));
    }
}

internal sealed class ProgressMandatoryCommitmentsHandler(DailySchedule schedule)
{
    public Result<DailyLoopHandlerResult, DailyLoopFailure> Handle(ProgressMandatoryCommitmentsCommand command, GameState state)
    {
        var afterSchool = DailyLoopScheduleQueries.FindAfterSchool(schedule);
        var lessons = schedule.Entries
            .Where(x => x.Semantics == ScheduleEntrySemantics.HardCommitment && x.Kind == ScheduleEntryKind.Lesson && !state.HonoredCommitments.Contains(x.Id))
            .OrderBy(x => x.Start.MinutesSinceMidnight)
            .ToArray();
        var nextLesson = lessons.FirstOrDefault();
        if (state.DayEnded || state.Context != DailyLoopContext.School ||
            state.LessonChoiceId is null || state.WellbeingChoiceId is null ||
            afterSchool is null || nextLesson is null ||
            state.CurrentTime.MinutesSinceMidnight < nextLesson.Start.MinutesSinceMidnight ||
            state.CurrentTime.MinutesSinceMidnight > afterSchool.Start.MinutesSinceMidnight)
        {
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Invalid(DailyLoopFailureCode.InvalidCommandOrder, "The remaining school commitments are not available from the current boundary."));
        }

        var next = state.Apply(new DailyLoopTransition(
            afterSchool.Start,
            DailyLoopContext.AfterSchool,
            afterSchool.AnchorLocationId,
            lessons.Select(x => x.Id),
            Consequence: new VisibleConsequence("school-day-anchors", "You made it through the remaining school anchors.")));
        return Result<DailyLoopHandlerResult, DailyLoopFailure>.Ok(new(next, "school-day-anchors"));
    }
}

internal sealed class DiscoverSocialTouchpointHandler(DailySchedule schedule)
{
    public Result<DailyLoopHandlerResult, DailyLoopFailure> Handle(DiscoverSocialTouchpointCommand command, GameState state)
    {
        var afterSchool = DailyLoopScheduleQueries.FindAfterSchool(schedule);
        if (string.IsNullOrWhiteSpace(command.ClueId) || string.IsNullOrWhiteSpace(command.FutureHookId))
        {
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Invalid(DailyLoopFailureCode.SocialDiscoveryUnavailable, "Social discovery requires both a clue and a future hook."));
        }

        if (afterSchool is null || state.Context != DailyLoopContext.AfterSchool ||
            state.CurrentTime.MinutesSinceMidnight < afterSchool.Start.MinutesSinceMidnight || state.SocialClue is not null)
        {
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Blocked(DailyLoopFailureCode.SocialDiscoveryUnavailable, "The social touchpoint is not available at this boundary."));
        }

        var next = state.Apply(new DailyLoopTransition(
            DailyLoopScheduleQueries.TimeFromMinutes(state.CurrentTime.MinutesSinceMidnight + 15),
            DailyLoopContext.AfterSchool,
            afterSchool.AnchorLocationId,
            SocialClue: new SocialClue(command.ClueId, "You noticed a quiet invitation to reconnect."),
            FutureHookCandidate: new FutureHookCandidate(command.FutureHookId, "A future conversation may grow from what you learned."),
            Wellbeing: state.Wellbeing.Adjust(0, -1),
            Consequence: new VisibleConsequence("social-discovery", "You learned something that will matter later.")));
        return Result<DailyLoopHandlerResult, DailyLoopFailure>.Ok(new(next, "social-discovery"));
    }
}

internal sealed class AttemptBlockedActionHandler(DailySchedule schedule)
{
    public Result<DailyLoopHandlerResult, DailyLoopFailure> Handle(AttemptBlockedActionCommand command, GameState state)
    {
        var nextLesson = DailyLoopScheduleQueries.FindNextLesson(schedule, state);
        if (nextLesson is not null && string.Equals(command.ActionId, "leave-school-early", StringComparison.Ordinal))
        {
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Blocked(DailyLoopFailureCode.MandatoryCommitment, "School commitments still require attendance."));
        }

        return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
            DailyLoopFailure.Invalid(DailyLoopFailureCode.InvalidCommandOrder, "The attempted action is not part of this deterministic path."));
    }
}

internal sealed class EndDayHandler(DailySchedule schedule)
{
    public Result<DailyLoopHandlerResult, DailyLoopFailure> Handle(EndDayCommand command, GameState state)
    {
        if (state.DayEnded)
        {
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Blocked(DailyLoopFailureCode.DayAlreadyEnded, "The school day is already complete."));
        }

        var latestSleep = DailyLoopScheduleQueries.FindLatestSleep(schedule);
        var dormReturn = DailyLoopScheduleQueries.FindDormReturn(schedule);
        var windDown = DailyLoopScheduleQueries.FindWindDown(schedule);
        var afterSchool = DailyLoopScheduleQueries.FindAfterSchool(schedule);
        var hasUnhonoredLesson = schedule.Entries.Any(x => x.Semantics == ScheduleEntrySemantics.HardCommitment && x.Kind == ScheduleEntryKind.Lesson && !state.HonoredCommitments.Contains(x.Id));
        if (latestSleep is null || dormReturn is null || windDown is null || afterSchool is null || hasUnhonoredLesson ||
            windDown.Start != dormReturn.Start || DailyLoopScheduleQueries.EndMinutes(windDown) > latestSleep.Start.MinutesSinceMidnight ||
            state.CurrentTime.MinutesSinceMidnight < afterSchool.Start.MinutesSinceMidnight)
        {
            return Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Blocked(DailyLoopFailureCode.MandatoryCommitment, "The day cannot end before school and dorm boundaries are honored."));
        }

        var atDorm = state.Apply(new DailyLoopTransition(
            dormReturn.Start,
            DailyLoopContext.DormWindDown,
            dormReturn.AnchorLocationId,
            Consequence: new VisibleConsequence("dorm-return", "You returned to the dorm for wind-down.")));
        var next = atDorm.Apply(new DailyLoopTransition(
            latestSleep.Start,
            DailyLoopContext.DayComplete,
            latestSleep.AnchorLocationId,
            Consequence: new VisibleConsequence("day-complete", "The first school day is complete."),
            EndDay: true));
        return Result<DailyLoopHandlerResult, DailyLoopFailure>.Ok(new(next, "day-complete"));
    }
}
