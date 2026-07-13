# High School Story - GDD Decision Log

Created: 2026-06-25  
Updated: 2026-06-27  
Status: finalized

---

## Source Inputs

- Primary: `_bmad-output/planning-artifacts/briefs/brief-High School Story-2026-06-25/brief.md`
- Supporting: `_bmad-output/planning-artifacts/briefs/brief-High School Story-2026-06-25/addendum.md`
- Supporting: `_bmad-output/planning-artifacts/briefs/brief-High School Story-2026-06-25/.decision-log.md`
- Supporting: `_bmad-output/brainstorming-session-2026-06-24T14-13-17.md`

---

## Decisions

### D001 - Create GDD From Brief

Decision: Create a new GDD for `High School Story` using the game brief as the primary source and the brainstorming session as supporting context.

Rationale: The brief includes corrected assumptions and user clarifications made after brainstorming, so it is the higher-authority input.

### D002 - Game Type

Decision: Declare the game type as `simulation`.

Rationale: The strongest signals are school-life systems, time management, resource pressure, NPC schedules, relationships, calendar structure, and long-session balance. Narrative and relationship content are central, but the game is not primarily a visual novel because the core play is simulation and daily planning.

### D003 - Working Mode Correction

Decision: Use the coached/facilitative path for GDD creation.

Rationale: The designer explicitly prefers a coached path. The initial `v0.1` draft is retained only as a provisional extraction aid and is not treated as an approved GDD.

### D004 - MVP Scope

Decision: Define MVP as a one-semester vertical slice.

Included:

- Time-block daily loop.
- Lessons as gameplay.
- Energy and Stress.
- Mood as limited or deferred. Superseded by D034-D037: Mood is now a full MVP planning system with qualitative states and mostly soft modifiers.
- 5 classmates.
- 1 partial classmate arc.
- 1-2 clubs.
- Cafe and park.
- Event calendar.

Excluded:

- Full ending.
- Full relationship arcs.
- Full three-year run.
- Broad town map.

### D005 - Full Game Scope Targets

Decision: Use 12 core classmates as the current full-game target.

Rationale: This balances the fantasy of a meaningful social cast with solo-dev production constraints. Background or lightweight students can support school population without adding full arcs.

### D006 - Time Granularity

Decision: Use 15-minute blocks as the standard scheduling unit.

Rationale: The brainstorming and brief both identify small visible time costs as central to the game's emotional identity.

### D007 - Lesson Treatment

Decision: Lessons are full gameplay and a major part of the day.

Rationale: The brief explicitly resolves this. The GDD defines lesson decisions but leaves exact moment-to-moment model open for design iteration.

### D008 - Technical Boundary

Decision: Keep Godot as target engine but avoid architecture detail in the GDD.

Rationale: The GDD should define player experience, system behavior, platform targets, and asset scope. Godot implementation patterns belong in `gds-game-architecture`.

### D009 - Game Pillars Confirmed

Decision: The GDD will use four game pillars:

- Time and wellbeing as core currencies.
- Relationships through interaction and story-arc progression.
- Identity enriched through tradeoffs.
- School structure as pressure.

Rationale: The designer confirmed that all four are foundational. Removing any one of them breaks the intended game rather than merely reducing feature depth.

### D010 - Relationship Progression Model

Decision: Relationship progression combines smaller repeatable/systemic interactions with major scripted story-arc milestones.

Details:

- Minor interactions include conversation, texting, meetings, gifts, and similar contact points.
- These interactions raise the relationship level between major milestones.
- Compatibility does not gate relationships by itself; it makes relationship growth faster or more efficient than it would be without compatibility.
- A classmate story arc advances through larger scripted quests rich in dialogue and events.
- Each major story milestone requires reaching the appropriate relationship level first.

Rationale: The relationship fantasy depends on both day-to-day effort and authored character moments. Systemic interactions create the rhythm of getting closer; story-arc quests provide emotional payoff and progression structure.

### D011 - Starting Identity vs Lived Identity

Decision: The player selects starting identity inputs through stats and favorites/preferences, while the protagonist's richer school identity develops through activities chosen and opportunities sacrificed during play.

Details:

- Starting identity includes stats and favorites such as music, films, literature, and food.
- These starting traits influence aptitude, compatibility, and activity texture.
- The richer identity is earned during play through repeated activity patterns, relationships, and tradeoffs.

Rationale: Character creation should give the protagonist a starting shape without replacing the game's central promise that identity gains meaning through lived choices.

### D012 - Updated Brief Reconciled

Decision: Treat the updated Game Brief and companions as aligned source input for continuing coached GDD work.

Details:

- `brief.md` now reflects relationships as repeatable interactions plus authored story milestones.
- `brief.md` now reflects starting identity through stats and favorites/preferences, with richer identity developing through play.
- `addendum.md` and `.decision-log.md` preserve the same corrections.

Rationale: The brief has been corrected from the GDD handoff and is again safe to use as the primary source for the coached GDD path.

### D013 - Planning Is Player-Initiated

Decision: Planning is not a system-managed phase. The player can plan whenever they choose, with the start of the day serving as the natural first planning moment.

Details:

- The game can surface information through calendar, smartphone, schedules, and events.
- The player decides when to stop and reassess their plan.
- New events can disrupt the player's current plan and create reasons to re-plan.
- The core loop should describe planning as a player-controlled behavior rather than a mandatory turn phase.

Rationale: The fantasy is about personally managing a school life, not following a system-imposed planning screen.

### D014 - Lessons as Restrictive Action-Selection Loop

Decision: Lessons are a distinct systemic phase of the daily loop, using the same basic action-selection grammar as the rest of the game but with a stricter structure and lesson-only actions.

Details:

- Each lesson contains 3 blocks of 15 minutes.
- Each lesson block gives the player one action choice.
- Example lesson actions include actively listening, using the phone/texting, and taking a short nap.
- Some lesson actions carry a risk that the teacher catches the player.
- The lesson fantasy is similar to a JRPG combat structure where the "opponents" are the teacher's attention and the remaining lesson time.
- After a lesson, there is one 15-minute break block.
- Break blocks allow one looser school-ground activity.

Rationale: Lessons should feel like full gameplay while staying connected to the broader time-and-action loop.

### D015 - Relationship Milestones Are Payoffs Above the Daily Loop

Decision: Classmate story-arc milestones are rarer payoffs above the daily loop, not a routine daily step.

Details:

- Repeatable interactions operate inside the day-to-day loop.
- Major classmate story-arc quests unlock after the required relationship level is reached.
- These milestones should feel like authored payoff moments for prior investment.

Rationale: Relationship milestones need emotional weight and should not be flattened into ordinary daily actions.

### D016 - Consequences Resolve Continuously

Decision: Consequences resolve mostly after each activity rather than being held for an end-of-day resolution phase.

Details:

- Activity outcomes should appear fluidly when the activity resolves.
- Going to sleep can show a compact summary of the most important consequences.
- The end-of-day summary should not become a large accounting screen.

Rationale: The player should feel immediate cause and effect while still getting a gentle day-closing rhythm.

### D017 - One More Day Motivation

Decision: The "one more day" pull comes from the combination of story curiosity, process optimization, calendar pressure, recovery needs, discovered opportunities, and approaching deadlines.

Details:

- Important next-day hooks include tests, events, clubs, and social opportunities.
- Relationship levels can sit close to a classmate story milestone, creating curiosity-driven pressure.
- The player may want to repair consequences from prior choices, including Stress, Energy, relationship state, or grades.
- Newly discovered NPC/schedule hints can motivate the player to try a different route tomorrow.
- Larger deadlines such as exams, major events, and semester moments create longer-horizon pull.
- The two dominant motivations are curiosity about story arcs and the desire to optimize daily process while preserving high wellbeing and academic effectiveness.

Rationale: Each new day should promise either a better personal process, new character discovery, or both.

### D018 - Academic Promotion Fail Gate

Decision: The game can end through academic failure if the player fails the end-of-year exam and does not earn promotion to the next class/year.

Details:

- Each semester ends with a semester exam.
- The first semester exam in a given school year does not fail the whole year by itself.
- A weak first-semester result signals academic risk and gives the player time to correct gaps before the end-of-year exam.
- The end-of-year exam determines promotion to the next class/year.
- Failing the end-of-year exam ends the run because the student does not receive promotion.
- Passing with even the minimum required result allows the player to continue and eventually complete the full game.

Rationale: The game needs mostly interpretive life outcomes, but school progression requires a hard academic gate that gives exams real stakes.

### D019 - Failure Recovery Checkpoints

Decision: If the player fails the end-of-year exam, the game offers large checkpoint recovery options rather than forcing a full restart.

Checkpoint options:

- Return to 1 week before the failed exam.
- Return to 4 weeks before the failed exam.
- Return to the beginning of the semester.

Rationale: Academic failure should matter, but the player should be able to correct their path without replaying the entire game from the beginning.

### D020 - Wellbeing Is Not a Game Over State

Decision: Wellbeing does not create an independent hard fail state. It worsens outcomes or prevents activities, especially through Energy requirements.

Details:

- Energy is the primary action-gating wellbeing resource.
- Activities can require enough Energy to start or perform effectively.
- Low Energy prevents the player from doing some desired actions.
- Stress and Mood can affect results, quality, availability, or efficiency, but they do not end the game by themselves.
- The player must manage wellbeing to do the things they care about, not to avoid a separate wellbeing game over.

Rationale: Wellbeing should shape daily planning and tradeoffs without competing with academic promotion as a hard fail condition.

### D034 - Full Wellbeing Resource Set

Decision: Energy, Stress, and Mood are all full systems, including for MVP planning.

Details:

- Energy gates activity availability and affects performance.
- Stress worsens outcomes, efficiency, and pressure.
- Mood affects social willingness, activity texture, enjoyment, and relationship/event tone.
- Mood is no longer treated as a candidate for MVP deferral.

Rationale: Wellbeing is a full game pillar, and the three-resource model gives distinct levers for stamina, pressure, and emotional state.

### D035 - Wellbeing Representation

Decision: Energy and Stress are continuous 0-100 values, while Mood is represented as qualitative emotional states.

Details:

- Energy uses an underlying 0-100 value.
- Stress uses an underlying 0-100 value.
- Energy and Stress are shown through bars and/or icons with descriptive labels.
- Mood is not simply a third identical 0-100 bar.
- Mood uses states such as happy, sad, depressed, and similar emotional categories.

Rationale: Energy and Stress need precise tuning and readable pressure, while Mood should carry emotional character rather than feeling like another spreadsheet meter.

### D036 - Mood Model

Decision: Mood is not a one-dimensional good/bad spectrum. It is a qualitative emotional state derived from hidden emotional pressures/tags.

Details:

