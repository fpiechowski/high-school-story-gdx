package pro.piechowski.highschoolstory.ecs.input

import org.koin.core.scope.Scope
import pro.piechowski.highschoolstory.physics.movement.input.MovementControllerInputSystem
import pro.piechowski.highschoolstory.physics.movement.input.MovementMultiplexInputSystem

context(scope: Scope)
val inputSystems
    get() =
        with(scope) {
            listOf(
                get<MovementControllerInputSystem>(),
                get<MovementMultiplexInputSystem>(),
            )
        }
