package pro.piechowski.highschoolstory.framebuffer

import com.github.quillraven.fleks.IntervalSystem
import io.github.oshai.kotlinlogging.KotlinLogging
import org.koin.core.component.KoinComponent

open class BeginFrameBufferSystem<T : FrameBufferManager>(
    private val frameBufferManager: T,
) : IntervalSystem(),
    KoinComponent {
    private val logger = KotlinLogging.logger { }

    override fun onTick() {
        frameBufferManager.frameBuffer.value.begin()

        logger.debug { "Frame buffer started" }
    }
}
