using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Ports.Content;
using Xunit;

namespace HighSchoolStory.Application.Tests.Calendar;

public sealed class DailyScheduleRepositoryContractTests
{
    [Fact]
    public void Consumer_receives_a_typed_daily_schedule_from_the_port()
    {
        var expected = new DailySchedule(new ScheduleId("monday"), DayOfWeek.Monday, []);
        IDailyScheduleRepository repository = new StubRepository(expected);

        var schedule = repository.Find(new ScheduleId("monday"));

        Assert.Same(expected, schedule);
    }

    private sealed class StubRepository(DailySchedule schedule) : IDailyScheduleRepository
    {
        public DailySchedule? Find(ScheduleId scheduleId) => scheduleId == schedule.Id ? schedule : null;
    }
}
