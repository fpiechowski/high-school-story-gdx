using System.Collections.Immutable;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;
using HighSchoolStory.Domain.Shared;
using HighSchoolStory.Ports.Content;
using HighSchoolStory.Ports.Time;

namespace HighSchoolStory.Application.Features.DailyLoop;

public sealed class DailyLoopSession
{
    private readonly DailySchedule? _schedule;
    private readonly IClock _clock;
    private readonly IRandomSource _random;
    private GameState _state;

    public DailyLoopSession(IDailyScheduleRepository scheduleRepository, ScheduleId scheduleId, IClock clock, IRandomSource random)
    {
        ArgumentNullException.ThrowIfNull(scheduleRepository);
        _clock = clock ?? throw new ArgumentNullException(nameof(clock));
        _random = random ?? throw new ArgumentNullException(nameof(random));
        _schedule = scheduleRepository.Find(scheduleId);
        _state = GameState.CreateSeeded(scheduleId, _schedule?.DayOfWeek ?? DayOfWeek.Monday, _random.Seed, _clock.Now);
    }

    public string CurrentStateFingerprint => _state.Fingerprint();

    public ScheduleTime ControlledTime => _clock.Now;

    public DailyLoopReadModel CurrentReadModel =>
        _schedule is null
            ? throw new InvalidOperationException("A daily schedule is required to build a read model.")
            : DailyLoopReadModelMapper.Map(_state, _schedule);

    public Result<DailyLoopCommandResult, DailyLoopFailure> Execute(DailyLoopCommand command)
    {
        ArgumentNullException.ThrowIfNull(command);
        if (_schedule is null)
        {
            return Result<DailyLoopCommandResult, DailyLoopFailure>.Fail(
                DailyLoopFailure.Invalid(DailyLoopFailureCode.ScheduleNotFound, $"Schedule '{_state.ScheduleId.Value}' was not found."));
        }

        var before = _state;
        var outcome = Dispatch(command, before, _schedule);
        if (!outcome.IsSuccess)
            return Result<DailyLoopCommandResult, DailyLoopFailure>.Fail(outcome.Failure!);

        _state = outcome.Success!.State;
        var honored = _state.HonoredCommitments
            .Except(before.HonoredCommitments)
            .OrderBy(x => x.Value, StringComparer.Ordinal)
            .ToImmutableArray();
        return Result<DailyLoopCommandResult, DailyLoopFailure>.Ok(new(
            command.CommandId,
            command.Type,
            before.CurrentTime,
            _state.CurrentTime,
            honored,
            outcome.Success.EvidenceId,
            DailyLoopReadModelMapper.Map(_state, _schedule)));
    }

    private static Result<DailyLoopHandlerResult, DailyLoopFailure> Dispatch(DailyLoopCommand command, GameState state, DailySchedule schedule) => command switch
    {
        ReviewDayContextCommand typed => new ReviewDayContextHandler().Handle(typed, state),
        HonorMandatoryCommitmentCommand typed => new HonorMandatoryCommitmentHandler(schedule).Handle(typed, state),
        ChooseLessonActionCommand typed => new ChooseLessonActionHandler(schedule).Handle(typed, state),
        ResolveWellbeingChoiceCommand typed => new ResolveWellbeingChoiceHandler(schedule).Handle(typed, state),
        ProgressMandatoryCommitmentsCommand typed => new ProgressMandatoryCommitmentsHandler(schedule).Handle(typed, state),
        DiscoverSocialTouchpointCommand typed => new DiscoverSocialTouchpointHandler(schedule).Handle(typed, state),
        AttemptBlockedActionCommand typed => new AttemptBlockedActionHandler(schedule).Handle(typed, state),
        EndDayCommand typed => new EndDayHandler(schedule).Handle(typed, state),
        _ => Result<DailyLoopHandlerResult, DailyLoopFailure>.Fail(DailyLoopFailure.Invalid(DailyLoopFailureCode.InvalidCommandOrder, "The command is not supported by the daily-loop kernel.")),
    };
}
