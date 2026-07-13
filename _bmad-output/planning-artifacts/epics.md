---
stepsCompleted: [1, 2, 3, 4]
inputDocuments:
  - "_bmad-output/planning-artifacts/briefs/brief-High School Story-2026-06-25/brief.md"
  - "_bmad-output/planning-artifacts/gdds/gdd-High School Story-2026-06-25/gdd.md"
  - "_bmad-output/planning-artifacts/ndds/ndd-High School Story-2026-06-30/narrative-design.md"
  - "_bmad-output/planning-artifacts/ux-designs/ux-High School Story-2026-07-02/EXPERIENCE.md"
  - "_bmad-output/planning-artifacts/ux-designs/ux-High School Story-2026-07-02/DESIGN.md"
  - "_bmad-output/planning-artifacts/architectures/architecture-High School Story-2026-07-05/architecture.md"
---

# High School Story - Epic Breakdown

## Overview

This document provides the complete epic and story breakdown for High School Story, decomposing the requirements from the GDD, UX Design if it exists, and Architecture requirements into implementable stories.

## Requirements Inventory

### Functional Requirements

FR1: The game must support a custom student protagonist entering a fictional European high-school town, living in a dorm, and forming an emergent school identity through repeated choices.

FR2: The MVP must cover one 20-week autumn-to-winter semester as a restrained vertical slice, not a compressed full three-year game.

FR3: The full-game design must support a three-year school journey with days, weeks, semesters, school years, promotion gates, graduation, and reflective epilogue outcomes, while MVP defers full-run completion.

FR4: The core loop must let the player review known schedule/context information, spend scarce time on lessons, study, relationships, travel, recovery, fun, clubs, events, and off-school life, then absorb consequences and repeat.

FR5: The game must use 15-minute time blocks as the standard scheduling unit, with activities costing 15, 30, 45, 60, or 90 minutes where appropriate.

FR6: The game must enforce school-day anchors, including 06:00 default wake, explicit before-school and after-school free-time windows, mandatory school attendance, lesson schedules, breaks, lunch, dorm return rules, final dorm-only wind-down time, and latest sleep rules.

FR7: The game must support schedule-valid snooze choices that trade morning agency for recovery without allowing truancy or voluntary lateness.

FR8: The game must validate travel, activity duration, and return requirements before allowing activities that could conflict with school, curfew, dorm return, or other hard commitments.

FR9: The game must support campus movement without travel cost between dorm, school, and shared campus entrance, while off-campus locations normally cost travel time.

FR10: The game must support weekend wake plans and looser weekend day structure while preserving dorm return and sleep constraints.

FR11: The game must model Energy, Stress, and Mood as core wellbeing resources, with Energy as the main hard-gating resource, Stress as broad soft pressure, and Mood as qualitative emotional state rather than a simple third meter.

FR12: The game must provide recovery, sleep, rest, relaxing activity, favorite-aligned activity, and snooze effects that alter Energy, Stress, and Mood.

FR13: The game must support activities that resolve immediately into changes to academics, relationships, wellbeing, discovered information, event access, future obligations, money, flags, or memories.

FR14: The game must implement active lessons as 45-minute sessions with exactly three 15-minute blocks, one player lesson action per block, per-block feedback, possible teacher checks, and a final lesson result.

FR15: Lesson actions must use stable top-level categories with contextual variants: Focus/Listen Actively, Take Notes/Light Participation, Study Another Subject, Use Phone/Text, Quiet Recovery/Zone Out, Chat/Pass Note, and Risky Distraction.

FR16: Lesson risk must combine teacher/lesson strictness and action risk profile, show qualitative risk labels, and resolve caught/attention outcomes with academic, wellbeing, teacher-impression, restriction, or relationship consequences.

FR17: The game must model academics through subject knowledge, visible current grades, homework/deadlines, smaller tests, exam readiness, teacher impression, and subject-level exam results.

FR18: MVP academics must include 4 subjects, 6-8 homework tasks across the semester, one smaller test per subject before the exam period, and a Week 20 exam period.

FR19: The full game must use academic promotion as the one hard progression fail gate, requiring all subjects to meet the minimum passing grade at end-of-year promotion.

FR20: Academic failure must offer large checkpoint recovery options: one week before the exam, four weeks before the exam, or the beginning of the semester.

FR21: Character creation must support name, gender, starting attributes, favorites/preferences, and pixel-art sprite appearance.

FR22: Starting attributes must use a 1-5 point-buy system across 6 attributes, start from baseline 2, provide 6 additional points, allow lowering attributes to gain points, and remain fixed after creation.

FR23: Favorites/preferences must personalize social preference, free time preference, music, media, and food; they must influence Mood, compatibility, hangouts, gifts, dialogue, texting, social media, and activity flavor without hard-locking major content.

FR24: Relationships must track hidden Bond, visible familiarity tier, compatibility modifiers, schedule habits, preferred locations, story arc state, and story flags.

FR25: Relationship progression must combine systemic repeatable interactions and authored story milestones; Bond alone must not unlock major progression without context, flags, timing, location, availability, and prior attention.

FR26: The game must support visible relationship tiers: Stranger, Acquaintance, Friend, Close Friend, and Best Friend/Romantic Partner where eligible.

FR27: Romance must be authored per character through orientation, compatibility, relationship tier, story state, and choices; romance is a high-tier path/state, not a universal grind or superior friendship tier.

FR28: MVP relationship content must include 5 classmates, 7 total relationship/story beats, Nell as the 3-beat partial arc proof case, and one intro/early beat each for Charlotte, Oliver, Amelia, and Leo.

FR29: Nell's MVP progression must reward restraint, attention, boundary respect, authorship consent, story flags, and non-club access routes.

FR30: Clubs must provide recurring activities, social circles, story opportunities, identity texture, and calendar pressure without becoming independent grind systems.

FR31: MVP clubs must include Science Club and Literary Club, each with weekly 60-minute meetings and one 90-minute special event.

FR32: Club membership may add variants, insider context, preparation, lower friction, stronger outcomes, or flags, but must not hard-gate essential classmate progression.

FR33: The smartphone must act as the major information/system hub with Calendar, Messages, Social, Map, School App/Journal, Settings, notifications, and safe save/load access.

FR34: The phone must not execute ordinary activities remotely; ordinary activities must start through world presence at NPCs, objects, locations, or events.

FR35: Phone Calendar must show known commitments, lessons, lunch, club meetings, exams, events, deadlines, and timed clues, but not act as a formal slot-planning tool.

FR36: Phone Map must show known destinations, travel time, travel feasibility, and usable time before hard commitments, but not live NPC radar or hidden schedules.

FR37: Phone Social must show feed/profile information, discovered facts, preferences, public clues, relationship status, and current-location clues for known eligible NPCs without exposing full world truth.

FR38: School App/Journal must show subject grades, homework, deadlines, subject status, teacher notes, exam calendar, and exam readiness in a scannable form.

FR39: The game must support MVP locations/spaces: dorm/private room, outdoor/courtyard, hallway/common area, classrooms, cafeteria, library/study room, Science Club room, Literary Club room, cafe, park, convenience store, and event-specific spaces as needed.

FR40: Off-school life must support cafe, park, dorm/private room, and convenience store roles, including social hangouts, recovery, study, small purchases, gifts, errands, and limited odd jobs.

FR41: The MVP must include fixed and special events: Class Integration Day, Harvest Evening, Science Showcase Sprint, Zine Deadline Crisis, and Week 20 exam period.

FR42: Harvest Evening must support partial attendance, fixed event ending, limited time, limited money, social bandwidth, small purchases, preferences, Mood/Stress effects, social clues, brief encounters, and memory flags without minigames or large economy simulation.

FR43: Science Showcase Sprint must resolve through activity choices involving research, setup, presentation, team support, or fixing a last-minute issue, with academic, club, relationship, wellbeing, and identity effects.

FR44: Zine Deadline Crisis must resolve through choices around editing, layout, errands, emotional support, public reading, consent, authorship, reputation, and protecting boundaries, while supporting member and non-member routes.

FR45: Dialogue must support large portrait mode for important scenes and small bubble mode for barks/short low-weight exchanges.

FR46: Dialogue choices must be concrete text options with limited branching and convergence, producing tone, relationship, flag, memory, wellbeing, and future acknowledgement effects.

FR47: The game must support authored beats, routine micro-dialogue, phone/message dialogue, reflection text, and compact variant lines without creating uncontrolled branching scope.

FR48: The MVP must include narrative beats: prologue arrival, first school day/onboarding, five classmate intro/progression beats, Science Showcase Sprint, Zine Deadline Crisis, Harvest Evening, exam pressure, Week 20 exam period, and Semester Reflection/Memory Ledger.

FR49: Story gating must combine Bond/tier, flags, calendar timing, location/context, availability, prior attention, wellbeing, attributes, club participation, and player choices as appropriate.

FR50: The game must expose all 5 MVP classmates early through class, social feed, events, lessons, lunch, hallway moments, or ambient school context; missed intros may delay or alter progression but must not block semester completion.

FR51: The Memory Ledger/Semester Reflection must summarize memories, missed chances, relationship states, academic standing, wellbeing patterns, identity signals, and event participation without becoming an epilogue or quest log.

FR52: Save/load must support autosave at start of day and end of day/sleep, safe-context manual save, load, and recovery from academic failure checkpoints.

FR53: Manual save must be blocked during lessons, exams, action resolution, active dialogue, and other unstable runtime modes.

FR54: The game must support a light allowance/money system for optional consumables, coffee/snacks, small gifts, hangout/date extras, preparation items, and limited odd jobs.

FR55: The game must support top-down pixel-art exploration with world interaction prompts, HUD, travel, activity choice overlays, result feedback, phone overlays, lesson mode, dialogue, and end-of-day/semester flows.

FR56: The MVP must include asset scope for 5 classmate visual sets, player sprite customization, 4 subject/classroom presentations, 10 MVP locations/spaces, smartphone UI, calendar/schedule UI, and baseline NPC portrait expressions.

FR57: Audio must support routine school ambience, phone notifications, classroom/study sounds, cafe/park ambience, UI feedback, event music, exam/stress music, relationship/reflection themes, and optional non-verbal reactions, without full voiced dialogue.

FR58: The game must support player-facing feedback for blocked choices, time costs, risks, relationship updates, academic changes, wellbeing effects, notifications, missed opportunities, and memory entries.

FR59: The MVP ending must be a personalized semester reflection with variation based on events, relationships, Nell progression, club participation, academic standing, wellbeing pattern, romance hints, event memories, and identity signals, with no true/golden ending.

FR60: The implementation must support content-driven authoring for calendars, activities, effects, flags, dialogues, availability, feedback, events, phone/social data, lessons, academics, and relationships.

### NonFunctional Requirements

NFR1: The game must sustain 60 FPS during representative school navigation, phone use, lessons, dialogue, travel, and day transition on the target PC.

NFR2: Steam Deck support is desirable but not a binding MVP requirement; UI and interaction must be Steam Deck-conscious where low-cost, especially at 720p.

NFR3: Scene transitions must complete in under 2 seconds on target PC spec.

NFR4: Save/load must complete in under 3 seconds for normal MVP run state.

NFR5: Day transition must complete in under 3 seconds for normal MVP run state.

NFR6: One in-game day should be completable in under 30 minutes.

NFR7: The representative performance loop must include school navigation, lesson decisions, smartphone use, relationship dialogue, travel, and day transition.

NFR8: The game must be PC/Steam-first; mobile, console, and web are out of first release scope.

NFR9: Core UI must be controller-first, keyboard-compatible, and mouse-auxiliary.

NFR10: Every phone app, lesson surface, dialogue choice, activity choice, and confirmation overlay must be fully operable without a mouse.

NFR11: Focus state must be unambiguous at Steam Deck size, follow reading/decision order, and avoid mouse-only affordances for core actions.

NFR12: Text must be readable at 1080p and Steam Deck-conscious handheld distance; important dialogue choice tone cues must not be truncated.

NFR13: Time pressure, risk, notifications, and wellbeing/resource states must not rely on color alone; labels, icons, shapes, positions, or reviewable notifications must reinforce them.

NFR14: UI must use the approved cozy pixel-art visual identity, design tokens, typography, spacing, small radii, crisp pixel outlines, and warm school-life palette from DESIGN.md.

NFR15: The UI must avoid combat framing for lessons, including HP bars, enemy framing, attack language, or boss-like teacher treatment.

NFR16: The phone must feel like a pixel-art smartphone hub, not a literal mobile OS clone or a productivity planner.

NFR17: Calendar must avoid looking like a selectable productivity-app schedule planner; Map must avoid becoming a remote quest/activity launcher.

NFR18: The game must be localization-ready for text expansion, with roughly 30% planning buffer for UI, dialogue, phone, journal, social posts, buttons, tooltips, and Memory Ledger text.

NFR19: MVP is English-only and does not include full voice acting.

NFR20: Content and branching scope must remain solo-dev feasible, with MVP writing target around 45,000-70,000 words and caution ceiling around 80,000 words.

NFR21: The game must avoid an optimization-only feel; balance should support multiple viable lifestyles and meaningful regret without punishing imperfect play.

NFR22: High Stress must create visible friction before it becomes punitive; low Energy must limit high-value actions while leaving low-impact fallbacks.

NFR23: The game must avoid uncontrolled content growth, including large routine micro-dialogue pools, full MVP romance routes, large side-quest chains, collectible lore grind, large economy/shop systems, and broad town simulation.

NFR24: Architecture must keep gameplay rules testable outside Godot; no gameplay rule is considered tested only because it worked in a scene.

NFR25: Default confidence gate must remain fast, non-flaky `dotnet test` without launching Godot.

NFR26: Godot smoke/E2E tests must remain few, focused, and separate from the fast inner loop where practical.

NFR27: The implementation must use typed Result errors for expected gameplay/application failures; exceptions are reserved for programmer bugs, broken invariants, corrupted infrastructure, and unexpected failures.

NFR28: The implementation must preserve player-facing causality from domain/application decisions and content sources to blocked choices, notifications, phone clues, relationship updates, lesson results, and Memory Ledger entries.

NFR29: Content validation must catch design correctness issues, not only syntax.

NFR30: Scenario execution and tests must be deterministic through controlled clock, seeded RNG, fixture catalogs, and in-memory stores.

### Additional Requirements

- Use Godot Engine 4.7 .NET with `Godot.NET.Sdk/4.7.0` and a .NET 10 SDK baseline.
- Use the Godot-generated C# solution as the host, then expand it into clean C# projects for Domain, Ports, Application, Content, tools, and tests.
- Do not adopt a full third-party Godot starter template; Chickensoft `GodotGame` may be used only as a reference for setup, tests, coverage, `global.json`, and CI ideas.
- Treat Godot as presentation/infrastructure host only; gameplay rules must live in clean C# projects outside Godot-specific code.
- Enforce a Hexagonal Architecture with pragmatic DDD: Domain owns canonical rules/state, Application owns commands/queries/runtime projections, Ports define adapter contracts, Content owns loading/validation/catalogs, and Godot implements concrete adapters/views.
- `HighSchoolStory.Domain` must have no project references and no dependencies on Godot, R3, Ports, Application, Content, JSON, logging, adapters, or infrastructure.
- `HighSchoolStory.Ports` may reference Domain for stable IDs/value objects/result types, but must not expose mutable aggregates for adapter-side manipulation.
- `HighSchoolStory.Application` must reference Domain, Ports, and R3; it owns command handlers, query services, runtime mode, save orchestration, R3 projections, read models, and side-effect orchestration through ports.
- `HighSchoolStory.Content` must reference Domain and Ports; it owns JSON loading, schema validation, content validation, preview reports, migrations, ContentCatalog, and repository implementations.
- The root Godot host project must reference Application, Ports, and Content, and must not directly reference Domain unless a concrete need is explicitly approved.
- The root Godot `.csproj` must explicitly exclude clean library, tool, and test source folders from direct compilation.
- Create solution structure including `src/HighSchoolStory.Domain`, `src/HighSchoolStory.Ports`, `src/HighSchoolStory.Application`, `src/HighSchoolStory.Content`, `src/HighSchoolStory.Godot`, `content/mvp`, `content/schemas`, `content/fixtures/vertical-slice`, `tools/HighSchoolStory.ContentValidator`, `tools/HighSchoolStory.ScenarioRunner`, and test projects.
- Runtime mode must be Application-owned; Godot node visibility and scene-local booleans must not become runtime truth.
- Application must expose hybrid command/query boundaries using explicit commands such as travel, start activity, choose lesson action, select dialogue option, end day, and save game.
- Application queries/projections must return screen-ready read models such as HUD, PhoneCalendar, PhoneMap, PhoneSocialProfile, ActivityChoice, Lesson, Dialogue, EndOfDaySummary, and SemesterReflection.
- All mutating Application commands must use a stable command identity, expected state revision where relevant, and documented replay semantics: exact replay returns the original Result without mutation, conflicting key reuse returns `CommandConflict`, and stale commands return typed Results with zero state/storage effects.
- Final readiness evidence must include a versioned traceability matrix mapping FR1-FR60, NFR1-NFR30, and UX-DR1-UX-DR27 to owning story, coverage tag, fixture/evidence type, and any approved waiver or deferral reason.
- Godot views must render Application read models/projections and must not query Domain directly, mutate GameState, or mutate projections.
- R3 is approved only at the Application/Godot boundary; Domain must remain deterministic and R3-free.
- R3 projections are read/UI state, not persisted truth; after save load, Application must rebuild projections from canonical GameState.
- Godot views must subscribe to projections through presenters/adapters and dispose subscriptions with Godot node/view lifecycle.
- Input must flow from Godot InputMap through GodotInputAdapter into Application intents/commands.
- Logical focus/selection state for Application screens belongs to Application reactive UI state even when Godot maintains a technical focused Control.
- Save system must use versioned JSON snapshots in an envelope containing schema version, game version, content version, saved timestamp, slot metadata, payload, and future cloud/conflict metadata fields.
- Application must orchestrate save/load, save eligibility, migrations, envelope creation, validation, state replacement/restoration, and projection rebuild.
- Godot implements `ISaveStore` and file access only; it must not serialize node trees or decide save legality.
- Content authoring must use JSON systems and custom JSON dialogue for MVP, with typed nodes, choices, conditions, effects, and flags, no arbitrary scripting, and no full DSL initially.
- Runtime command handlers must not perform ad-hoc file reads; validated content must load into ContentCatalog at startup or controlled boundaries.
- ContentValidator must validate schema correctness, references, availability, dialogue graph, phone clues, GDD/UX rules, dead nodes, missing variants, effect validation, save/content version compatibility, and playable reachability.
- ScenarioRunner must execute deterministic scenarios through the same Application command handlers used by Godot, not a parallel simulation engine.
- ScenarioRunner must cover one-day/vertical-slice flows including 1-2 locations, one lesson flow, one activity occasion, one phone clue, one relationship beat, one save slot, one validation report, and read-model snapshots.
- Architecture tests must fail if Domain references Godot/R3/Ports/Application/Content/JSON/logging, if Application references Godot, if Content references Application/Godot, or if the Godot host compiles clean library/tool/test sources directly.
- Test projects must include Domain, Application, Content, SaveMigration, Scenario, Architecture, and GodotSmoke coverage.
- Quality gates for architecture/rules/content/save/vertical-slice changes must include `dotnet test`, architecture tests, ContentValidator on fixture catalog, save migration round-trip, ScenarioRunner smoke, and minimal Godot scene/input smoke where appropriate.
- Debug tools must live as Application/Content/Ports capabilities with Godot/CLI adapters; debug commands should go through Application commands and must not normalize illegal states as valid gameplay.
- Content fixtures must exist from day one: minimal catalogs, GameState builders, read model snapshots, golden save fixtures, save migration fixtures, and vertical-slice fixture catalog.
- Use project-owned typed Result objects for expected blocked choices, content-authoring failures, corrupted saves, transient illegal commands, and adapter/infrastructure failures.
- Use structured logging in Application, adapters, tools, and host; Domain must not reference logging.
- Domain configuration/constants, content tuning, player settings, and platform/dev settings must be separated by ownership and volatility.
- Use typed domain events plus Application projection pipeline; do not introduce a global stringly-typed gameplay event bus.
- Godot signals may handle local presentation wiring, animation completion, button clicks, focus movement, and view lifecycle only.
- Audio and notifications must be Application-driven through outbound ports/projections; Domain emits semantic results/events but not concrete sound cues or UI copy.
- Phone clues, Calendar, and Map may reveal information but must not start ordinary activities remotely; StartActivityCommand requires current location, interaction context, ActivityOccasion, availability result, and feasibility result.
- ActivityOccasion must be derived from current state/content/location/anchor and revalidated on command execution; prompt caches are disposable and cannot become canonical truth.
- Time/commitment preview and command execution must derive from the same feasibility logic; previews are informative but non-authoritative and never mutate state.
- LessonSession must resolve blocks in order, reject or safely handle duplicate block commands, preserve per-block outcomes, and advance time exactly 3x15 minutes.
- DialogueSession must enforce availability of choices, apply typed effects once, set flags/bond/known facts as authored, and hide raw relationship math from projections.
- Memory Ledger must store intentional typed reflection records, not raw event logs, and must explicitly handle duplicates.
- First vertical slice must be built in this order: content fixture, ContentValidator, ScenarioRunner happy path, Time/Commitment policy, ActivityOccasion, LessonSession, DialogueSession/effects, Save/load rebuild, Godot smoke view.
- Do not begin the first slice by implementing full Academics, full Memory Ledger, all phone apps, a large dialogue DSL, many locations, Steam services, broad save migration infrastructure, or broad UI polish.

### UX Design Requirements

UX-DR1: Implement the smartphone as the major information/system hub with app grid, Calendar, Map, Social, Messages, School App/Journal, Settings/Controls/Accessibility, manual save/load where legal, and phone-level top bar.

UX-DR2: Implement phone system chrome with black top bar, date on the left, current time centered, Energy as a battery-style icon on the right, and app-level Back/Home controls inside app chrome.

UX-DR3: Implement Phone Social with Feed/Profile/Clubs-style sections, Instagram-like feed behavior, red notification badges, discovered facts/preferences, descriptive relationship status, contextual Message shortcut, and optional routing to Calendar/Map when actionable.

UX-DR4: Implement Phone Calendar as a reference view for known commitments and timed events, using a 15-minute timeline/grid if helpful, while ensuring empty time does not look selectable.

UX-DR5: Implement Phone Map as symbolic pixel-art map with known destinations, travel time, travel feasibility, and usable time before hard commitments; it must not show exact live NPC positions or hidden schedules.

UX-DR6: Implement Exploration HUD with always-visible time/day, lower-left Energy/Stress/Mood cluster, plain money text near lower-right phone access, contextual pressure alerts only when needed, in-world interaction prompts, and upper-right transient toasts.

UX-DR7: Implement world interaction prompts anchored near relevant NPCs, objects, doors, bus stops, or map-edge affordances, with adaptive input glyph plus short verb and target name.

UX-DR8: Implement Activity Choice + Time Cost Confirmation as an overlay/state opened only after world interaction, showing time cost, known consequences, warnings/blocks, and confirmation/cancel paths.

UX-DR9: Implement Activity Result Feedback as an overlay/toast/result state that communicates time, wellbeing, academic, relationship, money, flags, event, and missed-opportunity effects without requiring spreadsheet-style inspection.

UX-DR10: Implement Lesson Flow as a focused seated classroom composition rather than normal top-down camera, with teacher/board visible, circular three-block lesson clock, action choices, teacher pressure panel, block feedback, teacher checks, and lesson result.

UX-DR11: Implement Teacher Panel with teacher name, Attention status, Strictness status, and optional descriptor such as question-heavy or tolerant today.

UX-DR12: Implement lesson action list using stable categories with contextual variant labels/tooltips, qualitative risk tags, and hidden exact probabilities.

UX-DR13: Implement controller-first input contract with Confirm/Interact, Back/Cancel, Open Phone, focus movement, contextual details/secondary action, tab/panel switching, app navigation, configurable bindings, and adaptive glyphs.

UX-DR14: Implement focus navigation for every phone app, lesson surface, dialogue choice, activity choice, confirmation overlay, and system surface; focus order must follow reading path and primary decision path.

UX-DR15: Implement Back behavior that moves one layer up in the active surface before closing the phone or canceling a major flow.

UX-DR16: Implement state patterns for time-cost confirmation, result feedback, notifications/toasts, large/small dialogue, wellbeing detail, exam pressure, partial event attendance, and Semester Reflection/Memory Ledger.

UX-DR17: Implement large portrait dialogue for authored/important scenes and small bubble dialogue for short barks/low-weight exchanges, with concrete choice text and consequence states.

UX-DR18: Implement wellbeing HUD/status presentation with icons, labels, values/states, and resource colors for Energy, Stress, and Mood, reinforced without relying on hue alone.

UX-DR19: Implement notification badges as red circular badges with white counts only for unread/actionable information, with non-color reinforcement where needed.

UX-DR20: Implement toasts as compact upper-right notifications with icon, short title, one-line detail, and reviewability through Phone Notifications when actionable.

UX-DR21: Implement accessibility floor: readable/predictable controller and keyboard focus, Steam Deck-conscious text, clear focus states, remapping, active glyph set, qualitative risk labels, non-color-only feedback, and optional intensity controls for shake/flashing/strong motion/haptics.

UX-DR22: Implement phone UI/design tokens from DESIGN.md: ink/paper/dark panel/autumn/school/social/resource colors, pixel-readable typography, zero letter spacing, 8px-feeling grid, small radii, crisp pixel outlines, and no glassmorphism.

UX-DR23: Implement HUD, phone, lesson panel, phone top bar, bottom nav, badge, prompt, toast, wellbeing, money text, lesson clock, risk tag, and relationship/fact chip components using the specified visual contract.

UX-DR24: Implement visual rules that avoid stacked cards inside cards, large purple/blue gradients, one-note color themes, tiny icon-only important cues, productivity-app Calendar framing, remote activity-launcher Map framing, and combat UI language for lessons.

UX-DR25: Implement key player journeys: first school morning, afternoon under time pressure, and noticing Nell through Social clue -> profile -> location/travel -> world occasion -> dialogue -> flags/feedback/profile update.

UX-DR26: Treat mockups as visual references only; EXPERIENCE.md and DESIGN.md are the behavioral and visual contracts when conflicts occur.

UX-DR27: Track deferred but important UX surfaces for later mockups: Sleep/End-of-Day Summary, Semester Reflection/Memory Ledger, and Phone School App/Journal.

### Requirement Scope Classification

This classification preserves the complete preproduction traceability while preventing first vertical slice, MVP, full-game, and technical-enabler requirements from entering the backlog at the same priority level.

| Requirement Group | Scope | Planning Note |
| --- | --- | --- |
| FR1-FR2 | MVP / First Vertical Slice Context | Establishes the protagonist, dorm-student premise, and one-semester MVP boundary. The first vertical slice proves a smaller playable loop inside that semester. |
| FR3 | Full Game / Later Traceability | Preserves the three-year journey direction. Do not treat as active first-slice or MVP implementation scope except where architecture must remain future-ready. |
| FR4-FR10 | MVP Core / FVS Subset | Time, calendar, commitments, travel, school-day structure, weekends, and schedule feasibility form the MVP backbone. FVS should implement only the minimal day/commitment path needed for playable proof. |
| FR11-FR13 | MVP Core / FVS Subset | Wellbeing, activity resolution, and consequence feedback are MVP core. FVS should prove a small set of Energy/Stress/Mood and activity consequences. |
| FR14-FR18 | MVP Core / FVS Critical | Lessons and academics are central to the school fantasy. FVS should prove one 3-block lesson and minimal academic feedback before broader homework/test/exam scope. |
| FR19-FR20 | Full Game / Later Traceability | End-of-year promotion failure and recovery checkpoints are full-game direction. MVP can remain architecturally ready without implementing the full fail gate. |
| FR21-FR23 | MVP / Seeded in FVS Unless Needed | Character creation and preferences are MVP scope. FVS may use a seeded protagonist fixture unless character creation is explicitly selected for early implementation. |
| FR24-FR30 | MVP Core / FVS Relationship Proof | Relationship state, tiers, flags, romance direction, and Nell proof arc are MVP core. FVS should prove one relationship beat with typed effects, flags, and readable feedback. |
| FR31-FR32 | MVP / Later FVS Expansion | Clubs are MVP scope, but FVS should only include club context if needed for the chosen relationship/activity proof. |
| FR33-FR38 | MVP Core / FVS Thin Path | Full phone hub is MVP scope. FVS should implement a thin phone clue/profile/map/settings/save-legality path, not every app at full depth. |
| FR39-FR44 | MVP Content / FVS Minimal Path | Locations, off-school life, and events are MVP scope. FVS should include only the locations/events needed for one reachable occasion and proof journey. |
| FR45-FR51 | MVP Narrative / FVS Critical Subset | Dialogue, story gating, classmate exposure, and Memory Ledger are MVP scope. FVS should prove one dialogue session/effects path and at least minimal intentional memory/reflection input where useful. |
| FR52-FR53 | MVP / FVS Technical Gameplay | Save/load and safe-save restrictions are FVS-critical because runtime mode and projection rebuild must be proven early. |
| FR54 | MVP / Later FVS If Used | Money/allowance is MVP scope. FVS only needs it if the chosen activity/event path uses spending. |
| FR55-FR58 | MVP Presentation / FVS Smoke | Exploration, HUD, overlays, presentation, assets/audio, and feedback are MVP scope. FVS should prove one Godot smoke view consuming Application read models. |
| FR59 | MVP Completion / Later MVP | Semester reflection is MVP completion scope, not first playable slice scope, except for minimal Memory Ledger record/projection proof. |
| FR60 | Constraint / Technical Enabler | Content-driven authoring is a cross-cutting constraint. Implement through enabler stories attached to the first content-driven systems. |
| NFR1-NFR7 | MVP NFR / Measurement Needed | Performance and duration thresholds need target profile and measurement method before final story acceptance criteria. |
| NFR8-NFR20 | Product / UX / Platform Constraints | Apply to relevant stories where platform, input, UI, localization, and scope constraints are implemented. |
| NFR21-NFR23 | Design Quality Constraints | Use as acceptance criteria for balance, wellbeing, relationship, and content-scope stories. |
| NFR24-NFR30 | Engineering Quality Gates | Convert into technical-enabler stories and recurring evidence requirements, not standalone player-facing epics. |
| Additional Requirements | Technical Enablers / Constraints | Convert into concrete stories only when they produce demonstrable evidence for FVS or MVP architecture. |
| UX-DR1 | MVP, Split for FVS | Full phone hub is MVP scope; FVS should implement only the minimal phone path needed for the selected proof journey. |
| UX-DR2-UX-DR9 | MVP UX / FVS Subset | Phone chrome, Social, Calendar, Map, HUD, prompts, activity confirmation, and result feedback should be implemented incrementally along the first playable path. |
| UX-DR10-UX-DR12 | FVS / MVP | Lesson flow UX is first-slice critical because lessons are a core proof of school gameplay. |
| UX-DR13-UX-DR21 | Cross-Cutting UX Quality Gates | Controller focus, Back behavior, accessibility, non-color-only feedback, remapping, and glyphs must become acceptance criteria for relevant UI stories. |
| UX-DR22-UX-DR24 | Visual Contract Constraint | Apply to UI component stories and smoke surfaces; avoid duplicating token work across unrelated stories. |
| UX-DR25 | FVS Journey Candidate | Use as a canonical first relationship-discovery journey: phone clue -> profile/map -> travel -> world occasion -> dialogue -> flags/feedback/profile update. |
| UX-DR26-UX-DR27 | Process / Deferred UX | EXPERIENCE.md and DESIGN.md remain contracts. Deferred mockups do not block architecture, but full surfaces should not be overbuilt before content/read-model needs are known. |

### Story Type Taxonomy

Use these story types during implementation planning:

