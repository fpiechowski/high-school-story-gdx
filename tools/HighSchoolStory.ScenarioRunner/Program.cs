using HighSchoolStory.Application.Scenario;
using HighSchoolStory.Content.Catalog;
using HighSchoolStory.Content.Loading;
using HighSchoolStory.Domain.Calendar;
using HighSchoolStory.Ports.Time;

namespace HighSchoolStory.ScenarioRunner;

internal static class Program
{
    private const int InvalidInputExitCode = 2;
    private const int MalformedFixtureExitCode = 3;
    private const int ScenarioFailureExitCode = 4;

    private static int Main(string[] args)
    {
        if (args is ["--help"])
        {
            Console.WriteLine("Usage: HighSchoolStory.ScenarioRunner <fixture-path>");
            return 0;
        }

        if (args is ["--version"])
        {
            Console.WriteLine("HighSchoolStory.ScenarioRunner 0.1.0");
            return 0;
        }

        if (args.Length != 1 || !File.Exists(args[0]))
        {
            Console.Error.WriteLine("Fixture path is required and must exist.");
            return InvalidInputExitCode;
        }

        var fixture = new ScenarioFixtureLoader().Load(args[0]);
        if (!fixture.IsSuccess)
        {
            Console.Error.WriteLine($"Scenario fixture is invalid: {fixture.Failure!.Message}");
            return MalformedFixtureExitCode;
        }

        var repositoryRoot = FindRepositoryRoot(args[0]);
        if (repositoryRoot is null)
        {
            Console.Error.WriteLine("Scenario content could not be located from the repository root.");
            return ScenarioFailureExitCode;
        }

        var content = new DailyScheduleLoader().Load(Path.Combine(repositoryRoot, "content", "mvp"), "vertical-slice");
        if (!content.IsSuccess)
        {
            Console.Error.WriteLine("Scenario content validation failed:");
            foreach (var issue in content.Failure!.Issues)
                Console.Error.WriteLine($"{issue.RuleId.Value}: {issue.Message}");
            return ScenarioFailureExitCode;
        }

        var executor = new DailyLoopScenarioExecutor(
            new DailyScheduleRepository(content.Success!),
            () => new ControlledClock(ScheduleTime.FromHoursAndMinutes(6, 0)),
            seed => new SeededRandom(seed));
        var report = executor.Execute(fixture.Success!);
        if (!report.IsSuccess)
        {
            var prefix = report.Failure!.Code == ScenarioExecutionFailureCode.AssertionFailed
                ? "Scenario assertion failed"
                : "Scenario execution failed";
            Console.Error.WriteLine($"{prefix}: {report.Failure.Message}");
            return ScenarioFailureExitCode;
        }

        Console.WriteLine(ScenarioReportFormatter.ToJson(report.Success!));
        return 0;
    }

    private static string? FindRepositoryRoot(string fixturePath)
    {
        var starts = new[]
        {
            new FileInfo(Path.GetFullPath(fixturePath)).Directory,
            new DirectoryInfo(Directory.GetCurrentDirectory()),
            new DirectoryInfo(AppContext.BaseDirectory),
        };
        foreach (var start in starts)
        {
            var directory = start;
            while (directory is not null)
            {
                if (File.Exists(Path.Combine(directory.FullName, "High School Story.sln")))
                    return directory.FullName;
                directory = directory.Parent;
            }
        }

        return null;
    }

    private sealed class ControlledClock(ScheduleTime now) : IClock
    {
        public ScheduleTime Now { get; } = now;
    }

    private sealed class SeededRandom(int seed) : IRandomSource
    {
        public int Seed { get; } = seed;
    }
}
