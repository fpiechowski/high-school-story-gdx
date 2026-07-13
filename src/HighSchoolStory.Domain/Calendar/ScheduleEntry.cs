namespace HighSchoolStory.Domain.Calendar;

public sealed record ScheduleEntry(
    ScheduleEntryId Id,
    ScheduleEntryKind Kind,
    ScheduleTime Start,
    ScheduleDuration Duration,
    AnchorLocationId AnchorLocationId)
{
    public ScheduleEntrySemantics Semantics => Kind switch
    {
        ScheduleEntryKind.Lesson => ScheduleEntrySemantics.HardCommitment,
        ScheduleEntryKind.Lunch or ScheduleEntryKind.WindDown => ScheduleEntrySemantics.FixedWindow,
        ScheduleEntryKind.BeforeSchoolFree or ScheduleEntryKind.Break or ScheduleEntryKind.AfterSchoolFree => ScheduleEntrySemantics.AvailabilityWindow,
        ScheduleEntryKind.Wake or ScheduleEntryKind.DormReturn or ScheduleEntryKind.LatestSleep => ScheduleEntrySemantics.Boundary,
        _ => throw new ArgumentOutOfRangeException(nameof(Kind), Kind, "Unknown schedule entry kind."),
    };
}

public enum ScheduleEntryKind
{
    Wake,
    BeforeSchoolFree,
    Lesson,
    Break,
    Lunch,
    AfterSchoolFree,
    DormReturn,
    WindDown,
    LatestSleep,
}

public enum ScheduleEntrySemantics
{
    Boundary,
    HardCommitment,
    FixedWindow,
    AvailabilityWindow,
}
