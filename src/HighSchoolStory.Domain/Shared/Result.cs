namespace HighSchoolStory.Domain.Shared;

public readonly record struct Result<TSuccess, TFailure>
{
    private Result(TSuccess success) { Success = success; Failure = default; IsSuccess = true; }
    private Result(TFailure failure) { Success = default; Failure = failure; IsSuccess = false; }
    public TSuccess? Success { get; }
    public TFailure? Failure { get; }
    public bool IsSuccess { get; }
    public static Result<TSuccess, TFailure> Ok(TSuccess success) => new(success);
    public static Result<TSuccess, TFailure> Fail(TFailure failure) => new(failure);
}
