package pro.piechowski.highschoolstory.vehicle.bus

import com.badlogic.gdx.physics.box2d.BodyDef
import ktx.box2d.body
import ktx.box2d.box
import pro.piechowski.kge.direction.Direction4
import pro.piechowski.kge.koin
import pro.piechowski.kge.physics.PhysicsWorld
import pro.piechowski.kge.physics.body.PhysicsBody
import pro.piechowski.kge.physics.px

object BusBody {
    operator fun invoke(direction4: Direction4) =
        when (direction4) {
            Direction4.Left, Direction4.Right -> Horizontal()
            Direction4.Up, Direction4.Down -> Vertical()
        }

    object Horizontal {
        operator fun invoke() =
            PhysicsBody(
                koin
                    .get<PhysicsWorld>()
                    .body(BodyDef.BodyType.DynamicBody) {
                        box(
                            336f.px.toMeter().value,
                            192f.px.toMeter().value / 2,
                        )
                    },
            )
    }

    object Vertical {
        operator fun invoke() =
            PhysicsBody(
                koin
                    .get<PhysicsWorld>()
                    .body {
                        box(
                            144.px.toMeter().value,
                            336.px.toMeter().value,
                        )
                    },
            )
    }
}
