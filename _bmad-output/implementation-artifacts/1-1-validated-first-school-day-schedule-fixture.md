---
baseline_commit: 5e25bf18d818cd6e68a5e5362af4802aaed98c61
branch_name: story/1-1/validated-first-school-day-schedule-fixture
pull_request_url: https://github.com/codex-fp/high-school-story/pull/3
---

# Story 1.1: Validated First School-Day Schedule Fixture

Status: in-progress

## Story

As a developer,
I want a minimal content-driven first school-day schedule fixture,
so that the first vertical slice can prove time, commitments, and school-day anchors from validated content instead of hardcoded scene logic.

## Acceptance Criteria

1. Given the vertical-slice fixture catalog is loaded, when the first playable school day is validated, then the catalog includes a deterministic school-day schedule with a wake boundary, before-school and after-school free-time windows, lesson anchors, break/lunch windows, dorm return boundary, wind-down period, and latest sleep rule, and all schedule entries use 15-minute-aligned start times and durations.
2. Given the fixture includes mandatory school attendance, when ContentValidator checks the day schedule, then it rejects missing lesson anchors, overlapping hard commitments, invalid 15-minute alignment, unreachable required commitments, and latest-sleep conflicts, and reports typed validation errors with content IDs and readable diagnostics.
3. Given the fixture is consumed by Application tests or ScenarioRunner, when time and commitment data is requested, then gameplay systems receive schedule data through the validated ContentCatalog, and no runtime command handler reads raw JSON files or hardcodes the first-day schedule.
4. Given the fixture is scoped to the first vertical slice, when broader semester content is absent, then validation still passes for the minimal one-day path, and the fixture remains extendable to the 20-week MVP semester without changing the schedule model.

## Tasks / Subtasks

- [x] Define the smallest stable schedule contracts needed by the fixture (AC: 1, 3, 4)
  - [x] Add engine-independent time/calendar value objects and stable IDs only where they are genuine runtime concepts; keep JSON DTOs and serializer attributes in Content.
  - [x] Model a day as identified schedule content containing a wake boundary, before-school and after-school free-time windows, required commitments/windows, locations, mandatory/hard semantics, dorm return, wind-down, and latest sleep.
  - [x] Represent start times and durations so 15-minute alignment is explicit and testable; do not implement Story 1.3's runtime feasibility/command policy.
  - [x] Keep the schedule definition repeatable by date/day identity so additional days and a 20-week semester can reuse the same model rather than adding first-day-only fields.
  - [x] If an Application-facing access contract is required, expose the narrow query/repository contract through Ports using stable Domain types; do not add an Application reference to Content.
- [x] Add the canonical first-school-day authored fixture (AC: 1, 4)
  - [x] Create `content/mvp/calendar/first-school-day.json` using lower-kebab-case content IDs and the strict JSON contract defined in this story.
  - [x] Include the sourced invariants: Monday-Thursday school-night rules, 06:00 wake boundary, explicit 06:00-08:00 before-school free time at the dorm, 15-minute scheduling grammar, 45-minute lesson anchors, 15-minute break windows, fixed 12:00-12:45 lunch, after-school free time, 21:00 dorm return, 21:00-22:00 dorm-only wind-down, and 22:00 latest sleep.
  - [x] Implement the canonical six-lesson sequence documented in Schedule Model Guardrails, including stable IDs, exact starts/durations, and locations; tests must assert this sequence rather than accepting any internally valid timetable.
  - [x] Keep Story 1.2's scenario file `content/fixtures/vertical-slice/one-school-day.json` out of scope; the schedule fixture is catalog content, not a scripted command sequence.
- [ ] Implement strict content loading and atomic catalog construction (AC: 1, 3, 4)
  - [ ] Add schedule loading under `src/HighSchoolStory.Content/Loading/` and immutable runtime catalog/query types under `src/HighSchoolStory.Content/Catalog/`.
  - [ ] Use one explicit, reused `System.Text.Json` options instance with case-sensitive camelCase names, unmapped-member rejection, required-member enforcement, and string-only enum values.
  - [ ] Translate syntax, shape, required-member, and unmapped-member failures into typed content issues; do not leak `JsonException` or raw DTOs across the Content boundary.
  - [ ] Build and expose a ContentCatalog only after the complete selected catalog validates without errors. Never return a partial catalog from invalid input.
  - [ ] Load files in deterministic order and sort diagnostics by stable keys such as source path, content ID, and rule ID.
