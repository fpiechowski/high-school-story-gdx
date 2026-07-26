## Deferred from: code review of 1-1-validated-first-school-day-schedule-fixture (2026-07-26)

- Null or empty content paths are not explicitly rejected by the loader; pre-existing in `DailyScheduleLoader.Load` before PR #4.
- Access-denied calendar directories can be misreported as missing files because of the existing `File.Exists`/`Directory.Exists` preflight behavior; pre-existing before PR #4.
- Duplicate travel-time pairs are not rejected; pre-existing travel-time loading/lookup behavior outside the PR #4 change.