- **Player:** delivers behavior, feedback, or interface the player directly experiences.
- **Technical Enabler:** creates architecture, tooling, contracts, fixtures, or infrastructure needed by player-facing stories.
- **Evidence Gate:** proves existing behavior through tests, ScenarioRunner paths, smoke tests, validation output, or reviewable artifacts.
- **Constraint:** captures scope, boundary, compliance, or quality rules that must be enforced by owning stories rather than built as standalone product breadth.

### First Vertical Slice Execution Thread

This thread is not a replacement for the product epics. It is the recommended ordering for the first implementable stories across multiple epics:

0. Foundation solution/project structure and architecture guardrails.
1. Content fixture.
2. ContentValidator.
3. ScenarioRunner happy path.
4. Time/Commitment policy.
5. ActivityOccasion.
6. LessonSession.
7. DialogueSession/effects.
8. Save/load rebuild.
9. Godot smoke view.

The first vertical slice must prove one narrow playable journey: seeded protagonist -> first school morning -> schedule/context review -> one three-block lesson -> one time-spending activity or travel/world-presence interaction -> one phone clue/profile/map handoff -> one dialogue choice with typed effects and one memory hook -> safe save/load resume -> day-end feedback. Scope remains one subject, one classmate, one destination, one activity, and one memory hook; it must not require full phone apps, broad academics, full clubs/events, or semester reflection.

### FR Coverage Map

FRs may have one primary epic and supporting epics where behavior, discovery, presentation, validation, or persistence cross boundaries.

FR1: Epic 4 primary - Custom dorm student protagonist and emergent school identity.

FR2: Cross-cutting MVP/FVS scope constraint - One-semester MVP boundary and restrained vertical-slice framing across Epics 1-9.

FR3: Epic 9 later traceability - Full three-year journey, graduation, and reflective epilogue direction.

FR4: Epic 1 primary - Core daily loop with schedule/context review, time spending, consequences, and repetition.

FR5: Epic 1 primary - 15-minute time-block scheduling unit.

FR6: Epic 1 primary - School-day anchors, attendance, curfew, wind-down, and sleep rules.

FR7: Epic 1 primary - Schedule-valid snooze choices.

FR8: Epic 1 primary, Epic 2 supporting - Travel/activity duration and return feasibility before actions.

FR9: Epic 1 primary, Epic 7 supporting - Campus movement and off-campus travel cost rules.

FR10: Epic 1 primary - Weekend wake plans and weekend structure.

FR11: Epic 2 primary - Energy, Stress, and qualitative Mood as core wellbeing resources.

FR12: Epic 2 primary - Recovery, sleep, rest, relaxing activity, favorite-aligned activity, and snooze effects.

FR13: Epic 2 primary, Epics 3/5/7 supporting - Activity resolution into academics, relationships, wellbeing, info, event access, obligations, money, flags, and memories.

FR14: Epic 3 primary - Active lessons as three-block 45-minute sessions with feedback and results.

FR15: Epic 3 primary, Epic 8 supporting - Stable lesson action categories and contextual variants.

FR16: Epic 3 primary, Epic 8 supporting - Lesson risk, teacher strictness, qualitative risk labels, and caught/attention outcomes.

FR17: Epic 3 primary - Academic subject knowledge, grades, homework, tests, exam readiness, and teacher impression.

FR18: Epic 3 primary - MVP academic scope for subjects, homework, tests, and Week 20 exam period.

FR19: Epic 3/Epic 9 later traceability - Full-game academic promotion fail gate.

FR20: Epic 9 later traceability - Full-game academic failure checkpoint recovery.

FR21: Epic 4 primary - Character name, gender, attributes, preferences, and sprite appearance.

FR22: Epic 4 primary - Starting attribute point-buy rules.

FR23: Epic 4 primary, Epics 2/5/6 supporting - Favorites/preferences effects without hard-locking content.

FR24: Epic 5 primary - Relationship Bond, visible tier, compatibility, schedules, locations, arc state, and flags.

FR25: Epic 5 primary - Relationship progression through systemic interactions and authored milestones beyond Bond alone.

FR26: Epic 5 primary - Visible relationship tiers.

FR27: Epic 5 primary - Authored romance path/state rules.

FR28: Epic 5 primary - MVP classmate and story beat scope.

FR29: Epic 5 primary, Epic 7 supporting - Nell progression through restraint, boundary respect, authorship consent, flags, and non-club access routes.

FR30: Epic 7 primary, Epic 5 supporting - Clubs as recurring activities, social circles, story opportunities, and calendar pressure without grind.

FR31: Epic 7 primary - Science Club and Literary Club MVP meeting/event structure.

FR32: Epic 7 primary, Epic 5 supporting - Club membership as variants/context, not hard gates.

FR33: Epic 6 primary - Smartphone hub with Calendar, Messages, Social, Map, School App/Journal, Settings, notifications, and safe save/load access.

FR34: Epic 6 primary, Epics 2/7 supporting - Phone cannot execute ordinary activities remotely; activities require world presence.

FR35: Epic 6 primary, Epic 1 supporting - Phone Calendar known commitments/events without formal slot-planning.

FR36: Epic 6 primary, Epics 1/7 supporting - Phone Map destinations, travel time/feasibility, and usable time without NPC radar.

FR37: Epic 6 primary, Epic 5 supporting - Phone Social feed/profile info, discovered facts, preferences, public clues, relationship status, and current-location clues.

FR38: Epic 6 primary, Epic 3 supporting - School App/Journal academic dashboard.

FR39: Epic 7 primary, Epic 8 supporting - MVP locations/spaces and event spaces.

FR40: Epic 2 primary, Epic 7 supporting - Off-school activity roles for cafe, park, dorm/private room, and convenience store.

FR41: Epic 7 primary - MVP fixed and special events.

FR42: Epic 7 primary, Epics 1/2/5 supporting - Harvest Evening partial attendance, fixed ending, limited time/money/social bandwidth, effects, clues, encounters, and memory flags.

FR43: Epic 3 primary, Epic 7 supporting - Science Showcase Sprint activity-choice resolution.

FR44: Epic 7 primary, Epic 5 supporting - Zine Deadline Crisis choices, member/non-member routes, authorship, consent, and reputation.

FR45: Epic 8 primary, Epic 5 supporting - Large portrait and small bubble dialogue presentation modes.

FR46: Epic 5 primary, Epic 8 supporting - Concrete dialogue choices, limited branching, convergence, and consequences.

FR47: Epic 5 primary, Epic 8 supporting - Authored beats, micro-dialogue, phone/message dialogue, reflection text, and compact variants.

FR48: Epic 5 primary, Epics 3/7/9 supporting - MVP narrative beat list and timing.

FR49: Epic 5 primary, Epics 1/2/7 supporting - Story gating through relationship, flags, time, location, availability, wellbeing, attributes, clubs, and choices.

FR50: Epic 5 primary, Epic 6 supporting - Early exposure to all 5 MVP classmates and handling missed/delayed intros.

FR51: Epic 9 primary, Epic 5 supporting - Memory Ledger/Semester Reflection summaries without epilogue or quest-log behavior.

FR52: Epic 9 primary, Epic 6 supporting - Autosave, manual save/load, safe contexts, and recovery support.

FR53: Epic 9 primary, Epic 6 supporting - Manual save blocked during unsafe runtime modes.

FR54: Epic 2 primary, Epic 7 supporting - Light allowance/money system.

FR55: Epic 8 primary - Top-down exploration, prompts, HUD, travel, overlays, phone, lessons, dialogue, and summary flows.

FR56: Epic 8 primary - MVP asset scope.

FR57: Epic 8 primary - Audio/music/SFX scope without full voiced dialogue.

FR58: Epic 2 primary, Epic 8 supporting - Player-facing feedback for blocked choices, costs, risks, updates, effects, notifications, missed opportunities, and memories.

FR59: Epic 9 primary, Epic 4 supporting - Personalized MVP semester reflection based on accumulated play.

FR60: Cross-cutting technical enabler - Content-driven authoring support across content-authored epics.

## Epic List

### Foundation 0: Implementation Foundation and Architecture Guardrails

The implementation repo has the Godot host, clean C# solution boundaries, test harnesses, content tooling entry points, and architecture guardrails needed before feature stories consume the first vertical-slice fixture.

**FRs covered:** FR60

**Relevant NFRs / UX-DRs:** NFR24-NFR30

**Implementation notes:** This is a technical foundation, not a player-facing epic. It exists to make the first feature story executable without hiding setup work inside content acceptance criteria.

### Epic 1: Time, Calendar, and Daily Commitments

Player can live through structured school days and weekends where time, schedule, travel, school attendance, curfew, sleep, and calendar pressure create meaningful tradeoffs.

**FRs covered:** FR4, FR5, FR6, FR7, FR8, FR9, FR10

**Implementation notes:** First vertical slice should prove the smallest playable day/commitment path before implementing full semester breadth.

### Epic 2: Wellbeing, Activities, and Consequence Resolution

Player can choose activities that spend time and affect Energy, Stress, Mood, money, academics, relationships, future obligations, and readable feedback.

**FRs covered:** FR11, FR12, FR13, FR40, FR54, FR58

**Implementation notes:** Epic 1 owns time/feasibility legality; this epic owns activity consequences, wellbeing/resource effects, and player-facing outcome feedback.

### Epic 3: Lessons, Homework, Tests, and Academics

Player can participate in active lessons, make per-block classroom choices, handle academic pressure, and see school performance consequences.

**FRs covered:** FR14, FR15, FR16, FR17, FR18, FR43

**Later traceability:** FR19, FR20

**Implementation notes:** First vertical slice should prove one 3-block lesson with risk, feedback, and minimal academic delta. Homework, tests, exams, and full promotion failure are later scope within this epic/traceability.

### Epic 4: Character Creation, Preferences, and Player Identity

Player can create a personalized student with attributes, preferences, appearance, and an emergent school identity shaped by play.

**FRs covered:** FR1, FR21, FR22, FR23, FR59

**Implementation notes:** First vertical slice may use a seeded protagonist fixture, but preference data should remain available early enough to test Mood, compatibility, Social/Profile, and activity flavor when needed.

### Epic 5: Relationships, Classmates, Dialogue, and Story Beats

Player can meet classmates, build relationships through interaction and authored milestones, make dialogue choices, unlock story flags, and experience Nell's MVP proof arc.

**FRs covered:** FR24, FR25, FR26, FR27, FR28, FR29, FR45, FR46, FR47, FR48, FR49, FR50, FR51

**Implementation notes:** This epic owns dialogue behavior, choices, conditions, effects, flags, hidden relationship math, gates, and story consequences. Epic 8 owns dialogue presentation modes, readability, focus, portrait/bubble rendering, and visual/audio framing.

### Epic 6: Smartphone, Planning, and Discovery

Player can use the phone as a diegetic hub for known information, social clues, messages, calendar, map, school info, settings, and safe save/load access without remote-executing ordinary activities.

**FRs covered:** FR33, FR34, FR35, FR36, FR37, FR38, FR52, FR53

**Implementation notes:** This epic owns phone IA, chrome, navigation, Back stack, app focus, clue routing, and the non-remote-activity rule. Other epics provide the underlying read models/content.

### Epic 7: Locations, Clubs, Events, and Off-School Life

Player can move through school, dorm, campus, town spaces, clubs, and MVP events that create social, academic, recovery, and memory opportunities.

**FRs covered:** FR30, FR31, FR32, FR39, FR40, FR41, FR42, FR43, FR44

**Implementation notes:** First vertical slice should include only the reachable locations/occasions needed for the selected proof journey. Clubs and events should remain thin until the core world-presence rule is proven.

### Epic 8: Player-Facing Presentation, Operability, Art, and Audio

Player can read, navigate, understand feedback, make choices, and experience the core loop through usable HUD, phone, lesson, dialogue, exploration, art, and audio surfaces.

**FRs covered:** FR45, FR55, FR56, FR57, FR58

**Implementation notes:** This is not a late polish bucket. Every UI story in other epics must carry relevant focus, Back behavior, glyph, readability, non-color-only, and visual-contract acceptance criteria. This epic owns shared presentation systems and MVP surface operability.

### Epic 9: Save, Progression, Memory, and MVP Semester Completion

Player can safely save/load, preserve consequences, complete the MVP semester, and receive a personalized reflection of choices, missed chances, relationships, academics, wellbeing, and identity.

**FRs covered:** FR51, FR52, FR53, FR59

**Later traceability:** FR3, FR19, FR20

**Implementation notes:** Save/load rebuild is first-slice critical. Minimal MemoryRecord proof should appear earlier than full Semester Reflection; full MVP completion/reflection is later within this epic.

## Epic Sections

### Foundation 0: Implementation Foundation and Architecture Guardrails

The implementation repo has the Godot host, clean C# solution boundaries, test harnesses, content tooling entry points, and architecture guardrails needed before feature stories consume the first vertical-slice fixture.

**FRs covered:** FR60

**Relevant NFRs / UX-DRs:** NFR24-NFR30

**Implementation notes:** Complete this foundation before Story 1.1. It should be treated as a technical enabler and may be implemented as the first sprint item or waived only by explicit implementation lead decision.

### Story 0.1: Create Godot Host and Clean C# Solution Boundaries

Story Type: Technical Enabler

As a developer,
I want the Godot host, C# solution layout, tooling entry points, and boundary tests created,
So that feature stories can consume validated content and Application commands without introducing Godot or raw-content dependencies into gameplay rules.

**Acceptance Criteria:**

**Given** the implementation workspace is initialized
**When** the solution is inspected
**Then** Domain, Application, Ports, Content, Godot host, tools, and test projects exist with references matching the architecture document
**And** the Godot host compiles without pulling gameplay rules into scene scripts.

**Given** architecture boundaries are tested
**When** ArchitectureTests run
**Then** Domain, Application, Content, and the Godot host enforce no forbidden Godot, R3, raw JSON, infrastructure, or presentation dependencies according to the architecture document
**And** violations fail with readable project/type/member diagnostics.

**Given** content tooling is invoked
**When** ContentValidator and ScenarioRunner entry points are built or executed with help/version commands
**Then** each tool starts through a documented command-line path
**And** missing content fixtures return typed, readable errors instead of unhandled exceptions.

**Given** the first feature stories begin
**When** Story 1.1, Story 1.2, and later FVS stories need content catalogs, Application commands, ports, or tests
**Then** they use this foundation rather than creating parallel project structure, ad hoc loaders, or Godot-only simulation paths.

Coverage: FR60, NFR24, NFR25, NFR26, NFR27, NFR28, NFR29, NFR30
Tags: FVS, Technical Enabler, Constraint

### Epic 1: Time, Calendar, and Daily Commitments

Player can live through structured school days and weekends where time, schedule, travel, school attendance, curfew, sleep, and calendar pressure create meaningful tradeoffs.

**FRs covered:** FR4, FR5, FR6, FR7, FR8, FR9, FR10

**Relevant NFRs / UX-DRs:** NFR5-NFR7, NFR9-NFR13, NFR17, NFR24-NFR30, UX-DR4-UX-DR6, UX-DR8, UX-DR13-UX-DR16, UX-DR21, UX-DR25

**Implementation notes:** First vertical slice should prove the smallest playable day/commitment path before implementing full semester breadth.

**Epic 1 Test Evidence:** Rejected commands must return typed Result errors and leave GameState unchanged. Preview and execution paths must share the same feasibility reason-code model. Time-dependent tests must use a controlled clock, not Godot frame time. ContentValidator coverage must include schedule alignment, travel costs, hard boundaries, weekend plans, and authored attendance exceptions. ScenarioRunner coverage must include the school-day happy path plus representative blocked, stale-preview, and boundary paths. Read-model snapshots must verify player-facing reason consistency.

### Story 1.1: Validated First School-Day Schedule Fixture

Story Type: Technical Enabler

As a developer,
I want a minimal content-driven first school-day schedule fixture,
So that the first vertical slice can prove time, commitments, and school-day anchors from validated content instead of hardcoded scene logic.

**Acceptance Criteria:**

**Given** the vertical-slice fixture catalog is loaded
**When** the first playable school day is validated
**Then** the catalog includes a deterministic school-day schedule with a 06:00 wake boundary, an explicit before-school free-time window, lesson anchors, break/lunch windows, after-school free time, dorm return boundary, wind-down period, and latest sleep rule
**And** the wake boundary is not a `Preparation` schedule entry or a reserved time block
**And** all schedule entries use 15-minute-aligned start times and durations.

**Given** the fixture includes mandatory school attendance
**When** ContentValidator checks the day schedule
**Then** it rejects missing lesson anchors, overlapping hard commitments, invalid 15-minute alignment, required commitments unreachable from the wake boundary using authored travel inputs, and latest-sleep conflicts
**And** it reports typed validation errors with content IDs and readable diagnostics.

**Given** the fixture is consumed by Application tests or ScenarioRunner
**When** time and commitment data is requested
**Then** gameplay systems receive schedule data through the validated ContentCatalog
**And** no runtime command handler reads raw JSON files or hardcodes the first-day schedule.

**Given** the fixture is scoped to the first vertical slice
**When** broader semester content is absent
**Then** validation still passes for the minimal one-day path
**And** the fixture remains extendable to the 20-week MVP semester without changing the schedule model.

Coverage: FR4, FR5, FR6, FR60, NFR24, NFR29, NFR30
Tags: FVS, Technical Enabler, Constraint

### Story 1.2: Deterministic Daily Loop Scenario Runner Path

Story Type: Evidence Gate

As a developer,
I want a deterministic ScenarioRunner path for the first playable school day,
So that the core daily loop can be proven through the same Application commands that Godot will later use.

**Acceptance Criteria:**

**Given** the vertical-slice fixture catalog and an initial seeded GameState are available
**When** ScenarioRunner executes the first school-day happy path
**Then** it advances through schedule/context review, one mandatory lesson or school commitment plus surrounding day anchors, one valid time-spending choice, consequence absorption, and day-end transition
**And** every action is executed through Application command handlers rather than a parallel simulation path.
**And** the path includes at least one active lesson choice, one wellbeing/time tradeoff, one social discovery or dialogue touchpoint, one immediate visible consequence, and one memory or future-hook candidate so design review can describe what the student chose, gave up, and learned without reading diagnostic traces.

**Given** the scenario reaches a decision point with known commitments ahead
**When** ScenarioRunner requests available choices or previews
**Then** the returned read models include current time, day context, next known commitment, available time window, and any feasibility warning needed for the next action
**And** read-model snapshots include player-facing warning/block labels, next-boundary text, and severity state under controlled clock, seeded RNG/config, fixture catalog, and in-memory stores.

**Given** a scenario command receives a rejection from an Application command handler
**When** ScenarioRunner attempts the command
**Then** ScenarioRunner can assert the typed Result error without treating it as a test harness failure
**And** the GameState remains unchanged after the rejected command.

**Given** the first-day scenario completes
**When** the scenario report is generated
**Then** it records the commands executed, time transitions, commitments honored, blocked-choice checks, and final day state
**And** the report can be used as a regression artifact for future Godot smoke tests.

Coverage: FR4, FR5, FR6, FR8, FR60, NFR5, NFR24, NFR25, NFR27, NFR28, NFR30
Tags: FVS, Technical Enabler

### Story 1.3: Fifteen-Minute Time and Commitment Policy

As a player,
I want time to advance in clear 15-minute blocks and respect known commitments,
So that every daily choice feels predictable, fair, and meaningfully constrained.

**Acceptance Criteria:**

**Given** time or commitment legality is evaluated
**When** a preview or command needs a feasibility decision
**Then** Story 1.3 provides the canonical time/commitment policy and command legality rules
**And** later preview and presentation stories must consume this policy rather than duplicating time math.

**Given** the player is in an active day with a known current time
**When** a gameplay command previews or spends time
**Then** the system normalizes the time cost to 15-minute blocks
**And** rejects unsupported durations that are not aligned to the scheduling unit.

**Given** the player has a known hard commitment later in the day
**When** the player previews an activity, travel action, rest action, or other time-spending command
**Then** the feasibility result accounts for action duration, travel time where applicable, required return time, and the next hard commitment
**And** the preview identifies whether the action is available, warning-level risky, or blocked.

**Given** a command would overrun a mandatory lesson, curfew, dorm return boundary, wind-down boundary, or latest sleep rule
**When** the player confirms the command
**Then** the Application rejects it with a typed Result error
**And** the player-facing read model includes a clear blocked reason without mutating GameState.

**Given** a command is feasible
**When** the command resolves
**Then** GameState advances by the exact normalized time cost
**And** the resulting read models expose updated current time, day context, next commitment, and remaining usable time before the next hard boundary.

**Given** the same feasibility rule is used by preview and execution
**When** content, location, current time, or commitments change between preview and confirmation
**Then** execution revalidates the command against current state
**And** stale preview data cannot authorize an illegal action.

Coverage: FR4, FR5, FR6, FR8, FR9, FR10, FR58, FR60, NFR13, NFR24, NFR27, NFR28
Tags: FVS, MVP

### Story 1.4: School-Day Anchors and Mandatory Attendance

As a player,
I want school days to have dependable mandatory anchors,
So that the semester feels like structured high-school life rather than an open-ended activity sandbox.

**Acceptance Criteria:**

**Given** the canonical first school day begins
**When** the active day is initialized
**Then** the schedule includes default wake time, before-school free time, mandatory school attendance, lesson commitments, break/lunch windows where scheduled, after-school free time, dorm return boundary, wind-down boundary, and latest sleep rule
**And** these anchors are derived from validated content and current GameState.

**Given** mandatory school-day anchors are active
**When** availability blocks are generated for the time/commitment policy
**Then** they are consumed by the policy from Story 1.3
**And** this story does not reimplement time math or feasibility calculations.

**Given** the player is approaching a mandatory lesson or school commitment
**When** the player previews or confirms a free-time, travel, rest, or activity command that would cause truancy, voluntary lateness, or missing the commitment
**Then** the Application rejects the command with the same typed Result style used by the time/commitment policy
**And** the blocked reason explains the relevant school anchor or hard commitment.
**And** GameState remains unchanged after the rejected command.

**Given** the player is within a lesson, break/lunch window marked as mandatory, or another mandatory school block
**When** normal free-time activity commands are requested
**Then** those commands are unavailable unless the current runtime mode explicitly provides allowed school-context actions
**And** a normal player action cannot create a conflict with a mandatory commitment.

**Given** the school day reaches after-school free time
**When** the player reviews available actions
**Then** the system permits valid free-time activities while still enforcing travel, curfew, dorm return, wind-down, and latest sleep boundaries
**And** the next hard boundary remains visible to read models that need it.

**Given** an authored exception is present in validated content
**When** it explicitly permits unusual attendance behavior
**Then** the exception is applied only through a validated content flag or exception rule scoped by commitment, day, and content ID
**And** the exception appears in validation reports and cannot globally disable mandatory attendance rules
**And** ordinary free-time, travel, rest, or activity commands cannot bypass mandatory attendance by themselves.

Coverage: FR4, FR6, FR8, FR49, FR60, NFR21, NFR24, NFR27, NFR28
Tags: FVS, MVP, Constraint

### Story 1.5: Shared Feasibility Preview for Activities and Travel

As a player,
I want to preview whether an activity or trip fits before I commit,
So that I can make informed time-management choices without accidentally missing hard commitments.

**Acceptance Criteria:**

**Given** the player inspects a seeded world interaction, activity occasion, travel option, rest option, or other time-spending choice
**When** the feasibility preview is requested
**Then** the preview shows the known time cost, travel time where applicable, required return time where applicable, usable time before the next hard boundary, and whether the choice is Available, Warning, or Blocked
**And** the preview is derived from the same CommitmentFeasibilityPolicy used at command execution.

**Given** activity and travel previews are requested from different surfaces
**When** their feasibility results are returned
**Then** both previews share the same result shape and typed reason-code model as a reusable preview/read-model contract over CommitmentFeasibilityPolicy
**And** this story does not add new time math, implement the full ActivityOccasion system, or implement the full Phone Map surface.

**Given** a previewed choice is blocked by a lesson, curfew, dorm return, wind-down, latest sleep rule, unavailable location, or missing interaction context
**When** the preview read model is returned
**Then** the blocked state includes a player-facing reason suitable for UI display
**And** the reason does not expose hidden schedules, hidden NPC locations, or raw implementation details.

**Given** a previewed choice is warning-level risky but still legal
**When** the preview read model is returned
**Then** the warning explains the known risk, such as tight return time or reduced usable time before a commitment
**And** the warning is reinforced through text/icons or other non-color-only cues.

**Given** a preview is requested
**When** the preview is generated
**Then** it never mutates GameState
**And** it can be recomputed deterministically from current state, validated content, location, commitments, and controlled clock.

**Given** the player confirms a previously previewed choice through StartActivityCommand or TravelToLocationCommand
**When** current time, location, content availability, hard commitments, or interaction context changed after the preview
**Then** the command is revalidated against current state
**And** stale previews cannot execute an illegal activity or travel action.

**Given** the preview is shown through an Activity Choice + Time Cost Confirmation read model
**When** the player uses controller or keyboard input
**Then** Confirm, Back/Cancel, focus order, and readable warning/block states are exposed in the read model
**And** mouse-only affordances are not required for the core decision.

Coverage: FR4, FR5, FR8, FR9, FR36, FR58, FR60, NFR9, NFR10, NFR11, NFR13, NFR17, NFR24, NFR27, NFR28, UX-DR5, UX-DR8, UX-DR13, UX-DR14, UX-DR15, UX-DR21, UX-DR25
Tags: FVS, MVP, UX

### Story 1.6: Wake, Snooze, Wind-Down, and Sleep Boundaries

As a player,
I want mornings and nights to have clear recovery and boundary rules,
So that sleep choices affect agency without letting me break the school-day structure.

**Acceptance Criteria:**

**Given** a school day begins from a normal prior-night state
**When** the day initializes
**Then** the player wakes at the default school-day wake time unless a valid snooze choice or authored exception applies
**And** the resulting current time remains compatible with mandatory school attendance.

**Given** the player is offered a snooze choice
**When** the player previews or selects it
**Then** the choice shows the recovery benefit and the agency/time tradeoff
**And** the choice is blocked if it would cause truancy, voluntary lateness, or an invalid conflict with a mandatory school anchor.
**And** invalid choices return a typed Result error and leave GameState unchanged.

**Given** the day reaches the dorm return boundary or wind-down period
**When** the player previews travel, activity, rest, or other time-spending choices
**Then** the system prevents choices that would violate dorm return, wind-down, or latest sleep rules
**And** the blocked reason is exposed through the same typed Result and player-facing reason model used by earlier feasibility stories.
**And** rejected choices leave GameState unchanged.

**Given** the player reaches a valid sleep opportunity
**When** the player ends the day or sleeps
**Then** the day transition records the final sleep time, advances to the next day, and provides the sleep/recovery inputs needed by wellbeing systems
**And** the transition remains deterministic for tests and ScenarioRunner.

**Given** a wake, snooze, sleep, or end-day decision is presented
**When** the player uses controller or keyboard input
**Then** the read model exposes Confirm, Back/Cancel, current focus, adaptive glyph, recovery benefit, time cost, and blocked reason when invalid
**And** mouse-only affordances are not required for the core decision.

**Given** weekend wake plans are not active
**When** school-day wake/snooze/sleep logic is evaluated
**Then** the rules apply only the canonical school-day path
**And** weekend-specific behavior remains deferred to the weekend structure story.

Coverage: FR6, FR7, FR10, FR58, FR60, NFR5, NFR21, NFR22, NFR24, NFR27, NFR28
Tags: FVS Subset, MVP

### Story 1.7: Campus and Off-Campus Travel Rules

As a player,
I want movement between school-life spaces to have clear time costs,
So that campus feels convenient while off-campus choices create meaningful schedule tradeoffs.

**Acceptance Criteria:**

**Given** the player moves between dorm, school, and the shared campus entrance
**When** the movement is requested from a valid interaction or navigation context
**Then** the movement has no travel time cost
**And** it still updates the logical location through Application state rather than Godot scene state.

**Given** the player previews travel to an off-campus location
**When** the destination is known and currently reachable
**Then** the preview includes travel time, usable time before the next hard commitment, and whether returning in time is available, warning-level risky, or blocked
**And** the result uses the shared feasibility result shape from Story 1.5.
**And** the preview inherits Story 1.5 and Story 1.9 requirements for focus, Back/Cancel behavior, non-color-only warning/block feedback, and hidden-information protection.

**Given** the player confirms off-campus travel
**When** current time, location, destination availability, or hard commitments changed after preview
**Then** the travel command revalidates against current state
**And** illegal travel is rejected with a typed Result error without mutating GameState.

**Given** a destination is unknown, unavailable, closed, or not yet discovered
**When** the player requests travel or preview
**Then** the result is blocked with a player-facing reason that does not expose hidden schedules or undiscovered content
**And** the Map/phone may show only known destinations and known travel facts.

**Given** travel succeeds
**When** the destination is reached
**Then** current time advances by the exact travel cost, logical location updates, relevant read models refresh, and the next hard boundary remains available to UI
**And** Godot scene transitions may present the move but do not decide travel legality.

Coverage: FR4, FR5, FR8, FR9, FR36, FR39, FR40, FR58, FR60, NFR17, NFR24, NFR27, NFR28, UX-DR5, UX-DR6, UX-DR7, UX-DR13, UX-DR14, UX-DR21
Tags: MVP

### Story 1.8: Weekend Day Structure

As a player,
I want weekends to feel looser than school days while still having boundaries,
So that I can plan recovery, relationships, study, clubs, events, and off-school life without losing the game's time pressure.

**Acceptance Criteria:**

**Given** a weekend day begins
**When** the active day is initialized
**Then** the schedule uses a weekend wake plan instead of the default school-day wake anchor
**And** it does not create mandatory lesson attendance unless a validated special event or authored commitment exists.

**Given** the player reviews weekend availability
**When** free-time actions, travel, recovery, study, club, event, or off-school choices are previewed
**Then** the same 15-minute time, feasibility, travel, dorm return, wind-down, and latest sleep policies apply
**And** the looser weekend structure does not bypass hard boundaries.
**And** rejected weekend commands return typed Result errors and leave GameState unchanged.

**Given** a weekend has a validated special event, club meeting, deadline, exam-prep commitment, or authored relationship opportunity
**When** the day schedule is initialized
**Then** the commitment appears as a known or discoverable schedule item according to content rules
**And** the player can make choices around it without the system treating empty time as a formal slot-planning grid.

**Given** a weekend action would make returning to dorm or sleeping on time impossible
**When** the player previews or confirms the action
**Then** the action is warning-level risky or blocked according to the shared feasibility policy
**And** the reason is player-facing and does not rely on color alone.
**And** weekend preview surfaces inherit Story 1.5 and Story 1.9 requirements for focus, Back/Cancel behavior, non-color-only warning/block feedback, and hidden-information protection.

**Given** weekend behavior is validated
**When** tests or ScenarioRunner exercise a minimal weekend path
**Then** the path proves wake plan, free-time preview, at least one valid time-spending action, boundary enforcement, and day-end transition
**And** it remains separate from the canonical first school-day FVS path.

Coverage: FR4, FR5, FR8, FR10, FR30, FR31, FR35, FR41, FR42, FR58, FR60, NFR6, NFR17, NFR21, NFR24, NFR27, NFR28, UX-DR4, UX-DR8, UX-DR13, UX-DR14, UX-DR21
Tags: Later MVP

### Story 1.9: Player-Facing Time Pressure Feedback

As a player,
I want time pressure, blocked choices, and upcoming commitments to be clearly communicated,
So that I understand why choices are available, risky, or blocked without needing to inspect hidden systems.

**Acceptance Criteria:**

**Given** time pressure or feasibility output needs player-facing communication
**When** a surface requests display-ready state
**Then** Story 1.9 owns consistent wording, severity, reason keys, and presentation semantics across surfaces
**And** it does not implement detailed visual layouts owned by Epic 8.

**Given** the player is in exploration, phone, activity confirmation, or travel preview
**When** time context is displayed
**Then** the read model exposes current day/time, next known commitment or boundary, usable time before that boundary, and relevant pressure state
**And** it does not expose hidden schedules, hidden NPC locations, or undiscovered event truth.

