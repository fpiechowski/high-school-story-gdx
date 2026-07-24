---
name: gds-deliberative-dev-story
description: Guide BMAD story implementation through Deliberation. Use when the user says "deliberative dev story" or wants every story step approved before implementation.
---

# Deliberative Dev Story Workflow

## Overview

Guide a BMAD/GDS implementation story as a collaborative engineering session using the `deliberation` skill. The user remains the decision owner; the story remains the durable BMAD artifact. Every task or subtask is one bounded Deliberation milestone and must pass a proposal checkpoint, approved execution, result walkthrough, and explicit acceptance before the workflow advances.

## Resolution rules

- Bare paths and `{skill-root}` resolve from this skill's installed directory.
- `{project-root}` resolves to the project working directory.
- `{skill-name}` resolves to this skill directory's basename.

## On Activation

1. Load `{project-root}/_bmad/gds/config.yaml` for GDS module settings and load `{project-root}/_bmad/config.toml`, `{project-root}/_bmad/config.user.toml`, `{project-root}/_bmad/custom/config.toml`, and `{project-root}/_bmad/custom/config.user.toml` when present. Resolve `communication_language` from the core/user configuration layers and use it for every user-visible message; resolve `document_output_language` from the core configuration layers and use it for story-record updates. Never assume that both values live in the GDS module file.
2. Resolve customization with:

   ```powershell
   uv run {project-root}/_bmad/scripts/resolve_customization.py --skill {skill-root} --key workflow
   ```

   Apply `{workflow.*}` values. If resolution fails, merge the base, team, and personal customization files in that order and use the base defaults.
3. Load configured persistent project-context facts and the complete `sprint-status.yaml` when it exists. Preserve sprint ordering, comments, and unrelated entries.
4. Load and activate `skill:deliberation` for this story run and keep it active until the user explicitly disables it. Use its bounded milestones, visible roadmap, Choice/Consequence/Drift assessment, decision-ready checkpoints, explicit approval, result walkthrough, and roadmap update semantics. If `skill:deliberation` is unavailable, HALT and tell the user that the required plugin must be enabled; never silently simulate deliberation. Do not create `.deliberation/` or any plugin-owned project state unless a repository convention explicitly requires it.
5. Search for the target story and its current Deliberation state before acting. Read an existing story-side state record once on resume; never infer a gate from conversation memory alone.

## Story intake and BMAD state

Accept an explicit story path. Without one, locate the first `ready-for-dev` story in sprint order. Resume an `in-progress` story only after confirming it is the intended story.

Read the entire story before the first proposal: frontmatter, Story, Acceptance Criteria, Tasks/Subtasks, Dev Notes, Dev Agent Record, Completion Notes, File List, Change Log, and Status. Also load project-context and sprint status. Map every task/subtask to its applicable AC and Dev Notes. Requirements and constraints from those sources are fixed; implementation details remain open only inside the story scope.

Treat the story and sprint status as durable state. Preserve an existing `baseline_commit`; otherwise capture it before the first approved implementation. Set story and matching sprint entry to `in-progress` on first active work.

Before implementation, read `docs/development-workflow.md`. Require the repository's story branch and pull-request lifecycle. Ask for separate explicit authorization for commits, pushes, draft PR creation, and PR state changes; accepting a Deliberation checkpoint authorizes only the current milestone.

Only edit these story areas:

- frontmatter: `baseline_commit`, `branch_name`, `pull_request_url`;
- Tasks/Subtasks checkboxes;
- Dev Agent Record;
- File List;
- Change Log;
- Status.

Never alter Story, Acceptance Criteria, Dev Notes, task wording, or unrelated story sections. A requirement change or scope conflict is a HALT routed to `gds-correct-course`.

## Deliberation state in the story

Persist the workflow state inside the story's Dev Agent Record. Do not create a parallel implementation artifact. Keep this record append-only except for the small current-state snapshot needed for resume. Existing records from earlier workflows remain historical and must not be rewritten.

Use this structure for new stories:

```md
#### Deliberation State

- **Source:** <story path and story id>
- **Status:** in-progress | awaiting-approval | awaiting-decision | changes-requested | blocked | completed
- **Current milestone:** S2 - T1.S2 - <subtask text>
- **Next milestone:** S3 - T1.S3 - <subtask text>, or none

#### Deliberation Roadmap

- [x] S1 - T1.S1 - <accepted subtask>
- [-] S2 - T1.S2 - <current milestone>
- [ ] S3 - T1.S3 - <pending subtask>

#### Deliberation Decisions

<append-only decisions, alternatives, consequences, rationale, and user choices>

#### Deliberation Evidence

<append-only changed files, commands, results, limitations, and review findings>

#### Deliberation Checkpoints

**S2 - <timestamp>**

- **Phase:** Propose | Explain | Alternatives | Discuss | Decision | Approval | Result walkthrough
- **State:** awaiting-approval
- **Choice / Consequence / Drift:** <assessment>
- **Proposal:** ...
- **Approval boundary:** ...
- **Verification:** ...
- **User response:** pending
- **Next proposal:** S3 - ...
```

