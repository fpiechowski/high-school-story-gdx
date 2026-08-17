using System.Collections.Immutable;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Text.RegularExpressions;
using HighSchoolStory.Application.Features.DailyLoop;
using HighSchoolStory.Application.Scenario;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;
using HighSchoolStory.Domain.Shared;

namespace HighSchoolStory.ScenarioRunner;

public sealed record ScenarioFixtureFailure(string Message);

public sealed class ScenarioFixtureLoader
{
    private static readonly Regex LowerKebabCase = new("^[a-z][a-z0-9]*(?:-[a-z0-9]+)*$", RegexOptions.CultureInvariant | RegexOptions.Compiled);
    private static readonly JsonSerializerOptions Options = new()
    {
        PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
        PropertyNameCaseInsensitive = false,
        UnmappedMemberHandling = JsonUnmappedMemberHandling.Disallow,
        RespectRequiredConstructorParameters = true,
    };

    public Result<DailyLoopScenarioDefinition, ScenarioFixtureFailure> Load(string fixturePath)
    {
        if (string.IsNullOrWhiteSpace(fixturePath) || !File.Exists(fixturePath))
            return Fail("Fixture path is required and must exist.");

        try
        {
            var dto = JsonSerializer.Deserialize<FixtureDto>(File.ReadAllText(fixturePath), Options);
            if (dto is null || dto.Commands is null)
                return Fail("Fixture must contain a non-empty commands array.");
            if (dto.SchemaVersion != 1)
                return Fail("Fixture schemaVersion must be 1.");
            EnsureId(dto.ScenarioId, "scenarioId");
            EnsureId(dto.ScheduleId, "scheduleId");
            if (string.IsNullOrWhiteSpace(dto.FixtureVersion))
                return Fail("Fixture fixtureVersion is required.");
            if (dto.Seed < 0)
                return Fail("Fixture seed must be non-negative.");
            if (dto.Commands.Count == 0)
                return Fail("Fixture commands must contain at least one command.");

            var ids = new HashSet<string>(StringComparer.Ordinal);
            var commands = new List<DailyLoopScenarioStep>();
            foreach (var command in dto.Commands)
            {
                if (command is null)
                    return Fail("Fixture command elements must not be null.");
                EnsureId(command.Id, "command id");
                if (!ids.Add(command.Id))
                    return Fail($"Fixture command ID '{command.Id}' is duplicated.");

                if (!TryParseCommandType(command.Type, out var commandType))
                    return Fail($"Fixture command '{command.Id}' has unsupported type '{command.Type}'.");
                if (!TryParseOutcome(command.ExpectedOutcome, out var expectedOutcome))
                    return Fail($"Fixture command '{command.Id}' has unsupported expectedOutcome '{command.ExpectedOutcome}'.");
                if (!HasRequiredFields(command, commandType))
                    return Fail($"Fixture command '{command.Id}' is missing fields required by '{command.Type}'.");
                EnsureOptionalId(command.TargetId, "targetId");
                EnsureOptionalId(command.ChoiceId, "choiceId");
                EnsureOptionalId(command.ClueId, "clueId");
                EnsureOptionalId(command.FutureHookId, "futureHookId");

                var snapshot = ParseSnapshot(command.Snapshot, command.Id, out var snapshotFailure);
                if (snapshotFailure is not null)
                    return Fail(snapshotFailure);
                if (!TryParseFailureCode(command.ExpectedFailureCode, out var expectedFailureCode))
                    return Fail($"Fixture command '{command.Id}' has unsupported expectedFailureCode '{command.ExpectedFailureCode}'.");
                if (expectedOutcome == ScenarioExpectedOutcome.Rejected && !expectedFailureCode.HasValue)
                    return Fail($"Fixture rejected command '{command.Id}' requires expectedFailureCode.");

                commands.Add(new(
                    command.Id,
                    commandType,
                    command.TargetId,
                    command.ChoiceId,
                    command.ClueId,
                    command.FutureHookId,
                    expectedOutcome,
                    expectedFailureCode,
                    snapshot));
            }

            return Result<DailyLoopScenarioDefinition, ScenarioFixtureFailure>.Ok(new(
                dto.ScenarioId,
                dto.FixtureVersion,
                dto.Seed,
                new ScheduleId(dto.ScheduleId),
                commands.ToImmutableArray()));
        }
        catch (JsonException exception)
        {
            return Fail($"Fixture JSON is invalid: {exception.Message}");
        }
        catch (ArgumentException exception)
        {
            return Fail($"Fixture shape is invalid: {exception.Message}");
        }
        catch (IOException exception)
        {
            return Fail($"Fixture could not be read: {exception.Message}");
        }
        catch (UnauthorizedAccessException exception)
        {
            return Fail($"Fixture could not be read: {exception.Message}");
        }
    }

