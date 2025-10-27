package pro.piechowski.highschoolstory.inspector.ecs

import javafx.collections.FXCollections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import pro.piechowski.highschoolstory.inspector.InspectorViewModel
import pro.piechowski.highschoolstory.inspector.tickerFlow
import kotlin.time.Duration.Companion.seconds

@ExperimentalCoroutinesApi
class EcsViewModel(
    private val ecs: ECS,
) : InspectorViewModel() {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val componentTypes =
        tickerFlow(2.seconds)
            .map { FXCollections.observableList(ecs.componentTypes) }
            .stateIn(coroutineScope, SharingStarted.Eagerly, emptyList())

    val entityComponents =
        ecs.entityComponents
            .map {
                it
                    .filterValues { it.isNotEmpty() }
                    .toList()
                    .let { FXCollections.observableList(it) }
            }.stateIn(coroutineScope, SharingStarted.Eagerly, FXCollections.emptyObservableList())
}