- The player sees one current qualitative Mood state.
- Mood examples include Happy, Content, Tired, Sad, Lonely, Anxious, Irritated, Depressed, Excited, Calm, and Overwhelmed.
- The system may track hidden emotional pressures/tags such as social fulfillment, loneliness, anxiety, irritation, sadness, excitement, calm, and confidence.
- The current Mood is derived from the dominant emotional pressures and recent events.
- Mood should represent the emotional interpretation of the protagonist's recent life, not a third resource bar to optimize.

Rationale: Emotional states do not sit cleanly on one axis. A tag/pressure-driven mood model preserves emotional nuance while still giving the game useful state for dialogue, activity, and event modulation.

### D037 - Mood Effects Are Mostly Soft

Decision: Mood primarily modifies activities softly rather than hard-blocking them.

Details:

- Mood can change Energy cost, Stress impact, relationship effect, dialogue tone, activity reward, or activity drain.
- Mood can affect social willingness and event tone.
- Hard activity blocks should be rare.
- Hard blocks are reserved for extreme Mood states or specifically authored events.

Rationale: Mood should matter without frequently removing player agency. Soft modifiers preserve emotional texture while keeping player choice central.

### D038 - Energy Gating Model

Decision: Energy is the main hard-gating wellbeing resource, with low-effort and recovery exceptions.

Details:

- Most activities have a minimum Energy requirement.
- If the player does not meet the Energy requirement, the activity is blocked or offered as a low-effort variant when appropriate.
- Recovery activities should remain available even at low Energy.
- Mandatory lessons still occur regardless of Energy.
- Low Energy reduces the effectiveness or available choices of lesson actions rather than skipping lessons.

Rationale: Energy should force meaningful planning while avoiding dead-end states where the player has nothing useful to do.

### D039 - Stress Effect Model

Decision: Stress is a soft but broad pressure modifier, not a hard fail state.

Details:

- Stress mainly worsens outcomes and efficiency.
- High Stress can increase Energy costs for difficult activities.
- High Stress can reduce study, lesson, or test performance.
- High Stress can feed negative Mood pressures such as anxiety, irritation, or overwhelmed.
- Very high Stress can trigger authored/event-like consequences.
- Stress does not cause game over by itself.

Rationale: Stress should create pressure across the life sim without replacing academic promotion as the hard fail gate.

### D040 - Lesson Risk Model

Decision: Lesson risk is determined by lesson/teacher strictness plus the selected action's risk profile.

Details:

- Each lesson or teacher has a strictness level.
- Each lesson action has a risk profile.
- The combination determines the chance or severity of being caught.
- The model should differentiate teachers/subjects without becoming a heavy stealth system.

Rationale: This provides enough tactical texture for lesson choices while keeping the system readable and repeatable.

### D041 - Qualitative Lesson Risk Display

Decision: Lesson risk is shown qualitatively rather than as exact percentages.

Details:

- Example risk labels: Safe, Low Risk, Risky, Very Risky.
- Tooltips can explain the meaning of a risk label.
- Exact percentages should not be shown by default.

Rationale: Qualitative risk keeps decisions readable without pushing the player into spreadsheet-style optimization.

### D042 - Caught During Lesson Consequences

Decision: Being caught during a risky lesson action causes a small immediate penalty by default, with larger consequences reserved for repeated behavior or strict teachers.

Possible consequences:

- Reduced or lost benefit from the current lesson block.
- Stress increase.
- Teacher impression decrease.
- Mood pressure such as embarrassment, irritation, or anxiety.
- Temporary restriction such as phone actions being unavailable for the rest of the lesson.
- Later consequence such as detention only for repeated issues, severe actions, or high-strictness teachers.
- Relationship impact if the risky action involved social interaction or texting.

Rationale: Risk should matter without making one failed action ruin the day. Escalation keeps repeated risky play meaningful.

### D043 - Teacher Impression

Decision: Teacher impression is a lightly visible modifier per teacher/subject, not a full relationship system.

Details:

- Teacher impression can influence lesson consequences, strictness response, academic support, or tolerance.
- Teacher impression should be visible enough for the player to understand it matters.
- Teacher impression should not become a classmate-style relationship arc system.

Rationale: Teachers should affect school pressure and lesson texture without competing with classmates as the primary relationship content.

### D044 - Lesson Action Set Direction

Decision: The baseline lesson action set should include learning the current subject, lower-effort academic participation, phone/social actions, rest/recovery, risky social/distraction actions, and studying another subject.

Details:

- Studying another subject during a lesson is lightly risky.
- This action lets the player trade current-class attention for preparation toward another deadline or weaker subject.
- Further lesson action ideation will be handed off to a Storyteller/narrative-focused session.

Rationale: Studying another subject creates a school-specific tradeoff that supports exams, time pressure, and the feeling of managing competing academic demands.

### D045 - Homework and Teacher Questions

Decision: Homework/deadlines and teacher questions should be included as intended game systems.

Details:

- Homework/deadline state can support lesson actions such as Finish Homework Quietly.
- Teacher questions/checks can support lesson actions such as Answer When Called.
- These systems should stay scoped to school pressure and lesson texture rather than becoming separate minigame layers.

Rationale: Homework and teacher questions reinforce the school fantasy and create useful pressure inside lessons and planning.

### D046 - Lesson Variant Tiers

Decision: Lesson actions use stable top-level categories with variants classified into three tiers: systemic, contextual, and authored-only.

Tiers:

- **Systemic:** reusable variants that can appear broadly under common conditions.
- **Contextual:** variants that depend on Mood, Energy, Stress, subject, teacher strictness, calendar pressure, nearby classmate, homework state, or teacher question context.
- **Authored-only:** unique actions written for specific classmate story arcs or authored events.

Details:

- Authored-only actions are not generic reusable variants.
- Authored-only actions should be specific to the story arc or event that introduces them.
- The player should still understand authored-only actions through the same broad lesson-action grammar.

Rationale: Tiering preserves the emotional richness from lesson variants while protecting the core system from UI bloat and content-scope creep.

### D047 - Teacher Questions / Checks

Decision: Teacher questions/checks are semi-predictable lesson events based on teacher/subject profile, not a separate minigame.

Details:

- Strict or question-heavy teachers have a known profile that signals higher check likelihood.
- The player does not know the exact moment of a question/check.
- Checks can occur after a lesson block or as a response to low attention/risky behavior.
- Attentive actions such as Focus, Take Notes, Follow the Thread, and Answer When Called improve outcomes.
- Distracted actions such as Phone, Chat, Micro-Nap, Risky Distraction, or Study Another Subject worsen outcomes.
- Check outcomes can affect subject gain, teacher impression, Stress, Mood pressure, or caught-like consequences.

Rationale: Semi-predictability makes teacher questions strategic rather than feeling like random punishment.

### D048 - Subject Grade as Academic State

Decision: Each subject has a current grade in the student's school journal from the start of the game. This replaces the proposed abstract per-subject backlog concept.

Details:

- Subject grade is not an arithmetic average of individual marks.
- Subject grade is a synthetic indicator of how the student is doing in that subject.
- Low grade signals that the player should invest more study/attention into that subject.
- Subject grade changes through lessons, studying, homework, tests, teacher impression, and lesson performance.
- Subject grade should be visible through the school journal/school app.

Rationale: A grade is a more diegetic and readable school-life signal than an abstract backlog meter.

### D049 - Homework Task Model

Decision: Homework is an occasional concrete task with a deadline and 0-100% completion progress.

Details:

- Homework appears periodically, not as daily spam for every subject.
- Homework is completed outside school through repeated `Do Homework` actions.
- Each `Do Homework` action adds progress.
- Some homework requires multiple sessions to reach 100%.
- At 100%, homework is ready to submit.
- Submitted homework can improve subject grade, teacher impression, Stress, and/or exam readiness.
- Missed or incomplete homework can hurt subject grade, teacher impression, Stress, and/or exam readiness.

Rationale: Homework should create concrete planning pressure without turning the game into a dense task manager.

### D050 - Knowledge, Grade, and Exam Model

Decision: Subject knowledge and subject grade are separate systems.

Details:

- Subject knowledge is the student's real preparation/understanding for a subject.
- Subject grade is the visible school-journal position for that subject.
- Subject grade affects Stress before exams and the final grade/standing.
- Subject grade should not be the main direct source of exam performance.

Rationale: Separating knowledge from grade lets the game model both actual preparedness and institutional school standing.

### D051 - Exam Result Factors

Decision: Exam results are primarily determined by subject knowledge, recent study/exam readiness, Energy, Stress, and Mood modifiers.

Details:

- Teacher impression or subject grade can provide context but should not dominate exam performance.
- Energy and Stress on exam day matter.
- Mood can softly modify performance or tone.

Rationale: Exams should reward long-term preparation while still making the player's current condition matter.

### D052 - Exam Week and Promotion Gate

Decision: Semester/end-of-year exams are exam periods with subject-level results, not one abstract test.

Details:

- Each relevant subject receives a result.
- Promotion requires every subject to reach the minimum passing grade.
- Example grade threshold: E still passes, F fails.
- If any subject fails at the end-of-year promotion gate, the year is failed.
- First-semester weakness affects grade/standing and signals risk, but can be corrected before the end-of-year gate.
- The passing threshold is visible through grades rather than exact percentages.

Rationale: Subject-level exams make academic planning concrete and preserve the school fantasy that one failed subject can endanger promotion.

### D053 - Relationship Bond Representation

Decision: Classmate relationships use an underlying 0-100 Bond value, shown to the player as named relationship tiers with a subtle progress indicator.

Details:

- The exact Bond number is not shown by default.
- The player sees relationship tiers such as acquaintance/friend/close friend equivalents.
- A subtle progress indicator can show movement toward the next tier.
- Bond value is used for balancing, unlocks, and milestone thresholds.

Rationale: This preserves tuning precision while avoiding overly numeric relationship farming.

### D054 - Five Relationship Tiers

Decision: Classmate relationships use 5 visible relationship tiers.

Details:

- Working tier names:
  1. Stranger
  2. Acquaintance
  3. Friend
  4. Close Friend
  5. Best Friend / Romantic Partner, depending on path and eligibility.
- Five tiers should be enough to support progression and milestone unlocks without over-fragmenting relationships.
- Romance is a path/state at the highest close relationship tier, not a sixth tier above friendship.

Rationale: A simpler tier structure keeps relationship progress readable and avoids artificial-feeling micro-ranks.

### D055 - Full Classmate Arc Beat Structure

Decision: A full classmate story arc targets 10 arc beats across the relationship progression, with post-commitment content for Best Friend or Romantic Partner paths.

Structure:

- Stranger: 1 beat.
- Acquaintance: 2 beats.
- Friend: 3 beats.
- Close Friend: 2 beats.
- Final Path, Best Friend or Romantic Partner: 2 beats.

Details:

- Becoming a Romantic Partner or Best Friend does not end the arc.
- The final path includes relationship content after that status is reached.
- The same high-level structure supports platonic and romantic paths.
- Not all 10 beats should be equally large.
- A full arc can use a mix such as 4 major beats and 6 minor beats.
- Final Path should include at least 1 major beat because it is a relationship payoff phase.

