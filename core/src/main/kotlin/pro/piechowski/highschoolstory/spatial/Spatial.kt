package pro.piechowski.highschoolstory.spatial

import com.badlogic.gdx.math.Vector2
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.EntityComponentContext
import pro.piechowski.highschoolstory.physics.body.PhysicsBody
import pro.piechowski.highschoolstory.rendering.sprite.CurrentSprite

abstract class Spatial(
    open val entity: Entity,
) {
    context(ecc: EntityComponentContext)
    open val body: PhysicsBody get() = with(ecc) { entity[PhysicsBody.Companion] }

    context(ecc: EntityComponentContext)
    open val sprite: CurrentSprite get() = with(ecc) { entity[CurrentSprite.Companion] }

    context(ecc: EntityComponentContext)
    var position: Vector2 get() = body.body.position
        set(value) = body.body.setTransform(value, body.body.angle)
}
