using HighSchoolStory.Application.Features.DailyLoop;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;
using HighSchoolStory.Ports.Content;
using HighSchoolStory.Ports.Time;
using Xunit;

namespace HighSchoolStory.Application.Tests.DailyLoop;

public sealed class DailyLoopSessionTests
{
    [Fact]
    public void Review_day_context_returns_a_player_facing_decision_snapshot()
    {
        var session = CreateSession();

        var result = session.Execute(new ReviewDayContextCommand("review-context"));

        Assert.True(result.IsSuccess);
        var snapshot = result.Success!.ReadModel.DecisionSnapshot;
        Assert.Equal(ScheduleTime.FromHoursAndMinutes(6, 0), snapshot.CurrentTime);
        Assert.Equal(DailyLoopContext.BeforeSchool, snapshot.DayContext);
        Assert.Equal(new ScheduleEntryId("first-day-lesson-1"), snapshot.NextKnownCommitment);
        Assert.Equal(120, snapshot.AvailableTimeWindowMinutes);
        Assert.Equal(FeasibilityStatus.Fits, snapshot.Feasibility);
        Assert.Contains("08:00", snapshot.NextBoundaryText);
        Assert.NotEmpty(snapshot.WarningBlockLabel);
        Assert.Equal(DailyLoopSeverity.Informational, snapshot.Severity);
    }

    [Fact]
    public void Blocked_action_returns_typed_failure_and_preserves_canonical_state()
    {
        var session = CreateSession();
        Assert.True(session.Execute(new ReviewDayContextCommand("review-context")).IsSuccess);
        Assert.True(session.Execute(new HonorMandatoryCommitmentCommand("honor-lesson", new ScheduleEntryId("first-day-lesson-1"))).IsSuccess);
        Assert.True(session.Execute(new ChooseLessonActionCommand("lesson-choice", new ScheduleEntryId("first-day-lesson-1"), "participate")).IsSuccess);
        Assert.True(session.Execute(new ResolveWellbeingChoiceCommand("wellbeing-choice", "take-breath")).IsSuccess);
        var beforeFingerprint = session.CurrentStateFingerprint;

        var result = session.Execute(new AttemptBlockedActionCommand("blocked-choice", "leave-school-early"));

        Assert.False(result.IsSuccess);
        Assert.Equal(DailyLoopFailureCode.MandatoryCommitment, result.Failure!.Code);
        Assert.Equal(beforeFingerprint, session.CurrentStateFingerprint);
        Assert.Contains("attendance", result.Failure.PlayerFacingLabel, StringComparison.OrdinalIgnoreCase);
    }

    [Fact]
    public void Remaining_commitments_social_evidence_and_day_end_use_application_handlers()
    {
        var session = CreateSession();
        Assert.True(session.Execute(new ReviewDayContextCommand("review-context")).IsSuccess);
        Assert.True(session.Execute(new HonorMandatoryCommitmentCommand("honor-lesson", new ScheduleEntryId("first-day-lesson-1"))).IsSuccess);
        Assert.True(session.Execute(new ChooseLessonActionCommand("lesson-choice", new ScheduleEntryId("first-day-lesson-1"), "participate")).IsSuccess);
        Assert.True(session.Execute(new ResolveWellbeingChoiceCommand("wellbeing-choice", "take-breath")).IsSuccess);
        Assert.False(session.Execute(new AttemptBlockedActionCommand("blocked-choice", "leave-school-early")).IsSuccess);

        var progress = session.Execute(new ProgressMandatoryCommitmentsCommand("progress-school"));
        Assert.True(progress.IsSuccess);
        Assert.Equal(DailyLoopContext.AfterSchool, progress.Success!.ReadModel.DayContext);
        Assert.Equal("school-day-anchors", progress.Success.EvidenceId);

        var social = session.Execute(new DiscoverSocialTouchpointCommand("discover-social", "quiet-reconnection-clue", "future-conversation-hook"));
        Assert.True(social.IsSuccess);
        Assert.Equal("quiet-reconnection-clue", social.Success!.ReadModel.SocialClue!.Id);
        Assert.Equal("future-conversation-hook", social.Success.ReadModel.FutureHookCandidate!.Id);

        var end = session.Execute(new EndDayCommand("end-day"));
        Assert.True(end.IsSuccess);
        Assert.True(end.Success!.ReadModel.DayEnded);
        Assert.Equal(DailyLoopContext.DayComplete, end.Success.ReadModel.DayContext);
        Assert.Equal("day-complete", end.Success.EvidenceId);
        Assert.Null(end.Success.ReadModel.DecisionSnapshot.NextBoundary);
        Assert.Equal("The school day is complete.", end.Success.ReadModel.DecisionSnapshot.NextBoundaryText);

        var repeatedEnd = session.Execute(new EndDayCommand("end-day-again"));
        Assert.False(repeatedEnd.IsSuccess);
        Assert.Equal(DailyLoopFailureCode.DayAlreadyEnded, repeatedEnd.Failure!.Code);
    }

