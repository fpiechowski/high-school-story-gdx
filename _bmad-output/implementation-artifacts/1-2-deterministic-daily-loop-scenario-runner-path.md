---
baseline_commit: 2e792db35ffb263bee5a6ced0a95935c7f692188
branch_name: story/1-2/deterministic-daily-loop-scenario-runner-path
pull_request_url: https://github.com/codex-fp/high-school-story/pull/5
---

# Story 1.2: Deterministic Daily Loop Scenario Runner Path

Status: done

## Story

As a developer,
I want a deterministic ScenarioRunner path for the first playable school day,
so that the core daily loop can be proven through the same Application commands that Godot will later use.

## Acceptance Criteria

1. **Happy-path first day.** Given the validated vertical-slice catalog and a seeded initial `GameState`, when ScenarioRunner executes the canonical first-school-day fixture, then it reviews schedule/context, honors one mandatory lesson or school commitment and surrounding anchors, resolves one valid time-spending choice, absorbs its consequence, and reaches a day-end transition through Application command handlers only. The path includes an active lesson choice, a wellbeing/time trade-off, a social discovery or dialogue touchpoint, an immediate visible consequence, and a memory/future-hook candidate.
2. **Decision snapshots.** Given a decision point with known commitments ahead, when ScenarioRunner requests an Application query or preview, then its read model includes current time, day context, next known commitment, available time window, and feasibility warning. Snapshots also include player-facing warning/block labels, next-boundary text, and severity under a controlled clock, recorded seed, fixture catalog, and in-memory stores.
3. **Expected rejection.** Given an Application command rejects an attempted scenario action, when ScenarioRunner executes it, then the runner records and asserts the typed `Result` failure without treating it as a harness crash, and the canonical `GameState` is unchanged.
4. **Regression report.** Given the first-day scenario completes, when ScenarioRunner emits its report, then it records scenario and fixture identity, seed, commands executed, time transitions, commitments honored, blocked-choice checks, snapshots, and final day state. Repeating the same fixture produces an equivalent structured report suitable for later Godot-smoke regression evidence.
5. **CLI compatibility.** `HighSchoolStory.ScenarioRunner` preserves its `--help`, `--version`, and missing-path behavior (readable stderr and exit code `2`). Invalid fixture shape and failed scenario assertions produce readable deterministic diagnostics without a Godot dependency.

## Scope Decision and Boundaries

- Implement the smallest production-shaped daily-loop kernel needed to execute the canonical `first-school-day` through Application. The fixture selects commands and assertions; it must never calculate game outcomes or mutate state itself.
- Reuse Story 1.1's validated `ContentCatalog`, `IDailyScheduleRepository`, `DailySchedule`, and `ScheduleEntry` semantics. Application receives typed schedule data through Ports and must not load raw JSON or reference Content.
- Story 1.2 **does not** introduce the reusable canonical time/commitment policy, generic feasibility calculator, travel rules, stale-preview revalidation, full attendance policy, full lesson system, dialogue system, relationship model, Memory Ledger, save/load, Godot UI, or editor tooling. Those remain owned by Stories 1.3 onward.
- The implementation may add only narrowly scoped, deterministic transition rules necessary to make this one evidence path executable. Name and test them as a daily-loop kernel, never as the canonical policy; Story 1.3 must be able to consolidate/replace those local rules without a second source of truth.
- Do not modify Godot scenes or host code. The CLI tool must not reference Godot.

## Tasks / Subtasks

- [x] Define the minimal daily-loop state and typed outcomes below the engine boundary (AC: 1-4)
  - [x] Add immutable Domain value types/state for a seeded first-day execution: schedule identity, current time/context, narrowly scoped wellbeing, visible consequences, discovered social clue, and future-hook candidate.
  - [x] Keep `GameState` canonical and mutable only through Domain/Application transitions. Do not persist or derive truth from report snapshots, CLI objects, or Godot state.
  - [x] Define explicit gameplay rejection/failure payloads and use `Result<TSuccess, TFailure>` for expected rejected commands. Exceptions remain for bugs, corrupt infrastructure, or broken invariants.
  - [x] Reuse `ScheduleTime`, `ScheduleDuration`, `DailySchedule`, and `ScheduleEntrySemantics`; do not duplicate time representations or parse schedule JSON outside Content.

