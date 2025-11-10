package pro.piechowski.highschoolstory.camera

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class CameraManager : KoinComponent {
    interface Mode

    private val _strategy = MutableStateFlow<Mode>(DefaultCameraMode)
    val strategy = _strategy.asStateFlow()

    fun setStrategy(mode: Mode) {
        _strategy.value = mode
    }
}
