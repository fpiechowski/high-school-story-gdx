# 📦 Game Modules Outline – *High School Story*

This document outlines the major gameplay systems and modules used in *High School Story*. Each module represents an
isolated mechanic or system that can be documented, designed, implemented, and iterated independently.

---

## 🕒 Time & Calendar System

Manages in-game time progression, daily routines, and special calendar events.

- Real-time clock using 15-minute intervals
- Daily/weekly scheduling structure
- Event scheduling (e.g. school trips, holidays, exams)
- Sleep enforcement and time skipping mechanics

---

## 📚 Class & School System

Handles the gameplay experience during classes and academic routines.

- Class schedules per year
- Turn-based “class battle” mechanic (study vs. socialize, risk/reward)
- Actions taken during class affect stats and relationships
- School performance metrics and tracking

---

## 🧠 Stat System

Manages the player's condition and performance metrics.

- Core stats: Energy, Stress, Mood
- Academic stats: Knowledge and Skills per subject
- Stat decay, regeneration, and modifiers
- Action-based stat gain/loss

---

## 🤝 Relationship System

Tracks the development of social bonds between the player and NPCs.

- Relationship values and progression stages
- Dialogue options and unlockable scenes
- NPC backstory reveal system
- Relationship gates for locations, events, or choices

---

## 🧍 Character Creation & Identity

Defines the player character and how preferences influence gameplay.

- Gender and name selection
- Initial stat customization (knowledge/skills)
- Player preferences (hobbies, food) that affect activity outcomes

---

## 🧑‍🎓 NPC Behavior & Scheduling

Governs NPC movement, availability, and interaction opportunities.

- Daily/weekly schedules for classmates and staff
- Location-based presence logic
- NPC-initiated interactions
- NPC stats and roles in school (e.g. top student, club leader)

---

## 🏫 Location & World System

Defines the 2D map, travel mechanics, and place-based activities.

- School and town zone structure
- Time cost for traveling between zones
- Activity and interaction availability per location
- Unlockable locations through progression or relationships

---

## 🗓️ Event & Cutscene System

Handles narrative and visual storytelling through timed or triggered events.

- Relationship cutscenes
- School calendar events (holidays, exams, festivals)
- Holiday choice system (e.g. part-time job, vacation, study)
- Final cutscene and summary generation

---

## 💬 Dialogue System

Interactive conversation mechanics affecting relationships and mood.

- Dialogue trees and branching choices
- Stat- or relationship-locked options
- Feedback system (e.g. +5 Relationship)
- Integration with event and cutscene systems

---

## 🎒 Inventory & Items System

Tracks and manages usable items and their effects on gameplay.

- Temporary stat modifiers (e.g. snacks, drinks, study aids)
- Shop and vending interfaces
- Inventory limitations and backpack prep

---

## 💰 Economy System

Tracks in-game currency and player spending.

- Earning via jobs or events
- Shops, activity fees, and item costs
- Currency sink balance across 3 years

---

## 📈 Progression System

Controls academic success and determines game outcomes.

- School year advancement and failure conditions
- Midterm/final exam score calculations
- Unlocks for university acceptance cutscenes
- Game-over handling and save rollback options

---

## 🧪 AI/Behavior Modules *(Optional/Future Scope)*

Experimental or expandable systems for deeper simulation.

- NPC dynamic personality shifts
- Memory of past events and interactions
- Adaptive dialogue or behavior patterns

---

## 📎 Meta / Integration Modules *(Optional)*

Technical systems to support gameplay and development.

- Save/Load system
- UI/UX presentation layers
- Audio/visual pipelines
- Modding or API integration hooks

---
