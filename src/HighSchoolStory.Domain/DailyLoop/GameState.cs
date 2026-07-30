using System.Collections.Immutable;
using HighSchoolStory.Domain.Calendar;

namespace HighSchoolStory.Domain.DailyLoop;

public sealed record DailyLoopTransition(
    ScheduleTime CurrentTime,
    DailyLoopContext Context,
    AnchorLocationId Location,
    IEnumerable<ScheduleEntryId>? CommitmentsToHonor = null,
    WellbeingState? Wellbeing = null,
    string? LessonChoiceId = null,
    string? WellbeingChoiceId = null,
    SocialClue? SocialClue = null,
    FutureHookCandidate? FutureHookCandidate = null,
    VisibleConsequence? Consequence = null,
    bool EndDay = false);

public sealed record GameState
{
    private GameState(
        ScheduleId scheduleId,
        DayOfWeek dayOfWeek,
        int seed,
        ScheduleTime currentTime,
        DailyLoopContext context,
        AnchorLocationId location,
        WellbeingState wellbeing,
        ImmutableHashSet<ScheduleEntryId> honoredCommitments,
        string? lessonChoiceId,
        string? wellbeingChoiceId,
        SocialClue? socialClue,
        FutureHookCandidate? futureHookCandidate,
        ImmutableArray<VisibleConsequence> visibleConsequences,
        bool dayEnded)
    {
        ScheduleId = scheduleId;
        DayOfWeek = dayOfWeek;
        Seed = seed;
        CurrentTime = currentTime;
        Context = context;
        Location = location;
        Wellbeing = wellbeing;
        HonoredCommitments = honoredCommitments;
        LessonChoiceId = lessonChoiceId;
        WellbeingChoiceId = wellbeingChoiceId;
        SocialClue = socialClue;
        FutureHookCandidate = futureHookCandidate;
        VisibleConsequences = visibleConsequences;
        DayEnded = dayEnded;
    }

    public ScheduleId ScheduleId { get; }
    public DayOfWeek DayOfWeek { get; }
    public int Seed { get; }
    public ScheduleTime CurrentTime { get; }
    public DailyLoopContext Context { get; }
    public AnchorLocationId Location { get; }
    public WellbeingState Wellbeing { get; }
    public ImmutableHashSet<ScheduleEntryId> HonoredCommitments { get; }
    public string? LessonChoiceId { get; }
    public string? WellbeingChoiceId { get; }
    public SocialClue? SocialClue { get; }
    public FutureHookCandidate? FutureHookCandidate { get; }
    public ImmutableArray<VisibleConsequence> VisibleConsequences { get; }
    public bool DayEnded { get; }

    public static GameState CreateSeeded(ScheduleId scheduleId, DayOfWeek dayOfWeek, int seed) => new(
        scheduleId,
        dayOfWeek,
        seed,
        ScheduleTime.FromHoursAndMinutes(6, 0),
        DailyLoopContext.BeforeSchool,
        new AnchorLocationId("dorm"),
        new WellbeingState(70, 20),
        ImmutableHashSet<ScheduleEntryId>.Empty,
        null,
        null,
        null,
        null,
        ImmutableArray<VisibleConsequence>.Empty,
        false);

    public GameState Apply(DailyLoopTransition transition)
    {
        ArgumentNullException.ThrowIfNull(transition);

        if (DayEnded)
            throw new InvalidOperationException("A completed day cannot receive another transition.");
        if (transition.CurrentTime.MinutesSinceMidnight < CurrentTime.MinutesSinceMidnight)
            throw new InvalidOperationException("Daily-loop time cannot move backwards.");

        var honoredCommitments = HonoredCommitments;
        if (transition.CommitmentsToHonor is not null)
            honoredCommitments = honoredCommitments.Union(transition.CommitmentsToHonor);

        var consequences = VisibleConsequences;
        if (transition.Consequence is not null)
            consequences = consequences.Add(transition.Consequence);

        return new GameState(
            ScheduleId,
            DayOfWeek,
            Seed,
            transition.CurrentTime,
            transition.Context,
            transition.Location,
            transition.Wellbeing ?? Wellbeing,
            honoredCommitments,
            transition.LessonChoiceId ?? LessonChoiceId,
            transition.WellbeingChoiceId ?? WellbeingChoiceId,
            transition.SocialClue ?? SocialClue,
            transition.FutureHookCandidate ?? FutureHookCandidate,
            consequences,
            DayEnded || transition.EndDay);
    }

    public string Fingerprint()
    {
        var commitments = string.Join(',', HonoredCommitments.Select(x => x.Value).Order(StringComparer.Ordinal));
        var consequences = string.Join(',', VisibleConsequences.Select(x => x.Id));
        return string.Join('|',
            ScheduleId.Value,
            DayOfWeek,
            Seed,
            CurrentTime.MinutesSinceMidnight,
            Context,
            Location.Value,
            Wellbeing.Energy,
            Wellbeing.Stress,
            commitments,
            LessonChoiceId ?? string.Empty,
            WellbeingChoiceId ?? string.Empty,
            SocialClue?.Id ?? string.Empty,
            FutureHookCandidate?.Id ?? string.Empty,
            consequences,
            DayEnded);
    }
}
