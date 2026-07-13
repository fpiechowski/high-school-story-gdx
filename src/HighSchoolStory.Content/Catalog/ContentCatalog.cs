using System.Collections.Immutable;
using HighSchoolStory.Domain.Calendar;

namespace HighSchoolStory.Content.Catalog;

public sealed class ContentCatalog
{
    private readonly ImmutableDictionary<ScheduleId, DailySchedule> _schedules;
    public ContentCatalog(IEnumerable<DailySchedule> schedules) => _schedules = schedules.ToImmutableDictionary(x => x.Id);
    public DailySchedule? FindDailySchedule(ScheduleId id) => _schedules.GetValueOrDefault(id);
}
 