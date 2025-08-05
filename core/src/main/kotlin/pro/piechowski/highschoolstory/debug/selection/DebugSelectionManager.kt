package pro.piechowski.highschoolstory.debug.selection

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ktx.async.KtxAsync
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.debug.ui.DebugUserInterface
import pro.piechowski.highschoolstory.rendering.sprite.CurrentSprite

class DebugSelectionManager : KoinComponent {
    val selectedEntity = MutableStateFlow<Entity?>(null)

    private val debugUserInterface by inject<DebugUserInterface>()

    private val world by inject<World>()

    init {
        KtxAsync.launch {
            selectedEntity.collect { entity ->
                with(world) {
                    debugUserInterface.debugLabel.setText(entity?.toString() ?: "")
                }
            }
        }
    }
}
