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

Commit the `in-progress` tracking update with the required story commit format, push the branch, create a draft pull request, then record its URL in `pull_request_url` frontmatter and push that tracking commit. If the GitHub CLI is unavailable, unauthenticated, or cannot create the pull request, HALT and give the user the exact manual action; never continue story implementation directly on `main`.

Only edit these story-file areas:

- YAML frontmatter: `baseline_commit`, `branch_name`, `pull_request_url`
- Tasks/Subtasks checkboxes
- Dev Agent Record
- File List
- Change Log
- Status

Do not alter the story, acceptance criteria, Dev Notes, task wording, or any other section. A scope conflict, missing requirement, or needed story change is a HALT and should be routed to `gds-correct-course`.

## Guided Task Loop

Work task by task, starting with the first top-level task that contains incomplete work. If it has no subtasks, treat the task itself as the only planning unit. If it has subtasks, begin with its first incomplete subtask while presenting the whole task.

Every message that opens a new task or subtask must begin with a level-one heading in `{communication_language}` showing the current task and subtask key when applicable, followed by story progress. Count progress by leaf work units: each subtask counts once; a task without subtasks counts once; do not double-count a parent task and its subtasks. Use this shape:

```md
# Task T1 / Subtask T1.S2 - <current subtask text>

**Story progress:** 3/12 work units complete (25%); 9 remaining.
```

Then provide this structured task overview:

```md
## Task Design

- Task goal, linked AC, relevant Dev Notes, architecture constraints, and key design relationships.

## Subtasks

- [x] `T1.S1` - <completed subtask text>
- [ ] `T1.S2` - <current subtask text>
- [ ] `T1.S3` - <remaining subtask text>

## Planning Recommendation

- State one recommendation: whole task, per subtask, or custom groups.
- Explain the recommendation from task size, coupling between subtasks, shared files or tests, distinct AC or risk boundaries, and expected validation cost.
- For custom groups, list the concrete suggested groups using subtask keys and say which subtask should remain separate.

## Planning Choice

**Recommended:** <1, 2, or 3> - <one-line rationale>

Choose a planning scope:

1. Plan the whole task in one batch.
2. Plan and implement each subtask separately.
3. Create custom subtask groups.
```

Use the environment's question tool with these three answers when it is available. Otherwise render the numbered Planning Choice menu in `{communication_language}` and wait for an explicit `1`, `2`, or `3`. Treat unambiguous natural-language selection as a fallback only; if the response is unclear, show the same menu again rather than inferring a scope.

1 creates one plan for all remaining subtasks, then implements and validates them as one approved batch. 2 creates, approves, implements, validates, and completes one subtask before planning the next. 3 lets the user choose one or more remaining subtasks for the current group and creates a separate plan and implementation batch for each later group.

When the task has no subtasks, present the recommendation as a single-task plan and do not ask for a planning-scope choice.

Execute included subtasks in their story order even when a custom group was selected non-contiguously. Do not include work outside the selected planning scope. After a plan batch is validly completed, select the next incomplete task or remaining subtask in story order. If the current task still has unplanned work, return to its planning-scope choice; otherwise move to the next incomplete task.

### Design And Planning Checkpoint

Before drafting an implementation plan, run a Decision Discovery pass for the selected task, subtask, or custom group. Separate what is already fixed by the story, Dev Notes, project-context, or existing code from open implementation choices.

An open decision is a choice that materially changes component boundaries, public or internal contracts, ownership, control flow, persistence or error behavior, dependencies, test strategy, or the future shape of the code. Do not manufacture choices for details already constrained or obvious from local conventions.

For each material open decision, present a `Decision to Make` with:

- the decision and why it matters for this scope;
- two or three viable options with concrete consequences;
- a recommendation and its trade-off;
- an explicit question that lets the user select an option or state a different preference.

Use the environment's question tool when it is available; otherwise show a numbered menu for each decision. Start with the one to three decisions that block the rest of the design, group dependent choices, and resolve later decisions only after their prerequisites are chosen. Do not show a finished plan, code preview, or plan-approval question until all material open decisions are resolved.

When no material decision remains, say so explicitly and name the constraints or conventions that determined the approach. Then build the plan from the user's selected decisions. Present:

- the selected scope and its task or subtask goals;
- linked acceptance criteria and relevant Dev Notes;
- the smallest proposed change set, likely files or components, and their responsibilities;
- a red-phase or validation plan, including the narrowest useful test command;
- risks, assumptions, and any implementation decision that needs the user's answer.

For code-bearing changes, also show an `Implementation Preview` before asking for approval. Include concise snippets for the components that carry the design: public or internal signatures, important control flow, domain rules, integration boundaries, or test arrangement. Show only code that helps the user evaluate the plan; omit boilerplate and do not write files yet.

When an existing target file can be read and the intended edit is bounded, include a small unified `Proposed Diff` as well. Label it `Proposed - not applied`; it is a preview, not output from `git diff`. Do not fabricate a broad or uncertain diff. When the final shape depends on unresolved details or new files, show focused snippets and named insertion points instead.

Include a short `Decisions Chosen` summary in the plan. Then pause and ask an equivalent closed question in `{communication_language}`, for example: "Czy akceptujesz ten design i plan implementacji?"

If the user does not approve, clarify or revise the proposed design and plan, then ask again. Do not edit implementation files. If the user approves, record the plan and immediately implement it as an agent patch.

Record the approved result in Dev Agent Record under `Approved Implementation Plan`, with one entry per plan batch. Derive stable keys from the story's task tree: `T1`, `T2`, and so on for whole-task plans; `T1.S1`, `T1.S2`, and so on for individual-subtask plans; and `T1.G1`, `T1.G2`, and so on for custom-group plans. Once recorded, preserve a key even if a later correct-course update reorders the task list.

