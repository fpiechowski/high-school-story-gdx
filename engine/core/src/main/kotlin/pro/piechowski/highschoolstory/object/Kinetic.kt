package pro.piechowski.highschoolstory.`object`

import com.badlogic.gdx.math.Vector2
import com.github.quillraven.fleks.EntityComponentContext
import pro.piechowski.highschoolstory.ecs.get
import pro.piechowski.highschoolstory.input.movement.MovementInput
import pro.piechowski.highschoolstory.movement.Speed
import pro.piechowski.highschoolstory.physics.MetersPerSeconds

interface Kinetic :
    Spatial,
    EntityGameObject {
    context(_: EntityComponentContext)
    val speed: Speed
        get() = entity[Speed.Companion]

    context(ecc: EntityComponentContext)
    val movementInput: MovementInput
        get() = entity[MovementInput.Multiplex]

    context(ecc: EntityComponentContext)
    fun moving(direction: Vector2) =
        apply {
            movementInput.movementInput = direction
        }

    context(ecc: EntityComponentContext)
    fun withSpeed(speed: MetersPerSeconds) =
        apply {
            this.speed.speed = speed
        }
}
