package pro.piechowski.highschoolstory.debug.highlight

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class DebugEntityHighlightManager : KoinComponent {
    private val _highlighted = MutableStateFlow<Boolean>(false)

    val highlighted: StateFlow<Boolean> get() = _highlighted.asStateFlow()

    var highlightedValue
        get() = highlighted.value
        set(value) {
            _highlighted.value = value
        }
}