Rationale: Relationship status should open new story texture rather than act only as an end reward. Beat weighting keeps the 10-beat target emotionally rich but more production-realistic.

### D056 - Romance Availability

Decision: Not every core classmate needs to be romanceable.

Details:

- Romance availability is defined per classmate.
- Some classmates can support only platonic Best Friend final paths.
- Romance should exist where it fits the character, arc, and production scope.
- Non-romanceable classmates should still have meaningful final-path content.

Rationale: Selective romance supports stronger writing, clearer character identity, and better scope control than making every classmate romanceable.

### D057 - Romanceability Discovery

Decision: Romanceability is not shown as a hard badge from the start.

Details:

- The player discovers romantic possibility through dialogue, chemistry, choices, story moments, and relationship development.
- UI should avoid presenting classmates as a checklist of romance routes at first sight.
- Later in a closer relationship, the game can subtly signal whether a romantic path is becoming available.

Rationale: Discovering romance through relationship texture supports tone and character believability better than explicit upfront labels.

### D058 - Romance Exclusivity

Decision: Romance is exclusive/monogamous by default.

Details:

- The default game model does not support multiple simultaneous romantic partners.
- Exclusivity gives romantic commitment more emotional and mechanical weight.
- Authored exceptions are possible only if a specific character/arc meaningfully requires them.

Rationale: Exclusive romance better supports attachment, consequence, drama, and bittersweet reflection than relationship collecting.

### D059 - Romantic Path Includes Friendship Foundation

Decision: Entering a Romantic Partner path does not erase the friendship/trust foundation of that relationship, but final-path content branches between Best Friend and Romantic Partner versions.

Details:

- Romantic Partner still includes support, trust, and friendship.
- Best Friend and Romantic Partner are different final paths, not a hierarchy where romance is simply "better."
- The final 2 beats of a full classmate arc can branch by final path.

Rationale: Romance should deepen or transform the relationship rather than replace friendship, while still providing distinct authored content.

### D060 - Breakups Are Authored, Not a General System

Decision: The game does not need a full generic breakup system for v1.

Details:

- Romantic commitment is treated as a meaningful path decision.
- Relationship conflict can exist inside authored beats.
- Breakups can happen only as authored consequences in specific arcs if the story requires them.
- The game should not model breakup/reconciliation as a broad reusable relationship state machine.

Rationale: A generic breakup system would add substantial state complexity and content branching. Authored handling preserves emotional weight and scope control.

### D061 - Repeatable Relationship Interactions

Decision: Repeatable interactions form the relationship-growth layer between classmate story beats.

Baseline interaction types:

- Talk.
- Text.
- Hangout.
- Lunch together.
- Gift.
- Study together.
- Club/activity together.
- Check in / emotional support.
- Invite to event.

Details:

- Each classmate can have preferences for interaction types.
- Compatibility and protagonist favorites/preferences can modify interaction effects.
- Preferred interactions should grow relationships more efficiently or produce better Mood/Trust outcomes.
- Non-preferred interactions can still work, but less efficiently or with different tone.

Rationale: Relationship growth should feel personal to each classmate instead of a generic point grind.

### D062 - No Separate Trust Meter

Decision: The relationship system does not need a separate global Trust/Openness meter.

Details:

- Bond, relationship tier, story beats, and story flags carry relationship progression.
- Specific scenes can set story flags such as shared secret, accepted help, or opened up about a personal issue.
- Story flags can gate or alter later content.
- The game should avoid adding another relationship bar unless future design proves it necessary.

Rationale: A separate Trust meter would add complexity and redundancy. Story flags preserve emotional specificity better than another generic value.

### D063 - Story Beat Unlock Conditions

Decision: Classmate story beats unlock through Bond/tier plus contextual conditions, not Bond alone.

Possible conditions:

- Minimum Bond or relationship tier.
- Calendar timing.
- Classmate availability or location.
- Story flags from prior beats or choices.
- Current school/event context.
- Wellbeing or Mood as a tone modifier, rarely as a hard gate.

Rationale: Story beats should feel embedded in school life, schedules, places, and prior choices rather than firing as abstract menu unlocks.

### D064 - Smartphone as Diegetic Planning Hub

Decision: The smartphone is the main diegetic hub for information and planning outside direct activity selection.

Details:

- The phone shows what the player character knows, not the full truth of the world.
- The phone helps with planning, reminders, social navigation, messages, school information, map use, and classmate profiles.
- The phone can archive discovered information.
- The phone can surface hints, but should not fully solve NPC schedules or relationship opportunities.
- Discovering people still requires presence, interaction, and attention.

Rationale: The smartphone should support school-life navigation without replacing the relationship and discovery fantasy.

### D065 - Core Smartphone Apps

Decision: The core smartphone app set is Calendar, Messages, Social Media, School App / Journal, Map, and Settings.

Details:

- Calendar: plan dnia, lessons, deadlines, events, exams.
- Messages: texting, relationship threads, event invites.
- Social Media: combined feed and classmate profiles.
- School App / Journal: grades, homework, subject info, teacher notes.
- Map: locations, travel feasibility, known places and services.
- Settings: game options UI.

Rationale: Merging feed and profiles into Social Media matches the fiction and keeps the app set clean, while Settings handles game options through the phone UI.

### D066 - Settings as Phone App

Decision: Settings should be rendered as an app inside the smartphone UI.

Details:

- Game options appear through the phone's Settings app presentation.
- The Settings app should still prioritize usability, accessibility, and clarity.
- Diegetic styling should not make critical options harder to find or understand.

Rationale: Presenting settings as a phone app strengthens UI cohesion and supports the game's smartphone-as-hub fantasy.

### D067 - Smartphone Availability and Context Limits

Decision: The smartphone UI is always accessible, but context can restrict available apps.

Details:

- The player can always open the phone UI.
- During lessons and exams, only Settings is available by default.
- Other apps are blocked in these restricted contexts unless accessed through an explicit lesson action such as Use Phone/Text.
- Settings remains available for game options even during restricted contexts.

Rationale: This preserves access to game settings while preventing the player from bypassing lesson/exam phone restrictions through the general phone UI.

### D068 - Calendar Scope

Decision: Calendar shows known scheduled commitments and events, not live NPC presence hints.

Calendar includes:

- School schedule.
- Exams and deadlines.
- Planned meetings.
- School club meetings.
- Known global school events.
- Known town events such as festivals.
- Major known events such as prom.

Details:

- Unknown opportunities do not appear until discovered.
- NPC current locations are not a Calendar feature.

Rationale: Calendar should track planned time commitments and known events, while social discovery belongs to Social Media and in-world interaction.

### D069 - Social Media Current Location Signals

Decision: Known NPC current-location information is surfaced through Social Media profiles/feed in immersive form.

Details:

- Each known NPC profile can show current location when the player has enough knowledge/access.
- Current location information is accurate at the time it is viewed.
- Location information should appear diegetically, such as posts or feed updates about what the character is doing and where.
- Social Media should not reveal unknown NPCs or opportunities before the player has discovered them.
- The player can still miss the NPC if travel time means the NPC leaves before the player arrives.
- NPC location schedules are fixed per character, allowing the player to learn routines over time.

Rationale: Social Media is the main practical tool for finding known characters. Tension comes from travel time and learned routines rather than unreliable location data.

### D070 - Social Media Profile Access

Decision: Social Media profile information expands with relationship familiarity.

Details:

- After first meeting a classmate, the player can see a basic profile.
- Full current-location visibility becomes available from Acquaintance onward.
- More detailed profile information can unlock through relationship growth and discovered facts.
- Current location should not be locked too deeply because it is the main practical tool for finding known characters.

Rationale: The player must still discover people through the world first, but the phone becomes useful quickly once a basic relationship exists.

### D071 - Texting Time Cost

Decision: General texting is free and does not usually consume a 15-minute time block.

Details:

- Reading and replying to ordinary messages is generally free.
- Texting can still affect relationships, tone, Mood, or story context.
- Authored texting inside a story beat uses the time cost of that beat or activity.
- Texting should not become a constant micro-tax on time.

Rationale: Time should be spent on meaningful activities and authored moments, not every small message interaction.

### D072 - Texting Cooldowns and Authored Threads

Decision: Generic/systemic texting has cooldowns, while authored message threads belong to story beats.

Details:

- Generic SMS/text interactions can be free but limited by cooldown.
- Generic texting should not be spammable for unlimited Bond gain.
- Authored message threads are written content tied to classmate story beats or authored events.
- Authored threads follow the timing and cost rules of their associated beat/event.

Rationale: Cooldowns protect systemic balance, while authored threads preserve emotional specificity for important relationship moments.

### D073 - Map Scope

Decision: Map shows locations, travel time/feasibility, and objects/services in locations, but not NPC presence.

Details:

- The map can show known locations.
- The map can show travel time and whether travel/activity/return timing is feasible.
- Selecting a location from the map can begin travel.
- Activities are selected after arriving at the location.
- The map can show objects/services such as shops, cafes, and facilities in a location.
- The map should not show which NPCs are currently present in locations.
- NPC presence/current-location information belongs to Social Media.

Rationale: Map should support spatial planning without becoming a social radar. Keeping NPC location in Social Media preserves the distinct role of each app.

### D074 - School App / Journal Scope

Decision: School App / Journal functions as the academic dashboard.

Details:

- Shows subject grades.
- Shows homework and deadlines.
- Shows subject list and subject status.
- Shows teacher profiles/notes with light strictness and impression hints.
- Shows exam calendar and exam readiness.
- Can show lesson notes or study bonuses when relevant.
- Should remain a readable academic dashboard, not a dense text archive.

Rationale: School App / Journal should centralize academic pressure and planning while keeping information scannable.

### D075 - MVP Location Set

Decision: MVP location set includes core campus locations plus cafe, park, and convenience store.

MVP locations:

- Dorm.
- School entrance/courtyard/outdoor campus area.
- Classrooms.
- Hallway/common area.
- Cafeteria.
- Library/study room.
- Club room(s).
- Cafe.
- Park.
- Convenience store.

Details:

- Convenience store should support limited scoped functions such as snacks, coffee, small gifts, or preparation items.
- Convenience store should not become a large economy/shop system in MVP.
- Convenience store is nearby off-campus.
- Travel to convenience store costs 15 minutes one way.
- Cafe is nearby off-campus with 15-minute one-way travel.
- Park is nearby off-campus with 15-minute one-way travel.
- Nearby off-campus locations can be traveled between directly for 15 minutes in MVP.
- The player does not need to return to campus between nearby off-campus locations.

Rationale: Convenience store supports wellbeing, gifts, and school-life routine while fitting the small-town/campus-adjacent fantasy.

### D076 - MVP Club Count

Decision: MVP includes 2 school clubs.

Details:

- Two clubs are enough to show social/identity tradeoffs.
- One club would not demonstrate meaningful club choice.
- More than two clubs would likely over-expand MVP content.
- The two clubs should have meaningfully different identity and activity vibes.

