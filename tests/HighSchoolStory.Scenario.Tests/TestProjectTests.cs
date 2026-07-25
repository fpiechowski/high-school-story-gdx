using System.Diagnostics;
using System.Reflection;
using Xunit;

namespace HighSchoolStory.Scenario.Tests;

public sealed class ToolCliContractTests
{
    public static TheoryData<string, string, int, string, bool> ToolInvocations => new()
    {
        { "HighSchoolStory.ContentValidator", "--help", 0, "Usage: HighSchoolStory.ContentValidator", false },
        { "HighSchoolStory.ContentValidator", "--version", 0, "HighSchoolStory.ContentValidator 0.1.0", false },
        { "HighSchoolStory.ContentValidator", MissingPath(), 2, "Content path is required and must exist.", true },
        { "HighSchoolStory.ScenarioRunner", "--help", 0, "Usage: HighSchoolStory.ScenarioRunner", false },
        { "HighSchoolStory.ScenarioRunner", "--version", 0, "HighSchoolStory.ScenarioRunner 0.1.0", false },
        { "HighSchoolStory.ScenarioRunner", MissingPath(), 2, "Fixture path is required and must exist.", true },
    };

    [Theory]
    [MemberData(nameof(ToolInvocations))]
    public async Task Tool_exposes_expected_cli_contract(
        string toolName,
        string argument,
        int expectedExitCode,
        string expectedOutput,
        bool expectsStandardError)
    {
        var result = await RunToolAsync(toolName, argument);

        Assert.Equal(expectedExitCode, result.ExitCode);
        Assert.Contains(expectedOutput, expectsStandardError ? result.StandardError : result.StandardOutput);
    }

    [Fact]
    public async Task ContentValidator_validates_the_vertical_slice_profile()
    {
        using var fixture = new TemporaryContent();

        var result = await RunToolAsync("HighSchoolStory.ContentValidator", fixture.Root, "--profile", "vertical-slice");

        Assert.Equal(0, result.ExitCode);
        Assert.Contains("Content validation passed for profile 'vertical-slice'.", result.StandardOutput);
        Assert.Empty(result.StandardError);
    }

    [Fact]
    public async Task ContentValidator_reports_invalid_content_with_a_validation_exit_code()
    {
        using var fixture = new TemporaryContent();
        File.WriteAllText(fixture.TravelTimesPath, TemporaryContent.InvalidTravelTimesJson);

        var result = await RunToolAsync("HighSchoolStory.ContentValidator", fixture.Root, "--profile", "vertical-slice");

        Assert.Equal(1, result.ExitCode);
        Assert.Contains("schedule.unreachable-required-commitment", result.StandardOutput);
        Assert.Empty(result.StandardError);
    }

    [Fact]
    public async Task ContentValidator_rejects_invalid_invocations_before_loading()
    {
        using var fixture = new TemporaryContent();
        var cases = new[]
        {
            (Arguments: new[] { "--unknown" }, Message: "Unknown option '--unknown'."),
            (Arguments: new[] { fixture.Root, "--profile", "vertical-slice", "--profile", "vertical-slice" }, Message: "--profile may be specified only once."),
            (Arguments: new[] { fixture.Root, "--profile" }, Message: "--profile requires a value."),
            (Arguments: new[] { fixture.Root, "--profile", "semester" }, Message: "Profile 'semester' is not supported."),
            (Arguments: new[] { fixture.Root, "extra" }, Message: "Exactly one content path is required."),
            (Arguments: new[] { MissingPath() }, Message: "Content path is required and must exist."),
        };

        foreach (var testCase in cases)
        {
            var result = await RunToolAsync("HighSchoolStory.ContentValidator", testCase.Arguments);

            Assert.Equal(2, result.ExitCode);
            Assert.Contains(testCase.Message, result.StandardError);
            Assert.Empty(result.StandardOutput);
        }
    }