    private static ScenarioSnapshotExpectation? ParseSnapshot(SnapshotDto? dto, string commandId, out string? failure)
    {
        failure = null;
        if (dto is null)
            return null;
        if (!TryParseTime(dto.CurrentTime, out var currentTime))
        {
            failure = $"Fixture command '{commandId}' snapshot currentTime must use exact HH:mm format.";
            return null;
        }
        if (!TryParseContext(dto.DayContext, out var context))
        {
            failure = $"Fixture command '{commandId}' snapshot dayContext '{dto.DayContext}' is unsupported.";
            return null;
        }
        if (!TryParseFeasibility(dto.Feasibility, out var feasibility))
        {
            failure = $"Fixture command '{commandId}' snapshot feasibility '{dto.Feasibility}' is unsupported.";
            return null;
        }
        if (!TryParseSeverity(dto.Severity, out var severity))
        {
            failure = $"Fixture command '{commandId}' snapshot severity '{dto.Severity}' is unsupported.";
            return null;
        }
        if (dto.NextKnownCommitment is not null)
            EnsureId(dto.NextKnownCommitment, "snapshot nextKnownCommitment");

        return new(
            currentTime,
            context,
            dto.NextKnownCommitment is null ? null : new ScheduleEntryId(dto.NextKnownCommitment),
            dto.AvailableTimeWindowMinutes,
            feasibility,
            dto.NextBoundaryText,
            dto.WarningBlockLabel,
            severity);
    }

    private static bool TryParseCommandType(string? value, out DailyLoopCommandType result)
    {
        result = value switch
        {
            "review-day-context" => DailyLoopCommandType.ReviewDayContext,
            "honor-mandatory-commitment" => DailyLoopCommandType.HonorMandatoryCommitment,
            "choose-lesson-action" => DailyLoopCommandType.ChooseLessonAction,
            "resolve-wellbeing-choice" => DailyLoopCommandType.ResolveWellbeingChoice,
            "progress-mandatory-commitments" => DailyLoopCommandType.ProgressMandatoryCommitments,
            "discover-social-touchpoint" => DailyLoopCommandType.DiscoverSocialTouchpoint,
            "attempt-blocked-action" => DailyLoopCommandType.AttemptBlockedAction,
            "end-day" => DailyLoopCommandType.EndDay,
            _ => default,
        };
        return value is "review-day-context" or "honor-mandatory-commitment" or "choose-lesson-action" or "resolve-wellbeing-choice" or "progress-mandatory-commitments" or "discover-social-touchpoint" or "attempt-blocked-action" or "end-day";
    }

    private static bool HasRequiredFields(CommandDto command, DailyLoopCommandType commandType) => commandType switch
    {
        DailyLoopCommandType.HonorMandatoryCommitment => !string.IsNullOrWhiteSpace(command.TargetId),
        DailyLoopCommandType.ChooseLessonAction => !string.IsNullOrWhiteSpace(command.TargetId) && !string.IsNullOrWhiteSpace(command.ChoiceId),
        DailyLoopCommandType.ResolveWellbeingChoice => !string.IsNullOrWhiteSpace(command.ChoiceId),
        DailyLoopCommandType.DiscoverSocialTouchpoint => !string.IsNullOrWhiteSpace(command.ClueId) && !string.IsNullOrWhiteSpace(command.FutureHookId),
        DailyLoopCommandType.AttemptBlockedAction => !string.IsNullOrWhiteSpace(command.TargetId),
        _ => true,
    };

    private static bool TryParseOutcome(string? value, out ScenarioExpectedOutcome result)
    {
        result = value switch
        {
            "success" => ScenarioExpectedOutcome.Success,
            "rejected" => ScenarioExpectedOutcome.Rejected,
            _ => default,
        };
        return value is "success" or "rejected";
    }

