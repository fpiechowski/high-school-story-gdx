---
title: "High School Story - Game Design Document"
game_type: simulation
platforms:
  - PC
  - Steam
created: 2026-06-25
updated: 2026-06-27
status: final_v1.0
---

# High School Story - Game Design Document

**Author:** Filip Piechowski  
**Game Type:** Simulation  
**Target Platform(s):** PC / Steam first, Steam Deck desirable if low-cost

---

## Executive Summary

### Core Concept

`High School Story` is a nostalgic school social sim about guiding a custom student through three years of high school, spending scarce time across lessons, studying, relationships, recovery, fun, and school events. The game is not about doing everything perfectly. It is about authoring a personal school-life path and accepting the cost of the memories, bonds, and opportunities the player chooses.

The player lives through a structured school calendar made of days, weeks, semesters, and school years. Each day presents schedule pressure, social opportunities, wellbeing constraints, and academic obligations. Repeated decisions define who the protagonist becomes: the diligent student, the socially present friend, the club regular, the romantic partner, the burned-out overachiever, the relaxed drifter, or a mixed identity that emerges from play.

### Target Audience

Primary players are adults nostalgic for high-school life and interested in revisiting that emotional stage through a warm, structured simulation. The game targets players who like relationship-driven sandbox play, cozy daily loops, and school-calendar pressure, especially fans of the school-life layer in `Persona 5`, the daily rhythm and NPC community of `Stardew Valley`, and the readable life-management layer of `The Sims`.

The game should remain accessible to players without deep simulation mastery. It should support one in-game day in under 30 minutes while still creating longer "one more day" sessions.

### Unique Selling Points (USPs)

- **Time as emotional currency:** small decisions, including 15-minute choices, create visible opportunity cost and emotional regret.
- **Lessons as full gameplay:** classes are not passive calendar blocks; they connect academics, wellbeing, social behavior, and long-term pressure.
- **Relationships through interaction and story milestones:** bonds grow through repeatable contact, compatibility, and authored story beats, with major milestones unlocking after enough relationship investment.
- **Life-path identity:** the ending interprets the player's accumulated lifestyle, academic outcomes, memories, relationships, and missed chances.
- **Smartphone as social navigation:** messages, social media, calendar, map, and school tools help the player discover opportunities without replacing in-person relationship building.

---

## Goals and Context

### Project Goals

- Deliver a commercial indie PC school-life simulation with strong emotional identity and restrained solo-dev scope.
- Build a first MVP as a one-semester vertical slice, not a compressed full game.
- Prove the time-block daily loop, lessons as gameplay, early relationships, wellbeing pressure, calendar events, and a small off-school life.
- Keep the full-game fantasy aimed at a three-year high-school journey with reflective graduation and epilogue.
- Make the game feel cozy and nostalgic while preserving meaningful pressure, tradeoffs, and missed opportunities.

### Background and Rationale

The design originates from the desire to make the school-life layer the main game rather than connective tissue around combat, farming, or broad sandbox play. `High School Story` focuses on ordinary but emotionally loaded adolescence: where the player spends time, who they keep showing up for, what they sacrifice, and what version of youth they look back on.

The game should feel grounded enough to support sincere drama while using recognizable American high-school film cliches: school events, social rituals, crushes, conflicts, clubs, school pressure, and emotionally legible turning points. The setting is a fictional European town, but the fantasy is shaped by heightened high-school media more than strict local realism.

---

## Core Gameplay

### Game Pillars

1. **Time and wellbeing are the core currencies.**  
   Every meaningful action consumes time. Energy, stress, and mood shape what the player can sustain and how well activities resolve.

2. **Relationships grow through interaction and story milestones.**  
   Bonds deepen through repeatable interactions such as conversation, texting, meetings, and gifts, then advance through major scripted story-arc quests once the relationship reaches the required level. Compatibility accelerates growth but does not replace effort.

3. **Identity is enriched through tradeoffs.**  
   Character creation gives the protagonist starting stats and favorites/preferences, while the richer school identity is built through repeated activities, relationships, and the opportunities the player gives up.

4. **School structure creates pressure.**  
   Lessons, weekly rhythm, semesters, exams, clubs, and time-bound events give the cozy daily loop stakes and shape.

### Core Gameplay Loop

1. The player reviews available information whenever they choose: calendar, smartphone, school schedule, known NPC availability, upcoming exams, event reminders, and current wellbeing.
2. The player spends time blocks on available activities: lessons, study, social contact, travel, recovery, fun, club activity, optional events, or off-school life.
3. During lessons, the loop enters a stricter school phase: each lesson has 3 fifteen-minute blocks, each block asks for one lesson-specific action, and some actions risk teacher attention.
4. After each lesson, the player gets one 15-minute break block for a looser school-ground activity.
5. The school day includes a fixed 12:00-12:45 lunch break with cafeteria, recovery, and social actions.
6. Activities resolve immediately, changing academics, relationships, Energy, Stress, Mood, discovered information, event access, or future obligations.
7. Repeatable relationship interactions build toward rarer classmate story-arc milestones, which act as authored payoffs above the daily loop.
8. Going to sleep can show a compact summary of the day's most important consequences, then the next day begins.
9. The cycle repeats across weeks and semesters until the accumulated lifestyle is reflected in exams, memories, relationship outcomes, and epilogue.

Planning is player-initiated rather than system-mandated. The start of the day is the natural first planning moment, but the player can re-plan at any time, especially when new events disrupt the current intention.

The "one more day" motivation comes from several overlapping pressures:

- Tomorrow may contain a test, event, club meeting, or social opportunity.
- A relationship may be close to the required level for a classmate story milestone.
- The player may want to repair Stress, Energy, relationship damage, or academic weakness from earlier choices.
- A newly discovered NPC schedule hint may invite a better route tomorrow.
- A larger deadline, exam, event, or semester moment may be approaching.

The dominant pull should be curiosity about classmate story arcs and the desire to improve the player's daily process: maintaining high wellbeing while becoming more effective at academic progress and relationship growth.

### Win/Loss Conditions

`High School Story` has one hard progression fail gate: academic promotion. Most outcomes are interpretive, but the player must pass the end-of-year exam to continue to the next class/year.

Each semester ends with a semester exam:

- The first semester exam in a given school year measures progress and can produce weak subject results, but it does not end the run by itself.
- A weak first-semester result affects grade/standing and warns the player that academic gaps need correction before the end-of-year exam.
- Exams are exam periods with subject-level results, not one abstract test.
- The end-of-year exam period determines whether the student earns promotion to the next class/year.
- Promotion requires every subject to reach the minimum passing grade.
- Example threshold: E still passes, while F fails.
- Failing any subject at the end-of-year promotion gate ends the run because the student does not receive promotion.
- Passing the end-of-year exam with even the minimum required result allows the player to continue.

If the player fails the end-of-year exam, the game offers large checkpoint recovery options:

- Return to 1 week before the exam.
- Return to 4 weeks before the exam.
- Return to the beginning of the semester.

Outside this academic fail gate, the game uses outcome bands instead of binary victory:

- **Academic outcome:** final exam result and subject performance.
- **Relationship outcome:** close bonds, damaged relationships, romances, unresolved arcs, and missed connections.
- **Wellbeing outcome:** sustained balance, burnout, high stress, low mood, or resilient recovery habits.
- **Memory outcome:** major events attended, personal milestones, club memories, and emotional highlights.
- **Identity outcome:** a named life-path interpretation based on dominant patterns across the run.

A barely passing run should still be narratively valid. The player can regret choices, miss opportunities, or graduate with uneven outcomes without feeling the game has rejected their path.

Wellbeing does not create a separate game over. Energy, Stress, and Mood shape what the player can do and how well actions resolve. Energy is the main activity-gating resource: if the player lacks enough Energy, some desired actions cannot be performed or cannot be performed effectively. Stress and Mood can worsen outcomes, reduce efficiency, or affect availability, but they do not end the game on their own.

---

## Game Mechanics

### Primary Mechanics

#### Time Blocks

- The standard scheduling unit is 15 in-game minutes.
- A playable school day uses fixed mandatory school blocks plus flexible before-school, break, after-school, evening, and weekend blocks.
- Most meaningful activities cost 15, 30, 45, 60, or 90 minutes.
- Travel between major locations costs 15 minutes unless the locations are adjacent.
- The player can preview known time costs before confirming an activity.

#### Campus Zone and Travel

The school, dorm, and a shared outdoor entrance area form the core campus zone.

