package pro.piechowski.highschoolstory.inspector.ecs

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import com.github.quillraven.fleks.Entity
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.Scene
import javafx.scene.control.ComboBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.util.Callback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import pro.piechowski.highschoolstory.inspector.asBidirectionalObservableValue
import pro.piechowski.highschoolstory.inspector.asObservableValue
import pro.piechowski.highschoolstory.inspector.`object`.ObjectTableCell

class EcsInspectorView(
    private val viewModel: EcsInspectorViewModel,
) {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val entityColumn =
        TableColumn<Pair<Entity, Component<Any>>, Entity>()
            .apply {
                text = "Name"
                cellValueFactory =
                    Callback {
                        SimpleObjectProperty(it.value.first)
                    }
            }

    private val componentNameColumn =
        TableColumn<Pair<Entity, Component<Any>>, Any>()
            .apply {
                text = "Name"
                cellFactory =
                    Callback {
                        ObjectTableCell()
                    }
                cellValueFactory =
                    Callback {
                        SimpleObjectProperty(it.value.second)
                    }
            }

    private val componentTable =
        TableView<Pair<Entity, Component<Any>>>().apply {
            columns += listOf(entityColumn, componentNameColumn)
            columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY_NEXT_COLUMN
            VBox.setVgrow(this, Priority.ALWAYS)
            itemsProperty().bind(viewModel.components.asObservableValue(coroutineScope, FXCollections.observableList(listOf())))
        }

    private val componentTypeComboBox =
        ComboBox<Class<ComponentType<Any>>>().apply {
            itemsProperty().bind(viewModel.componentTypes.asObservableValue(coroutineScope))
            valueProperty().bindBidirectional(viewModel.componentTypeFilter.asBidirectionalObservableValue(coroutineScope))
        }

    private val root =
        VBox().apply {
            children += listOf(componentTypeComboBox, componentTable)
        }

    val scene = Scene(root)
}
