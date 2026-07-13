namespace HighSchoolStory.Domain.Calendar;

public readonly record struct ScheduleDuration
{
    public ScheduleDuration(int minutes)
    {
        if (minutes < 0)
        {
            throw new ArgumentOutOfRangeException(nameof(minutes));
        }

        Minutes = minutes;
    }

    public int Minutes { get; }

    public bool IsQuarterHourAligned => Minutes % 15 == 0;
}