- [x] Add real Application daily-loop commands, queries, and read models (AC: 1-3)
  - [x] Place feature code under `src/HighSchoolStory.Application/Features/DailyLoop/` and scenario/report contracts under `src/HighSchoolStory.Application/Scenario/`.
  - [x] Implement named command handlers for the evidence path: `ReviewDayContext`, mandatory commitment/lesson progression, one active lesson choice, one wellbeing/time choice, one social discovery touchpoint, a deliberately blocked action, and end-of-day transition.
  - [x] Every command invoked by the fixture must travel through an Application handler; ScenarioRunner must not apply a parallel state transition.
  - [x] Expose read models rather than Domain aggregates. Decision snapshots must include current time, day context, next commitment, available window, feasibility status, next-boundary text, textual warning/block label, and qualitative severity.
  - [x] Make the active lesson choice observable and deterministic. Do not build Story 3.2's full three-block lesson runtime; document any temporary lesson transition as deliberately narrow.
  - [x] Model the social/memory proof as typed, minimal evidence (for example a discovered clue and future-hook candidate), not as a premature Dialogue, Relationships, or Memory Ledger subsystem.

- [x] Define the deterministic scenario and report contracts (AC: 1, 2, 4, 5)
  - [x] Create `content/fixtures/vertical-slice/one-school-day.json` with lower-kebab-case IDs, schema version, scenario ID, seed, schedule ID, ordered commands, and expected outcomes/snapshot assertions.
  - [x] Treat the fixture as a script over validated catalog content, not a second authored calendar. It identifies `first-school-day`; its commands must not contain independently calculated availability, timestamps, or effects.
  - [x] Record scenario ID, fixture version, seed, schedule ID, command outcome, typed expected rejection, time before/after, read-model snapshot, honored commitment, and final-state fingerprint in a stable report contract.
  - [x] Sort/format report fields deterministically and avoid machine-specific paths, current wall-clock time, non-seeded randomness, or Godot frame time.

- [x] Replace the ScenarioRunner scaffold with thin composition and execution (AC: 1-5)
  - [x] Update `tools/HighSchoolStory.ScenarioRunner/Program.cs` without breaking its existing discovery contract.
  - [x] In the tool composition root, load the catalog through `DailyScheduleLoader`, construct `ContentCatalog`/`DailyScheduleRepository`, and inject the `IDailyScheduleRepository` port into Application. Application must not obtain content from filesystem paths.
  - [x] Supply controlled clock/RNG and in-memory state/store dependencies. Record the seed in the report.
  - [x] Parse and validate the scenario fixture in the tool or Content boundary; keep CLI parsing/rendering thin and do not create a second game engine.
  - [x] Keep exit code `2` for invalid invocation/missing path. Establish and test a distinct, stable nonzero exit code for malformed fixture or unmet scenario assertion; print concise diagnostics.

- [x] Add focused regression evidence (AC: 1-5)
  - [x] Add Application tests for each command handler, essential state transition, player-facing snapshot mapping, and typed rejection with state equality before/after.
  - [x] Add Scenario tests that run the canonical fixture twice and compare the structured reports, including seed, command order, snapshots, time transitions, and final fingerprint.
  - [x] Assert the happy path includes the required lesson action, wellbeing/time trade-off, social discovery, immediate consequence, future-hook candidate, mandatory commitment, and day-end transition.
  - [x] Assert the blocked action is reported as expected evidence rather than a test/process crash.
  - [x] Extend `tests/HighSchoolStory.Scenario.Tests/TestProjectTests.cs` while preserving all existing ContentValidator and ScenarioRunner help/version/missing-path tests.
  - [x] Retain architecture evidence that Domain has no Godot/R3/Ports/JSON/logging dependencies, Application has no Godot or Content dependency, and the CLI has no Godot dependency.

- [x] Verify the documented tool contract and repository gates (AC: 1-5)
  - [x] Run the narrow Application and Scenario test projects first, then `dotnet test` before handoff.
  - [x] Exercise `dotnet run --project tools/HighSchoolStory.ScenarioRunner -- --help`, `--version`, a missing fixture path, the canonical fixture, and a malformed fixture.
  - [x] Run `dotnet build "High School Story.sln"` only if project/build configuration changes.
  - [x] Do not run Godot smoke checks unless this story changes Godot-host or engine-integration code.

### Review Findings

