package pro.piechowski.highschoolstory.inspector.game

import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.stage.Stage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import pro.piechowski.highschoolstory.inspector.asObservableValue

class GameInspectorView(
    private val viewModel: GameInspectorViewModel,
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val launchButton: Button =
        Button("Launch")
            .apply {
                onAction = viewModel.launchGameButtonEventHandler
                disableProperty().bind(viewModel.gameRunning.asObservableValue(coroutineScope, false))
            }

    private val root = HBox(launchButton)

    private val scene = Scene(root)

    private val stage =
        Stage().apply {
            minWidth = 300.0
            minHeight = 100.0
            scene = this@GameInspectorView.scene
            title = "Game"
            onCloseRequest = viewModel.onCloseRequestEventHandler

            focusedProperty().addListener { _, _, newValue ->
                coroutineScope.launch { viewModel.focused.emit(newValue) }
            }
        }

    init {
        coroutineScope.launch {
            launch {
                viewModel.toFrontIntents.collect {
                    stage.toFront()
                }
            }

            launch {
                viewModel.showIntents.collect {
                    stage.apply {
                        show()
                        centerOnScreen()
                        y = 0.0
                    }
                }
            }
        }
    }
}
