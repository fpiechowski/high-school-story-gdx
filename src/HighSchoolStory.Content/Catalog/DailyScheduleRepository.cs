using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Ports.Content;

namespace HighSchoolStory.Content.Catalog;

public sealed class DailyScheduleRepository(ContentCatalog catalog) : IDailyScheduleRepository
{
    public DailySchedule? Find(ScheduleId scheduleId) => catalog.FindDailySchedule(scheduleId);
}
