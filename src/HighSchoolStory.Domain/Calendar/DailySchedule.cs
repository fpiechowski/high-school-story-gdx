using System.Collections.Immutable;

namespace HighSchoolStory.Domain.Calendar;

public sealed record DailySchedule
{
    public DailySchedule(ScheduleId id, DayOfWeek dayOfWeek, IEnumerable<ScheduleEntry> entries)
    {
        ArgumentNullException.ThrowIfNull(entries);

        Id = id;
        DayOfWeek = dayOfWeek;
        Entries = entries.ToImmutableArray();
    }

    public ScheduleId Id { get; }

    public DayOfWeek DayOfWeek { get; }

    public ImmutableArray<ScheduleEntry> Entries { get; }
}
