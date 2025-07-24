package pro.piechowski.highschoolstory.physics.collision

import com.badlogic.gdx.math.Shape2D
import com.badlogic.gdx.math.Vector2
import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.EntityComponentContext
import com.github.quillraven.fleks.FamilyDefinition
import com.github.quillraven.fleks.FleksNoSuchEntityComponentException
import pro.piechowski.highschoolstory.gdx.GdxCircle
import pro.piechowski.highschoolstory.gdx.GdxEllipse
import pro.piechowski.highschoolstory.gdx.GdxRectangle
import kotlin.reflect.jvm.jvmName

sealed class CollisionShape(
    open val shape: Shape2D,
    open val offset: Vector2,
) {
    companion object {
        context(fd: FamilyDefinition)
        fun any() =
            with(fd) {
                any(Rectangle, Circle, Ellipse)
            }
    }

    data class Rectangle(
        override val shape: GdxRectangle,
        override val offset: Vector2,
    ) : CollisionShape(shape, offset),
        Component<Rectangle> {
        override fun type(): ComponentType<Rectangle> = Rectangle

        companion object : ComponentType<Rectangle>()
    }

    data class Circle(
        override val shape: GdxCircle,
        override val offset: Vector2,
    ) : CollisionShape(shape, offset),
        Component<Circle> {
        override fun type(): ComponentType<Circle> = Circle

        companion object : ComponentType<Circle>()
    }

    data class Ellipse(
        override val shape: GdxEllipse,
        override val offset: Vector2,
    ) : CollisionShape(shape, offset),
        Component<Ellipse> {
        override fun type(): ComponentType<Ellipse> = Ellipse

        companion object : ComponentType<Ellipse>()
    }
}

context(ecc: EntityComponentContext)
operator fun Entity.get(type: CollisionShape.Companion): CollisionShape =
    with(ecc) {
        getOrNull(CollisionShape.Rectangle) ?: getOrNull(CollisionShape.Circle) ?: getOrNull(CollisionShape.Ellipse)
            ?: throw FleksNoSuchEntityComponentException(
                this@get,
                CollisionShape::class.jvmName,
            )
    }
