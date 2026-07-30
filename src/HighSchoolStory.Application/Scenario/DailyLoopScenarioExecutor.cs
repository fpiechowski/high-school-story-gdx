using System.Collections.Immutable;
using HighSchoolStory.Application.Features.DailyLoop;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;
using HighSchoolStory.Domain.Shared;
using HighSchoolStory.Ports.Content;
using HighSchoolStory.Ports.Time;

namespace HighSchoolStory.Application.Scenario;

public sealed class DailyLoopScenarioExecutor
{
    private readonly IDailyScheduleRepository _scheduleRepository;
    private readonly Func<IClock> _clockFactory;
    private readonly Func<int, IRandomSource> _randomFactory;

    public DailyLoopScenarioExecutor(
        IDailyScheduleRepository scheduleRepository,
        Func<IClock> clockFactory,
        Func<int, IRandomSource> randomFactory)
    {
        _scheduleRepository = scheduleRepository ?? throw new ArgumentNullException(nameof(scheduleRepository));
        _clockFactory = clockFactory ?? throw new ArgumentNullException(nameof(clockFactory));
        _randomFactory = randomFactory ?? throw new ArgumentNullException(nameof(randomFactory));
    }

    public Result<DailyLoopScenarioReport, ScenarioExecutionFailure> Execute(DailyLoopScenarioDefinition definition)
    {
        if (string.IsNullOrWhiteSpace(definition.ScenarioId) ||
            string.IsNullOrWhiteSpace(definition.FixtureVersion) ||
            definition.Seed < 0 || definition.Steps.IsDefaultOrEmpty)
        {
            return Fail(ScenarioExecutionFailureCode.InvalidDefinition, "Scenario definition requires identity, a non-negative seed, and at least one command.");
        }

        var session = new DailyLoopSession(
            _scheduleRepository,
            definition.ScheduleId,
            _clockFactory(),
            _randomFactory(definition.Seed));
        var commandReports = new List<ScenarioCommandReport>();
        var transitions = new List<ScenarioTimeTransition>();
        var blockedChecks = new List<ScenarioBlockedChoiceCheck>();

        foreach (var step in definition.Steps)
        {
            if (string.IsNullOrWhiteSpace(step.CommandId))
                return Fail(ScenarioExecutionFailureCode.InvalidDefinition, "Every scenario command requires a stable command ID.");

            var command = CreateCommand(step, out var commandFailure);
            if (command is null)
                return Fail(ScenarioExecutionFailureCode.InvalidDefinition, commandFailure!, step.CommandId);

            var fingerprintBefore = session.CurrentStateFingerprint;
            var result = session.Execute(command);
            if (step.ExpectedOutcome == ScenarioExpectedOutcome.Success)
            {
                if (!result.IsSuccess)
                    return Fail(ScenarioExecutionFailureCode.AssertionFailed, $"Command '{step.CommandId}' was rejected: {result.Failure!.Message}", step.CommandId);

                var commandResult = result.Success!;
                var snapshotFailure = ValidateSnapshot(step.SnapshotExpectation, commandResult.Snapshot);
                if (snapshotFailure is not null)
                    return Fail(ScenarioExecutionFailureCode.AssertionFailed, snapshotFailure, step.CommandId);

                commandReports.Add(new(
                    step.CommandId,
                    command.Type,
                    ScenarioExpectedOutcome.Success,
                    commandResult.TimeBefore,
                    commandResult.TimeAfter,
                    null,
                    commandResult.EvidenceId,
                    commandResult.ReadModel));
                transitions.Add(new(step.CommandId, commandResult.TimeBefore, commandResult.TimeAfter));
                continue;
            }

            if (result.IsSuccess)
                return Fail(ScenarioExecutionFailureCode.AssertionFailed, $"Command '{step.CommandId}' was expected to be rejected but succeeded.", step.CommandId);

            var failure = result.Failure!;
            if (step.ExpectedFailureCode.HasValue && step.ExpectedFailureCode.Value != failure.Code)
                return Fail(ScenarioExecutionFailureCode.AssertionFailed, $"Command '{step.CommandId}' returned '{failure.Code}' instead of expected '{step.ExpectedFailureCode.Value}'.", step.CommandId);

            var fingerprintAfter = session.CurrentStateFingerprint;
            var unchanged = string.Equals(fingerprintBefore, fingerprintAfter, StringComparison.Ordinal);
            if (!unchanged)
                return Fail(ScenarioExecutionFailureCode.AssertionFailed, $"Rejected command '{step.CommandId}' changed canonical state.", step.CommandId);

            var readModel = session.CurrentReadModel;
            var snapshotFailureForRejection = ValidateSnapshot(step.SnapshotExpectation, readModel.DecisionSnapshot);
            if (snapshotFailureForRejection is not null)
                return Fail(ScenarioExecutionFailureCode.AssertionFailed, snapshotFailureForRejection, step.CommandId);

            commandReports.Add(new(
                step.CommandId,
                command.Type,
                ScenarioExpectedOutcome.Rejected,
                readModel.CurrentTime,
                readModel.CurrentTime,
                failure.Code,
                null,
                readModel));
            transitions.Add(new(step.CommandId, readModel.CurrentTime, readModel.CurrentTime));
            blockedChecks.Add(new(step.CommandId, failure.Code, fingerprintBefore, fingerprintAfter, unchanged));
        }

        var finalDayState = session.CurrentReadModel;
        if (!finalDayState.DayEnded)
            return Fail(ScenarioExecutionFailureCode.AssertionFailed, "The scenario must end with a completed day.");

        return Result<DailyLoopScenarioReport, ScenarioExecutionFailure>.Ok(new(
            definition.ScenarioId,
            definition.FixtureVersion,
            definition.Seed,
            definition.ScheduleId,
            commandReports.ToImmutableArray(),
            transitions.ToImmutableArray(),
            commandReports
                .SelectMany(x => x.ReadModel.HonoredCommitments)
                .Distinct()
                .OrderBy(x => x.Value, StringComparer.Ordinal)
                .ToImmutableArray(),
            blockedChecks.ToImmutableArray(),
            commandReports.Select(x => x.ReadModel.DecisionSnapshot).ToImmutableArray(),
            finalDayState,
            finalDayState.StateFingerprint));
    }