- The campus zone contains the school building, dorm, and outdoor location with entrances to both.
- Moving from dorm to school within campus does not consume a 15-minute travel block.
- Travel time applies when the player leaves campus for farther locations.
- If the player leaves campus before lessons, the game validates whether travel out, activity time, and return to school can fit before mandatory school commitments.
- The player can leave campus before school if the time math works; the game blocks infeasible choices rather than banning morning off-campus movement.
- The normal system does not allow voluntary lateness. Activities that would make the player late for mandatory lessons are blocked.
- Lateness can exist only as a scripted/authored event consequence.
- After school, off-campus activities must also fit travel and return-to-dorm timing before the 21:00 dorm requirement.

#### Daily Structure

- A playable day starts with a 06:00 alarm.
- Default wake-up is 06:00.
- The player can snooze in 15-minute increments when the day's schedule allows it.
- The latest valid wake-up is calculated from the first required school commitment minus any required travel time; wake-up must occur before that commitment begins. Dorm-to-school movement inside campus does not consume a travel block.
- Morning routine is abstract, automatic, and does not consume a scheduled time block or reserve player time.
- After waking, the schedule exposes an explicit non-reserving before-school free-time window until the first mandatory commitment; player actions remain subject to the travel required to arrive on time.
- The player does not choose individual routine actions such as showering, getting dressed, or brushing teeth.
- Snooze can restore additional Energy or reduce fatigue, but costs morning activity time.
- Waking up close to the school deadline can increase Stress or remove morning opportunities.
- Snooze cannot allow truancy or make the player miss mandatory school attendance.
- A playable day ends no later than 22:00 with sleep.
- The player can choose to go to sleep earlier.
- The final hour before latest sleep is dorm-only activity time.
- On normal school nights and Sunday this is 21:00-22:00.
- On Friday and Saturday nights this is 23:00-24:00.
- On school days, school hours follow the day's class schedule, e.g. 07:00-15:00.
- The player must attend school; truancy is not available.
- Before school, the game blocks activities that would prevent timely school arrival. Leaving campus before school requires enough time to return.
- The player cannot voluntarily arrive late to mandatory lessons through normal activity choices.
- Once the player reaches school, they cannot leave school grounds until lessons are finished.
- During the school day, actions are limited to lessons, break activities, and school-ground activities.
- A typical school day has 5-6 lessons.
- A light school day has 4 lessons.
- A heavy school day has 7 lessons.
- After school, the player has free time for off-school, social, study, recovery, fun, club, or event activities if travel and time allow.
- The player must return to the dorm by 21:00.
- Activities that cannot fit travel, activity duration, and return-to-dorm time before 21:00 are unavailable or blocked.

This structure makes school and dorm the fixed anchors of the day. Player freedom exists inside those constraints, with travel time and curfew turning location choices into meaningful tradeoffs. The final hour before latest sleep is always dorm-only wind-down time. Sleep timing is a recovery choice on both ends of the day: early sleep trades evening activity time for better next-day readiness, while snooze trades morning agency for additional Energy.

#### Weekend Structure

Weekends use the same time-block grammar but loosen the school-day anchors.

| Day | Wake Rule | Return-to-Dorm | Latest Sleep | Structure |
| --- | --- | --- | --- | --- |
| Monday-Thursday | 06:00 alarm with schedule-valid snooze | 21:00 | 22:00 | School day |
| Friday | 06:00 alarm with schedule-valid snooze | 23:00 | 24:00 | School day plus freer evening |
| Saturday | Player-selected weekend wake plan | 23:00 | 24:00 | Free day |
| Sunday | Player-selected weekend wake plan | 21:00 | 22:00 | Free day with Monday prep pressure |

On Friday and Saturday nights, the player chooses a wake plan for the next morning:

- Early Start: 06:00-07:00, more usable day time, less recovery.
- Normal Weekend: 08:00, balanced time and recovery.
- Sleep In: 09:00-10:00, more recovery, less usable day time.

The maximum weekend sleep-in target is 10:00. If Energy reaches cap before the chosen wake time, additional sleep gives little or no Energy but can still reduce Stress. The wake plan UI should make this tradeoff clear so the player understands when they are trading time for Stress recovery rather than Energy.

#### Calendar Structure

- Full game target: 3 school years.
- Each school year contains 2 semesters.
- Each semester target length: 20 school weeks.
- A 2-week playable winter break separates semesters.
- Summer break is off-screen for the base game and can be summarized between years.
- MVP target: 1 semester with a restricted event calendar and no full-run ending.

The design model treats a semester as 20 weeks. MVP pacing should be designed around this 20-week semester unless production planning later explicitly scopes a shorter vertical slice.

Semester pacing framework:

| Weeks | Phase | Purpose |
| --- | --- | --- |
| 1-2 | Onboarding | Introduce school routine, phone, lessons, wellbeing, and first classmates. |
| 3-6 | Routine Formation | Let the player form habits through homework, clubs, early relationship beats, and schedule discovery. |
| 7-10 | First Pressure Rise | Introduce tests, calendar conflicts, and the first stronger event pressure. |
| 11-15 | Mid-Semester Identity | The player's priorities become visible through club participation, relationship milestones, academic standing, and routines. |
| 16-19 | Exam Pressure | Grades, readiness, Stress, and final study choices dominate planning. |
| 20 | Exam Period / Semester Resolution | Subject exams, results, and semester consequences resolve. |

#### Wellbeing Resources

- **Energy:** underlying 0-100 value. Gates activity availability and affects performance on demanding tasks. Displayed through a bar and/or icon with descriptive label.
- **Stress:** underlying 0-100 value. Increases from pressure, poor preparation, conflicts, and overextension; reduces performance, efficiency, or activity quality at high values. Displayed through a bar and/or icon with descriptive label.
- **Mood:** qualitative emotional state rather than a third identical meter. Mood is not a one-dimensional good/bad spectrum. Example states include Happy, Content, Tired, Sad, Lonely, Anxious, Irritated, Depressed, Excited, Calm, and Overwhelmed. Mood affects social willingness, activity enjoyment, relationship-event tone, and how rewarding or draining activities feel.

All three wellbeing resources are full systems. Mood is not deferred from MVP planning.

Mood can be derived from hidden emotional pressures/tags instead of a single score. Example pressures include social fulfillment, loneliness, anxiety, irritation, sadness, excitement, calm, and confidence. The visible Mood should represent the emotional interpretation of the protagonist's recent life: a player who has ignored social needs may become Lonely, a player under exam pressure may become Anxious, a player after a strong relationship milestone may become Excited or Happy, and a player recovering through quiet activities may become Calm.

Mood effects should be mostly soft. Mood can modify Energy cost, Stress impact, relationship gain, dialogue tone, activity reward, or how draining an activity feels. Hard activity blocks should be rare and reserved for extreme Mood states or authored events.

Energy is the main hard-gating wellbeing resource. Most activities have a minimum Energy requirement. If the player does not meet it, the activity is blocked or offered as a low-effort variant when appropriate. Recovery activities should remain available at low Energy. Mandatory lessons still occur regardless of Energy, but low Energy reduces the effectiveness or available choices of lesson actions.

Stress is a soft but broad pressure modifier. High Stress can worsen outcomes, reduce efficiency, increase Energy costs for difficult activities, weaken study/lesson/test performance, and feed negative Mood pressures such as anxiety, irritation, or overwhelmed. Very high Stress can trigger authored or event-like consequences, but Stress does not cause game over by itself.

Default daily restoration:

- Full sleep restores 50-70 Energy and reduces Stress by 5-15, modified by prior day state.
- Earlier sleep improves recovery compared to staying active until 22:00.
- Snooze in 15-minute increments can restore additional Energy or reduce fatigue if the day's schedule leaves enough morning slack.
- Weekend sleep past Energy cap gives little or no additional Energy but can continue reducing Stress.
- Short rest costs 30 minutes and restores 10-20 Energy.
- Relaxing activity costs 30-60 minutes and reduces Stress by 10-25.
- Favorite-aligned activity costs 30-90 minutes and increases Mood by 10-30.

#### Lessons

Lessons are active gameplay segments, not automatic schedule skips.

Each lesson contains 3 fifteen-minute blocks. Each block gives the player one action choice from lesson-specific options. The lesson phase uses the same basic grammar as the rest of the game, choosing how to spend time, but with a stricter structure and a narrower action set.

School days vary by lesson load: 5-6 lessons is typical, 4 lessons is light, and 7 lessons is heavy. This variation should affect Energy pressure, after-school freedom, and the player's sense of the weekly rhythm.

