package pro.piechowski.highschoolstory.inspector.runtime

import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.event.EventHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import pro.piechowski.highschoolstory.Entrypoint
import pro.piechowski.highschoolstory.entrypointOverride
import pro.piechowski.highschoolstory.inspector.classGraph
import pro.piechowski.highschoolstory.inspector.tickerFlow
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor
import kotlin.time.Duration.Companion.seconds

class RuntimeViewModel(
    private val runtime: Runtime,
) {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val entrypoints =
        tickerFlow(2.seconds)
            .map {
                classGraph
                    .scan()
                    .use { scan ->
                        scan
                            .getClassesImplementing(Entrypoint::class.java)
                            .loadClasses()
                            .map { it.kotlin as KClass<Entrypoint> }
                    }.let { FXCollections.observableList(it) }
            }.stateIn(coroutineScope, SharingStarted.Eagerly, FXCollections.emptyObservableList())

    val selectedEntrypoint =
        MutableStateFlow<KClass<Entrypoint>?>(null)
            .also {
                it
                    .onEach {
                        entrypointOverride = it?.primaryConstructor?.call()
                    }.launchIn(coroutineScope)
            }

    val gameRunning: Flow<Boolean> = runtime.launchedGameJob.map { it != null && it.isActive }

    val playButtonEventHandler =
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