- [ ] Implement semantic school-day schedule validation (AC: 1, 2, 4)
  - [ ] Define stable rule IDs/reason codes and readable diagnostics for missing lesson anchors, overlapping hard commitments, invalid start alignment, invalid duration alignment, unreachable required commitments, and latest-sleep conflicts.
  - [ ] Use the initial canonical rule IDs `schedule.missing-lesson-anchor`, `schedule.overlapping-hard-commitment`, `schedule.start-not-aligned`, `schedule.duration-not-aligned`, `schedule.unreachable-required-commitment`, and `schedule.latest-sleep-conflict`; add narrower codes only when a failure is materially distinct.
  - [ ] Require every issue to carry severity/failure category, rule ID, source path, content ID when recoverable, optional causality trace ID, and a readable message; add a suggested fix where it is reliably actionable.
  - [ ] Validate the complete boundary chain: wake/before-school free time/travel reachability -> required school anchors -> break/lunch windows -> after-school free time -> dorm return -> wind-down -> latest sleep.
  - [ ] For reachability, validate authored ordering and minimum transition/travel inputs through a narrow content-side lookup. Do not build runtime travel legality or duplicate Story 1.3/1.7 policies.
  - [ ] Allow a valid one-day catalog without semester metadata or unrelated activity, lesson-resolution, relationship, phone, save, or UI content.
- [ ] Replace the ContentValidator scaffold with real vertical-slice validation (AC: 2, 3, 4)
  - [ ] Update `tools/HighSchoolStory.ContentValidator/Program.cs` to parse `<content-path> [--profile <profile>]`, load through HighSchoolStory.Content, print deterministic diagnostics, and return a nonzero status for invalid authored content.
  - [ ] Enforce exactly one existing positional content path and at most one `--profile` option with a required value. Support only `vertical-slice` now; reject unknown/duplicate options, unknown/missing profile values, extra positionals, or missing paths on stderr with exit code 2 before loading.
  - [ ] Make `--profile vertical-slice` deterministically select the FVS catalog rooted at the supplied content path; do not silently ignore the profile.
  - [ ] Preserve the existing `--help`, `--version`, and missing-path contract, including readable stderr and exit code 2 for invalid invocation/path.
  - [ ] Use exit code 0 for valid content and reserve a distinct deterministic exit code (recommended 1) for validation findings.
  - [ ] Keep CLI orchestration thin: parsing and rendering may live in the tool, but loading, validation, issue types, and catalog construction belong in Content.
- [ ] Add deterministic evidence for the fixture, loader, validator, and catalog boundary (AC: 1-4)
  - [ ] Replace the Content test placeholder with focused tests for successful load, complete schedule shape, 15-minute alignment, atomic catalog creation, and equivalent results across repeated loads.
  - [ ] Add one focused invalid fixture/test per required semantic rejection and assert exact stable rule/content IDs plus readable diagnostic fragments.
  - [ ] Prove invalid content never produces a runtime catalog and issue ordering is stable.
  - [ ] Prove the minimal one-day catalog passes without broader semester content and can coexist with a second day using the same model.
  - [ ] Prove an application-facing consumer receives typed schedule/catalog data rather than paths, raw JSON, or JSON DTOs; keep Application free of Content/System.Text.Json dependencies.
  - [ ] Extend process-level CLI tests for a profile-aware valid catalog, an invalid catalog, and each invalid invocation class while preserving all existing ContentValidator and ScenarioRunner discovery contracts.
- [ ] Verify the story through the supported repository gates (AC: 1-4)
  - [ ] Run `dotnet test tests/HighSchoolStory.Content.Tests` first; also run Domain/Application/Scenario tests when their boundaries are touched.
  - [ ] Run `dotnet test tests/HighSchoolStory.Architecture.Tests` and retain explicit guards proving Application has neither `System.Text.Json` nor concrete Content dependencies while Content remains the raw-JSON owner.
  - [ ] Run ContentValidator against `content/mvp --profile vertical-slice` and exercise `--help`, `--version`, a missing path, and an invalid fixture/catalog.
  - [ ] Run `dotnet test` before handoff. Run `dotnet build "High School Story.sln"` if project/package/build configuration changes.

