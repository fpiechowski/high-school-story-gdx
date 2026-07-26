using HighSchoolStory.Content.Catalog;
using HighSchoolStory.Content.Loading;
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

    [Fact]
    public void Loader_catalog_repository_and_port_form_a_typed_consumer_path()
    {
        var contentRoot = FindRepositoryRoot();
        var result = new DailyScheduleLoader().Load(Path.Combine(contentRoot, "content", "mvp"), "vertical-slice");

        Assert.True(result.IsSuccess);
        IDailyScheduleRepository repository = new DailyScheduleRepository(result.Success!);
        var schedule = repository.Find(new ScheduleId("first-school-day"));

        Assert.NotNull(schedule);
        Assert.Equal(new ScheduleId("first-school-day"), schedule!.Id);
    }

    private static string FindRepositoryRoot()
    {
        var directory = new DirectoryInfo(AppContext.BaseDirectory);
        while (directory is not null && !File.Exists(Path.Combine(directory.FullName, "High School Story.sln")))
            directory = directory.Parent;

        return directory?.FullName ?? throw new DirectoryNotFoundException("Repository root was not found.");
    }
}