The intended feel is similar to a JRPG combat structure where the "opponents" are teacher attention and the remaining lesson time. The player is not fighting the teacher literally; the pressure comes from deciding how much attention, recovery, social behavior, or risk to take inside a constrained class period.

Lesson risk is determined by two factors:

- **Teacher/lesson strictness:** how closely the teacher monitors the class and how harshly they respond.
- **Action risk profile:** how visible, disruptive, or rule-breaking the chosen action is.

The combination determines the chance or severity of being caught. The model should differentiate teachers and subjects without becoming a heavy stealth system.

Risk should be shown qualitatively rather than as exact percentages. Example labels: Safe, Low Risk, Risky, Very Risky. Tooltips can explain the label, but exact probabilities should not be shown by default.

Being caught during a risky lesson action should cause a small immediate penalty by default, with larger consequences reserved for repeated behavior, severe actions, or high-strictness teachers.

Possible caught consequences:

- Reduced or lost benefit from the current lesson block.
- Stress increase.
- Teacher impression decrease.
- Mood pressure such as embarrassment, irritation, or anxiety.
- Temporary restriction, such as phone actions being unavailable for the rest of the lesson.
- Later consequence, such as detention, only when escalated.
- Relationship impact if the risky action involved social interaction or texting.

Teacher impression is a lightly visible modifier per teacher or subject, not a full relationship system. It can influence consequence severity, tolerance, academic support, or lesson texture, but it should not become a classmate-style relationship arc.

Lesson action examples:

- Pay focused attention: improves subject knowledge, costs Energy, may raise Stress if already strained.
- Take light notes: moderate subject gain, low Energy cost.
- Study another subject: improves a different subject or upcoming deadline preparation, low-to-moderate current-subject gain, lightly risky.
- Use phone or text: relationship, social media, or message opportunity, lower subject gain, risk of teacher response.
- Quiet recovery: restores a small amount of Energy or Mood, low subject gain.
- Risky behavior: potential social payoff, academic penalty or Stress increase if caught.

Lesson actions should be organized as stable top-level categories with contextual variants. The top-level categories are the systemic buttons the player learns; variants provide flavor, tuning, and situational specificity without bloating the main action list.

Baseline lesson action categories:

- Focus / Listen Actively.
- Take Notes / Light Participation.
- Study Another Subject.
- Use Phone / Text.
- Quiet Recovery / Zone Out.
- Chat / Pass Note.
- Risky Distraction.

Variant tiers:

- **Systemic variants:** reusable variants that can appear broadly under common conditions.
- **Contextual variants:** variants triggered or modified by Mood, Energy, Stress, subject, teacher strictness, calendar pressure, nearby classmate, homework state, or teacher question context.
- **Authored-only variants:** unique actions written for specific classmate story arcs or authored events.

Authored-only variants should not be treated as generic reusable actions. They should remain specific to the story arc or event that introduces them, while still using the same broad lesson-action grammar so the player understands their tradeoffs.

Homework/deadline state and teacher questions are intended systems. Homework can support lesson variants such as finishing homework quietly or patching a weak subject. Teacher questions/checks can support variants such as staying ready to answer when called. These systems should reinforce school pressure and lesson texture without becoming separate minigame layers.

Teacher questions/checks are semi-predictable lesson events, not a separate minigame. Strict or question-heavy teachers should have a readable profile so the player understands that checks are more likely, but the exact moment is not shown. A check can occur after a lesson block or as a response to low attention/risky behavior. Attentive actions such as Focus, Take Notes, Follow the Thread, and Answer When Called improve check outcomes. Distracted actions such as Phone, Chat, Micro-Nap, Risky Distraction, and Study Another Subject worsen check outcomes. Results can affect subject gain, teacher impression, Stress, Mood pressure, or caught-like consequences.

Each subject has a current grade in the student's journal or school app from the start of the game. This grade is not an arithmetic average of individual marks. It is a synthetic indicator of how the student is doing in that subject. A low grade signals that the player should invest more study, lesson attention, or homework effort into that subject. Subject grade changes through lessons, studying, homework, tests, teacher impression, and lesson performance.

Homework is an occasional concrete task with a deadline and 0-100% completion progress. It should appear often enough to create school pressure, but not as daily task spam for every subject. Homework is completed outside school through repeated `Do Homework` actions. Each session adds progress; some homework requires multiple sessions to reach 100%. At 100%, homework is ready to submit. Submitted homework can improve subject grade, teacher impression, Stress, and/or exam readiness. Missed or incomplete homework can hurt subject grade, teacher impression, Stress, and/or exam readiness.

MVP homework cadence:

- 6-8 homework tasks total across the 20-week semester.
- Early semester usually has no more than 1 active larger homework at a time.
- Near exam pressure, up to 2 homework/deadline obligations can overlap.
- Each homework task requires 1-3 `Do Homework` sessions to complete.

Recommended lesson variants:

| Category | Variant | Tier | Role | Risk |
| --- | --- | --- | --- | --- |
| Focus / Listen Actively | Deep Focus | Systemic | Highest current-subject gain, high Energy cost. | Safe |
| Focus / Listen Actively | Prove Yourself | Contextual | Strong gain plus possible teacher impression gain, raises Stress. | Safe |
| Focus / Listen Actively | Follow the Thread | Systemic | Stable medium gain, useful when understanding is partial. | Safe |
| Focus / Listen Actively | Answer When Called | Contextual | Lower gain, protects against teacher questions/checks. | Safe |
| Take Notes / Light Participation | Clean Notes | Systemic | Moderate gain plus later study bonus. | Safe |
| Take Notes / Light Participation | Copy From Board | Systemic | Low safe academic gain, low Energy fallback. | Safe |
| Take Notes / Light Participation | Mark What You Don't Get | Systemic | Low immediate gain, improves later study/help-seeking. | Safe |
| Take Notes / Light Participation | Organize Old Notes | Contextual | Low current gain, reduces academic backlog Stress. | Safe |
| Study Another Subject | Cram for Next Test | Contextual | Improves upcoming-test readiness, raises Stress. | Risky |
| Study Another Subject | Finish Homework Quietly | Contextual | Improves homework/deadline state; possible later Stress relief. | Risky |
| Study Another Subject | Patch a Weak Subject | Contextual | Helps a weak subject and reduces long-term academic pressure. | Low Risk |
| Study Another Subject | Trade Today for Tomorrow | Contextual | Sacrifices current class for a stronger other-deadline benefit. | Low Risk |
| Use Phone / Text | Quick Check Messages | Systemic | Small social update; Mood depends on message content. | Low Risk |
| Use Phone / Text | Text Back Carefully | Contextual | Bond/trust gain during active relationship threads. | Risky |
| Use Phone / Text | Scroll to Escape | Systemic | Short-term relief, low academic gain, possible later guilt/distraction. | Low Risk |
| Use Phone / Text | Check Social Clues | Systemic | Gains social knowledge or schedule hints. | Low Risk |
| Use Phone / Text | Hide a Hard Message | Authored-only | Strong Mood beat tied to a relationship/story moment. | Low Risk |
| Quiet Recovery / Zone Out | Mental Pause | Systemic | Small Energy recovery and Stress relief, very low academic gain. | Safe |
| Quiet Recovery / Zone Out | Look Out the Window | Systemic | Stronger Stress relief, possible melancholy/lonely pressure. | Safe |
| Quiet Recovery / Zone Out | Micro-Nap | Systemic | Best Energy recovery, near-zero academic gain. | Low Risk |
| Quiet Recovery / Zone Out | Breathing Through It | Contextual | Strong anti-Stress option when Anxious/Overwhelmed. | Safe |
| Quiet Recovery / Zone Out | Doodle in the Margin | Contextual | Mood benefit, low academic gain, can reflect creative identity. | Low Risk |
| Chat / Pass Note | Whisper About Class | Systemic | Small relationship gain and small academic gain. | Low Risk |
| Chat / Pass Note | Share a Tiny Joke | Systemic | Bond and Mood gain, low academic gain. | Risky |
| Chat / Pass Note | Ask If They're Okay | Contextual | Bond/story-flag gain and possible story hint when classmate context exists. | Low Risk |
| Chat / Pass Note | Pass a Practical Note | Contextual | Can unlock or improve break/lunch interaction. | Risky |
| Chat / Pass Note | Flirt Under the Radar | Contextual | Romantic tension/bond gain, embarrassment risk if caught. | Risky |
| Risky Distraction | Make the Back Row Laugh | Systemic | Strong social/Mood payoff, academic loss. | Very Risky |
| Risky Distraction | Dare From a Friend | Authored-only | Bond/identity moment tied to a specific classmate or arc. | Very Risky |
| Risky Distraction | Deflect the Lesson | Contextual | Can reduce class tension or buy time, hurts teacher impression. | Risky |
| Risky Distraction | Show Off Quietly | Systemic | Smaller confidence/social payoff with lower risk than full disruption. | Risky |

