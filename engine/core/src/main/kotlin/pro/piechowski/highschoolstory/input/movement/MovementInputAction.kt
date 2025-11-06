package pro.piechowski.highschoolstory.input.movement

import com.badlogic.gdx.Input
import pro.piechowski.highschoolstory.input.InputAction

val InputAction.Companion.MoveUp get() = InputAction("moveUp")
val InputAction.Companion.MoveLeft get() = InputAction("moveLeft")
val InputAction.Companion.MoveDown get() = InputAction("moveDown")
val InputAction.Companion.MoveRight get() = InputAction("moveRight")

val MovementInputMappings =
    mapOf(
        InputAction.MoveUp to listOf(Input.Keys.UP),
        InputAction.MoveDown to listOf(Input.Keys.DOWN),
        InputAction.MoveLeft to listOf(Input.Keys.LEFT),
        InputAction.MoveRight to listOf(Input.Keys.RIGHT),
    )
