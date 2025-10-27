package pro.piechowski.highschoolstory.inspector.runtime

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import kotlinx.coroutines.flow.map
import org.kordamp.ikonli.javafx.FontIcon
import pro.piechowski.highschoolstory.inspector.InspectorView
import pro.piechowski.highschoolstory.inspector.asObservableValue

class RuntimeView(
    viewModel: RuntimeViewModel,
) : InspectorView<RuntimeViewModel>(viewModel) {
    private val playButton: Button =
        Button("", FontIcon("fas-play:24:GREEN"))
            .apply {
                graphicProperty().bind(disableProperty().map { if (it) FontIcon("fas-play:24:GRAY") else FontIcon("fas-play:24:GREEN") })
                onAction = viewModel.startButtonEventHandler
                disableProperty().bind(viewModel.gameRunning.asObservableValue(coroutineScope, false))
            }

    private val pauseButton: Button =
        Button("", FontIcon("fas-pause:24:ORANGE"))
            .apply {
                graphicProperty().bind(disableProperty().map { if (it) FontIcon("fas-pause:24:GRAY") else FontIcon("fas-pause:24:ORANGE") })
                onAction = viewModel.pauseButtonEventHandler
                disableProperty().bind(viewModel.gameRunning.map { !it }.asObservableValue(coroutineScope, false))
            }

    private val stopButton: Button =
        Button("", FontIcon("fas-stop:24:RED"))
            .apply {
                graphicProperty().bind(disableProperty().map { if (it) FontIcon("fas-stop:24:GRAY") else FontIcon("fas-stop:24:RED") })
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