Not all variants should be visible at once. A lesson block should present a small readable set of categories, with the contextual variant name and tooltip used when helpful. Mood, Energy, Stress, teacher strictness, subject, nearby classmates, homework state, teacher questions, calendar pressure, and active story arcs determine which variants appear or how they are tuned.

Lesson outcomes affect subject knowledge, teacher impression, Stress, Mood, Energy, and small relationship moments. Lesson design must avoid becoming a disconnected minigame collection; each lesson should reinforce school-life pressure and identity formation.

After each lesson, the player gets one 15-minute break block for a looser school-ground activity such as talking briefly, checking messages, moving location, recovering, or preparing for the next class.

Lunch break is a fixed special school-day break from 12:00 to 12:45, regardless of the day's lesson plan. It contains 3 fifteen-minute blocks and should feel meaningfully different from normal breaks without becoming unrestricted free time. The cafeteria is the primary lunch location and can provide Energy recovery.

Lunch action examples:

- Quick lunch: 15 minutes, small Energy gain.
- Full lunch: 30 minutes, larger Energy gain.
- Lunch with a classmate: 30-45 minutes, relationship gain plus moderate Energy gain.
- Coffee or snack: 15 minutes, immediate Energy bump with possible Stress or Mood tradeoff.
- Skip lunch: preserves time, gives no recovery and may create later Energy pressure.

#### Academics

- The game tracks subject knowledge per subject on a 0-100 scale.
- Each subject also has a visible current grade in the school journal/app.
- Current grade is a synthetic academic-state indicator, not an arithmetic average.
- Subject knowledge and subject grade are separate systems.
- Subject knowledge represents real preparation and understanding.
- Subject grade represents visible school standing.
- Subject grade affects pre-exam Stress and final grade/standing.
- MVP subject target: 4 subjects.
- Full game subject target: 6-8 subjects.
- Exams combine subject knowledge, recent preparation, Energy, Stress, and attendance.
- A prepared but exhausted student can underperform; a rested but unprepared student can only do so much.

Exam results are primarily determined by subject knowledge, recent study/exam readiness, Energy on exam day, Stress on exam day, and soft Mood modifiers. Teacher impression or current subject grade can provide context, but they should not dominate exam performance. Promotion requires every subject to meet the minimum passing grade; E can be the lowest passing grade and F is a failure.

Academic difficulty depends meaningfully on starting attributes/aptitudes. A student with attributes that support a subject, regular attendance, and occasional study should usually earn good but not top grades in that subject. A student with average attributes, regular attendance, and occasional study should usually pass minimally. A student without supportive attributes can be at risk of failing under the same effort pattern. Top grades require deliberate planning, study, homework completion, good condition management, and meaningful tradeoffs.

#### Character Creation and Starting Identity

Character creation defines the protagonist's starting shape, not their full identity. The player chooses character name, gender, starting attributes, favorites/preferences, and pixel-art sprite appearance. The richer school identity emerges through activities, relationships, academic effort, routines, missed opportunities, and story flags.

Starting attributes describe broad character traits and translate indirectly into subject aptitude. For example, Charisma can support social ease, public presence, performance, flirting, group situations, and some social/humanities-oriented subjects; Empathy can support emotional support, reading people, conflict handling, trust-building, and deeper relationship moments; logical thinking can support science/math-oriented subjects; Discipline can support memorization-heavy subjects and regular preparation; and Physicality can support physical education or physical activities. Attributes are soft but meaningful predispositions. Limited min-maxing is possible, but attributes should not remove the need to study a subject for the whole game. They matter most early in the protagonist's school path; in later years, accumulated subject knowledge, time investment, and effort should matter more than starting aptitude.

The game does not need a separate Memory attribute. Discipline covers regular study habits, concentration, homework completion, test preparation, and memorization-heavy learning. History or similar subjects can use Discipline as a primary influence, with Charisma, Empathy, or Logic as secondary influences depending on whether the subject emphasizes social/cultural understanding, emotional/human understanding, or cause-effect analysis.

Charisma and Empathy should remain distinct. Charisma covers social ease, small talk, group presence, confidence, flirting, public performance, and surface-level social navigation. Empathy covers emotional sensitivity, listening, support, reading people, conflict handling, trust-building, and deeper relationship moments. A high-Charisma/low-Empathy character can be socially magnetic but emotionally careless; a low-Charisma/high-Empathy character can be socially awkward but deeply supportive and perceptive.

Starting attributes use a point-buy system on a simple 1-5 scale. There are 6 attributes. Baseline is 2 in each attribute, and the player receives 6 additional points to distribute. Minimum attribute value is 1 and maximum starting value is 5. The player can lower any number of attributes from baseline 2 to 1 to gain additional points, allowing very uneven character profiles if desired. Example distributions include balanced 3/3/3/3/3/3 or more specialized 5/4/3/2/2/2. Point buy should give the player authorship over the protagonist's starting profile while making the consequences of extreme weaknesses readable.

Starting attributes do not increase during play. They define the protagonist's starting profile. Character development happens through subject knowledge, grades, relationships, story flags, identity markers, habits, and player choices rather than attribute leveling.

Favorites/preferences personalize the protagonist and provide soft hooks for Mood, relationship chemistry, gifts, hangouts, dialogue, texting, social media, and activity flavor. They do not provide direct attribute bonuses, hard-lock major story or relationship content, or replace relationship effort.

MVP favorites/preferences categories:

| Category | Example Options | Primary Uses | Compatibility Use |
| --- | --- | --- | --- |
| Social Preference | One-on-one, small group, large group, quiet company, spontaneous plans, planned activities, texting | Hangout Mood, invite fit, social event comfort, relationship pacing | High |
| Free Time Preference | Rest, outdoor activity, social activity, study, creative activity, family time, town exploration | Weekend activities, Mood recovery, activity flavor, event suggestions | High |
| Music Preference | Pop, indie/alternative, hip-hop/R&B, rock/punk, electronic, acoustic/folk, classical/soundtrack, K-pop/global | Shared-interest dialogue, dance/concert moments, bedroom/social media flavor | Medium |
| Media Preference | Comedy, romance/drama, mystery, fantasy/sci-fi, horror/thriller, sports/action, documentary, indie/art | Movie nights, club talk, dates, texting, fandom-style bonding | Medium |
| Food Preference | Pizza/fries, baked sweets, spicy snacks, boba/smoothies, coffee/tea, fresh bowls, homemade food, adventurous food | Gifts, cafeteria choices, hangout snacks, small kindness moments | Low-Medium |

Preferences are fixed after character creation for MVP. Evolving preferences are deferred. Mismatched preferences should create texture or playful contrast, not punishment.

MVP test cadence:

- 4 MVP subjects.
- 1 smaller test per subject before the exam period.
- Smaller tests occur across weeks 7-15.
- Week 20 remains the exam period.

#### Relationships

Each core classmate has:

- Bond value: underlying 0-100 value, not shown exactly by default.
- Familiarity tier: Stranger, Acquaintance, Friend, Close Friend, and Best Friend / Romantic Partner where applicable.
- Compatibility modifiers based on protagonist attributes and preferences.
- Schedule habits and preferred locations.
- Story arc state.
- Story flags from authored beats and important choices.

Bond growth sources:

- Conversation.
- Texting.
- Meetings and hangouts.
- Gifts.
- Spending time together.
- Choosing compatible activities.
- Remembering discovered preferences.
- Supporting the classmate during story-arc events.

Bond decay should be light and targeted. Ignoring someone for long periods can stall or cool a relationship, but the system should not make social life feel like maintenance chores.

Relationship progression has two layers:

- **Systemic interaction layer:** smaller repeatable interactions raise the relationship level and express day-to-day effort.
- **Story milestone layer:** major scripted quests rich in dialogue and events advance the classmate's personal story arc after the required relationship level is reached.

Compatibility modifies growth speed and efficiency. It should make a compatible relationship feel more natural, but it should not be the only reason a relationship can grow.

Baseline repeatable interaction types:

- Talk.
- Text.
- Hangout.
- Lunch together.
- Gift.
- Study together.
- Club/activity together.
- Check in / emotional support.
- Invite to event.