- [x] [Review][Patch] [P1] Reject repeated `EndDay` commands with typed `DayAlreadyEnded` instead of allowing `GameState.Apply` to throw [src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopHandlers.cs:165-183]
- [x] [Review][Patch] [P1] Prevent `ProgressMandatoryCommitments` from being called at 06:00 and skipping every lesson before the active lesson path [src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopHandlers.cs:98-116]
- [x] [Review][Patch] [P1] Require the authored dorm-return and wind-down boundary before completing the day [src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopHandlers.cs:165-183]
- [x] [Review][Patch] [P1] Convert missing-schedule execution into typed `ScheduleUnavailable` diagnostics instead of throwing while building a rejected-step read model [src/HighSchoolStory.Application/Scenario/DailyLoopScenarioExecutor.cs:84-92]
- [x] [Review][Patch] [P2] Reject null command elements in a fixture and preserve malformed-fixture exit code `3` [tools/HighSchoolStory.ScenarioRunner/ScenarioFixtureLoader.cs:49-51]
- [x] [Review][Patch] [P2] Resolve repository content from the fixture path or another stable root so absolute fixture invocations work outside the repository working directory [tools/HighSchoolStory.ScenarioRunner/Program.cs:76-87]
- [x] [Review][Patch] [P2] Make the injected controlled clock affect the initial/legality path or remove the unused dependency [src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopSession.cs:13-28]
- [x] [Review][Patch] [P2] Require `expectedFailureCode` for rejected fixture commands so the runner cannot silently accept the wrong typed failure [src/HighSchoolStory.Application/Scenario/DailyLoopScenarioExecutor.cs:84-85]
- [x] [Review][Patch] [P1] Prevent public `GameState.Apply` from accepting arbitrary commitments and end-day transitions outside validated Application handlers [src/HighSchoolStory.Domain/DailyLoop/GameState.cs:84-115]
- [x] [Review][Patch] [P2] Return a day-complete snapshot without a stale latest-sleep next boundary after `DayEnded` [src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopReadModelMapper.cs:11-44]
- [x] [Review][Patch] [P2] Validate type-specific required fixture fields during loading so malformed commands return exit code `3`, not execution exit code `4` [tools/HighSchoolStory.ScenarioRunner/ScenarioFixtureLoader.cs:55-79]
- [x] [Review][Patch] [P2] Include visible consequence and clue labels in the canonical state fingerprint used for state-equality evidence [src/HighSchoolStory.Domain/DailyLoop/GameState.cs:118-137]
- [x] [Review][Patch] [P2] Return typed rejection for empty social clue/future-hook IDs instead of allowing Application constructors to throw [src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopHandlers.cs:123-143]
- [x] [Review][Patch] [P3] Make wellbeing delta arithmetic saturating for extreme integer inputs [src/HighSchoolStory.Domain/DailyLoop/DailyLoopTypes.cs:47-49]
- [x] [Review][Patch] [P2] Reuse `ScheduleEntrySemantics` for general commitment/boundary queries instead of reclassifying schedule entries only by `ScheduleEntryKind` [src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopScheduleQueries.cs:11-24]

## Dev Notes

### Implementation Flow

```text
one-school-day.json
  -> ScenarioRunner: invocation/fixture parsing and composition
  -> DailyScheduleLoader -> ContentCatalog -> DailyScheduleRepository
  -> Application daily-loop queries and command handlers
  -> typed Result + Application read-model snapshot + canonical GameState
  -> deterministic ScenarioReport
```

The fixture should describe a single deterministic journey:

1. Load the canonical Monday `first-school-day` schedule and seed the initial state.
2. Review the day at the wake/before-school context, exposing a player-facing next commitment and boundary text.
3. Honor a mandatory school commitment and make one active lesson choice.
4. Resolve one legal, explicit wellbeing/time trade-off and reflect its visible result.
5. Resolve a minimal social discovery/dialogue touchpoint and record an immediate consequence plus future-hook candidate.
6. Attempt one intentionally blocked action; retain the typed rejection and prove the state is unchanged.
7. End the day and emit the stable report.

The existing schedule provides the authored anchors: 06:00 wake and before-school availability, six 45-minute lessons with breaks/lunch, 14:45-21:00 after-school availability, 21:00 dorm return/wind-down, and 22:00 latest sleep. Treat it as the authoritative input; no handler may hardcode a duplicate first-day timetable.

### Architecture Guardrails

- `HighSchoolStory.Domain` remains engine/framework free. It must not reference Godot, R3, Ports, Application, Content, JSON, logging, or tool code.
- `HighSchoolStory.Application` owns commands, queries, runtime orchestration, read models, and outcome-to-presentation mapping. It may reference Domain and Ports, not Content or Godot.
- `HighSchoolStory.Content` owns raw JSON, schema/semantic validation, and catalog construction. The CLI can compose Content's repository implementation with Application ports.
- `HighSchoolStory.ScenarioRunner` is a CLI adapter and design/test harness. It executes real Application handlers with controlled dependencies; it is not a second simulation engine or an editor.
- Use no Godot node state, frame time, scene paths, UI visibility flags, or screen pixels as scenario truth or scenario assertions.
- Preserve `--help`, `--version`, and missing-path behavior already protected by process-level tests.

