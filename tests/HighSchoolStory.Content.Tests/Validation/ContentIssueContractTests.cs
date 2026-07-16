using HighSchoolStory.Content.Validation;
using HighSchoolStory.Domain.Shared;
using Xunit;

namespace HighSchoolStory.Content.Tests.Validation;

public sealed class ContentIssueContractTests
{
    [Fact]
    public void Exposes_canonical_schedule_rule_ids_as_lower_kebab_case_report_values()
    {
        var ruleIds = new[]
        {
            ScheduleValidationRuleIds.MissingLessonAnchor,
            ScheduleValidationRuleIds.OverlappingHardCommitment,
            ScheduleValidationRuleIds.StartNotAligned,
            ScheduleValidationRuleIds.DurationNotAligned,
            ScheduleValidationRuleIds.UnreachableRequiredCommitment,
            ScheduleValidationRuleIds.LatestSleepConflict,
        };

        Assert.Equal(
            new[]
            {
                "schedule.missing-lesson-anchor",
                "schedule.overlapping-hard-commitment",
                "schedule.start-not-aligned",
                "schedule.duration-not-aligned",
                "schedule.unreachable-required-commitment",
                "schedule.latest-sleep-conflict",
            },
            ruleIds.Select(x => x.Value));
    }

    [Fact]
    public void Sorts_diagnostic_issues_by_source_content_and_rule_id()
    {
        var failure = ContentLoadFailure.Create(
        [
            new(IssueSeverity.Error, FailureCategory.Semantic, "second", "b.json", ScheduleValidationRuleIds.StartNotAligned, null, "Second.", null),
            new(IssueSeverity.Error, FailureCategory.Semantic, "first", "a.json", ScheduleValidationRuleIds.DurationNotAligned, "trace-1", "First.", "Use a 15-minute duration."),
        ]);

        var issue = Assert.Single(failure.Issues.Take(1));
        Assert.Equal("first", issue.ContentId);
        Assert.Equal("trace-1", issue.CausalityTraceId);
        Assert.Equal("Use a 15-minute duration.", issue.SuggestedFix);
    }

    [Theory]
    [InlineData("Schedule.Start")]
    [InlineData("schedule_start")]
    [InlineData("")]
    public void Rejects_rule_ids_that_are_not_lower_kebab_case(string value)
    {
        Assert.Throws<ArgumentException>(() => new RuleId(value));
    }
}
