package pro.piechowski.highschoolstory.vehicle.bus

import com.github.quillraven.fleks.Entity
import pro.piechowski.kge.direction.Direction4
import pro.piechowski.kge.ecs.Prototype
import pro.piechowski.kge.ecs.plusAssign
import pro.piechowski.kge.facedirection.FaceDirection4
import pro.piechowski.kge.movement.input.MovementInput
import pro.piechowski.kge.movement.Speed
import pro.piechowski.kge.`object`.Kinetic
import pro.piechowski.kge.`object`.Spatial
import pro.piechowski.kge.`object`.Visual
import pro.piechowski.kge.physics.MetersPerSeconds
import pro.piechowski.kge.power.Powered
import pro.piechowski.kge.world

class Bus(
    override val entity: Entity,
) : Visual,
    Spatial,
    Kinetic {
    companion object {


        suspend operator fun invoke(
            direction4: Direction4,
            color: BusColor,
            speed: MetersPerSeconds,
        ) = Bus(
            world.entity {
                it += archetype(it, direction4, color, speed)
            },
        )

        suspend fun archetype(
            entity: Entity,
            direction4: Direction4,
            color: BusColor,
            speed: MetersPerSeconds,
        ) = Prototype {
            this += FaceDirection4(direction4)
            this += BusSprite(color, direction4)
            val physicsBody = BusBody(direction4)
            this += physicsBody
            this += Powered()
            this += BusLights.Headlights(direction4, physicsBody)
            this += Speed(speed)
            this += MovementInput.AI(direction4)
        }
    }
}
