package pro.piechowski.highschoolstory.physics.collision

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import ktx.graphics.use
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CollisionShapeDebugSystem :
    IteratingSystem(
        World.family {
            CollisionShape.any()
        },
    ),
    KoinComponent {
    private val shapeRenderer: ShapeRenderer by inject()

    override fun onTickEntity(entity: Entity) {
        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)

        val collisionShape = entity[CollisionShape]

        shapeRenderer.use(ShapeRenderer.ShapeType.Filled) {
            it.color = Color.YELLOW.cpy().also { color -> color.a = 0.2f }

            when (collisionShape) {
                is CollisionShape.Circle ->
                    it.circle(
                        collisionShape.shape.x,
                        collisionShape.shape.y,
                        collisionShape.shape.radius,
                        16,
                    )

                is CollisionShape.Ellipse ->
                    it.ellipse(
                        collisionShape.shape.x,
                        collisionShape.shape.y,
                        collisionShape.shape.width,
                        collisionShape.shape.height,
                    )

                is CollisionShape.Rectangle ->
                    it.rect(
                        collisionShape.shape.x,
                        collisionShape.shape.y,
                        collisionShape.shape.width,
                        collisionShape.shape.height,
                    )
            }
        }
    }
}
