package pro.piechowski.highschoolstory.inspector.runtime

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import kotlinx.coroutines.flow.map
import org.kordamp.ikonli.fontawesome6.FontAwesomeSolid
import pro.piechowski.highschoolstory.inspector.View
import pro.piechowski.highschoolstory.inspector.asObservableValue
import pro.piechowski.highschoolstory.inspector.iconProperty

class RuntimeView(
    viewModel: RuntimeViewModel,
) : View() {
    private val playButton: Button =
        Button()
            .apply {
                graphicProperty().bind(iconProperty(FontAwesomeSolid.PLAY, 24, Color.GREEN, disableProperty()))
                onAction = viewModel.startButtonEventHandler
                disableProperty().bind(viewModel.gameRunning.asObservableValue(coroutineScope, false))
            }

    private val pauseButton: Button =
        Button()
            .apply {
                graphicProperty().bind(iconProperty(FontAwesomeSolid.PAUSE, 24, Color.ORANGE, disableProperty()))
                onAction = viewModel.pauseButtonEventHandler
                disableProperty().bind(viewModel.gameRunning.map { !it }.asObservableValue(coroutineScope, false))
            }

    private val stopButton: Button =
        Button()
            .apply {
                graphicProperty().bind(iconProperty(FontAwesomeSolid.STOP, 24, Color.RED, disableProperty()))
                onAction = viewModel.stopButtonEventHandler
                disableProperty().bind(viewModel.gameRunning.map { !it }.asObservableValue(coroutineScope, false))
            }

    override val root =
        HBox(playButton, pauseButton, stopButton).apply {
            alignment = Pos.CENTER
            spacing = 5.0
            padding = Insets(10.0)
        }
}