**Given** an action is blocked by time, travel, curfew, dorm return, wind-down, latest sleep, or mandatory attendance
**When** the blocked state is presented
**Then** the player-facing message explains the actionable reason in plain language
**And** the message maps back to a typed reason code and player-facing key for tests and localization readiness.

**Given** an action is legal but warning-level risky
**When** the warning is presented
**Then** the feedback communicates the specific risk, such as tight return time, little usable time, or approaching commitment
**And** the warning is reinforced without relying on color alone.

**Given** warning, blocked, and next-commitment text is displayed
**When** it is rendered in Steam Deck-conscious 720p/1280x800 layouts
**Then** the actionable reason and next-boundary text remain readable without truncating the core decision information
**And** longer localized text has layout buffer or fallback behavior rather than overflowing the decision surface.

**Given** the player navigates the feedback with controller or keyboard
**When** focus moves through HUD, confirmation overlay, phone Calendar/Map references, or related details
**Then** focus order follows the reading and decision path
**And** Back/Cancel returns one layer up without accidentally confirming or dismissing a major flow.

**Given** time pressure feedback appears in different surfaces
**When** the same underlying reason is shown in HUD, confirmation overlay, Calendar, Map, toast, or notification review
**Then** the wording and severity remain consistent enough for the player to recognize the cause
**And** surface-specific presentation follows the visual and accessibility contracts without becoming a productivity planner or remote activity launcher.

Coverage: FR4, FR6, FR8, FR35, FR36, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR17, NFR18, NFR24, NFR28, UX-DR4, UX-DR5, UX-DR6, UX-DR8, UX-DR13, UX-DR14, UX-DR15, UX-DR16, UX-DR20, UX-DR21, UX-DR24
Tags: MVP, UX

### Epic 2: Wellbeing, Activities, and Consequence Resolution

**Goal:** Player can choose activities that spend time and affect Energy, Stress, Mood, money, academics, relationships, future obligations, and readable feedback.

**Epic 2 Test Evidence:** Epic 2 implementation must produce fast unit/Application tests for effect application, wellbeing bounds, activity availability, money, and hook routing; ContentValidator reports for activity schema, effect targets, fallback availability, preview/result keys, and design-scope warnings; ScenarioRunner coverage for a narrow FVS path from minimal catalog -> legal activity command -> wellbeing/money/effect application -> result feedback -> one low Energy or high Stress fallback; and read-model snapshots proving traceability from source content ID to effect/reason code to player-facing feedback key. ScenarioRunner should remain narrow and representative; combinatorial effect cases belong in unit/Application tests.

### Story 2.1: Minimal Activity Catalog and Consequence Fixture

As a developer,
I want a minimal validated activity catalog with authored consequence effects,
So that the first playable loop can resolve player activities from content instead of hardcoded test paths.

**Acceptance Criteria:**

**Given** the vertical-slice content fixture is loaded
**When** ContentValidator validates activities
**Then** the catalog includes a minimal set of activity definitions needed for the first playable school-day loop
**And** each activity has a stable content ID, display key, location or interaction context, duration reference, availability tags, consequence effect list, and feedback key.

**Given** an activity is authored in content
**When** its duration, location, or hard time feasibility must be evaluated
**Then** the activity references the time and feasibility policies from Epic 1
**And** this story does not duplicate time math, travel rules, curfew rules, dorm return rules, wind-down rules, or mandatory attendance rules.

**Given** an activity defines consequence effects
**When** ContentValidator checks the activity catalog
**Then** every effect targets a known supported effect type, such as Energy, Stress, Mood, academics, relationship signal, money, flag, memory hook, discovered information, future obligation, or notification hook
**And** invalid effect targets, missing reason keys, unsupported values, or references to unknown content IDs fail validation with actionable report messages.

**Given** the FVS fixture needs activity coverage
**When** the minimal activity set is inspected
**Then** it includes at least one recovery/rest activity, one study or academic-support activity, one social or relationship-support activity, and one low-impact fallback activity
**And** each activity includes at least one real tradeoff, such as time, Energy, Stress, Mood, money, academic opportunity, social opportunity, or future obligation pressure
**And** each activity is small enough to prove consequence resolution without implying a full town-sim, shop, job, or relationship-grind system.

**Given** an activity has player-facing feedback
**When** the activity is validated
**Then** feedback keys exist for the preview/result surfaces that will later communicate wellbeing, academic, relationship, money, flag, memory, obligation, or notification changes
**And** the keys are localization-ready and do not embed raw implementation names.

**Given** activity content includes Mood effects
**When** the validator checks the effect payload
**Then** Mood is represented as a qualitative state or transition cue rather than a simple numeric meter
**And** favorite-aligned or preference-flavored Mood effects can be expressed without hard-locking major content.

**Given** fixture activities are used by ScenarioRunner or Application tests
**When** test setup loads the catalog
**Then** the activity definitions are deterministic from content, seeded configuration, and in-memory stores
**And** tests can select activities by stable content ID without relying on Godot scene nodes or presentation labels.

**Given** the minimal catalog is intentionally scoped
**When** future MVP activities, location-specific variants, off-school purchases, odd jobs, events, or classmate-specific hangouts are added
**Then** they can extend the same content shape without changing the FVS activity fixture contract
**And** broad activity families remain deferred to later Epic 2 stories or supporting epics.

**Given** Epic 2 FVS ordering is planned
**When** the first implementation slice is selected
**Then** the preferred order is minimal activity catalog, activity command path, minimal wellbeing/effect contract, activity result feedback, and one low Energy or high Stress fallback
**And** cafe/park/store role breadth, light money variants, and future obligations should follow only after that path is proven.

Coverage: FR11, FR12, FR13, FR23, FR40, FR54, FR58, FR60, NFR18, NFR21, NFR23, NFR24, NFR27, NFR28, NFR29, NFR30
Tags: FVS, Technical Enabler, Constraint

### Story 2.2: Activity Resolution Command and Typed Effects

As a player,
I want confirmed activities to resolve into clear immediate consequences,
So that my daily choices visibly shape wellbeing, academics, relationships, resources, and future opportunities.

**Acceptance Criteria:**

**Given** the player confirms a valid activity from the activity catalog
**When** `StartActivityCommand` or the equivalent Application command resolves the activity
**Then** the command applies the authored consequence effects from validated content
**And** the resolution path uses Application command handlers rather than Godot scene logic or a parallel simulation path.

**Given** activity execution begins
**When** the command evaluates time, location, attendance, curfew, dorm return, wind-down, or other hard feasibility rules
**Then** it consumes the Epic 1 feasibility policy and preview contract
**And** this story only owns activity consequence application after execution is legal.

**Given** an activity has wellbeing effects
**When** the activity resolves
**Then** Energy, Stress, and Mood effects are applied through the minimal wellbeing/effect contract needed by the command path
**And** the richer canonical wellbeing rules from Story 2.3 can refine bounds, Mood transitions, and availability behavior without changing the Application command boundary
**And** invalid wellbeing transitions are rejected with typed Result errors rather than exceptions for expected gameplay failures.

**Given** an activity has non-wellbeing effects
**When** the activity resolves
**Then** supported effects can update academics, relationship signals, money, discovered information, event access, future obligations, flags, memories, or notifications
**And** each applied effect records enough source context for later player-facing feedback and causality tracing
**And** activity resolution routes domain-specific meaning to the owning bounded area or epic rather than becoming a generic effect engine that mutates every system without ownership.

**Given** an activity command is rejected
**When** the rejection is caused by blocked feasibility, missing content, invalid interaction context, insufficient resources, unavailable location, or unsupported effect payload
**Then** the command returns a typed Result error with a stable reason code
**And** GameState remains unchanged.

**Given** the activity was previewed before confirmation
**When** current time, location, resources, commitments, content availability, or interaction context changed before execution
**Then** execution revalidates the command against current GameState
**And** stale preview data cannot apply consequences.

**Given** activity consequences are applied successfully
**When** the command completes
**Then** GameState advances by the authorized activity time cost from the Epic 1 policy
**And** all resulting state changes are committed atomically as one activity resolution.

**Given** an activity resolution contains multiple effects
**When** one effect cannot be applied as authored
**Then** the command fails without partially applying earlier effects
**And** conflicting effect order, out-of-bounds values, and multiple Mood transition cues are resolved through deterministic policy or rejected
**And** the failure is reported as a validation or typed Result problem depending on whether the issue is content-time or runtime-state dependent.

**Given** ScenarioRunner executes the FVS daily loop
**When** it resolves at least one activity from the minimal activity catalog
**Then** the report records the command, activity content ID, applied effects, resulting state deltas, typed errors if any, and final post-activity state
**And** the same command path can later be driven by Godot interaction prompts and activity confirmation UI.

Coverage: FR4, FR11, FR12, FR13, FR40, FR54, FR58, FR60, NFR21, NFR22, NFR24, NFR27, NFR28, NFR29, NFR30
Tags: FVS, MVP, Technical Enabler

### Story 2.3: Energy, Stress, and Qualitative Mood Model

As a player,
I want my wellbeing state to reflect fatigue, pressure, and emotional tone,
So that activity choices feel personally meaningful without turning the game into pure meter optimization.

**Acceptance Criteria:**

**Given** the player has an active GameState
**When** wellbeing state is initialized or loaded
**Then** the system tracks Energy, Stress, and qualitative Mood as core wellbeing resources
**And** the canonical wellbeing state is owned by gameplay/application state rather than Godot UI widgets.

**Given** Energy is evaluated
**When** an activity, lesson action, recovery choice, or other gameplay command requires enough stamina
**Then** Energy can act as the primary hard-gating resource for high-effort actions
**And** low-impact fallback choices remain available unless a specific validated rule blocks them.

**Given** Stress is evaluated
**When** the player accumulates pressure through activities, academics, social friction, poor recovery, or time pressure
**Then** Stress creates visible friction before it becomes punitive
**And** its effects can influence availability, risk, feedback tone, or recovery needs without becoming an instant fail state.

**Given** Mood is evaluated
**When** activities, favorites, preferences, dialogue outcomes, recovery, or story events affect emotional state
**Then** Mood is represented as a qualitative state, tag, or transition cue rather than a simple third numeric meter
**And** Mood can flavor feedback, compatibility, activity outcomes, or dialogue/context variants without hard-locking major content.
**And** Mood should hard-block major choices only through explicit authored extreme states, validated story/event rules, or another approved constraint.

**Given** a wellbeing effect is applied from an activity or other validated content
**When** the effect changes Energy, Stress, or Mood
**Then** the resulting state respects canonical bounds, allowed transitions, and content-authored limits
**And** multiple Mood transition cues from one command are combined through deterministic policy or rejected with an actionable validation or typed Result problem
**And** unsupported or invalid wellbeing effects are rejected through validation or typed Result errors according to when the issue can be detected.

**Given** multiple wellbeing effects resolve from one command
**When** the command completes successfully
**Then** the system applies them atomically with the rest of the command effects
**And** the command records source context needed for result feedback, notifications, and later Memory Ledger summaries.

**Given** wellbeing state is exposed to player-facing surfaces
**When** HUD, activity confirmation, result feedback, lesson choices, or phone/status surfaces request read models
**Then** they receive display-ready Energy, Stress, and Mood state without direct Domain mutation
**And** high Stress, low Energy, and Mood changes are expressible through text/icon/shape/position cues rather than color alone.

**Given** wellbeing read models are rendered on controller-first and Steam Deck-conscious surfaces
**When** the player reviews wellbeing information
**Then** the state labels, warning severity, and actionable consequences remain understandable at 1080p and 720p/1280x800 layouts
**And** the read model provides enough focus/selection metadata for Godot views without making Godot responsible for gameplay rules.

**Given** ScenarioRunner or Application tests exercise wellbeing changes
**When** Energy, Stress, or Mood changes are applied from fixture content
**Then** outcomes are deterministic under controlled clock, seeded RNG/config, fixture catalogs, and in-memory stores
**And** tests can assert final wellbeing state, typed errors, and player-facing reason keys without launching Godot.

Coverage: FR11, FR12, FR13, FR23, FR46, FR49, FR58, FR60, NFR13, NFR18, NFR21, NFR22, NFR24, NFR27, NFR28, NFR30, UX-DR6, UX-DR9, UX-DR16, UX-DR18, UX-DR21, UX-DR25
Tags: FVS Subset, MVP, UX

### Story 2.4: Recovery, Rest, Sleep, Snooze, and Favorite-Aligned Effects

As a player,
I want recovery choices to affect my wellbeing in understandable ways,
So that rest, sleep, relaxing activities, snooze choices, and personal favorites feel like meaningful parts of school-life planning.

**Acceptance Criteria:**

**Given** a recovery, rest, relaxing activity, sleep, snooze, or favorite-aligned activity resolves
**When** the command applies wellbeing consequences
**Then** it can alter Energy, Stress, and qualitative Mood through the canonical wellbeing model from Story 2.3
**And** the effect is authored in validated content or derived from a validated rule rather than hardcoded in Godot UI.

**Given** a sleep or end-day transition provides recovery inputs
**When** the next day initializes
**Then** Energy, Stress, and Mood can be adjusted based on final sleep time, prior-day pressure, and validated recovery rules
**And** Epic 1 remains responsible for whether sleep timing and day transition were legal.

**Given** the player selects a valid snooze choice
**When** the snooze resolves
**Then** the system applies the authored recovery benefit and agency/time tradeoff consequences
**And** Epic 1 remains responsible for preventing snooze choices that would break school-day attendance or hard time anchors.

**Given** the player chooses a relaxing activity or low-effort recovery action
**When** the activity resolves
**Then** it can reduce Stress, restore Energy, or improve/shift Mood according to validated content
**And** the result preserves viable low-impact fallback choices for tired or stressed players.

**Given** the player chooses an activity aligned with favorites or preferences
**When** the activity resolves
**Then** preference alignment can improve Mood, flavor result feedback, or adjust consequence intensity within validated bounds
**And** preference mismatch must not hard-lock major content or create a single optimal lifestyle.

**Given** recovery effects are authored in content
**When** ContentValidator checks them
**Then** invalid effect targets, unsupported Mood transitions, missing feedback keys, unknown preference references, or excessive out-of-bounds values fail validation
**And** validation messages identify the activity, rule, or content ID that caused the issue.

**Given** a recovery choice is previewed or confirmed
**When** the player-facing read model is generated
**Then** it communicates the known wellbeing benefit, time cost, and any relevant tradeoff without exposing hidden formulas
**And** the message can be reinforced through non-color-only cues.

**Given** recovery effects are applied successfully
**When** result feedback is generated
**Then** the read model includes player-facing deltas or qualitative summaries for Energy, Stress, and Mood
**And** the feedback keys are suitable for localization and later notification or Memory Ledger references.

**Given** recovery and favorite-aligned effects are tested
**When** ScenarioRunner or Application tests execute representative choices
**Then** tests cover sleep recovery input, snooze recovery tradeoff, relaxing activity recovery, favorite-aligned Mood effect, and at least one rejected invalid recovery effect
**And** outcomes remain deterministic under controlled clock, seeded RNG/config, fixture catalogs, and in-memory stores.

Coverage: FR7, FR11, FR12, FR13, FR23, FR58, FR60, NFR13, NFR18, NFR21, NFR22, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR8, UX-DR9, UX-DR16, UX-DR18, UX-DR20, UX-DR21
Tags: MVP, UX

### Story 2.5: Activity Result Feedback Read Model

As a player,
I want activity results to clearly explain what changed and why,
So that I can understand the consequences of my choices without needing to inspect hidden systems.

**Acceptance Criteria:**

**Given** an activity resolves successfully
**When** the Application generates result feedback
**Then** it returns an `ActivityResult` or equivalent display-ready read model
**And** the read model is derived from the applied activity effects, source content ID, current GameState, and player-visible context rather than Godot presentation state.
**And** each result item traces from source content ID to effect ID or reason code to feedback key so automated tests can assert causality without guessing.

**Given** an activity changes wellbeing, academics, relationships, money, discovered information, event access, future obligations, flags, memories, or notifications
**When** result feedback is generated
**Then** the read model groups the consequences into player-readable result items
**And** each item includes a stable feedback key, severity or tone where relevant, visible label, optional icon/category hint, and source reason code.

**Given** a consequence should remain partially hidden
**When** the result feedback is generated
**Then** the read model communicates only player-visible information
**And** hidden relationship values, hidden schedules, undiscovered NPC locations, raw flags, raw formulas, or future story truth are not exposed.

**Given** an activity has no visible consequence in a category
**When** result feedback is generated
**Then** the read model avoids noisy empty sections
**And** it can still show a small qualitative acknowledgement when the activity intentionally has subtle or delayed effects.

**Given** the activity was blocked or warning-level risky before confirmation
**When** result feedback references the decision
**Then** wording and severity remain consistent with the reason-code and player-facing key model established by Epic 1 time-pressure feedback
**And** this story owns consequence-result communication, not the legality preview itself.

**Given** result feedback includes wellbeing changes
**When** Energy, Stress, or Mood changed
**Then** the read model can express numeric or qualitative Energy/Stress changes and qualitative Mood shifts in plain language
**And** wellbeing state does not rely on color alone to communicate improvement, strain, warning, or loss.

**Given** result feedback includes relationship, academic, money, memory, or obligation changes
**When** those effects are player-visible
**Then** the read model communicates the change at the right abstraction level, such as "Charlotte noticed your help," "Math readiness improved," "You spent a little money," or "You'll remember this later"
**And** it does not reveal hidden Bond numbers, exact formulas, or unrevealed future gates.

**Given** result feedback is rendered in controller-first UI
**When** the player reviews the result
**Then** the read model exposes focus order, primary continue action, optional details action when supported, Back/Confirm behavior, and adaptive input glyph metadata
**And** mouse-only interaction is not required to acknowledge or inspect the result.

**Given** result feedback appears on Steam Deck-conscious layouts
**When** labels, deltas, reason text, or localized strings are displayed at 720p/1280x800
**Then** the core consequence information remains readable without truncating important choice outcomes
**And** longer text has layout buffer or fallback behavior.

**Given** activity results are tested
**When** ScenarioRunner or Application tests execute representative activities
**Then** snapshots verify result grouping, visible/hidden information boundaries, reason keys, non-color-only severity cues, and deterministic ordering
**And** the same read model can later be rendered by Godot activity result UI without changing gameplay rules.

**Given** an activity preview includes consequence information
**When** the player inspects a known activity before confirmation
**Then** the preview read model exposes known time cost, known wellbeing/money/academic/relationship consequence hints, requirements, fallback or blocked reason when relevant, focus order, Confirm, Back, optional Details, and adaptive input glyph metadata
**And** unknown or hidden consequences are summarized without exposing hidden schedules, raw flags, hidden Bond, or future story truth.

Coverage: FR11, FR12, FR13, FR23, FR24, FR38, FR49, FR51, FR54, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, NFR21, NFR24, NFR28, NFR30, UX-DR6, UX-DR8, UX-DR9, UX-DR16, UX-DR18, UX-DR20, UX-DR21, UX-DR23, UX-DR25
Tags: FVS, MVP, UX

### Story 2.6: Low Energy and High Stress Availability/Friction

As a player,
I want tiredness and pressure to change my options without ending the day for me,
So that wellbeing creates meaningful tradeoffs while still leaving humane fallback choices.

**Acceptance Criteria:**

**Given** the player's Energy is low
**When** activity availability or confirmation is evaluated
**Then** high-effort actions can be blocked, warning-level risky, or downgraded according to validated content rules
**And** at least one low-impact fallback path remains available unless a specific hard constraint from Epic 1 blocks all action.

**Given** the player's Stress is high
**When** activities, lesson actions, social choices, or recovery choices are evaluated
**Then** Stress can create visible friction through warnings, reduced effectiveness, increased risk, altered feedback tone, or recovery prompts
**And** high Stress does not become an instant fail state or a hidden punishment.

**Given** an activity has Energy or Stress thresholds
**When** ContentValidator checks the activity definition
**Then** thresholds, fallback behavior, warning keys, and blocked reason keys must be valid
**And** the validator can identify at least one valid fallback activity for representative low Energy or high Stress fixture states
**And** rules that would create optimization-only play or remove all viable choices for common states are reported as design validation warnings.

**Given** an activity is blocked because of Energy or Stress
**When** the Application rejects the command
**Then** it returns a typed Result error with a stable reason code
**And** GameState remains unchanged.

**Given** an activity is warning-level risky because of Energy or Stress
**When** the player previews or confirms it
**Then** the read model explains the known risk in player-facing language
**And** the warning is reinforced without relying on color alone.

**Given** Energy or Stress affects activity outcomes
**When** the activity resolves successfully
**Then** the consequence application records the wellbeing source context needed for result feedback, notifications, and later reflection
**And** hidden formulas, raw thresholds, and exact optimization math are not exposed to the player.

**Given** low Energy or high Stress states are tested
**When** ScenarioRunner or Application tests execute representative choices
**Then** tests cover one blocked high-effort action, one warning-level action, one successful fallback, one recovery path, and one unchanged GameState rejection
**And** results remain deterministic under controlled clock, seeded RNG/config, fixture catalogs, and in-memory stores.

Coverage: FR11, FR12, FR13, FR16, FR49, FR58, FR60, NFR13, NFR21, NFR22, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR6, UX-DR8, UX-DR9, UX-DR16, UX-DR18, UX-DR20, UX-DR21
Tags: MVP, Constraint, UX

### Story 2.7: Light Money and Allowance Consequences

As a player,
I want small money choices to affect optional activities and preparation,
So that spending feels like school-life texture without becoming a large economy system.

**Acceptance Criteria:**

**Given** the player has an active semester state
**When** money or allowance state is initialized, loaded, or queried
**Then** the game tracks a light money resource suitable for optional consumables, snacks, coffee, small gifts, hangout extras, preparation items, and limited odd jobs
**And** this story does not introduce a broad shop simulation, inventory economy, or town job system.

**Given** an activity or purchase has a money cost
**When** the player previews or confirms it
**Then** affordability can be shown as available, warning-level risky, or blocked using the shared preview/reason-code style
**And** time, travel, and hard commitment legality remain owned by Epic 1.

**Given** the player confirms a valid money-spending activity
**When** the activity resolves
**Then** the money cost is applied atomically with the rest of the activity effects
**And** result feedback communicates the spending at a player-readable level without exposing raw economy formulas.

**Given** the player lacks enough money for a costed activity
**When** the activity command is confirmed
**Then** the command returns a typed Result error with a stable reason code
**And** GameState remains unchanged.

**Given** an allowance or limited odd-job effect is authored in content
**When** ContentValidator checks it
**Then** money gains, losses, feedback keys, availability limits, and referenced activity IDs must be valid
**And** content that implies repeatable grinding, large earnings, or uncontrolled economy growth is reported as a design validation warning.

**Given** money affects social or event options
**When** a hangout extra, small gift, consumable, preparation item, or event purchase is evaluated
**Then** money can create optional variants, mild tradeoffs, or small missed opportunities
**And** it must not hard-lock semester completion, essential classmate exposure, or academic progression.

**Given** money changes are surfaced to the player
**When** HUD, activity preview, result feedback, phone/status, or notification surfaces request read models
**Then** they receive display-ready money state or deltas through Application read models
**And** Godot views do not mutate money or decide affordability.

**Given** money behavior is tested
**When** Application tests or ScenarioRunner execute representative spending and earning paths
**Then** tests cover affordable spending, unaffordable rejection, small income, result feedback, and deterministic final money state
**And** rejected money commands leave GameState unchanged.

Coverage: FR13, FR40, FR42, FR54, FR58, FR60, NFR13, NFR21, NFR23, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR8, UX-DR9, UX-DR16, UX-DR20, UX-DR21
Tags: MVP, Constraint, UX

### Story 2.8: Off-School Activity Roles for Cafe, Park, Dorm, and Convenience Store

As a player,
I want off-school spaces to offer distinct activity roles,
So that leaving campus can support recovery, social life, study, errands, small purchases, and limited work without becoming a broad town simulator.

**Acceptance Criteria:**

**Given** MVP off-school activity roles are authored
**When** ContentValidator validates cafe, park, dorm/private room, and convenience store activity definitions
**Then** each space has a constrained role profile covering only supported activity types such as social hangouts, recovery, study, small purchases, gifts, errands, or limited odd jobs
**And** each role is represented through content IDs, availability conditions, consequence effects, feedback keys, and location/context references.

**Given** an off-school activity is previewed or confirmed
**When** the player is not physically or logically present at the required context
**Then** the activity cannot be started remotely through the phone
**And** the blocked read model provides player-facing reason text rather than a technical or arbitrary denial
**And** travel, known destination, and hard commitment feasibility remain owned by Epic 1 and Epic 6 supporting surfaces.

**Given** a cafe activity is authored
**When** it resolves
**Then** it can support social hangouts, light recovery, coffee/snack spending, study texture, or brief encounter hooks
**And** it remains scoped as an activity role rather than a full cafe management, shop, or romance-date subsystem.

**Given** a park activity is authored
**When** it resolves
**Then** it can support recovery, low-pressure social time, walking, event clues, or reflective activity hooks
**And** it does not expose hidden NPC schedules or live NPC tracking.

**Given** a dorm/private-room activity is authored
**When** it resolves
**Then** it can support rest, study, personal recovery, messaging-related context, preference-flavored downtime, or end-of-day preparation
**And** sleep legality, wind-down, and day boundary enforcement remain owned by Epic 1.

**Given** a convenience store activity is authored
**When** it resolves
**Then** it can support small purchases, gifts, errands, snacks, preparation items, or limited odd-job hooks
**And** it remains bounded by the light money scope from Story 2.7.

**Given** off-school activity content creates relationship, academic, wellbeing, money, flag, memory, or obligation effects
**When** those activities resolve
**Then** they use the activity resolution and result feedback paths from Stories 2.2 and 2.5
**And** location presentation, exploration layout, and event-specific spaces remain owned by later location and presentation epics.

**Given** off-school activity roles are tested
**When** ScenarioRunner or Application tests exercise representative off-school choices
**Then** tests cover one valid activity per MVP role, one phone-remote-start rejection, one missing-context rejection, and one deterministic result feedback snapshot
**And** rejected commands leave GameState unchanged.
**And** preview/result snapshots prove important consequence text remains readable at 720p/1280x800 with localized-text buffer or fallback behavior.

Coverage: FR13, FR34, FR39, FR40, FR42, FR54, FR58, FR60, NFR17, NFR21, NFR23, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR5, UX-DR8, UX-DR9, UX-DR13, UX-DR14, UX-DR16, UX-DR20, UX-DR21
Tags: MVP, Constraint, UX

### Story 2.9: Future Obligations, Missed Opportunities, and Memory Hooks

As a player,
I want activities to create visible follow-up consequences over time,
So that daily choices can matter beyond immediate stat changes without becoming a quest log or hidden checklist.

**Acceptance Criteria:**

**Given** an activity defines a future obligation, missed-opportunity marker, memory hook, notification hook, event access change, or delayed consequence
**When** ContentValidator checks the activity
**Then** every referenced content ID, timing window, feedback key, visibility rule, and source activity reference must be valid
**And** invalid or orphaned hooks fail validation with actionable report messages.

**Given** an activity successfully resolves
**When** it creates a future obligation or delayed consequence
**Then** the hook is recorded in canonical GameState with source context, visibility state, timing constraints, and player-facing reason keys
**And** the command applies it atomically with the rest of the activity effects.

**Given** an activity causes a missed opportunity
**When** the player is allowed to learn about it
**Then** the read model communicates the missed chance at a player-readable level
**And** it avoids revealing hidden schedules, hidden NPC locations, unrevealed story states, or exact optimization formulas.

**Given** an activity creates a memory hook
**When** result feedback, notification review, or later reflection systems request visible memory context
**Then** the hook can be surfaced as a short player-facing summary or reference key
**And** memory hooks are typed reflection inputs, not Memory Ledger entries or final reflection summaries by themselves
**And** the full Semester Reflection and Memory Ledger remain owned by Epic 9.

**Given** an activity creates a notification hook
**When** the notification system or phone surfaces later request it
**Then** the hook provides enough source context for notification text, timing, severity, and related target surface
**And** the phone does not become a remote launcher for ordinary activities.

**Given** a future obligation affects later availability
**When** the relevant day, time, location, relationship, academic, event, or wellbeing context is evaluated
**Then** the obligation can influence availability, result variants, warnings, or follow-up choices through validated rules
**And** it must not hard-lock semester completion unless explicitly covered by a validated progression rule.
**And** the obligation should create follow-up pressure, memory, or opportunity texture rather than becoming a hidden quest-log checklist.

**Given** delayed activity consequences are player-facing
**When** the Application generates feedback, phone/status, calendar/journal references, or future activity previews
**Then** messaging remains consistent with the Activity Result and Epic 1 reason-code models
**And** consequences are reinforced through text/icon/shape/position cues rather than color alone.

**Given** hooks are tested
**When** ScenarioRunner or Application tests execute representative activities across immediate and later states
**Then** tests cover creation, visibility, hidden-information protection, later availability influence, missed-opportunity feedback, memory hook availability, notification hook availability, and deterministic ordering
**And** rejected hook-creating commands leave GameState unchanged.
**And** preview/result snapshots prove delayed-consequence and missed-opportunity text remains readable at 720p/1280x800 with localized-text buffer or fallback behavior.

Coverage: FR13, FR35, FR37, FR38, FR41, FR42, FR49, FR51, FR58, FR60, NFR13, NFR18, NFR21, NFR23, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR4, UX-DR5, UX-DR8, UX-DR9, UX-DR16, UX-DR20, UX-DR21, UX-DR24, UX-DR25
Tags: MVP, Cross-Epic, UX

### Epic 3: Lessons, Homework, Tests, and Academics

**Goal:** Player can participate in active lessons, choose lesson actions, manage homework/deadlines/tests, build subject knowledge and grades, and face Week 20 exams with readable academic consequences.

**Epic 3 Test Evidence:** Epic 3 implementation must produce ContentValidator coverage for academic calendars, subject fixtures, lesson definitions, homework/deadlines, tests, exams, teacher metadata, feedback/result keys, and no-combat-framing labels/keys; Application/unit tests for lesson runtime, lesson actions, duplicate/stale lesson-block command rejection, risk resolution, academic state changes, homework/deadline outcomes, exam readiness, and typed Result failures; ScenarioRunner coverage for one narrow academic FVS path through a 3-block lesson, one homework/deadline path, one smaller-test/readiness path, teacher-check causality under seeded RNG/config, and one School App/Journal snapshot; and read-model snapshots proving controller-first focus, non-color risk cues, hidden-information boundaries, and readable academic consequences at 1080p and 720p/1280x800.

**Epic 3 FVS Ordering:** The first academic slice should prove a minimal subject/lesson fixture, one active 3-block lesson runtime, stable lesson action categories, one risk/teacher-check path, per-block and final lesson feedback, a narrow academic state/readiness update, and one School App/Journal snapshot. Full 4-subject breadth, all 6-8 homework tasks, all smaller tests, Week 20 exam breadth, and academic recovery checkpoints should follow only after the playable lesson path is proven.

### Story 3.1: Validated MVP Academic Calendar and Subject Fixture

As a developer,
I want a validated MVP academic fixture for subjects, lessons, homework, tests, and exams,
So that academic gameplay can be authored from content and proven before Godot presentation work.

**Acceptance Criteria:**

**Given** the MVP academic content fixture is loaded
**When** ContentValidator validates academic content
**Then** it includes exactly the MVP subject set needed for the semester slice, including 4 subjects, lesson definitions, teacher/lesson metadata, homework/deadline entries, smaller tests, and the Week 20 exam period
**And** each item has stable content IDs, display keys, schedule references, feedback keys, and validation-friendly source locations.

