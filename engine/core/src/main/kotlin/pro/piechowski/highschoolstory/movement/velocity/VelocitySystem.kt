package pro.piechowski.highschoolstory.movement.velocity

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import io.github.oshai.kotlinlogging.KotlinLogging
import pro.piechowski.highschoolstory.ecs.ReadOnly
import pro.piechowski.highschoolstory.ecs.Write
import pro.piechowski.highschoolstory.input.movement.MovementInput
import pro.piechowski.highschoolstory.movement.Speed
import pro.piechowski.highschoolstory.physics.body.PhysicsBody
import pro.piechowski.highschoolstory.physics.times

class VelocitySystem :
    IteratingSystem(
        World.Companion.family {
            all(
                @ReadOnly MovementInput.Multiplex,
                @ReadOnly Speed,
                @Write PhysicsBody,
            )
        },
    ) {
    private val logger = KotlinLogging.logger { }

    override fun onTickEntity(entity: Entity) {
        val movementSpeed = entity[Speed.Companion]
        val movementInput = entity[MovementInput.Multiplex]
        val physicsBody = entity[PhysicsBody]

        physicsBody.body.linearVelocity = movementInput.movementInput * movementSpeed.speed
    }
}
