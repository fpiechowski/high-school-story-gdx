using HighSchoolStory.Domain.Calendar;

namespace HighSchoolStory.Ports.Content;

public interface IDailyScheduleRepository
{
    DailySchedule? Find(ScheduleId scheduleId);
}