## Dev Notes

### Scope and Dependency Position

- Story 0.1 established the clean projects, tool entry points, tests, and architecture guards. Reuse them; do not create parallel projects or loaders.
- Story 1.1 is the first content/validation slice. It proves authored schedule data can become a validated runtime catalog.
- Story 1.2 owns deterministic ScenarioRunner command execution and `content/fixtures/vertical-slice/one-school-day.json`.
- Story 1.3 owns canonical runtime time/commitment feasibility and command legality. Story 1.4 owns active school-day anchors/attendance behavior. This story validates authored schedule consistency only.
- The broader vertical-slice order is: content fixture -> ContentValidator -> ScenarioRunner -> runtime time/commitment policy -> activity occasion -> lesson session -> dialogue/effects -> save/load rebuild -> Godot smoke.

### Current Repository State

- `content/` does not exist yet. The clean Domain, Ports, Application, and Content projects contain only their project files.
- `HighSchoolStory.Content` already references Domain and Ports and is the sole owner of JSON loading, validation, reports, and concrete ContentCatalog/repository implementation.
- `HighSchoolStory.Application` references Domain and Ports, not Content. Architecture tests also reject `System.Text.Json` in Application. Preserve this direction.
- `ContentValidator` currently implements `--help`, `--version`, missing-directory exit code 2, and a placeholder success message for existing directories. This story replaces only the placeholder path.
- `ScenarioRunner` remains a scaffold. Do not implement or alter scenario execution in this story.
- Existing Scenario.Tests invoke both tools as child processes and assert the exact discovery/missing-input contracts. Preserve those tests and add evidence rather than weakening them.
- Existing architecture tests verify compiled dependencies and the effective Godot host compile set. Keep them green; no Godot host or scene change is required.

### Required Architecture and Data Flow

```text
content/mvp/calendar/first-school-day.json
  -> HighSchoolStory.Content strict JSON loader
  -> typed authored definition
  -> semantic schedule validators
  -> deterministic ContentIssue report
  -> ContentCatalog (only when valid)
  -> narrow Ports/Domain schedule contract for Application consumers

ContentValidator
  -> invokes the same Content loading/validation core
  -> renders issues and deterministic exit status
```

- Domain may own stable IDs, time values, schedule concepts, and invariants, but must not reference JSON, Content, Ports, Godot, R3, or logging.
- Ports may expose a narrow `IContentRepository` or schedule lookup using stable immutable Domain/value types. It must not expose mutable aggregates or raw authoring DTOs.
- Content owns authoring DTOs, serializer configuration, file discovery, semantic validators, reports, and concrete catalog/repository construction.
- Application receives typed validated schedule data through stable contracts. It must not read files, deserialize JSON, or reference concrete Content classes.
- Godot remains presentation/infrastructure only and is not part of this story.

### Schedule Model Guardrails

- Use the 15-minute block as the authored scheduling grammar. Reject unsupported start times and durations; do not silently round authored data.
- Preserve distinct semantics for before-school free time, lesson anchors, break, lunch, after-school free time, dorm return, wind-down, and latest sleep. Do not collapse them into untyped string labels.
- Stable content IDs and rule IDs must be lower-kebab-case in JSON/reports. C# types use PascalCase and namespaces follow boundary/system, for example `HighSchoolStory.Domain.Calendar` and `HighSchoolStory.Content.Validation`.
- Hard commitments may not overlap. Informational/free windows can be represented distinctly so the validator does not mistake every displayed span for a hard reservation.
- The first fixture should use a normal Monday-Thursday school night. Friday/weekend boundary variants belong to later stories unless a small invalid/compatibility fixture is needed for the model test.
- The model must permit additional days/weeks through repeated schedule definitions or catalog composition. Do not add a fixed 20-week array, semester engine, recurrence DSL, or first-day-specific C# type.
- Reachability validation should be narrow and authoring-focused. It may use minimum transition data or a small validated travel-time lookup, but it must not become the runtime feasibility engine.

