using HighSchoolStory.Domain.Calendar;

namespace HighSchoolStory.Content.Validation;

public sealed record TravelTime(
    AnchorLocationId From,
    AnchorLocationId To,
    ScheduleDuration MinimumDuration);
