package pro.piechowski.highschoolstory.debug.selection

import com.github.quillraven.fleks.Entity
import kotlinx.coroutines.flow.MutableStateFlow

class DebugSelectionManager {
    val selectedEntity = MutableStateFlow<Entity?>(null)
}