Canonical first-day sequence (all entries at `school` except the wake boundary and before-school free-time window at `dorm`; dorm-to-school minimum travel is 0 minutes, but arrival must still respect the first mandatory commitment):

| ID | Kind | Start | Duration | Semantics |
| --- | --- | --- | --- | --- |
| `first-day-wake` | wake | 06:00 | 0 | boundary at dorm |
| `first-day-before-school` | before-school-free | 06:00 | 120 min | non-reserving dorm availability through 08:00 |
| `first-day-lesson-1` | lesson | 08:00 | 45 min | mandatory hard commitment |
| `first-day-break-1` | break | 08:45 | 15 min | school availability window |
| `first-day-lesson-2` | lesson | 09:00 | 45 min | mandatory hard commitment |
| `first-day-break-2` | break | 09:45 | 15 min | school availability window |
| `first-day-lesson-3` | lesson | 10:00 | 45 min | mandatory hard commitment |
| `first-day-break-3` | break | 10:45 | 15 min | school availability window |
| `first-day-lesson-4` | lesson | 11:00 | 45 min | mandatory hard commitment |
| `first-day-break-4` | break | 11:45 | 15 min | school availability window |
| `first-day-lunch` | lunch | 12:00 | 45 min | fixed school window; no lesson overlap |
| `first-day-lesson-5` | lesson | 12:45 | 45 min | mandatory hard commitment |
| `first-day-break-5` | break | 13:30 | 15 min | school availability window |
| `first-day-lesson-6` | lesson | 13:45 | 45 min | mandatory hard commitment |
| `first-day-break-6` | break | 14:30 | 15 min | school availability window |
| `first-day-after-school` | after-school-free | 14:45 | 375 min | non-reserving availability through 21:00 |
| `first-day-dorm-return` | dorm-return | 21:00 | 0 | hard deadline/boundary, not an interval |
| `first-day-wind-down` | wind-down | 21:00 | 60 min | fixed dorm-only interval |
| `first-day-latest-sleep` | latest-sleep | 22:00 | 0 | terminal deadline/boundary |

Interval policy:

- Duration-bearing entries use half-open intervals `[start, end)`, so adjacent entries may share a boundary without overlapping.
- Lessons are hard commitments. Lunch and wind-down are fixed windows that hard commitments may not overlap.
- Before-school-free, break, and after-school-free entries are non-reserving availability windows; they describe usable context and must not overlap hard/fixed entries.
- Wake, dorm-return, and latest-sleep are zero-duration boundaries/deadlines. Dorm return must not be modeled as an all-evening reservation.
- The validator must reject a hard/fixed interval crossing dorm return, wind-down, or latest sleep even if its own start is aligned.

### Strict JSON Contract

- Use the .NET 10 BCL `System.Text.Json`; no additional JSON package is required for this slice.
- Configure options explicitly. Do not use `JsonSerializerOptions.Web`, case-insensitive property matching, comments, trailing commas, quoted-number coercion, or integer enum fallbacks.
- Enforce unknown-member rejection with `JsonUnmappedMemberHandling.Disallow` (or an equivalent type-level contract), required members, required constructor parameters, and nullable annotations where applicable.
- Deserialize readable enum-like values through a string enum converter with integer values disabled.
- `TimeOnly` may be used in Content parse DTOs, but map it to explicit engine-independent time/block types before catalog exposure.
- Catch `JsonException` at the Content loading boundary and translate it into deterministic content issues. Deserialization success is not semantic validation success.
- Avoid source generation solely for this small fixture. If introduced later, deserialization requires metadata mode.

### Content Issue and Report Contract

Use a narrow implementation of the architecture's issue shape rather than console-only strings:

```csharp
public sealed record ContentIssue(
    IssueSeverity Severity,
    FailureCategory FailureCategory,
    ContentId? ContentId,
    string? SourcePath,
    RuleId RuleId,
    CausalityTraceId? CausalityTraceId,
    string Message,
    string? SuggestedFix);
```

The exact namespaces/types may adapt to the codebase, but the information must remain typed and deterministic. Populate `CausalityTraceId` when a validation path already has a stable trace and otherwise use null; preserve it in report/CLI output so ScenarioRunner can reuse the contract later. Do not invent player-facing UI copy here; this report is authoring/developer evidence.

