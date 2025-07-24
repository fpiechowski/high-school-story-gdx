package pro.piechowski.highschoolstory.rendering

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.quillraven.fleks.IntervalSystem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BeginRenderingBatchSystem :
    IntervalSystem(),
    KoinComponent {
    private val spriteBatch: SpriteBatch by inject()

    override fun onTick() {
        spriteBatch.begin()
    }
}

class EndRenderingBatchSystem :
    IntervalSystem(),
    KoinComponent {
    private val spriteBatch: SpriteBatch by inject()

    override fun onTick() {
        spriteBatch.end()
    }
}
