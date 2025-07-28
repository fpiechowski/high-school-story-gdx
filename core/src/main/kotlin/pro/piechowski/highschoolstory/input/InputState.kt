package pro.piechowski.highschoolstory.input

import kotlinx.coroutines.flow.MutableStateFlow

class InputState {
    val mode: MutableStateFlow<Mode> = MutableStateFlow(Mode.MENU)

    enum class Mode {
        EXPLORATION,
        DIALOGUE,
        MENU,
    }
}
