using HighSchoolStory.Domain.Calendar;

namespace HighSchoolStory.Content.Validation;

public sealed class DailyScheduleValidator
{
    public IReadOnlyList<ContentIssue> Validate(DailySchedule schedule, string sourcePath, IReadOnlyCollection<TravelTime> travelTimes)
    {
        ArgumentNullException.ThrowIfNull(travelTimes);

        var issues = new List<ContentIssue>();
        var contentId = schedule.Id.Value;
        var entries = schedule.Entries;

        ValidateAlignment(entries, contentId, sourcePath, issues);
        ValidateEntryBounds(entries, contentId, sourcePath, issues);
        ValidateAnchorLocations(entries, contentId, sourcePath, issues);
        ValidateRequiredChain(entries, contentId, sourcePath, issues);
        ValidateBoundaryChain(entries, contentId, sourcePath, issues);
        ValidateReachability(entries, travelTimes, contentId, sourcePath, issues);
        ValidateReservedWindowOverlaps(entries, contentId, sourcePath, issues);
        ValidateLatestSleep(entries, contentId, sourcePath, issues);

        return issues;
    }

    private static void ValidateAlignment(IEnumerable<ScheduleEntry> entries, string contentId, string sourcePath, List<ContentIssue> issues)
    {
        foreach (var entry in entries)
        {
            if (!entry.Start.IsQuarterHourAligned)
                issues.Add(Issue(contentId, sourcePath, ScheduleValidationRuleIds.StartNotAligned, $"Entry '{entry.Id.Value}' starts at minute {entry.Start.MinutesSinceMidnight}, which is not 15-minute aligned.", "Set the start time to a 15-minute boundary."));

            if (!entry.Duration.IsQuarterHourAligned)
                issues.Add(Issue(contentId, sourcePath, ScheduleValidationRuleIds.DurationNotAligned, $"Entry '{entry.Id.Value}' has duration {entry.Duration.Minutes} minutes, which is not 15-minute aligned.", "Set the duration to a multiple of 15 minutes."));
        }
    }

    private static void ValidateEntryBounds(IEnumerable<ScheduleEntry> entries, string contentId, string sourcePath, List<ContentIssue> issues)
    {
        foreach (var entry in entries)
        {
            if ((long)entry.Start.MinutesSinceMidnight + entry.Duration.Minutes > ScheduleTime.MinutesPerDay)
                issues.Add(new(IssueSeverity.Error, FailureCategory.Semantic, contentId, sourcePath, ScheduleValidationRuleIds.IntervalOutOfRange, null, $"Entry '{entry.Id.Value}' ends after the end of the day.", "Shorten the entry so it ends by 24:00."));

            if (entry.Semantics is not ScheduleEntrySemantics.Boundary && entry.Duration.Minutes == 0)
                issues.Add(new(IssueSeverity.Error, FailureCategory.Semantic, contentId, sourcePath, ScheduleValidationRuleIds.NonPositiveDuration, null, $"Entry '{entry.Id.Value}' must have a positive duration.", "Give interval-based entries a duration greater than 0 minutes."));
        }
    }

    private static void ValidateAnchorLocations(IEnumerable<ScheduleEntry> entries, string contentId, string sourcePath, List<ContentIssue> issues)
    {
        foreach (var entry in entries)
        {
            var expectedLocation = entry.Kind switch
            {
                ScheduleEntryKind.Wake or ScheduleEntryKind.BeforeSchoolFree or ScheduleEntryKind.DormReturn or ScheduleEntryKind.WindDown or ScheduleEntryKind.LatestSleep => "dorm",
                ScheduleEntryKind.Lesson or ScheduleEntryKind.Break or ScheduleEntryKind.Lunch or ScheduleEntryKind.AfterSchoolFree => "school",
                _ => null,
            };

            if (expectedLocation is not null && entry.AnchorLocationId.Value != expectedLocation)
                issues.Add(new(IssueSeverity.Error, FailureCategory.Semantic, contentId, sourcePath, ScheduleValidationRuleIds.AnchorLocationInvalid, null, $"Entry '{entry.Id.Value}' of kind '{entry.Kind}' must be anchored at '{expectedLocation}'.", $"Set anchorLocationId to '{expectedLocation}'."));
        }
    }