**Given** academic schedule content references school-day commitments
**When** validation checks lesson timing, tests, homework deadlines, and exams
**Then** every scheduled academic item aligns with the calendar/time model from Epic 1
**And** invalid durations, unsupported time blocks, impossible overlaps, missing return windows, or broken school-day anchors fail validation with actionable messages.

**Given** a lesson definition is authored
**When** ContentValidator checks it
**Then** it includes subject, teacher/strictness metadata, lesson context, allowed action categories or variants, feedback keys, and consequence rules
**And** it does not include combat framing, HP/boss/enemy language, or presentation-only Godot state.
**And** lesson labels, feedback keys, result keys, and validation-visible text must reject combat-framed terms such as enemy, boss, attack, damage, HP, defeat teacher, or equivalent framing.

**Given** homework, test, or exam content is authored
**When** validation checks academic progression references
**Then** each item maps to a known subject, timing window, readiness/knowledge effect, feedback key, and School App/Journal visibility rule
**And** missing subject references, orphaned deadlines, unbounded grading effects, or hidden raw formulas fail validation.

**Given** MVP academic scope is intentionally restrained
**When** future subjects, assignments, tests, teacher variants, or full-game promotion rules are added
**Then** they can extend the same academic content shape without changing the FVS fixture contract
**And** the MVP fixture remains small enough to support deterministic ScenarioRunner and fast `dotnet test` evidence.

**Given** the FVS academic fixture is selected
**When** implementation begins the first academic slice
**Then** it may use one subject, one teacher, one lesson, one representative homework/deadline, and one readiness/test placeholder before the full MVP academic fixture is complete
**And** that FVS subset must still validate against the same content shape as the full MVP fixture.

Coverage: FR14, FR17, FR18, FR38, FR60, NFR15, NFR18, NFR20, NFR24, NFR25, NFR27, NFR29, NFR30, UX-DR16, UX-DR21
Tags: FVS, Technical Enabler, Constraint

### Story 3.2: Active Lesson Session Runtime

As a player,
I want lessons to play as structured 45-minute sessions,
So that school time feels active and choice-driven instead of passive schedule advancement.

**Acceptance Criteria:**

**Given** the player is scheduled for a lesson commitment
**When** the lesson starts
**Then** the Application enters an active lesson runtime mode for a 45-minute session made of exactly three 15-minute blocks
**And** runtime truth is owned by Application state rather than Godot node visibility or scene-local flags.

**Given** a lesson block is active
**When** the player requests available lesson actions
**Then** the read model exposes the current subject, block number, teacher/context cues, available action categories, qualitative risk information, current focus, and allowed input actions
**And** the player can choose exactly one lesson action per block.
**And** a representative lesson should offer at least one academic-focused choice, one wellbeing or low-effort choice, and one tradeoff/risk choice when content context allows.

**Given** the lesson action surface is displayed
**When** the read model is generated
**Then** it exposes a three-block lesson clock state, teacher panel fields, selected/focused action, stable category, contextual variant label, risk tag, Confirm, Back, optional Details, and adaptive input glyph metadata
**And** any tab or section switching metadata is provided by the read model rather than inferred from Godot scene state.

**Given** the player confirms a valid lesson action
**When** the block resolves
**Then** time advances by one 15-minute lesson block through the same time model used by Epic 1
**And** the lesson runtime advances to the next block or final lesson result.

**Given** a lesson block has already resolved
**When** a duplicate, stale-session, wrong-runtime-mode, or out-of-order lesson action command is submitted for that block
**Then** the Application rejects it with a typed Result error
**And** GameState remains unchanged.

**Given** a lesson action command references an invalid lesson session ID, stale block index, already-resolved block, future block, or inactive lesson runtime mode
**When** the command is handled
**Then** the Application rejects it with a stable typed reason code
**And** the current lesson state, time, academic state, and feedback state remain unchanged.

**Given** the player attempts a non-lesson activity, travel command, normal phone activity launch, manual save, or other unstable command during an active lesson
**When** the command is evaluated
**Then** the Application rejects it with a typed Result error and player-facing reason code
**And** GameState remains unchanged.

**Given** the lesson reaches the end of the third block
**When** final resolution completes
**Then** the Application exits lesson runtime mode, records the lesson result, refreshes academic/wellbeing/relationship read models, and returns control to the appropriate post-lesson school context
**And** Godot views only render the resulting read models.

Coverage: FR4, FR5, FR6, FR14, FR15, FR33, FR52, FR53, FR55, FR58, FR60, NFR9, NFR10, NFR11, NFR15, NFR24, NFR27, NFR28, NFR30, UX-DR8, UX-DR16, UX-DR21, UX-DR25
Tags: FVS, MVP, Technical Enabler, UX

### Story 3.3: Lesson Action Categories and Contextual Variants

As a player,
I want lesson choices to use familiar stable categories with contextual flavor,
So that I can learn the academic decision language without every class feeling like a new minigame.

**Acceptance Criteria:**

**Given** a lesson action list is generated
**When** the current lesson context is known
**Then** the top-level action categories remain stable: Focus/Listen Actively, Take Notes/Light Participation, Study Another Subject, Use Phone/Text, Quiet Recovery/Zone Out, Chat/Pass Note, and Risky Distraction
**And** contextual variants can alter labels, tone, risk, or effects without replacing the category model.

**Given** lesson action content is authored
**When** ContentValidator checks action definitions
**Then** every action maps to a stable category, subject/context constraints, risk profile, allowed consequence effects, feedback keys, and optional contextual display text
**And** unsupported categories, missing feedback keys, or variants that hide the underlying category fail validation.

**Given** the player views lesson actions
**When** the read model presents contextual variants
**Then** each option communicates its general intent and known risk without exposing hidden formulas, exact teacher check math, or hidden relationship/academic values
**And** important tone or risk cues are not color-only.

**Given** an action is unavailable due to lesson context, Energy, Stress, restriction, teacher state, or content rule
**When** the lesson action read model is generated
**Then** it can show the action as unavailable with a player-facing reason or omit it according to visibility rules
**And** availability logic remains Application/content-driven rather than Godot UI-driven.

**Given** a future lesson, subject, teacher, or story context adds variants
**When** the variant is validated
**Then** it must preserve the stable action category contract
**And** it must not introduce uncontrolled minigame, combat, or branching scope.
**And** the representative lesson action set should preserve meaningful choice by including academic progress, wellbeing/recovery, and risk/tradeoff routes when content context allows.

Coverage: FR14, FR15, FR16, FR23, FR47, FR58, FR60, NFR9, NFR10, NFR12, NFR13, NFR15, NFR18, NFR20, NFR23, NFR24, NFR27, NFR29, UX-DR16, UX-DR21, UX-DR25
Tags: FVS, MVP, UX, Constraint

### Story 3.4: Lesson Risk, Teacher Checks, and Caught Outcomes

As a player,
I want risky lesson choices to show qualitative risk and resolve fairly,
So that distractions, recovery, participation, and social choices feel tense without becoming opaque punishment.

**Acceptance Criteria:**

**Given** a lesson action has a risk profile
**When** the player previews or selects it
**Then** the lesson read model exposes qualitative risk labels derived from teacher strictness, lesson context, current player state, and action risk
**And** the approved baseline labels are Safe, Low Risk, Risky, and Very Risky unless content validation approves a localized equivalent
**And** the labels avoid exact hidden formulas and do not rely on color alone.

**Given** a risky action resolves
**When** teacher check or attention outcome is evaluated
**Then** the result is deterministic under controlled clock, seeded RNG/config, fixture catalogs, and in-memory stores
**And** the result can produce academic, wellbeing, teacher-impression, restriction, relationship, feedback, flag, or memory-hook consequences.
**And** ScenarioRunner evidence records the seed/config, teacher strictness input, action risk input, outcome reason code, and applied consequence summary needed to debug the result.

**Given** a player is caught or loses teacher attention
**When** the outcome is applied
**Then** the command records the source action, teacher/context reason, applied effects, feedback key, and player-visible summary
**And** default caught outcomes should remain small and immediate unless repeated behavior, teacher strictness, or authored context explicitly escalates them
**And** hidden formulas, exact teacher meters, or unrevealed future gates are not exposed.

**Given** risk outcomes are authored in content
**When** ContentValidator checks teacher strictness, action risk, and caught/attention outcome definitions
**Then** missing feedback keys, unsupported effect targets, impossible restrictions, unbounded penalties, or combat-framed language fail validation
**And** design warnings are reported for risk rules that make one lesson action always optimal or always useless.

**Given** a risky action is rejected due to current state or content constraints
**When** the player confirms it
**Then** the Application returns a typed Result error with stable reason code
**And** GameState remains unchanged.

**Given** a lesson action resolves multiple effects
**When** one academic, wellbeing, relationship, teacher-impression, restriction, flag, or memory-hook effect cannot be applied
**Then** the command fails without partially applying earlier effects
**And** the failure is reported through validation or typed Result errors according to whether the issue is content-time or runtime-state dependent.

Coverage: FR14, FR15, FR16, FR17, FR24, FR46, FR49, FR58, FR60, NFR13, NFR15, NFR21, NFR22, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR8, UX-DR9, UX-DR16, UX-DR20, UX-DR21
Tags: MVP, UX, Constraint

### Story 3.5: Lesson Block Feedback and Final Lesson Result

As a player,
I want each lesson block and final lesson result to explain what happened,
So that I understand academic, wellbeing, teacher, and social consequences without reading hidden systems.

**Acceptance Criteria:**

**Given** a lesson action block resolves
**When** the Application generates per-block feedback
**Then** it returns display-ready feedback items for visible academic, risk, wellbeing, teacher-impression, relationship, restriction, flag, or memory-hook effects
**And** each item traces from lesson content ID to action/effect or reason code to feedback key.
**And** the read model identifies whether each item should appear immediately, roll into final lesson summary, or feed a later Journal/notification reference so the player is not shown duplicate noise.

**Given** a consequence should remain hidden
**When** per-block or final lesson feedback is generated
**Then** the read model communicates only player-visible information
**And** it does not expose hidden Bond numbers, exact teacher-impression values, raw formulas, raw flags, or unrevealed future gates.

**Given** the third lesson block completes
**When** final lesson result is generated
**Then** it summarizes the lesson at the right abstraction level, including visible subject progress/readiness, wellbeing strain or recovery, teacher impression cues, restrictions, relationship beats, or notable memory hooks
**And** it avoids noisy empty categories.
**And** final results should consolidate per-block outcomes rather than repeat every minor line verbatim.

**Given** lesson feedback appears on controller-first surfaces
**When** the player reviews block or final results
**Then** the read model exposes focus order, Continue, optional Details when supported, Back behavior, and adaptive input glyph metadata
**And** mouse-only interaction is not required.

**Given** lesson feedback appears in Steam Deck-conscious layouts
**When** labels, risk text, result summaries, or localized strings are displayed at 720p/1280x800
**Then** important academic and risk consequences remain readable without truncating the core outcome
**And** longer text has layout buffer or fallback behavior.

Coverage: FR14, FR16, FR17, FR24, FR38, FR46, FR49, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR15, NFR18, NFR24, NFR28, NFR30, UX-DR8, UX-DR9, UX-DR16, UX-DR20, UX-DR21, UX-DR23, UX-DR25
Tags: FVS, MVP, UX

### Story 3.6: Subject Knowledge, Grades, Teacher Impression, and Exam Readiness Model

As a player,
I want academic progress to be visible through subject knowledge, grades, teacher impression, and exam readiness,
So that school choices create semester pressure without becoming a pure optimization spreadsheet.

**Acceptance Criteria:**

**Given** academic state is initialized or loaded
**When** the Application queries academic progress
**Then** it tracks subject knowledge, visible current grades, homework/deadline status, smaller-test results, teacher impression, and exam readiness for MVP subjects
**And** canonical academic state is not owned or mutated by Godot UI.

**Given** an academic effect is applied from a lesson, study activity, homework, test, dialogue, event, or validated content rule
**When** the effect resolves
**Then** it updates the relevant subject or academic dimension through deterministic Application/domain policy
**And** invalid values, unknown subjects, unsupported effect types, or out-of-bounds outcomes are rejected by validation or typed Result errors.
**And** grade, knowledge, readiness, and teacher-impression bounds and conflict resolution rules are deterministic enough for unit tests to assert without relying on hidden formulas.

**Given** visible grades or readiness are shown to the player
**When** the School App/Journal or result feedback requests read models
**Then** the read model exposes player-readable standing, trend, readiness, concern, or teacher note cues
**And** it does not expose hidden formulas, exact probability math, or raw internal thresholds.

**Given** teacher impression affects academic or lesson outcomes
**When** it is updated or queried
**Then** the system can use it as contextual pressure, feedback, or gate input
**And** it must not become a broad hidden reputation grind detached from authored school context.

**Given** academic state changes are tested
**When** Application tests apply representative lesson, study, homework, and test effects
**Then** tests assert deterministic state changes, bounds, typed errors, feedback keys, and read-model outputs
**And** ScenarioRunner covers only a narrow representative academic path rather than every grade permutation.

Coverage: FR14, FR16, FR17, FR18, FR19, FR20, FR38, FR49, FR58, FR60, NFR18, NFR21, NFR23, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR9, UX-DR16, UX-DR18, UX-DR20, UX-DR21
Tags: MVP, Technical Enabler, UX

### Story 3.7: Homework and Deadline Management

As a player,
I want homework and deadlines to create manageable academic pressure,
So that study choices, missed work, and preparation matter across the semester.

**Acceptance Criteria:**

**Given** MVP homework content is loaded
**When** ContentValidator validates the semester fixture
**Then** it includes 6-8 homework tasks across the semester, each with subject, deadline, visibility rule, effort/time reference, academic effect, feedback key, and School App/Journal entry
**And** invalid deadlines, missing subject references, unsupported effects, or impossible timing fail validation.

**Given** the player reviews homework
**When** the School App/Journal read model is requested
**Then** it shows known homework, deadlines, subject context, status, and actionable next-step cues in a scannable form
**And** it avoids becoming a formal slot-planning productivity tool.

**Given** the player studies for or works on homework
**When** the activity resolves
**Then** the command applies authorized time cost through Epic 1 feasibility and academic/wellbeing effects through Application state
**And** result feedback explains visible progress, strain, or missed tradeoffs.

**Given** a homework deadline passes
**When** day transition or schedule evaluation processes deadlines
**Then** submitted, incomplete, late, or missed homework states resolve deterministically according to validated content rules
**And** player-facing feedback explains visible academic consequences without exposing hidden formulas.

**Given** homework behavior is tested
**When** Application tests or ScenarioRunner execute representative homework paths
**Then** tests cover visible assignment, progress/submission, missed or late deadline, academic effect, feedback keys, and Journal snapshot
**And** rejected homework commands leave GameState unchanged.
**And** golden fixtures include at least deadline passed, late homework, submitted homework, and missed homework states.

Coverage: FR4, FR5, FR8, FR17, FR18, FR35, FR38, FR58, FR60, NFR6, NFR17, NFR18, NFR21, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR4, UX-DR8, UX-DR9, UX-DR16, UX-DR20, UX-DR21
Tags: MVP, UX

### Story 3.8: Smaller Tests and Week 20 Exam Period

As a player,
I want tests and exams to reflect my preparation,
So that academic pressure builds toward a readable Week 20 exam period.

**Acceptance Criteria:**

**Given** the MVP semester academic fixture is validated
**When** ContentValidator checks tests and exams
**Then** it includes one smaller test per subject before the exam period and a Week 20 exam period for MVP subjects
**And** each test/exam has subject, timing, readiness inputs, result rules, feedback keys, and School App/Journal visibility.

**Given** a smaller test occurs
**When** the test resolves
**Then** it can use subject knowledge, homework state, study preparation, wellbeing context, and validated content rules to produce a visible academic result
**And** the result records source context for feedback, Journal updates, and later reflection.

**Given** the Week 20 exam period begins
**When** exam commitments are initialized
**Then** they appear as hard academic commitments according to the calendar/schedule model
**And** normal activity/travel legality around them remains governed by Epic 1.

**Given** an exam resolves
**When** the Application applies exam results
**Then** subject standing, exam readiness, grade state, and visible academic feedback update deterministically
**And** failure or weak performance can feed checkpoint recovery logic without immediately implementing a full-game graduation epilogue.

**Given** tests or exams are player-facing
**When** result feedback or School App/Journal summaries are generated
**Then** they show readable outcome, preparation signal, subject impact, and next-step cues
**And** they avoid exact formula exposure, combat framing, or boss-like teacher treatment.

**Given** test/exam behavior is tested
**When** Application tests or ScenarioRunner execute representative academic assessments
**Then** tests cover one smaller test, one exam result, readiness influence, feedback keys, Journal update, and deterministic final academic state
**And** broad permutations remain unit/Application tests rather than ScenarioRunner bloat.
**And** golden fixtures include weak test result, adequate test result, low exam readiness snapshot, and improved readiness snapshot.

Coverage: FR6, FR17, FR18, FR19, FR20, FR35, FR38, FR48, FR58, FR60, NFR13, NFR15, NFR18, NFR21, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR4, UX-DR9, UX-DR16, UX-DR20, UX-DR21
Tags: MVP, UX

### Story 3.9: Academic Failure and Checkpoint Recovery Options

As a player,
I want academic failure to offer clear recovery checkpoints,
So that school stakes matter without destroying the whole semester through one bad ending.

**Acceptance Criteria:**

**Given** end-of-year or exam-period academic failure is evaluated
**When** subject results fail the minimum passing requirement
**Then** the system can identify academic failure as the hard progression fail gate for the full-game design
**And** MVP implementation can defer full three-year promotion while preserving the recovery contract.

**Given** academic failure is detected
**When** recovery options are generated
**Then** the player can be offered checkpoint recovery options for one week before the exam, four weeks before the exam, or the beginning of the semester
**And** the options are derived from save/checkpoint capability owned by Epic 9.
**And** Epic 3 owns academic failure cause/read-model inputs, while Epic 9 owns checkpoint selection, save/load, restoration, and recovery execution.

**Given** the player reviews academic recovery options
**When** the read model is generated
**Then** it explains the failure cause and recovery choice at a player-facing level
**And** it avoids shame framing, hidden formula exposure, or implying a true/golden ending path.

**Given** a recovery checkpoint is selected
**When** the command is executed
**Then** it routes through the save/load and checkpoint recovery systems rather than mutating academic state ad hoc
**And** unsupported recovery states return typed Result errors with unchanged GameState.
**And** checkpoint restore is an Epic 9 command path, not an academic-system mutation.

**Given** checkpoint recovery content and state are validated
**When** ContentValidator or tests inspect the failure/recovery setup
**Then** required checkpoint availability, subject failure reasons, feedback keys, and recovery option text must be valid
**And** missing checkpoints or impossible recovery routes are reported clearly.

Coverage: FR3, FR17, FR19, FR20, FR52, FR58, FR59, FR60, NFR4, NFR13, NFR18, NFR21, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR16, UX-DR20, UX-DR21
Tags: Later MVP, Full Game/Later, Cross-Epic, UX

### Story 3.10: School App / Journal Academic Read Models

As a player,
I want the School App and Journal to summarize academics clearly,
So that I can understand grades, homework, deadlines, teacher notes, exam calendar, and readiness without using a planner UI.

**Acceptance Criteria:**

**Given** the player opens academic information through the phone or journal surface
**When** the Application returns academic read models
**Then** they include subject grades, homework, deadlines, subject status, teacher notes, exam calendar, and exam readiness where known
**And** they are derived from Application state/projections rather than Godot querying Domain or content directly.
**And** Epic 3 owns academic read-model shape and hidden-information rules, while Epic 6 owns phone information architecture/navigation and Epic 8 owns detailed visual presentation/focus polish.

**Given** academic information includes hidden or uncertain state
**When** the read model is generated
**Then** it shows only player-known information, qualitative cues, and actionable next-step hints
**And** it does not expose hidden formulas, exact fail thresholds, raw teacher-impression values, or unrevealed future outcomes.

**Given** the School App/Journal is controller-first
**When** the player navigates academic surfaces
**Then** the read model exposes focus order, tabs or sections, Back behavior, readable selected state, adaptive glyph metadata, and no mouse-only core actions
**And** focus follows the reading and decision path.

**Given** academic summaries are displayed at 1080p and 720p/1280x800
**When** subject labels, homework text, teacher notes, deadlines, or readiness cues are rendered
**Then** important academic information remains readable without truncating essential consequences
**And** longer localized text has layout buffer or fallback behavior.

**Given** the phone Calendar and School App/Journal both show academic commitments
**When** the same deadline, test, exam, or commitment appears across surfaces
**Then** wording, severity, timing, and visibility remain consistent
**And** the Calendar does not become a formal slot planner while the Journal does not become a quest log.
**And** Journal next-step cues must remain actionable summaries rather than quest checklist tasks or formal schedule slots.

Coverage: FR33, FR35, FR38, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR16, NFR17, NFR18, NFR24, NFR28, NFR30, UX-DR4, UX-DR5, UX-DR6, UX-DR13, UX-DR14, UX-DR15, UX-DR16, UX-DR20, UX-DR21, UX-DR23, UX-DR24
Tags: MVP, UX

### Story 3.11: Academic ScenarioRunner and Validation Evidence

Story Type: Evidence Gate

As a developer,
I want deterministic academic scenario evidence for the MVP loop,
So that lessons, homework, tests, and academic read models can be trusted before broad content expansion.

**Acceptance Criteria:**

**Given** the academic FVS fixture is available
**When** ScenarioRunner executes the representative academic path
**Then** it runs through one scheduled lesson session, three lesson blocks, one valid lesson action per block, final lesson feedback, a minimal academic delta, and one School App/Journal snapshot
**And** every gameplay action uses Application commands and read models rather than a parallel simulation path, while homework/study and smaller-test/readiness evidence become MVP evidence after Stories 3.6-3.8 are implemented.

**Given** the scenario reaches lesson, homework, test, or Journal decision points
**When** read models are captured
**Then** snapshots include current time/context, academic state, known commitments, available academic actions, visible risk/readiness cues, focus metadata, and player-facing feedback keys
**And** hidden formulas, hidden values, and unrevealed future gates are absent.

**Given** the academic scenario includes rejected commands
**When** it attempts at least one invalid lesson action, blocked manual save during lesson, impossible homework timing, or unavailable academic command
**Then** ScenarioRunner can assert typed Result errors
**And** GameState remains unchanged after each rejected command.

**Given** ContentValidator runs against academic fixtures
**When** it validates subjects, lessons, actions, homework, tests, exams, feedback keys, and journal visibility
**Then** the report distinguishes syntax failures, design correctness failures, and design-scope warnings
**And** it catches no-combat-framing violations for lesson labels and feedback.
**And** it catches no-combat-framing violations in feedback keys, result keys, and author-facing labels, including enemy, boss, attack, damage, HP, defeat teacher, or equivalent combat framing.

**Given** academic evidence is generated
**When** test reports are reviewed
**Then** broad effect permutations, grading bounds, risk outcomes, and read-model formatting are covered by fast Application/unit tests
**And** ScenarioRunner remains narrow, deterministic, and suitable as a future Godot smoke-test reference.

Coverage: FR14, FR15, FR16, FR17, FR18, FR33, FR38, FR52, FR53, FR58, FR60, NFR13, NFR15, NFR24, NFR25, NFR26, NFR27, NFR28, NFR29, NFR30, UX-DR8, UX-DR9, UX-DR16, UX-DR20, UX-DR21, UX-DR25
Tags: FVS, Technical Enabler, UX

### Epic 4: Character Creation, Preferences, and Player Identity

**Goal:** Player can create a personalized student protagonist with a chosen identity, fixed starting attributes, favorites, preferences, and pixel-art appearance whose starting shape influences play without predetermining or hard-locking their emerging school identity.

**Epic 4 Test Evidence:** Epic 4 implementation must produce ContentValidator coverage for identity fields, point-buy rules, preference catalogs, sprite-part compatibility, display/localization keys, and seeded protagonist fixtures; Application/unit tests for creation state transitions, point allocation, invalid or duplicate handoff, preference influence boundaries, immutable confirmed attributes, typed Result failures, and deterministic handoff; ScenarioRunner coverage for one seeded FVS protagonist and one representative completed character-creation path into the first school-day context; and controller/readability snapshots for creation, review, validation feedback, and sprite preview at 1080p and 720p/1280x800.

**Epic 4 FVS Ordering:** The first vertical slice may begin with one validated seeded protagonist fixture that uses the same confirmed-character contract as the final flow. Full interactive creation should then prove identity fields, point-buy, preferences, sprite selection, review/confirmation, and first-day handoff without requiring relationship, activity, phone, or semester-reflection implementations to be complete.

### Story 4.1: Protagonist Identity Fixture and Validation

As a developer,
I want a validated protagonist identity model and seeded FVS fixture,
So that gameplay systems can use one stable player identity contract before the full creation interface is available.

**Acceptance Criteria:**

**Given** protagonist identity content and creation rules are loaded
**When** ContentValidator validates them
**Then** the contract supports player name, gender and associated player-facing identity fields, six starting attributes, five preference categories, and pixel-art sprite selections
**And** each catalog option uses a stable content ID, display/localization key, supported value set, and validation-friendly source location.

**Given** the FVS uses a seeded protagonist
**When** the fixture is initialized
**Then** it produces a complete confirmed protagonist through the same Application/domain contract used by interactive character creation
**And** it does not bypass validation, point-buy invariants, preference rules, or sprite compatibility.

**Given** identity content contains a missing display key, unknown preference option, unsupported identity value, duplicate ID, invalid attribute definition, or incompatible sprite reference
**When** ContentValidator runs
**Then** validation fails with an actionable message that identifies the source and reason
**And** invalid content cannot enter the runtime `ContentCatalog`.

**Given** protagonist identity is canonical gameplay state
**When** the game starts, saves, loads, or rebuilds read models
**Then** the confirmed identity can be represented without Godot node paths, scene-local state, focus state, or presentation caches
**And** Godot views receive display-ready read models rather than mutating identity state directly.

**Given** future full-game customization expands beyond MVP
**When** new cosmetic or preference options are added
**Then** they can extend validated catalogs without changing the confirmed protagonist contract
**And** evolving preferences remain later scope rather than weakening the fixed MVP creation contract.

Coverage: FR1, FR21, FR22, FR23, FR58, FR60, NFR18, NFR20, NFR23, NFR24, NFR27, NFR29, NFR30
Tags: FVS, Technical Enabler, Constraint

### Story 4.2: Character Creation Session, Navigation, and Review

As a player,
I want to enter and review my protagonist's core identity,
So that I can begin school as a student who feels intentionally mine.

**Acceptance Criteria:**

**Given** the player starts a new game that requires character creation
**When** the creation flow opens
**Then** the Application creates an unconfirmed creation session with steps for core identity, attributes, preferences, appearance, and final review
**And** abandoning or cancelling the session does not create or partially mutate a confirmed protagonist.

**Given** the player enters a name and selects supported gender or identity fields
**When** the input is validated
**Then** valid selections are retained in the creation session and shown in the review model
**And** empty, whitespace-only, over-limit, unsupported, or otherwise invalid values produce readable validation feedback without losing valid choices.

**Given** the player navigates the creation flow with a controller or keyboard
**When** focus moves between fields, options, steps, and review actions
**Then** focus order follows the reading and decision path, current focus is unambiguous, Confirm and Back behave consistently, and no core action requires a mouse
**And** adaptive glyph metadata and non-color-only validation states are available to the view.

**Given** the player uses Back within the creation flow
**When** the current step is not the first step
**Then** Back returns to the previous step without discarding valid session data and restores focus to the last meaningful control
**And** Back from the first step opens an explicit abandon-session confirmation.

**Given** the player reaches final review
**When** required identity fields or selections are incomplete or invalid
**Then** confirmation is blocked with a summary that identifies the affected step and provides a direct, focusable route back to it
**And** the creation session remains editable.

**Given** the player activates an issue or Edit action in final review
**When** the affected creation step opens
**Then** focus moves to the first invalid control or the last meaningful edited control while all other valid choices are preserved
**And** after correction the issue clears immediately and returning to review restores focus to the same review section.

**Given** creation text is displayed at 1080p or 720p/1280x800
**When** names, option labels, descriptions, errors, or review summaries use 130% of reference text length
**Then** all required choices, validation messages, and navigation actions remain visible without overlap or truncation
**And** core text is not truncated behind controls or focus decoration.

Coverage: FR1, FR21, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, NFR24, NFR27, NFR28, UX-DR13, UX-DR14, UX-DR16, UX-DR21
Tags: MVP, UX

### Story 4.3: Starting Attribute Point-Buy System

As a player,
I want to shape my protagonist's starting strengths and weaknesses,
So that early school choices reflect a deliberate but imperfect personal profile.

**Acceptance Criteria:**

**Given** a new attribute-allocation step is initialized
**When** the point-buy state is created
**Then** it contains exactly six validated attributes, each starts at baseline 2, and the player receives six additional points to allocate
**And** every attribute is constrained to the inclusive range 1-5.

**Given** the player raises or lowers an attribute
**When** the allocation command is accepted
**Then** `remainingPoints = 6 - sum(attributeValue - 2)` across exactly six attributes, so raising consumes points and lowering below baseline makes those points available for reallocation
**And** a confirmable allocation has `remainingPoints == 0`, totals exactly 18 attribute points, and keeps every value within 1-5.

**Given** the player attempts an out-of-range, overspent, unknown-attribute, stale-session, or otherwise invalid allocation command
**When** the Application evaluates it
**Then** it returns a stable typed Result reason code
**And** the allocation state and remaining point pool are unchanged.

**Given** an allocation command uses a command ID
**When** the same command is replayed or a conflicting replay is submitted
**Then** an exact replay returns the original Result without applying its delta twice, while a conflicting replay returns a typed error
**And** the complete allocation remains unchanged after a rejected replay.

**Given** the player reviews attribute allocation
**When** the read model is generated
**Then** it shows all six attribute names, baseline values, allocated changes, current values, minimum and maximum states, remaining points, available increase/decrease actions, and concise player-facing descriptions
**And** it communicates strengths and weaknesses without presenting one recommended or optimal build.
**And** increase/decrease actions expose why they are enabled or blocked without relying on color.

**Given** the player attempts to confirm character creation
**When** the point pool is not exactly exhausted or any attribute violates its bounds
**Then** confirmation is blocked with a readable reason and focus target
**And** no confirmed protagonist is created.

**Given** the protagonist has been confirmed
**When** later gameplay, content, a load rebuild, or a UI command attempts to change starting attributes
**Then** the mutation is rejected because starting attributes remain fixed after creation
**And** temporary modifiers or contextual checks, if later introduced, remain distinct from the canonical starting values.

Coverage: FR21, FR22, FR58, FR60, NFR9, NFR10, NFR11, NFR13, NFR21, NFR24, NFR27, NFR28, NFR30, UX-DR13, UX-DR14, UX-DR16, UX-DR21
Tags: MVP, UX, Constraint

### Story 4.4: Attribute Influence Contract for School Life

As a player,
I want my starting attributes to influence academics, social situations, and activities without dictating them,
So that my build matters while effort, context, and later choices remain meaningful.

**Acceptance Criteria:**

**Given** an academic, social, lesson, dialogue, event, or activity rule needs a protagonist attribute
**When** the rule is authored and validated
**Then** it references a known attribute through a typed condition or modifier contract
**And** Godot scenes do not read raw attributes to decide gameplay outcomes.

**Given** an attribute influences an available action or outcome
**When** the Application evaluates the relevant rule
**Then** starting attributes produce observable, authored, and bounded differences in one early academic fixture and one social or activity fixture
**And** accumulated knowledge, preparation, effort, wellbeing, relationship, timing, location, and story context can progressively outweigh starting aptitude.

**Given** a protagonist has a low value in an attribute
**When** ordinary MVP content is evaluated
**Then** low value alone does not hard-lock major academic, relationship, club, event, or ending content
**And** the player retains a viable fallback, alternative approach, or opportunity to succeed through investment and context.

