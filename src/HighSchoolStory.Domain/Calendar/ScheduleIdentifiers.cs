namespace HighSchoolStory.Domain.Calendar;

public readonly record struct ScheduleId
{
    public ScheduleId(string value) => Value = RequireValue(value);

    public string Value { get; }

    private static string RequireValue(string value) =>
        string.IsNullOrWhiteSpace(value)
            ? throw new ArgumentException("A schedule ID is required.", nameof(value))
            : value;
}

public readonly record struct ScheduleEntryId
{
    public ScheduleEntryId(string value) => Value = RequireValue(value);

    public string Value { get; }

    private static string RequireValue(string value) =>
        string.IsNullOrWhiteSpace(value)
            ? throw new ArgumentException("A schedule entry ID is required.", nameof(value))
            : value;
}

public readonly record struct AnchorLocationId
{
    public AnchorLocationId(string value) => Value = RequireValue(value);

    public string Value { get; }

    private static string RequireValue(string value) =>
        string.IsNullOrWhiteSpace(value)
            ? throw new ArgumentException("An anchor location ID is required.", nameof(value))
            : value;
}
