package pro.piechowski.highschoolstory.inspector.ecs

import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.util.Callback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.runningFold
import pro.piechowski.highschoolstory.inspector.View
import pro.piechowski.highschoolstory.inspector.asObservableValue
import pro.piechowski.highschoolstory.inspector.`object`.ObjectInspectorViewModel
import pro.piechowski.highschoolstory.inspector.`object`.ObjectTableCell
import kotlin.collections.associateWith

@ExperimentalCoroutinesApi
class ECSView(
    viewModel: ECSViewModel,
    objectInspectorViewModel: ObjectInspectorViewModel,
) : View() {
    private val entityColumn =
        TableColumn<Pair<ECS.Entity, List<ECS.Component>>, ECS.Entity>()
            .apply {
                text = "Name"
                cellValueFactory =
                    Callback {
                        SimpleObjectProperty(it.value.first)
                    }
            }

    private val componentTypeColumns =
        viewModel.entityComponents
            .map {
                it
                    .flatMap { it.second }
                    .map { it.type }
                    .distinct()
                    .map { componentType ->
                        TableColumn<Pair<ECS.Entity, List<ECS.Component>>, Any?>()
                            .apply {
                                text = componentType.name
                                cellValueFactory =
                                    Callback {
                                        SimpleObjectProperty(
                                            it.value.second
                                                .find { it.type == componentType }
                                                ?.value,
                                        )
                                    }
                                cellFactory =
                                    Callback {
                                        ObjectTableCell(objectInspectorViewModel)
                                    }
                                prefWidth = 100.0
                            }
                    }
            }.preserveColumnsWidth()

    private val entityTable =
        TableView<Pair<ECS.Entity, List<ECS.Component>>>().apply {
            componentTypeColumns
                .map { componentTypeColumns ->
                    columns.setAll(listOf(entityColumn) + componentTypeColumns)
                }.launchIn(coroutineScope)

            VBox.setVgrow(this, Priority.ALWAYS)

            itemsProperty().bind(
                viewModel.entityComponents.asObservableValue(coroutineScope),
            )
        }

    override val root =
        VBox().apply {
            children += listOf(entityTable)
        }

    private fun <S, T> Flow<List<TableColumn<S, T>>>.preserveColumnsWidth() =
        runningFold(emptyList<TableColumn<S, T>>()) { previousColumns, nextColumns ->
            nextColumns
                .associateWith { nextColumn -> previousColumns.find { it.text == nextColumn.text } }
                .mapKeys { (nextColumn, previousColumn) ->
                    previousColumn?.let {
                        nextColumn.apply {
                            prefWidth = previousColumn.width
                        }
                    } ?: nextColumn
                }.keys
                .toList()
        }
}