    [Fact]
    public void Progress_before_the_active_lesson_boundary_is_rejected_without_state_change()
    {
        var session = CreateSession();
        var before = session.CurrentStateFingerprint;

        var result = session.Execute(new ProgressMandatoryCommitmentsCommand("progress-too-early"));

        Assert.False(result.IsSuccess);
        Assert.Equal(DailyLoopFailureCode.InvalidCommandOrder, result.Failure!.Code);
        Assert.Equal(before, session.CurrentStateFingerprint);
    }

    [Fact]
    public void Empty_social_evidence_is_rejected_as_typed_failure()
    {
        var session = CreateSession();
        session.Execute(new ReviewDayContextCommand("review-context"));

        var result = session.Execute(new DiscoverSocialTouchpointCommand("discover-social", "", "future-hook"));

        Assert.False(result.IsSuccess);
        Assert.Equal(DailyLoopFailureCode.SocialDiscoveryUnavailable, result.Failure!.Code);
    }

    [Fact]
    public void Controlled_clock_seeds_the_initial_state()
    {
        var session = new DailyLoopSession(
            new StubRepository(CreateSchedule()),
            new ScheduleId("first-school-day"),
            new FixedClock(Time(7)),
            new FixedRandom(1201));

        var review = session.Execute(new ReviewDayContextCommand("review-context"));

        Assert.True(review.IsSuccess);
        Assert.Equal(Time(7), review.Success!.ReadModel.CurrentTime);
    }

    private static DailyLoopSession CreateSession()
    {
        return new DailyLoopSession(
            new StubRepository(CreateSchedule()),
            new ScheduleId("first-school-day"),
            new FixedClock(Time(6)),
            new FixedRandom(1201));
    }

    private static DailySchedule CreateSchedule()
    {
        var entries = new[]
        {
            new ScheduleEntry(new ScheduleEntryId("first-day-wake"), ScheduleEntryKind.Wake, Time(6), new ScheduleDuration(0), new AnchorLocationId("dorm")),
            new ScheduleEntry(new ScheduleEntryId("first-day-before-school"), ScheduleEntryKind.BeforeSchoolFree, Time(6), new ScheduleDuration(120), new AnchorLocationId("dorm")),
            new ScheduleEntry(new ScheduleEntryId("first-day-lesson-1"), ScheduleEntryKind.Lesson, Time(8), new ScheduleDuration(45), new AnchorLocationId("school")),
            new ScheduleEntry(new ScheduleEntryId("first-day-break-1"), ScheduleEntryKind.Break, Time(8, 45), new ScheduleDuration(15), new AnchorLocationId("school")),
            new ScheduleEntry(new ScheduleEntryId("first-day-lesson-2"), ScheduleEntryKind.Lesson, Time(9), new ScheduleDuration(45), new AnchorLocationId("school")),
            new ScheduleEntry(new ScheduleEntryId("first-day-after-school"), ScheduleEntryKind.AfterSchoolFree, Time(14, 45), new ScheduleDuration(375), new AnchorLocationId("school")),
            new ScheduleEntry(new ScheduleEntryId("first-day-dorm-return"), ScheduleEntryKind.DormReturn, Time(21), new ScheduleDuration(0), new AnchorLocationId("dorm")),
            new ScheduleEntry(new ScheduleEntryId("first-day-wind-down"), ScheduleEntryKind.WindDown, Time(21), new ScheduleDuration(60), new AnchorLocationId("dorm")),
            new ScheduleEntry(new ScheduleEntryId("first-day-latest-sleep"), ScheduleEntryKind.LatestSleep, Time(22), new ScheduleDuration(0), new AnchorLocationId("dorm")),
        };
        return new DailySchedule(new ScheduleId("first-school-day"), DayOfWeek.Monday, entries);
    }

    private static ScheduleTime Time(int hour, int minute = 0) => ScheduleTime.FromHoursAndMinutes(hour, minute);

    private sealed class StubRepository(DailySchedule schedule) : IDailyScheduleRepository
    {
        public DailySchedule? Find(ScheduleId scheduleId) => schedule.Id == scheduleId ? schedule : null;
    }

    private sealed class FixedClock(ScheduleTime now) : IClock
    {
        public ScheduleTime Now { get; } = now;
    }

    private sealed class FixedRandom(int seed) : IRandomSource
    {
        public int Seed { get; } = seed;
    }
}