### UX and Design Constraints

- A schedule/calendar is known-context information, not a free-slot planner. The snapshot must make the next commitment and relevant availability understandable without exposing raw internals.
- Warnings and blocked outcomes require readable text and qualitative severity; colour/animation alone is insufficient.
- The evidence journey must reveal what the student chose, what it cost/gave up, and what was learned, not merely print a technical trace.
- The first path must not permit voluntary truancy, voluntary lateness, or ignoring dorm/sleep boundaries. Full generalized enforcement belongs to the later policy stories.

### Previous Story Intelligence

Story 1.1 established the only current content/runtime seam. Reuse `DailyScheduleLoader`, `ContentCatalog`, `DailyScheduleRepository`, `IDailyScheduleRepository`, immutable calendar types, deterministic ordering, and typed failure style. Do not create another loader, catalog, JSON DTO, schedule model, or raw-content access path. The ScenarioRunner fixture file is intentionally deferred by Story 1.1 and is owned here.

Recent Story 1.1 hardening also requires preserving typed diagnostics, deterministic issue/report order, atomic content loading, strict lower-kebab-case content IDs, and readable CLI failures. Do not weaken those boundaries while composing the runner.

### Project Context Rules

- Target `net10.0`; the repository pins SDK `10.0.301` with `latestPatch` roll-forward.
- Keep `dotnet test` fast and non-Godot. Most confidence belongs in Domain/Application/Content/Scenario tests; Godot smoke is a separate gate.
- ScenarioRunner must exercise the same Application command handlers that future Godot use will call. It must not bypass handlers with direct `GameState` mutation.
- Expected gameplay rejections are typed `Result` errors, not exceptions.
- Use deterministic clocks, seeded RNG, fixture catalogs, and in-memory stores for Application/Scenario tests.
- Do not parse raw JSON at runtime or on demand. Load validated content at a controlled boundary.

### File Structure Notes

Expected new files are limited to the scenario fixture, focused Domain daily-loop types, Application feature/scenario contracts, focused tests, and thin ScenarioRunner support. Expected updates are `tools/HighSchoolStory.ScenarioRunner/Program.cs` and `tests/HighSchoolStory.Scenario.Tests/TestProjectTests.cs` (or a dedicated scenario test file). Add or adjust project files only if the existing solution/test projects cannot compile the necessary focused code; do not create parallel projects.

### Latest Technical Information

Current Microsoft documentation describes `System.CommandLine` as a separate package and notes the currently documented line is package-provided. Do not add it merely to replace the existing small, tested argument parser: it would expand dependencies without helping this narrow CLI. Keep the existing parsing style unless a concrete command grammar justifies a separately approved dependency. [Source: Microsoft Learn, System.CommandLine overview, accessed 2026-07-30]

### References