Each classmate can have preferences for interaction types. Compatibility and protagonist favorites/preferences can modify interaction effects. Preferred interactions should grow relationships more efficiently or produce better Mood, Bond, or story-flag outcomes. Non-preferred interactions can still work, but less efficiently or with different tone.

The relationship system does not use a separate global Trust/Openness meter. Emotional specificity comes from story beats and story flags, such as shared secrets, accepted help, or opened up about a personal issue. These flags can gate or alter later content.

Classmate story beats unlock through Bond/tier plus contextual conditions, not Bond alone. Conditions can include calendar timing, classmate availability, location, current school/event context, and story flags from prior choices. Wellbeing or Mood can modify tone and outcomes, but should rarely be a hard gate.

Core classmate story beats should not require membership in a specific club. Club context can be one access route, but core relationship progression must remain reachable through Bond/tier, story flags, Social Media or text clues, non-club locations, classmate invitations, public school context, or guest/helper roles. Club membership adds variants, insider context, lower friction, stronger outcomes, or additional flags; it should not be a hard lock on the classmate's main arc.

Relationship tier progression requires multiple meaningful interactions between authored beats. A beat alone should not instantly carry the relationship without prior interaction. MVP guidance: Stranger to Acquaintance takes roughly 2-3 meaningful interactions plus the relevant beat; Acquaintance to Friend takes roughly 4-6 meaningful interactions plus the relevant beat. Later full-game tiers should take longer and scale with relationship depth.

The player sees relationships as named tiers with a subtle progress indicator toward the next tier. The exact Bond value is used for balancing, unlocks, and milestone thresholds but is not shown by default.

Relationships use 5 visible tiers:

1. Stranger
2. Acquaintance
3. Friend
4. Close Friend
5. Best Friend / Romantic Partner, depending on path and eligibility

Romance is a path/state at the highest close relationship tier, not a sixth tier above friendship.

Not every core classmate needs to be romanceable. Romance availability is defined per classmate. Some classmates can support only a platonic Best Friend final path, and those paths should still feel meaningful. Romance should exist where it fits the character, arc, orientation, protagonist compatibility, and production scope.

Each classmate has a specific authored orientation. A classmate is romanceable only for a compatible protagonist. The game should treat orientation as part of character writing, not as a route checklist in the UI.

Romanceability is not shown as a hard badge from the start. The player discovers romantic possibility through dialogue, chemistry, choices, story moments, and relationship development. Later in a closer relationship, the game can subtly signal whether a romantic path is becoming available.

If the player builds a close relationship with a classmate who is not romantically compatible, the relationship should still support a meaningful Best Friend path. If a romantic attempt occurs where romance is not compatible, it should resolve through an authored clarification or gentle rejection scene. This can create brief Mood or Stress impact and relationship texture, but should not punish the player with severe mechanical loss for reading a relationship differently.

Flirting exists as a contextual or authored tone option in dialogue, texting, hangouts, and story scenes rather than as a generic repeatable "Flirt" action. Flirt choices can reveal chemistry, create warmth, produce awkwardness, or help clarify romantic possibility, but they should not become a spammable romance-grind tool.

Romance is exclusive/monogamous by default. The base game should not encourage collecting multiple simultaneous romantic partners. Authored exceptions are possible only if a specific character or arc meaningfully requires them.

Entering a Romantic Partner path does not erase the friendship and trust foundation of that relationship. Romantic Partner and Best Friend are distinct final paths, not a hierarchy where romance is simply better. The final 2 beats of a full classmate arc can branch into Best Friend or Romantic Partner content when the classmate supports both.

The game does not need a generic breakup system for v1. Romantic commitment is a meaningful path decision. Relationship conflict can exist inside authored beats, and breakups can happen as authored arc-specific consequences if a story requires them, but the base game should not model breakup/reconciliation as a broad reusable state machine.

A full classmate story arc targets 10 arc beats across the relationship progression:

| Relationship Phase | Beats | Purpose |
| --- | ---: | --- |
| Stranger | 1 | Initial encounter / first impression |
| Acquaintance | 2 | Getting to know the classmate |
| Friend | 3 | Core personal problem and personality depth open up |
| Close Friend | 2 | Vulnerability, emotional confidence, and stronger arc stakes |
| Final Path: Best Friend / Romantic Partner | 2 | Post-commitment relationship content |

Becoming a Best Friend or Romantic Partner should not end the arc. The final path has its own content after the status is reached. Not all 10 beats need to be equally large; a full arc can mix roughly 4 major beats with 6 minor beats. The final path should include at least 1 major beat because it is a payoff phase.

#### MVP Classmate Cast

The MVP classmate cast is defined in detail in `classmates-cast-and-arcs/package--mvp-classmate-cast.md`. The GDD-level canon is:

| Classmate | Archetype | Core Drama | Main System Hook | MVP Beat Role |
| --- | --- | --- | --- | --- |
| Charlotte "Lottie" Fairchild | Hyper-polished Queen Bee / perfect daughter | Chose status over the friend who knew her before the crown | Social hierarchy, public image, Class Integration Day | Intro beat |
| Oliver "Ollie" Grant | Injured golden-boy jock | Played hurt and was rewarded for self-damage | Sport-adjacent school pride, Energy/Stress/Mood, public image | Intro beat |
| Eleanor "Nell" Graves | Goth rumor girl / wounded writer | Private heartbreak writing was exposed and weaponized | Literary Club, Zine Deadline Crisis, Social Media, trust | 3-beat partial arc |
| Amelia Price | Teacher's pet / top student | Parentified and unseen, using academic excellence as escape | Science Club, grades, homework, recommendation pressure | Intro beat |
| Leo Ward | Rebel skater / protective troublemaker | Punished for defending someone in a morally messy way | Detention, after-school spaces, direct justice, Amelia mirror | Intro beat |

MVP romanceability and orientation are authored per character:

- Charlotte "Lottie" Fairchild: bisexual, romanceable for compatible protagonists; MVP can show social chemistry/private softness.
- Oliver "Ollie" Grant: straight, romanceable for compatible protagonists; MVP can show warmth or public-image chemistry.
- Eleanor "Nell" Graves: bi/pan, romanceable for compatible protagonists in the full game; MVP focuses on trust and emotional safety.
- Amelia Price: aromantic asexual, not romanceable; supports a meaningful Best Friend path.
- Leo Ward: bisexual, romanceable for compatible protagonists in the full game; MVP can show warmth or protective attention.

MVP beat distribution:

| Beat Slot | Classmate | Beat Type | Timing Window | Main Context | Purpose |
| --- | --- | --- | --- | --- | --- |
| 1 | Amelia | Intro Beat | Week 1, Day 1-2 | First lesson / academic setup | Establish academic benchmark, teacher trust, and hidden over-preparation |
| 2 | Charlotte | Intro Beat | Week 1, Day 3-5 / Class Integration Day | Class photo / integration display | Establish social hierarchy, Eleanor tension, and public mask |
| 3 | Eleanor | Stranger Beat | Week 1-2 | Rumor / hallway-library-social context | Meet the school story before the person |
| 4 | Oliver | Intro Beat | Week 2 / first PE or sport window | School-spirit / sports aftermath | Reveal golden-boy pressure and hidden injury |
| 5 | Leo | Intro Beat | Week 2-3 | After-school discipline / phone rumor echo | Establish protective troublemaker contradiction |
| 6 | Eleanor | Acquaintance Beat 1 | Week 3-5 | Writing clue / quiet contact | See private authorship beneath public rumor |
| 7 | Eleanor | Acquaintance Beat 2 | Week 6-8 | Zine Deadline Crisis | Create first real trust flag before mid-semester pressure |

Eleanor/Nell is the selected MVP partial-arc classmate because her progression best proves relationship growth as trust rather than grind. Her MVP 3-beat arc covers the first 3 beats of the full 10-beat structure: 1 Stranger beat plus 2 Acquaintance beats, with Beat 3 acting as the doorway into future Friendship rather than resolving the full wound. Her core flags should test whether the player withholds judgment, respects private writing, and protects authorship/consent.

#### Romance

Romance is an important possible outcome, not a separate route structure that replaces friendship. Romantic availability depends on classmate design, authored orientation, relationship tier, story state, protagonist compatibility, and player choices.

Romance availability, romantic path details, and any rare authored breakup consequences should be resolved during narrative design before full production.

MVP should establish romantic potential rather than resolve a full romance. The partial 3-beat classmate arc can include chemistry, flirt, awkwardness, or a first clear signal of possible romantic direction, but it should not need to reach full Romantic Partner status. MVP tests whether the player wants to pursue a relationship, not the complete post-commitment romance structure.