### Intended File Placement

Likely new files:

```text
content/
  mvp/calendar/first-school-day.json
  fixtures/vertical-slice/schedules/        # focused valid/invalid authored test inputs if file fixtures are used

src/HighSchoolStory.Domain/
  Shared/                                   # stable ContentId/RuleId only if truly cross-system
  Time/                                     # engine-independent time/block values
  Calendar/                                 # schedule/commitment runtime concepts

src/HighSchoolStory.Ports/
  Content/                                  # narrow catalog/repository query contract if required

src/HighSchoolStory.Content/
  Loading/                                  # JSON options, DTO loading, file discovery
  Validation/                               # issue types and schedule validators
  Catalog/                                  # immutable validated ContentCatalog
  Reports/                                  # deterministic report rendering/model if shared

tests/HighSchoolStory.Content.Tests/
tests/HighSchoolStory.Domain.Tests/          # only for Domain value/invariant types
tests/HighSchoolStory.Application.Tests/     # only if proving a Ports-based consumer
tests/HighSchoolStory.Scenario.Tests/        # CLI process contract evidence
```

Likely update files:

- `tools/HighSchoolStory.ContentValidator/Program.cs`: replace placeholder validation while preserving discovery/error behavior.
- `tests/HighSchoolStory.Content.Tests/TestProjectTests.cs`: replace or split the placeholder into named behavior tests.
- `tests/HighSchoolStory.Scenario.Tests/TestProjectTests.cs`: add valid/invalid ContentValidator process cases without changing ScenarioRunner expectations.

Do not update the root Godot project, Godot scripts/scenes, `ScenarioRunner/Program.cs`, or add Application -> Content references. `HighSchoolStory.Content.csproj` needs no change when using BCL JSON.

### Testing Requirements

- Pure content tests must not depend on the current working directory, unordered directory enumeration, mutable static serializer settings, system clock, culture, or shared writable fixture directories.
- xUnit v3 may run collections in parallel. Use in-memory JSON where useful or isolated per-test temporary directories; do not globally disable parallelism to hide shared-state bugs.
- Minimum positive evidence: canonical fixture loads; all required schedule elements are present; starts/durations align; catalog construction succeeds twice with equivalent results; one-day-only content passes; a second day can reuse the model.
- Minimum negative evidence: exact stable diagnostics for missing lesson anchor, overlapping hard commitments, misaligned start, misaligned duration, unreachable required commitment, and latest-sleep conflict.
- Minimum boundary evidence: malformed/unknown JSON members fail readably; invalid catalogs are not exposed; consumer-facing types contain no raw JSON/path DTOs; Application remains independent of Content/System.Text.Json.
- Minimum CLI evidence: valid profile succeeds, invalid catalog reports findings and fails deterministically, help/version succeed, missing path remains exit 2.
- Invalid-invocation evidence: unknown option, duplicate `--profile`, missing profile value, unknown profile, extra positional path, and absent path all fail before content loading with exit code 2.
- Architecture evidence: run the focused Architecture.Tests project and retain the existing compiled/source boundary checks for Application raw JSON and concrete Content dependencies.
- Do not run Godot smoke/headless checks; this story does not modify the host or engine integration.

### Git and Previous-Story Intelligence

- The clean baseline is `5e25bf18d818cd6e68a5e5362af4802aaed98c61`; the implementation branch is `story/1-1/validated-first-school-day-schedule-fixture`.
- Before implementation, follow `docs/development-workflow.md`: create the branch from clean, up-to-date `main`, commit the initial in-progress tracking change, push, create a draft PR, and record its URL above.
- Story commits must be scoped Conventional Commits containing `1.1` immediately after the colon and the `Story-File: _bmad-output/implementation-artifacts/1-1-validated-first-school-day-schedule-fixture.md` trailer.
- Story 0.1 review found that declarative guards were insufficient: raw JSON needed an actual Application boundary test, and `Compile Remove` declarations needed effective MSBuild-item verification. Apply the same lesson here: prove real load/catalog behavior and concrete invalid cases rather than only defining DTOs or schemas.

### Latest Technical Information