    private static void ValidateRequiredChain(IReadOnlyCollection<ScheduleEntry> entries, string contentId, string sourcePath, List<ContentIssue> issues)
    {
        Require(entries, ScheduleEntryKind.Wake, exactlyOne: true, contentId, sourcePath, issues);
        Require(entries, ScheduleEntryKind.BeforeSchoolFree, exactlyOne: false, contentId, sourcePath, issues);
        Require(entries, ScheduleEntryKind.Lesson, exactlyOne: false, contentId, sourcePath, issues);
        Require(entries, ScheduleEntryKind.Break, exactlyOne: false, contentId, sourcePath, issues);
        Require(entries, ScheduleEntryKind.Lunch, exactlyOne: false, contentId, sourcePath, issues);
        Require(entries, ScheduleEntryKind.AfterSchoolFree, exactlyOne: false, contentId, sourcePath, issues);
        Require(entries, ScheduleEntryKind.DormReturn, exactlyOne: true, contentId, sourcePath, issues);
        Require(entries, ScheduleEntryKind.WindDown, exactlyOne: false, contentId, sourcePath, issues);
        Require(entries, ScheduleEntryKind.LatestSleep, exactlyOne: true, contentId, sourcePath, issues);

        var wake = SingleOrDefault(entries, ScheduleEntryKind.Wake);
        var dormReturn = SingleOrDefault(entries, ScheduleEntryKind.DormReturn);
        var latestSleep = SingleOrDefault(entries, ScheduleEntryKind.LatestSleep);

        if (wake is not null && wake.Duration.Minutes != 0)
            AddChainIssue(contentId, sourcePath, issues, "Wake must be a zero-duration boundary.", "Set the wake duration to 0 minutes.");
        if (dormReturn is not null && dormReturn.Duration.Minutes != 0)
            AddChainIssue(contentId, sourcePath, issues, "Dorm return must be a zero-duration boundary.", "Set the dorm-return duration to 0 minutes.");
        if (latestSleep is not null && latestSleep.Duration.Minutes != 0)
            AddChainIssue(contentId, sourcePath, issues, "Latest sleep must be a zero-duration boundary.", "Set the latest-sleep duration to 0 minutes.");

    }

    private static void ValidateReservedWindowOverlaps(IReadOnlyList<ScheduleEntry> entries, string contentId, string sourcePath, List<ContentIssue> issues)
    {
        for (var firstIndex = 0; firstIndex < entries.Count; firstIndex++)
        for (var secondIndex = firstIndex + 1; secondIndex < entries.Count; secondIndex++)
        {
            var first = entries[firstIndex];
            var second = entries[secondIndex];
            if (first.Semantics is not (ScheduleEntrySemantics.HardCommitment or ScheduleEntrySemantics.FixedWindow) && second.Semantics is not (ScheduleEntrySemantics.HardCommitment or ScheduleEntrySemantics.FixedWindow) || !Overlaps(first, second)) continue;
            issues.Add(Issue(contentId, sourcePath, ScheduleValidationRuleIds.OverlappingHardCommitment, $"Reserved entry '{first.Id.Value}' overlaps '{second.Id.Value}'.", "Move or shorten one of the overlapping entries."));
        }
    }

