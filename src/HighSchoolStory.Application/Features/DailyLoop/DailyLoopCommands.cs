using HighSchoolStory.Domain.Calendar;

namespace HighSchoolStory.Application.Features.DailyLoop;

public enum DailyLoopCommandType
{
    ReviewDayContext,
    HonorMandatoryCommitment,
    ChooseLessonAction,
    ResolveWellbeingChoice,
    ProgressMandatoryCommitments,
    DiscoverSocialTouchpoint,
    AttemptBlockedAction,
    EndDay,
}

public abstract record DailyLoopCommand(string CommandId)
{
    public abstract DailyLoopCommandType Type { get; }
}

public sealed record ReviewDayContextCommand(string CommandId) : DailyLoopCommand(CommandId)
{
    public override DailyLoopCommandType Type => DailyLoopCommandType.ReviewDayContext;
}

public sealed record HonorMandatoryCommitmentCommand(string CommandId, ScheduleEntryId CommitmentId) : DailyLoopCommand(CommandId)
{
    public override DailyLoopCommandType Type => DailyLoopCommandType.HonorMandatoryCommitment;
}

public sealed record ChooseLessonActionCommand(string CommandId, ScheduleEntryId LessonId, string ChoiceId) : DailyLoopCommand(CommandId)
{
    public override DailyLoopCommandType Type => DailyLoopCommandType.ChooseLessonAction;
}

public sealed record ResolveWellbeingChoiceCommand(string CommandId, string ChoiceId) : DailyLoopCommand(CommandId)
{
    public override DailyLoopCommandType Type => DailyLoopCommandType.ResolveWellbeingChoice;
}

public sealed record ProgressMandatoryCommitmentsCommand(string CommandId) : DailyLoopCommand(CommandId)
{
    public override DailyLoopCommandType Type => DailyLoopCommandType.ProgressMandatoryCommitments;
}

public sealed record DiscoverSocialTouchpointCommand(string CommandId, string ClueId, string FutureHookId) : DailyLoopCommand(CommandId)
{
    public override DailyLoopCommandType Type => DailyLoopCommandType.DiscoverSocialTouchpoint;
}

public sealed record AttemptBlockedActionCommand(string CommandId, string ActionId) : DailyLoopCommand(CommandId)
{
    public override DailyLoopCommandType Type => DailyLoopCommandType.AttemptBlockedAction;
}

public sealed record EndDayCommand(string CommandId) : DailyLoopCommand(CommandId)
{
    public override DailyLoopCommandType Type => DailyLoopCommandType.EndDay;
}
