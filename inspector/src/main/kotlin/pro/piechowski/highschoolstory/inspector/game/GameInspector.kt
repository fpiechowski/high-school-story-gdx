package pro.piechowski.highschoolstory.inspector.game

import javafx.application.Platform
import javafx.event.EventHandler
import javafx.stage.Stage
import javafx.stage.WindowEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.javafx.asFlow

@ExperimentalCoroutinesApi
class GameInspector(
    gameScope: CoroutineScope,
) {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val model = GameInspectorModel(gameScope)
    private val viewModel = GameInspectorViewModel(model)
    private val view = GameInspectorView(viewModel)
    private val stage =
        Stage().apply {
            minWidth = 300.0
            minHeight = 100.0
            scene = view.scene
            title = "Game"
            onCloseRequest =
                EventHandler {
                    Platform.exit()
                }
        }

    fun show() =
        stage.apply {
            show()
            centerOnScreen()
            y = 0.0
        }

    val focused =
        stage
            .focusedProperty()
            .asFlow()

    fun toFront() =
        stage
            .toFront()
}
