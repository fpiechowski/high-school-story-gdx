using System.Collections.Immutable;
using HighSchoolStory.Application.Features.DailyLoop;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;

namespace HighSchoolStory.Application.Scenario;

public enum ScenarioExpectedOutcome
{
    Success,
    Rejected,
}

public sealed record ScenarioSnapshotExpectation(
    ScheduleTime? CurrentTime = null,
    DailyLoopContext? DayContext = null,
    ScheduleEntryId? NextKnownCommitment = null,
    int? AvailableTimeWindowMinutes = null,
    FeasibilityStatus? Feasibility = null,
    string? NextBoundaryText = null,
    string? WarningBlockLabel = null,
    DailyLoopSeverity? Severity = null);

public sealed record DailyLoopScenarioStep(
    string CommandId,
    DailyLoopCommandType CommandType,
    string? TargetId = null,
    string? ChoiceId = null,
    string? ClueId = null,
    string? FutureHookId = null,
    ScenarioExpectedOutcome ExpectedOutcome = ScenarioExpectedOutcome.Success,
    DailyLoopFailureCode? ExpectedFailureCode = null,
    ScenarioSnapshotExpectation? SnapshotExpectation = null);

public sealed record DailyLoopScenarioDefinition(
    string ScenarioId,
    string FixtureVersion,
    int Seed,
    ScheduleId ScheduleId,
    ImmutableArray<DailyLoopScenarioStep> Steps);

public sealed record ScenarioCommandReport(
    string CommandId,
    DailyLoopCommandType CommandType,
    ScenarioExpectedOutcome Outcome,
    ScheduleTime TimeBefore,
    ScheduleTime TimeAfter,
    DailyLoopFailureCode? ExpectedFailureCode,
    DailyLoopFailureCode? FailureCode,
    string? EvidenceId,
    DailyLoopReadModel ReadModel);

public sealed record ScenarioTimeTransition(
    string CommandId,
    ScheduleTime Before,
    ScheduleTime After);

public sealed record ScenarioBlockedChoiceCheck(
    string CommandId,
    DailyLoopFailureCode FailureCode,
    string StateFingerprintBefore,
    string StateFingerprintAfter,
    bool StateUnchanged);

public sealed record DailyLoopScenarioReport(
    string ScenarioId,
    string FixtureVersion,
    int Seed,
    ScheduleId ScheduleId,
    ImmutableArray<ScenarioCommandReport> Commands,
    ImmutableArray<ScenarioTimeTransition> TimeTransitions,
    ImmutableArray<ScheduleEntryId> CommitmentsHonored,
    ImmutableArray<ScenarioBlockedChoiceCheck> BlockedChoiceChecks,
    ImmutableArray<DecisionSnapshot> Snapshots,
    DailyLoopReadModel FinalDayState,
    string FinalStateFingerprint);

public enum ScenarioExecutionFailureCode
{
    InvalidDefinition,
    ScheduleUnavailable,
    AssertionFailed,
}

public sealed record ScenarioExecutionFailure(
    ScenarioExecutionFailureCode Code,
    string Message,
    string? CommandId = null);