**Given** an attribute-influenced result is shown to the player
**When** feedback or a read model explains the outcome
**Then** it can communicate a readable strength, difficulty, or contextual contribution
**And** it does not expose raw formulas, hidden thresholds, or imply that one build is the correct path.

**Given** attribute influence rules are tested
**When** representative high, baseline, and low profiles are evaluated under controlled inputs
**Then** an isolated fixture matrix proves deterministic modifiers against named authored caps, typed failures, and at least one major-content route for each profile
**And** design validation warns when an attribute becomes universally optimal, useless, or a major-content hard gate
**And** consuming epics own cross-epic integration tests beyond these representative fixtures.

Coverage: FR1, FR13, FR17, FR22, FR24, FR46, FR49, FR58, FR60, NFR21, NFR23, NFR24, NFR27, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic, Constraint

### Story 4.5: Favorites and Preference Selection

As a player,
I want to choose my protagonist's tastes and preferred ways of spending time,
So that social and everyday school-life moments can feel personally flavored from the start.

**Acceptance Criteria:**

**Given** the preference step is initialized
**When** the preference read model is generated
**Then** it presents validated options for social preference, free-time preference, music, media, and food
**And** each category explains its scope without promising unavailable content or revealing hidden compatibility formulas.

**Given** the player chooses an option in each preference category
**When** a selection command is accepted
**Then** the creation session stores one valid selection per required category
**And** moving focus never changes the committed selection until Confirm; replacing a selection updates only that category without changing other creation choices.

**Given** the player submits an unknown option, wrong-category option, stale-session command, or incomplete preference set
**When** the Application validates the command or final confirmation
**Then** runtime commands return distinct stable reason codes for unknown option, wrong category, stale session, or incomplete set, while invalid catalog definitions remain ContentValidator failures
**And** the complete creation session and canonical state remain unchanged after rejection.

**Given** the player reviews selected favorites and preferences
**When** the review model is generated
**Then** it shows player-facing labels and concise flavor summaries for all five categories
**And** it avoids numeric compatibility, Mood formulas, or language suggesting irreversible content locks.

**Given** preference options are presented with controller or keyboard input
**When** the player navigates, changes, or reviews selections
**Then** every option has predictable focus, selected and focused states are distinguishable without color alone, Confirm and Back are available, and no core action requires a mouse
**And** selected and focused states remain simultaneously identifiable through text, icon, shape, or outline cues while long or localized option text remains readable at 720p/1280x800.

Coverage: FR21, FR23, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, NFR24, NFR27, NFR28, UX-DR13, UX-DR14, UX-DR16, UX-DR21
Tags: MVP, UX

### Story 4.6: Preference Influence Without Hard Locks

As a player,
I want my preferences to color moods, compatibility, conversations, and activities,
So that my protagonist feels recognizable without losing meaningful content paths.

**Acceptance Criteria:**

**Given** Mood, compatibility, hangout, gift, dialogue, texting, social-media, or activity content uses protagonist preferences
**When** ContentValidator checks the rule
**Then** it references known preference categories and options through typed conditions, modifiers, or flavor variants
**And** unsupported effects, broken references, unbounded modifiers, or raw UI-driven rules fail validation.

**Given** a protagonist preference matches or conflicts with contextual content
**When** the relevant Application rule resolves
**Then** it may influence qualitative Mood, compatibility, reaction flavor, available approach, feedback, or bounded consequence strength
**And** preferences never modify canonical attributes directly
**And** the result remains deterministic under controlled state and content.

**Given** the protagonist does not have a preferred taste for an activity, gift, character, or story situation
**When** major content eligibility is evaluated
**Then** preference mismatch alone does not hard-lock a major relationship arc, club, event, academic path, or ending
**And** mismatch may change flavor, efficiency, or bounded Mood response but does not become a punitive failure state
**And** authored context can provide neutral, effort-based, or alternative routes.

**Given** protagonist preferences are visible in creation or later identity summaries
**When** a player-facing read model is produced
**Then** it may expose the protagonist's own confirmed preferences and visible flavor consequences
**And** it does not expose NPC hidden preferences, raw compatibility values, unrevealed dialogue gates, or future outcomes.

**Given** preference influence is tested across representative systems
**When** matching, neutral, and conflicting fixtures are evaluated
**Then** an isolated fixture matrix proves deterministic influence against named authored caps, player-facing causality, and at least one major-content route for each profile
**And** design validation warns when one preference is always optimal, has no observable influence, or becomes a major-content hard gate
**And** consuming epics own cross-epic integration tests beyond the representative Mood and dialogue or activity fixtures.

Coverage: FR1, FR13, FR17, FR23, FR24, FR46, FR49, FR51, FR56, FR58, FR59, FR60, NFR21, NFR23, NFR24, NFR27, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic, Constraint

### Story 4.7: Pixel-Art Sprite Customization Contract

As a player,
I want to customize my protagonist's pixel-art appearance,
So that I can recognize my student in exploration and school-life scenes.

**Acceptance Criteria:**

**Given** the appearance step is opened
**When** the Application returns the sprite-customization read model
**Then** it provides validated options for hair, outfit, eye color, and skin color plus the currently selected combination
**And** it exposes stable cosmetic IDs and layer ordering only; the Godot adapter resolves those IDs through a validated ID-to-asset manifest.

**Given** the player changes an appearance option
**When** the selection is valid
**Then** moving focus leaves the committed selection unchanged until Confirm, after which the creation session updates that option and emits stable cosmetic IDs for preview
**And** selected and focused states remain simultaneously identifiable while other creation choices remain unchanged.

**Given** an appearance combination references missing assets, incompatible layers, unsupported palette values, duplicate IDs, or invalid preview metadata
**When** ContentValidator runs
**Then** validation fails with actionable source information
**And** the invalid combination cannot be confirmed or loaded into runtime catalogs.
**And** validation checks declared compatibility rules for a bounded catalog of composited parts and palettes without requiring bespoke assets or an exhaustively authored Cartesian product.

**Given** the player navigates appearance options
**When** controller or keyboard focus moves through categories and choices
**Then** the current category, focused option, selected option, Confirm, and Back actions are unambiguous without relying on color alone
**And** the preview uses integer pixel scaling, sufficient backdrop contrast, and a stable viewport that does not resize, shift, or obscure required labels, controls, or validation feedback at 1080p or 720p/1280x800.

**Given** MVP visual scope is enforced
**When** protagonist presentation assets are planned or validated
**Then** the player uses pixel-art sprite customization in the world and does not require a high-resolution dialogue portrait set
**And** expanded cosmetics, animations, or evolving appearance remain later scope unless separately approved.

Coverage: FR21, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, NFR20, NFR23, NFR24, NFR29, UX-DR13, UX-DR14, UX-DR16, UX-DR21
Tags: MVP, UX, Constraint

### Story 4.8: Starting Identity Summary and First-Day Handoff

As a player,
I want my confirmed creation choices to carry into the first school day,
So that the student I designed immediately feels connected to the life I begin playing.

**Acceptance Criteria:**

**Given** a complete valid creation session or validated seeded fixture
**When** one idempotent `StartNewGame` command executes
**Then** it atomically consumes the session or fixture, initializes one canonical protagonist and first playable school-day context, and marks the source consumed
**And** the handoff uses Application commands and canonical state rather than direct scene mutation.

**Given** the first-day context requests protagonist information
**When** identity read models are generated
**Then** they provide the confirmed name, supported identity display fields, starting attributes, preferences, and sprite descriptor needed by the requesting surface
**And** each surface receives only the fields it is authorized to display.

**Given** early academic, lesson, activity, dialogue, social, or wellbeing systems request identity inputs
**When** those systems are available
**Then** they can consume typed attribute and preference hooks without requiring the full relationship, phone, event, or semester-reflection implementation
**And** absent downstream systems do not block character confirmation or first-day entry.

**Given** identity signals are accumulated for future reflection
**When** the protagonist begins play
**Then** the handoff exports stable starting identity signal IDs for later comparison with accumulated gameplay signals
**And** creation choices provide baseline context only while Epic 9 owns reflection selection and presentation
**And** accumulated actions, habits, relationships, academics, wellbeing, and missed opportunities take precedence over any precomputed identity, true ending, golden path, or fixed Memory Ledger conclusion.

**Given** handoff initialization fails because of invalid content, duplicate protagonist state, stale creation session, or unavailable first-day fixture
**When** the Application evaluates the command
**Then** it returns a stable typed Result reason code without partially initializing a new game
**And** the player receives a recoverable, readable error or can return to review as appropriate.

**Given** a successful `StartNewGame` command is replayed for the same consumed source
**When** the Application evaluates the replay
**Then** it returns the documented idempotent Result or stable duplicate-handoff error without creating a second protagonist or first-day context
**And** canonical state remains equivalent to the first successful handoff.

Coverage: FR1, FR13, FR17, FR21, FR22, FR23, FR24, FR46, FR49, FR51, FR56, FR58, FR59 (supporting), FR60, NFR21, NFR24, NFR27, NFR28, NFR30
Tags: MVP, Cross-Epic, Technical Enabler

### Story 4.9: Character Creation Scenario and Test Evidence

Story Type: Evidence Gate

As a developer,
I want deterministic character-creation evidence and a reusable protagonist fixture,
So that identity choices and first-day handoff can be trusted before broader content integration.

**Acceptance Criteria:**

**Given** the seeded FVS protagonist fixture is available
**When** ScenarioRunner initializes the first vertical slice
**Then** it validates and confirms the fixture through the production character contract before entering the first playable school-day context
**And** the scenario records stable identity, attribute, preference, sprite, and handoff summaries without exposing presentation-only state.

**Given** deterministic character scenarios use identical content version, fixture ID, controlled clock, and RNG seed
**When** the scenarios are run twice
**Then** they produce byte-equivalent canonical summaries and typed Results
**And** changing only the seed affects exclusively outcomes declared as seeded.

**Given** interactive creation is available
**When** the representative character-creation scenario runs
**Then** it enters valid core identity data, completes a legal six-attribute point-buy, selects all five preference categories, selects a compatible sprite appearance, reviews the result, executes `StartNewGame` once, and reaches first-day handoff
**And** every action uses the same Application commands and read models used by Godot.

**Given** the scenario exercises invalid creation behavior
**When** it attempts representative empty identity input, overspent or incomplete point-buy, unknown preference, incompatible sprite selection, or repeated handoff
**Then** it asserts stable validation or typed Result reasons at the correct boundary
**And** canonical state remains unchanged after every rejected command.

**Given** ContentValidator runs against character-creation fixtures
**When** it checks identity fields, attributes, point-buy configuration, preferences, display keys, sprite parts, compatibility, and seeded protagonists
**Then** the report distinguishes syntax failures, design correctness failures, and design-scope warnings
**And** it warns about hard-locking preference rules, universally optimal attributes, missing low-profile routes, and unsupported MVP cosmetic expansion.

**Given** creation read-model snapshots are captured
**When** they are reviewed at 1080p and 720p/1280x800
**Then** they prove controller-first focus order, non-color selection and error states, readable expanded text, review navigation, and unobstructed sprite preview
**And** versioned baselines use controlled fonts, localization fixtures, viewport, and input glyph set and fail on differences outside an approved tolerance
**And** until a final Character Creation mockup is approved, snapshots cover every step, review/edit return, validation recovery, simultaneous focused/selected states, and longest supported localized labels at 1280x800
**And** broad point-buy and preference permutations remain in fast Domain/Application tests rather than expanding ScenarioRunner.

Coverage: FR1, FR21, FR22, FR23, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, NFR20, NFR21, NFR23, NFR24, NFR25, NFR26, NFR27, NFR28, NFR29, NFR30, UX-DR13, UX-DR14, UX-DR16, UX-DR21
Tags: FVS, Technical Enabler, UX

### Epic 5: Relationships, Classmates, Dialogue, and Story Beats

**Goal:** Player can meet the five MVP classmates, build relationships through everyday attention and authored milestones, make concrete dialogue choices, and experience Nell's partial arc with consequences that remain legible without exposing hidden relationship math.

**Epic 5 Test Evidence:** Epic 5 implementation must produce ContentValidator coverage for classmates, relationship rules, dialogue graphs, choice conditions/effects, beat gates, alternate access routes, acknowledgements, and intentional memory hooks; Domain/Application tests for Bond/tier progression, compatibility, dialogue runtime, stale or unavailable choices, effect atomicity/idempotency, beat eligibility, and hidden-information boundaries; ScenarioRunner evidence for one FVS dialogue/effects path, one gated beat, early classmate exposure, and Nell's representative three-beat path; and read-model snapshots proving concrete choices, controller metadata, non-color-only states, and no raw Bond or unrevealed gates.

**Epic 5 FVS Ordering:** The first relationship slice should prove one validated classmate/dialogue fixture, minimal hidden relationship state, one active `DialogueSession`, concrete choices with typed effects, one gated follow-up acknowledgement, and one intentional memory hook. Broad classmate content, all seven MVP relationship beats, Nell's complete partial arc, communication variants, and romance-path contracts should follow after that path works through the production Application commands.

### Story 5.1: Validated MVP Classmate and Relationship Fixture

As a developer,
I want a validated classmate, relationship, and dialogue fixture,
So that the first social path can be proven from authored content rather than hardcoded scenes.

**Acceptance Criteria:**

**Given** the FVS classmate catalog is validated
**When** ContentValidator checks it
**Then** it contains one fully authored classmate with stable IDs, display keys, compatibility inputs, schedule and location references, arc state definitions, supported story flags, and applicable orientation metadata
**And** the remaining four full catalog entries may be added with Story 5.8 before early-exposure validation.

**Given** the narrow FVS relationship fixture is selected
**When** validation runs
**Then** it may contain one classmate, one dialogue session, concrete choices, one gated acknowledgement, and one memory hook
**And** it defines the minimal typed effect transaction, acknowledgement, intentional memory-hook, and outcome-record contracts used by later stories
**And** it uses the same schemas and Application contracts as the full MVP catalog.

**Given** classmate or relationship content references an unknown ID, invalid tier, impossible schedule/location, unsupported condition/effect, missing display key, or contradictory romance rule
**When** ContentValidator runs
**Then** it fails with actionable source information
**And** invalid content cannot enter the runtime `ContentCatalog`.

**Given** Godot presents a classmate or social interaction
**When** it requests relationship content
**Then** Application read models expose only authorized display data and available intents
**And** scenes do not own Bond, gates, arc state, flags, or dialogue effects.

Coverage: FR24, FR27, FR28, FR46, FR47, FR49, FR50, FR58, FR60, NFR18, NFR20, NFR23, NFR24, NFR29, NFR30
Tags: FVS, Technical Enabler, Constraint

### Story 5.2: Relationship State and Player-Facing Tiers

As a player,
I want relationships to show recognizable levels of familiarity,
So that I can understand growing closeness without reducing classmates to visible score bars.

**Acceptance Criteria:**

**Given** a relationship is initialized
**When** canonical state is created
**Then** `RelationshipState` owns hidden Bond constrained to 0-100, familiarity tier/path state, arc state, discovered facts, and story flags
**And** authored profiles, schedules, preferred locations, orientation, and compatibility rules remain in validated `ContentCatalog` for Application to combine with canonical state.

**Given** relationship progression reaches its closest authored state
**When** path state is evaluated
**Then** visible familiarity progression ends at Close Friend, after which Best Friend and Romantic Partner may be mutually exclusive authored path states at the same progression level
**And** neither path is numerically superior or presented as the next sequential tier.

**Given** a relationship read model is requested
**When** the classmate is known to the player
**Then** it exposes the visible tier, a subtle qualitative progress cue where appropriate, discovered facts, and player-known relationship context
**And** it never exposes exact Bond, raw compatibility, hidden flags, thresholds, orientation-as-a-checklist, or unrevealed gates.
**And** any qualitative progress cue uses player-known interactions or milestones rather than acting as a disguised meter or reacting to undisclosed Bond changes.

**Given** a relationship effect attempts an unknown classmate, invalid tier transition, out-of-bounds Bond change, unsupported flag, or illegal path state
**When** Application evaluates it
**Then** it returns a typed Result error or content-validation failure at the appropriate boundary
**And** failed evaluation leaves Bond, tier/path state, flags, and projections unchanged.

**Given** relationship projections update or rebuild
**When** representative state changes are applied
**Then** incremental projections match a full rebuild from canonical state
**And** Godot presentation state is not persisted as relationship truth.

Coverage: FR24, FR26, FR27, FR38, FR58, FR60, NFR13, NFR18, NFR24, NFR27, NFR28, NFR30, UX-DR9, UX-DR18, UX-DR20, UX-DR21
Tags: FVS, MVP, UX

### Story 5.3: Systemic Relationship Interactions and Tier Progression

As a player,
I want everyday social effort to matter alongside important story moments,
So that closeness grows through attention rather than a single quest or repetitive grind.

**Acceptance Criteria:**

**Given** a validated repeatable interaction is available
**When** the player completes it
**Then** authored effects may update bounded Bond, Mood, discovered preferences, feedback, or flags according to compatibility and context
**And** content declares per-interaction Bond caps, repetition attenuation or cooldown, cooling interval, and maximum decay
**And** non-preferred interactions can still work with different tone or efficiency rather than becoming automatic failures.

**Given** a relationship reaches a numeric threshold
**When** tier progression is evaluated
**Then** Bond alone cannot advance the tier without required meaningful interactions, relevant authored beat state, and applicable contextual conditions
**And** MVP guidance expects approximately 2-3 meaningful interactions plus the relevant beat for Stranger-to-Acquaintance and 4-6 plus the relevant beat for Acquaintance-to-Friend
**And** validation warns when authored tuning materially bypasses those ranges without exposing exact counters to the player.

**Given** the player ignores a classmate for an authored long interval
**When** targeted relationship cooling is evaluated
**Then** it may stall or lightly reduce progress according to bounded content rules
**And** it does not create broad daily maintenance chores, severe decay, or punishment for maintaining only a few close bonds.

**Given** systemic progression rules are tested
**When** compatible, neutral, non-preferred, repeated, and long-gap fixtures are evaluated
**Then** fixtures assert exact expected Bond deltas, attenuation, cooling bounds, tier eligibility, and identical traces under identical clock, state, content version, and seed
**And** validation warns about spam-optimal interactions, instant tier jumps, excessive decay, or routes that require monotonous repetition.

Coverage: FR23, FR24, FR25, FR26, FR49, FR58, FR60, NFR21, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: MVP, Constraint

### Story 5.4: Active Dialogue Session and Concrete Choices

As a player,
I want conversations to present concrete choices and resolve consistently,
So that what I say can shape relationships and future moments without opaque or duplicated consequences.

**Acceptance Criteria:**

**Given** an eligible dialogue is started
**When** Application opens a `DialogueSession`
**Then** runtime mode, dialogue ID, node ID, speaker, player-visible text, available concrete choices, and allowed intents are Application-owned
**And** Godot only renders the read model and submits intents.

**Given** a dialogue choice is available
**When** the player selects it
**Then** Application first validates all conditions, targets, and effects and then atomically commits the complete effect set, node transition or terminal outcome, runtime-mode transition, and outcome record
**And** any failure leaves `GameState` and `DialogueSession` unchanged and emits no success projection.

**Given** a dialogue selection carries `DialogueSessionId`, source `NodeId`, `ChoiceId`, and a command idempotency key
**When** the same command is delivered again or its key is reused with different payload
**Then** an exact replay returns the recorded outcome or stable `AlreadyProcessed` Result without mutation
**And** conflicting reuse returns `CommandConflict` without applying effects or advancing dialogue.

**Given** a choice is unavailable, stale, already selected, from another session, or points to an inactive node
**When** selection is attempted
**Then** Application returns a stable typed Result reason code
**And** dialogue and canonical game state remain unchanged
**And** the player receives a readable reason while the current line and choices remain available and focus returns to the nearest valid choice.

**Given** an active dialogue is unresolved
**When** the player attempts travel, ordinary activity, manual save, another dialogue, or another unsafe command
**Then** the command is rejected according to Application runtime-mode policy
**And** Settings or explicitly allowed system actions remain reachable as defined elsewhere.

**Given** dialogue reaches a valid terminal node
**When** final effects complete
**Then** Application closes the session, records the outcome, refreshes affected read models, and returns to the appropriate world context
**And** replaying the terminal command cannot apply effects twice.

**Given** dialogue choices are displayed
**When** focus enters or the choice list refreshes
**Then** focus uses reading order and selects the first available or last stable valid choice, Confirm selects only the focused choice, and unavailable state is not color-only
**And** Back cannot silently abandon unresolved dialogue but may open an authored exit confirmation when quitting is allowed.

Coverage: FR46, FR47, FR49, FR52, FR53, FR55, FR58, FR60, NFR9, NFR10, NFR11, NFR24, NFR27, NFR28, NFR30, UX-DR8, UX-DR13, UX-DR14, UX-DR16, UX-DR21, UX-DR25
Tags: FVS, MVP, Technical Enabler, UX

### Story 5.5: Dialogue Effects, Branching, and Convergence

As a player,
I want dialogue choices to create meaningful but manageable consequences,
So that conversations acknowledge my tone and decisions without every line creating an unreachable branch.

**Acceptance Criteria:**

**Given** a dialogue choice is authored
**When** ContentValidator checks it
**Then** it uses concrete player-facing text, a valid target or terminal outcome, supported typed conditions/effects, and optional convergence or acknowledgement metadata
**And** dead targets, unreachable required nodes, unsupported effects, or missing feedback keys fail validation.
**And** choice text states the concrete line or action; abstract labels such as Nice, Mean, Flirt, or Risky cannot replace player-facing intent or preview hidden outcomes.

**Given** a choice resolves multiple tone, Bond, wellbeing, flag, memory, or future-acknowledgement effects
**When** any required effect cannot be applied
**Then** the choice fails atomically without applying earlier effects
**And** content-time defects and runtime-state rejection use their appropriate validation or typed Result boundary.

**Given** multiple dialogue choices express different tones
**When** branches resolve
**Then** they may create distinct immediate consequences and compact later acknowledgements while converging where authored
**And** convergence does not erase meaningful flags or falsely claim identical outcomes.

**Given** dialogue graphs are validated for solo-dev scope
**When** branch depth, variant count, repeated micro-lines, or unresolved paths exceed authored limits
**Then** ContentValidator emits design-scope warnings with source locations
**And** the system does not require a general scripting language or uncontrolled branching engine.

Coverage: FR46, FR47, FR49, FR51, FR58, FR60, NFR20, NFR23, NFR24, NFR27, NFR28, NFR29, NFR30
Tags: FVS, MVP, Constraint

### Story 5.6: Dialogue Variants Across Story and Communication Contexts

As a player,
I want conversations to fit their context,
So that important scenes, quick exchanges, messages, and recurring encounters feel distinct without behaving inconsistently.

**Acceptance Criteria:**

**Given** authored dialogue is used for a story beat, routine micro-dialogue, phone message, reflection line, bark, or compact variant
**When** it is validated
**Then** it uses the shared dialogue node, choice, condition, effect, acknowledgement, and visibility contracts needed for that context
**And** Epic 5 implements the shared semantic contract plus representative world/story and bark fixtures while phone, reflection, and presentation integrations wait for their owning epics
**And** presentation mode metadata remains descriptive rather than embedding Godot scene behavior.

**Given** a context does not support player choices
**When** a bark, message, or compact line is delivered
**Then** it can use a bounded no-choice or acknowledgement form without creating a full active conversation
**And** the read model identifies whether the player acknowledges, advances, or makes a consequential choice so acknowledgement controls are not presented as choices
**And** any gameplay effects still route through authorized Application handling.

**Given** large or small dialogue presentation is requested
**When** the read model is generated
**Then** Epic 5 supplies ordered speaker/text segments, choice IDs and labels, availability semantics, acknowledgement intent, semantic presentation mode, and controller-action metadata
**And** Epic 8 exclusively owns layout, typography, portrait/bubble treatment, focus visuals, animation, and responsive readability.

**Given** phone or message dialogue is used
**When** it is started or acknowledged
**Then** Epic 5 owns dialogue behavior and effects while Epic 6 owns phone navigation, thread access, and Back stack
**And** phone dialogue cannot remotely start an ordinary world activity.

Coverage: FR45, FR46, FR47, FR53, FR58, FR60, NFR18, NFR20, NFR23, NFR24, NFR28, UX-DR8, UX-DR16, UX-DR20, UX-DR21
Tags: MVP, Cross-Epic, UX, Constraint

### Story 5.7: Contextual Story-Beat Eligibility and Gating

As a player,
I want story moments to arise from my relationship and circumstances,
So that progression feels attentive and contextual rather than unlocked by filling one meter.

**Acceptance Criteria:**

**Given** an authored story beat is evaluated
**When** Application checks eligibility
**Then** it can combine Bond or tier, flags, calendar timing, location/context, availability, prior attention, wellbeing, attributes, club participation, and player choices as authored
**And** no major beat is unlocked by Bond alone.
**And** wellbeing and attributes normally modify tone, risk, options, or outcomes; any exceptional hard gate must be explicit, readable, and provide a fallback or later route.

**Given** a gate is not satisfied
**When** player-visible availability is generated
**Then** the read model exposes a specific blocked reason only when its cause is player-known and otherwise uses a generic unavailable cue
**And** it does not expose hidden Bond, raw flags, orientation rules, unrevealed schedules, or future story truth.

**Given** a previewed or previously eligible beat becomes stale
**When** the player attempts to start it after time, place, availability, flag, or relationship state changes
**Then** Application revalidates the gate and rejects an invalid start with a typed Result reason
**And** canonical state remains unchanged.

**Given** ContentValidator analyzes required MVP beats
**When** it evaluates gates and access routes
**Then** each required beat declares at least one witness route with prerequisite state, timing window, location, prior beats, and cross-epic fixture facts
**And** bounded reachability reports unknown references, impossible conjunctions, dead windows, circular prerequisites, or missing witnesses while unsupported external predicates are reported separately.

Coverage: FR24, FR25, FR29, FR49, FR50, FR58, FR60, NFR13, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR8, UX-DR9, UX-DR16, UX-DR20, UX-DR21
Tags: FVS, MVP, Technical Enabler, Constraint

### Story 5.8: Early Exposure and Intro Beats for All Five Classmates

As a player,
I want to encounter every core classmate early,
So that the semester presents meaningful social possibilities before I commit my limited attention.

**Acceptance Criteria:**

**Given** the MVP narrative fixture is validated
**When** early classmate exposure is analyzed
**Then** four non-Nell classmates each have one intro beat and Nell's Stranger beat serves as her required early exposure, producing exactly seven relationship beats with Nell's full three-beat partial arc
**And** each exposure has a timing window, context, discovery effect, and fallback or delayed route.
**And** Epic 5 owns exposure content, eligibility, and discovery effects while consuming epics surface opportunities through stable delivery contracts.

**Given** the player encounters an intro beat
**When** it resolves
**Then** it establishes the classmate's visible premise and records only player-known facts, relationship state, and authored consequences
**And** it does not reveal the full wound, orientation checklist, hidden compatibility, or complete future arc.

**Given** the player misses or declines an intro opportunity
**When** later exposure is evaluated
**Then** progression may be delayed, reframed, or use a bounded fallback
**And** missing an intro cannot block semester completion or silently make a required classmate unreachable.

**Given** early exposure is tested
**When** ScenarioRunner follows representative attended and missed-intro paths
**Then** all five classmates become discoverable within validated windows
**And** reachability evidence identifies the route and source content for each classmate.

Coverage: FR28, FR47, FR48, FR49, FR50, FR58, FR60, NFR20, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic

### Story 5.9: Nell Three-Beat MVP Partial Arc

As a player,
I want Nell's early story to respond to restraint, attention, and respect,
So that trust feels earned through how I treat her rather than through relationship grinding.

**Acceptance Criteria:**

**Given** Nell's MVP content is validated
**When** her partial arc is inspected
**Then** it contains a Stranger beat in Weeks 1-2, `A Piece With No Name` in Weeks 3-5, and `Zine Deadline Crisis` in Weeks 6-8
**And** the third beat opens future friendship possibility without resolving her full wound or full-game romance.

**Given** the player makes choices around rumor, private writing, or zine authorship
**When** Nell's effects and gates resolve
**Then** restraint, withheld judgment, boundary respect, authorship consent, and protection of private work can create distinct trust flags and acknowledgements
**And** invasive or careless choices create authored texture without severe arbitrary punishment or permanent semester failure.

**Given** any required Nell beat is approached
**When** access routes are evaluated
**Then** Literary Club membership may offer insider context, lower friction, or stronger variants
**And** every required beat, especially `Zine Deadline Crisis`, has a validated non-member route through library/cafeteria context, Social or message clues, invitation, public zine context, or a bounded trusted-outsider role.

**Given** Nell's arc is tested under deterministic fixtures
**When** respectful, careless, club-member, and non-member paths are executed
**Then** the declared respectful/careless by club/non-club by attended/missed-prior-opportunity matrix asserts applicable reachability, flags, acknowledgements, memory hooks, and visible tier cues
**And** raw Bond and hidden trust logic remain absent from player-facing projections.

Coverage: FR25, FR28, FR29, FR46, FR47, FR48, FR49, FR50, FR51, FR58, FR60, NFR20, NFR21, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic

### Story 5.10: MVP Narrative Beat Schedule and Cross-Epic Hooks

As a player,
I want relationship moments to occur within the semester's school and town life,
So that classmates feel woven into lessons, events, pressure, and changing routines.

**Acceptance Criteria:**

**Given** the MVP narrative schedule is validated
**When** relationship/story beats are counted
**Then** it contains exactly seven relationship beats: four non-Nell intro beats plus Nell's three-beat partial arc, whose Stranger beat is also her early introduction
**And** prologue, first-day onboarding, event, exam-pressure, exam-period, and reflection beats remain traceable to their owning epics.

**Given** a relationship beat depends on a lesson, club, event, phone clue, calendar window, or wellbeing context
**When** the integration is authored
**Then** Content stores validated condition/effect data using Domain/Ports vocabulary and Application interprets it
**And** Epic 5 validates external IDs and fixture capabilities without referencing Application handlers, Godot types, or scene resources
**And** consuming epics own production integration tests for lesson, event, phone, time, and reflection command paths.

**Given** the player misses an optional relationship beat
**When** later narrative state is evaluated
**Then** the miss can produce an intentional missed-opportunity or acknowledgement hook
**And** it does not become a hidden requirement for semester completion unless explicitly classified and validated as required.

**Given** narrative schedule reachability is analyzed
**When** ContentValidator processes representative semester fixtures
**Then** it detects impossible dates, conflicting required contexts, missing hooks, dead routes, and accidental dependence on unimplemented full-game content
**And** design-scope warnings identify excessive beat or variant growth.

Coverage: FR28, FR29, FR47, FR48, FR49, FR50, FR51, FR58, FR59, FR60, NFR20, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic, Constraint

### Story 5.11: MVP Romance Eligibility and Platonic-Path Constraints

As a player,
I want close relationships to respect each character's orientation and support meaningful friendship,
So that romance feels authored rather than collectible or mechanically superior.

**Acceptance Criteria:**

**Given** romance eligibility is defined for a classmate
**When** ContentValidator checks it
**Then** orientation, protagonist compatibility, relationship tier, story state, choices, exclusivity rules, and authored signals are internally consistent
**And** romanceability is not exposed as a starting badge or route checklist.

**Given** a close relationship is not romantically eligible or the player follows a platonic route
**When** later path state is evaluated
**Then** a meaningful Best Friend path remains available where authored
**And** Romantic Partner is a distinct highest-tier path state rather than a sixth tier above or better than friendship.

**Given** the player chooses an authored flirt or romantic signal
**When** it resolves
**Then** it may reveal chemistry, warmth, awkwardness, or gentle clarification according to character and context
**And** there is no generic repeatable Flirt action, spammable romance grind, severe rejection penalty, or generic breakup state machine.

**Given** MVP romance scope is enforced
**When** classmate content is validated
**Then** MVP implements eligibility metadata validation and bounded chemistry, warmth, awkwardness, or clarification signals only
**And** Nell remains focused on trust and emotional safety
**And** committed romance routes, exclusivity enforcement, generic commitment systems, and post-commitment arcs remain later implementation scope.

