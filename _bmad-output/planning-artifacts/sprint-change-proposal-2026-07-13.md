# Sprint Change Proposal — No-Cost Abstract Morning Routine and Explicit Before-School Free Time

Date: 2026-07-13  
Project: High School Story  
Status: Approved — implementation routed

## 1. Issue Summary

Story 1.1 currently models a mandatory 15-minute `Preparation` interval after wake-up. The interval is abstract, presents no player choice, and consumes playable morning time while also acting as a hard commitment and schedule-validation dependency.

The stakeholder selected a no-cost abstract routine. Morning routine remains narrative flavor; time is spent only on visible player decisions, authored commitments, or travel. The morning period from wake to the first mandatory commitment is an explicit non-reserving `before-school-free` window rather than an implicit schedule gap.

Evidence:

- The GDD describes routine as abstract, but also reserves 15 minutes for it.
- Story 1.1 makes it a required fixture entry and hard commitment.
- The UX daily loop already supports `Wake / Snooze / Start Day` without a preparation surface.

## 2. Impact Analysis

### Epic and story impact

- Epic 1 remains viable without resequencing, new stories, or a scope reduction.
- Story 1.1 changes its schedule contract, canonical fixture, and authoring validation from `wake/preparation` to `wake/before-school-free/travel reachability`.
- Stories 1.3, 1.6, and 1.7 retain their existing responsibilities for runtime feasibility, snooze/sleep, and travel. The new GDD decision removes preparation from their shared rule inputs.
- Stories 1.8 and later weekend work remain valid: weekend wake plans begin usable time directly after wake-up.

### Artifact impact

- Update GDD Daily Structure.
- Append a decision-log entry; preserve D025 and D026 as historical decisions.
- Update the source Epic 1 Story 1.1 plan and the active Story 1.1 implementation artifact.
- No architecture update: this removes a schedule concept and preserves application-owned feasibility.
- No UX update: the documented daily loop already begins `Wake / Snooze / Start Day`.
- No narrative update: routine remains optional narrative texture.
- No sprint-status update: story IDs, dependencies, status, and epic order are unchanged.

## 3. Recommended Approach

Selected approach: Direct Adjustment.

Remove mandatory `Preparation` from authored schedules. A wake-up is a zero-duration boundary followed by an explicit, non-reserving `before-school-free` window. Snooze validity considers wake time, required travel, and the next mandatory commitment; the player must wake before that commitment begins. A future optional morning action may cost time only when it offers a visible choice and a concrete effect.

Effort: Low. Risk: Low. Timeline impact: none expected. No rollback or MVP scope review is warranted because implementation has not yet created the fixture.

## 4. Detailed Change Proposals

### GDD — `Daily Structure`

Replace:

> The latest valid wake-up is calculated from the first required school commitment minus minimum preparation time and any required travel time.
>
> Morning routine is abstract and consumes 15 minutes of minimum preparation time for schedule validation.

With:

> The latest valid wake-up is calculated from the first required school commitment minus any required travel time; wake-up must occur before that commitment begins.
>
> Morning routine is abstract, automatic, and does not consume a scheduled time block or reserve player time.

### GDD decision log — append D107

```text
### D107 - No-Cost Abstract Morning Routine

Decision: Morning routine remains abstract and automatic, but does not consume a scheduled time block or act as a feasibility requirement.

Details:
- `Preparation` is not a schedule entry, hard commitment, or validation rule.
- Snooze validity is determined by wake time, required travel, and the first mandatory commitment.
- The player must wake before the mandatory commitment begins.
- Optional authored morning activities may consume time only when they present a visible player choice and a concrete effect.

Rationale: Time pressure should arise from player decisions and authored commitments, not from an invisible mandatory routine.
```

### Story 1.1 — source epic and active implementation artifact

Replace the morning-preparation schedule concept with a wake boundary. Specifically:

- remove `minimum morning preparation` from the day model;
- remove the 15-minute preparation invariant from the fixture;
- change `wake/preparation -> required school anchors` to `wake/travel reachability -> required school anchors`;
- remove `first-day-morning-preparation` from the canonical first-day sequence;
- make `first-day-wake` the only morning boundary before `first-day-lesson-1`;
- remove required preparation from hard-commitment interval policy and reachability inputs.
- add `first-day-before-school` as a 06:00-08:00 non-reserving dorm availability window; update the schedule contract, GDD, and Story 1.4's active-day wording to make this window explicit.

## 5. Implementation Handoff

Scope classification: Minor.

- Game Designer / planning owner: apply the approved GDD, decision-log, and Story 1.1 text changes.
- Developer: implement the revised Story 1.1 fixture and validation model; do not create a preparation type, field, hard commitment, or validator rule.
- QA: verify valid fixture loading and the existing schedule/arrival rejection paths; test the new absence of a preparation entry when Story 1.1 implementation begins.

Success criteria:

1. No authored first-day `Preparation` entry or minimum-preparation field exists.
2. The catalog validates the canonical first school day with wake, before-school and after-school free time, lessons, breaks/lunch, dorm return, wind-down, and latest sleep.
3. Snooze/travel feasibility cannot permit a missed mandatory commitment.
4. Morning time is spent only through player choices, authored commitments, or travel.

## Checklist Completion

- [x] Trigger, problem, and evidence identified.
- [x] Current and future epic impact assessed.
- [x] GDD, architecture, UX, narrative, and secondary artifact impact assessed.
- [x] Direct adjustment selected; rollback and MVP review not needed.
- [x] Specific, approved edit proposals recorded.
- [x] User approved implementation on 2026-07-13.