Rationale: Two clubs prove the club system while preserving MVP scope discipline.

### D077 - MVP Club Selection

Historical decision: MVP clubs were initially Science Club and Drama Club.

Current status: Superseded by the 2026-06-27 club scope change. Active MVP clubs are Science Club and Literary Club; Drama Club remains a valid full-game/deferred candidate.

Details:

- Science Club provides an academic/technical curiosity identity space.
- Drama Club provides a social, expressive, performance-oriented identity space.
- The pair creates a strong contrast in activity style, social vibe, and player identity without requiring a sports-specific subsystem.

Rationale: Science Club and Drama Club test different forms of belonging and tradeoff while fitting the school-life fantasy and MVP scope.

### D078 - Multiple Club Membership

Decision: The player can join both MVP clubs, but fully engaging with both is constrained by time, schedule, and Energy.

Details:

- Club membership is not mutually exclusive by rule.
- Club schedules and activity costs create the real tradeoff.
- The player can sample both clubs or prioritize one.
- Full commitment to both should be possible but difficult and costly.

Rationale: Calendar pressure should create tradeoffs more naturally than arbitrary membership locks.

### D079 - Club Cadence

Decision: Each MVP club has 1 regular weekly meeting plus occasional special events/milestones.

Details:

- Regular club meeting duration: 60 minutes.
- Special event/milestone duration: 90 minutes by default.
- Regular meetings create routine participation.
- Special events create larger identity/social/story moments.

Rationale: Weekly 60-minute meetings make clubs present without overwhelming the schedule, while longer special events give club arcs weight.

### D080 - Club Progression Scope

Decision: Clubs do not use a separate progress/rank system.

Details:

- Clubs are driven by attendance and events.
- Clubs provide opportunities for authored events.
- Clubs help deepen relationships with other club members.
- Clubs can contribute to identity through participation, but should not become a separate progression grind.

Rationale: Clubs should function as social/story venues and activity contexts, not another independent system to optimize.

### D081 - MVP Event Scope

Decision: MVP event scope includes a small set of semester anchors.

MVP events:

- 1 school-wide event.
- 1 town event.
- 1 Science Club special event.
- 1 Drama Club special event. Superseded by the 2026-06-27 Literary Club change: the active MVP club special event is `Zine Deadline Crisis`.
- 1 exam period.

Rationale: This gives the MVP enough calendar texture and larger moments without over-expanding event content.

### D082 - Fixed Calendar Events vs Condition-Triggered Milestones

Decision: Events split into fixed calendar events and condition-triggered story/progression milestones.

Details:

- Calendar events such as town festivals, class holiday events, prom, and similar rituals occur on fixed dates.
- Fixed calendar events can be missed if the player chooses not to attend or cannot attend.
- Story arc events and progression milestones should not be fixed to one date.
- Story/progression milestones trigger when conditions are met and the first viable opportunity appears.
- This avoids permanently missing important story progress because the player had the wrong schedule on one date.

Rationale: Calendar rituals should make time feel real and sometimes missable, while relationship/progression milestones should respect player investment and trigger flexibly.

### D083 - Semester Length

Decision: A semester is 20 weeks in the design model.

Details:

- The GDD should treat 20 weeks as the canonical semester length.
- MVP pacing should be designed around this 20-week semester unless production planning later explicitly scopes a shorter vertical slice.

Rationale: A 20-week semester supports routine formation, relationship pacing, event cadence, and exam pressure without compressing the school-life fantasy.

### D084 - Semester Pacing Framework

Decision: The 20-week semester uses a six-phase pacing framework.

Phases:

- Weeks 1-2: Onboarding.
- Weeks 3-6: Routine Formation.
- Weeks 7-10: First Pressure Rise.
- Weeks 11-15: Mid-Semester Identity.
- Weeks 16-19: Exam Pressure.
- Week 20: Exam Period / Semester Resolution.

Rationale: This structure gives relationship beats, homework, clubs, events, and exam pressure room to escalate across the semester.

### D085 - MVP Relationship Beat Scope

Decision: MVP relationship content targets 7 relationship/story beats across 5 classmates.

Scope:

- 1 partial arc classmate with 3 beats.
- 4 other classmates with 1 early/introduction beat each.

Rationale: This proves relationship discovery, repeatable interactions, and partial story progression without attempting full classmate arcs in MVP.

### D086 - MVP Homework Cadence

Decision: MVP semester targets 6-8 homework tasks total.

Details:

- Early semester should usually have no more than 1 active larger homework at a time.
- Near exam pressure, up to 2 homework/deadline obligations can overlap.
- Each homework task requires 1-3 `Do Homework` sessions to complete.

Rationale: This creates academic planning pressure without turning homework into constant task spam.

### D087 - MVP Test Cadence

Decision: MVP semester includes 1 smaller test per subject before the exam period.

Details:

- MVP has 4 subjects.
- Each subject has 1 smaller test.
- Smaller tests occur across weeks 7-15.
- Week 20 remains the exam period.

Rationale: Smaller tests provide early academic feedback and pressure before semester exams.

### D088 - Relationship Tier Pacing

Decision: Relationship tier progression requires multiple meaningful interactions between story beats.

MVP guidance:

- Stranger to Acquaintance: roughly 2-3 meaningful interactions plus the relevant beat.
- Acquaintance to Friend: roughly 4-6 meaningful interactions plus the relevant beat.
- Later full-game tiers should take longer and scale with relationship depth.

Details:

- A story beat alone should not instantly carry the relationship without prior interaction.
- Repeatable interactions create the lived relationship rhythm between authored beats.

Rationale: Relationship progression should feel earned through repeated attention, not only through completing milestone scenes.

### D089 - Academic Difficulty and Starting Aptitudes

Decision: Academic difficulty depends meaningfully on the protagonist's starting attributes/aptitudes for each subject.

Guidance:

- A student with supportive attributes for a subject, who attends lessons regularly and studies occasionally, should usually earn good but not top grades in that subject.
- A student with average attributes, regular attendance, and occasional study should usually pass minimally.
- A student without supportive attributes may be at risk of failing if they only attend regularly and study occasionally.
- Top grades should still require deliberate planning, study, homework completion, condition management, and tradeoffs.

Rationale: Character creation should meaningfully shape academic strengths and weaknesses, making different protagonists require different planning strategies.

### D090 - Character Attributes as Soft Predispositions

Decision: Starting attributes describe broad character traits and translate indirectly into subject aptitude.

Examples:

- Charisma can support social ease, public presence, performance, flirting, group situations, and some social/humanities-oriented subjects.
- Empathy can support emotional support, reading people, conflict handling, trust-building, and deeper relationship moments.
- Logical thinking can support science/math-oriented subjects.
- Discipline can support memorization-heavy subjects, regular preparation, homework, and test readiness.
- Physicality can support physical education or physical activities.

Details:

- Attributes are soft but meaningful predispositions.
- Min-maxing is possible only to a limited degree.
- Attributes should not remove the need to study a subject for the whole game.
- Attributes matter most in early school years and near the start of the educational path.
- In later years, accumulated subject knowledge, time investment, and effort should become more important than starting attributes.

Rationale: Character creation should shape the student's starting profile without locking the whole education path. Lived effort should gradually matter more than initial aptitude.

### D091 - No Separate Memory Attribute

Decision: The game does not need a separate Memory attribute. Discipline covers school memorization and systematic preparation.

Details:

- Discipline covers regular study habits, concentration, homework completion, test preparation, and memorization-heavy learning.
- History or similar memory-heavy subjects can use Discipline as a primary attribute.
- Depending on subject flavor, History can also use Social Ability for social/cultural understanding or Logic for cause-effect analysis as secondary influences.

Rationale: A separate Memory stat would feel too narrow and school-mechanical. Discipline better fits a character trait while covering the gameplay need.

### D092 - Charisma and Empathy Attribute Split

Decision: Replace Social Ability with Charisma and keep Empathy as a separate attribute.

Details:

- Charisma covers social ease, small talk, group presence, confidence, flirting, public performance, and surface-level social navigation.
- Empathy covers emotional sensitivity, listening, support, reading people, conflict handling, trust-building, and deeper relationship moments.
- A high-Charisma/low-Empathy character can be socially magnetic but emotionally careless.
- A low-Charisma/high-Empathy character can be socially awkward but deeply supportive and perceptive.

Rationale: Social Ability and Empathy overlapped too much. Charisma vs Empathy creates a clearer player-facing choice during character creation.

### D093 - Attribute Point Buy

Decision: Character creation uses a point-buy system for starting attributes.

Details:

- Attributes use a simple 1-5 starting scale.
- There are 6 attributes.
- Baseline is 2 in each attribute.
- The player receives 6 additional points to distribute.
- Minimum attribute value is 1.
- Maximum starting attribute value is 5.
- The player can lower any number of attributes from baseline 2 to 1 to gain additional points.
- Example distributions include balanced 3/3/3/3/3/3 or more specialized 5/4/3/2/2/2.
- Point buy is accepted over a pure strengths/weaknesses selection model.
- The system should avoid encouraging excessive min-max spreadsheet behavior.

Rationale: Point buy gives the player authorship over the protagonist's starting profile while keeping attributes readable.

### D094 - Attributes Do Not Increase During Play

Decision: Starting attributes do not grow during the game.

Details:

- Attributes are the protagonist's starting profile.
- Character development happens through subject knowledge, grades, relationships, story flags, identity markers, habits, and player choices.
- The game should avoid attribute leveling.

Rationale: The design promise is that identity gains meaning through lived choices, not through raising base character stats.

### D095 - Favorites / Preferences Categories

Decision: Character creation uses 5 favorites/preferences categories.

Categories:

- Social Preference.
- Free Time Preference.
- Music Preference.
- Media Preference.
- Food Preference.

Details:

- Preferences personalize the protagonist and provide soft hooks for Mood, relationship chemistry, gifts, hangouts, dialogue, texting, social media, and activity flavor.
- Preferences do not provide direct attribute bonuses.
- Preferences do not hard-lock major story or relationship content.
- Preferences do not replace relationship effort.
- Social Preference and Free Time Preference are high compatibility-use categories.
- Music and Media are medium compatibility-use categories.
- Food is low-to-medium compatibility and strong gift/flavor support.
- Preferences are fixed after character creation for MVP.
- Evolving preferences are deferred.

Rationale: Five categories create a specific starting identity without turning favorites into a second attribute system.

### D096 - Art Direction Format

Decision: Use a hybrid visual approach: top-down RPG-like pixel art for maps/world navigation, with larger high-resolution character portraits for dialogue and emotional expression.

Details:

- Maps use top-down pixel art.
- World sprites and locations follow an RPG-like pixel-art presentation.
- Character portraits are larger, high-resolution illustrations.
- Portraits use a cohesive, slightly comic-inspired style.
- Portraits support multiple expressions.

Rationale: This approach keeps world production manageable while giving relationship/dialogue scenes enough emotional nuance.