Coverage: FR25, FR26, FR27, FR28, FR46, FR47, FR49, FR58, FR60, NFR20, NFR21, NFR23, NFR24, NFR29
Tags: MVP, Full Game/Later, Constraint

### Story 5.12: Relationship Memories and Future Acknowledgement Hooks

As a player,
I want important social choices to be remembered and acknowledged later,
So that relationships feel continuous without turning the game into a quest log.

**Acceptance Criteria:**

**Given** a significant dialogue, beat, missed chance, boundary decision, relationship transition, or event interaction resolves
**When** authored effects are applied
**Then** they may create typed acknowledgement and intentional memory hooks with stable source, subject, visibility, significance, and player-facing keys
**And** ordinary lines and raw state changes do not automatically become memory records.

**Given** later dialogue eligibility or variants inspect prior choices
**When** acknowledgement hooks are queried
**Then** content can select bounded follow-up lines or gates without scanning a raw event log
**And** missing optional acknowledgements do not corrupt relationship progression.

**Given** player-facing relationship memory information is requested
**When** a read model is generated
**Then** it exposes only discovered facts, meaningful remembered moments, or contextual acknowledgements appropriate to that surface
**And** it does not become a task checklist, objective tracker, full world-history log, or preview of future beats.

**Given** Semester Reflection later consumes relationship inputs
**When** Epic 9 builds the final reflection
**Then** Epic 5 supplies typed relationship states, significant memories, missed chances, and identity-relevant social signals
**And** Epic 9 owns selection, interpretation, Memory Ledger composition, and presentation.

Coverage: FR24, FR25, FR46, FR47, FR48, FR49, FR51, FR59, FR58, FR60, NFR20, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: FVS, MVP, Cross-Epic, Technical Enabler

### Story 5.13: Relationship and Dialogue Scenario Evidence

Story Type: Evidence Gate

As a developer,
I want deterministic relationship and dialogue evidence,
So that social progression and authored consequences can be trusted before broad content expansion.

**Acceptance Criteria:**

**Given** the narrow FVS relationship fixture is available
**When** ScenarioRunner executes it
**Then** it starts one eligible dialogue, selects concrete choices, applies typed effects once, records a gated acknowledgement and memory hook, closes the session, and captures resulting relationship read models
**And** it uses the same Application commands as Godot.

**Given** the scenario exercises invalid dialogue behavior
**When** it attempts an unavailable choice, stale node, repeated selection, wrong session, unsafe concurrent command, or stale beat start
**Then** it asserts stable typed Result reasons
**And** canonical state remains unchanged after every rejection.

**Given** ContentValidator runs against relationship and dialogue content
**When** it checks classmate references, dialogue graphs, effects, gates, beat routes, early exposures, Nell's alternate access, romance rules, and hooks
**Then** it distinguishes syntax failures, design-correctness failures, reachability failures, and scope warnings
**And** it catches Bond-only major gates, dead dialogue targets, unreachable required beats, club-only core routes, and uncontrolled branching.

**Given** representative relationship scenarios are repeated with the same clock, content version, fixture IDs, and RNG seed
**When** results are compared
**Then** canonical summaries, typed Results, applied-effect traces, and player-facing read models are equivalent
**And** hidden Bond, raw flags, exact compatibility, and unrevealed gates are absent from snapshots.

**Given** versioned dialogue read-model baselines use fixed fixtures, clock, content version, localization, discovered-information state, viewport, and input glyph set
**When** snapshots run at 1080p and 720p/1280x800
**Then** they prove reading-order focus, stable Back behavior, non-color focused/selected/unavailable states, localized-text expansion, blocked-command recovery, and unclipped speaker text and choices
**And** newly exposed hidden values, unstable ordering, or missing controller/choice metadata fail approval.

**Given** Nell and early-exposure evidence is executed
**When** respectful, careless, missed-intro, club, and non-club fixture paths are sampled
**Then** ScenarioRunner covers the declared respectful/careless by club/non-club by attended/missed-prior-opportunity matrix where applicable
**And** broad graph/effect combinations remain in fast Content, Domain, and Application tests.

Coverage: FR24, FR25, FR26, FR27, FR28, FR29, FR45, FR46, FR47, FR48, FR49, FR50, FR51, FR58, FR60, NFR20, NFR23, NFR24, NFR25, NFR26, NFR27, NFR28, NFR29, NFR30, UX-DR8, UX-DR9, UX-DR13, UX-DR14, UX-DR16, UX-DR20, UX-DR21, UX-DR25
Tags: FVS, Technical Enabler, UX

### Epic 6: Smartphone, Planning, and Discovery

**Goal:** Player can use the smartphone as a controller-first hub for known commitments, messages, social clues, destinations, academics, settings, and safe system actions without exposing hidden world truth or remotely executing ordinary activities.

**Epic 6 Test Evidence:** Epic 6 implementation must produce Application tests for phone access policy, navigation and Back stack, focus restoration, notification routing, known-information filtering, travel handoff, and remote-activity rejection; read-model contract tests for Calendar, Map, Social, Messages, School App/Journal, Settings, and save/load entry points; ScenarioRunner coverage for a thin phone clue/profile/map/settings/save-legality path; and versioned snapshots at 1080p and 720p/1280x800 proving controller focus, adaptive glyphs, non-color state cues, text expansion, stable navigation, and hidden-information boundaries.

**Epic 6 FVS Ordering:** The first phone slice should prove the shell and context access policy, deterministic Back/focus behavior, one Social clue and classmate profile, one known Map destination with travel handoff, Settings access in a restricted mode, save-legality status, and rejection of a remote ordinary activity. Full Calendar breadth, message content, academic dashboards, and notification polish should follow that thin path.

### Story 6.1: Phone Shell, App Registry, and Context Access Policy

As a player,
I want to open a consistent smartphone hub,
So that I can reach appropriate information and system tools from different gameplay contexts.

**Acceptance Criteria:**

**Given** the player opens the phone in a normal safe context
**When** the phone shell read model is requested
**Then** it exposes registered entries for Calendar, Messages, Social, Map, School App/Journal, Settings, notifications, and save/load access
**And** app IDs, labels, semantic icon keys, stable display/grid-navigation metadata, availability, badges, and allowed intents come from validated configuration and Application state
**And** the Godot adapter resolves icon keys to presentation resources.

**Given** the current runtime mode is a lesson, exam, dialogue, action resolution, or another restricted context
**When** phone access is evaluated
**Then** Settings remains accessible while other apps are blocked by default unless the active context explicitly authorizes a bounded phone action
**And** focus enters Settings or the authorized action while unavailable apps cannot receive accidental Confirm activation
**And** the read model supplies a player-facing availability reason without exposing internal runtime state.

**Given** the player opens or closes the phone
**When** Application handles the intent
**Then** Application creates or discards a transient `PhoneNavigationSession` that is excluded from gameplay saves and never becomes canonical world state
**And** the phone behaves as the primary diegetic menu overlay outside direct activity selection
**And** opening it does not advance time, mutate gameplay state, or create a parallel persistent pause-menu truth.

**Given** any phone destination references an ordinary activity
**When** an owning-system command could be reached
**Then** one shared world-presence policy validates the intent first
**And** activity-start commands remain unavailable from the phone even before individual apps are implemented.

**Given** an unknown app, stale shell session, disallowed context, or unsupported navigation intent is submitted
**When** Application evaluates it
**Then** it returns a stable typed Result reason code
**And** phone navigation and canonical gameplay state remain unchanged.

Coverage: FR33, FR53, FR55, FR58, FR60, NFR9, NFR10, NFR11, NFR24, NFR27, NFR28, NFR30, UX-DR6, UX-DR13, UX-DR14, UX-DR15, UX-DR21, UX-DR25
Tags: FVS, MVP, Technical Enabler, UX

### Story 6.2: Phone Navigation, Back Stack, Focus Restoration, and Notifications

As a player,
I want predictable phone navigation and notifications,
So that I can inspect new information and return to where I was without losing orientation.

**Acceptance Criteria:**

**Given** the player enters an app, tab, detail surface, or cross-app destination
**When** phone navigation changes
**Then** `PhoneNavigationSession` records semantic destination IDs and stable content/item focus tokens up to `MAX_PHONE_DEPTH`
**And** it does not persist Godot node paths, screen instances, or raw focus controls as gameplay truth.

**Given** the player presses Back or app-level Home
**When** phone navigation handles the intent
**Then** Back removes exactly one semantic level, while Home always clears detail history and returns directly to the app grid
**And** focus restoration tries the prior stable item ID, then the clamped prior list index, then the owning section header, then the app primary control
**And** Back from the app grid closes the phone and restores world focus according to the calling context.

**Given** navigation targets the current destination or would exceed `MAX_PHONE_DEPTH`
**When** the command is evaluated
**Then** current-destination navigation adds no duplicate entry, while overflow returns `NavigationDepthExceeded`
**And** navigation and focus state remain unchanged after rejection.

**Given** the phone is closed and reopened
**When** no calling context authorizes a specific destination
**Then** transient detail history is discarded and the app grid reopens with its last valid semantic focus token restored
**And** unavailable entries do not destabilize authored grid order or directional movement.

**Given** a notification is created from an authorized gameplay source
**When** the phone shell or relevant app is queried
**Then** it exposes category, unread count, player-visible summary, timestamp/order, and a stable destination intent
**And** badges and unread states do not rely on color alone or reveal hidden content.

**Given** the player activates or clears a notification
**When** its destination is still available
**Then** Application revalidates the destination before atomically recording acknowledgement and pushing navigation
**And** opening marks it read without deleting it, while explicit dismissal updates badge counts idempotently and restores deterministic adjacent focus
**And** unavailable destinations return `NavigationTargetUnavailable`, preserve unread and Back-stack state, and expose a deterministic fallback intent.

**Given** phone navigation is displayed at target resolutions
**When** focus moves across app grid, tabs, lists, details, Back, and Home
**Then** focus order follows reading and primary decision paths, adaptive glyphs are present, and no core action requires a mouse
**And** selected, focused, unread, unavailable, and current-tab states remain distinguishable without color alone.

Coverage: FR33, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, NFR24, NFR28, NFR30, UX-DR5, UX-DR6, UX-DR13, UX-DR14, UX-DR15, UX-DR20, UX-DR21, UX-DR23
Tags: FVS, MVP, UX

### Story 6.3: Calendar of Known Commitments and Timed Clues

As a player,
I want the phone Calendar to summarize what I know about upcoming time pressure,
So that I can make informed choices without managing a formal productivity schedule.

**Acceptance Criteria:**

**Given** known calendar information is available
**When** the Calendar read model is generated
**Then** it can show known lessons, lunch, club meetings, exams, events, deadlines, commitments, and timed clues using the shared time/calendar vocabulary from Epic 1
**And** Epic 6 owns composition and visibility while owning epics supply typed projections
**And** MVP acceptance requires representative lesson, deadline, club/event, and timed-clue fixtures rather than full content breadth.

**Given** an event, deadline, or clue is hidden, uncertain, expired, missed, or only partially known
**When** Calendar content is projected
**Then** it displays only protagonist-known information and an appropriate visible state
**And** it does not expose hidden schedules, future flags, exact unavailable NPC truth, or unrevealed outcomes.

**Given** the player navigates Calendar dates, categories, or details
**When** focus and Back actions are used
**Then** focus remains predictable, selected date and focused item are distinct without color alone, and returning restores the prior semantic position
**And** essential timing and consequence text remains readable with 130% reference text at 720p/1280x800.

**Given** the player attempts to create, drag, move, optimize, or reserve a calendar slot
**When** the intent is evaluated
**Then** the Calendar offers no formal slot-planning command
**And** commitments are read-only projections of accepted authored meetings or system-known obligations that cannot be created, rescheduled, reserved, or optimized here.

Coverage: FR4, FR5, FR6, FR8, FR33, FR35, FR38, FR41, FR58, FR60, NFR13, NFR17, NFR18, NFR21, NFR24, NFR28, UX-DR4, UX-DR5, UX-DR6, UX-DR13, UX-DR14, UX-DR20, UX-DR21, UX-DR24
Tags: MVP, Cross-Epic, UX, Constraint

### Story 6.4: Map Destinations, Travel Feasibility, and Travel Handoff

As a player,
I want the phone Map to show reachable known destinations,
So that I can understand travel tradeoffs before committing to a journey.

**Acceptance Criteria:**

**Given** the player opens Map
**When** its read model is generated
**Then** it shows known destinations, travel time, feasibility, usable time before the next hard commitment, and known location services or objects
**And** all feasibility and time values come from Epic 1 policies rather than phone-specific calculations.

**Given** a destination is unknown, infeasible, restricted, or would violate a hard commitment or return rule
**When** Map projects it
**Then** visibility and blocked-reason behavior follow protagonist knowledge and shared travel reason codes
**And** the map does not expose hidden routes, schedules, live NPC radar, or unrevealed presence.

**Given** the player confirms a feasible destination
**When** Map submits the travel intent
**Then** the confirmation presents destination, travel time, usable time before the next hard commitment, and known restrictions, while Back cancels and restores destination focus
**And** the intent contains destination only; Epic 1 atomically commits logical time/location once and emits a scene-transition intent through a port
**And** Godot scene loading cannot replay travel cost if its adapter reports a recoverable presentation failure.

**Given** the destination becomes stale after preview or the travel command is rejected
**When** execution occurs
**Then** a typed Result and readable reason are returned, current location/time remain unchanged, and focus returns to the relevant destination
**And** no activity at the destination is started automatically.

**Given** travel succeeds or the same command ID is retried
**When** the handoff completes
**Then** the phone closes, its transient stack clears, world focus targets the destination entry, and the retry cannot consume time or travel twice
**And** services and objects remain informational until fresh world-presence validation after arrival.

Coverage: FR4, FR5, FR6, FR7, FR33, FR34, FR36, FR58, FR60, NFR9, NFR10, NFR13, NFR24, NFR27, NFR28, NFR30, UX-DR4, UX-DR5, UX-DR6, UX-DR13, UX-DR14, UX-DR16, UX-DR21
Tags: FVS, MVP, Cross-Epic, UX

### Story 6.5: Social Feed, Profiles, and Known Relationship Clues

As a player,
I want Social to collect public posts and discovered classmate information,
So that I can follow social clues without receiving complete answers about people or opportunities.

**Acceptance Criteria:**

**Given** Social information is requested
**When** feed and profile read models are generated
**Then** they can show public posts, discovered facts and preferences, public/event clues, descriptive relationship status, clubs, and player-known profile information
**And** every clue preserves source, discovery state, timestamp, and epistemic status such as public claim, observed fact, or confirmed preference
**And** Epic 6 wraps Epic 5 projections by stable source ID without copying or recomputing relationship truth.

**Given** a known classmate is eligible for a current-location clue
**When** Social projects the clue
**Then** it may show an authored protagonist-known observation that was accurate at its recorded discovery time
**And** it may become stale and never guarantees continued presence or availability after travel
**And** it does not expose full schedules, live radar, exact availability windows, hidden relationship math, or future beats.

**Given** the player follows a post or profile clue
**When** a destination intent is available
**Then** it may open a known profile, Map destination, Messages thread, or informational detail through the phone navigation contract
**And** it cannot start the related conversation, relationship beat, event, or ordinary activity remotely.

**Given** Social is controller-navigated
**When** the player moves among Feed, Profiles, Clubs, posts, facts, and contextual Message shortcuts
**Then** focus order, tabs, Back behavior, unread state, and selected/focused distinctions are explicit and non-color-only
**And** no Messages tab is duplicated inside Social.

Coverage: FR23, FR24, FR33, FR34, FR37, FR49, FR50, FR58, FR60, NFR13, NFR18, NFR24, NFR28, UX-DR5, UX-DR6, UX-DR13, UX-DR14, UX-DR15, UX-DR20, UX-DR21
Tags: FVS, MVP, Cross-Epic, UX, Constraint

### Story 6.6: Message Threads and Contextual Replies

As a player,
I want to read and reply to classmate messages,
So that communication can acknowledge relationships and story context without replacing in-person school life.

**Acceptance Criteria:**

**Given** the player opens Messages
**When** thread and message read models are generated
**Then** they show only discovered threads, ordered messages, unread state, speaker, timestamp/context, available acknowledgement or reply intents, and player-known consequences
**And** Epic 5 owns dialogue behavior/effects while Epic 6 owns thread navigation and delivery context.

**Given** the player reads or replies to an ordinary message
**When** the action resolves
**Then** it normally consumes no 15-minute block and may apply bounded authored relationship, tone, Mood, flag, or acknowledgement effects
**And** generic or systemic texting uses validated per-thread or per-character cooldowns and cannot grant unlimited repeatable Bond, Mood, or flag effects
**And** an authored texting beat uses the explicit timing, cost, and replay rules of its owning beat or activity.

**Given** a reply is unavailable, stale, already processed, or from another thread/session
**When** it is submitted
**Then** Application returns a stable typed Result reason, applies no effects, and preserves thread state
**And** the view restores focus to the nearest valid reply or acknowledgement.

**Given** the player reaches Messages through a profile shortcut or notification
**When** Back is used
**Then** the semantic Back stack returns to the originating profile or notification context
**And** Messages remains a standalone app rather than a nested Social tab.

Coverage: FR33, FR37, FR46, FR47, FR53, FR58, FR60, NFR9, NFR10, NFR18, NFR20, NFR24, NFR27, NFR28, NFR30, UX-DR5, UX-DR6, UX-DR13, UX-DR14, UX-DR16, UX-DR20, UX-DR21
Tags: MVP, Cross-Epic, UX

### Story 6.7: School App and Journal Integration

As a player,
I want a scannable school dashboard on my phone,
So that I can understand academic standing and upcoming pressure without reading a dense archive.

**Acceptance Criteria:**

**Given** Epic 3 academic projections are available
**When** School App/Journal read models are requested
**Then** they show subject grades, homework and deadlines, subject status, teacher notes, exam calendar, exam readiness, and applicable lesson/study cues
**And** Epic 6 wraps Epic 3 read models in phone navigation envelopes by stable source ID without duplicating, persisting, or recalculating academic truth.

**Given** some academic projections are not yet implemented or temporarily unavailable
**When** School App/Journal opens
**Then** it shows a stable unavailable or empty state for the affected section
**And** Epic 6 completion does not require every academic subsystem or content item.

**Given** academic information is hidden, uncertain, stale, or unavailable
**When** the dashboard is projected
**Then** it exposes only player-known qualitative standing and next-step cues
**And** it does not reveal raw formulas, exact hidden thresholds, teacher-impression numbers, or future results.

**Given** the same academic commitment appears in Calendar and School App/Journal
**When** both surfaces are queried
**Then** timing, wording, visibility, status, and severity remain consistent through shared source IDs
**And** Calendar remains time-oriented while School App/Journal remains academically oriented.

**Given** the player navigates subject, homework, teacher, and exam sections
**When** focus, tabs, details, and Back are used at target resolutions
**Then** essential grades, deadlines, and readiness cues remain readable and controller-operable
**And** the app does not become a quest log or formal planner.

Coverage: FR17, FR18, FR33, FR35, FR38, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR16, NFR18, NFR24, NFR28, UX-DR4, UX-DR5, UX-DR6, UX-DR9, UX-DR13, UX-DR14, UX-DR20, UX-DR21, UX-DR24
Tags: MVP, Cross-Epic, UX

### Story 6.8: Phone Settings, Controls, and Accessibility Entry

As a player,
I want Settings to remain reachable through the phone,
So that I can adjust the game even when other phone apps are restricted.

**Acceptance Criteria:**

**Given** any supported runtime mode including lesson, exam, active dialogue, or action resolution
**When** the player opens the phone or system overlay
**Then** Settings remains reachable unless a platform-critical interruption explicitly overrides input
**And** unavailable apps do not block access to controls, accessibility, audio, display, or other approved settings categories.

**Given** Settings is opened
**When** its entry read model is generated
**Then** it uses the phone app/navigation contract and exposes semantic categories, current values, allowed changes, Confirm/Back, and adaptive glyph metadata
**And** Epic 8 owns detailed control presentation, remapping UI, accessibility visuals, and responsive styling.

**Given** a setting is changed
**When** the command succeeds
**Then** the appropriate settings store updates independently from canonical gameplay save state
**And** gameplay time, phone information, relationship state, and current runtime mode remain unchanged.

**Given** a setting is unsupported, invalid, stale, or cannot be persisted
**When** the player confirms it
**Then** a readable error is shown, the previous value remains effective, and focus stays on the affected control
**And** the player can still navigate Back or restore defaults through an explicit confirmation where supported.

**Given** Settings contains unapplied changes
**When** the player presses Back
**Then** the surface offers Apply, Discard, or Continue Editing and restores focus to the originating setting
**And** focused, edited, applied, and invalid values remain distinguishable without color alone.

Coverage: FR33, FR53, FR54, FR55, FR56, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, NFR24, NFR27, UX-DR6, UX-DR13, UX-DR14, UX-DR15, UX-DR21, UX-DR22, UX-DR23
Tags: FVS, MVP, Cross-Epic, UX

### Story 6.9: Safe Save and Load Entry Points

Story Type: Player

As a player,
I want the phone to explain when saving or loading is available,
So that system actions feel predictable without risking transient gameplay state.

**Acceptance Criteria:**

**Given** the player opens save/load access in a safe context
**When** the phone requests capability status
**Then** it displays Application-provided availability, slot/checkpoint summaries, primary intents, and player-facing reason metadata
**And** Epic 6 owns capability display and command routing only while Epic 9 exclusively owns FR52 execution, FR53 legality policy, serialization, slots, loading, checkpoints, and recovery.

**Given** runtime mode is a lesson, exam, action resolution, active dialogue, or another unsafe state
**When** manual save availability is requested
**Then** save is unavailable with a readable reason while Settings remains reachable
**And** the phone does not infer legality from visible Godot scenes or controls.

**Given** the player submits an allowed save or load intent
**When** Epic 9 Application handlers are available
**Then** the phone submits Application save/load commands and presents their Result while infrastructure adapters alone access storage through ports
**And** absent handlers return a stable `Unavailable` capability without blocking completion of the phone entry surface.

**Given** capability state changes after preview
**When** the player confirms the system action
**Then** Epic 9 revalidates execution and may return a typed rejection
**And** stale or unavailable commands perform zero storage reads/writes, preserve slot metadata and gameplay state, and refresh capability status from the typed reason.

Coverage: FR33, FR52 (supporting), FR53 (supporting), FR58, FR60, NFR4, NFR9, NFR10, NFR24, NFR27, NFR28, UX-DR6, UX-DR13, UX-DR14, UX-DR16, UX-DR21
Tags: FVS, MVP, Cross-Epic, UX

### Story 6.10: Information Visibility and Non-Remote-Activity Enforcement

As a player,
I want phone clues to guide me back into the world,
So that discovery supports presence and attention instead of completing school life from a menu.

**Acceptance Criteria:**

**Given** any phone app requests calendar, academic, social, location, relationship, event, or activity-related information
**When** Application builds the read model
**Then** every field follows an explicit player-knowledge and visibility rule
**And** every projected field declares a source ID and visibility classification so ContentValidator rejects hidden-only sources in player-visible mappings
**And** runtime filtering handles knowledge that changes with canonical state while full world truth, hidden schedules, raw flags, exact Bond, unrevealed gates, and live NPC radar remain absent.

**Given** a post, profile, message, Calendar clue, Map detail, notification, or School App cue references an ordinary activity
**When** the player activates it
**Then** all destination intents pass through the shared world-presence policy before any owning-system command executes
**And** the phone may navigate to information, expose a destination, or hand off a travel intent
**And** it cannot execute the activity without validated world presence at its NPC, object, location, or event.

**Given** a remote ordinary-activity intent is submitted through any phone surface
**When** Application evaluates it
**Then** it returns a stable `RequiresWorldPresence` or equivalent typed reason
**And** payload substitution into travel-and-start, activity-start, lesson, club, event, or object-interaction commands is rejected before effects
**And** the response may offer Map information or travel but never presents the activity as executable from the phone.

**Given** an explicitly authored message or contextual phone action applies dialogue effects
**When** it resolves
**Then** it uses an allowlisted dialogue/message effect family rather than the ordinary activity path
**And** it cannot invoke ordinary activity commands or grant their full time, resource, academic, club, or relationship rewards.

**Given** visibility and remote-action policies are tested
**When** representative known, unknown, stale, misleading, and hidden fixtures are evaluated
**Then** read-model snapshots contain only permitted knowledge and rejected commands preserve canonical state
**And** ContentValidator catches phone clues that reveal hidden truth or point to impossible world routes.

Coverage: FR33, FR34, FR35, FR36, FR37, FR38, FR49, FR58, FR60, NFR13, NFR17, NFR24, NFR27, NFR28, NFR29, NFR30, UX-DR4, UX-DR5, UX-DR6, UX-DR16, UX-DR20, UX-DR21, UX-DR24
Tags: FVS, MVP, Cross-Epic, Constraint

### Story 6.11: Smartphone Scenario and UX Evidence

Story Type: Evidence Gate

As a developer,
I want deterministic smartphone scenario and navigation evidence,
So that the information hub can be trusted before every app receives full content breadth.

**Acceptance Criteria:**

**Given** the thin FVS phone fixture is available
**When** ScenarioRunner executes it
**Then** it opens the app grid, follows one Social clue into a profile, confirms one feasible Map destination, verifies Epic 1 applies expected time/location travel results without starting a destination activity, checks Settings in a restricted context, and inspects save-legality status
**And** every action uses production Application commands and read models.

**Given** the scenario tests navigation history
**When** it crosses apps, opens details, uses Back/Home, closes/reopens the phone, or encounters a stale destination
**Then** semantic destinations and focus restoration are deterministic
**And** no Godot node path or presentation object is required by the test.

**Given** the scenario attempts a remote ordinary activity, hidden-information query, unavailable app, stale navigation target, or unsafe save
**When** Application evaluates each attempt
**Then** stable typed Result reasons and readable fallback metadata are produced
**And** canonical gameplay and valid phone navigation state remain unchanged.

**Given** versioned phone snapshots use fixed fixture IDs, clock, content version, localization, viewport, input glyph set, and known-information state
**When** ScenarioRunner and the separate controller/readability harness run
**Then** ScenarioRunner captures deterministic serialized navigation/read-model content and ordering
**And** focused Godot smoke/visual baselines at 1080p and 720p/1280x800 prove focus rendering, Back/Home behavior, non-color states, fixed glyphs/fonts, 130% text expansion, and unclipped core information
**And** newly exposed hidden truth, unstable ordering, remote activity actions, or missing controller metadata fail approval.

**Given** broader phone behavior is tested
**When** Calendar, Messages, Social, Map, School App/Journal, Settings, notification, and save-entry contracts run in fast tests
**Then** each app proves known-information filtering, navigation intents, unavailable states, and cross-epic source consistency
**And** ScenarioRunner remains a narrow representative path rather than enumerating every app item.

Coverage: FR33, FR34, FR35, FR36, FR37, FR38, FR52, FR53, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR17, NFR18, NFR24, NFR25, NFR26, NFR27, NFR28, NFR29, NFR30, UX-DR4, UX-DR5, UX-DR6, UX-DR13, UX-DR14, UX-DR15, UX-DR16, UX-DR20, UX-DR21, UX-DR23, UX-DR24
Tags: FVS, Technical Enabler, UX

### Epic 7: Locations, Clubs, Events, and Off-School Life

**Goal:** Player can inhabit a bounded school-and-town world, discover interactions through physical presence, join recurring clubs, and choose how to spend limited time and attention during authored events without turning locations, clubs, or money into separate grind systems.

**Epic 7 Test Evidence:** Epic 7 implementation must produce ContentValidator coverage for location graphs, interaction anchors, club schedules, membership routes, fixed-event windows, partial attendance, member/non-member access, event choices, and cross-epic IDs; Application tests for presence checks, stale availability, club attendance, event entry/exit, fixed endings, and typed rejections; ScenarioRunner evidence for one FVS travel-to-presence journey, one weekly club meeting, Harvest partial attendance, and member/non-member Zine routes; and read-model snapshots proving known location context, readable event pressure, controller focus, and non-color-only states.

**Epic 7 FVS Ordering:** The first slice should prove one validated origin/destination fixture, Epic 1 travel, arrival at a logical location, discovery of one interaction anchor, Epic 2 `ActivityOccasion` handoff, and rejection when world presence is absent. Campus/off-school breadth, club membership, recurring meetings, and authored special events should follow only after that proof journey works.

### Story 7.1: Validated Location, Club, and Event Fixture

As a developer,
I want validated location, club, and event content,
So that world opportunities can be authored without embedding gameplay rules in scenes.

**Acceptance Criteria:**

**Given** the narrow FVS fixture is validated
**When** ContentValidator runs
**Then** it contains an origin, destination, travel reference, logical location ID, interaction anchor, one activity reference, and player-facing keys
**And** it uses the same location and availability contracts as full MVP content.

**Given** MVP location content is validated
**When** catalog publication is attempted
**Then** Story 7.1 requires only the narrow origin/destination/activity fixture and extensible schemas
**And** full location, club, and event catalog validation is completed incrementally by Stories 7.3-7.11
**And** Godot scene/resource keys remain presentation metadata rather than canonical location truth.

**Given** a location graph is validated
**When** routes and required destinations are inspected
**Then** unknown endpoints, self-links, duplicates, non-positive durations, unmarked asymmetry, and unreachable required locations fail validation
**And** every required destination declares a witness path from an approved origin fixture.

**Given** club or event content is validated
**When** references are inspected
**Then** schedules, durations, membership rules, entry routes, fixed windows, choices, effects, and cross-epic IDs resolve
**And** unknown IDs, impossible windows, dead routes, unsupported effects, or missing feedback fail with source information.

**Given** content scope exceeds MVP boundaries
**When** validation detects large shop catalogs, employment schedules, minigames, broad town simulation, excessive club progression, or event-space proliferation
**Then** it emits design-scope warnings
**And** the minimal FVS fixture remains independently runnable.

Coverage: FR30, FR31, FR32, FR39, FR40, FR41, FR42, FR43, FR44, FR58, FR60, NFR20, NFR23, NFR24, NFR29, NFR30
Tags: FVS, Technical Enabler, Constraint

### Story 7.2: World Presence and Location Interaction Discovery

As a player,
I want opportunities to appear when I am physically present,
So that school and town life rewards showing up rather than selecting everything from menus.

**Acceptance Criteria:**

**Given** the player arrives at a logical location
**When** Application builds its location read model
**Then** it exposes known interaction anchors, services, objects, NPC opportunities, exits, and allowed intents for current state
**And** Godot renders that model without deciding availability or mutating location truth.

**Given** an interaction requires a location, object, NPC, event, or presence context
**When** the player requests its `ActivityOccasion`
**Then** Application returns an occasion ID tied to activity, logical location, anchor, availability/state revision, and relevant event session
**And** confirmation revalidates canonical state through Epic 2's production activity command
**And** previews are non-authoritative and successful occasions cannot be consumed twice.

**Given** presence, time, availability, flag, or event context changed after discovery
**When** the interaction is confirmed
**Then** stale requests return a stable typed reason and canonical state remains unchanged
**And** the prompt supplies action/target labels, adaptive glyph, availability, and concise known requirements with non-color state cues
**And** focus returns to the refreshed anchor, nearest valid neighbor, or primary exit when the anchor disappears.

**Given** the same ordinary interaction is requested from a phone clue or remote context
**When** world presence is absent
**Then** it returns `RequiresWorldPresence` or an equivalent typed Result
**And** it may expose travel information but cannot execute the activity.

**Given** logical travel has committed but Godot cannot load the presentation scene
**When** the scene adapter reports failure
**Then** Application logical location remains authoritative and travel cost is not replayed
**And** a recoverable infrastructure result is exposed without scene state becoming world truth.

