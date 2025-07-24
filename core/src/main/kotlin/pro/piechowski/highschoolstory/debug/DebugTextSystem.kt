package pro.piechowski.highschoolstory.debug

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World.Companion.family
import ktx.graphics.use
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.physics.body.PhysicsBody

class DebugTextSystem :
    IteratingSystem(
        family { all(DebugText, PhysicsBody) },
    ),
    KoinComponent {
    private val spriteBatch: SpriteBatch by inject()
    private val bitmapFont: BitmapFont by inject()

    override fun onTickEntity(entity: Entity) {
        val debugText = entity[DebugText]
        val position = entity[PhysicsBody].body.position

        spriteBatch.use {
            bitmapFont.draw(spriteBatch, debugText.getText(world, entity), position.x, position.y)
        }
    }
}
