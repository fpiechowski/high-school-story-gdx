package pro.piechowski.highschoolstory.movement.velocity

import com.badlogic.gdx.math.Vector2
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import io.github.oshai.kotlinlogging.KotlinLogging
import ktx.math.times
import pro.piechowski.highschoolstory.ReadOnly
import pro.piechowski.highschoolstory.Write
import pro.piechowski.highschoolstory.debug
import pro.piechowski.highschoolstory.movement.Speed
import pro.piechowski.highschoolstory.movement.input.MovementInput

class VelocitySystem :
    IteratingSystem(
        World.family {
            all(
                @ReadOnly MovementInput.Multiplex,
                @ReadOnly Speed,
                @Write Velocity,
            )
        },
    ) {
    private val logger = KotlinLogging.logger { }

    override fun onTickEntity(entity: Entity) {
        val movementInput = entity[MovementInput.Multiplex].movementInput

        val velocity = entity[Velocity]
        velocity.velocity = movementInput * entity[Speed.Companion].speed

        if (velocity.velocity != Vector2.Zero.cpy()) {
            logger.debug(velocity, entity)
        }
    }
}