Coverage: FR11, FR12, FR34, FR39, FR40, FR58, FR60, NFR13, NFR24, NFR27, NFR28, NFR30, UX-DR7, UX-DR8, UX-DR16, UX-DR21, UX-DR25
Tags: FVS, MVP, Cross-Epic, Technical Enabler

### Story 7.3: MVP Campus Location Roles

As a player,
I want school spaces to offer distinct everyday opportunities,
So that campus feels like a lived place rather than a list of interchangeable rooms.

**Acceptance Criteria:**

**Given** the MVP campus catalog is validated
**When** its roles are inspected
**Then** it supports dorm/private room, outdoor/courtyard, hallway/common area, classrooms, cafeteria, library/study room, Science Club room, and Literary Club room
**And** event-specific school spaces may be added only when required by approved content.

**Given** a campus location is entered
**When** its read model is generated
**Then** its authored anchors reflect bounded roles such as rest, study, lessons, lunch, quick social contact, rumors, club participation, or story context
**And** each anchor connects to at least one approved gameplay pillar.

**Given** an opportunity belongs to another epic
**When** it appears at a campus anchor
**Then** the location supplies presence and context while the owning epic supplies lesson, activity, dialogue, relationship, or consequence behavior
**And** location content does not duplicate those rules.

**Given** campus fixtures are tested
**When** representative school-day states are projected
**Then** unavailable, restricted, discovered, and stale anchors are deterministic and player-readable
**And** no location becomes an all-purpose optimal hub.

Coverage: FR30, FR31, FR39, FR40, FR41, FR58, FR60, NFR20, NFR21, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic, Constraint

### Story 7.4: Off-School Locations and Direct Travel Network

As a player,
I want nearby town locations to support a small life beyond campus,
So that free time can include recovery, errands, study, and social moments without a sprawling town simulation.

**Acceptance Criteria:**

**Given** off-school MVP locations are validated
**When** the travel graph is inspected
**Then** it includes cafe, park, convenience store, and dorm/private-room anchors
**And** cafe, park, and convenience store support direct 15-minute travel between one another without mandatory campus return
**And** dorm/private room references the same canonical location ID and anchors used by campus/home projections.

**Given** the player views or enters an off-school location
**When** its read model is generated
**Then** cafe supports bounded social/study/comfort roles, park supports recovery/walk/conversation roles, and convenience store supports limited purchase/errand roles
**And** the dorm remains the home/rest/private anchor.

**Given** travel is requested
**When** Epic 1 evaluates route, time, commitments, and return constraints
**Then** location content supplies stable endpoints and route metadata only
**And** Epic 7 does not duplicate feasibility or time-cost policy.

**Given** future town content is considered
**When** it is outside approved MVP spaces
**Then** it remains later scope unless needed by an approved event
**And** the MVP network stays bounded and testable.

Coverage: FR6, FR7, FR34, FR36, FR39, FR40, FR58, FR60, NFR20, NFR21, NFR23, NFR24, NFR29, NFR30
Tags: MVP, Cross-Epic, Constraint

### Story 7.5: Off-School Services, Purchases, Errands, and Odd Jobs

As a player,
I want modest off-school services and ways to earn or spend pocket money,
So that town choices create texture and tradeoffs without becoming an economy simulator.

**Acceptance Criteria:**

**Given** off-school service content is validated
**When** cafe, park, store, or dorm anchors are inspected
**Then** MVP provides one representative purchase, one gift or preparation item, one errand, and one limited odd job alongside bounded hangout/recovery/study roles
**And** Epic 2 owns activity preview, affordability, wellbeing, money, and consequence resolution.

**Given** a purchase or odd job is available
**When** the player requests it in world presence
**Then** its occasion exposes known time, Energy, money, reward, and context through shared activity contracts
**And** confirmation revalidates presence, affordability, feasibility, and availability.

**Given** an odd job resolves
**When** authorized effects are applied
**Then** it uses an approved 60- or 90-minute activity duration; longer authored work is represented as explicit sequential occasions revalidated between segments
**And** bounded availability or cooldown keeps rewards secondary to allowance and preserves opportunity cost
**And** it does not create recurring employment schedules, career progression, or a primary relationship route.

**Given** service catalogs are validated for scope
**When** item, price, reward, or repetition patterns are analyzed
**Then** warnings identify large economies, dominant money loops, unlimited reward exploits, or irrelevant item growth
**And** normal living costs remain abstracted.

Coverage: FR13, FR23, FR34, FR39, FR40, FR58, FR60, NFR20, NFR21, NFR22, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic, Constraint

### Story 7.6: Science and Literary Club Membership and Weekly Meetings

As a player,
I want to join recurring school clubs,
So that my schedule, social circle, and emerging identity can reflect sustained interests.

**Acceptance Criteria:**

**Given** MVP club content is validated
**When** clubs are inspected
**Then** Science Club and Literary Club each define membership, a weekly 60-minute meeting, club-room context, known calendar projection, interaction roles, and one 90-minute special event
**And** each provides at least one recurring social context and one authored story opportunity routed through Epic 5 contracts
**And** schedules align with Epic 1 commitments and travel feasibility.

**Given** the player joins an eligible club
**When** membership is confirmed
**Then** preview shows club name, known weekly time, duration, location, and non-punitive attendance expectations
**And** canonical state records membership and exposes future known meetings, club context, and authored opportunities
**And** joining does not instantly grant attendance, relationship tiers, event outcomes, or broad progression.

**Given** a weekly meeting occurs
**When** the player attends in the required context
**Then** one command identified by member, club, meeting occurrence, and idempotency key atomically commits time/resources, Epic 2 consequences, attendance, and typed identity/story hooks
**And** replay returns the recorded Result without duplicate cost/effects while conflicting key reuse returns `CommandConflict`
**And** meetings use a small rotating set of contextual roles so repeated identical choices cannot remain universally optimal.

**Given** a membership or attendance command is stale, duplicate, ineligible, remote, or conflicts with a hard commitment
**When** Application evaluates it
**Then** it returns a stable typed Result and leaves state unchanged
**And** player-facing feedback preserves the known cause without exposing hidden story gates.

Coverage: FR30, FR31, FR32, FR34, FR35, FR39, FR41, FR58, FR60, NFR13, NFR20, NFR21, NFR23, NFR24, NFR27, NFR28, NFR30
Tags: MVP, Cross-Epic

### Story 7.7: Club Participation Without Grind or Relationship Hard Gates

As a player,
I want club membership to enrich opportunities without controlling my friendships,
So that clubs feel meaningful but remain one viable lifestyle among several.

**Acceptance Criteria:**

**Given** club participation influences content
**When** an authored gate or effect is evaluated
**Then** membership or attendance may provide variants, insider context, preparation, lower friction, stronger outcomes, identity signals, or additional flags
**And** those benefits are bounded and causally visible.

**Given** an essential classmate beat is associated with a club
**When** reachability is validated
**Then** at least one non-member route exists through invitation, public context, guest/helper role, clue, or bounded trusted-outsider task
**And** membership cannot be the sole hard gate for core relationship progression.

**Given** club progression is tuned
**When** attendance and reward patterns are analyzed
**Then** validation warns about mandatory attendance streaks, repetitive optimal loops, independent skill trees, or disproportionate penalties
**And** club identity emerges from lived participation rather than a separate grind meter.

**Given** member and non-member variants are tested
**When** representative routes execute
**Then** both reach the essential beat while member context may alter preparation, dialogue, friction, wellbeing, or outcomes
**And** neither route bypasses other required relationship/story conditions.

**Given** essential club/event reachability is validated
**When** member and non-member access is required
**Then** bounded reachability uses explicit fixture facts and declared external capabilities with at least one witness for each required route
**And** indeterminate external predicates are reported separately before ScenarioRunner proves representative production routes.

Coverage: FR25, FR29, FR30, FR31, FR32, FR44, FR49, FR58, FR60, NFR20, NFR21, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic, Constraint

### Story 7.8: Fixed Events, Attendance Windows, and Partial Participation

As a player,
I want major events to occur on meaningful fixed dates,
So that choosing whether and when to attend creates memorable opportunity cost.

**Acceptance Criteria:**

**Given** the MVP event calendar is validated
**When** fixed and special events are inspected
**Then** it includes Class Integration Day, Harvest Evening, Science Showcase Sprint, Zine Deadline Crisis, and Week 20 exam-period references
**And** each event defines owning epic, date/window, location, discovery rule, entry routes, fixed ending, and missed-state behavior.

**Given** Class Integration Day occurs in Week 1
**When** the player attends
**Then** it provides a bounded event session with limited social-attention choices, early classmate and club clues, fixed entry/exit rules, and typed relationship/identity hooks
**And** Epic 5 owns resulting dialogue and relationship effects.

**Given** an event is entered
**When** Application creates its runtime
**Then** Application owns an `EventSession` with occurrence/session ID, arrival time, fixed end, participation state, completed choices, available intents, and runtime mode
**And** Godot only renders it while entry, choice, exit, re-entry, and expiry commands revalidate and transition atomically.

**Given** an event supports partial attendance
**When** the player arrives after its start but before its fixed ending
**Then** remaining event time and reachable choices derive from actual arrival time
**And** entry confirms window, arrival, remaining duration, location, and known commitment risk while Back cancels
**And** the event never extends its ending to compensate for late arrival.

**Given** an event choice has an authored duration
**When** its full duration cannot fit before the fixed ending
**Then** it returns `InsufficientEventTime` unless explicitly interruptible with defined partial effects
**And** rejected actions change no state and the event never extends.

**Given** an event is missed, declined, ended, unavailable, or stale
**When** entry is attempted
**Then** Application returns a typed reason and may record an intentional missed-opportunity hook
**And** it does not retroactively execute content or block semester completion unless explicitly classified as required.

**Given** event content belongs partly to another epic
**When** it resolves
**Then** one Application command atomically commits authorized time/resources, activity consequences, event progression, and typed cross-feature effects
**And** any failure leaves complete canonical state unchanged without feature-to-feature gameplay event buses.

Coverage: FR6, FR8, FR35, FR39, FR41, FR42, FR43, FR44, FR48, FR58, FR60, NFR13, NFR20, NFR23, NFR24, NFR27, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic, Constraint

### Story 7.9: Harvest Evening Choice Experience

As a player,
I want to spend a limited autumn evening among stalls and classmates,
So that time, money, preferences, and social attention create a personal event memory.

**Acceptance Criteria:**

**Given** Harvest Evening content is validated
**When** its fixture is inspected
**Then** it is announced through a discoverable clue in Weeks 7-9 and scheduled around Week 8-9 with a fixed 18:00-21:00 window
**And** it uses one bounded event space with validated maximum counts for stalls, interactions, purchasable items, and actionable opportunities
**And** it contains no minigame, crowd simulation, or large vendor economy.

**Given** the player enters Harvest Evening
**When** event choices are projected
**Then** they can include small food/gift purchases, preference-aligned comfort, brief encounters, social clues, and bounded Mood/Stress or memory effects
**And** known time, money, and social tradeoffs are readable without revealing hidden outcomes.

**Given** the player cannot visit every opportunity
**When** time advances or the fixed ending arrives
**Then** at least two desirable opportunities cannot both fit in the available event window, remaining options expire deterministically, and intentional missed contact may be recorded
**And** the result does not frame incomplete attendance as failure.

**Given** event actions resolve
**When** multiple authorized effects apply
**Then** Application atomically interprets validated effects and updates owning Domain state, event progression, and typed hooks
**And** rejected or stale actions leave event and canonical state unchanged without feature-to-feature calls.

Coverage: FR13, FR23, FR39, FR40, FR41, FR42, FR49, FR51, FR58, FR60, NFR13, NFR20, NFR21, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic

### Story 7.10: Science Showcase Sprint Choices

As a player,
I want to help the Science Club under time pressure,
So that my contribution reflects academics, teamwork, and wellbeing without becoming a separate minigame.

**Acceptance Criteria:**

**Given** Science Showcase Sprint is validated
**When** its event fixture is inspected
**Then** it is an optional 90-minute Science Club event scheduled in Weeks 6-10 and announced early enough for preparation tradeoffs
**And** it has bounded choices for research, materials/setup, presentation, team support, and fixing a last-minute issue
**And** it references Logic, Creativity, Discipline, and Charisma only through typed attribute/academic influence contracts.

**Given** the event is entered
**When** choices are generated
**Then** member preparation may add context, lower friction, or variants while eligible guest/helper access can be authored where required
**And** choices communicate known academic, club, relationship, and wellbeing intent without exact formulas.

**Given** a choice resolves
**When** Epic 2/3 policies apply effects
**Then** Epic 7 owns event window, presence, participation state, and choice routing; Epic 3 owns choice evaluation and academic outcomes; Epic 2 owns shared activity costs and wellbeing effects
**And** one Application transaction commits the resulting state atomically.

**Given** Science Showcase content is tested
**When** representative prepared, unprepared, member, and helper fixtures execute
**Then** outcomes remain deterministic, bounded, and reachable without a minigame
**And** no single attribute or role is always optimal.

Coverage: FR13, FR22, FR30, FR31, FR32, FR39, FR41, FR43 (supporting), FR49, FR58, FR60, NFR20, NFR21, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic, Constraint

### Story 7.11: Zine Deadline Crisis and Non-Member Route

As a player,
I want to help during the zine deadline while respecting authorship and boundaries,
So that participation tests attention and consent rather than club membership alone.

**Acceptance Criteria:**

**Given** Zine Deadline Crisis is validated
**When** its fixture is inspected
**Then** it is a 90-minute Literary Club event with bounded choices around editing, layout, practical errands, emotional support, public reading, authorship, consent, reputation, and protecting boundaries
**And** choices may use Creativity, Empathy, Discipline, and Charisma through bounded typed influence without hard-locking essential participation
**And** it connects to Nell's approved Week 6-8 beat through stable Epic 5 contracts.

**Given** event eligibility is evaluated
**When** the protagonist is not a Literary Club member
**Then** a validated invitation, public context, guest/helper, clue, or trusted-outsider route can reach the essential event/relationship content
**And** Epic 7 guarantees member/non-member event entry while Epic 5 exclusively owns Nell beat eligibility, dialogue, trust flags, and relationship consequences.

**Given** the player handles private or misattributed writing
**When** choices resolve
**Then** restraint, consent, attribution, protection, and careless exposure produce distinct authored flags, acknowledgements, and bounded relationship/wellbeing effects
**And** hidden trust or Bond values remain absent from player-facing feedback.

**Given** member/non-member and respectful/careless fixtures are executed
**When** reachability and outcomes are compared
**Then** every declared route reaches its intended essential content and applies deterministic consequences
**And** no route requires a publishing minigame or broad club-management system.

Coverage: FR25, FR29, FR30, FR31, FR32, FR39, FR41, FR44, FR46, FR48, FR49, FR51, FR58, FR60, NFR20, NFR21, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: MVP, Cross-Epic, Constraint

### Story 7.12: Location, Club, and Event Scenario Evidence

Story Type: Evidence Gate

As a developer,
I want deterministic world, club, and event scenarios,
So that presence, attendance, and authored event routes remain trustworthy as content expands.

**Acceptance Criteria:**

**Given** the FVS location fixture is available
**When** ScenarioRunner executes the proof journey
**Then** Epic 1 travels from origin to destination, Application commits logical location, one interaction anchor becomes visible, and Epic 2 receives one valid `ActivityOccasion`
**And** repeating the request remotely or with stale presence returns typed errors without state mutation.

**Given** the club fixture is executed
**When** membership, one weekly meeting, missed attendance, and a repeated command are tested
**Then** schedule, duration, attendance, effects/hook routing, and idempotency match authored expectations
**And** no grind meter or severe maintenance penalty appears.

**Given** event scenarios execute
**When** Harvest late arrival/fixed ending, Science choices, and Zine member/non-member routes are sampled
**Then** entry windows, remaining time, choices, effects, missed opportunities, and essential reachability are deterministic
**And** each scenario uses production Application commands rather than a parallel simulator.
**And** event scenario evidence is required incrementally as each owning event story enters scope: Harvest late arrival/fixed ending for 7.9, Science choice sampling for 7.10, and Zine member/non-member routes for 7.11; Story 7.12 FVS completion requires only the world-presence proof journey from AC1.

**Given** ContentValidator runs against location, club, and event fixtures
**When** graphs, anchors, schedules, routes, windows, choices, and cross-epic references are checked
**Then** it reports syntax, reference, reachability, design-correctness, and scope failures separately
**And** catches remote activity paths, impossible event windows, club-only core beats, uncontrolled shops/jobs, and minigame dependencies.

**Given** read-model evidence is captured
**When** fixed fixtures are projected
**Then** snapshots show logical location, known anchors, event time remaining, membership/attendance context, available choices, controller metadata, and readable blocked reasons
**And** versioned 1080p and 720p/1280x800 baselines use fixed IDs, clock, content, localization, and ordering to prove focus/Back/exit, stale-action focus recovery, 130% text expansion, and non-color states
**And** hidden schedules, raw relationship values, unrevealed gates, unstable ordering, and Godot scene state remain absent.

Coverage: FR30, FR31, FR32, FR34, FR39, FR40, FR41, FR42, FR43, FR44, FR58, FR60, NFR20, NFR23, NFR24, NFR25, NFR26, NFR27, NFR28, NFR29, NFR30, UX-DR7, UX-DR8, UX-DR9, UX-DR13, UX-DR14, UX-DR16, UX-DR20, UX-DR21, UX-DR25
Tags: FVS, Technical Enabler, UX

### Epic 8: Player-Facing Presentation, Operability, Art, and Audio

**Goal:** Player can navigate every MVP surface, read choices and consequences, and experience the school-life loop through coherent controller-first presentation, bounded pixel-art assets, and responsive audio without moving gameplay authority into Godot views.

**Epic 8 Test Evidence:** Epic 8 must produce component/read-model adapter tests for shared focus, Back, glyph, HUD, prompt, phone, lesson, dialogue, feedback, and accessibility contracts; focused Godot smoke/visual baselines at 1080p and 720p/1280x800 with fixed fonts, localization, input glyphs, and state fixtures; canvas/frame evidence that primary scenes are nonblank, correctly framed, and free of incoherent overlap; and representative performance evidence for exploration, phone, lesson, dialogue, travel, and transitions.

**Epic 8 FVS Ordering:** The first presentation slice should establish tokens/components, controller/focus/Back/glyph handling, one exploration HUD and prompt, one activity confirmation/result, one phone surface, one lesson surface, one dialogue surface, and a Godot smoke journey over the existing Application read models. Broader art and audio production should follow after this operable path is proven.

**Epic 8 Integration Rule:** Each owning epic must integrate its MVP surface with Epic 8 shared contracts before that feature is considered complete. Epic 8 does not defer baseline operability to a final polish phase; Godot may arrange, animate, format, and sonify semantic data but cannot calculate availability, costs, outcomes, navigation legality, hidden visibility, or canonical state.

### Story 8.1: Presentation Foundation, Design Tokens, and Shared Components

As a player,
I want consistent visual language across the game,
So that information and actions remain recognizable as contexts change.

**Acceptance Criteria:**

**Given** the Godot presentation foundation is loaded
**When** shared surfaces are rendered
**Then** they use approved semantic tokens for ink/paper/dark panels, autumn/school/social/resource colors, pixel-readable typography, zero letter spacing, spacing grid, small radii, crisp outlines, and focus treatment
**And** no gameplay rule, canonical state, or hidden value is encoded in visual tokens.

**Given** a HUD, phone, lesson panel, top bar, tab bar, badge, prompt, toast, wellbeing display, money label, lesson clock, risk tag, or relationship/fact chip is needed
**When** its shared component is instantiated
**Then** Story 8.1 implements only components required by the first exploration/activity/phone/lesson/dialogue slice, with later components added by their owning presentation stories
**And** it consumes display-ready semantic data and stable actions
**And** page sections avoid nested cards, glassmorphism, oversized decorative framing, and one-note palette treatment.

**Given** visual references conflict
**When** implementation selects behavior or styling
**Then** `EXPERIENCE.md` and `DESIGN.md` remain authoritative behavioral/visual contracts while mockups are references
**And** any approved deviation is recorded with affected component and reason.

**Given** components render localized or dynamic content
**When** reference text expands to 130%
**Then** essential labels, values, actions, and feedback remain readable without overlap or clipping at target resolutions
**And** fixed-format elements use stable responsive dimensions that do not shift from focus or state changes.

**Given** Godot presenters consume changing UI state
**When** nodes enter or leave the tree
**Then** they subscribe only to Application projections/read models, dispose subscriptions with node lifecycle, and never mutate projections or query Domain directly
**And** longest approved strings plus a deterministic 130% pseudo-localized catalog fail tests on missing keys, fallback leaks, overflow, truncation, or action-label collision.

Coverage: FR55, FR56, FR58, FR60, NFR9, NFR11, NFR12, NFR13, NFR18, NFR24, UX-DR22, UX-DR23, UX-DR24, UX-DR26
Tags: FVS, MVP, Technical Enabler, UX

### Story 8.2: Controller Input, Adaptive Glyphs, Focus, and Back Contract

As a player,
I want predictable controls and focus behavior,
So that every core action is comfortable without requiring a mouse.

**Acceptance Criteria:**

**Given** physical controller or keyboard input occurs
**When** Godot captures it
**Then** `GodotInputAdapter` maps physical input to semantic actions, while Godot handles local focus traversal using Application-provided focus metadata
**And** activation, runtime-affecting Back semantics, and gameplay actions are submitted to Application.

**Given** a supported surface opens or refreshes
**When** focus is assigned
**Then** it declares initial focus, navigation order, restoration key, and fallback chain of prior stable target, nearest enabled semantic sibling, primary action, then Back
**And** focused, selected, unavailable, warning, and active states remain distinguishable without color alone.

**Given** the player presses Back
**When** the active surface handles it
**Then** Back moves one semantic layer up before closing/canceling the larger flow
**And** pending consequential input or settings require declared discard/cancel confirmation while committed dialogue/event state uses an authorized parent action or typed rejection.

**Given** the active input device changes
**When** glyph presentation updates
**Then** every visible glyph updates within one rendered frame without changing semantic focus, pending input, or executing an action
**And** unsupported devices use a validated fallback glyph set
**And** keyboard, controller, and auxiliary mouse paths remain behaviorally consistent.

Coverage: FR55, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR24, UX-DR13, UX-DR14, UX-DR15, UX-DR21
Tags: FVS, MVP, Technical Enabler, UX

### Story 8.3: Exploration HUD and In-World Interaction Prompts

As a player,
I want exploration to show essential status and nearby interactions,
So that I can move through school life without opening a dashboard for every decision.

**Acceptance Criteria:**

**Given** normal exploration is active
**When** the HUD renders
**Then** it shows time/day, compact Energy/Stress treatment, qualitative contextual Mood, plain money text, phone access, contextual pressure alerts only when relevant, and transient upper-right toasts
**And** it renders Application projections without recomputing time, wellbeing, money, or availability.

**Given** an interaction anchor is visible
**When** its prompt renders near an NPC, object, door, bus stop, or map-edge affordance
**Then** it shows adaptive glyph, short concrete verb, target name, focus/availability state, and concise known requirement
**And** placement remains legible without obscuring the target or important HUD information.

**Given** anchors overlap, disappear, or refresh
**When** prompt priority and focus update
**Then** Application supplies semantic priority/availability while Godot resolves screen-space placement and overlap without changing semantic ordering
**And** the presentation does not preserve stale prompts or move world state.

**Given** travel succeeds
**When** the presentation transitions to the destination
**Then** it shows destination, known time cost, transition state, and arrival feedback from Epic 1 semantic data
**And** Godot does not own travel legality, time advancement, or logical location.

**Given** exploration runs at target resolutions
**When** long labels, pressure alerts, toasts, and prompts coexist
**Then** they avoid incoherent overlap, remain non-color-dependent, and preserve a readable world viewport
**And** 720p/1280x800 remains usable at handheld distance.

Coverage: FR39, FR54, FR55, FR58, FR60, NFR9, NFR11, NFR12, NFR13, NFR18, UX-DR6, UX-DR7, UX-DR18, UX-DR20, UX-DR21, UX-DR23
Tags: FVS, MVP, Cross-Epic, UX

### Story 8.4: Activity Confirmation, Result Feedback, and Toast Presentation

As a player,
I want actions and outcomes to explain their known costs and consequences,
So that decisions feel informed without exposing hidden formulas.

**Acceptance Criteria:**

**Given** a world interaction opens an activity preview
**When** the confirmation overlay renders
**Then** it shows concrete action, time cost, known resource/relationship/academic/context implications, warning or blocked reason, Confirm, Back, optional Details, and adaptive glyphs
**And** it does not become authoritative or expose hidden effects.

**Given** an activity resolves
**When** result feedback renders
**Then** it groups visible consequences under the action or authored event that caused them and communicates concise qualitative causality
**And** internal flags and memory hooks are never displayed directly; only intentionally player-visible authored acknowledgements may appear
**And** Epic 2 owns effect meaning, causality, and payload while Epic 8 owns hierarchy, readability, interaction, and visual/audio presentation.

**Given** compact feedback is delivered as a toast
**When** it is actionable or reviewable
**Then** it uses icon, short title, one-line detail, non-color reinforcement, and a stable route to Phone Notifications where applicable
**And** critical consequences are not lost solely because a toast expires.

**Given** preview or result focus is used
**When** the surface closes or refreshes
**Then** Back/Continue returns focus to the originating interaction or deterministic fallback
**And** rejected commands preserve readable reasons without presenting success feedback.

Coverage: FR13, FR55, FR58 (supporting), FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, NFR24, NFR28, UX-DR8, UX-DR9, UX-DR16, UX-DR20, UX-DR21, UX-DR23
Tags: FVS, MVP, Cross-Epic, UX

### Story 8.5: Smartphone Frame and Shared App Presentation

As a player,
I want phone apps to share a familiar frame and navigation language,
So that information remains easy to scan across apps.

**Acceptance Criteria:**

**Given** the phone opens
**When** its shared frame renders
**Then** it includes the approved top bar with date, centered current time, Energy/battery treatment, app grid, app-level Back/Home, and semantic status from Epic 6
**And** the frame does not own navigation, app availability, unread truth, or gameplay state.

**Given** an app uses tabs, lists, details, badges, facts, or contextual actions
**When** shared patterns render
**Then** authored order, semantic focus targets, Back stack behavior, badges, and adaptive glyphs remain consistent
**And** notification state, selected state, and focused state are distinguishable without color alone.

**Given** Calendar, Map, Social, Messages, School App/Journal, Settings, or save/load surfaces render
**When** their data is unavailable, empty, stale, or restricted
**Then** stable empty/unavailable/error patterns preserve app chrome and focus recovery
**And** presentation does not invent hidden information or executable remote activities.

**Given** phone surfaces render at target resolutions
**When** tabs, expanded text, badges, and nested details are present
**Then** core content remains readable without stacked-card clutter, productivity-dashboard framing, or clipped primary actions
**And** app identity uses restrained semantic accent colors rather than a single dominant hue.

Coverage: FR33, FR35, FR36, FR37, FR38, FR52, FR53, FR55, FR58, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, UX-DR1, UX-DR2, UX-DR3, UX-DR4, UX-DR5, UX-DR13, UX-DR14, UX-DR15, UX-DR19, UX-DR22, UX-DR23, UX-DR24
Tags: FVS, MVP, Cross-Epic, UX

### Story 8.6: Focused Lesson Presentation

As a player,
I want lessons to present school pressure clearly,
So that three classroom choices feel active without resembling combat.

**Acceptance Criteria:**

**Given** an active lesson read model is available
**When** the lesson surface renders
**Then** it uses a focused seated classroom composition with teacher/board, circular three-block clock, action list, teacher panel, feedback, and result regions
**And** it does not use the normal exploration camera as the primary lesson presentation.

**Given** teacher information is player-visible
**When** the teacher panel renders
**Then** it shows name, Attention status, Strictness status, and optional authored descriptor
**And** it hides exact check probabilities, raw impression values, and unrevealed rules.

**Given** lesson actions render
**When** the player navigates them
**Then** stable categories, contextual variants/tooltips, qualitative risk labels, known requirements, focus order, and adaptive glyphs remain readable
**And** risk and completed/active clock states do not rely on color alone.

**Given** block feedback or final result renders
**When** the lesson progresses
**Then** focus and selected action remain stable where valid, feedback is not duplicated, and core information fits target resolutions
**And** pacing, transitions, sounds, meters, animations, labels, and feedback read as classroom attention and passing time rather than turns, attacks, enemy phases, health depletion, victory, or defeat.

Coverage: FR14, FR15, FR16, FR17, FR45, FR55, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR15, UX-DR10, UX-DR11, UX-DR12, UX-DR13, UX-DR14, UX-DR16, UX-DR21, UX-DR23, UX-DR24
Tags: FVS, MVP, Cross-Epic, UX, Constraint

### Story 8.7: Large and Small Dialogue Presentation Modes

As a player,
I want important conversations and quick exchanges to feel appropriately framed,
So that I can recognize narrative weight while keeping choices readable.

**Acceptance Criteria:**

**Given** Epic 5 requests large dialogue mode
**When** the surface renders
**Then** NPC portrait and dialogue box occupy the primary focus while the world/background is subordinated appropriately
**And** ordered speaker/text, concrete choices, availability, acknowledgement intent, focus, and glyph metadata come from the dialogue read model.
**And** large mode is reserved for authored relationship beats or emotionally significant conversations, with scope warnings for excessive use.

**Given** Epic 5 requests small dialogue mode
**When** the surface renders
**Then** a compact bubble appears near the speaking sprite for barks or low-weight exchanges without an NPC portrait
**And** text beyond approved compact length wraps safely or promotes to large mode rather than shrinking below the readable token
**And** placement avoids clipping, prompt collision, and ambiguity about the speaker.

**Given** the protagonist participates in dialogue
**When** either mode renders
**Then** no high-resolution protagonist portrait is required for MVP
**And** concrete choice text remains distinct from acknowledgement controls and abstract tone labels.

**Given** dialogue choices, errors, or long localized text render
**When** focus moves or the model refreshes
**Then** reading order, stable focus, Back/exit semantics, selected/unavailable states, and 130% text expansion remain usable at target resolutions
**And** hidden outcomes and raw relationship values remain absent.

Coverage: FR45, FR46, FR47, FR55, FR56, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, UX-DR13, UX-DR14, UX-DR16, UX-DR17, UX-DR21, UX-DR23
Tags: FVS, MVP, Cross-Epic, UX

### Story 8.8: Summary and High-Pressure State Presentation

As a player,
I want transitions and high-pressure states to summarize what matters,
So that I understand changing stakes without being overwhelmed by system detail.

**Acceptance Criteria:**

**Given** end-of-day, exam pressure, partial event attendance, or semester-reflection semantic data is available
**When** the corresponding presentation contract renders
**Then** Story 8.8 delivers one reusable summary/pressure pattern with ordered sections, primary/secondary actions, known consequences, focus/Back behavior, and non-color urgency cues
**And** each owning epic supplies content and behavior while Epic 8 supplies reusable presentation patterns.

**Given** partial attendance or expiring pressure is shown
**When** time or opportunity state changes
**Then** persistent text plus icon/shape communicates remaining time or known loss without moving focus unexpectedly
**And** unrevealed expired opportunities remain hidden.

**Given** end-of-day or semester content is not yet implemented
**When** its presentation contract is exercised
**Then** representative fixtures can validate layout and operability without implementing Epic 9 progression/reflection logic
**And** deferred mockups remain tracked rather than silently invented.

**Given** summary content expands at target resolutions
**When** sections, missed chances, memories, or actions grow
**Then** overflow uses controller-scrollable sections with visible position/progress, persistent primary actions, reading-order focus, and focus restoration from details
**And** essential information remains scannable and free of quest-log or spreadsheet framing.

Coverage: FR41, FR42, FR51, FR55, FR58, FR59, NFR9, NFR11, NFR12, NFR13, NFR18, UX-DR16, UX-DR20, UX-DR21, UX-DR27
Tags: MVP, Cross-Epic, UX

