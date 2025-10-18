package pro.piechowski.highschoolstory.inspector.game

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.WindowEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map

class GameInspectorViewModel(
    private val model: GameInspectorModel,
) {
    private val _toFrontIntents = MutableSharedFlow<Unit>()
    val toFrontIntents: SharedFlow<Unit> = _toFrontIntents

    suspend fun toFront() = _toFrontIntents.emit(Unit)

    val showIntents = model.showIntents

    private val _focused = MutableSharedFlow<Boolean>()
    val focused: SharedFlow<Boolean> = _focused

    val gameRunning: Flow<Boolean> = model.gameLaunchJob.map { it != null && it.isActive }

    val launchGameButtonEventHandler =
        EventHandler<ActionEvent> {
            model.launchGame()
        }

    val onCloseRequestEventHandler =
        EventHandler<WindowEvent> {
            Platform.exit()
        }
}
