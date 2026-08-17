using System.Collections.Immutable;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;

namespace HighSchoolStory.Application.Features.DailyLoop;

public enum FeasibilityStatus
{
    Fits,
    Warning,
    Blocked,
}

public sealed record DecisionSnapshot(
    ScheduleTime CurrentTime,
    DailyLoopContext DayContext,
    ScheduleEntryId? NextKnownCommitment,
    int AvailableTimeWindowMinutes,
    FeasibilityStatus Feasibility,
    string NextBoundaryText,
    string WarningBlockLabel,
    DailyLoopSeverity Severity,
    ScheduleTime? NextBoundary)
{
    public DecisionSnapshot Snapshot => this;
}

public sealed record DailyLoopReadModel(
    ScheduleId ScheduleId,
    DayOfWeek DayOfWeek,
    int Seed,
    ScheduleTime CurrentTime,
    DailyLoopContext DayContext,
    AnchorLocationId Location,
    WellbeingState Wellbeing,
    ImmutableArray<ScheduleEntryId> HonoredCommitments,
    ImmutableArray<VisibleConsequence> VisibleConsequences,
    SocialClue? SocialClue,
    FutureHookCandidate? FutureHookCandidate,
    bool DayEnded,
    string StateFingerprint,
    DecisionSnapshot DecisionSnapshot)
{
    public DecisionSnapshot Snapshot => DecisionSnapshot;
}

public sealed record DailyLoopCommandResult(
    string CommandId,
    DailyLoopCommandType CommandType,
    ScheduleTime TimeBefore,
    ScheduleTime TimeAfter,
    ImmutableArray<ScheduleEntryId> CommitmentsHonored,
    string? EvidenceId,
    DailyLoopReadModel ReadModel)
{
    public DecisionSnapshot Snapshot => ReadModel.DecisionSnapshot;
}
