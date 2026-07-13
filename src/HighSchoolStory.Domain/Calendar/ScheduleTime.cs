namespace HighSchoolStory.Domain.Calendar;

public readonly record struct ScheduleTime
{
    public ScheduleTime(int minutesSinceMidnight)
    {
        if (minutesSinceMidnight is < 0 or >= MinutesPerDay)
        {
            throw new ArgumentOutOfRangeException(nameof(minutesSinceMidnight));
        }

        MinutesSinceMidnight = minutesSinceMidnight;
    }

    public const int MinutesPerDay = 24 * 60;

    public int MinutesSinceMidnight { get; }

    public bool IsQuarterHourAligned => MinutesSinceMidnight % 15 == 0;

    public static ScheduleTime FromHoursAndMinutes(int hour, int minute)
    {
        if (hour is < 0 or >= 24)
        {
            throw new ArgumentOutOfRangeException(nameof(hour));
        }

        if (minute is < 0 or >= 60)
        {
            throw new ArgumentOutOfRangeException(nameof(minute));
        }

        return new((hour * 60) + minute);
    }
}