Write durable records in `{document_output_language}`. Render all user-facing labels, explanations, options, and questions in `{communication_language}`. Preserve the Deliberation phase names as behavioral concepts, but translate them in the conversation.

## Step selection and roadmap

Process the first incomplete top-level task in story order. If it has subtasks, each subtask is exactly one milestone; if it has none, the task itself is one milestone. Assign stable sequential IDs `S1`, `S2`, and so on when first encountered, mapping each to `Tn.Sn` or `Tn`; never renumber an existing mapping.

At the start of each milestone, present a visible roadmap in the main response with the current task/subtask, story progress, foreseeable later milestones, and known uncertainties. Approval authorizes only the current milestone unless the user explicitly approves a clearly listed set of milestones. Do not batch hidden work under a broad approval.

## Mandatory deliberation checkpoint

Every milestone must pass a Deliberation checkpoint before implementation, even when no material alternative exists. A checkpoint must be decision-ready and include:

- the bounded outcome and exact story unit;
- the linked AC, Dev Notes, and architecture constraints;
- the smallest implementation change set, likely files/components, and validation;
- the Choice, Consequence, and Drift assessment;
- a recommendation when material alternatives exist, with only plausible alternatives and their trade-offs;
- what approval authorizes and explicitly does not authorize;
- an invitation to approve, revise, reject, or ask questions.

Do not invent alternatives merely to fill a template. If no material Choice, Consequence, or Drift exists, say so and explain which fixed constraint determines the approach. The milestone boundary itself still requires approval because every story step must pass deliberation.

For code-bearing work, include concise implementation snippets or a clearly labelled `Proposed - not applied` diff when they materially improve the decision. Do not write implementation files during this checkpoint.

When an unresolved material decision appears, set state to `awaiting-decision`, show two or three viable options with the recommendation first, and wait for an explicit selection. Generic approval does not select an option. If the user asks a question, answer it while keeping the same checkpoint open.

If the user requests a change, set `changes-requested`, revise only the current checkpoint, append the response, and present the replacement checkpoint. If new information causes scope drift, invalidates an assumption, adds a dependency, or crosses a material consequence, stop and open a new checkpoint before proceeding.

## Approved milestone execution

After explicit approval of the current milestone and any pending decisions:

1. Re-read the latest story-side checkpoint and confirm that approval covers this exact `S#`.
2. Implement only the accepted scope with the smallest coherent diff. Preserve unrelated user changes and established C# boundaries.
3. Run focused verification proportional to the milestone, then review the diff against its AC, Dev Notes, and approved scope. Run broader regression checks when practical.
4. Update File List, Dev Agent Record, and Change Log only where the accepted work changed them. Record exact commands, results, limitations, deviations, and findings in Deliberation Evidence.
5. Present a localized result walkthrough covering the achieved outcome, the relevant player or user journey, verification result, deviations, roadmap impact, and remaining risks.
6. Ask exactly one gate question: accept this milestone, request changes, ask a question, or select a pending decision. Stop after the question; do not begin the next milestone in the same turn.

An executed milestone remains `[-]` until the user accepts its result walkthrough. Acceptance of a result does not authorize GitHub side effects. Request separate authorization for the BMAD commit/push/PR action.

## Completion and task closure

When the user accepts a result walkthrough, mark its `S#` `[x]`, append the accepted response, and update the story checkbox only for that subtask. Immediately inspect its containing top-level task. If every direct subtask is complete, verify that their accepted milestones and evidence collectively cover the task's AC and Dev Notes, with no task-level deliverable, unresolved decision, defect, scope drift, or validation gap. If valid, mark the parent task complete and append a `Task Closure` record naming the task and aggregated evidence; otherwise leave it open and state the missing gate.

After accepted tracking updates, present the next incomplete milestone in story order. Do not advance while the current state is `awaiting-approval`, `awaiting-decision`, `changes-requested`, or `blocked`.

When all milestones and tasks are accepted, run `references/checklist.md`, verify all AC, required tests, project-context rules, story tracking, and regression checks, then present a final Deliberation checkpoint. With explicit final approval and separate Git authorization, set story and sprint status to `review`, validate story Git history, push the branch, mark the draft PR ready, and recommend `gds-code-review`.

## Halt conditions

HALT rather than guess when the story is missing or ambiguous, the baseline or branch is unsafe, changes exceed the current approved milestone, tests expose an out-of-scope defect, a required story change is discovered, or the worktree prevents safe story tracking. Record the exact blocker, evidence, and smallest unblock decision in the story-side Deliberation State.
