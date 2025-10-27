package pro.piechowski.highschoolstory.inspector.ecs

import com.github.quillraven.fleks.World
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.annotation.KoinInternalApi
import pro.piechowski.highschoolstory.inspector.globals.GlobalInstances
import pro.piechowski.highschoolstory.inspector.tickerFlow
import kotlin.time.Duration.Companion.seconds

class GlobalFleksWorldInstanceProvider(
    private val globalInstances: GlobalInstances,
) {
    private val logger = KotlinLogging.logger { }

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    @ExperimentalCoroutinesApi
    @KoinInternalApi
    val world =
        tickerFlow(2.seconds)
            .flatMapLatest {
                globalInstances.instances
                    .map {
                        it
                            .map { it.value }
                            .filterIsInstance<World>()
                            .firstOrNull()
                    }
            }.stateIn(coroutineScope, SharingStarted.Eagerly, initialValue = null)
            .also { logger.info { "World = ${it.value}" } }
}