### D097 - MVP Portrait Expression Set

Decision: MVP core classmates use a baseline set of 6 portrait expressions.

Baseline expressions:

- Neutral.
- Happy.
- Sad.
- Angry/Irritated.
- Embarrassed/Nervous.
- Thoughtful/Serious.

Details:

- The partial arc classmate can receive 1-2 additional special expressions if needed.
- Expression scope should be controlled because each core classmate multiplies portrait production cost.

Rationale: Six expressions cover most school-life dialogue tone while keeping portrait scope manageable.

### D098 - Player Character Visual Scope

Decision: The player character has customizable pixel-art sprite appearance but no high-resolution portrait set in MVP.

Details:

- Player sprite customization includes hair, outfit, eye color, and skin color.
- High-resolution portraits are reserved for NPCs whose appearance is known in advance.
- Player portraits would require too much art coverage for customization combinations.

Rationale: This preserves player visual customization while avoiding a large portrait production burden.

### D099 - Dialogue Presentation Modes

Decision: Dialogue UI has two presentation modes: large portrait dialogue and small in-world speech bubble dialogue.

Modes:

- **Large dialogue mode:** portrait and dialogue box occupy most of the screen; the map/background is slightly darkened. Used for important conversations, relationship scenes, and authored beats.
- **Small dialogue mode:** small dialogue box / speech-bubble-like UI appears above the speaking character's sprite, without portrait. Used for short lines, barks, quick exchanges, and low-weight interactions.

Details:

- The player character has no dialogue portrait or avatar.
- NPC portraits appear in large dialogue mode.
- Small dialogue mode uses sprites and text only.

Rationale: Two dialogue modes allow the game to distinguish emotionally important scenes from lightweight in-world chatter while controlling portrait usage.

### D100 - Audio Direction

Decision: Audio direction uses a hybrid approach: subtle ambient/cozy background music for routine play, with stronger melodic themes for events, relationships, exams, and dramatic moments.

Details:

- Routine school life should lean lo-fi, cozy, and ambient.
- Relationship scenes can use more emotional character or mood themes.
- Exams and high-pressure moments can use stronger tension cues.
- Events can use more memorable melodic tracks.

Rationale: Hybrid audio supports long daily-loop play without fatigue while giving important moments stronger emotional identity.

### D101 - Character Music Scope

Decision: MVP does not require full leitmotifs or unique tracks for every core classmate.

Details:

- The MVP partial arc character can have one relationship theme or motif.
- Other classmates can use shared relationship/mood music in MVP.
- Full game can revisit character-specific motifs for major classmates if audio scope allows.

Rationale: Character-specific music is emotionally valuable but scales quickly with cast size. MVP should reserve bespoke audio for the deepest relationship content.

### D102 - Technical Platform Targets

Decision: Technical targets remain PC/Steam first, Godot engine, Steam Deck desirable but not mandatory, with mobile/console/web out of first release.

Details:

- First release target: PC through Steam.
- Engine: Godot.
- Steam Deck support is desirable if achievable with low additional cost.
- Mobile, console, and web are out of scope for first release.
- GDD should stay at platform/performance/asset-budget level and avoid architecture decisions.

Rationale: These targets align with the brief and solo-dev scope while preserving room for architecture work later.

### D103 - Performance Targets

Decision: Use measurable 2D-appropriate performance targets.

Targets:

- PC: 60 FPS sustained during normal gameplay.
- Steam Deck, if supported: 60 FPS at 720p.
- Scene/location transition: under 2 seconds.
- Day transition: under 3 seconds.
- Save/load: under 3 seconds.
- Measurement loop: 10-minute representative loop with school navigation, lesson, phone, relationship dialogue, travel, and day transition.

Rationale: These targets are realistic for a 2D game and give architecture/QA concrete measurement goals.

### D104 - Save System

Decision: Save system uses day-boundary autosaves, safe-context manual saves, and large academic-failure checkpoints.

Details:

- Autosave at start of day.
- Autosave at end of day / sleep.
- Manual save only in safe contexts such as dorm or free exploration.
- Manual save should not occur during exams, lessons, action resolution, or other unstable states.
- End-of-year academic failure offers large checkpoint recovery: 1 week before, 4 weeks before, or semester start.

Rationale: The save model supports calendar-based play and recovery from academic failure without encouraging manipulation of every micro-choice.

### D105 - MVP Success Metrics

Decision: MVP success metrics are playtest targets, not hard business KPIs.

Gameplay targets:

- 80% of testers understand by the end of week 1 that time is the main cost.
- 60-70% of testers feel lessons belong to the school-life fantasy rather than feeling like a detached minigame.
- 70% of testers intentionally choose 1-2 classmates they want to know better.
- 70% of testers understand how Energy, Stress, and Mood affect planning.
- 60% of testers want to play another day to see story/social/academic consequences.
- A typical in-game day stays around 30 minutes.
- 70% of testers can name one missed opportunity they cared about.

Rationale: These metrics test whether the core emotional and systemic promises are landing in player experience.

### D106 - Out of Scope Boundaries

Decision: Out of Scope is divided into MVP, v1, and deferred/post-launch boundaries.

MVP excludes:

- Full 3-year run.
- Full ending / graduation / life-path summary.
- Full classmate arcs.
- Generic breakup system.
- More than 5 classmates.
- More than 2 clubs.
- Full romance system across many characters.
- More than 3 off-campus locations.
- Mobile/console/web.
- Full Steam Deck support as requirement.
- Full character-specific soundtrack.
- High-resolution player portraits.
- Large economy/shop system.

v1 excludes:

- Combat/dungeons/supernatural Persona-like layer.
- Farming/crafting production loop.
- School management/building.
- Open sandbox mode.
- Online multiplayer.
- Procedural school generation.
- User-generated content tools.
- Mobile-first design.

Deferred/post-launch:

- Mobile port.
- Expanded summer break.
- Extra classmates/clubs.
- Extra town locations.
- More epilogue variants.
- Character-specific leitmotifs.
- Evolving preferences.
- Expanded cosmetic and personal customization.

Rationale: Explicit scope boundaries protect the solo-dev MVP and prevent unrelated genre expectations from creeping into the project.

### D021 - Daily Time Boundaries

Decision: A playable day runs from 06:00 wake-up to 22:00 sleep.

Details:

- The day always starts at 06:00.
- The day ends no later than 22:00 when the player goes to sleep.
- The player may choose to go to sleep earlier.
- The final hour before latest sleep is dorm-only activity time.
- On normal school nights this is 21:00-22:00.
- On Friday and Saturday nights this is 23:00-24:00.
- The player must return to the dorm by 21:00.

Rationale: Fixed day boundaries make planning legible and reinforce school-life routine.

### D024 - Early Sleep as Recovery Choice

Decision: The player can go to sleep before 22:00.

Details:

- Early sleep ends the current day.
- Early sleep trades remaining activity time for improved recovery.
- Early sleep is one of the main ways to manage Energy.

Rationale: Sleep should be a meaningful recovery decision rather than only an automatic day boundary.

### D025 - Alarm and Snooze Wake-Up

Decision: The day has a 06:00 alarm, but the player can choose to snooze and wake later when the schedule allows it.

Details:

- The alarm always rings at 06:00.
- Default wake-up is 06:00.
- The player can snooze in 15-minute increments.
- The latest valid wake-up is calculated from the first required school commitment minus minimum preparation and travel time.
- Snooze can restore additional Energy or reduce fatigue compared to waking immediately.
- Snooze costs morning activity time.
- Waking up close to the school deadline can increase Stress or remove morning opportunities.
- Snooze cannot allow truancy or make the player miss mandatory school attendance.

Rationale: Snooze lets the player trade morning agency for recovery while preserving school structure.

### D026 - Abstract Morning Routine

Decision: Morning routine is abstract, not a set of individual selectable activities.

Details:

- Minimum morning routine time is 15 minutes and is used for wake-up validation and schedule feasibility.
- The player does not individually choose routine actions such as showering, brushing teeth, or getting dressed.
- Playable morning agency exists only in time that remains after the abstract routine and travel requirements.

Rationale: Morning routine should support schedule pressure without becoming its own micro-simulation.

### D022 - Mandatory School Attendance and Campus Lock

Decision: The player must attend school on school days and cannot skip school.

Details:

- School hours follow the day's class schedule, e.g. 07:00-15:00.
- The game blocks activities that would prevent timely school attendance.
- Availability checks can consider travel time to a destination and return travel time to school.
- Once the player arrives at school, they cannot leave school grounds until lessons are finished.
- During school hours, available actions are limited to lesson actions, break activities, and school-ground activities.

Rationale: The fantasy is about living within school structure, not opting out of it. The restriction also preserves calendar pressure and makes lesson systems central.

### D023 - Free Time Bound by Curfew and Travel

Decision: After school, the player has free time but must respect travel feasibility and the 21:00 dorm return requirement.

Details:

- After lessons end, the player can choose off-school, social, study, recovery, fun, club, or event activities if travel and time allow.
- The game checks whether the player can complete travel, activity time, and return to dorm before 21:00.
- Activities that cannot fit before curfew are unavailable or blocked.
- From 21:00 to 22:00, the player can only perform dorm activities before sleep.

Rationale: Free time should feel open while still producing tradeoffs around distance, duration, and evening routine.

### D027 - Campus Zone and School Access

Decision: The school, dorm, and shared outdoor area form a single campus zone. Moving from dorm to school within this zone does not consume a 15-minute travel block.

Details:

- The campus zone contains the school building, dorm, and outdoor location with entrances to both.
- Entering school from the dorm/campus outdoor area does not cost a travel block.
- Travel cost matters when the player leaves the campus zone for farther locations.
- If the player leaves campus before lessons, the game validates whether there is enough time to return to the school/campus area before mandatory school commitments.

Rationale: Dorm proximity should make school attendance feel natural while preserving travel tradeoffs for off-campus choices.

### D028 - Morning Off-Campus Feasibility

Decision: The player can leave campus before school if the schedule math allows them to return in time.

Details:

- Morning off-campus actions are permitted when travel out, activity duration, return travel, and school start timing fit.
- The game blocks only infeasible choices, not off-campus movement by rule.
- Mandatory school attendance remains enforced.

Rationale: The time system should govern feasibility directly: the question is whether the player can make it back, not whether off-campus movement is categorically allowed.

### D029 - No Voluntary Lateness

Decision: The normal time system does not allow voluntary lateness to mandatory lessons.

Details:

- Activities that would make the player late for mandatory school commitments are blocked.
- The game does not model partial lateness as a regular player choice.
- Truancy remains unavailable.
- Lateness can exist only as a scripted/authored event consequence when explicitly designed.

Rationale: Voluntary lateness would blur the boundary between allowed lateness and unavailable truancy, add significant rule complexity, and weaken school attendance as a clear daily anchor.

### D030 - Lunch Break

Decision: School days always include a fixed special 45-minute lunch break from 12:00 to 12:45.

Details:

- Lunch break contains 3 blocks of 15 minutes.
- Lunch break always occurs at 12:00-12:45 regardless of the day's lesson plan.
- Lunch break is distinct from normal 15-minute breaks.
- Lunch break remains on school/campus grounds.
- The cafeteria is a key lunch location.
- Cafeteria actions can restore Energy.
- Lunch can also support social and relationship actions.

Rationale: A fixed 45-minute lunch is realistic, predictable, and long enough to create meaningful recovery/social choices without turning the school day into unrestricted free time.

### D031 - Lessons Per School Day

Decision: School days vary in lesson load.

Details:

- Typical school day: 5-6 lessons.
- Light school day: 4 lessons.
- Heavy school day: 7 lessons.
- Each lesson remains 45 minutes, split into 3 fifteen-minute action blocks.
- Normal post-lesson breaks remain 15 minutes unless replaced by the fixed lunch break.

Rationale: Variable lesson load creates different daily rhythms while preserving predictable lesson and break structure.

### D032 - Weekend Structure

Decision: Weekends have a looser structure than school days, with later sleep allowed on Friday and Saturday nights and later wake-up possible on Saturday and Sunday.

Details:

- Monday-Thursday use normal school-day boundaries: 06:00 alarm and 22:00 latest sleep.
- Friday remains a school day, but Friday night can extend to 24:00 latest sleep.
- Saturday is a free day with latest sleep at 24:00.
- Sunday is a free day but returns to 22:00 latest sleep because Monday school pressure returns.
- Friday and Saturday curfew/return-to-dorm is 23:00.
- Sunday curfew/return-to-dorm returns to 21:00.
- The final hour before latest sleep is always dorm-only wind-down time.
- Weekend structure should create a different rhythm without becoming a separate simulation mode.

Rationale: Weekends should feel freer and more socially/recovery oriented while preserving the game's time-pressure identity.

### D033 - Weekend Wake Plan

Decision: On Friday and Saturday nights, the player chooses a weekend wake plan for the next morning rather than receiving a fixed weekend alarm.

Wake plan examples:

- Early Start: 06:00-07:00, more usable day time, less recovery.
- Normal Weekend: 08:00, balanced time and recovery.
- Sleep In: 09:00-10:00, more recovery, less usable day time.

Details:

- Saturday and Sunday wake-up follows the selected wake plan.
- Maximum weekend sleep-in target is 10:00.
- If Energy reaches cap before the wake target, additional sleep gives little or no Energy but can continue reducing Stress.
- The UI should communicate diminishing Energy returns and possible Stress recovery when choosing a wake plan.

Rationale: Weekend wake-up should be a player decision about time vs recovery, not an automatic default that wastes usable Energy.

---

## Deferred Decisions

### O001 - Classroom Moment-to-Moment Model

Status: Resolved in coached GDD.

Current stance: Each lesson is a 45-minute phase with 3 fifteen-minute action blocks. Each block asks the player to choose one lesson-specific action from systemic, contextual, or authored-only variants.

### O002 - Mood in MVP

Status: Resolved in coached GDD.

Current stance: Energy, Stress, and Mood are all full systems. Mood is qualitative, derived from hidden emotional pressures/tags, and should ship as part of the MVP planning model.

### O003 - Romance Rules

Status: Resolved for MVP/GDD; full-game cast details deferred to narrative design.

Current stance: Romance is selective per character, discovered through story rather than shown as an upfront badge, exclusive by default, and does not replace friendship. Each classmate has an authored orientation, and romance is available only for a compatible protagonist. MVP romanceability is defined for the 5 MVP classmates. The full-game romanceable cast and any character-specific breakup/jealousy cases are deferred to narrative design.

### O004 - Classmate Arc Structure

Status: Resolved for design target.

Current stance: A full classmate arc targets 10 beats: 1 Stranger, 2 Acquaintance, 3 Friend, 2 Close Friend, and 2 final-path Best Friend/Romantic Partner beats. MVP proves 1 partial arc with 3 beats and 4 additional early/introduction beats.

### O005 - NPC Schedule Visibility

Status: Resolved for GDD.

Current stance: Social Media profiles/feed show current location for known Acquaintance-or-higher NPCs in an immersive form. Map does not show NPC presence. Fixed NPC routines remain learnable through repeated observation.

### O006 - MVP Event Cadence

Status: Resolved for current MVP scope.

Current stance: MVP includes 1 school-wide event, 1 town event, 1 Science Club special event, 1 Literary Club special event, and the Week 20 exam period. Fixed calendar events can be missable; story/progression beats trigger at the first viable opportunity once conditions are met.

---

## Version History

### v0.1 - 2026-06-25

Created initial GDD, epics, and decision log from the approved brief and supporting brainstorming session.

### Process correction - 2026-06-25

Designer corrected the working mode from Express to coached/facilitative. Existing draft artifacts remain on disk as scratch/reference material until coached decisions replace or confirm them.

### Pillars confirmation - 2026-06-25

Designer confirmed all four proposed game pillars as true pillars.

### Pillar refinement - 2026-06-25

Designer refined pillar 2 around relationship interactions plus scripted classmate story-arc milestones, with compatibility acting as a growth accelerator. Designer refined pillar 3 so starting identity includes stats and favorites, while richer identity emerges during play.

### Brief reconciliation - 2026-06-25

Updated brief and companions were reviewed. They now include the relationship progression and starting/lived identity corrections from the coached GDD session.

### Core loop refinement - 2026-06-25

Designer clarified that planning is player-initiated at any time, lessons are a distinct restrictive action-selection loop with 3 fifteen-minute lesson blocks plus a 15-minute break, relationship milestones are rare payoffs above the daily loop, and consequences resolve continuously after activities with only compact end-of-day summary.

### Loop motivation refinement - 2026-06-25

Designer confirmed all proposed one-more-day hooks matter. The primary pull is curiosity about classmate story arcs plus optimization of daily choices to keep wellbeing high while improving academic results.

### Outcome model refinement - 2026-06-25

Designer confirmed that semester exams exist, first-semester failure is a warning rather than a run-ending fail, and end-of-year exam failure ends the game unless the player loads a large checkpoint.

### Wellbeing outcome refinement - 2026-06-25

Designer clarified that wellbeing does not cause game over. It mainly worsens outcomes or prevents actions, especially through Energy as the resource required to perform activities.

### Daily structure refinement - 2026-06-25

Designer clarified that each day runs 06:00-22:00, school attendance is mandatory with no truancy, the player cannot leave school grounds during lessons, post-school free time must allow return to dorm by 21:00, and 21:00-22:00 is dorm-only activity time.

### Sleep refinement - 2026-06-25

Designer clarified that the player can go to sleep earlier than 22:00.

### Wake-up refinement - 2026-06-25

Designer approved a 06:00 alarm with optional 15-minute snooze increments, validated against first lesson timing, minimum preparation, and travel.

### Morning routine refinement - 2026-06-25

Designer clarified that morning routine is abstract rather than composed of selectable routine actions.

### Morning routine duration - 2026-06-25

Designer set minimum abstract morning routine duration to 15 minutes.

### Campus travel refinement - 2026-06-25

Designer clarified that dorm, school, and a shared outdoor entrance area form one campus zone. Dorm-to-school movement does not consume a travel block; travel costs apply when leaving campus.

### Morning off-campus refinement - 2026-06-25

Designer clarified that the player may leave campus before school if travel and activity timing allow return before mandatory lessons.

### Lateness refinement - 2026-06-25

Designer accepted that voluntary lateness should not be part of the normal system. The game blocks actions that would cause lateness; lateness may exist only in authored events.

### Lunch break refinement - 2026-06-25

Designer accepted a fixed 45-minute lunch break from 12:00-12:45 with cafeteria activities that can restore Energy.

### Lesson load refinement - 2026-06-25

Designer accepted typical days having 5-6 lessons, light days 4 lessons, and heavy days 7 lessons.

### Weekend structure refinement - 2026-06-25

Designer accepted looser weekend rules: Friday/Saturday late sleep, Friday/Saturday 23:00 curfew, Sunday 21:00 curfew, Saturday/Sunday later wake-up, and weekend wake plan choices. Designer especially approved longer sleep reducing Stress even when Energy is already full.

### Dorm wind-down refinement - 2026-06-25

Designer confirmed that the final hour before latest sleep is always dorm-only: 21:00-22:00 on normal nights and 23:00-24:00 on Friday/Saturday nights.

### Wellbeing resource refinement - 2026-06-25

Designer confirmed that Energy, Stress, and Mood should all be maintained as full systems rather than deferring Mood from MVP.

### Wellbeing representation refinement - 2026-06-25

Designer clarified that Energy and Stress are underlying 0-100 values shown as bars/icons with descriptions, while Mood is represented by qualitative states such as happy, sad, and depressed.

### Mood model refinement - 2026-06-25

Designer accepted Mood as a qualitative state derived from hidden emotional pressures/tags rather than a one-dimensional good/bad meter.

### Mood effect refinement - 2026-06-25

Designer accepted Mood as primarily a soft modifier, with hard blocks only for extreme states or authored events.

### Energy gating refinement - 2026-06-25

Designer accepted Energy as the main hard-gating resource: most activities require Energy, some can offer low-effort variants, recovery remains available, and lessons continue with reduced effectiveness/options.

### Stress model refinement - 2026-06-25

Designer accepted Stress as a soft broad modifier: it worsens outcomes, can raise Energy costs, weakens academic performance, feeds negative Mood pressures, and may trigger authored consequences at very high levels.

### Lesson risk refinement - 2026-06-25

Designer accepted lesson risk as a combination of teacher/lesson strictness and action risk profile.

### Lesson risk display refinement - 2026-06-25

Designer accepted qualitative lesson risk display rather than exact percentages.

### Lesson caught consequence refinement - 2026-06-25

Designer accepted small immediate penalties for being caught, with larger consequences for repeated behavior or strict teachers.

### Teacher impression refinement - 2026-06-25

Designer accepted teacher impression as a lightly visible modifier rather than a full teacher relationship system.

### Lesson action refinement - 2026-06-25

Designer added studying another subject during a lesson as a lightly risky action. Additional lesson-action ideation is being handed off to a Storyteller session.

### Lesson variant handoff review - 2026-06-25

Reviewed `_bmad-output/planning-artifacts/gdds/gdd-High School Story-2026-06-25/lessons-and-academics/response-from-story-team--lesson-action-variants.md`. Designer confirmed homework and teacher questions are desired systems. Designer accepted three lesson variant tiers: systemic, contextual, and authored-only, with authored-only actions being unique to specific story arcs.

### Lesson variant table - 2026-06-25

Condensed the lesson action variants handoff into `gdd.md` as a tiered table covering category, variant, tier, role, and risk.

### Teacher questions refinement - 2026-06-25

Designer accepted teacher questions/checks as semi-predictable events based on teacher/subject profile, with exact timing not shown.

### Homework and grades refinement - 2026-06-25