- The repository pins .NET SDK 10.0.301, `net10.0`, Godot.NET.Sdk 4.7.0, xUnit v3 3.2.2, and centrally managed package versions. Do not upgrade them as part of this story.
- .NET 10 `System.Text.Json` supports strict required/unmapped/nullable contracts and native `TimeOnly`; use official behavior rather than adding an unapproved serializer/schema dependency.
- xUnit v3 runs test collections in parallel by default, so fixture and serializer tests must be isolated.
- Relevant official references:
  - [System.Text.Json options](https://learn.microsoft.com/dotnet/standard/serialization/system-text-json/configure-options)
  - [Required properties](https://learn.microsoft.com/dotnet/standard/serialization/system-text-json/required-properties)
  - [Unmapped member handling](https://learn.microsoft.com/dotnet/standard/serialization/system-text-json/missing-members)
  - [Nullable annotations](https://learn.microsoft.com/dotnet/standard/serialization/system-text-json/nullable-annotations)
  - [DateOnly and TimeOnly support](https://learn.microsoft.com/dotnet/standard/datetime/how-to-use-dateonly-timeonly)
  - [xUnit parallel execution](https://xunit.net/docs/running-tests-in-parallel)

### Project Context Rules

- Gameplay rules never belong in Godot scene scripts. Godot receives Application-owned read models and captures input only.
- Content owns raw JSON, validation, reports, and catalog construction. Runtime paths use validated catalog data and never scan/parse files mid-interaction.
- Domain remains deterministic and free of Godot, R3, Ports, JSON, logging, and infrastructure dependencies.
- Application remains free of Godot and raw JSON and accesses external capabilities through Ports.
- Expected authored-content failures are typed results/issues, not unhandled exceptions.
- CLI tools must not reference Godot. ScenarioRunner must eventually execute real Application handlers, but that work is deferred to Story 1.2.
- Keep the first vertical slice deliberately small. Do not add full semester scheduling, academics, activities, relationships, phone UI, save/load, a generic content DSL, or broad editor tooling here.

### References

- [Source: _bmad-output/planning-artifacts/epics.md#Story 1.1: Validated First School-Day Schedule Fixture]
- [Source: _bmad-output/planning-artifacts/epics.md#Epic 1: Time, Calendar, and Daily Commitments]
- [Source: _bmad-output/planning-artifacts/architectures/architecture-High School Story-2026-07-05/architecture.md#Content Authoring]
- [Source: _bmad-output/planning-artifacts/architectures/architecture-High School Story-2026-07-05/architecture.md#Content Validation]
- [Source: _bmad-output/planning-artifacts/architectures/architecture-High School Story-2026-07-05/architecture.md#Fixture Architecture]
- [Source: _bmad-output/planning-artifacts/architectures/architecture-High School Story-2026-07-05/architecture.md#Content Validation + Scenario Runner]
- [Source: _bmad-output/planning-artifacts/gdds/gdd-High School Story-2026-06-25/gdd.md#Time Blocks]
- [Source: _bmad-output/planning-artifacts/gdds/gdd-High School Story-2026-06-25/gdd.md#Daily Structure]
- [Source: _bmad-output/planning-artifacts/gdds/gdd-High School Story-2026-06-25/gdd.md#Calendar Structure]
- [Source: _bmad-output/planning-artifacts/ux-designs/ux-High School Story-2026-07-02/EXPERIENCE.md#Phone Calendar]
- [Source: _bmad-output/project-context.md#Critical Implementation Rules]
- [Source: docs/implementation.md#CLI tools]
- [Source: docs/development-workflow.md#Story branch and pull request]

## Dev Agent Record

### Agent Model Used

GPT-5 Codex

### Debug Log References

- Story context created from planning, architecture, GDD, UX, project-context, current code, recent Git history, and official .NET/xUnit documentation.

### Approved Implementation Plan

#### T1 - Stable daily schedule contracts (v2)

- **Status:** Approved
- **Included units:** T1.S1 - Add engine-independent time/calendar value objects and stable IDs; T1.S2 - Model an identified daily schedule; T1.S3 - Make 15-minute alignment explicit; T1.S4 - Keep the definition repeatable; T1.S5 - Expose a narrow Ports access contract.
- **Decisions:** D1 - One ordered `ScheduleEntry` model; D2 - `IDailyScheduleRepository` returns Domain types only; D3 - `DailySchedule` is immutable content, not runtime state; D4 - `BeforeSchoolFree` and `AfterSchoolFree` are explicit availability windows; D5 - `ScheduleEntrySemantics` is derived from `ScheduleEntryKind`; D6 - `AnchorLocationId` identifies the entry's schedule anchor.
- **Approach:** Add pure Domain schedule types and a Ports repository contract without JSON, Content, runtime feasibility, or Godot dependencies.
- **Scope:** T1.S1-T1.S5 only.
- **Files / components:** `src/HighSchoolStory.Domain/Calendar/`; `src/HighSchoolStory.Ports/Content/`; focused Domain and Application contract tests.
- **Preview references:** `DailySchedule`, `ScheduleEntry`, `ScheduleEntryKind`, `ScheduleEntrySemantics`, `IDailyScheduleRepository`.
- **Validation:** `dotnet test tests/HighSchoolStory.Domain.Tests`; `dotnet test tests/HighSchoolStory.Application.Tests`; `dotnet test tests/HighSchoolStory.Architecture.Tests`.

#### T2 - Canonical first school-day fixture (v1)

- **Status:** Approved
- **Included units:** T2.S1 - Create the authored fixture; T2.S2 - Include the approved day anchors; T2.S3 - Include the canonical six-lesson sequence; T2.S4 - Keep the Story 1.2 scenario fixture out of scope.
- **Decisions:** D1 - One file contains one daily-schedule definition; D2 - Times use `HH:mm` and durations use `durationMinutes`; D3 - `schemaVersion: 1` versions the JSON document format; D4 - JSON Schema is deferred to T3 with the strict loader and DTO contract.
- **Approach:** Add the first-day JSON only; runtime semantics remain derived from `kind` and no loader, DTO, scenario, or validator is added.
- **Scope:** T2.S1-T2.S4 only.
- **Files / components:** `content/mvp/calendar/first-school-day.json`.
- **Preview references:** Root schedule document and its lower-kebab-case entries.
- **Validation:** JSON syntax and an exact structural comparison with the Story 1.1 canonical entry sequence; Domain contract regression test.

### Completion Notes List

- Ultimate context engine analysis completed - comprehensive developer guide created.
- T1 (v2) completed: added immutable Domain daily-schedule contracts, derived entry semantics, explicit schedule anchors, and the narrow `IDailyScheduleRepository` port. Validation passed: Domain 11/11, Application 1/1, Architecture 4/4.
- Task Closure T1: all five subtasks are complete; the active T1 v2 plan, reviewed diff, and focused tests cover AC 1, 3, and 4 without adding JSON, Content, Godot, or runtime feasibility behavior.
- T2 (v1) completed: added the versioned canonical first school-day fixture with all 19 approved entries. JSON syntax and exact entry sequence passed; Domain regression passed 11/11.
- Task Closure T2: all four subtasks are complete; the fixture includes the approved schedule anchors and six-lesson sequence while keeping Story 1.2's scenario fixture out of scope.

### File List

- _bmad-output/implementation-artifacts/1-1-validated-first-school-day-schedule-fixture.md
- src/HighSchoolStory.Domain/Calendar/DailySchedule.cs
- src/HighSchoolStory.Domain/Calendar/ScheduleDuration.cs
- src/HighSchoolStory.Domain/Calendar/ScheduleEntry.cs
- src/HighSchoolStory.Domain/Calendar/ScheduleIdentifiers.cs
- src/HighSchoolStory.Domain/Calendar/ScheduleTime.cs
- src/HighSchoolStory.Ports/Content/IDailyScheduleRepository.cs
- tests/HighSchoolStory.Domain.Tests/Calendar/ScheduleContractTests.cs
- tests/HighSchoolStory.Application.Tests/Calendar/DailyScheduleRepositoryContractTests.cs
- content/mvp/calendar/first-school-day.json

### Change Log

- 2026-07-13: Completed T1 stable daily-schedule contracts and focused boundary tests.
- 2026-07-13: Completed T2 canonical first school-day JSON fixture.