Write each entry as a level-four heading containing the key, scope description, and version. For a whole-task plan or a group, list every included remaining subtask explicitly:

```md
#### T1.G1 - Clean project boundaries group (v1)

- **Status:** Approved
- **Included units:** T1.S1 - Create Domain; T1.S3 - Create Application
- **Decisions:** D1 - <chosen option>; D2 - <chosen option>
- **Approach:** ...
- **Scope:** ...
- **Files / components:** ...
- **Preview references:** <files and symbols shown in the implementation preview>
- **Validation:** ...
```

The task, subtask, or group text is the human-readable match; the `Tn`, `Tn.Sn`, or `Tn.Gn` key is the durable workflow reference. Append entries to existing plans; do not overwrite them. When a design decision changes, append the next version for the same key with `Status: Approved`, `Supersedes: vN`, and a `Reason`; the highest approved version is the active plan. Reference the same key and version in Completion Notes when each included unit is completed.

### Approved Agent Patch

After the user approves the micro-plan, implement only its recorded scope. Use red-green-refactor in a coached form: use the approved test or observable validation, implement the minimum to satisfy it, then make only justified cleanup. Tests are part of the patch, not a later optional activity.

Break an approved patch into meaningful implementation steps. After every step that changes implementation behavior, and before proceeding to the next implementation step, give the user an `Implementation Step Context` in `{communication_language}`. Tie every point to the actual change just made; do not replace it with generic teaching text. Include:

- **Co zostało zrobione?** The concrete file, component, or behavior changed.
- **Po co zostało to zrobione?** The linked plan item, acceptance criterion, or constraint it advances.
- **Jak zostało zrobione?** The relevant control flow, data flow, API, or structural change.
- **Dlaczego właśnie tak?** The selected decision, local convention, and important alternatives or trade-offs.
- **Przykład użycia:** A short user-journey example: describe the player's action, the relevant game state, and the resulting visible or persisted outcome enabled by this step. For an infrastructure-only change, connect it to the nearest player-facing journey it enables; do not substitute an API call or isolated test case for the journey.

Group only edits that form one inseparable implementation step. State clearly when a step is purely mechanical and has no user-visible behavior, but still explain its purpose and relationship to the next behavior-bearing step. Do not begin the next step until this context has been presented; a later diff summary does not satisfy this requirement.

### Review And Completion Gate

After the agent patch, do not show another letter menu or advance to another plan batch. Immediately review the diff against the approved plan, linked AC, and Dev Notes. Run the agreed targeted tests first, then broader relevant regression checks when practical. Identify omissions, regressions, scope drift, and test gaps.

Only after review and validation pass, summarize the evidence and ask in plain language whether to mark the unit complete. Never provide a shortcut that skips review or validation.

## Unit Definition Of Done

A task or subtask may be marked complete only when all of these are true:

- The implemented behavior is traceable to its linked AC and Dev Notes.
- An active, keyed Approved Implementation Plan exists for the current plan batch and the diff follows it.
- The diff has been reviewed against the approved scope.
- Relevant automated tests or an agreed observable validation have passed; any unavoidable gap is explicitly recorded and accepted by the user.
- No unresolved defect, scope drift, or blocking question remains.
- The File List, Dev Agent Record, and Change Log accurately describe the completed work when they changed.

When the user agrees to mark work as complete, present the evidence briefly. If it is insufficient, explain the missing gate and preserve the current state. Never check a box based on an implementation claim alone. Update every included subtask only after the batch evidence passes.

After recording a completed approved plan batch, stage only its approved implementation files and required story tracking records, commit them using the repository's story commit format, and push the current story branch. A dirty worktree containing unrelated files is a HALT.

After marking a subtask complete, immediately inspect its containing top-level task. If every direct subtask is marked complete, run a task-closure verification before moving on:

- confirm no task-level deliverable remains outside the completed subtasks;
- confirm the completed subtask plans, reviewed diffs, and validation evidence collectively cover the task's linked acceptance criteria and Dev Notes;
- confirm no unresolved defect, scope drift, validation gap, or blocking question belongs to the task.

If that verification passes, mark the containing task complete in the same workflow turn and append a concise `Task Closure` note to Dev Agent Record that names the task key and the evidence aggregated from its subtasks. Do not ask for a redundant second implementation approval for a parent task that has no independent work. If any task-closure condition fails, leave the parent task unchecked, state the missing condition, and do not advance to the next top-level task until it is resolved. A task without subtasks follows the normal unit-completion gate. Then select the next incomplete work in story order. When no incomplete unit remains, proceed to the Story Completion And Review Gate instead.

## Story Completion And Review Gate

When all tasks are complete, run `references/checklist.md`. Confirm all acceptance criteria, required tests, project-context rules, tracking records, and relevant regression checks pass. Ask for explicit approval before finalizing the story; do not auto-finalize after the final unit.

With approval, update the story Status and matching sprint-status entry to `review`, preserve the full sprint file's structure, and give a concise handoff that recommends `gds-code-review` as the external review gate.

Before this handoff, run `_bmad/scripts/validate_story_git.py` from `baseline_commit` to `HEAD` with the story's numeric ID and repository-relative file path. Push the branch and mark its draft pull request ready for review. HALT if validation, push, or the pull-request transition fails.

HALT rather than force progress when the story is missing or ambiguous, its baseline is unsafe to establish, changes exceed the current approved unit, tests expose a defect outside scope, or a story requirement needs to change. State the specific blocker and offer the smallest appropriate next choice.
