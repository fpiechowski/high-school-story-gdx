package pro.piechowski.highschoolstory.inspector

import io.github.oshai.kotlinlogging.KotlinLogging
import javafx.application.Application
import javafx.stage.Stage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import org.koin.core.Koin
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.GlobalContext
import pro.piechowski.highschoolstory.inspector.ecs.EcsInspector
import pro.piechowski.highschoolstory.inspector.game.GameInspector
import pro.piechowski.highschoolstory.inspector.koin.KoinInspector

@ExperimentalCoroutinesApi
@KoinInternalApi
class InspectorApplication : Application() {
    private val logger = KotlinLogging.logger { }

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val gameScope = CoroutineScope(newSingleThreadContext("GameScope"))

    private val koin: StateFlow<Koin?> =
        flow {
            while (true) {
                GlobalContext.getOrNull()?.let {
                    emit(it)
                }
                delay(2000)
            }
        }.stateIn(coroutineScope, SharingStarted.Eagerly, null)

    private val gameInspector = GameInspector(gameScope)
    private val koinInspector = KoinInspector()
    private val ecsInspector = EcsInspector(koin)

    private val inspectorViewModel = InspectorViewModel()

    override fun start(primaryStage: Stage?) {
        coroutineScope.launch {
            gameInspector.model.show()
            koinInspector.model.show()
            ecsInspector.show()
        }

        bringAllStagesToFrontWhenAnyOfThemIsFocused()
    }

    private fun bringAllStagesToFrontWhenAnyOfThemIsFocused() {
        coroutineScope.launch {
            combine(
                gameInspector.viewModel.focused,
                koinInspector.viewModel.focused,
                ecsInspector.focused,
            ) { gameInspectorFocused, koinInspectorFocused, ecsInspectorFocused ->
                gameInspectorFocused || koinInspectorFocused || ecsInspectorFocused
            }.stateIn(coroutineScope)
                .filter { it }
                .collect {
                    withContext(Dispatchers.JavaFx) {
                        gameInspector.viewModel.toFront()
                        koinInspector.toFront()
                        ecsInspector.toFront()
                    }
                }
        }
    }

    companion object {
        fun launch() = launch(InspectorApplication::class.java)
    }
}
