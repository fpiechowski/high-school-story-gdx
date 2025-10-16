package pro.piechowski.highschoolstory.inspector.ecs

import javafx.stage.Screen
import javafx.stage.Stage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.javafx.asFlow
import org.koin.core.Koin

class EcsInspector(
    koin: StateFlow<Koin?>,
) {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val model = EcsInspectorModel(koin)

    private val viewModel = EcsInspectorViewModel(model)
    private val view = EcsInspectorView(viewModel)
    private val stage =
        Stage().apply {
            width = 500.0
            height = 300.0
            scene = view.scene
        }

    fun show() =
        stage.apply {
            show()
            x = Screen.getPrimary().visualBounds.width - view.scene.width
            y = Screen.getPrimary().visualBounds.height - view.scene.height
        }

    val focused = stage.focusedProperty().asFlow()

    fun toFront() = stage.toFront()
}
