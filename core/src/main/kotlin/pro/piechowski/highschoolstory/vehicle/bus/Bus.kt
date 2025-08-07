package pro.piechowski.highschoolstory.vehicle.bus

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import ktx.box2d.body
import ktx.box2d.box
import org.koin.core.Koin
import pro.piechowski.highschoolstory.ecs.Archetype
import pro.piechowski.highschoolstory.ecs.plusAssign
import pro.piechowski.highschoolstory.exterior.ExteriorTexture
import pro.piechowski.highschoolstory.gdx.PhysicsWorld
import pro.piechowski.highschoolstory.physics.body.PhysicsBody
import pro.piechowski.highschoolstory.physics.px
import pro.piechowski.highschoolstory.rendering.sprite.CurrentSprite
import pro.piechowski.highschoolstory.spatial.Spatial

class Bus(
    override val entity: Entity,
) : Spatial {
    companion object {
        context(koin: Koin)
        suspend operator fun invoke() =
            Bus(
                koin.get<World>().entity {
                    it += archetype()
                },
            )

        context(koin: Koin)
        suspend fun archetype() =
            with(koin) {
                Archetype {
                    this += CurrentSprite(BusSprite.Brown.Right(get<ExteriorTexture>()))
                    this +=
                        PhysicsBody(
                            get<PhysicsWorld>()
                                .body {
                                    box(336f.px.toMeter().value, 192f.px.toMeter().value)
                                },
                        )
                }
            }
    }
}
