---
title: 'Guided Dev Story Definition of Done Checklist'
validation-target: 'Story markdown ({{story_path}})'
validation-criticality: 'HIGHEST'
required-inputs:
  - 'Story markdown file with Tasks/Subtasks, Acceptance Criteria, and Dev Notes'
  - 'Completed Tasks/Subtasks section with all items marked [x]'
  - 'Updated File List, Dev Agent Record, and Change Log'
optional-inputs:
  - 'Targeted test results'
  - 'Regression test results'
  - 'Review notes'
validation-rules:
  - 'Only permitted story sections modified: baseline_commit, Tasks/Subtasks checkboxes, Dev Agent Record, File List, Change Log, Status'
  - 'Every completed task has diff-review and validation evidence'
  - 'The user explicitly approves final story completion'
---

# Guided Dev Story Definition Of Done

## Context And Scope

- [ ] Story, Acceptance Criteria, Tasks/Subtasks, Dev Notes, project-context, and sprint status were loaded.
- [ ] Every task or subtask was linked to the applicable acceptance criteria.
- [ ] Each completed task, subtask, or plan group has an active, keyed Approved Implementation Plan in Dev Agent Record.
- [ ] Each completed increment stayed within its approved story scope.
- [ ] Architecture, dependencies, and implementation patterns follow Dev Notes and project-context.

## Per-Unit Evidence

- [ ] Each task or subtask had a checkpoint before implementation.
- [ ] Each Approved Implementation Plan uses a `Tn`, `Tn.Sn`, or `Tn.Gn` key, scope text, and version.
- [ ] Each group plan lists every included subtask explicitly.
- [ ] Each Approved Implementation Plan records the chosen approach, approved scope, files or components, and validation plan.
- [ ] Material implementation decisions were surfaced with options and recorded as selected decisions in the approved plan.
- [ ] For code-bearing work, the user reviewed key implementation snippets or a clearly labeled proposed diff before approving the plan.
- [ ] Every meaningful implementation step included a concrete five-part explanation: what changed, why it was needed, how it works, why that approach was chosen, and an example of use.
- [ ] Each completed unit has a reviewed diff.
- [ ] Each completed unit has passing targeted tests or agreed observable validation.
- [ ] Known validation gaps, failures, and follow-up work are resolved or explicitly accepted by the user.
- [ ] Every task and subtask is marked complete only after its evidence was reviewed.
- [ ] After the final subtask of a task, task closure verified that subtask evidence collectively covers the task's AC and Dev Notes, with no task-level deliverable or unresolved blocker remaining.
- [ ] Each parent task closed from completed subtasks has a `Task Closure` note in Dev Agent Record naming the aggregated evidence.

## Story Quality

- [ ] Every Acceptance Criterion is satisfied.
- [ ] Relevant unit, integration, and end-to-end tests were added or updated as required by the story.
- [ ] Relevant regression checks pass, with any intentional exception documented.
- [ ] Edge cases and error conditions from Dev Notes are handled.
- [ ] No unresolved defect, scope drift, or HALT condition remains.

## Tracking And Handoff

- [ ] File List contains every new, modified, or deleted file relative to the repository root.
- [ ] Dev Agent Record contains useful implementation and debugging notes.
- [ ] Change Log summarizes the completed story work.
- [ ] Only permitted story-file areas were modified.
- [ ] The user explicitly approved finalization.
- [ ] Story Status is `review`.
- [ ] Matching sprint-status entry is `review` when sprint tracking is used.
- [ ] `gds-code-review` is recommended as the external review gate.
