using HighSchoolStory.Domain.Calendar;

namespace HighSchoolStory.Ports.Time;

public interface IClock
{
    ScheduleTime Now { get; }
}
