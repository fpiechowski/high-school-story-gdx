package pro.piechowski.highschoolstory.physics.movement.input

import com.badlogic.gdx.math.Vector2
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import io.github.oshai.kotlinlogging.KotlinLogging
import ktx.math.plus
import pro.piechowski.highschoolstory.debug
import pro.piechowski.highschoolstory.ecs.ReadOnly
import pro.piechowski.highschoolstory.ecs.Write

class MovementMultiplexInputSystem :
    IteratingSystem(
        World.family {
            any(
                @ReadOnly MovementInput.AI,
                @ReadOnly MovementInput.Controller,
            ).all(
                @Write MovementInput.Multiplex,
            )
        },
    ) {
    private val logger = KotlinLogging.logger { }

    override fun onTickEntity(entity: Entity) {
        val multiplexMovementInput = entity[MovementInput.Multiplex]
        multiplexMovementInput.movementInput =
            (entity.getOrNull(MovementInput.Controller)?.movementInput ?: Vector2.Zero.cpy()) +
            (entity.getOrNull(MovementInput.AI)?.movementInput ?: Vector2.Zero)

        multiplexMovementInput.movementInput = multiplexMovementInput.movementInput.nor()

        if (multiplexMovementInput.movementInput != Vector2.Zero.cpy()) {
            logger.debug(multiplexMovementInput, entity)
        }
    }
}