#### Clubs and Social Identity

Clubs provide recurring activities, social circles, story opportunities, skill growth, and calendar pressure.

Full game target:

- 4-6 major clubs.
- Each club has a weekly meeting rhythm, member cast, 3-5 milestone events, and at least one meaningful conflict or decision.

MVP target:

- 2 clubs.
- MVP clubs are Science Club and Literary Club.
- Each MVP club supports early joining, recurring attendance, and one event or milestone.

The player can join both MVP clubs, but fully engaging with both is constrained by time, schedule, and Energy. Club membership is not mutually exclusive by rule; calendar pressure creates the tradeoff.

Each MVP club has 1 regular weekly meeting lasting 60 minutes. Special events or milestones last 90 minutes by default. Regular meetings create routine participation, while special events provide larger identity, social, or story moments.

Clubs do not use a separate progress/rank system. They are driven by attendance and events. Clubs provide opportunities for authored events and for deepening relationships with other club members. Club participation can contribute to identity, but should not become another independent progression grind.

Club-owned special events can allow non-member access when needed for a core classmate arc. A club member participates as an insider; a non-member can enter through invitation, guest helper role, public school context, or a bounded task from a classmate. The member route can provide better preparation, extra dialogue, lower Stress, stronger club identity, or additional flags, but the non-member route must still support the essential relationship beat.

#### Smartphone

The smartphone is the hub for:

- Calendar for known school schedule, meetings, club meetings, deadlines, exams, and known school/town events.
- Messages.
- Social Media, combining feed and classmate profiles.
- Map for locations, travel feasibility, and objects/services in locations.
- School App / Journal for grades, homework, subject info, and teacher notes.
- Settings, rendered as a phone app for game options.

Social Media reveals public-facing information, accurate current-location signals for known Acquaintance-or-higher NPCs, interests, and event signals. It should be useful but incomplete; deeper truth requires in-person presence and relationship investment.

The smartphone is the main diegetic planning and information hub outside direct activity selection. It shows what the protagonist knows, not the full truth of the world. It can archive discovered information and surface hints, but it should not fully solve NPC schedules or relationship opportunities. Discovering people still requires presence, interaction, and attention.

Settings should be presented as an app inside the smartphone UI. It should retain the phone-app look and feel while prioritizing usability, accessibility, and clarity for game options.

The smartphone UI is always accessible, but context can restrict available apps. During lessons and exams, only Settings is available by default. Other apps are blocked unless accessed through an explicit contextual action such as Use Phone/Text during a lesson. Settings remains available so the player can always reach game options.

General texting is usually free and does not consume a 15-minute time block. Reading and replying to ordinary messages can affect relationships, tone, Mood, or story context without acting as a constant time tax. Authored texting inside a story beat uses the time cost of that beat or activity.

Generic/systemic texting uses cooldowns so it cannot be spammed for unlimited Bond gain. Authored message threads are written content tied to classmate story beats or authored events, and follow the timing/cost rules of their associated beat.

Calendar shows known scheduled commitments and events: school schedule, exams, deadlines, planned meetings, club meetings, global school events, town events, and major known events such as prom. Unknown opportunities do not appear until discovered. NPC current locations are not a Calendar feature.

Known NPC current-location information is surfaced through Social Media profiles/feed in immersive form. A profile or post can indicate what a known character is doing and where, if the player has enough knowledge/access. This current-location information is accurate when viewed and is the main practical way to find known characters in the world. The player can still miss someone if travel time means that character leaves before the player arrives. NPC location schedules are fixed per character, allowing the player to learn routines over time. Social Media should not reveal unknown NPCs or unknown opportunities before discovery.

Social Media profile information expands with relationship familiarity. After first meeting a classmate, the player can see a basic profile. Full current-location visibility becomes available from Acquaintance onward. More detailed profile information unlocks through relationship growth and discovered facts.

Map shows known locations, travel time, travel feasibility, and objects/services in a location such as shops, cafes, and facilities. Selecting a location from the map can begin travel, but activities are selected after arrival. Map should not show which NPCs are currently present; NPC presence belongs to Social Media.

School App / Journal is the academic dashboard. It shows subject grades, homework, deadlines, subject status, teacher profiles/notes with light strictness and impression hints, exam calendar, and exam readiness. It can also show lesson notes or study bonuses when relevant. It should remain scannable rather than becoming a dense text archive.

#### Off-School Activities

Off-school life proves that the protagonist is more than a classroom schedule.

MVP locations:

- Cafe.
- Park.
- Home/dorm or private room.
- Convenience store.

The convenience store is a nearby off-campus location with 15-minute one-way travel. It should support limited functions such as snacks, coffee, small gifts, or preparation items without becoming a large shop/economy system in MVP.

Money exists as a light personal-budget system. The protagonist receives a weekly allowance from parents. Dorm costs, school costs, normal meals, and basic living expenses are abstracted away and assumed to be covered by the family. Allowance represents the student's personal spending money for optional consumables, coffee/snacks, small gifts, hangout/date extras, and preparation items.

The player can earn additional money through town odd jobs. Odd jobs are one-off activities, not scheduled employment. They are short and repeatable, usually 60-180 minutes, and represent helping somewhere in town for a limited stretch rather than taking on a standing job. They consume time and Energy, may affect Stress or Mood, and provide extra money for discretionary spending. Odd jobs can lightly connect to town life, NPCs, and story flavor through small hints, overheard context, brief encounters, or location-specific texture, but they should not become a primary relationship path. Odd jobs should create an interesting tradeoff against study, recovery, relationships, and events without becoming a separate job-management system.

Cafe and park are also nearby off-campus locations with 15-minute one-way travel.

Nearby off-campus MVP locations can be traveled between directly in 15 minutes. The player does not need to return to campus between cafe, park, and convenience store.

Off-campus MVP location roles:

- **Cafe:** social hangouts, casual dates, study together, coffee/snacks, and Mood support.
- **Park:** Stress reduction, Calm Mood, walks, emotional conversations, and solo recovery.
- **Convenience Store:** snacks, coffee, small gifts, preparation items, and quick errands.

Campus MVP location roles:

- **Dorm:** sleep, homework, solo study, texting, recovery, and evening wind-down.
- **Outdoor/courtyard:** quick social contact, transitions, school atmosphere, and short breaks.
- **Hallway/common area:** between-class interactions, rumors, quick chats, and chance encounters.
- **Classrooms:** lessons, teacher questions, academic actions, and lesson-specific social risk.
- **Cafeteria:** lunch, Energy recovery, lunch together, and social observation.
- **Library/study room:** focused study, homework, lower-social academic work, and stronger academic gain.
- **Club rooms:** club activities, club story events, and identity-building activities.

Full game locations may include town center, library, mall, cinema, sports area, music venue, and seasonal event spaces.

Activities should connect to at least one pillar: wellbeing recovery, relationship contact, academic preparation, club identity, or memory formation.

### Controls and Input

Primary target is mouse and keyboard.

Required input actions:

- Navigate map and menus.
- Select activities and confirm time costs.
- Advance dialogue.
- Open smartphone.
- Inspect calendar.
- Review classmate profile.
- Compare schedule opportunities.
- Save and load.

Steam Deck support is desirable. All core interactions should remain readable and usable with a controller-style input model if support is pursued.

---

## Simulation Specific Elements

### Core Simulation Systems

`High School Story` simulates a constrained school-life routine, not a realistic institution. The main simulation subject is the player's lived high-school lifestyle across time, wellbeing, academics, relationships, and event participation.

Core systems:

- Time-block schedule.
- School calendar.
- Energy, Stress, and Mood.
- Academic subject knowledge.
- Relationship bond and arc state.
- NPC schedules and availability.
- Smartphone discovery and reminders.
- Event eligibility and missed-opportunity tracking.

Simulation depth is semi-abstract. Values are visible enough for planning, but the emotional presentation should avoid spreadsheet dominance. Emergence should come from overlapping opportunities and resource pressures, not from opaque chaos.

### Management Mechanics

The player manages:

- Time allocation.
- Energy recovery.
- Stress control.
- Mood support.
- Study preparation.
- Relationship investment.
- Club and event commitments.

The game should support planning without forcing optimization. Players can intentionally choose inefficient actions because they are emotionally meaningful, socially important, or identity-defining.

### Building and Construction

No building or construction system is planned for v1.0. The player does not place structures, decorate rooms as a core system, or manage school facilities.

Small personal customization may exist later as flavor, but it is out of scope for the MVP and should not become a production pillar.

### Economic and Resource Loops

The primary economy is time and wellbeing.

