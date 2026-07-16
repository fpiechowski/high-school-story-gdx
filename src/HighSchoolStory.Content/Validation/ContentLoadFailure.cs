using System.Collections.Immutable;
using HighSchoolStory.Domain.Shared;

namespace HighSchoolStory.Content.Validation;

public enum IssueSeverity { Error }
public enum FailureCategory { Read, Shape, Semantic }
public sealed record ContentIssue(
    IssueSeverity Severity,
    FailureCategory FailureCategory,
    string? ContentId,
    string? SourcePath,
    RuleId RuleId,
    string? CausalityTraceId,
    string Message,
    string? SuggestedFix);

public static class ContentLoadRuleIds
{
    public static readonly RuleId JsonInvalid = new("content.json-invalid");
    public static readonly RuleId ScheduleInvalid = new("content.schedule-invalid");
}

public static class ScheduleValidationRuleIds
{
    public static readonly RuleId MissingLessonAnchor = new("schedule.missing-lesson-anchor");
    public static readonly RuleId OverlappingHardCommitment = new("schedule.overlapping-hard-commitment");
    public static readonly RuleId StartNotAligned = new("schedule.start-not-aligned");
    public static readonly RuleId DurationNotAligned = new("schedule.duration-not-aligned");
    public static readonly RuleId UnreachableRequiredCommitment = new("schedule.unreachable-required-commitment");
    public static readonly RuleId LatestSleepConflict = new("schedule.latest-sleep-conflict");
}

public sealed record ContentLoadFailure(ImmutableArray<ContentIssue> Issues)
{
    public bool HasErrors => !Issues.IsEmpty;
    public static ContentLoadFailure Create(IEnumerable<ContentIssue> issues) => new(issues.OrderBy(x => x.SourcePath, StringComparer.Ordinal).ThenBy(x => x.ContentId, StringComparer.Ordinal).ThenBy(x => x.RuleId.Value, StringComparer.Ordinal).ToImmutableArray());
}
