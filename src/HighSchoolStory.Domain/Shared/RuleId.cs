using System.Text.RegularExpressions;

namespace HighSchoolStory.Domain.Shared;

public readonly record struct RuleId
{
    private static readonly Regex ValidPattern = new("^[a-z][a-z0-9]*(?:[.-][a-z0-9]+)*$", RegexOptions.CultureInvariant);

    public RuleId(string value)
    {
        if (string.IsNullOrWhiteSpace(value) || !ValidPattern.IsMatch(value))
            throw new ArgumentException("A rule ID must use lower-kebab-case segments.", nameof(value));

        Value = value;
    }

    public string Value { get; }

    public override string ToString() => Value;
}