Designer replaced abstract per-subject backlog with subject grade as a synthetic journal indicator. Designer accepted homework as occasional concrete 0-100% progress tasks completed through repeated `Do Homework` actions.

### Academic model refinement - 2026-06-25

Designer confirmed knowledge and grade are separate systems; grade affects pre-exam Stress and final grade/standing; exams are subject-level periods; every subject must meet a minimum passing grade such as E, while F fails; first-semester weakness carries forward but can be corrected.

### Relationship representation refinement - 2026-06-25

Designer accepted underlying 0-100 Bond with visible named relationship tiers and subtle progress indicator, without exact numbers shown by default.

### Relationship tier refinement - 2026-06-25

Designer chose to simplify relationship tiers to 5 visible tiers.

### Relationship tier naming - 2026-06-25

Designer set tier 5 as Best Friend / Romantic Partner depending on relationship path.

### Classmate arc structure - 2026-06-25

Designer accepted a 10-beat full classmate arc structure: 1 Stranger, 2 Acquaintance, 3 Friend, 2 Close Friend, 2 Final Path beats. Romantic Partner/Best Friend status includes post-commitment content rather than ending the arc.

### Romance availability - 2026-06-25

Designer accepted that only selected classmates need to be romanceable.

### Romanceability discovery - 2026-06-25

Designer accepted that romanceability should be discovered through relationship development rather than shown as a hard badge from the start.

### Romance exclusivity - 2026-06-25

Designer accepted romance as exclusive/monogamous by default, with authored exceptions only if a specific arc meaningfully requires them.

### Romantic path structure - 2026-06-25

Designer accepted that Romantic Partner includes friendship/trust foundation, while final-path content branches between Best Friend and Romantic Partner.

### Breakup scope - 2026-06-25

Designer accepted no generic breakup system for v1; breakups can exist only as authored arc-specific consequences if needed.

### Repeatable interaction refinement - 2026-06-25

Designer accepted baseline repeatable interactions and classmate-specific preferences modified by compatibility and protagonist favorites/preferences.

### Trust meter scope - 2026-06-25

Designer confirmed no separate Trust/Openness meter is needed; Bond, tiers, story beats, and story flags carry relationship progression.

### Story beat unlock refinement - 2026-06-25

Designer accepted story beat unlocks based on Bond/tier plus contextual conditions such as calendar, location, availability, and story flags.

### Smartphone hub refinement - 2026-06-25

Designer accepted the smartphone as the main diegetic information/planning hub, with the constraint that it shows player-known information rather than full world truth.

### Smartphone app set refinement - 2026-06-25

Designer set the core smartphone apps as Calendar, Messages, Social Media, School App / Journal, Map, and Settings. Social Media combines feed and profiles.

### Settings app refinement - 2026-06-25

Designer chose to render game options as a Settings app inside the phone UI, while preserving usability.

### Smartphone availability refinement - 2026-06-25

Designer decided the phone UI is always accessible, but during lessons and exams only Settings is available unless another app is accessed through an explicit contextual action.

### Calendar and social media refinement - 2026-06-25

Designer clarified that Calendar contains known schedules, meetings, clubs, and school/town events, while NPC current locations are shown through Social Media profiles/feed in immersive post-like form.

### Social media location accuracy - 2026-06-25

Designer clarified that Social Media current-location information is always accurate when viewed and is the main way to find known characters, but travel time can cause the player to miss them. NPC schedules are fixed per character and learnable.

### Social media profile access - 2026-06-25

Designer accepted basic profiles after first meeting and full current-location visibility from Acquaintance onward.

### Texting cost refinement - 2026-06-25

Designer clarified that general texting is free. Authored texting inside a story beat carries the time cost of that beat rather than a separate generic texting cost.

### Texting structure refinement - 2026-06-25

Designer clarified that generic/systemic SMS interactions use cooldowns, while authored message threads are tied to story beats.

### Map scope refinement - 2026-06-25

Designer accepted Map as a location/travel/object tool, not an NPC presence tool. NPC locations remain in Social Media.

### School app refinement - 2026-06-26

Designer accepted School App / Journal as the academic dashboard: grades, homework, subjects, teacher hints, exam readiness, and relevant notes/study bonuses.

### MVP location refinement - 2026-06-26

Designer added convenience store to the MVP location set alongside campus, cafe, and park.

### Convenience store travel - 2026-06-26

Designer accepted convenience store as nearby off-campus with 15-minute one-way travel.

### Cafe and park travel - 2026-06-26

Designer accepted cafe and park as nearby off-campus locations with 15-minute one-way travel.

### Nearby off-campus travel - 2026-06-26

Designer accepted that nearby off-campus locations can be traveled between directly in 15 minutes without returning to campus.

### Off-campus location roles - 2026-06-26

Designer accepted MVP off-campus roles: Cafe for hangouts/dates/study/coffee/Mood, Park for Stress reduction/calm/walks/emotional conversations/recovery, Convenience Store for snacks/coffee/gifts/prep items/errands.

### Campus location roles - 2026-06-26

Designer accepted MVP campus roles: Dorm for sleep/homework/study/texting/recovery/wind-down, Outdoor/courtyard for quick social/transitions/atmosphere/breaks, Hallway/common area for between-class interactions/rumors/chance encounters, Classrooms for lessons/teacher questions/academic actions, Cafeteria for lunch/Energy/social observation, Library/study room for focused academic work, Club rooms for club activities/story/identity.

### MVP club count - 2026-06-26

Designer accepted 2 clubs for MVP.

### MVP club selection - 2026-06-26

Designer selected Science Club and Drama Club as MVP clubs.

### Club membership refinement - 2026-06-26

Designer accepted that the player can join both clubs, with time/schedule/Energy creating the tradeoff.

### Club cadence refinement - 2026-06-26

Designer accepted 1 regular weekly meeting per MVP club, 60 minutes each, with 90-minute special events/milestones.

### Club progression scope - 2026-06-26

Designer clarified that clubs use attendance + events only, with no separate club progress/rank system.

### MVP event scope - 2026-06-26

Designer accepted MVP event scope: 1 school-wide event, 1 town event, 1 special event per club, and 1 exam period.

### Event timing model - 2026-06-26

Designer clarified that calendar/ritual events are fixed in the calendar, while story arc and milestone events are condition-triggered at the first viable opportunity.

### Semester length - 2026-06-26

Designer confirmed semester length remains 20 weeks in the design model.

### Semester pacing - 2026-06-26

Designer accepted the 20-week pacing framework: Onboarding, Routine Formation, First Pressure Rise, Mid-Semester Identity, Exam Pressure, Exam Period/Semester Resolution.

### MVP relationship beat scope - 2026-06-26

Designer accepted MVP relationship scope: 1 partial arc classmate with 3 beats plus 4 other classmates with 1 early/introduction beat each.

### MVP homework cadence - 2026-06-26

Designer accepted 6-8 homework tasks total for MVP semester, each requiring 1-3 sessions, with limited overlap except near exam pressure.

### MVP test cadence - 2026-06-26

Designer accepted 1 smaller test per MVP subject before the exam period, distributed across weeks 7-15.

### Relationship pacing - 2026-06-26

Designer accepted that relationship tiers require several meaningful interactions between story beats, with early MVP guidance of 2-3 interactions to Acquaintance and 4-6 to Friend.

### Academic difficulty refinement - 2026-06-26

Designer clarified that academic outcomes depend on starting attributes/aptitudes: supportive attributes plus regular attendance and occasional study produce good grades, average attributes produce minimal passing, and weak aptitude can create failure risk.

### Character attribute refinement - 2026-06-26

Designer clarified that attributes describe broad character traits that indirectly support subject groups, act as soft predispositions, matter most early, and should be outweighed over time by study effort and accumulated subject knowledge.

### Discipline and memory-heavy subjects - 2026-06-26

Designer accepted that Discipline covers memorization-heavy subjects and preparation; no separate Memory attribute is needed.

### Charisma and empathy split - 2026-06-26

Designer chose to rename Social Ability to Charisma and keep Empathy as a separate attribute after clarifying the difference between social magnetism and emotional understanding.

### Attribute creation model - 2026-06-26

Designer accepted point buy for starting attributes.

### Attribute point budget - 2026-06-26

Designer accepted 6 attributes, 1-5 range, baseline 2 each, and 6 additional points to distribute.

### Attribute lowering freedom - 2026-06-26

Designer accepted that the player can lower as many attributes as desired from baseline 2 to 1 to gain additional points, preserving player freedom for uneven character profiles.

### Attribute growth scope - 2026-06-26

Designer confirmed starting attributes do not increase during play.

### Favorites/preferences model - 2026-06-26

Designer accepted Storyteller/Designer recommendation: Social Preference, Free Time Preference, Music Preference, Media Preference, and Food Preference as MVP character creation categories; soft modifiers only; fixed for MVP.

### Art direction format - 2026-06-26

Designer selected top-down RPG-like pixel art for maps/world navigation and larger high-resolution, slightly comic-style expressive portraits for characters.

### Portrait expression scope - 2026-06-26

Designer accepted 6 baseline expressions per MVP core classmate, with 1-2 additional special expressions possible for the partial arc character.

### Player visual scope - 2026-06-26

Designer confirmed player character customization applies to pixel-art sprite only; no high-resolution player portrait set for MVP.

### Dialogue UI modes - 2026-06-26

Designer defined two dialogue UI modes: large portrait/dialogue presentation with darkened map background, and small speech-bubble-like dialogue above sprites without portraits. Player character has no dialogue portrait/avatar.

### Audio direction - 2026-06-26

Designer selected hybrid audio: ambient/cozy routine music plus stronger themes for relationships, events, exams, and dramatic moments.

### Character music scope - 2026-06-26

Designer accepted no full leitmotifs for every MVP classmate; the partial arc character may receive one relationship theme/motif.

### Technical platform targets - 2026-06-26

Designer confirmed PC/Steam first, Godot, Steam Deck desirable but not mandatory, and mobile/console/web out of first release.

### Performance targets - 2026-06-26

Designer accepted 60 FPS PC target, optional Steam Deck 60 FPS at 720p, under-2s scene transitions, under-3s day transition/save/load targets, measured over a representative 10-minute loop.

### Save system - 2026-06-26

Designer accepted autosave at start/end of day, manual save in safe contexts only, and large checkpoint recovery for end-of-year exam failure.

### MVP success metrics - 2026-06-26

Designer accepted MVP playtest success metrics for time pressure clarity, lesson engagement, relationship pull, wellbeing understanding, one-more-day pull, day pacing, and meaningful missed opportunities.

### Out of scope boundaries - 2026-06-26

Designer accepted MVP/v1/deferred out-of-scope boundaries.

### GDD cleanup pass - 2026-06-26

Updated `gdd.md` to `coached_draft_v0.2` and reconciled stale Express-draft remnants with coached decisions: relationship USP, Trust/Openness terminology, Social Media/Map scope, club count, MVP location count, romance scope wording, success metrics, and open questions.