### Story 8.9: Accessibility, Remapping, and Motion/Feedback Controls

As a player,
I want controls and presentation intensity to fit my needs,
So that the game remains operable and comfortable across supported input and display contexts.

**Acceptance Criteria:**

**Given** the player opens accessibility or controls settings
**When** available options render
**Then** controller and keyboard bindings, active glyph set, text/readability options, and supported intensity controls for shake, flashing, strong motion, and haptics are controller-operable
**And** no core option requires a mouse.

**Given** a binding is remapped
**When** conflicts, reserved actions, or unsupported inputs are detected
**Then** the UI provides a readable conflict/validation flow with Confirm, Cancel, Replace where allowed, and restore-defaults confirmation
**And** Confirm, Back, focus movement, and Settings access cannot all become unbound or unreachable on the active device.

**Given** an accessibility or presentation setting changes
**When** it is applied
**Then** Application validates semantic settings commands through `ISettingsStore`, while Godot applies physical `InputMap`, display, audio, and haptic state
**And** persistence failure restores both stored and effective adapter state without mutating gameplay state.

**Given** risk, urgency, wellbeing, focus, notifications, or blocked state is shown
**When** color or motion is reduced
**Then** text, icon, shape, position, and stable focus cues preserve equivalent essential meaning
**And** disabling optional effects does not hide gameplay information.

**Given** text scale, motion, flashing, shake, haptics, or contrast-related settings are edited
**When** preview is supported
**Then** an immediate bounded preview preserves focus and supports Cancel/Revert
**And** preview state never mutates gameplay state.

Coverage: FR55, FR57, FR58, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, UX-DR13, UX-DR14, UX-DR21, UX-DR22, UX-DR23
Tags: MVP, UX

### Story 8.10: MVP Pixel-Art World and Character Asset Scope

As a player,
I want a coherent pixel-art school world and recognizable classmates,
So that the MVP feels authored and readable without requiring an unbounded art production.

**Acceptance Criteria:**

**Given** the MVP asset manifest is validated
**When** visual scope is inspected
**Then** it covers five core classmate visual sets, player sprite customization assets, four subject/classroom presentations, ten approved MVP location/space presentations, smartphone/calendar UI assets, and required event presentation reuse
**And** it distinguishes logical locations from the ten presentation spaces through an approved reuse mapping
**And** FVS requires one production-quality classmate set, protagonist sprite, one classroom, and representative spaces while remaining items may use approved placeholders until their content slice enters production.

**Given** classmate portrait scope is inspected
**When** MVP expressions are counted
**Then** each core classmate targets Neutral, Happy, Sad, Angry/Irritated, Embarrassed/Nervous, and Thoughtful/Serious
**And** Nell may receive only one or two additional special expressions when required by her partial arc.

**Given** protagonist art is inspected
**When** world and dialogue presentation are validated
**Then** hair, outfit, eye-color, and skin-color sprite parts resolve through stable cosmetic IDs with crisp integer scaling
**And** no high-resolution protagonist portrait set is required.

**Given** new asset requests are proposed
**When** they exceed approved characters, expressions, locations, classrooms, events, animation needs, or cosmetics
**Then** the manifest reports a scope warning and requires explicit approval
**And** placeholder/reuse policy preserves testability without silently expanding MVP.

**Given** asset references are validated
**When** Content and build tooling run
**Then** Content checks semantic asset IDs without Godot dependencies, while tooling rejects missing/duplicate keys, case mismatches, failed imports, invalid dimensions, non-integer scaling metadata, incompatible layers, and out-of-scope counts
**And** approved spaces may use lightweight reusable seasonal/post-event accents without creating new logical locations or unique scene sets.

Coverage: FR21, FR39, FR45, FR55, FR56, FR60, NFR12, NFR18, NFR20, NFR23, NFR24, NFR29, UX-DR17, UX-DR22, UX-DR23, UX-DR24
Tags: MVP, Constraint

### Story 8.11: MVP Audio, Music, Ambience, and Feedback Cues

As a player,
I want sound and music to reinforce school routine, pressure, and emotional context,
So that the world feels alive without relying on full voice acting.

**Acceptance Criteria:**

**Given** the MVP audio manifest is validated
**When** scope is inspected
**Then** it supports school bells/hallway ambience, classroom/study sounds, phone notifications, cafe/park ambience, gentle UI feedback, event music, exam/stress music, relationship/reflection themes, and optional non-verbal reactions
**And** it defines approved cue counts and reuse across shared school, free-time, study, relationship, pressure, event, and reflective music families, with only one optional Nell motif
**And** spoken dialogue, voiced barks, and unique themes for every classmate/event are outside MVP.

**Given** Application emits a semantic audio intent
**When** Godot audio adapters handle it
**Then** stable occurrence IDs route through an audio output port and adapter deduplication prevents projection rebuild, load, or resubscription from replaying consumed cues
**And** cue keys resolve to importable resource, bus, category, priority, concurrency/repetition limit, loop, ducking, and fallback metadata
**And** audio completion or playback state never becomes gameplay authority.

**Given** multiple cues compete
**When** dialogue, notification, event, ambience, and pressure music overlap
**Then** authored priority, ducking, repetition limits, and transition rules preserve intelligibility and avoid arcade-like noise
**And** missing optional cues fail gracefully without blocking gameplay.

**Given** audio accessibility settings are applied
**When** buses, haptics, or non-verbal reactions change
**Then** independent volume/intensity controls behave predictably and persist
**And** essential information remains available visually rather than audio-only.

**Given** optional non-verbal reactions are included
**When** production scope is checked
**Then** they are short non-word cues requiring no lip-sync, subtitles, or line-by-line recording
**And** they remain budget-dependent polish rather than partial voice-over scope.

**Given** language and voice scope is validated
**When** MVP audio/content readiness is checked
**Then** English-only text and no full voice acting remain the supported release scope
**And** any localized text pack, spoken dialogue, or partial voice-over requires explicit later-scope approval.

Coverage: FR45, FR55, FR57, FR58, FR60, NFR13, NFR19, NFR20, NFR23, NFR24, UX-DR20, UX-DR21, UX-DR23
Tags: MVP, Constraint

### Story 8.12: Early Godot Presentation Smoke Tests and UX Journey Evidence

Story Type: Evidence Gate

As a developer,
I want focused early Godot and visual evidence for representative player journeys,
So that presentation remains operable, readable, and performant as features integrate.

**Acceptance Criteria:**

**Given** the FVS Application fixture is available
**When** the Godot smoke view runs
**Then** it renders nonblank exploration HUD/prompt, activity confirmation/result, phone, lesson, and dialogue surfaces from production read models
**And** submitted intents use production Application handlers rather than presentation-only simulation.
**And** fast adapter/contract tests separately verify intent mapping and read-model consumption without Godot, while visual baselines verify layout only.
**And** this story may validate currently available Application read models before Epic 9 save/load completion, while post-load projection smoke is owned by Story 9.12.

**Given** first school morning, afternoon under time pressure, and Nell Social-clue-to-world-dialogue journeys are exercised
**When** controller navigation proceeds
**Then** focus order, Back, glyph changes, blocked recovery, cross-surface transitions, and feedback remain coherent
**And** Epic 8 completion requires the available FVS journey, while extended journeys become integration gates only when their owning fixtures exist.
**And** the first playable slice demonstrates schedule pressure, lesson agency, in-person social/navigation, wellbeing tradeoff, and consequence feedback in one coherent controller-playable school-life flow.

**Given** visual baselines use fixed fixtures, fonts, localization, glyphs, settings, and animation state
**When** captures run separately at 1920x1080, 1280x720, and 1280x800
**Then** essential Control bounds remain inside safe viewport and primary actions, focus decoration, labels, HUD regions, and modal content do not intersect
**And** nondeterministic animation/particles/blink/shaders are disabled while reviewed pixel-diff thresholds and masks detect blank, transparent, clipped, or materially displaced rendering.

**Given** the representative ten-minute loop is measured
**When** it includes exploration, phone, lesson, dialogue, travel, and day transition
**Then** measurements use a named release build and reference hardware/profile after one warm-up loop across at least three ten-minute runs
**And** reports include median and p95 frame time, dropped frames, memory peak, and transition percentiles, requiring p95 frame time at or below 16.67 ms and every measured transition below 2.0 seconds
**And** broad content validation or simulation does not run during normal gameplay.

**Given** the representative one-day MVP loop is measured
**When** first-school-day and representative free-day fixtures complete through production Application commands and presentation surfaces
**Then** evidence records observed/estimated real-time completion, command count, text dwell assumptions, retries/blocked-choice overhead, and variance
**And** the day remains under 30 minutes or receives an explicit approved waiver.

**Given** smoke or visual evidence fails
**When** diagnostics are produced
**Then** they identify fixture, viewport, active input/glyph set, focused semantic target, surface, and screenshot/log reference
**And** gameplay behavior still remains covered primarily by fast Domain/Application/Content tests.

**Given** Epic 8 evidence is reviewed
**When** UX coverage is traced
**Then** a versioned matrix maps UX-DR1 through UX-DR27 to owning story, surface, fixture, viewport, input mode, and automated or human-review evidence
**And** missing coverage or an unapproved exception fails Epic 8 approval.
**And** UI-bearing stories are not complete until their semantic read model has at least one Epic 8 contract/evidence link for focus, Back, glyphs, readability, non-color cues, and hidden-information boundaries appropriate to that surface.

Coverage: FR45, FR55, FR56, FR57, FR58, FR60, NFR1, NFR2, NFR3, NFR6, NFR9, NFR10, NFR11, NFR12, NFR13, NFR14, NFR16, NFR18, NFR24, NFR25, NFR26, UX-DR1, UX-DR2, UX-DR3, UX-DR4, UX-DR5, UX-DR6, UX-DR7, UX-DR8, UX-DR9, UX-DR10, UX-DR11, UX-DR12, UX-DR13, UX-DR14, UX-DR15, UX-DR16, UX-DR17, UX-DR18, UX-DR19, UX-DR20, UX-DR21, UX-DR22, UX-DR23, UX-DR24, UX-DR25, UX-DR26, UX-DR27
Tags: FVS, Technical Enabler, UX

### Epic 9: Save, Progression, Memory, and MVP Semester Completion

**Goal:** Player can safely preserve and resume canonical consequences, recover through supported checkpoints, complete the MVP semester, and receive a personalized reflection of lived choices without a golden ending, narrative game-over, or quest-log-style memory dump.

**Epic 9 Test Evidence:** Epic 9 must produce SaveMigration tests for current and old supported fixtures, Application tests for save legality, atomic manual/autosave/load/checkpoint commands, projection rebuild equivalence, corruption handling, intentional memory records, semester snapshotting, and reflection selection; ScenarioRunner evidence for an FVS round-trip and contrasting semester outcomes; and read-model snapshots proving safe-system feedback, controller operability, confirmation/error focus recovery, hidden-information boundaries, and neutral Memory Ledger framing.

**Epic 9 FVS Ordering:** The first save slice should establish a versioned envelope over minimal canonical state, Application-owned legality, in-memory/file storage ports, one safe manual save/load round-trip, projection rebuild equivalence, and a resumed playable context. Autosave, migration breadth, checkpoints, Memory Ledger breadth, and full semester reflection follow after this round-trip is proven.

### Story 9.1: Versioned Save Envelope and Canonical Game State Contract

As a player,
I want my meaningful progress stored as stable game state,
So that saving preserves the school life I created rather than transient screen details.

**Acceptance Criteria:**

**Given** a save envelope is created
**When** canonical state is serialized
**Then** it includes schema version, save ID, game build/version, content catalog version/hash, migration baseline, timestamp, playthrough identity, checkpoint/autosave classification, and canonical `GameState`
**And** serialization is deterministic for equivalent state under a controlled clock.

**Given** canonical state contains time, location, protagonist, academics, wellbeing, relationships, activities, clubs/events, flags, intentional memories, or progression data
**When** the save contract is inspected
**Then** supported values round-trip through stable IDs/value objects and validated bounds
**And** the envelope does not serialize Godot nodes/paths, active focus, animation, phone stack, prompt caches, R3 projections, or presentation-only state.

**Given** the narrow FVS save fixture is used
**When** it is serialized and deserialized
**Then** it may contain only state required for the representative content/time/activity/lesson/dialogue path
**And** it uses the same versioned contract as broader MVP saves.

**Given** a required stable ID, version field, invariant, or compatibility marker is invalid
**When** save encoding or decoding is attempted
**Then** it fails with a typed save-contract error and actionable diagnostics
**And** no partial canonical state is published.

Coverage: FR1, FR4, FR17, FR21, FR24, FR30, FR39, FR51, FR52, FR58, FR60, NFR4, NFR18, NFR24, NFR27, NFR28, NFR30
Tags: FVS, MVP, Technical Enabler

### Story 9.2: Application-Owned Save Legality Policy

As a player,
I want saving to be available only when the game is stable,
So that my save cannot capture an unresolved action or broken intermediate state.

**Acceptance Criteria:**

**Given** Application evaluates manual-save capability
**When** runtime mode and pending transactions are stable
**Then** it returns an allowed capability with player-facing metadata
**And** legality is not inferred from Godot scene visibility or enabled buttons.

**Given** the game is in a lesson, exam, action resolution, active dialogue, active event choice, transition, or another declared unsafe runtime mode
**When** manual save is requested
**Then** Application returns a stable typed blocked reason
**And** storage is not called and canonical state remains unchanged.

**Given** capability was allowed when previewed but state changes before execution
**When** the save command runs
**Then** legality is revalidated against the current state revision
**And** a stale or newly unsafe request is rejected without writing.

**Given** phone or another UI requests save status
**When** the capability read model is generated
**Then** it provides only allowed/blocked state, readable reason, and supported intents
**And** Epic 6/8 own routing and presentation while Epic 9 exclusively owns legality.

Coverage: FR52, FR53, FR55, FR58, FR60, NFR4, NFR9, NFR10, NFR24, NFR27, NFR28, NFR30, UX-DR6, UX-DR13, UX-DR14, UX-DR16, UX-DR21
Tags: FVS, MVP, Cross-Epic, Technical Enabler

### Story 9.3: Manual Save Slots and Safe Load Commands

As a player,
I want to manage and load manual saves safely,
So that I can resume an intentional point in my semester without corrupting current progress.

**Acceptance Criteria:**

**Given** save slots are queried
**When** Application builds slot summaries
**Then** each available slot exposes stable ID, save type, timestamp, current day/week, protagonist label, player-facing location/last-playable-context summary, compatibility/status, and confirmation intents
**And** raw file paths or unvalidated payload data remain hidden.

**Given** the player confirms a new save or overwrite in a legal context
**When** the command executes
**Then** Application revalidates slot generation, envelope identity, compatibility, and current state revision before one idempotent operation captures a consistent canonical snapshot and writes it atomically through a storage port
**And** failed, stale, or repeated delivery cannot leave a partial file, duplicate logical save, or misleading slot state.

**Given** the player confirms load
**When** the selected envelope passes current-schema FVS compatibility, integrity, and supported migration checks
**Then** Application revalidates load legality against the current runtime mode/state revision, atomically replaces canonical state, and starts the rebuild/resume path
**And** current state remains available until the replacement is fully ready, and the confirmation clearly states that current unsaved progress will be replaced.

**Given** a slot is missing, stale, incompatible, corrupted, or the command ID conflicts
**When** save/load is attempted
**Then** a named typed Result is returned, zero invalid state is committed, and storage effects follow the documented atomic-write/read policy
**And** the UI can refresh slot status and preserve deterministic focus.

Coverage: FR52, FR53, FR55, FR58, FR60, NFR4, NFR7, NFR8, NFR24, NFR27, NFR28, NFR30, UX-DR6, UX-DR13, UX-DR14, UX-DR16, UX-DR21
Tags: FVS, MVP, Technical Enabler, UX

### Story 9.4: Start-of-Day and End-of-Day Autosaves

As a player,
I want automatic saves at reliable day boundaries,
So that routine progress is protected without interrupting play.

**Acceptance Criteria:**

**Given** a new day has completed initialization in a stable playable context
**When** start-of-day autosave triggers
**Then** it captures the fully initialized canonical day state after required transitions complete
**And** it does not save an intermediate loading, summary, or unresolved runtime state.

**Given** sleep/end-of-day transition has atomically completed
**When** end-of-day autosave triggers
**Then** it preserves all committed consequences and the stable boundary state
**And** trigger replay is keyed by boundary type, playthrough ID, canonical day/week, and source state revision so it cannot create duplicate autosave effects.

**Given** autosave storage fails
**When** the failure is reported
**Then** canonical gameplay state and day progression remain valid, a non-destructive diagnostic/player-facing notification is available, and prior valid saves remain intact
**And** autosave success, in-progress, and failure states use concise text/icon feedback without relying on color alone, and gameplay does not pretend the autosave succeeded.

**Given** autosave retention is configured
**When** new boundary saves are written
**Then** a bounded rotation policy preserves at least the current recoverable boundary and avoids uncontrolled file growth
**And** retention behavior is deterministic and testable.

Coverage: FR9, FR10, FR52, FR58, FR60, NFR4, NFR7, NFR8, NFR24, NFR27, NFR28, NFR30
Tags: MVP, Technical Enabler

### Story 9.5: Load Rebuild of Projections and Playable Context

As a player,
I want loading to return me to an equivalent playable state,
So that the world, information, and choices match what I saved.

**Acceptance Criteria:**

**Given** a valid canonical state has been loaded
**When** Application rebuilds projections
**Then** time/calendar, logical location, protagonist, wellbeing, academics, relationships, phone information, clubs/events, feedback capability, and runtime mode derive from canonical state for projections present in the loaded fixture/content set
**And** unavailable downstream projections return stable empty/unavailable states only when explicitly out-of-scope for the fixture; any implemented required MVP projection that cannot rebuild fails load publication with a typed recoverable Result.
**And** no persisted R3 projection or Godot view state is used as authority, and runtime derives into a declared safe resume mode rather than restoring active sessions by default.

**Given** incremental projections existed before save
**When** equivalent state is loaded and fully rebuilt
**Then** rebuilt read models match deterministic expected outputs for the same content version and known-information state
**And** transient phone, focus, prompt, animation, and consumed audio states restart through documented defaults.

**Given** the saved state resumes normal play
**When** Godot receives the rebuilt context
**Then** it loads/resolves presentation for the logical location, restores an appropriate world/system focus target, and accepts production Application intents
**And** scene-load failure cannot replay time, travel, or canonical effects.

**Given** any required projection, catalog reference, or playable-context invariant cannot rebuild
**When** load orchestration detects the failure
**Then** the new state is not published, the prior running state remains intact where available, and a recoverable typed failure is returned
**And** no partially rebuilt projections leak to views.

Coverage: FR33, FR52, FR55, FR58, FR60, NFR4, NFR7, NFR8, NFR24, NFR25, NFR26, NFR27, NFR28, NFR30
Tags: FVS, MVP, Technical Enabler, Cross-Epic

### Story 9.6: Save Migration, Corruption Detection, and Recoverable Failure

As a player,
I want older or damaged saves handled safely,
So that compatibility problems do not silently destroy my progress.

**Acceptance Criteria:**

**Given** a supported older save fixture is loaded
**When** migration runs
**Then** ordered version steps transform it into the current canonical contract with source/target versions and diagnostics
**And** migrated state revalidates all required invariants before publication.

**Given** a save has invalid checksum/integrity data, truncated payload, unsupported future version, missing required IDs, or impossible canonical state
**When** load validation runs
**Then** it fails before state replacement with a named corruption/compatibility reason
**And** it never attempts best-effort partial gameplay state.

**Given** an atomic save write replaces an existing slot
**When** infrastructure interruption occurs
**Then** temporary-write/replace and backup policy preserves the last valid recoverable version where supported
**And** disk-full, permission-denied, interrupted write/replace, checksum mismatch, and backup cleanup failures never treat a partial file as playable or delete the only valid copy.

**Given** migration or corruption failure is player-facing
**When** the slot list or load result renders
**Then** it provides a concise status and safe next actions without technical payload exposure
**And** focus returns to the affected slot or safest available action with Retry, Back, and Delete/Manage intents only when supported, while detailed diagnostics remain available for development/support.

Coverage: FR52, FR58, FR60, NFR4, NFR7, NFR8, NFR18, NFR24, NFR27, NFR28, NFR30
Tags: MVP, Technical Enabler, Constraint

### Story 9.7: Academic Failure Checkpoints and Recovery Execution

As a player,
I want meaningful recovery points after academic failure,
So that school stakes matter without forcing a complete restart.

**Acceptance Criteria:**

**Given** Epic 3 reports an eligible academic failure cause
**When** recovery options are generated
**Then** Epic 9 can offer checkpoints one week before the exam, four weeks before the exam, and at semester start where valid captures exist
**And** Epic 3 owns failure cause/read-model inputs while Epic 9 owns selection, restoration, and execution.

**Given** a recovery checkpoint is selected
**When** the restore command executes
**Then** it uses the same compatibility, migration, atomic replacement, and projection rebuild path as normal load
**And** it restores the exact declared canonical boundary, included/excluded committed effects, content version, and state digest without mutating only academic values or preserving consequences from after the checkpoint.

**Given** a required checkpoint is unavailable, corrupted, incompatible, stale, or already being restored
**When** selection is attempted
**Then** Application returns a stable typed reason and current state remains unchanged
**And** only valid alternatives remain selectable.

**Given** MVP/full-game scope is enforced
**When** checkpoint functionality is planned
**Then** the contract, reason codes, and fixtures may be retained as Later MVP/full-game traceability without blocking the FVS or basic semester reflection
**And** runtime checkpoint capture, selection UI, and full three-year promotion/graduation remain future scope until the owning academic failure/promotion flow is implemented.

Coverage: FR3 (later traceability), FR19 (later traceability), FR20 (later traceability), FR52, FR58, FR60, NFR4, NFR21, NFR24, NFR27, NFR28, NFR30
Tags: Later MVP, Full Game/Later, Cross-Epic, Technical Enabler

### Story 9.8: Intentional Memory Record Model

As a player,
I want meaningful moments remembered selectively,
So that later reflection feels personal rather than like a raw history log.

**Acceptance Criteria:**

**Given** a significant authored/systemic outcome resolves
**When** its effects include a memory hook
**Then** Application interprets the memory hook and calls the Domain-owned Memory Ledger policy to create/validate a typed `MemoryRecord` with stable source, category, subjects, timing, significance, visibility, variation keys, and reflection eligibility
**And** Application owns orchestration, idempotency, projection updates, and atomic commit with the outcome that caused it.

**Given** ordinary repeated actions, raw flags, minor deltas, or presentation events occur
**When** memory eligibility is evaluated
**Then** they do not automatically create Memory Records
**And** the model remains an intentional reflection source rather than an event log, telemetry stream, codex, or quest history.

**Given** memories represent attended/missed events, relationships, academics, wellbeing patterns, identity choices, clubs, or meaningful dialogue
**When** records are queried
**Then** only player-visible authored summaries and known context are exposed, and missed chances are reflection-eligible only when the protagonist discovered, anticipated, declined, or meaningfully approached them
**And** raw Bond, hidden flags, exact formulas, fully unknown opportunities, and unrevealed future meaning remain private.

**Given** a memory command is replayed or conflicts
**When** the same source occurrence/effect idempotency key is processed
**Then** exact replay returns the recorded outcome without duplication while conflicting reuse returns a typed error
**And** canonical memory state remains deterministic.

Coverage: FR13, FR23, FR24, FR41, FR46, FR48, FR49, FR51, FR58, FR59, FR60, NFR20, NFR23, NFR24, NFR27, NFR28, NFR30
Tags: FVS, MVP, Cross-Epic, Technical Enabler

### Story 9.9: MVP Semester Completion and Reflection Input Snapshot

As a player,
I want the first semester to close cleanly after Week 20,
So that my accumulated school-life choices can be reflected without pretending the full journey is over.

**Acceptance Criteria:**

**Given** the Week 20 exam period and required semester flow have resolved
**When** completion eligibility is evaluated
**Then** Application enters a stable MVP semester-completion mode exactly once
**And** optional missed intros, weak relationships, low grades, high Stress, or missed events do not create a narrative game-over.

**Given** semester completion begins
**When** reflection inputs are captured
**Then** one immutable snapshot records eligible events/misses, relationship states, Nell progression, club participation, academic standing, wellbeing pattern, romance hints, event memories, identity signals, and relevant protagonist baseline context
**And** it uses stable typed inputs rather than querying mutable views or a raw event log, and creation attributes/preferences provide framing context while repeated actions, relationships, academics, wellbeing, priorities, and missed opportunities remain primary identity evidence.

**Given** completion is replayed, stale, premature, or missing required Week 20 state
**When** the command executes
**Then** exact replay returns the recorded result while invalid attempts return typed reasons
**And** no duplicate reflection snapshot or partial completion state is created.

**Given** future full-game progression is considered
**When** the MVP boundary is reached
**Then** the semester closes as a one-semester milestone rather than graduation, promotion, epilogue, or three-year completion
**And** FR2 is proven as a 20-week autumn-to-winter MVP semester boundary while FR3/FR19 future gates remain traceable without being implemented here.

Coverage: FR2, FR3 (later traceability), FR18, FR19 (later traceability), FR41, FR48, FR51, FR59, FR60, NFR20, NFR21, NFR23, NFR24, NFR27, NFR28, NFR30
Tags: MVP, Cross-Epic, Constraint

### Story 9.10: Semester Reflection Selection and Composition

As a player,
I want the reflection to emphasize the semester that I actually lived,
So that different lifestyles feel recognized without ranking one as correct.

**Acceptance Criteria:**

**Given** a valid immutable reflection snapshot
**When** composition runs
**Then** deterministic authored rules select and prioritize a bounded set of memories, missed chances, relationships, academics, wellbeing, identity, clubs, and event signals using a fixed maximum selection count, per-category caps, required fallbacks, and authored stable tie-breakers
**And** significance prevents accidental repetition while allowing a genuinely dominant academic, social, club, recovery, or solitary pattern to receive greater emphasis when supported by the snapshot.

**Given** multiple valid records compete for limited space
**When** priority and variation rules resolve
**Then** ordering is deterministic under the same snapshot/content version, avoids duplicate semantic moments, and uses authored fallback text where needed
**And** it does not depend on wall-clock time, unseeded randomness, mutable views, or storage enumeration order, and it does not enumerate every stored record.

**Given** contrasting socially full, academically focused, chaotic, balanced, lonely/regretful, club-centered, or romance-curious fixtures are composed
**When** outputs are compared
**Then** each receives materially relevant variation without a true/golden ending, secret score, fixed personality verdict, or optimization ranking
**And** imperfect play and missed chances remain reflective possibilities rather than punishments, failures, or instructions for an optimal replay.

**Given** reflection rules or text variants expand
**When** ContentValidator analyzes them
**Then** unknown keys, unreachable required sections, missing fallbacks, duplicate priorities, unbounded combinatorics, and quest-log language fail or warn appropriately
**And** composition remains solo-dev feasible.

Coverage: FR51, FR59, FR60, NFR18, NFR20, NFR21, NFR23, NFR24, NFR28, NFR29, NFR30
Tags: MVP, Constraint

### Story 9.11: Personalized Memory Ledger Read Model

As a player,
I want a readable Semester Reflection and Memory Ledger,
So that I can revisit what mattered without receiving an epilogue verdict or task checklist.

**Acceptance Criteria:**

**Given** composed reflection content is available
**When** the read model is generated
**Then** it supplies ordered player-facing sections for selected memories, missed chances, relationship states, clubs, academics, wellbeing, event participation, romance hints, identity signals, and Nell memories where meaningful under the same relevance rules as other relationships
**And** empty, irrelevant, or non-materially-present sections are omitted, and the read model provides stable section IDs, titles, primary actions, empty-state labels, and detail-open intents for Epic 8 presentation.

**Given** the read model references hidden or uncertain state
**When** player-visible text is selected
**Then** it communicates only authored known interpretation
**And** raw Bond, flags, formulas, thresholds, hidden routes, and future outcomes remain absent.

**Given** the reflection is navigated with controller or keyboard
**When** sections overflow or details open
**Then** reading-order focus, visible scroll position, persistent primary actions, Back, adaptive glyphs, and focus restoration follow Epic 8 contracts
**And** 130% text remains readable at 1080p, 1280x720, and 1280x800.

**Given** reflection framing is reviewed
**When** the semester closes
**Then** wording remains neutral, nostalgic, bittersweet, and personalized without declaring success/failure, golden/true ending, graduation, or final life outcome
**And** the ledger is not a quest log, achievement list, scorecard, completionist prompt, optimization guide, or raw event archive.

Coverage: FR51, FR55, FR58, FR59, FR60, NFR9, NFR10, NFR11, NFR12, NFR13, NFR18, NFR20, NFR21, UX-DR13, UX-DR14, UX-DR15, UX-DR16, UX-DR21, UX-DR27
Tags: MVP, Cross-Epic, UX

### Story 9.12: Save, Recovery, and Semester Completion Evidence

Story Type: Evidence Gate

As a developer,
I want deterministic save and completion evidence,
So that restoring state and reflecting a semester remain trustworthy across change.

**Acceptance Criteria:**

**Given** the narrow FVS fixture is executed
**When** ScenarioRunner saves after the representative path, mutates forward, loads, and resumes
**Then** canonical state, rebuilt projections, logical location, available actions, known information, and player-facing feedback match the expected saved point
**And** the FVS save gate covers current-schema manual save/load round-trip, blocked unsafe save, projection rebuild, and resumed playable context through production Application commands and storage ports.
**And** post-load Godot smoke verifies that rebuilt projections after resume still render the expected HUD, phone, lesson/dialogue handoff, focus target, and blocked-action feedback without presentation-only state.

**Given** save failure paths are tested
**When** manual save is blocked, storage fails, a command repeats/conflicts, a fixture is migrated, or corruption is detected
**Then** named Results, zero partial state, atomic file behavior, prior-save preservation, and diagnostics match the contract
**And** save allowed, save blocked, overwrite confirmation, load confirmation, failed load, autosave failure, post-error focus restoration, projections, and audio-cue replay are covered by deterministic evidence.
**And** autosave, migration, corruption handling, audio replay, reflection composition, and performance reports are MVP/release evidence gates enabled only after their owning stories are implemented.

**Given** checkpoint fixtures are enabled
**When** one-week, four-week, and semester-start recovery options are restored
**Then** each uses the normal load/rebuild path and returns to its exact canonical checkpoint
**And** checkpoint evidence is optional/later-scope unless the owning Epic 3 academic failure flow fixture is enabled.

**Given** contrasting semester snapshots are composed
**When** Memory Ledger outputs are captured
**Then** versioned snapshots prove deterministic selection, source diversity, hidden-information boundaries, no golden-ending language, and absence of quest-log/raw-event framing
**And** broad record permutations remain in fast Application/Content tests.
**And** minimum contrasting fixtures include an academically focused path, a social/club-focused path, and a messy or regretful path, each proving selected memories, missed chances, relationship/academic/wellbeing interpretation, and no success/failure or optimal-replay framing.

**Given** save/load and completion performance is measured
**When** release-build tests run on the named reference profile after warm-up
**Then** normal save/load and day-transition operations remain under three seconds with reported median/p95 and sample count
**And** reports include save size, migrated fixture version, serialize/write/read/migrate/rebuild/compose phase timings, warm-up count, sample count, storage adapter, migration version, fixture, and command/result context.

Coverage: FR3 (later traceability), FR19 (later traceability), FR20 (later traceability), FR51, FR52, FR53, FR59, FR60, NFR4, NFR7, NFR8, NFR18, NFR20, NFR21, NFR23, NFR24, NFR25, NFR26, NFR27, NFR28, NFR29, NFR30, UX-DR13, UX-DR14, UX-DR15, UX-DR16, UX-DR20, UX-DR21, UX-DR27
Tags: FVS, Technical Enabler, UX