    private static void ValidateBoundaryChain(IReadOnlyCollection<ScheduleEntry> entries, string contentId, string sourcePath, List<ContentIssue> issues)
    {
        var wake = SingleOrDefault(entries, ScheduleEntryKind.Wake);
        var firstLesson = entries.Where(x => x.Kind == ScheduleEntryKind.Lesson).OrderBy(x => x.Start.MinutesSinceMidnight).FirstOrDefault();
        var firstSchoolEntry = entries
            .Where(IsSchoolEntry)
            .OrderBy(x => x.Start.MinutesSinceMidnight)
            .FirstOrDefault();
        var lastSchoolEntry = entries
            .Where(IsSchoolEntry)
            .OrderByDescending(End)
            .FirstOrDefault();
        var beforeSchool = entries.Where(x => x.Kind == ScheduleEntryKind.BeforeSchoolFree).OrderBy(x => x.Start.MinutesSinceMidnight).FirstOrDefault();
        var afterSchool = entries.Where(x => x.Kind == ScheduleEntryKind.AfterSchoolFree).OrderByDescending(End).FirstOrDefault();
        var dormReturn = SingleOrDefault(entries, ScheduleEntryKind.DormReturn);
        var windDown = entries.Where(x => x.Kind == ScheduleEntryKind.WindDown).OrderBy(x => x.Start.MinutesSinceMidnight).FirstOrDefault();
        var latestSleep = SingleOrDefault(entries, ScheduleEntryKind.LatestSleep);

        if (wake is not null && beforeSchool is not null && beforeSchool.Start.MinutesSinceMidnight < wake.Start.MinutesSinceMidnight)
            AddChainIssue(contentId, sourcePath, issues, "Before-school free time starts before wake.", "Move before-school free time to wake or later.");

        if (firstSchoolEntry is not null && firstSchoolEntry.Kind != ScheduleEntryKind.Lesson)
            AddChainIssue(contentId, sourcePath, issues, "The first school entry must be a lesson anchor.", "Move the first break or lunch after the first lesson.");

        if (beforeSchool is not null && firstLesson is not null && End(beforeSchool) > firstLesson.Start.MinutesSinceMidnight)
            AddChainIssue(contentId, sourcePath, issues, "Before-school free time extends past the first lesson.", "End before-school free time no later than the first lesson.");

        if (lastSchoolEntry is not null && afterSchool is not null && afterSchool.Start.MinutesSinceMidnight < End(lastSchoolEntry))
            AddChainIssue(contentId, sourcePath, issues, "After-school free time begins before the school schedule ends.", "Start after-school free time after the final school entry.");

        if (afterSchool is not null && dormReturn is not null && End(afterSchool) > dormReturn.Start.MinutesSinceMidnight)
            AddChainIssue(contentId, sourcePath, issues, "After-school free time extends past dorm return.", "End after-school free time no later than dorm return.");

        if (dormReturn is not null && windDown is not null && windDown.Start.MinutesSinceMidnight < dormReturn.Start.MinutesSinceMidnight)
            AddChainIssue(contentId, sourcePath, issues, "Wind-down begins before dorm return.", "Start wind-down at or after dorm return.");

        if (windDown is not null && latestSleep is not null && End(windDown) > latestSleep.Start.MinutesSinceMidnight)
            AddChainIssue(contentId, sourcePath, issues, "Wind-down ends after latest sleep.", "End wind-down no later than latest sleep.");
    }

    private static void ValidateLatestSleep(IReadOnlyList<ScheduleEntry> entries, string contentId, string sourcePath, List<ContentIssue> issues)
    {
        var latestSleep = SingleOrDefault(entries, ScheduleEntryKind.LatestSleep);
        if (latestSleep is null) return;

        foreach (var entry in entries.Where(x => x.Semantics is ScheduleEntrySemantics.HardCommitment or ScheduleEntrySemantics.FixedWindow))
        {
            if (End(entry) > latestSleep.Start.MinutesSinceMidnight)
                issues.Add(Issue(contentId, sourcePath, ScheduleValidationRuleIds.LatestSleepConflict, $"Reserved entry '{entry.Id.Value}' ends after latest sleep.", "End the entry no later than latest sleep."));
        }
    }

    private static void ValidateReachability(
        IReadOnlyCollection<ScheduleEntry> entries,
        IReadOnlyCollection<TravelTime> travelTimes,
        string contentId,
        string sourcePath,
        List<ContentIssue> issues)
    {
        var beforeSchool = entries.Where(x => x.Kind == ScheduleEntryKind.BeforeSchoolFree).OrderByDescending(End).FirstOrDefault();
        var requiredCommitments = entries
            .Where(x => x.Semantics == ScheduleEntrySemantics.HardCommitment)
            .OrderBy(x => x.Start.MinutesSinceMidnight)
            .ToArray();

        if (beforeSchool is not null)
        {
            var previous = beforeSchool;
            foreach (var commitment in requiredCommitments)
            {
                ValidateTravelTime(previous.AnchorLocationId, commitment.AnchorLocationId, End(previous), commitment.Start.MinutesSinceMidnight, commitment.Id.Value, travelTimes, contentId, sourcePath, issues);
                previous = commitment;
            }
        }

        var afterSchool = entries.Where(x => x.Kind == ScheduleEntryKind.AfterSchoolFree).OrderByDescending(End).FirstOrDefault();
        var dormReturn = SingleOrDefault(entries, ScheduleEntryKind.DormReturn);
        if (afterSchool is not null && dormReturn is not null)
            ValidateTravelTime(afterSchool.AnchorLocationId, dormReturn.AnchorLocationId, End(afterSchool), dormReturn.Start.MinutesSinceMidnight, dormReturn.Id.Value, travelTimes, contentId, sourcePath, issues);
    }

