using System.Text.Json;
using HighSchoolStory.Application.Features.DailyLoop;
using HighSchoolStory.Application.Scenario;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;

namespace HighSchoolStory.ScenarioRunner;

public static class ScenarioReportFormatter
{
    public static string ToJson(DailyLoopScenarioReport report)
    {
        var payload = new
        {
            scenarioId = report.ScenarioId,
            fixtureVersion = report.FixtureVersion,
            seed = report.Seed,
            scheduleId = report.ScheduleId.Value,
            commands = report.Commands.Select(ToCommand).ToArray(),
            timeTransitions = report.TimeTransitions.Select(x => new
            {
                commandId = x.CommandId,
                before = FormatTime(x.Before),
                after = FormatTime(x.After),
            }).ToArray(),
            commitmentsHonored = report.CommitmentsHonored.Select(x => x.Value).ToArray(),
            blockedChoiceChecks = report.BlockedChoiceChecks.Select(x => new
            {
                commandId = x.CommandId,
                failureCode = FormatFailureCode(x.FailureCode),
                stateFingerprintBefore = x.StateFingerprintBefore,
                stateFingerprintAfter = x.StateFingerprintAfter,
                stateUnchanged = x.StateUnchanged,
            }).ToArray(),
            snapshots = report.Snapshots.Select(ToSnapshot).ToArray(),
            finalDayState = ToState(report.FinalDayState),
            finalStateFingerprint = report.FinalStateFingerprint,
        };

        return JsonSerializer.Serialize(payload, new JsonSerializerOptions { WriteIndented = true });
    }

    private static object ToCommand(ScenarioCommandReport command) => new
    {
        commandId = command.CommandId,
        type = FormatCommandType(command.CommandType),
        outcome = command.Outcome == ScenarioExpectedOutcome.Success ? "success" : "rejected",
        timeBefore = FormatTime(command.TimeBefore),
        timeAfter = FormatTime(command.TimeAfter),
        expectedFailureCode = command.ExpectedFailureCode.HasValue ? FormatFailureCode(command.ExpectedFailureCode.Value) : null,
        failureCode = command.FailureCode.HasValue ? FormatFailureCode(command.FailureCode.Value) : null,
        evidenceId = command.EvidenceId,
        snapshot = ToSnapshot(command.ReadModel.DecisionSnapshot),
    };

    private static object ToSnapshot(DecisionSnapshot snapshot) => new
    {
        currentTime = FormatTime(snapshot.CurrentTime),
        dayContext = FormatContext(snapshot.DayContext),
        nextKnownCommitment = snapshot.NextKnownCommitment?.Value,
        availableTimeWindowMinutes = snapshot.AvailableTimeWindowMinutes,
        feasibility = FormatFeasibility(snapshot.Feasibility),
        nextBoundaryText = snapshot.NextBoundaryText,
        warningBlockLabel = snapshot.WarningBlockLabel,
        severity = FormatSeverity(snapshot.Severity),
    };

    private static object ToState(HighSchoolStory.Application.Features.DailyLoop.DailyLoopReadModel state) => new
    {
        currentTime = FormatTime(state.CurrentTime),
        dayContext = FormatContext(state.DayContext),
        location = state.Location.Value,
        wellbeing = new { energy = state.Wellbeing.Energy, stress = state.Wellbeing.Stress },
        honoredCommitments = state.HonoredCommitments.Select(x => x.Value).ToArray(),
        visibleConsequences = state.VisibleConsequences.Select(x => new { id = x.Id, playerLabel = x.PlayerLabel }).ToArray(),
        socialClue = state.SocialClue is null ? null : new { id = state.SocialClue.Id, playerLabel = state.SocialClue.PlayerLabel },
        futureHookCandidate = state.FutureHookCandidate is null ? null : new { id = state.FutureHookCandidate.Id, playerLabel = state.FutureHookCandidate.PlayerLabel },
        dayEnded = state.DayEnded,
        stateFingerprint = state.StateFingerprint,
    };

    private static string FormatTime(ScheduleTime time) => $"{time.MinutesSinceMidnight / 60:00}:{time.MinutesSinceMidnight % 60:00}";

    private static string FormatCommandType(DailyLoopCommandType type) => type switch
    {
        DailyLoopCommandType.ReviewDayContext => "review-day-context",
        DailyLoopCommandType.HonorMandatoryCommitment => "honor-mandatory-commitment",
        DailyLoopCommandType.ChooseLessonAction => "choose-lesson-action",
        DailyLoopCommandType.ResolveWellbeingChoice => "resolve-wellbeing-choice",
        DailyLoopCommandType.ProgressMandatoryCommitments => "progress-mandatory-commitments",
        DailyLoopCommandType.DiscoverSocialTouchpoint => "discover-social-touchpoint",
        DailyLoopCommandType.AttemptBlockedAction => "attempt-blocked-action",
        DailyLoopCommandType.EndDay => "end-day",
        _ => throw new ArgumentOutOfRangeException(nameof(type), type, null),
    };

    private static string FormatContext(DailyLoopContext context) => context switch
    {
        DailyLoopContext.BeforeSchool => "before-school",
        DailyLoopContext.School => "school",
        DailyLoopContext.AfterSchool => "after-school",
        DailyLoopContext.DormWindDown => "dorm-wind-down",
        DailyLoopContext.DayComplete => "day-complete",
        _ => throw new ArgumentOutOfRangeException(nameof(context), context, null),
    };

    private static string FormatFeasibility(FeasibilityStatus feasibility) => feasibility switch
    {
        FeasibilityStatus.Fits => "fits",
        FeasibilityStatus.Warning => "warning",
        FeasibilityStatus.Blocked => "blocked",
        _ => throw new ArgumentOutOfRangeException(nameof(feasibility), feasibility, null),
    };

    private static string FormatSeverity(DailyLoopSeverity severity) => severity switch
    {
        DailyLoopSeverity.Informational => "informational",
        DailyLoopSeverity.Warning => "warning",
        DailyLoopSeverity.Blocked => "blocked",
        _ => throw new ArgumentOutOfRangeException(nameof(severity), severity, null),
    };

    private static string FormatFailureCode(DailyLoopFailureCode code) => code switch
    {
        DailyLoopFailureCode.ScheduleNotFound => "schedule-not-found",
        DailyLoopFailureCode.InvalidCommandOrder => "invalid-command-order",
        DailyLoopFailureCode.MandatoryCommitment => "mandatory-commitment",
        DailyLoopFailureCode.InvalidLessonChoice => "invalid-lesson-choice",
        DailyLoopFailureCode.InvalidWellbeingChoice => "invalid-wellbeing-choice",
        DailyLoopFailureCode.SocialDiscoveryUnavailable => "social-discovery-unavailable",
        DailyLoopFailureCode.DayAlreadyEnded => "day-already-ended",
        DailyLoopFailureCode.ScenarioAssertion => "scenario-assertion",
        _ => throw new ArgumentOutOfRangeException(nameof(code), code, null),
    };
}
