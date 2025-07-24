package pro.piechowski.highschoolstory.physics.collision

import com.badlogic.gdx.math.Vector2
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import ktx.math.minus
import ktx.math.plus
import pro.piechowski.highschoolstory.ecs.ReadOnly
import pro.piechowski.highschoolstory.movement.position.Position

class CollisionShapePositionSystem :
    IteratingSystem(
        World.family {
            all(
                @ReadOnly Position,
            )
            CollisionShape.any()
        },
    ) {
    override fun onTickEntity(entity: Entity) {
        val position = entity[Position]

        entity
            .getOrNull(CollisionShape.Rectangle)
            ?.also {
                it.shape.setPosition(
                    position.position -
                        Vector2(
                            it.shape.width / 2f,
                            it.shape.height / 2f,
                        ) + it.offset,
                )
            }

        entity
            .getOrNull(CollisionShape.Circle)
            ?.also {
                it.shape.setPosition(
                    position.position -
                        Vector2(
                            it.shape.radius / 2f,
                            it.shape.radius / 2f,
                        ) + it.offset,
                )
            }

        entity
            .getOrNull(CollisionShape.Ellipse)
            ?.also {
                it.shape.setPosition(
                    position.position -
                        Vector2(
                            it.shape.width / 2f,
                            it.shape.height / 2f,
                        ) + it.offset,
                )
            }
    }
}
