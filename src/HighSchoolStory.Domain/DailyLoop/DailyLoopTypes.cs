namespace HighSchoolStory.Domain.DailyLoop;

public enum DailyLoopContext
{
    BeforeSchool,
    School,
    AfterSchool,
    DormWindDown,
    DayComplete,
}

public enum DailyLoopSeverity
{
    Informational,
    Warning,
    Blocked,
}

public enum DailyLoopFailureCode
{
    ScheduleNotFound,
    InvalidCommandOrder,
    MandatoryCommitment,
    InvalidLessonChoice,
    InvalidWellbeingChoice,
    SocialDiscoveryUnavailable,
    DayAlreadyEnded,
    ScenarioAssertion,
}

public readonly record struct WellbeingState
{
    public WellbeingState(int energy, int stress)
    {
        if (energy is < 0 or > 100)
            throw new ArgumentOutOfRangeException(nameof(energy));
        if (stress is < 0 or > 100)
            throw new ArgumentOutOfRangeException(nameof(stress));

        Energy = energy;
        Stress = stress;
    }

    public int Energy { get; }
    public int Stress { get; }

    public WellbeingState Adjust(int energyDelta, int stressDelta) => new(
        Clamp(Energy, energyDelta),
        Clamp(Stress, stressDelta));

    private static int Clamp(int current, int delta) => (int)Math.Clamp((long)current + delta, 0L, 100L);
}

public sealed record VisibleConsequence
{
    public VisibleConsequence(string id, string playerLabel)
    {
        Id = RequireValue(id, nameof(id));
        PlayerLabel = RequireValue(playerLabel, nameof(playerLabel));
    }

    public string Id { get; }
    public string PlayerLabel { get; }

    private static string RequireValue(string value, string name) =>
        string.IsNullOrWhiteSpace(value)
            ? throw new ArgumentException("A value is required.", name)
            : value;
}

public sealed record SocialClue
{
    public SocialClue(string id, string playerLabel)
    {
        Id = RequireValue(id, nameof(id));
        PlayerLabel = RequireValue(playerLabel, nameof(playerLabel));
    }

    public string Id { get; }
    public string PlayerLabel { get; }

    private static string RequireValue(string value, string name) =>
        string.IsNullOrWhiteSpace(value)
            ? throw new ArgumentException("A value is required.", name)
            : value;
}

public sealed record FutureHookCandidate
{
    public FutureHookCandidate(string id, string playerLabel)
    {
        Id = RequireValue(id, nameof(id));
        PlayerLabel = RequireValue(playerLabel, nameof(playerLabel));
    }

    public string Id { get; }
    public string PlayerLabel { get; }

    private static string RequireValue(string value, string name) =>
        string.IsNullOrWhiteSpace(value)
            ? throw new ArgumentException("A value is required.", name)
            : value;
}

public sealed record DailyLoopFailure(
    DailyLoopFailureCode Code,
    string Message,
    string PlayerFacingLabel,
    DailyLoopSeverity Severity)
{
    public static DailyLoopFailure Blocked(DailyLoopFailureCode code, string message) =>
        new(code, message, message, DailyLoopSeverity.Blocked);

    public static DailyLoopFailure Invalid(DailyLoopFailureCode code, string message) =>
        new(code, message, message, DailyLoopSeverity.Warning);
}
