package pro.piechowski.highschoolstory.vehicle.bus

import ktx.box2d.body
import ktx.box2d.box
import org.koin.core.Koin
import pro.piechowski.kge.direction.Direction4
import pro.piechowski.kge.physics.PhysicsWorld
import pro.piechowski.kge.physics.body.PhysicsBody
import pro.piechowski.kge.physics.px

object BusBody {
    context(koin: Koin)
    operator fun invoke(direction4: Direction4) =
        when (direction4) {
            Direction4.Left, Direction4.Right -> Horizontal()
            Direction4.Up, Direction4.Down -> Vertical()
        }

    object Horizontal {
        context(koin: Koin)
        operator fun invoke() =
            PhysicsBody(
                koin
                    .get<PhysicsWorld>()
                    .body {
                        box(
                            336f.px.toMeter().value,
                            192f.px.toMeter().value / 2,
                        )
                    },
            )
    }

    object Vertical {
        context(koin: Koin)
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
