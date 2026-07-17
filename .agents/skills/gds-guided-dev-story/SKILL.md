---
name: gds-guided-dev-story
description: Guide approved story implementation. Use when the user says "guided dev story", "guide this story implementation", or "implement this story with approval checkpoints".
---

# Guided Dev Story Workflow

## Overview

Guide a story through the normal GDS lifecycle while acting as a senior pair-programming coach and implementation reviewer. The user remains in control of each implementation decision. Do not silently take over the story, advance to another task, or finalize the story without the user's explicit direction.

## Resolution Rules

- Bare paths and `{skill-root}` resolve from this skill's installed directory.
- `{project-root}` resolves to the project working directory.
- `{skill-name}` resolves to this skill directory's basename.

## On Activation

1. Resolve customization:

   ```powershell
   uv run {project-root}/_bmad/scripts/resolve_customization.py --skill {skill-root} --key workflow
   ```

   Apply `{workflow.*}` values. If resolution fails, read `customize.toml` and use its defaults.

2. Load `{project-root}/_bmad/gds/config.yaml` when it exists. Use its `user_name`, `communication_language`, `document_output_language`, `game_dev_experience`, `planning_artifacts`, and `implementation_artifacts` values.

3. Load the persistent project-context facts and the complete `sprint-status.yaml` when it exists. Preserve sprint ordering, comments, and unrelated entries whenever it is updated.

4. Use `{communication_language}` for conversation and `{document_output_language}` for story-record updates unless the user requests otherwise.

## Story Intake And State

Accept an explicit story path. Without one, locate the first story marked `ready-for-dev` in sprint order. A story already `in-progress` may be resumed only when the user confirms it is the intended story.

Read the full story before taking action, including its frontmatter, Story, Acceptance Criteria, Tasks/Subtasks, Dev Notes, Dev Agent Record, File List, Change Log, and Status. Map every task or subtask to the applicable acceptance criteria and Dev Notes. Treat these as the authoritative requirements and technical constraints; use the coached design checkpoint to decide implementation details that remain open within that scope.

Treat the story file and sprint status as the workflow's durable state. Preserve any existing `baseline_commit`; otherwise capture the current commit before the first approved implementation action. On first active work, set the story and matching sprint entry to `in-progress`.

### Required Story Git Lifecycle

Before the first implementation action, read the repository's story development workflow. Require a clean, up-to-date `main` checkout, create the prescribed story branch, and record its name in `branch_name` frontmatter. Preserve or capture `baseline_commit` before creating that branch.

Commit the `in-progress` tracking update with the required story commit format, push the branch, create a draft pull request, then record its URL in `pull_request_url` frontmatter and push that tracking commit. Ask for the user's separate, explicit authorization before each commit, push, pull-request creation, or pull-request state change; accepting an ITL checkpoint never grants that authorization. If the GitHub CLI is unavailable, unauthenticated, or cannot create the pull request, HALT and give the user the exact manual action; never continue story implementation directly on `main`.

Only edit these story-file areas:

- YAML frontmatter: `baseline_commit`, `branch_name`, `pull_request_url`
- Tasks/Subtasks checkboxes
- Dev Agent Record
- File List
- Change Log
- Status

Do not alter the story, acceptance criteria, Dev Notes, task wording, or any other section. A scope conflict, missing requirement, or needed story change is a HALT and should be routed to `gds-correct-course`.

## ITL Adapter Protocol

This workflow adapts the interaction semantics of `itl:dev`, `itl:explain`, and the ITL interaction protocol to a BMAD story. Read those plugin instructions when the plugin is available, but do not invoke `itl:dev` directly: its `.itl/implementations/` artifact contract is incompatible with this workflow. Do not create, update, or rely on `.itl/` files. The story remains the sole durable artifact and source of lifecycle state.

Work task by task and, within a task, in story order. Each subtask is exactly one ITL step. A task with no subtasks is exactly one ITL step. Assign stable sequential IDs `S1`, `S2`, and so on when first encountered, mapping them to `Tn.Sn` or `Tn`; never renumber an existing mapping after a story change.

### ITL Adapter State In The Story

Create and maintain a dedicated, append-only `ITL Adapter State` record inside Dev Agent Record. Existing `Approved Implementation Plan` entries are historical records: preserve them, but do not create new ones. The adapter record must contain:

```md
#### ITL Adapter State

- **Source:** <story path and story identifier>
- **Status:** in-progress | awaiting-approval | awaiting-decision | changes-requested | blocked | completed
- **Current step:** S2 - T1.S2 - <subtask text>
- **Next step:** S3 - T1.S3 - <subtask text>, or none

#### ITL Adapter Plan

- [x] S1 - T1.S1 - <accepted subtask>
- [-] S2 - T1.S2 - <current step awaiting approval>
- [ ] S3 - T1.S3 - <pending subtask>

#### ITL Adapter Decisions

<append-only pending and resolved decision records>

#### ITL Adapter Evidence

<append-only commands, results, changed files, and limitations>

#### ITL Adapter Checkpoints

**S2 - <timestamp>**

- **State:** awaiting-approval
- **What:** ...
- **Why:** ...
- **How:** ...
- **Why this approach:** ...
- **User journey:** ...
- **Verification:** ...
- **User response:** pending
- **Next proposal:** S3 - ...
```

