package pro.piechowski.highschoolstory.inspector.container

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS
import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import javafx.scene.layout.Region.USE_COMPUTED_SIZE
import javafx.scene.layout.VBox
import javafx.util.Callback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import pro.piechowski.highschoolstory.inspector.View
import pro.piechowski.highschoolstory.inspector.asBidirectionalObservableValue
import pro.piechowski.highschoolstory.inspector.asObservableValue
import pro.piechowski.highschoolstory.inspector.fullTypeName
import pro.piechowski.highschoolstory.inspector.`object`.ObjectInspectorViewModel
import pro.piechowski.highschoolstory.inspector.`object`.ObjectTableCell
import pro.piechowski.highschoolstory.inspector.toObservableList

@ExperimentalCoroutinesApi
class ObjectContainerView(
    viewModel: ObjectContainerViewModel,
    objectInspectorViewModel: ObjectInspectorViewModel,
) : View() {
    private val typeColumn =
        TableColumn<Object<Any>, String>().apply {
            minWidth = 100.0
            prefWidth = USE_COMPUTED_SIZE
            maxWidth = Double.MAX_VALUE
            text = "Type"
            cellValueFactory =
                Callback {
                    SimpleStringProperty(it.value.type.fullTypeName)
                }
        }

    private val valueColumn =
        TableColumn<Object<Any>, Any?>().apply {
            minWidth = 100.0
            prefWidth = USE_COMPUTED_SIZE
            maxWidth = Double.MAX_VALUE
            text = "Value"
            cellFactory =
                Callback { ObjectTableCell(objectInspectorViewModel) }
            cellValueFactory =
                Callback {
                    SimpleObjectProperty(it.value.instance ?: "null")
                }
        }

    private val instancesTable =
        TableView<Object<Any>>().apply {
            columnResizePolicy = CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS
            maxHeight = Double.MAX_VALUE
            VBox.setVgrow(this, Priority.ALWAYS)

            columns += listOf(typeColumn, valueColumn)

            itemsProperty().bind(
                viewModel.filteredInstances
                    .map { it.toObservableList() }
                    .asObservableValue(
                        coroutineScope,
                        FXCollections.emptyObservableList(),
                    ),
            )
        }

    private val searchTextField =
        TextField().apply {
            promptText = "Search..."
            textProperty().bindBidirectional(viewModel.searchText.asBidirectionalObservableValue(coroutineScope))
        }

    override val root =
        VBox().apply {
            prefWidth = 400.0

            children += listOf(searchTextField, instancesTable)
        }
}