### Epics update - 2026-06-26

Rewrote `epics.md` to `coached_draft_v0.2` as a design-to-production bridge aligned with the coached GDD. Updated the GDD epic summary table to match the new 10-epic sequence.

### Money and allowance model - 2026-06-26

Designer confirmed that MVP includes money as a light personal-budget system. The protagonist receives weekly allowance from parents. Dorm costs, school costs, normal meals, and basic living expenses are abstracted away and assumed to be covered by family. Allowance is personal discretionary money for consumables, snacks/coffee, small gifts, hangout/date extras, and preparation items.

Designer also confirmed that the player can earn extra money through town odd jobs. Odd jobs are one-off activities rather than scheduled employment. They should cost time and Energy and remain a tradeoff against study, recovery, relationships, and events rather than becoming a separate job-management system.

Designer clarified that odd jobs should be short and frequent rather than rare all-day commitments. A typical odd job should last about 60-180 minutes, with "max a few hours" as the intended feel.

Designer confirmed that odd jobs should be lightly connected to town life, NPCs, and story flavor. They can provide small hints, overheard context, brief encounters, or location-specific texture, but should not become a primary relationship path.

### Romance orientation model - 2026-06-26

Designer confirmed that classmates have specific authored orientations. A classmate is romanceable only for a compatible protagonist. Orientation should be treated as part of character writing and relationship discovery rather than as an upfront UI checklist.

### Incompatible romance handling - 2026-06-26

Designer accepted that a close relationship with a non-compatible or non-romanceable classmate should still support a meaningful Best Friend path. If the player attempts romance where it is not compatible, the game should resolve it through an authored clarification or gentle rejection scene with possible brief Mood/Stress impact and relationship texture, but without severe mechanical punishment.

### Flirt interaction scope - 2026-06-26

Designer accepted flirt as a contextual or authored tone option in dialogue, texting, hangouts, and story scenes rather than a generic repeatable action. Flirt can reveal chemistry, create warmth or awkwardness, and clarify romantic possibility, but should not become a spammable romance-grind tool.

### MVP romance scope - 2026-06-26

Designer accepted that MVP should establish romantic potential rather than resolve a full romance. The partial 3-beat classmate arc can include chemistry, flirt, awkwardness, or a first signal of possible romantic direction, but MVP does not need to reach full Romantic Partner status or post-commitment content.

### MVP school-wide event - 2026-06-26

Designer selected Class Integration Day as the MVP school-wide event. It should be an integration-focused fixed calendar event centered on meeting classmates, choosing who to spend limited time with, noticing group dynamics, and relationship discovery rather than academic competition.

### Town event storyteller handoff - 2026-06-26

Designer rejected the initial `Autumn Town Fair` working idea as too generic and insufficiently believable as an autumn event. Created `events-and-calendar/request-to-storyteller--autumn-town-event.md` requesting 3-5 more seasonally grounded town event concepts for MVP, with a recommendation from the Storyteller.

### MVP town event - 2026-06-26

Designer returned with `events-and-calendar/response-from-storyteller--harvest-evening-town-event.md`, selecting `Harvest Evening` as the MVP town event. Harvest Evening is an official autumn harvest market / kiermasz organized by the town, intended as the seasonal town-wide counterpart to a fair without generic summer-festival flavor. It should use a restrained MVP scope: park or town-square-style event layout, small stalls, warm seasonal food, gifts/consumables, Social Media clues, relationship choices, Mood/memory flags, and no minigames or large vendor economy.

Designer prefers Harvest Evening as a Saturday evening event. Recommended placement is around Week 8 or 9 so it occurs after initial classmate discovery but before exam pressure dominates the semester.

Designer accepted Harvest Evening running 18:00-21:00 with partial attendance allowed. The event ends at a fixed time even if the player arrives late.

### MVP Science Club special event - 2026-06-26

Designer selected a project-academic direction over a contemplative/narrative astronomy-style event. The MVP Science Club special event is `Science Showcase Sprint`: a 90-minute project-focused event where the club prepares a small demonstration, prototype, or experiment under time pressure. It should use activity choices and role contributions rather than a separate minigame.

### MVP Drama Club special event - 2026-06-26

Designer accepted `Dress Rehearsal Crisis` as the MVP Drama Club special event. It is a 90-minute backstage pressure event where a rehearsal goes wrong before a small performance or internal run-through. It should focus on performance, improvisation, emotional support, practical problem-solving, and group tension without requiring a fully produced performance scene.

### MVP club scope revision - 2026-06-27

Designer accepted the cast-driven club scope change from `clubs-and-arc-access/request-to-game-designer--club-scope-reconsideration.md`: MVP club count remains 2, but the second club changes from Drama Club to Literary Club. Science Club remains in MVP and stays tied to academic/project pressure. Literary Club replaces Drama Club because the emerging MVP cast, especially Eleanor "Nell" Graves, is stronger when her writing, authorship, anonymous profile, and fear of exposure are supported directly rather than forced into a theatre/performance structure.

Supersedes the prior MVP club selection of Science Club + Drama Club for active GDD planning. Drama Club remains a valid full-game/deferred club candidate.

### MVP Literary Club special event - 2026-06-27

Designer accepted replacing `Dress Rehearsal Crisis` with `Zine Deadline Crisis` as the Literary Club special event. It is a 90-minute writing/publishing pressure event around finalizing a small zine, school magazine page, reading board, or literary showcase material before a deadline. The event should focus on authorship, consent, editing, layout, reputation, emotional support, public reading, and protecting boundaries around private writing. It should remain text-forward and avoid a large performance scene or complex publishing system.

### Club gating for classmate arcs - 2026-06-27

Designer accepted the rule from `clubs-and-arc-access/request-to-game-designer--classmate-arcs-club-gating.md`: core classmate story beats should not require membership in a specific club. Club membership can provide variants, insider context, better preparation, lower friction, stronger outcomes, and additional flags, but it should not hard-lock the classmate's main relationship/story progression.

Club-owned special events can allow non-member access when required for core classmate arcs through invitation, guest/helper roles, public school context, Social Media/text clues, or bounded tasks from the classmate. For Eleanor/Nell specifically, Literary Club is her strongest thematic environment, but her 3-beat MVP partial arc must support both Literary Club member and non-member access routes.

### MVP 3-beat relationship progression handoff - 2026-06-27

Created `classmates-cast-and-arcs/request-to-storyteller--mvp-3-beat-relationship-progression.md` to clarify that the MVP partial classmate arc's 3 beats should represent the first 3 beats of the full 10-beat arc: 1 Stranger beat plus 2 Acquaintance beats. Beat 3 should feel like the doorway into Friendship, creating a meaningful trust/story flag and setting up the later Friend-phase arc rather than fully resolving the classmate's core wound.

### MVP classmate cast package accepted - 2026-06-27

Accepted `classmates-cast-and-arcs/package--mvp-classmate-cast.md` as the canonical MVP classmate cast companion. The MVP cast is Charlotte "Lottie" Fairchild, Oliver "Ollie" Grant, Eleanor "Nell" Graves, Amelia Price, and Leo Ward. Eleanor/Nell receives the 3-beat partial arc; Charlotte, Oliver, Amelia, and Leo receive 1 early/introduction beat each. Updated `gdd.md` and `epics.md` with the cast summary, beat distribution, MVP romance/orientation notes, and Nell's role as the relationship-system proof case.

### Open items triage - 2026-06-27

Reviewed remaining open items after the consistency pass. `12 core classmates` is no longer treated as an assumption in `gdd.md`; it is the current full-game working target, already supported by earlier brief/GDD decisions, while still subject to later production estimates. Full-game romanceable cast remains deferred to narrative design and is not a blocker for MVP GDD readiness because MVP romanceability is defined for the 5 MVP classmates. Steam Deck support remains an assumption/dependency: desirable, but not a binding MVP requirement.

### MVP cast storyteller handoff - 2026-06-26

Designer requested a Storyteller handoff for proposing the MVP classmate cast. Created `classmates-cast-and-arcs/request-to-storyteller--mvp-classmate-cast.md`, asking for 5 classmates built from recognizable high-school archetypes, including one recommended partial 3-beat arc character and four intro-beat classmates.

### Input reconciliation pass - 2026-06-27

Reconciled `gdd.md` and `epics.md` against the updated game brief, brief addendum, and original brainstorming session. No blocking gaps or contradictions were found. The GDD preserves the brief's core fantasy, adult nostalgic audience, emotional priority order, platform/engine scope, one-semester MVP, relationship progression model, lessons-as-gameplay requirement, wellbeing economy, smartphone/social navigation, and production-risk framing.

Noted intentional coached-path updates relative to earlier inputs: Mood is now a full MVP planning system rather than optional/limited; MVP clubs are Science Club + Literary Club rather than the earlier Science Club + Drama Club candidate; classmate arcs are not hard-gated by club membership; and the MVP classmate cast is now concrete rather than open. These are later design decisions, not reconciliation defects.

### Open-items review and polish pass - 2026-06-27

Reviewed `gdd.md`, `epics.md`, and `decision-log.md` for remaining open questions, assumptions, stale deferred decisions, and polish issues. No phase-blocking open items remain for MVP GDD readiness. Steam Deck support remains an explicit non-binding assumption. Full-game romanceable cast remains deferred to narrative design, while MVP romanceability and orientation are already defined for the 5 MVP classmates. Cleaned stale log wording around Mood and club changes, clarified post-launch classmates/clubs wording, and removed duplicate customization wording from deferred post-launch possibilities.

### GDD finalized - 2026-06-27

Marked `gdd.md` and `epics.md` as `final_v1.0` for downstream planning. The finalized GDD is the canonical design source for High School Story's MVP and full-game design intent. Remaining non-blocking follow-up work is narrative design for the full classmate cast and, after that, game architecture for the systems-heavy implementation plan.

### D107 - No-Cost Abstract Morning Routine

Decision: Morning routine remains abstract and automatic, but does not consume a scheduled time block or act as a feasibility requirement.

Details:
- `Preparation` is not a schedule entry, hard commitment, or validation rule.
- Snooze validity is determined by wake time, required travel, and the first mandatory commitment.
- The player must wake before the mandatory commitment begins.
- Optional authored morning activities may consume time only when they present a visible player choice and a concrete effect.

Rationale: Time pressure should arise from player decisions and authored commitments, not from an invisible mandatory routine.

### D108 - Explicit Before-School Free-Time Window

Decision: The time from the wake boundary until the first mandatory school commitment is represented by an explicit non-reserving before-school free-time window rather than an implicit schedule gap.

Details:

- The canonical first school day exposes `before-school-free` from 06:00 to 08:00 at the dorm.
- The window is available context, not a hard commitment or reserved activity time.
- Player actions in this window must still preserve travel feasibility for the first mandatory commitment.
- `after-school-free` remains a distinct window because it has different boundary and location constraints.

Rationale: Explicit windows distinguish author-intended player agency from missing schedule data and make availability visible to validation and future read models.
