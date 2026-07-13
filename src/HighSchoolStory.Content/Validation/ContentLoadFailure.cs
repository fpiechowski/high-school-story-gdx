using System.Collections.Immutable;

namespace HighSchoolStory.Content.Validation;

public enum IssueSeverity { Error }
public enum FailureCategory { Read, Shape }
public sealed record ContentIssue(IssueSeverity Severity, FailureCategory FailureCategory, string? ContentId, string? SourcePath, string RuleId, string Message);
public sealed record ContentLoadFailure(ImmutableArray<ContentIssue> Issues)
{
    public bool HasErrors => !Issues.IsEmpty;
    public static ContentLoadFailure Create(IEnumerable<ContentIssue> issues) => new(issues.OrderBy(x => x.SourcePath, StringComparer.Ordinal).ThenBy(x => x.ContentId, StringComparer.Ordinal).ThenBy(x => x.RuleId, StringComparer.Ordinal).ToImmutableArray());
}