    private static void ValidateTravelTime(
        AnchorLocationId from,
        AnchorLocationId to,
        long availableAt,
        long requiredAt,
        string commitmentId,
        IReadOnlyCollection<TravelTime> travelTimes,
        string contentId,
        string sourcePath,
        List<ContentIssue> issues)
    {
        if (from == to)
            return;

        var travelTime = travelTimes
            .Where(x => x.From == from && x.To == to)
            .OrderBy(x => x.MinimumDuration.Minutes)
            .FirstOrDefault();

        if (travelTime is null)
        {
            issues.Add(Issue(contentId, sourcePath, ScheduleValidationRuleIds.UnreachableRequiredCommitment, $"Required commitment '{commitmentId}' cannot be reached from '{from.Value}' to '{to.Value}' because no authored travel time exists.", $"Add a travel time from '{from.Value}' to '{to.Value}'."));
            return;
        }

        if (availableAt + travelTime.MinimumDuration.Minutes > requiredAt)
            issues.Add(Issue(contentId, sourcePath, ScheduleValidationRuleIds.UnreachableRequiredCommitment, $"Required commitment '{commitmentId}' cannot be reached from '{from.Value}' to '{to.Value}' within the authored time window.", "Move the commitment later or provide a shorter authored travel time."));
    }

    private static void Require(IReadOnlyCollection<ScheduleEntry> entries, ScheduleEntryKind kind, bool exactlyOne, string contentId, string sourcePath, List<ContentIssue> issues)
    {
        var count = entries.Count(x => x.Kind == kind);
        if (kind == ScheduleEntryKind.Lesson && count == 0)
        {
            issues.Add(Issue(contentId, sourcePath, ScheduleValidationRuleIds.MissingLessonAnchor, "A school day requires at least one lesson anchor.", "Add a lesson with a start, duration, and anchor location."));
            return;
        }

        if (count == 0 || exactlyOne && count != 1)
            AddChainIssue(contentId, sourcePath, issues, $"A school day requires {(exactlyOne ? "exactly one" : "at least one")} '{kind}' entry.", $"Add {(exactlyOne ? "one" : "an")} '{kind}' entry in the appropriate position.");
    }

    private static ScheduleEntry? SingleOrDefault(IEnumerable<ScheduleEntry> entries, ScheduleEntryKind kind) => entries.FirstOrDefault(x => x.Kind == kind);

    private static ContentIssue Issue(string contentId, string sourcePath, HighSchoolStory.Domain.Shared.RuleId ruleId, string message, string? suggestedFix) => new(IssueSeverity.Error, FailureCategory.Semantic, contentId, sourcePath, ruleId, null, message, suggestedFix);
    private static void AddChainIssue(string contentId, string sourcePath, List<ContentIssue> issues, string message, string suggestedFix) => issues.Add(Issue(contentId, sourcePath, ScheduleValidationRuleIds.BoundaryChainInvalid, message, suggestedFix));
    private static long End(ScheduleEntry entry) => (long)entry.Start.MinutesSinceMidnight + entry.Duration.Minutes;
    private static bool Overlaps(ScheduleEntry first, ScheduleEntry second) => first.Start.MinutesSinceMidnight < End(second) && second.Start.MinutesSinceMidnight < End(first);
    private static bool IsSchoolEntry(ScheduleEntry entry) => entry.Kind is ScheduleEntryKind.Lesson or ScheduleEntryKind.Break or ScheduleEntryKind.Lunch;
}
