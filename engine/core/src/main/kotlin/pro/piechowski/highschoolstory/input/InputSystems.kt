package pro.piechowski.highschoolstory.input

import org.koin.core.scope.Scope
import pro.piechowski.highschoolstory.input.movement.MovementControllerInputSystem
import pro.piechowski.highschoolstory.input.movement.MovementMultiplexInputSystem

context(scope: Scope)
val inputSystems
    get() =
        with(scope) {
            listOf(
                get<MovementControllerInputSystem>(),
                get<MovementMultiplexInputSystem>(),
            )
        }
