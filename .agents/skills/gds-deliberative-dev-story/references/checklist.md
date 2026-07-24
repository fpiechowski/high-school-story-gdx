---
title: 'Deliberative Dev Story Definition of Done Checklist'
validation-target: 'Story markdown ({{story_path}})'
validation-criticality: 'HIGHEST'
required-inputs:
  - 'Story markdown with Acceptance Criteria, Tasks/Subtasks, and Dev Notes'
  - 'Deliberation State with accepted milestones'
  - 'Updated File List, Dev Agent Record, and Change Log'
validation-rules:
  - 'Only permitted story areas modified: baseline_commit, branch_name, pull_request_url, Tasks/Subtasks checkboxes, Dev Agent Record, File List, Change Log, Status'
  - 'Every completed milestone has an accepted deliberation checkpoint, diff review, and validation evidence'
  - 'The user explicitly approves final story completion'
---

# Deliberative Dev Story Definition Of Done

## Story context

- [ ] Story, AC, Tasks/Subtasks, Dev Notes, project-context, and sprint status were loaded.
- [ ] Every task or subtask was mapped to its applicable AC and Dev Notes.
- [ ] `skill:deliberation` was loaded and remains active for the story run; if unavailable, the workflow halted.
- [ ] No implementation artifact outside the BMAD story was created.

## Milestone evidence

- [ ] Every task or subtask has a stable `S#` mapping in Deliberation Roadmap.
- [ ] Every milestone received a pre-implementation deliberation checkpoint and explicit approval.
- [ ] Material Choice, Consequence, or Drift decisions were surfaced with plausible alternatives and recorded user selections.
- [ ] No artificial alternatives were invented when fixed constraints determined the approach.
- [ ] Every accepted milestone has a result walkthrough, reviewed diff, focused validation, and recorded evidence.
- [ ] Every result walkthrough includes outcome, relevant journey, verification, deviations, roadmap impact, and remaining risks.
- [ ] No milestone advanced while awaiting approval, decision, changes, or unblock.

## Story quality

- [ ] Every AC is satisfied and traceable to accepted milestone evidence.
- [ ] Relevant unit, integration, end-to-end, or Godot checks passed.
- [ ] No unresolved defect, scope drift, decision, or validation gap remains.
- [ ] Parent tasks were closed only after all direct subtasks and task-level gates were verified.

## Tracking and handoff

- [ ] Tasks/Subtasks checkboxes match accepted milestones.
- [ ] File List includes every changed file relative to the repository root.
- [ ] Dev Agent Record contains Deliberation State, decisions, evidence, checkpoints, and task closure notes.
- [ ] Change Log accurately summarizes accepted work.
- [ ] Only permitted story areas were changed.
- [ ] User explicitly approved finalization.
- [ ] Story and sprint status are `review`.
- [ ] `gds-code-review` is recommended as the external review gate.