    private static async Task<ProcessResult> RunToolAsync(string toolName, params string[] arguments)
    {
        var startInfo = new ProcessStartInfo("dotnet")
        {
            WorkingDirectory = FindRepositoryRoot(),
            RedirectStandardOutput = true,
            RedirectStandardError = true,
            UseShellExecute = false,
        };
        startInfo.ArgumentList.Add("run");
        startInfo.ArgumentList.Add("--no-build");
        startInfo.ArgumentList.Add("--project");
        startInfo.ArgumentList.Add(Path.Combine("tools", toolName, $"{toolName}.csproj"));
        startInfo.ArgumentList.Add("--configuration");
        startInfo.ArgumentList.Add(CurrentConfiguration());
        startInfo.ArgumentList.Add("--");
        foreach (var argument in arguments)
            startInfo.ArgumentList.Add(argument);

        using var process = Process.Start(startInfo);
        Assert.NotNull(process);

        var standardOutput = process.StandardOutput.ReadToEndAsync();
        var standardError = process.StandardError.ReadToEndAsync();
        await process.WaitForExitAsync();

        return new ProcessResult(process.ExitCode, await standardOutput, await standardError);
    }

    private static string CurrentConfiguration() =>
        Assembly.GetExecutingAssembly().GetCustomAttribute<AssemblyConfigurationAttribute>()?.Configuration ?? "Debug";

    private static string FindRepositoryRoot()
    {
        var directory = new DirectoryInfo(AppContext.BaseDirectory);
        while (directory is not null && !File.Exists(Path.Combine(directory.FullName, "High School Story.sln")))
        {
            directory = directory.Parent;
        }

        return directory?.FullName ?? throw new DirectoryNotFoundException("Repository root was not found.");
    }

    private static string MissingPath() => Path.Combine(Path.GetTempPath(), $"high-school-story-missing-{Guid.NewGuid():N}");

    private sealed record ProcessResult(int ExitCode, string StandardOutput, string StandardError);

    private sealed class TemporaryContent : IDisposable
    {
        public TemporaryContent()
        {
            Root = Path.Combine(Path.GetTempPath(), $"high-school-story-cli-{Guid.NewGuid():N}");
            var calendar = Directory.CreateDirectory(Path.Combine(Root, "calendar"));
            File.WriteAllText(Path.Combine(calendar.FullName, "first-school-day.json"), ScheduleJson);
            File.WriteAllText(Path.Combine(calendar.FullName, "travel-times.json"), TravelTimesJson);
        }

        public const string InvalidTravelTimesJson = """
            {
              "schemaVersion": 1,
              "travelTimes": [
                { "from": "dorm", "to": "school", "minimumTravelMinutes": 15 },
                { "from": "school", "to": "dorm", "minimumTravelMinutes": 0 }
              ]
            }
            """;

        public string Root { get; }
        public string TravelTimesPath => Path.Combine(Root, "calendar", "travel-times.json");

        public void Dispose() => Directory.Delete(Root, true);

        private static string ScheduleJson => """
            {
              "schemaVersion": 1,
              "id": "first-school-day",
              "dayOfWeek": "monday",
              "entries": [
                { "id": "wake", "kind": "wake", "start": "06:00", "durationMinutes": 0, "anchorLocationId": "dorm" },
                { "id": "before", "kind": "before-school-free", "start": "06:00", "durationMinutes": 120, "anchorLocationId": "dorm" },
                { "id": "lesson", "kind": "lesson", "start": "08:00", "durationMinutes": 45, "anchorLocationId": "school" },
                { "id": "break", "kind": "break", "start": "08:45", "durationMinutes": 15, "anchorLocationId": "school" },
                { "id": "lunch", "kind": "lunch", "start": "09:00", "durationMinutes": 45, "anchorLocationId": "school" },
                { "id": "after", "kind": "after-school-free", "start": "09:45", "durationMinutes": 675, "anchorLocationId": "school" },
                { "id": "return", "kind": "dorm-return", "start": "21:00", "durationMinutes": 0, "anchorLocationId": "dorm" },
                { "id": "wind-down", "kind": "wind-down", "start": "21:00", "durationMinutes": 60, "anchorLocationId": "dorm" },
                { "id": "sleep", "kind": "latest-sleep", "start": "22:00", "durationMinutes": 0, "anchorLocationId": "dorm" }
              ]
            }
            """;

        private static string TravelTimesJson => """
            {
              "schemaVersion": 1,
              "travelTimes": [
                { "from": "dorm", "to": "school", "minimumTravelMinutes": 0 },
                { "from": "school", "to": "dorm", "minimumTravelMinutes": 0 }
              ]
            }
            """;
    }
}
