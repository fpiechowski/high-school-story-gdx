package pro.piechowski.highschoolstory.inspector.`object`

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.control.OverrunStyle
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Priority
import javafx.scene.layout.Region.USE_COMPUTED_SIZE
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.util.Callback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import pro.piechowski.highschoolstory.inspector.View
import pro.piechowski.highschoolstory.inspector.asObservableValue

@ExperimentalContextParameters
@ExperimentalCoroutinesApi
class ObjectInspectorView(
    viewModel: ObjectInspectorViewModel,
) : View() {
    private val propertyColumn =
        TableColumn<ObjectInspectorViewModel.ObjectProperty, String>()
            .apply {
                minWidth = 100.0
                prefWidth = USE_COMPUTED_SIZE
                maxWidth = Double.MAX_VALUE
                text = "Property"
                cellValueFactory =
                    Callback {
                        SimpleStringProperty(it.value.name)
                    }
            }

    private val valueColumn =
        TableColumn<ObjectInspectorViewModel.ObjectProperty, Any>()
            .apply {
                minWidth = 100.0
                prefWidth = USE_COMPUTED_SIZE
                maxWidth = Double.MAX_VALUE
                text = "Value"
                cellFactory =
                    Callback { ObjectTableCell(viewModel) }
                cellValueFactory =
                    Callback {
                        SimpleObjectProperty(it.value.value)
                    }
            }

    private val propertiesTable =
        TableView<ObjectInspectorViewModel.ObjectProperty>()
            .apply {
                prefWidth = 400.0
                columnResizePolicy = CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS
                columns.addAll(propertyColumn, valueColumn)
                maxHeight = Double.MAX_VALUE
                prefHeight = USE_COMPUTED_SIZE

                addEventHandler(MouseEvent.MOUSE_CLICKED) { event ->
                    when (event.button) {
                        MouseButton.BACK -> viewModel.navigateBack()
                        MouseButton.FORWARD -> viewModel.navigateForward()
                        else -> {}
                    }
                }

                itemsProperty().bind(
                    viewModel.properties.map { FXCollections.observableList(it) }.asObservableValue(
                        coroutineScope,
                        FXCollections.emptyObservableList(),
                    ),
                )
            }

    override val root =
        VBox().apply {
            maxWidth = 400.0
            maxHeight = Double.MAX_VALUE

            children +=
                listOf(
                    Label().apply {
                        textProperty().bind(
                            viewModel.currentObject
                                .map { it?.name ?: "Object" }
                                .asObservableValue(coroutineScope, "Object"),
                        )
                        textOverrun = OverrunStyle.ELLIPSIS
                        font = Font.font(16.0)
                        padding = Insets(2.0)
                    },
                    propertiesTable,
                )

            VBox.setVgrow(propertiesTable, Priority.ALWAYS)
        }
}
