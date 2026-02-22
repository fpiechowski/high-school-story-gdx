package pro.piechowski.highschoolstory.vehicle.bus

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.EntityCreateContext
import pro.piechowski.kge.gameobject.Archetype
import pro.piechowski.kge.gameobject.BindableEntityGameObject
import pro.piechowski.kge.gameobject.EntityGameObjectCompanion
import pro.piechowski.kge.direction.Direction4
import pro.piechowski.kge.gameobject.Prototype
import pro.piechowski.kge.gameobject.from
import pro.piechowski.kge.facedirection.FaceDirection4
import pro.piechowski.kge.movement.Speed
import pro.piechowski.kge.movement.input.MovementInput
import pro.piechowski.kge.gameobject.Kinetic
import pro.piechowski.kge.gameobject.Spatial
import pro.piechowski.kge.gameobject.Visual
import pro.piechowski.kge.physics.MetersPerSeconds
import pro.piechowski.kge.physics.body.PhysicsBody
import pro.piechowski.kge.power.Powered
import pro.piechowski.kge.sprite.CurrentSprite
import pro.piechowski.kge.vehicle.VehicleLights
import pro.piechowski.kge.world

interface Bus :
    Visual,
    Spatial,
    Kinetic {

    companion object : EntityGameObjectCompanion<Bus>({ BindableBus(it) }) {
        suspend operator fun invoke(
            direction4: Direction4,
            color: BusColor,
            speed: MetersPerSeconds,
        ) = Bus(
            world.entity {
                it.from(prototype(direction4, color, speed))
            },
        )

        context(ecc: EntityCreateContext)
        fun prototype(
            direction4: Direction4,
            color: BusColor,
            speed: MetersPerSeconds,
        ) = Prototype {
            with(ecc) {
                it += FaceDirection4(direction4)
                it += BusSprite(color, direction4)
                val physicsBody = BusBody(direction4)
                it += physicsBody
                it += Powered()
                it += BusLights.Headlights(direction4, physicsBody)
                it += Speed(speed)
                it += MovementInput.AI(direction4)
            }
        }

        override val archetype = Archetype {
            all(
                FaceDirection4,
                CurrentSprite,
                PhysicsBody,
                Powered,
                VehicleLights.Headlights,
                Speed,
                MovementInput.AI
            )
        }
    }
}

class BindableBus(_entity: Entity) : Bus, BindableEntityGameObject<Bus>(_entity)
