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
  - 'Only permitted story sections modified: baseline_commit, branch_name, pull_request_url, Tasks/Subtasks checkboxes, Dev Agent Record, File List, Change Log, Status'
  - 'Every completed task has accepted ITL Adapter checkpoint, diff-review, and validation evidence'
  - 'The user explicitly approves final story completion'
---

# Guided Dev Story Definition Of Done

## Context And Scope

- [ ] Story, Acceptance Criteria, Tasks/Subtasks, Dev Notes, project-context, and sprint status were loaded.
- [ ] Every task or subtask was linked to the applicable acceptance criteria.
- [ ] Each completed task or subtask has a stable `S#` mapping and accepted checkpoint in ITL Adapter State inside Dev Agent Record.
- [ ] Each completed increment stayed within its approved story scope.
- [ ] Architecture, dependencies, and implementation patterns follow Dev Notes and project-context.

## Per-Unit Evidence

- [ ] ITL Adapter State records the current status, current and next `S#`, step-to-story-unit mapping, plan, decisions, evidence, and append-only checkpoints.
- [ ] Every step was proposed and explicitly accepted before implementation; a question or generic decision approval was never treated as authorization.
- [ ] Material decisions list two or three options with a recommendation and remain unimplemented until explicitly selected.
- [ ] Every accepted step has focused verification, a diff review, and a checkpoint ordered as What, Why, How, Why this approach, and User journey.
- [ ] Each User journey describes a player action, game state, and visible or persisted result, including for infrastructure work.
- [ ] Each completed unit has a reviewed diff.
- [ ] Each completed unit has passing targeted tests or agreed observable validation.
- [ ] Known validation gaps, failures, and follow-up work are resolved or explicitly accepted by the user.
- [ ] Every task and subtask is marked complete only after the completed ITL checkpoint was accepted and its evidence was reviewed.
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
