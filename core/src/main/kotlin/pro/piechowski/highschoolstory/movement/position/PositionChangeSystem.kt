package pro.piechowski.highschoolstory.movement.position

import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.Vector2
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.EntityComponentContext
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.collection.EntityBag
import io.github.oshai.kotlinlogging.KotlinLogging
import ktx.math.plusAssign
import ktx.math.times
import org.koin.core.component.KoinComponent
import pro.piechowski.highschoolstory.debug
import pro.piechowski.highschoolstory.ecs.ReadOnly
import pro.piechowski.highschoolstory.ecs.Write
import pro.piechowski.highschoolstory.gdx.toPolygon
import pro.piechowski.highschoolstory.movement.velocity.Velocity
import pro.piechowski.highschoolstory.physics.collision.CollisionShape
import pro.piechowski.highschoolstory.physics.collision.get

class PositionChangeSystem :
    IteratingSystem(
        World.family {
            all(
                @ReadOnly Velocity,
                @Write Position,
            )
            CollisionShape.any()
        },
    ),
    KoinComponent {
    private val logger = KotlinLogging.logger { }

    override fun onTickEntity(entity: Entity) {
        val position = entity[Position]
        val desiredPositionChange = entity[Velocity].velocity * deltaTime

        if (desiredPositionChange == Vector2.Zero) {
            return
        }

        val actualPositionChange =
            with(world) {
                entity.getDeltaPositionAfterCollisions(desiredPositionChange)
            }

        position.position += actualPositionChange

        logger.debug(position, entity)
    }

    context(world: World, ecc: EntityComponentContext)
    private fun Entity.getDeltaPositionAfterCollisions(desiredDeltaPosition: Vector2): Vector2 =
        with(ecc) {
            val colliders = world.family { CollisionShape.any() }.filter { it != this@getDeltaPositionAfterCollisions }

            val collidingEntitiesOnNewPosition =
                getCollidingEntities(colliders, desiredDeltaPosition)

            if (collidingEntitiesOnNewPosition.isEmpty()) {
                desiredDeltaPosition
            } else {
                val verticalOnlyDeltaPosition = desiredDeltaPosition.cpy().apply { x = 0f }
                val collidingEntitiesOnNewVerticalOnlyPosition =
                    getCollidingEntities(colliders, verticalOnlyDeltaPosition)

                if (collidingEntitiesOnNewVerticalOnlyPosition.isEmpty()) {
                    verticalOnlyDeltaPosition
                } else {
                    val horizontalOnlyDeltaPosition = desiredDeltaPosition.cpy().apply { y = 0f }
                    val collidingEntitiesOnNewHorizontalOnlyPosition =
                        getCollidingEntities(colliders, horizontalOnlyDeltaPosition)

                    if (collidingEntitiesOnNewHorizontalOnlyPosition.isEmpty()) {
                        horizontalOnlyDeltaPosition
                    }

                    Vector2.Zero
                        .cpy()
                        .also {
                            logger.debug {
                                "${this@getDeltaPositionAfterCollisions} can't move due to collision with $collidingEntitiesOnNewPosition"
                            }
                        }
                }
            }
        }

    context(ecc: EntityComponentContext)
    private fun Entity.getCollidingEntities(
        colliders: EntityBag,
        deltaPosition: Vector2,
    ): EntityBag {
        val movedEntityCollisionPolygon =
            this[CollisionShape]
                .shape
                .toPolygon()
                .also { it.setPosition(deltaPosition.x, deltaPosition.y) }

        return colliders.filter {
            val colliderCollisionPolygon =
                it[CollisionShape].shape.toPolygon()

            Intersector.overlapConvexPolygons(colliderCollisionPolygon, movedEntityCollisionPolygon)
        }
    }
}
