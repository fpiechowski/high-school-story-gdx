using HighSchoolStory.Content.Catalog;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Ports.Content;
using Xunit;

namespace HighSchoolStory.Content.Tests.Catalog;

public sealed class ContentCatalogBoundaryTests
{
    [Fact]
    public void Content_repository_exposes_typed_schedule_data_through_the_application_port()
    {
        var expected = new DailySchedule(new ScheduleId("monday"), DayOfWeek.Monday, []);
        IDailyScheduleRepository repository = new DailyScheduleRepository(new ContentCatalog([expected]));

        var schedule = repository.Find(new ScheduleId("monday"));

        Assert.Same(expected, schedule);
        Assert.IsType<DailySchedule>(schedule);
    }
}
