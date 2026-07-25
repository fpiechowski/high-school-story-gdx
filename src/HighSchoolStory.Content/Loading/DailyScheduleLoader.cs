using System.Text.Json;
using System.Text.Json.Serialization;
using HighSchoolStory.Content.Catalog;
using HighSchoolStory.Content.Validation;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.Shared;

namespace HighSchoolStory.Content.Loading;

public sealed class DailyScheduleLoader
{
    private const string TravelTimesFileName = "travel-times.json";

    private static readonly JsonSerializerOptions Options = new()
    {
        PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
        PropertyNameCaseInsensitive = false,
        UnmappedMemberHandling = JsonUnmappedMemberHandling.Disallow,
        RespectRequiredConstructorParameters = true,
    };

    static DailyScheduleLoader() => Options.Converters.Add(new JsonStringEnumConverter(JsonNamingPolicy.KebabCaseLower, false));

    public Result<ContentCatalog, ContentLoadFailure> Load(string contentPath)
    {
        var issues = new List<ContentIssue>(); var schedules = new List<DailySchedule>();
        var calendar = Path.Combine(contentPath, "calendar");
        var travelTimes = LoadTravelTimes(calendar, issues);
        foreach (var path in Directory.Exists(calendar)
            ? Directory.EnumerateFiles(calendar, "*.json")
                .Where(x => !string.Equals(Path.GetFileName(x), TravelTimesFileName, StringComparison.Ordinal))
                .OrderBy(x => x, StringComparer.Ordinal)
            : Enumerable.Empty<string>())
        {
            try
            {
                var dto = JsonSerializer.Deserialize<ScheduleDto>(File.ReadAllText(path), Options) ?? throw new JsonException("Schedule document was empty.");
                if (dto.SchemaVersion != 1) throw new JsonException("Unsupported schema version.");
                var schedule = new DailySchedule(new(dto.Id), dto.DayOfWeek, dto.Entries.Select(x => new ScheduleEntry(new(x.Id), x.Kind, ScheduleTime.FromHoursAndMinutes(x.Start.Hour, x.Start.Minute), new(x.DurationMinutes), new(x.AnchorLocationId))));
                issues.AddRange(new DailyScheduleValidator().Validate(schedule, path, travelTimes));
                schedules.Add(schedule);
            }
            catch (JsonException ex) { issues.Add(new(IssueSeverity.Error, FailureCategory.Shape, null, path, ContentLoadRuleIds.JsonInvalid, null, ex.Message, null)); }
            catch (ArgumentException ex) { issues.Add(new(IssueSeverity.Error, FailureCategory.Shape, null, path, ContentLoadRuleIds.ScheduleInvalid, null, ex.Message, null)); }
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
            return dto.TravelTimes.Select(x => new TravelTime(new(x.From), new(x.To), new(x.MinimumTravelMinutes))).ToArray();
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
    }

    private sealed class ScheduleDto
    {
        public required int SchemaVersion { get; init; }
        public required string Id { get; init; }
        public required DayOfWeek DayOfWeek { get; init; }
        public required List<EntryDto> Entries { get; init; }
    }
    private sealed class TravelTimesDto
    {
        public required int SchemaVersion { get; init; }
        public required List<TravelTimeDto> TravelTimes { get; init; }
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
        public required TimeOnly Start { get; init; }
        public required int DurationMinutes { get; init; }
        public required string AnchorLocationId { get; init; }
    }
}
