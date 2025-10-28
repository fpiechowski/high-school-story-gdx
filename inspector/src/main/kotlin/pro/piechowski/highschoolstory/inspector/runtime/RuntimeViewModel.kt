package pro.piechowski.highschoolstory.inspector.runtime

import javafx.event.ActionEvent
import javafx.event.EventHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RuntimeViewModel(
    private val runtime: Runtime,
) {
    val gameRunning: Flow<Boolean> = runtime.launchedGameJob.map { it != null && it.isActive }

    val startButtonEventHandler =
        EventHandler<ActionEvent> {
            runtime.start()
        }

    val stopButtonEventHandler =
        EventHandler<ActionEvent> {
            runtime.stop()
        }

    val pauseButtonEventHandler =
        EventHandler<ActionEvent> {
            runtime.pause()
        }
}