- [Source: _bmad-output/planning-artifacts/epics.md#Story-1.2-Deterministic-Daily-Loop-Scenario-Runner-Path]
- [Source: _bmad-output/implementation-artifacts/1-1-validated-first-school-day-schedule-fixture.md#Dev-Notes]
- [Source: _bmad-output/planning-artifacts/architectures/architecture-High School Story-2026-07-05/architecture.md#Scenario-Runner-and-Determinism]
- [Source: _bmad-output/planning-artifacts/architectures/architecture-High School Story-2026-07-05/architecture.md#Content-Validation--Scenario-Runner]
- [Source: _bmad-output/planning-artifacts/gdds/gdd-High School Story-2026-06-25/gdd.md#Core-Gameplay-Loop]
- [Source: _bmad-output/planning-artifacts/ux-designs/ux-High School Story-2026-07-02/EXPERIENCE.md#Daily-Loop]
- [Source: _bmad-output/project-context.md#Critical-Implementation-Rules]
- [Source: docs/implementation.md]
- [Source: docs/development-workflow.md]

## Dev Agent Record

### Agent Model Used

GPT-5.6

### Debug Log References

- Story-context analysis completed from epics, architecture, GDD, UX, project context, previous story, current code, Git history, and current Microsoft documentation.

### Implementation Plan

- Keep `GameState` immutable and Domain-owned; express each allowed daily-loop change as a `DailyLoopTransition`.
- Route the canonical path through named Application handlers and expose only `DailyLoopReadModel`/`DecisionSnapshot` to the scenario adapter.
- Let the fixture identify commands and assertions only; load the authored schedule through `DailyScheduleLoader` and execute with controlled clock/RNG dependencies.
- Treat expected rejection as a typed `Result` outcome, record before/after fingerprints, and format the report from stable IDs, seeded values, and authored schedule boundaries.

### Completion Notes List

- Ultimate context engine analysis completed - comprehensive developer guide created.
- Deliberation decision accepted: implement a narrow production-shaped daily-loop kernel now; reserve the canonical reusable policy for Story 1.3.
- Domain daily-loop kernel added with immutable seeded `GameState`, transition-only updates, deterministic fingerprints, wellbeing/evidence value types, and typed gameplay failures. Domain tests pass (14/14).
- Application daily-loop handlers and read models added for context review, mandatory progression, active lesson choice, wellbeing trade-off, social discovery, blocked action, and day end. Application tests pass (7/7).
- Deterministic scenario contracts, strict fixture loading, canonical `one-school-day.json`, report formatting, and twice-run equivalence evidence added. Scenario tests pass (16/16).
- ScenarioRunner CLI now composes validated Content with Application, controlled clock/RNG, and stable report output. Process tests cover canonical execution, malformed fixture exit 3, assertion exit 4, and the existing CLI contract (14/14 Scenario tests).
- Focused regression evidence covers every daily-loop handler, canonical fixture equivalence across two runs, typed blocked-choice state equality, architecture boundaries, and required CLI diagnostics.
- Review hardening closed all 15 patch findings: transition mutation is internal to the Domain/Application boundary, day-end and progression boundaries are explicit, fixture diagnostics are typed and strict, and deterministic fingerprints include player-visible evidence.
- Repository verification passed: `dotnet test` 87/87; `dotnet build "High School Story.sln"` 0 warnings/0 errors; ScenarioRunner help/version/missing/canonical/outside-cwd manual matrix exited 0/0/2/0/0; malformed fixture and unmet assertion are covered by process tests with exit 3/4.

### File List

- _bmad-output/implementation-artifacts/1-2-deterministic-daily-loop-scenario-runner-path.md
- src/HighSchoolStory.Domain/DailyLoop/DailyLoopTypes.cs
- src/HighSchoolStory.Domain/DailyLoop/GameState.cs
- src/HighSchoolStory.Domain/Properties/AssemblyInfo.cs
- src/HighSchoolStory.Ports/Time/IClock.cs
- src/HighSchoolStory.Ports/Time/IRandomSource.cs
- src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopCommands.cs
- src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopHandlers.cs
- src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopReadModels.cs
- src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopReadModelMapper.cs
- src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopScheduleQueries.cs
- src/HighSchoolStory.Application/Features/DailyLoop/DailyLoopSession.cs
- tests/HighSchoolStory.Application.Tests/DailyLoop/DailyLoopSessionTests.cs
- tests/HighSchoolStory.Domain.Tests/DailyLoop/GameStateTests.cs
- src/HighSchoolStory.Application/Scenario/DailyLoopScenarioContracts.cs
- src/HighSchoolStory.Application/Scenario/DailyLoopScenarioExecutor.cs
- tools/HighSchoolStory.ScenarioRunner/ScenarioFixtureLoader.cs
- tools/HighSchoolStory.ScenarioRunner/ScenarioReportFormatter.cs
- content/fixtures/vertical-slice/one-school-day.json
- tools/HighSchoolStory.ScenarioRunner/Program.cs
- tests/HighSchoolStory.Scenario.Tests/TestProjectTests.cs
- tests/HighSchoolStory.Scenario.Tests/DailyLoopScenarioTests.cs
- _bmad-output/implementation-artifacts/sprint-status.yaml

### Change Log

- 2026-07-30: Added the minimal engine-independent daily-loop state and typed failure contracts.
- 2026-07-30: Added Application command handlers, decision snapshots, controlled runtime ports, and canonical session transitions.
- 2026-07-30: Added the canonical deterministic scenario fixture, typed scenario contracts, strict fixture parsing, and stable report evidence.
- 2026-07-30: Replaced the ScenarioRunner scaffold with thin validated-content composition and stable exit-code/report behavior.
- 2026-07-30: Completed focused regression evidence and repository verification gates; implementation is ready for review.
- 2026-08-17: Closed the adversarial review findings, expanded regression coverage, and marked the story done pending merge.
