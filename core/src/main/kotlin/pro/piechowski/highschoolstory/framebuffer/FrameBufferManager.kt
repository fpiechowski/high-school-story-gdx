package pro.piechowski.highschoolstory.framebuffer

import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ktx.async.RenderingScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.camera.PixelViewportManager

open class FrameBufferManager : KoinComponent {
    private val coroutineScope = RenderingScope()

    private val pixelViewportManager by inject<PixelViewportManager>()

    val frameBuffer =
        pixelViewportManager.pixelViewport
            .map { pixelViewport ->
                FrameBuffer(
                    Pixmap.Format.RGBA8888,
                    pixelViewport.worldWidth.toInt(),
                    pixelViewport.worldHeight.toInt(),
                    false,
                )
            }.stateIn(
                coroutineScope,
                SharingStarted.Companion.Eagerly,
                FrameBuffer(
                    Pixmap.Format.RGBA8888,
                    pixelViewportManager.pixelViewportValue.worldWidth.toInt(),
                    pixelViewportManager.pixelViewportValue.worldHeight.toInt(),
                    false,
                ),
            )

    val currentFrameBuffer
        get() = frameBuffer.value
}
