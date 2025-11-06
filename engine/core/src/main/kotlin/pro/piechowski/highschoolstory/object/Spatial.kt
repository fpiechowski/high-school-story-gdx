package pro.piechowski.highschoolstory.`object`

import com.badlogic.gdx.math.Vector2
import com.github.quillraven.fleks.EntityComponentContext
import pro.piechowski.highschoolstory.ecs.get
import pro.piechowski.highschoolstory.physics.body.PhysicsBody

interface Spatial : EntityGameObject {
    context(ecc: EntityComponentContext)
    val body: PhysicsBody get() = entity[PhysicsBody.Companion]

    context(ecc: EntityComponentContext)
    var position: Vector2
        get() = body.body.position
        set(value) = body.body.setTransform(value, body.body.angle)

    context(ecc: EntityComponentContext)
    fun atPosition(position: Vector2) = apply { this.position = position }
}
