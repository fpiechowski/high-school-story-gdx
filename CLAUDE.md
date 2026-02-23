# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

High School Story is a social simulation game where the player goes through 3 years of high school life. It is built on top of a custom in-house engine called **KGE (Kotlin Game Engine 2D)**, which is included as a Gradle composite build from the `./engine` directory.

## Build & Run Commands

All commands use the Gradle wrapper (`./gradlew` on Unix, `gradlew.bat` on Windows).

```bash
# Run the sandbox (development mode) launcher
./gradlew :lwjgl3:run -PmainClass=pro.piechowski.highschoolstory.game.lwjgl3.SandboxLauncher

# Run the main game launcher
./gradlew :lwjgl3:run -PmainClass=pro.piechowski.highschoolstory.game.lwjgl3.MainLauncher

# Run a specific Preview test (opens a live LibGDX window)
./gradlew :lwjgl3:test --tests "pro.piechowski.highschoolstory.character.player.PlayerCharacterPreview.preview"

# Lint (ktlint check)
./gradlew ktlintCheck

# Lint auto-format
./gradlew ktlintFormat

# Run all tests
./gradlew test
```

Configuration (window title, resolution, vsync, etc.) is loaded from `core/src/main/resources/config.yml` via [Hoplite](https://github.com/sksamuel/hoplite).

## Architecture

### Gradle Project Structure

```
high-school-story/         ← game project
  core/                    ← game logic (platform-independent)
  lwjgl3/                  ← desktop launcher + Preview tests
  engine/                  ← composite build: KGE engine
    core/                  ← engine core (LibGDX, Fleks ECS, Koin, physics…)
    gameplay/              ← genre-specific engine modules
      character/
      dialogue/
      interaction/
      power/
      story/
      time/
      vehicle/
      weather/
    annotation/            ← @GameObject and other annotations
    annotation-processor/  ← KSP annotation processor
```

### Technology Stack

- **LibGDX** — rendering backend via LWJGL3
- **KTX** — Kotlin extensions for LibGDX (`ktx-async`, `ktx-app`, etc.)
- **Fleks ECS** — entity-component-system (`com.github.quillraven.fleks`)
- **Koin** — dependency injection; accessed via `DependencyInjection.Global.inject<T>()` / `get<T>()`
- **Box2D** — physics via LibGDX physics wrapper
- **KtxAsync / Kotlin Coroutines** — async game logic
- **Hoplite** — YAML config loading
- **ktlint** — code style enforcement

### Game Loop

`Lwjgl3Launcher` → starts Koin, creates `Lwjgl3Application` → drives `KtxApplicationGameLoopAdapter` (implements `KtxApplicationAdapter`) → calls `world.update(deltaTime)` each frame → runs all registered Fleks ECS systems.

On startup, `Entrypoint.run()` is called once (suspending) inside a coroutine.

### Dependency Injection (Koin Modules)

Game-level DI is configured in `core/.../Module.kt` (`highSchoolStoryModule`). It includes engine gameplay modules (`CharacterModule`, `DialogueModule`, `TimeModule`, etc.) plus game-specific singletons.

Engine-level DI is in `engine/core/.../CoreModule.kt` (`coreModule()`), always added by the launcher.

### Entity-Component-System (Fleks)

- **Components** are plain data classes added to Fleks `Entity`.
- **Systems** extend `IntervalSystem` and are composed via `SystemComposer` (a `fun interface` taking `List<IntervalSystem>` and returning the ordered list). The game's `systemComposer` is defined in `core/.../SystemComposer.kt` and injected into the engine.
- **Game objects** are interfaces that wrap a Fleks `Entity` and expose typed accessors for components via Kotlin context parameters. The hierarchy is: `EntityGameObject` → `Spatial` (has physics body/position) → `Kinetic` (has speed, movement input) → `Visual` (has sprite). `CharacterBase` combines all three and adds animation, name, dialogue actor, etc.

### GameObject Pattern

Game objects use a companion-object factory pattern:

```kotlin
@GameObject
interface PlayerCharacter : PlayerCharacterBase {
    companion object : EntityGameObjectCompanion<PlayerCharacter>(...) {
        suspend operator fun invoke(firstName: String, lastName: String): PlayerCharacter
        fun prototype(...): Prototype  // reusable entity builder
    }
}
```

`Prototype` is a `fun interface` holding a suspend lambda that configures a Fleks `Entity`. Prototypes compose via `Entity.from(prototype)`.

`@GameObject` is an annotation processed by KSP (annotation-processor module).

### Story / Scene System

Game content is authored as `Story.Beat<GameState>` implementations. A beat declares `shouldBePlayed(state)` / `shouldSpawn(state)` and runs its content in the suspending `play()` / `spawn()` coroutines. Example: `RoadToLakeview` in `core/.../scene/intro/`.

Dialogue is built with a DSL (`dialogue { ... }`) using `DialogueBuilder`. Characters "say" lines via context-extension functions.

### Preview Mode

`Preview` tests in `lwjgl3/src/test/` launch a live LibGDX window for visual iteration on isolated game objects without running the full game. Use `preview(yourModule) { ... }` to set up the scene.