    private static DailyLoopCommand? CreateCommand(DailyLoopScenarioStep step, out string? failure)
    {
        failure = null;
        switch (step.CommandType)
        {
            case DailyLoopCommandType.ReviewDayContext:
                return new ReviewDayContextCommand(step.CommandId);
            case DailyLoopCommandType.HonorMandatoryCommitment when !string.IsNullOrWhiteSpace(step.TargetId):
                return new HonorMandatoryCommitmentCommand(step.CommandId, new ScheduleEntryId(step.TargetId));
            case DailyLoopCommandType.ChooseLessonAction when !string.IsNullOrWhiteSpace(step.TargetId) && !string.IsNullOrWhiteSpace(step.ChoiceId):
                return new ChooseLessonActionCommand(step.CommandId, new ScheduleEntryId(step.TargetId), step.ChoiceId);
            case DailyLoopCommandType.ResolveWellbeingChoice when !string.IsNullOrWhiteSpace(step.ChoiceId):
                return new ResolveWellbeingChoiceCommand(step.CommandId, step.ChoiceId);
            case DailyLoopCommandType.ProgressMandatoryCommitments:
                return new ProgressMandatoryCommitmentsCommand(step.CommandId);
            case DailyLoopCommandType.DiscoverSocialTouchpoint when !string.IsNullOrWhiteSpace(step.ClueId) && !string.IsNullOrWhiteSpace(step.FutureHookId):
                return new DiscoverSocialTouchpointCommand(step.CommandId, step.ClueId, step.FutureHookId);
            case DailyLoopCommandType.AttemptBlockedAction when !string.IsNullOrWhiteSpace(step.TargetId):
                return new AttemptBlockedActionCommand(step.CommandId, step.TargetId);
            case DailyLoopCommandType.EndDay:
                return new EndDayCommand(step.CommandId);
            default:
                failure = $"Scenario command '{step.CommandId}' is missing the fields required by '{step.CommandType}'.";
                return null;
        }
    }

    private static string? ValidateSnapshot(ScenarioSnapshotExpectation? expected, DecisionSnapshot actual)
    {
        if (expected is null)
            return null;
        if (expected.CurrentTime.HasValue && expected.CurrentTime.Value != actual.CurrentTime)
            return $"Expected snapshot time '{expected.CurrentTime}' but received '{actual.CurrentTime}'.";
        if (expected.DayContext.HasValue && expected.DayContext.Value != actual.DayContext)
            return $"Expected snapshot context '{expected.DayContext}' but received '{actual.DayContext}'.";
        if (expected.NextKnownCommitment.HasValue && expected.NextKnownCommitment.Value != actual.NextKnownCommitment)
            return $"Expected next commitment '{expected.NextKnownCommitment}' but received '{actual.NextKnownCommitment}'.";
        if (expected.AvailableTimeWindowMinutes.HasValue && expected.AvailableTimeWindowMinutes.Value != actual.AvailableTimeWindowMinutes)
            return $"Expected available window '{expected.AvailableTimeWindowMinutes}' but received '{actual.AvailableTimeWindowMinutes}'.";
        if (expected.Feasibility.HasValue && expected.Feasibility.Value != actual.Feasibility)
            return $"Expected feasibility '{expected.Feasibility}' but received '{actual.Feasibility}'.";
        if (expected.NextBoundaryText is not null && !string.Equals(expected.NextBoundaryText, actual.NextBoundaryText, StringComparison.Ordinal))
            return $"Expected next-boundary text '{expected.NextBoundaryText}' but received '{actual.NextBoundaryText}'.";
        if (expected.WarningBlockLabel is not null && !string.Equals(expected.WarningBlockLabel, actual.WarningBlockLabel, StringComparison.Ordinal))
            return $"Expected warning/block label '{expected.WarningBlockLabel}' but received '{actual.WarningBlockLabel}'.";
        if (expected.Severity.HasValue && expected.Severity.Value != actual.Severity)
            return $"Expected severity '{expected.Severity}' but received '{actual.Severity}'.";
        return null;
    }

    private static Result<DailyLoopScenarioReport, ScenarioExecutionFailure> Fail(ScenarioExecutionFailureCode code, string message, string? commandId = null) =>
        Result<DailyLoopScenarioReport, ScenarioExecutionFailure>.Fail(new(code, message, commandId));
}
