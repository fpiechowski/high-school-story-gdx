using HighSchoolStory.Content.Loading;

namespace HighSchoolStory.ContentValidator;

internal static class Program
{
    private const int InvalidInputExitCode = 2;

    private static int Main(string[] args)
    {
        if (args is ["--help"])
        {
            Console.WriteLine("Usage: HighSchoolStory.ContentValidator <content-path> [--profile <profile>]");
            return 0;
        }

        if (args is ["--version"])
        {
            Console.WriteLine("HighSchoolStory.ContentValidator 0.1.0");
            return 0;
        }

        if (!TryParseArguments(args, out var contentPath, out var profile, out var error))
        {
            Console.Error.WriteLine(error);
            return InvalidInputExitCode;
        }

        var result = new DailyScheduleLoader().Load(contentPath);
        if (!result.IsSuccess)
        {
            foreach (var issue in result.Failure!.Issues)
            {
                var contentId = issue.ContentId ?? "<none>";
                var sourcePath = issue.SourcePath ?? "<none>";
                var suggestedFix = issue.SuggestedFix is null ? string.Empty : $" Suggested fix: {issue.SuggestedFix}";
                Console.WriteLine($"{issue.Severity} | {issue.FailureCategory} | {issue.RuleId.Value} | content={contentId} | source={sourcePath} | {issue.Message}{suggestedFix}");
            }

            return 1;
        }

        Console.WriteLine($"Content validation passed for profile '{profile}'.");
        return 0;
    }

    private static bool TryParseArguments(string[] args, out string contentPath, out string profile, out string error)
    {
        contentPath = string.Empty;
        profile = "vertical-slice";
        error = string.Empty;

        var positional = new List<string>();
        var profileSpecified = false;
        for (var index = 0; index < args.Length; index++)
        {
            var argument = args[index];
            if (argument == "--profile")
            {
                if (profileSpecified)
                {
                    error = "--profile may be specified only once.";
                    return false;
                }

                if (index + 1 >= args.Length || args[index + 1].StartsWith("--", StringComparison.Ordinal))
                {
                    error = "--profile requires a value.";
                    return false;
                }

                profile = args[++index];
                profileSpecified = true;
                continue;
            }

            if (argument.StartsWith("--", StringComparison.Ordinal))
            {
                error = $"Unknown option '{argument}'.";
                return false;
            }

            positional.Add(argument);
        }

        if (positional.Count != 1)
        {
            error = positional.Count == 0
                ? "Content path is required and must exist."
                : "Exactly one content path is required.";
            return false;
        }

        if (!string.Equals(profile, "vertical-slice", StringComparison.Ordinal))
        {
            error = $"Profile '{profile}' is not supported. Use 'vertical-slice'.";
            return false;
        }

        contentPath = positional[0];
        if (!Directory.Exists(contentPath))
        {
            error = "Content path is required and must exist.";
            return false;
        }

        return true;
    }
}