    private static bool TryParseFailureCode(string? value, out DailyLoopFailureCode? result)
    {
        result = value switch
        {
            null => null,
            "schedule-not-found" => DailyLoopFailureCode.ScheduleNotFound,
            "invalid-command-order" => DailyLoopFailureCode.InvalidCommandOrder,
            "mandatory-commitment" => DailyLoopFailureCode.MandatoryCommitment,
            "invalid-lesson-choice" => DailyLoopFailureCode.InvalidLessonChoice,
            "invalid-wellbeing-choice" => DailyLoopFailureCode.InvalidWellbeingChoice,
            "social-discovery-unavailable" => DailyLoopFailureCode.SocialDiscoveryUnavailable,
            "day-already-ended" => DailyLoopFailureCode.DayAlreadyEnded,
            "scenario-assertion" => DailyLoopFailureCode.ScenarioAssertion,
            _ => null,
        };
        return value is null or "schedule-not-found" or "invalid-command-order" or "mandatory-commitment" or "invalid-lesson-choice" or "invalid-wellbeing-choice" or "social-discovery-unavailable" or "day-already-ended" or "scenario-assertion";
    }

    private static bool TryParseTime(string? value, out ScheduleTime? result)
    {
        result = null;
        if (value is null)
            return true;
        if (!TimeOnly.TryParseExact(value, "HH:mm", System.Globalization.CultureInfo.InvariantCulture, System.Globalization.DateTimeStyles.None, out var time))
            return false;
        result = ScheduleTime.FromHoursAndMinutes(time.Hour, time.Minute);
        return true;
    }

    private static bool TryParseContext(string? value, out DailyLoopContext? result)
    {
        result = value switch
        {
            null => null,
            "before-school" => DailyLoopContext.BeforeSchool,
            "school" => DailyLoopContext.School,
            "after-school" => DailyLoopContext.AfterSchool,
            "dorm-wind-down" => DailyLoopContext.DormWindDown,
            "day-complete" => DailyLoopContext.DayComplete,
            _ => null,
        };
        return value is null or "before-school" or "school" or "after-school" or "dorm-wind-down" or "day-complete";
    }

    private static bool TryParseFeasibility(string? value, out FeasibilityStatus? result)
    {
        result = value switch
        {
            null => null,
            "fits" => FeasibilityStatus.Fits,
            "warning" => FeasibilityStatus.Warning,
            "blocked" => FeasibilityStatus.Blocked,
            _ => null,
        };
        return value is null or "fits" or "warning" or "blocked";
    }

    private static bool TryParseSeverity(string? value, out DailyLoopSeverity? result)
    {
        result = value switch
        {
            null => null,
            "informational" => DailyLoopSeverity.Informational,
            "warning" => DailyLoopSeverity.Warning,
            "blocked" => DailyLoopSeverity.Blocked,
            _ => null,
        };
        return value is null or "informational" or "warning" or "blocked";
    }

    private static void EnsureId(string value, string field)
    {
        if (string.IsNullOrWhiteSpace(value) || !LowerKebabCase.IsMatch(value))
            throw new ArgumentException($"Fixture {field} must use lower-kebab-case.");
    }

    private static void EnsureOptionalId(string? value, string field)
    {
        if (value is not null)
            EnsureId(value, field);
    }

    private static Result<DailyLoopScenarioDefinition, ScenarioFixtureFailure> Fail(string message) =>
        Result<DailyLoopScenarioDefinition, ScenarioFixtureFailure>.Fail(new ScenarioFixtureFailure(message));

    private sealed class FixtureDto
    {
        public required int SchemaVersion { get; init; }
        public required string ScenarioId { get; init; }
        public required string FixtureVersion { get; init; }
        public required int Seed { get; init; }
        public required string ScheduleId { get; init; }
        public required List<CommandDto?>? Commands { get; init; }
    }

    private sealed class CommandDto
    {
        public required string Id { get; init; }
        public required string Type { get; init; }
        public required string ExpectedOutcome { get; init; }
        public string? TargetId { get; init; }
        public string? ChoiceId { get; init; }
        public string? ClueId { get; init; }
        public string? FutureHookId { get; init; }
        public string? ExpectedFailureCode { get; init; }
        public SnapshotDto? Snapshot { get; init; }
    }

    private sealed class SnapshotDto
    {
        public string? CurrentTime { get; init; }
        public string? DayContext { get; init; }
        public string? NextKnownCommitment { get; init; }
        public int? AvailableTimeWindowMinutes { get; init; }
        public string? Feasibility { get; init; }
        public string? NextBoundaryText { get; init; }
        public string? WarningBlockLabel { get; init; }
        public string? Severity { get; init; }
    }
}
