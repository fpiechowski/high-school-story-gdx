package pro.piechowski.highschoolstory.framebuffer

import com.github.quillraven.fleks.IntervalSystem
import io.github.oshai.kotlinlogging.KotlinLogging
import org.koin.core.component.KoinComponent

open class EndFrameBufferSystem<T : FrameBufferManager>(
    private val frameBufferManager: T,
) : IntervalSystem(),
    KoinComponent {
    private val logger = KotlinLogging.logger { }

    override fun onTick() {
        frameBufferManager.frameBuffer.value.end()

        logger.debug { "Frame buffer ended" }
    }
}
