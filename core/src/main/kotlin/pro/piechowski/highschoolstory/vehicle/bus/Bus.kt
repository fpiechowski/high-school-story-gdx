package pro.piechowski.highschoolstory.vehicle.bus

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import org.koin.core.Koin
import pro.piechowski.highschoolstory.direction.Direction4
import pro.piechowski.highschoolstory.ecs.Archetype
import pro.piechowski.highschoolstory.ecs.plusAssign
import pro.piechowski.highschoolstory.physics.MetersPerSeconds
import pro.piechowski.highschoolstory.physics.movement.Speed
import pro.piechowski.highschoolstory.physics.movement.facedirection.FaceDirection4
import pro.piechowski.highschoolstory.physics.movement.input.MovementInput
import pro.piechowski.highschoolstory.power.Powered
import pro.piechowski.highschoolstory.spatial.Spatial

class Bus(
    override val entity: Entity,
) : Spatial {
    companion object {
        context(koin: Koin)
        suspend operator fun invoke(
            direction4: Direction4,
            color: BusColor,
            speed: MetersPerSeconds,
        ) = Bus(
            koin.get<World>().entity {
                it += archetype(direction4, color, speed)
            },
        )

        context(koin: Koin)
        suspend fun archetype(
            direction4: Direction4,
            color: BusColor,
            speed: MetersPerSeconds,
        ) = with(koin) {
            Archetype {
                this += FaceDirection4(direction4)
                this += BusSprite(color, direction4)
                val physicsBody = BusBody(direction4)
                this += physicsBody
                this += Powered()
                this += BusLights.Headlights(direction4, physicsBody)
                this += Speed(speed)
                this += MovementInput.Multiplex()
                this += MovementInput.AI(direction4)
            }
        }
    }
}