Resource loops:

- Time is spent to gain academics, relationships, recovery, memories, or event access.
- Energy is spent on demanding activities and restored through sleep, rest, and light leisure.
- Stress rises through pressure and poor balance, then falls through recovery and supportive choices.
- Mood rises through enjoyable, favorite-aligned, or socially meaningful activities and falls through conflict, exhaustion, and isolation.
- Academic knowledge increases through lessons, study, and tutoring-like social activities.
- Relationship bond increases through repeatable interactions, compatibility, meaningful activity choices, and classmate story decisions.

Money is a secondary resource. MVP uses a light allowance model with low-to-medium decision weight, mainly for consumables, coffee/snacks, small gifts, hangout/date extras, and convenience-store purchases. Optional odd jobs let the player convert time and Energy into extra discretionary money.

### Progression and Unlocks

Progression occurs through:

- Calendar advancement.
- Subject knowledge growth.
- Relationship tiers.
- Classmate story arc milestones.
- Club attendance and club events.
- Discovered social knowledge.
- New locations and activities.
- Semester events and exams.

Unlocks should be tied to lived participation. A classmate's deeper event unlocks because the player has spent time, learned context, reached relationship thresholds, and set relevant story flags, not because they completed an isolated quest checklist.

### Sandbox vs. Scenario

The game is a structured life simulation with a fixed school arc, not an open sandbox.

Mode target:

- Base game: campaign-like three-year run with flexible personal path.
- MVP: one-semester vertical slice with no final life-path ending.
- No sandbox mode for v1.0.
- No procedural school generation for v1.0.

---

## Progression and Balance

### Player Progression

The player progresses by accumulating consequences across repeated days:

- Higher subject knowledge improves exam odds and academic identity.
- Relationship tiers unlock deeper conversations, events, romance possibilities, and epilogue outcomes.
- Club attendance unlocks club events, social access, and identity markers.
- Discovered classmate facts and preferences improve the player's ability to choose meaningful social actions.
- Wellbeing habits affect how sustainable the protagonist's lifestyle becomes.

The full game should make it impossible to maximize every major path in one run. A satisfying run should include 2-4 close bonds, 1-2 major activity identities, a meaningful academic profile, and several missed opportunities.

### Difficulty Curve

The curve is pressure-based rather than combat-based.

- Early weeks teach schedule reading, basic lessons, recovery, and first social choices.
- Mid-semester adds overlapping events, tests, club commitments, and relationship invitations.
- Late semester increases exam pressure, event conflicts, relationship milestones, and wellbeing strain.
- Later years introduce more mature relationship arcs, larger identity consequences, and stronger FOMO.

The game should avoid a single optimal schedule. Balance should support several viable lifestyles:

- Academically focused.
- Socially focused.
- Club-focused.
- Romance-focused.
- Balanced but slower-progressing.
- Chaotic but memory-rich.

### Economy and Resources

Primary resources:

- Time blocks.
- Energy.
- Stress.
- Mood.
- Subject knowledge.
- Relationship bond.
- Story flags.
- Calendar access.

Balance targets:

- A normal school day should force at least 2 meaningful tradeoffs.
- A week should contain more attractive opportunities than the player can complete.
- High Stress should create visible friction before it becomes punitive.
- Low Energy should limit high-value actions but still leave low-impact fallback options.
- Relationship arcs should reward commitment without demanding daily maintenance.

---

## Level Design Framework

### Level Types

This game uses calendar-and-location design rather than traditional levels.

Core playable spaces:

- School corridors and common areas.
- Classrooms.
- Library or study space.
- Club rooms.
- Cafe.
- Park.
- Convenience store.
- Home/dorm/private room.
- Event-specific spaces.

Core temporal levels:

- School day.
- Weekend day.
- Exam week.
- Club event day.
- School festival or seasonal event.
- Semester finale.

MVP event scope:

- 1 school-wide event: Class Integration Day.
- 1 town event: Harvest Evening.
- 1 Science Club special event: Science Showcase Sprint.
- 1 Literary Club special event: Zine Deadline Crisis.
- 1 exam period.

This gives the semester a few larger calendar anchors without over-expanding event content.

Class Integration Day is the MVP school-wide social event. It is an integration-focused fixed calendar event centered on meeting classmates, choosing who to spend limited time with, noticing group dynamics, and receiving early hints about clubs, routines, and relationship opportunities. Its main pressure is social attention and early belonging, not academic competition.

Harvest Evening is the MVP town event. It is an official autumn harvest market / kiermasz organized by the town, held on a Saturday evening in the park or a modest town-square-style event space. Local vendors, families, community groups, and school volunteers create a seasonal evening around warm food, small stalls, handmade items, local produce, charity/donation texture, and temporary lights. The event should feel town-owned rather than school-owned, while still giving students a believable reason to attend.

Harvest Evening's core tension is limited time, limited money, and limited social bandwidth. The player cannot visit every stall, buy every comfort item, and spend meaningful time with every classmate. It supports small purchases, food and gift preferences, Mood recovery, possible Stress from crowds or awkwardness, Social Media clues, brief NPC encounters, and memory flags. It should stay text-forward and restrained: one main event layout, a small number of interactable stalls, no minigames, no large vendor economy, and no crowd simulation. Recommended placement is mid-semester around Week 8 or 9, after the player has met several classmates but before exam pressure dominates. Event time is 18:00-21:00, with partial attendance allowed; the event ends at a fixed time even if the player arrives late.

Science Showcase Sprint is the MVP Science Club special event. It is a 90-minute project-focused academic event where the club prepares a small demonstration, prototype, or experiment under time pressure. The player can contribute through research, materials/setup, presentation, team support, or fixing a last-minute issue. It should touch Logic, Creativity, Discipline, and Charisma; offer subject gain, club/teacher impression, relationship opportunities, Stress/Mood effects, and identity texture; and resolve through activity choices rather than a separate minigame.

Zine Deadline Crisis is the MVP Literary Club special event. It is a 90-minute writing/publishing pressure event where the club must finalize a small zine, school magazine page, reading board, or literary showcase material before a deadline. The pressure should center on authorship, consent, editing, layout, reputation, and whether a private or misattributed piece risks becoming public. The player can help through editing, layout, practical errands, emotional support, public reading, or protecting someone's boundaries around authorship. It should touch Creativity, Empathy, Discipline, and Charisma; create relationship and Mood/Stress consequences; and avoid requiring a large performance scene or complex publishing system.

For Eleanor/Nell's MVP partial arc, Literary Club is her strongest thematic environment but not a membership gate. Her 3-beat arc should support parallel access: Literary Club member route with insider context and stronger club-role options, and non-member route through library/cafeteria encounters, Social Media or text clues, invitation, public zine context, or a bounded trusted-outsider task. `Zine Deadline Crisis` can be club-owned while still allowing a non-member protagonist to participate when the relationship/context justifies it.

Event timing model:

- **Fixed calendar events:** town festivals, class holiday events, prom, and similar rituals occur on fixed dates. They can be missed if the player chooses not to attend or cannot attend.
- **Condition-triggered milestones:** classmate story beats, story arc events, and progression milestones are not fixed to one date. They trigger when requirements are met and the first viable opportunity appears.

This preserves the reality of the calendar without letting important relationship/progression content vanish because the player had the wrong schedule on one specific date.

### Level Progression

Progression is organized by expanding the player's reachable routines:

- Onboarding: limited school map, mandatory classes, first classmates, basic phone.
- Early semester: study locations, first club access, first off-school location.
- Mid-semester: recurring club schedule, social events, relationship invitations.
- Late semester: exams, larger event conflicts, partial classmate arc milestone.
- Full game later years: new locations, mature arcs, larger events, graduation buildup.

Locations should be designed around opportunity density: every major place needs a clear reason to visit, a relationship or resource role, and time cost implications.

---

## Art and Audio Direction

### Art Style

The art direction uses a hybrid approach: top-down RPG-like pixel art for maps, locations, and world navigation, paired with larger high-resolution character portraits for dialogue and emotional expression. Portraits should use a cohesive, slightly comic-inspired style and support multiple expressions.

Dialogue UI has two presentation modes:

- **Large dialogue mode:** portrait and dialogue box occupy most of the screen, with the map/background slightly darkened. Used for important conversations, relationship scenes, and authored beats.
- **Small dialogue mode:** a small speech-bubble-like dialogue box appears above the speaking character's sprite, without portrait. Used for short lines, barks, quick exchanges, and low-weight interactions.

The player character has no dialogue portrait or avatar. NPC portraits appear in large dialogue mode; small dialogue mode uses sprites and text only.

