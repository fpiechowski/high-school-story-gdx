using System.Globalization;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Text.RegularExpressions;
using HighSchoolStory.Content.Catalog;
using HighSchoolStory.Content.Validation;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.Shared;

namespace HighSchoolStory.Content.Loading;

public sealed class DailyScheduleLoader
{
    private const string TravelTimesFileName = "travel-times.json";
    private const string VerticalSliceProfile = "vertical-slice";
    private static readonly Regex LowerKebabCase = new("^[a-z][a-z0-9]*(?:-[a-z0-9]+)*$", RegexOptions.CultureInvariant | RegexOptions.Compiled);

    private static readonly JsonSerializerOptions Options = new()
    {
        PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
        PropertyNameCaseInsensitive = false,
        UnmappedMemberHandling = JsonUnmappedMemberHandling.Disallow,
        RespectRequiredConstructorParameters = true,
    };

    static DailyScheduleLoader() => Options.Converters.Add(new JsonStringEnumConverter(JsonNamingPolicy.KebabCaseLower, false));

    public Result<ContentCatalog, ContentLoadFailure> Load(string contentPath) => Load(contentPath, VerticalSliceProfile);

    public Result<ContentCatalog, ContentLoadFailure> Load(string contentPath, string profile)
    {
        if (!string.Equals(profile, VerticalSliceProfile, StringComparison.Ordinal))
        {
            return Result<ContentCatalog, ContentLoadFailure>.Fail(ContentLoadFailure.Create([
                new(IssueSeverity.Error, FailureCategory.Shape, null, contentPath, ContentLoadRuleIds.ProfileUnsupported, null, $"Profile '{profile}' is not supported. Use '{VerticalSliceProfile}'.", null),
            ]));
        }

        var issues = new List<ContentIssue>();
        var schedules = new List<DailySchedule>();
        var scheduleIds = new HashSet<string>(StringComparer.Ordinal);
        var calendar = Path.Combine(contentPath, "calendar");
        var travelTimes = LoadTravelTimes(calendar, issues);
        IReadOnlyList<string> schedulePaths;
        try
        {
            schedulePaths = Directory.Exists(calendar)
                ? Directory.EnumerateFiles(calendar, "*.json")
                    .Where(x => !string.Equals(Path.GetFileName(x), TravelTimesFileName, StringComparison.Ordinal))
                    .OrderBy(x => x, StringComparer.Ordinal)
                    .ToArray()
                : [];
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException or NotSupportedException)
        {
            issues.Add(ReadIssue(calendar, ex));
            schedulePaths = [];
        }

        foreach (var path in schedulePaths)
        {
            string? recoveredScheduleId = null;
            try
            {
                var json = File.ReadAllText(path);
                recoveredScheduleId = TryRecoverScheduleId(json);
                var dto = JsonSerializer.Deserialize<ScheduleDto>(json, Options) ?? throw new JsonException("Schedule document was empty.");
                if (dto.SchemaVersion != 1) throw new JsonException("Unsupported schema version.");
                if (dto.Entries is null || dto.Entries.Any(x => x is null)) throw new JsonException("Schedule entries must not be null.");
                EnsureContentId(dto.Id, "schedule ID");
                var entryIds = new HashSet<string>(StringComparer.Ordinal);
                var entries = new List<ScheduleEntry>();
                foreach (var entryDto in dto.Entries)
                {
                    EnsureContentId(entryDto.Id, "schedule entry ID");
                    if (!entryIds.Add(entryDto.Id))
                    {
                        issues.Add(new(IssueSeverity.Error, FailureCategory.Shape, dto.Id, path, ScheduleValidationRuleIds.DuplicateEntryId, null, $"Schedule entry ID '{entryDto.Id}' is duplicated.", "Give each schedule entry a unique ID."));
                        continue;
                    }

                    var start = ParseStart(entryDto.Start);
                    EnsureContentId(entryDto.AnchorLocationId, "anchor location ID");
                    entries.Add(new ScheduleEntry(new(entryDto.Id), entryDto.Kind, start, new(entryDto.DurationMinutes), new(entryDto.AnchorLocationId)));
                }
                var schedule = new DailySchedule(new(dto.Id), dto.DayOfWeek, entries);
                issues.AddRange(new DailyScheduleValidator().Validate(schedule, path, travelTimes));
                if (!scheduleIds.Add(schedule.Id.Value))
                {
                    issues.Add(new(IssueSeverity.Error, FailureCategory.Shape, schedule.Id.Value, path, ContentLoadRuleIds.ScheduleInvalid, null, $"Schedule ID '{schedule.Id.Value}' is duplicated.", "Give each schedule document a unique ID."));
                }
                else
                {
                    schedules.Add(schedule);
                }
            }
            catch (JsonException ex) { issues.Add(new(IssueSeverity.Error, FailureCategory.Shape, recoveredScheduleId, path, ContentLoadRuleIds.JsonInvalid, null, ex.Message, null)); }
            catch (ArgumentException ex) { issues.Add(new(IssueSeverity.Error, FailureCategory.Shape, recoveredScheduleId, path, ContentLoadRuleIds.ScheduleInvalid, null, ex.Message, null)); }
            catch (Exception ex) when (ex is IOException or UnauthorizedAccessException or NotSupportedException) { issues.Add(ReadIssue(path, ex)); }
        }
        if (schedulePaths.Count == 0)
        {
            var sourcePath = Path.Combine(calendar, "*.json");
            issues.Add(new(IssueSeverity.Error, FailureCategory.Shape, null, sourcePath, ContentLoadRuleIds.ScheduleInvalid, null, "At least one daily schedule document is required.", "Add a daily schedule JSON document under the calendar content directory."));
        }

        var failure = ContentLoadFailure.Create(issues);
        return failure.HasErrors ? Result<ContentCatalog, ContentLoadFailure>.Fail(failure) : Result<ContentCatalog, ContentLoadFailure>.Ok(new ContentCatalog(schedules));
    }

