using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;
using HighSchoolStory.Domain.Shared;
using Xunit;

namespace HighSchoolStory.Domain.Tests.DailyLoop;

public sealed class GameStateTests
{
    [Fact]
    public void Seeded_first_day_starts_at_wake_with_narrow_runtime_state()
    {
        var state = GameState.CreateSeeded(new ScheduleId("first-school-day"), DayOfWeek.Monday, 1201);

        Assert.Equal(new ScheduleId("first-school-day"), state.ScheduleId);
        Assert.Equal(ScheduleTime.FromHoursAndMinutes(6, 0), state.CurrentTime);
        Assert.Equal(DailyLoopContext.BeforeSchool, state.Context);
        Assert.Equal(new AnchorLocationId("dorm"), state.Location);
        Assert.Equal(new WellbeingState(70, 20), state.Wellbeing);
        Assert.Empty(state.HonoredCommitments);
        Assert.Null(state.SocialClue);
        Assert.Null(state.FutureHookCandidate);
        Assert.False(state.DayEnded);
    }

    [Fact]
    public void Applying_a_transition_produces_a_new_canonical_state_and_stable_fingerprint()
    {
        var state = GameState.CreateSeeded(new ScheduleId("first-school-day"), DayOfWeek.Monday, 1201);
        var transition = new DailyLoopTransition(
            ScheduleTime.FromHoursAndMinutes(8, 0),
            DailyLoopContext.School,
            new AnchorLocationId("school"),
            [new ScheduleEntryId("first-day-lesson-1")],
            LessonChoiceId: "participate",
            Consequence: new VisibleConsequence("lesson-choice", "You showed up ready to learn."));

        var next = state.Apply(transition);

        Assert.NotSame(state, next);
        Assert.Contains(new ScheduleEntryId("first-day-lesson-1"), next.HonoredCommitments);
        Assert.Equal("participate", next.LessonChoiceId);
        Assert.Equal("lesson-choice", Assert.Single(next.VisibleConsequences).Id);
        Assert.Equal(state.Fingerprint(), (state with { }).Fingerprint());
        Assert.Equal(next.Fingerprint(), (next with { }).Fingerprint());
    }

    [Fact]
    public void Expected_failure_is_typed_and_does_not_require_an_exception()
    {
        var failure = DailyLoopFailure.Blocked(
            DailyLoopFailureCode.MandatoryCommitment,
            "School commitments still require attendance.");

        var result = Result<string, DailyLoopFailure>.Fail(failure);

        Assert.False(result.IsSuccess);
        Assert.Equal(DailyLoopFailureCode.MandatoryCommitment, result.Failure!.Code);
        Assert.Equal("School commitments still require attendance.", result.Failure.Message);
    }

    [Fact]
    public void Fingerprint_includes_player_visible_evidence_labels()
    {
        var first = GameState.CreateSeeded(new ScheduleId("first-school-day"), DayOfWeek.Monday, 1201).Apply(
            new DailyLoopTransition(
                ScheduleTime.FromHoursAndMinutes(8, 0),
                DailyLoopContext.School,
                new AnchorLocationId("school"),
                SocialClue: new SocialClue("clue", "You noticed a quiet invitation."),
                FutureHookCandidate: new FutureHookCandidate("hook", "A future conversation may grow."),
                Consequence: new VisibleConsequence("consequence", "The visible result is clear.")));
        var second = GameState.CreateSeeded(new ScheduleId("first-school-day"), DayOfWeek.Monday, 1201).Apply(
            new DailyLoopTransition(
                ScheduleTime.FromHoursAndMinutes(8, 0),
                DailyLoopContext.School,
                new AnchorLocationId("school"),
                SocialClue: new SocialClue("clue", "A different clue label."),
                FutureHookCandidate: new FutureHookCandidate("hook", "A different hook label."),
                Consequence: new VisibleConsequence("consequence", "A different consequence label.")));

        Assert.NotEqual(first.Fingerprint(), second.Fingerprint());
    }

    [Fact]
    public void Wellbeing_adjustment_saturates_extreme_deltas()
    {
        var wellbeing = new WellbeingState(70, 20).Adjust(int.MaxValue, int.MinValue);

        Assert.Equal(new WellbeingState(100, 0), wellbeing);
    }
}