Priorities:

- Readable locations and UI at PC resolution.
- Distinct silhouettes and color palettes for core classmates.
- Expressive high-resolution portraits for relationship scenes.
- Reusable environment tiles and location variants to control solo-dev art scope.
- Seasonal and event accents that make time feel like it is passing.

MVP asset targets:

- 5 core classmate visual sets.
- Player character pixel-art sprite customization: hair, outfit, eye color, and skin color.
- No high-resolution player portrait set for MVP.
- 4 subject/classroom presentations.
- 10 MVP locations/spaces.
- Smartphone UI.
- Calendar and schedule UI.
- 6 baseline portrait expressions per MVP core classmate: Neutral, Happy, Sad, Angry/Irritated, Embarrassed/Nervous, Thoughtful/Serious.
- 1-2 additional special expressions for the MVP's partial arc character if needed.

### Audio and Music

Audio direction uses a hybrid approach. Routine school life leans lo-fi, cozy, and ambient so long daily-loop play does not become fatiguing. Relationship scenes, events, exams, and dramatic moments can use stronger melodic themes or tension cues.

Music needs:

- Daily school theme.
- After-school/free-time theme.
- Study or library theme.
- Relationship scene theme.
- Optional relationship theme/motif for the MVP partial arc character.
- Stress/exam pressure theme.
- Event theme.
- Reflective/melancholy theme.

MVP does not require full leitmotifs or unique tracks for every core classmate. The partial arc character can receive one relationship theme or motif. Other classmates can use shared relationship or mood music. Full game production can revisit character-specific motifs if audio scope allows.

Sound priorities:

- School bells and hallway ambience.
- Phone notification sounds.
- Page turns, writing, classroom ambience.
- Cafe and park ambience.
- UI feedback that feels gentle rather than arcade-like.

---

## Technical Specifications

### Performance Requirements

Target:

- PC: 60 FPS sustained during normal gameplay.
- Steam Deck desired: 60 FPS at 720p if supported without major scope expansion.
- Scene transition target: under 2 seconds on target PC spec.
- Save/load target: under 3 seconds for a normal run state.
- Day transition target: under 3 seconds.

Measurement should use a 10-minute representative loop containing school navigation, lesson decisions, smartphone use, relationship dialogue, travel, and day transition.

### Platform-Specific Details

First release:

- PC through Steam.
- Built in Godot.
- Keyboard and mouse required.
- Controller/Steam Deck support desirable but not mandatory for MVP.

Out of first release:

- Mobile.
- Console.
- Web.

The GDD does not define Godot node structure, data architecture, or implementation patterns. Those belong in the architecture phase.

### Save Model

- Autosave at start of day.
- Autosave at end of day / sleep.
- Manual save only in safe contexts such as dorm or free exploration.
- Manual save should not occur during exams, lessons, action resolution, or other unstable states.
- End-of-year academic failure offers large checkpoint recovery: 1 week before the failed exam, 4 weeks before, or semester start.

### Asset Requirements

The project is content-heavy. Asset planning must protect the solo-dev scope.

Full-game target content:

- 12 core classmates with full arcs.
- Background/lightweight students as ambience if needed.
- 4-6 major clubs.
- 6-8 school subjects.
- Multi-year event calendar.
- Relationship, club, school, and seasonal event scenes.

MVP content:

- 5 classmates: Charlotte "Lottie" Fairchild, Oliver "Ollie" Grant, Eleanor "Nell" Graves, Amelia Price, and Leo Ward.
- 1 partial classmate story arc with 3 beats: Eleanor "Nell" Graves.
- 4 other classmates with 1 early/introduction beat each: Charlotte, Oliver, Amelia, and Leo.
- 7 total MVP relationship/story beats.
- 4 subjects.
- 2 clubs: Science Club and Literary Club.
- 10 MVP locations/spaces.
- 1 semester.
- No full ending.

---

## Development Epics

### Epic Structure

Detailed epic breakdown is maintained in `epics.md`.

| ID | Epic | Purpose | MVP Priority |
| --- | --- | --- | --- |
| E1 | Time, Calendar, and Travel | 20-week semester rhythm, day rules, school/dorm anchors, travel, and calendar pressure. | Must |
| E2 | Wellbeing and Activity Resolution | Energy, Stress, Mood, activity outcomes, recovery, and planning pressure. | Must |
| E3 | Lessons, Homework, and Academics | Active lessons, teacher checks, subjects, grades, homework, tests, and exam period. | Must |
| E4 | Relationships, Classmates, and Story Beats | Bond, tiers, repeatable interactions, Social Media discovery, and MVP story beats. | Must |
| E5 | Smartphone Apps and Planning UI | Calendar, Messages, Social Media, School App/Journal, Map, and Settings. | Must |
| E6 | Locations, Clubs, and Events | Campus/off-campus spaces, Science Club, Literary Club, and MVP event anchors. | Must |
| E7 | Character Creation and Identity | Attributes, preferences, sprite customization, and starting identity. | Must |
| E8 | Presentation, Dialogue, Art, and Audio | Pixel-art world, NPC portraits, dialogue modes, UI readability, and hybrid audio. | Must |
| E9 | Save, Progression, and MVP Completion | Autosaves, safe manual saves, semester resolution, success metrics, and MVP boundaries. | Must |
| E10 | Full-Game Reflection and Epilogue | Three-year completion, graduation, life-path interpretation, and expanded epilogues. | Later |

---

## Success Metrics

### Technical Metrics

- Representative 10-minute loop holds 60 FPS on target PC.
- Day transition completes in under 3 seconds.
- Save/load completes in under 3 seconds for an MVP run.
- No blocker bugs in a complete MVP semester playthrough.
- UI remains readable at 1080p and Steam Deck 720p if Deck support is pursued.

### Gameplay Metrics

MVP playtest targets:

- At least 80% of testers understand why time is scarce by the end of week 1.
- At least 70% can name one missed opportunity they cared about by mid-semester.
- At least 70% form an intentional preference for 1-2 classmates by mid-semester.
- At least 60-70% report that lessons feel connected to the school-life fantasy rather than detached minigames.
- At least 70% understand how Energy, Stress, and Mood affect planning.
- At least 60% want to play another day to see story, social, or academic consequences.
- Average in-game day completion time remains under 30 minutes.

Full-game targets:

- Players can describe their protagonist's identity without relying only on score values.
- Multiple viable lifestyles produce emotionally satisfying but different endings.
- The ending produces nostalgia, attachment, and bittersweet reflection in that order of priority.

---

## Out of Scope

### Out of Scope for MVP

- Full three-year run.
- Full ending, graduation, and life-path summary.
- Complete relationship arcs.
- Generic breakup system.
- Full romance system across many characters.
- All full-game classmates.
- More than 1 partial classmate arc.
- Full club system.
- Broad town map.
- Large activity catalog.
- More than 3 off-campus locations.
- Mobile, console, and web support.
- Full Steam Deck support as requirement.
- Deep room decoration or building.
- Procedural school generation.
- Full character-specific soundtrack.
- High-resolution player portraits.
- Large economy/shop system.

### Out of Scope for v1.0

- Combat, dungeons, supernatural plot, or Persona-like combat layer.
- Farming, crafting economy, or Stardew-like production loops.
- Multi-character household control.
- School-building management.
- Online multiplayer.
- User-generated content tools.
- Mobile-first design.
- Procedural school generation.
- Full sandbox mode.

### Deferred Post-Launch Possibilities

- Mobile port if the project succeeds.
- Expanded summer-break content.
- Additional classmates beyond the 12-core-classmate full-game target, or extra clubs beyond the v1.0 club plan.
- Extra locations.
- Expanded cosmetic and personal customization.
- Additional epilogue variants.
- Character-specific leitmotifs.
- Evolving preferences.

---

## Assumptions and Dependencies

### Assumptions Index

- [ASSUMPTION: Steam Deck support remains desirable but not a binding MVP requirement.]

### Deferred Design Items

- Full-game romanceable cast: deferred to narrative design for the 12-core-classmate full-game cast. MVP romanceability is defined for the 5 MVP classmates.

### Dependencies

- 12 core classmates is the current full-game working target unless later production estimates force a smaller cast.
- Narrative design must define full-game classmate arc details, full-game romanceable cast, and tone boundaries.
- Architecture must support content-heavy calendar, event, relationship, and save-state systems.
- Art production planning must cap character, portrait, location, and UI scope before MVP production.
- Playtesting must validate that time pressure feels meaningful without becoming punishing optimization.
