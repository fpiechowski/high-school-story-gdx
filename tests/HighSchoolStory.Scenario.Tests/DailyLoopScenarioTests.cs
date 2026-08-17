using HighSchoolStory.Application.Scenario;
using HighSchoolStory.Content.Catalog;
using HighSchoolStory.Content.Loading;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Domain.DailyLoop;
using HighSchoolStory.Ports.Time;
using HighSchoolStory.ScenarioRunner;
using Xunit;

namespace HighSchoolStory.Scenario.Tests;

public sealed class DailyLoopScenarioTests
{
    [Fact]
    public void Canonical_first_school_day_produces_the_same_structured_report_twice()
    {
        var definition = LoadDefinition();
        var first = Execute(definition);
        var second = Execute(definition);

        var firstJson = ScenarioReportFormatter.ToJson(first);
        var secondJson = ScenarioReportFormatter.ToJson(second);

        Assert.Equal(firstJson, secondJson);
        Assert.Equal(1201, first.Seed);
        Assert.Equal(8, first.Commands.Length);
        Assert.Equal(6, first.CommitmentsHonored.Length);
        Assert.Single(first.BlockedChoiceChecks);
        Assert.True(first.BlockedChoiceChecks[0].StateUnchanged);
        Assert.Contains(first.Commands, x => x.CommandType == HighSchoolStory.Application.Features.DailyLoop.DailyLoopCommandType.ChooseLessonAction && x.EvidenceId == "lesson-choice");
        Assert.Contains(first.Commands, x => x.EvidenceId == "wellbeing-trade-off");
        Assert.Contains(first.FinalDayState.VisibleConsequences, x => x.Id == "social-discovery");
        Assert.Equal("quiet-reconnection-clue", first.FinalDayState.SocialClue!.Id);
        Assert.Equal("future-conversation-hook", first.FinalDayState.FutureHookCandidate!.Id);
        Assert.True(first.FinalDayState.DayEnded);
        Assert.Equal(first.FinalStateFingerprint, first.FinalDayState.StateFingerprint);
    }

    [Fact]
    public void Fixture_loader_rejects_unknown_shape_without_throwing()
    {
        var path = Path.Combine(Path.GetTempPath(), $"scenario-invalid-{Guid.NewGuid():N}.json");
        File.WriteAllText(path, "{\"schemaVersion\":1,\"scenarioId\":\"bad\",\"fixtureVersion\":\"1.0\",\"seed\":1,\"scheduleId\":\"first-school-day\",\"commands\":[],\"effects\":[]}");
        try
        {
            var result = new ScenarioFixtureLoader().Load(path);

            Assert.False(result.IsSuccess);
            Assert.Contains("JSON is invalid", result.Failure!.Message);
        }
        finally
        {
            File.Delete(path);
        }
    }

    [Fact]
    public void Fixture_loader_rejects_null_commands_and_missing_type_specific_fields()
    {
        var paths = new[]
        {
            Path.Combine(Path.GetTempPath(), $"scenario-null-command-{Guid.NewGuid():N}.json"),
            Path.Combine(Path.GetTempPath(), $"scenario-missing-field-{Guid.NewGuid():N}.json"),
            Path.Combine(Path.GetTempPath(), $"scenario-missing-failure-code-{Guid.NewGuid():N}.json"),
        };
        File.WriteAllText(paths[0], "{\"schemaVersion\":1,\"scenarioId\":\"bad\",\"fixtureVersion\":\"1.0\",\"seed\":1,\"scheduleId\":\"first-school-day\",\"commands\":[null]}");
        File.WriteAllText(paths[1], "{\"schemaVersion\":1,\"scenarioId\":\"bad\",\"fixtureVersion\":\"1.0\",\"seed\":1,\"scheduleId\":\"first-school-day\",\"commands\":[{\"id\":\"honor\",\"type\":\"honor-mandatory-commitment\",\"expectedOutcome\":\"success\"}]}");
        File.WriteAllText(paths[2], "{\"schemaVersion\":1,\"scenarioId\":\"bad\",\"fixtureVersion\":\"1.0\",\"seed\":1,\"scheduleId\":\"first-school-day\",\"commands\":[{\"id\":\"blocked\",\"type\":\"attempt-blocked-action\",\"targetId\":\"leave-school-early\",\"expectedOutcome\":\"rejected\"}]}");
        try
        {
            var loader = new ScenarioFixtureLoader();

            var nullCommand = loader.Load(paths[0]);
            var missingField = loader.Load(paths[1]);
            var missingFailureCode = loader.Load(paths[2]);

            Assert.False(nullCommand.IsSuccess);
            Assert.Contains("must not be null", nullCommand.Failure!.Message);
            Assert.False(missingField.IsSuccess);
            Assert.Contains("missing fields", missingField.Failure!.Message);
            Assert.False(missingFailureCode.IsSuccess);
            Assert.Contains("requires expectedFailureCode", missingFailureCode.Failure!.Message);
        }
        finally
        {
            foreach (var path in paths)
                File.Delete(path);
        }
    }

    [Fact]
    public void Scenario_executor_reports_missing_schedule_without_building_a_read_model()
    {
        var definition = LoadDefinition() with { ScheduleId = new ScheduleId("missing-schedule") };
        var root = FindRepositoryRoot();
        var loaded = new DailyScheduleLoader().Load(Path.Combine(root, "content", "mvp"), "vertical-slice");
        Assert.True(loaded.IsSuccess, loaded.Failure?.ToString());
        var executor = new DailyLoopScenarioExecutor(
            new DailyScheduleRepository(loaded.Success!),
            () => new FixedClock(ScheduleTime.FromHoursAndMinutes(6, 0)),
            seed => new FixedRandom(seed));

        var result = executor.Execute(definition);

        Assert.False(result.IsSuccess);
        Assert.Equal(ScenarioExecutionFailureCode.ScheduleUnavailable, result.Failure!.Code);
    }

    private static DailyLoopScenarioDefinition LoadDefinition()
    {
        var path = Path.Combine(FindRepositoryRoot(), "content", "fixtures", "vertical-slice", "one-school-day.json");
        var result = new ScenarioFixtureLoader().Load(path);
        Assert.True(result.IsSuccess, result.Failure?.Message);
        return result.Success!;
    }

    private static DailyLoopScenarioReport Execute(DailyLoopScenarioDefinition definition)
    {
        var root = FindRepositoryRoot();
        var loaded = new DailyScheduleLoader().Load(Path.Combine(root, "content", "mvp"), "vertical-slice");
        Assert.True(loaded.IsSuccess, loaded.Failure?.ToString());
        var executor = new DailyLoopScenarioExecutor(
            new DailyScheduleRepository(loaded.Success!),
            () => new FixedClock(ScheduleTime.FromHoursAndMinutes(6, 0)),
            seed => new FixedRandom(seed));

        var result = executor.Execute(definition);
        Assert.True(result.IsSuccess, result.Failure?.Message);
        return result.Success!;
    }

    private static string FindRepositoryRoot()
    {
        var directory = new DirectoryInfo(AppContext.BaseDirectory);
        while (directory is not null && !File.Exists(Path.Combine(directory.FullName, "High School Story.sln")))
            directory = directory.Parent;
        return directory?.FullName ?? throw new DirectoryNotFoundException("Repository root was not found.");
    }

    private sealed class FixedClock(ScheduleTime now) : IClock
    {
        public ScheduleTime Now { get; } = now;
    }

    private sealed class FixedRandom(int seed) : IRandomSource
    {
        public int Seed { get; } = seed;
    }
}
