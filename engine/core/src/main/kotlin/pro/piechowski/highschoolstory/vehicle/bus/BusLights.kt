package pro.piechowski.highschoolstory.vehicle.bus

import box2dLight.ConeLight
import com.badlogic.gdx.graphics.Color
import org.koin.core.Koin
import pro.piechowski.highschoolstory.direction.Direction4
import pro.piechowski.highschoolstory.physics.body.PhysicsBody

object BusLights {
    object Headlights {
        context(koin: Koin)
        operator fun invoke(
            direction4: Direction4,
            physicsBody: PhysicsBody,
        ) = with(koin) {
            VehicleLights.Headlights(
                listOf(
                    ConeLight(
                        get(),
                        64,
                        Color(1f, 0.95f, 0.85f, 1f),
                        20f,
                        when (direction4) {
                            Direction4.Right -> 10f
                            else -> TODO()
                        },
                        when (direction4) {
                            Direction4.Right -> 10f
                            else -> TODO()
                        },
                        direction4.angleDegrees,
                        20f,
                    ).apply {
                        attachToBody(physicsBody.body)
                    },
                ),
            )
        }
    }
}