Keep prior plan, decision, evidence, and checkpoint records intact. When a user responds, append a resolution record that faithfully summarizes the response and whether it was accepted, changes-requested, selected, a question, or blocked. Update only the current status and step summary needed for resume.

### Recovery And Gates

At every activation and before each action, read the adapter state, its current step, and its latest checkpoint before using conversational context. Reconcile newer user instructions explicitly.

- With `awaiting-approval`, do not start another step unless the newest user message clearly accepts the current non-decision checkpoint.
- With `awaiting-decision`, do not implement an option unless the user names it or unambiguously restates its defining route. Generic approval never selects an option.
- With `changes-requested`, revise and re-verify only the same step, then present a replacement checkpoint.
- With `blocked`, record the exact unblock condition and wait for the required external change or user input.
- Treat questions, reactions, partial preferences, silence, and unrelated messages as neither approval nor option selection. Answer a question while keeping the same gate open.

Every user-facing checkpoint must end after exactly one gate question: accept, request changes, ask a question, or, at a decision checkpoint, select an option. Do not run further task-progressing tools or preview the next step as accepted after that question.

### Step Proposal And Decisions

For the first incomplete `S#`, load its task/subtask wording, linked AC, Dev Notes, project-context constraints, existing code, and relevant tests. Present one bounded proposal containing its goal and boundaries, the smallest planned diff, likely files or components, and focused verification. Show concise snippets or a clearly labeled `Proposed - not applied` diff only when they help evaluate the step. Persist this proposed checkpoint with `awaiting-approval`, ask for acceptance, and stop without changing implementation files.

Before acting, stop for a decision checkpoint when an unresolved choice materially affects scope, UX, architecture, data, security, cost, compatibility, rollback, a new dependency, or reversibility. Persist two or three viable options, label the recommendation first, and state consequences and trade-offs. Do not manufacture a choice already fixed by the story, Dev Notes, project-context, or local conventions. Do not implement any option before explicit selection.

When implementation exposes scope drift, a requirement change, an unrelated defect, a destructive migration, or a failing check outside the approved step, persist it as a deviation or blocked decision. Route a required story change to `gds-correct-course`; never silently repair unrelated defects.

### Accepted Step Loop

For an accepted current step only:

1. Re-read the gate and confirm the user accepted this exact `S#`.
2. Implement the smallest coherent diff that satisfies its story scope. Preserve unrelated user changes and project conventions.
3. Run focused verification proportional to the step, then review the diff against its linked AC and Dev Notes. Record exact commands, results, changed files, limitations, and deviations in the adapter evidence before replying.
4. Persist an `awaiting-approval` checkpoint and explain the completed step in this exact order: **What**, **Why**, **How**, **Why this approach**, then **User journey**. The user journey must be a concrete player action, relevant game state, and resulting visible or persisted outcome. For infrastructure-only work, connect it to the nearest player-facing journey; do not substitute an API call or isolated test.
5. Ask whether the user accepts the completed step, requests changes, or has a question, then stop. Do not start the next subtask in the same turn.

An executed step remains `[-]` until its completed checkpoint is explicitly accepted. The user may request changes to the current step; do not mark it complete or begin another step until its revised checkpoint is accepted.

## Unit Definition Of Done

Mark a task or subtask complete only after the completed checkpoint is accepted and all of these are true:

- Its `S#` is `[x]` in ITL Adapter Plan and has an accepted checkpoint with the five required explanation sections.
- The implemented behavior is traceable to its linked AC and Dev Notes, and the diff stays within the accepted step.
- Focused automated tests or agreed observable validation passed, with evidence and any accepted limitation recorded in ITL Adapter Evidence.
- No unresolved defect, scope drift, decision, validation gap, or blocking question remains.
- File List, Dev Agent Record, and Change Log accurately describe the accepted work when they changed.

After the user accepts a completed subtask, mark only that subtask complete. Immediately inspect its containing top-level task. When all direct subtasks are complete, verify that their accepted checkpoints, diffs, and evidence collectively cover the task's linked AC and Dev Notes, and that no task-level deliverable or unresolved blocker remains. If verification passes, mark the parent task complete and append a `Task Closure` record naming the task and aggregated evidence. Otherwise leave the parent unchecked and keep the task open. A task without subtasks follows the same accepted-step gate.

Do not stage, commit, push, create a pull request, or change a pull-request state merely because a step was accepted. Request the separate authorization required by the BMAD Git lifecycle, then stage only accepted step files and permitted story tracking records. A dirty worktree containing unrelated files is a HALT for a story commit, not for local step implementation.

After a completed step is accepted and tracking is updated, select the next incomplete `S#` in story order and present only its proposal checkpoint. When no incomplete step remains, set the adapter state to `completed` and proceed to the final story gate.

## Story Completion And Review Gate

When all tasks are complete, run `references/checklist.md`. Confirm all acceptance criteria, required tests, project-context rules, adapter records, tracking records, and relevant regression checks pass. Persist a final `awaiting-approval` checkpoint and ask for explicit approval before finalizing the story.

With approval and separate authorization for required GitHub actions, update the story Status and matching sprint-status entry to `review`, preserve the full sprint file's structure, run `_bmad/scripts/validate_story_git.py` from `baseline_commit` to `HEAD` with the story's numeric ID and repository-relative file path, push the branch, mark its draft pull request ready for review, and recommend `gds-code-review` as the external review gate. HALT if validation, push, or the pull-request transition fails.
