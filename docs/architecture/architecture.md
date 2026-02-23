# Architecture & Code Style Reference

This document captures the rules, conventions, and patterns used throughout the KGE engine and High School Story game. It serves as the authoritative guide for writing new code that fits the existing style.

---

## Table of Contents

1. [Project Structure](#1-project-structure)
2. [Naming Conventions](#2-naming-conventions)
3. [File Organization Rules](#3-file-organization-rules)
4. [GameObject Pattern](#4-gameobject-pattern)
5. [Interface Hierarchy & Composition](#5-interface-hierarchy--composition)
6. [ECS — Components & Systems](#6-ecs--components--systems)
7. [Dependency Injection (Koin)](#7-dependency-injection-koin)
8. [Story & Beat System](#8-story--beat-system)
9. [Dialogue DSL](#9-dialogue-dsl)
10. [Preview Tests](#10-preview-tests)
11. [Kotlin Code Style](#11-kotlin-code-style)

---

## 1. Project Structure

```
high-school-story/              ← game project root
  core/                         ← game logic (platform-independent)
  lwjgl3/                       ← desktop launcher + Preview tests
  engine/                       ← composite build: KGE engine
    annotation/                 ← @GameObject annotation
    annotation-processor/       ← KSP processor (generates boilerplate)
    core/
      src/domain/               ← pure domain logic
      src/main/                 ← LibGDX/Koin integration
    gameplay/
      character/                ← CharacterBase, PlayerCharacterBase
      dialogue/                 ← Dialogue DSL, DialogueManager
      interaction/              ← Interactable system
      power/                    ← Power/energy
      story/                    ← Story.Beat, StoryManager
      time/                     ← Clock, Calendar
      vehicle/                  ← Vehicle base
      weather/                  ← Weather simulation
```

**Rule:** The `domain/` source set must stay free of platform/rendering imports (LibGDX, LWJGL3) and DI framework imports (Koin). Fleks ECS is allowed — it is treated as part of the engine domain itself. Introducing adapters around every ECS type would add unnecessary indirection without benefit.

---

## 2. Naming Conventions

| Kind | Convention | Example |
|------|-----------|---------|
| Classes / Interfaces | PascalCase | `PlayerCharacter`, `CharacterBase` |
| Components | Descriptive noun (no suffix) | `CurrentSprite`, `Speed`, `PhysicsBody` |
| Systems | `[Action]System` | `SpriteAnimationSystem`, `VelocitySystem` |
| Managers | `[Entity]Manager` | `PlayerCharacterManager`, `DialogueManager` |
| Tags | Descriptive + `Tag` suffix | `CharacterTag`, `PlayerCharacterTag` |
| Koin modules | `[Feature]Module` | `CharacterModule`, `DialogueModule` |
| Entrypoints | Name reflects role | `GameEntrypoint`, `SandboxEntrypoint` |
| Generated files | `[ClassName]Generated.kt` | `PlayerCharacterGenerated.kt` |
| Companion factories | `invoke` (operator) | `PlayerCharacter("First", "Last")` |
| Prototype builders | `prototype(...)` | `PlayerCharacter.prototype(...)` |
| Koin lazy delegates | `by inject<T>()` | `private val world by inject<World>()` |
| Extension conversions | `.toMeter()`, `.toPixel()` | `10f.px.toMeter()` |
| Unit extension props | single letter | `Float.m`, `Float.px`, `Float.s`, `Float.mps` |

**Package naming:** Reverse domain, then feature hierarchy.
- Engine: `pro.piechowski.kge.[feature].[sub-feature]`
- Game: `pro.piechowski.highschoolstory.[feature].[sub-feature]`

---

## 3. File Organization Rules

- **One public type per file.** File name matches the top-level public class/interface.
- Tightly coupled private types may share a file with their owner.
- Sealed class/interface hierarchies (all cases) live in the same file as their parent.
- Generated files go to `build/generated/ksp/main/kotlin/...` — never edit them.
- Koin module declarations live in `[Feature]Module.kt` at the feature root.
- Test previews live in `lwjgl3/src/test/kotlin/...` and are annotated `@Test`.

---

## 4. GameObject Pattern

The GameObject pattern is the primary abstraction for entities visible to game logic. KSP generates the boilerplate from `@GameObject`-annotated interfaces.

### What the developer writes

```kotlin
@GameObject
interface PlayerCharacter : PlayerCharacterBase {
    companion object : PlayerCharacterCompanion() {          // generated base class
        // Factory — creates and configures a Fleks entity
        suspend operator fun invoke(
            firstName: String,
            lastName: String,
        ): PlayerCharacter = invoke(
            world.entity { it.from(prototype(firstName, lastName)) }
        )

        // Reusable entity configuration (composable)
        fun prototype(firstName: String, lastName: String) = Prototype {
            it.from(PlayerCharacterBase.prototype(firstName, lastName, ...))
        }
    }
}
```

### What KSP generates

```kotlin
open class PlayerCharacterCompanion : EntityGameObjectCompanion<PlayerCharacter>(
    { BindablePlayerCharacter(it) }
) {
    override val archetype: Archetype = Archetype { from(PlayerCharacterBase.archetype) }
}

class BindablePlayerCharacter(entity: Entity) :
    PlayerCharacter, BindableEntityGameObject<PlayerCharacter>(entity) {
    context(_: EntityComponentContext)
    override val sprite: CurrentSprite get() = entity[CurrentSprite]
    // ... all other component accessors
}
```

### Rules

- Always annotate the interface with `@GameObject`.
- The companion object must extend the generated `[Name]Companion` class.
- Declare a `suspend operator fun invoke(...)` factory for every concrete game object.
- Declare a `fun prototype(...)` for reusable / composable entity configurations.
- Component accessors are **declared in the interface** using context parameters (see §11).
- Never manually create a `Bindable*` class — the generator handles it.

### Prototype composition

```kotlin
fun interface Prototype {
    suspend fun EntityCreateContext.invoke(entity: Entity)
}

// Compose via `Entity.from(prototype)`
fun prototype(...) = Prototype {
    it.from(BaseClass.prototype(...))   // chain base prototypes
    it += SpeedComponent(2f.mps)        // then add specific components
}
```

---

## 5. Interface Hierarchy & Composition

### Vertical hierarchy (engine-provided)

```
EntityGameObject
    └─ Spatial          (PhysicsBody, position)
        └─ Kinetic      (Speed, MovementInput)
            └─ Visual   (CurrentSprite)
                └─ CharacterBase     (animation, dialogue, interaction, name)
                    └─ PlayerCharacterBase   (controller MovementInput)
                        └─ PlayerCharacter   (game-specific)
```

Each level adds components and context-parameter accessors on top of the one below.

### Horizontal composition

An entity that is not a character can still mix traits without going through `CharacterBase`:

```kotlin
@GameObject
interface Bus : Visual, Spatial, Kinetic { ... }
```

### Tag components

Marker components with no data use `data object` implementing `EntityTag`:

```kotlin
data object CharacterTag : EntityTag
data object PlayerCharacterTag : EntityTag
data object InteractorTag : EntityTag
```

Tags are added to the `Archetype` and used in `Family` queries.

### Archetype inheritance

Each layer declares its `archetype` building on the parent's:

```kotlin
override val archetype = Archetype {
    from(ParentClass.archetype)
    all(MyNewComponent, AnotherComponent)
}
```

---

## 6. ECS — Components & Systems

### Components

Every component is a **data class** that implements `Component<T>` with a companion `ComponentType<T>`:

```kotlin
data class Speed(
    var speed: MetersPerSeconds = 0f.mps,
) : Component<Speed> {
    override fun type() = Speed
    companion object : ComponentType<Speed>() {
        val walk = Speed(1.4f.mps)
        val jog  = Speed(2.5f.mps)
        val run  = Speed(5f.mps)
    }
}
```

Rules:
- The companion object **is** the `ComponentType` — use it directly as a key.
- Predefined constant values (e.g., `Speed.walk`) belong on the companion.
- Mutable fields use `var`; immutable use `val`.

### Systems

Use `MeasuredIteratingSystem` (per-entity) or `MeasuredIntervalSystem` (global):

```kotlin
@ExperimentalTime
class SpriteAnimationSystem : MeasuredIteratingSystem(
    World.family {
        all(
            @ReadOnly CurrentAnimation.Companion,
            @Write   CurrentSprite.Companion,
        )
    },
) {
    override fun onMeasuredTickEntity(entity: Entity) {
        val anim = entity[CurrentAnimation]
        anim.time += deltaTime
        entity[CurrentSprite].sprite = anim.animation.getKeyFrame(anim.time)
    }
}
```

Rules:
- Declare the `Family` inline in the constructor — keep it close to the system.
- Annotate components with `@ReadOnly` / `@Write` for correctness and performance.
- Override `onMeasuredTickEntity` (not `onTickEntity`).
- Never bypass the `Measured*` base — it hooks metrics automatically.

### System ordering (SystemComposer)

Systems run in the order returned by `SystemComposer`:

```kotlin
// Engine defines default order in GameSystems, PhysicsSystems, etc.
// Game overrides via its own SystemComposer in core/SystemComposer.kt

val systemComposer = SystemComposer { systems ->
    systems.withAddedAfter<VelocitySystem>(MyCustomSystem())
}
```

The identity composer (`SystemComposer { it }`) keeps the default engine order.

---

## 7. Dependency Injection (Koin)

### Module declaration

```kotlin
val CharacterModule = module {
    single { PlayerCharacterManager() }
}
```

Feature modules are declared at the feature root (`[Feature]Module.kt`) and included from the parent module:

```kotlin
val highSchoolStoryModule = module {
    single<Entrypoint> { GameEntrypoint() }
    single { systemComposer }
    // ...
    includes(CharacterModule)
    includes(DialogueModule)
    includes(TimeModule)
}
```

### Injection patterns

**Lazy property (preferred in classes):**
```kotlin
private val world by inject<World>()
private val dialogueManager by inject<DialogueManager>()
```

**Context-parameter scope (preferred in top-level functions):**
```kotlin
context(scope: Scope)
val gameSystems get() = with(scope) {
    listOf(get<VelocitySystem>(), get<SpriteAnimationSystem>())
}
```

**Global access (use sparingly, mainly in companion factories):**
```kotlin
DependencyInjection.Global.get<World>()
// or via KoinComponent
get<World>()
```

### Rules

- Prefer `by inject<T>()` for class-level dependencies (deferred, lazy).
- Prefer context parameters for function-level scope access.
- Never call `DependencyInjection.Global.get()` inside hot paths (use `by lazy` or `by inject`).
- Register singletons with `single { }`, not `factory { }`, unless a new instance per injection is required.

---

## 8. Story & Beat System

### Story.Beat interface

```kotlin
interface Beat<T> : Playable<T> {
    val priority: Int get() = 0
    fun shouldBePlayed(state: T): Boolean
    suspend fun play()
    fun shouldSpawn(state: T): Boolean
    suspend fun spawn()
}
```

### Beat implementation rules

- One `Beat` per class, one class per file.
- Inject all dependencies lazily (`by inject<T>()`).
- `shouldBePlayed` / `shouldSpawn` must be pure functions — no side effects, no suspension.
- `play()` and `spawn()` are suspending coroutines; use `delay()`, `await()`, `async { }` freely.
- Wrap ECS mutations in `with(world) { ... }` to access the entity context.
- Register beats as singletons in a Koin module; `StoryManager` discovers them.

```kotlin
class RoadToLakeview : Story.Beat<GameState> {
    private val world by inject<World>()
    private val dialogueManager by inject<DialogueManager>()
    private val mapManager by inject<TiledMapManagerAdapter>()

    override fun shouldBePlayed(state: GameState) = !state.facts.contains(RoadToLakeviewPlayed)

    override suspend fun play() = with(world) {
        mapManager.currentMap = Road()
        val bus = Bus(Direction4.Right, BusColor.YELLOW, 10f.mps).apply {
            position = Tile.Position(15, 8).toPixel() * px.toMeter()
        }
        delay(1000)
        dialogueManager.startDialogue(dialogue {
            playerCharacter.says("Ehhhh...")
        }).await()
    }

    override fun shouldSpawn(state: GameState) = TODO()
    override suspend fun spawn() = TODO()
}
```

---

## 9. Dialogue DSL

### Entry point

```kotlin
fun dialogue(init: DialogueBuilder.() -> Dialogue.Node): Dialogue
```

### Node types

| Node | Purpose |
|------|---------|
| `actor.says(line)` | A spoken sentence |
| `actor.choice { option(...) }` | A branching choice presented to the player |
| `wait(duration)` | Pause without dialogue |
| `goTo(id)` | Jump to a node by id |
| `Dialogue.Node.End` | Terminates the dialogue |

### Rules

- Chain nodes via `nextNode` parameter: `playerCharacter.says("Hi", nextNode = playerCharacter.says("Bye"))`.
- Assign `id` to nodes you need to `goTo`.
- `CharacterBase.says(...)` is a context extension — it requires both `EntityComponentContext` and `DialogueBuilder` in scope.
- Dialogue is **data** — no logic/side effects inside the DSL. Use `onAdvanced` callbacks sparingly for state mutations.

```kotlin
dialogueManager.startDialogue(dialogue {
    playerCharacter.says(
        "Trzy kolejne lata bez starych.",
        nextNode = npc.says("Może tym razem będzie lepiej?"),
    )
}).await()
```

---

## 10. Preview Tests

Previews launch a live LibGDX window for isolated visual testing without the full game.

```kotlin
class PlayerCharacterPreview {
    @Test
    fun preview() {
        preview(highSchoolStoryModule) {
            get<AssetsLoader<Assets>>().load()
            PlayerCharacter("Preview", "Character")
                .also { get<InputManager>().passOwnership(it) }
        }
    }
}
```

### Rules

- Place previews in `lwjgl3/src/test/kotlin/...`, mirroring the main source structure.
- Use `@Test` so the Gradle test task can run them by name.
- Pass only the modules your subject needs — avoid loading the whole game.
- The preview lambda is an `Entrypoint` body; it runs once after assets are loaded.
- The window stays open until manually closed — this is intentional for iteration.

---

## 11. Kotlin Code Style

### Context parameters

Context parameters are used throughout to pass implicit dependencies without cluttering signatures:

```kotlin
context(ecc: EntityComponentContext)
val body: PhysicsBody

context(ecc: EntityComponentContext)
var position: Vector2
    get() = body.body.position
    set(value) = body.body.setTransform(value, body.body.angle)
```

Rules:
- Use named context parameters (`ecc: EntityComponentContext`) not anonymous `context(EntityComponentContext)` — the name enables explicit forwarding.
- Declare component accessors in the **interface** with context parameters; implement them in the `Bindable*` class (or let KSP generate them).
- Do not tunnel context parameters through unrelated layers — add a `context` only when the function logically belongs to that scope.

### Functional interfaces (`fun interface`)

Prefer `fun interface` over full interfaces for single-abstract-method types:

```kotlin
fun interface Prototype { suspend fun EntityCreateContext.invoke(entity: Entity) }
fun interface SystemComposer { fun compose(systems: List<IntervalSystem>): List<IntervalSystem> }
fun interface Entrypoint { suspend fun run() }
```

### Value classes

Use `@JvmInline value class` for type-safe units:

```kotlin
@JvmInline value class Meter(val value: Float) { ... }
@JvmInline value class Pixel(val value: Float) { ... }
@JvmInline value class MetersPerSeconds(val value: Float) { ... }

// Convenient construction via extension properties
val Float.m   get() = Meter(this)
val Float.px  get() = Pixel(this)
val Float.mps get() = MetersPerSeconds(this)
```

### Sealed hierarchies

Use `sealed class` / `sealed interface` for exhaustive type sets. Declare all cases in the same file:

```kotlin
sealed interface Dialogue.Node {
    data class Sentence(...) : Node
    data class Choice(...) : Node
    data class Wait(...) : Node
    data object End : Node
}
```

### Extension functions

Extension functions are the primary mechanism for adding behaviour to types you don't own, and for DSL building:

```kotlin
// DSL extension
fun Dialogue.Actor.says(line: String, ...): Dialogue.Node { ... }

// Fluent builder on game objects
context(ecc: EntityComponentContext)
fun Spatial.atPosition(pos: Vector2) = apply { position = pos }

// Type conversion
fun Pixel.toMeter() = Meter(value * METERS_PER_PIXEL)
```

### Lazy delegation

- Use `by lazy { }` for properties that are expensive to initialize.
- Use `by inject<T>()` for Koin dependencies in classes.
- Never store injected values in `init` blocks — Koin may not be started yet.

### What to avoid

| Anti-pattern | Preferred alternative |
|---|---|
| Deep inheritance chains | Horizontal interface composition |
| Mutable globals | `by inject<T>()` or context parameters |
| Raw types | Parameterised generics |
| Logic in `shouldBePlayed` / `shouldSpawn` | Keep them pure predicates |
| Editing generated `*Generated.kt` files | Modify the source `@GameObject` interface |
| `--no-verify` or `ktlintCheck` suppressions | Fix the lint issue |
| `factory { }` in Koin when `single { }` suffices | Use `single { }` |
| Calling `DependencyInjection.Global.get()` in hot paths | `by inject<T>()` lazy delegate |