    private static IReadOnlyList<TravelTime> LoadTravelTimes(string calendar, List<ContentIssue> issues)
    {
        var path = Path.Combine(calendar, TravelTimesFileName);
        if (!File.Exists(path))
        {
            issues.Add(new(IssueSeverity.Error, FailureCategory.Shape, null, path, ContentLoadRuleIds.ScheduleInvalid, null, $"Required travel-time document '{TravelTimesFileName}' was not found.", $"Add '{TravelTimesFileName}' under the calendar content directory."));
            return [];
        }

        try
        {
            var dto = JsonSerializer.Deserialize<TravelTimesDto>(File.ReadAllText(path), Options) ?? throw new JsonException("Travel-time document was empty.");
            if (dto.SchemaVersion != 1) throw new JsonException("Unsupported travel-time schema version.");
            if (dto.TravelTimes is null || dto.TravelTimes.Any(x => x is null)) throw new JsonException("Travel-time entries must not be null.");
            return dto.TravelTimes.Select(x =>
            {
                EnsureContentId(x.From, "travel-time source location ID");
                EnsureContentId(x.To, "travel-time destination location ID");
                return new TravelTime(new(x.From), new(x.To), new(x.MinimumTravelMinutes));
            }).ToArray();
        }
        catch (JsonException ex)
        {
            issues.Add(new(IssueSeverity.Error, FailureCategory.Shape, null, path, ContentLoadRuleIds.JsonInvalid, null, ex.Message, null));
            return [];
        }
        catch (ArgumentException ex)
        {
            issues.Add(new(IssueSeverity.Error, FailureCategory.Shape, null, path, ContentLoadRuleIds.ScheduleInvalid, null, ex.Message, null));
            return [];
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException or NotSupportedException)
        {
            issues.Add(ReadIssue(path, ex));
            return [];
        }
    }

    private static ScheduleTime ParseStart(string value)
    {
        if (!TimeOnly.TryParseExact(value, "HH:mm", CultureInfo.InvariantCulture, DateTimeStyles.None, out var start))
            throw new JsonException($"Schedule entry start '{SanitizeDiagnosticValue(value)}' must use exact HH:mm format.");

        return ScheduleTime.FromHoursAndMinutes(start.Hour, start.Minute);
    }

    private static string? TryRecoverScheduleId(string json)
    {
        try
        {
            using var document = JsonDocument.Parse(json);
            return document.RootElement.ValueKind == JsonValueKind.Object &&
                document.RootElement.TryGetProperty("id", out var id) &&
                id.ValueKind == JsonValueKind.String
                ? id.GetString()
                : null;
        }
        catch (JsonException)
        {
            return null;
        }
    }

    private static string SanitizeDiagnosticValue(string? value) =>
        (value ?? "<null>").Replace("\r", "\\r", StringComparison.Ordinal).Replace("\n", "\\n", StringComparison.Ordinal);

    private static void EnsureContentId(string value, string label)
    {
        if (string.IsNullOrWhiteSpace(value) || !LowerKebabCase.IsMatch(value))
            throw new JsonException($"A {label} must use lower-kebab-case.");
    }

    private static ContentIssue ReadIssue(string path, Exception exception) =>
        new(IssueSeverity.Error, FailureCategory.Read, null, path, ContentLoadRuleIds.ContentReadFailed, null, $"Could not read content: {exception.Message}", "Ensure the content path and files are readable.");

    private sealed class ScheduleDto
    {
        public required int SchemaVersion { get; init; }
        public required string Id { get; init; }
        public required DayOfWeek DayOfWeek { get; init; }
        public required List<EntryDto>? Entries { get; init; }
    }
    private sealed class TravelTimesDto
    {
        public required int SchemaVersion { get; init; }
        public required List<TravelTimeDto>? TravelTimes { get; init; }
    }
    private sealed class TravelTimeDto
    {
        public required string From { get; init; }
        public required string To { get; init; }
        public required int MinimumTravelMinutes { get; init; }
    }
    private sealed class EntryDto
    {
        public required string Id { get; init; }
        public required ScheduleEntryKind Kind { get; init; }
        public required string Start { get; init; }
        public required int DurationMinutes { get; init; }
        public required string AnchorLocationId { get; init; }
    }
}
