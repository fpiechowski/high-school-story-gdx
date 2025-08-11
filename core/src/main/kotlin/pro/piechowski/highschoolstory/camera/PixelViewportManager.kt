package pro.piechowski.highschoolstory.camera

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.updateAndGet
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class PixelViewportManager : KoinComponent {
    private val _pixelViewport = MutableStateFlow(PixelViewport(get()))

    val pixelViewport = _pixelViewport.asStateFlow()

    val pixelViewportValue get() = _pixelViewport.value

    fun update(
        width: Int,
        height: Int,
    ) = _pixelViewport.updateAndGet { it.apply { update(width, height) } }
}
