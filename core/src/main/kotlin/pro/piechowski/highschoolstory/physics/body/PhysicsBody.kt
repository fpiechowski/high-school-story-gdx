package pro.piechowski.highschoolstory.physics.body

import com.badlogic.gdx.physics.box2d.Body
import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType

class PhysicsBody(
    val body: Body,
) : Component<PhysicsBody> {
    override fun type(): ComponentType<PhysicsBody> = PhysicsBody

    companion object : ComponentType<PhysicsBody>()
}
