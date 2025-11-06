package pro.piechowski.highschoolstory.inspector.`object`

import com.github.quillraven.fleks.World
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.core.Koin
import pro.piechowski.highschoolstory.inspector.container.Object
import pro.piechowski.highschoolstory.inspector.tickerFlow
import pro.piechowski.highschoolstory.inspector.tryGetPropertyValue
import kotlin.reflect.KProperty
import kotlin.time.Duration.Companion.milliseconds

@ExperimentalContextParameters
@ExperimentalCoroutinesApi
class ObjectInspectorViewModel(
    initialObject: Object<Any>?,
    koin: Flow<Koin?>,
    world: Flow<World?>,
) {
    companion object {
        val propertiesPollingInterval = 200.milliseconds
    }

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val logger = KotlinLogging.logger { }

    private val objects =
        MutableStateFlow(ArrayDeque(initialObject?.let { listOf(it) } ?: emptyList()))

    private val currentIndex =
        MutableStateFlow<Int?>(null)

    val currentObject =
        objects
            .combine(currentIndex) { objects, index -> index?.let { objects[it] } }
            .stateIn(coroutineScope, SharingStarted.Eagerly, initialObject)

    val properties =
        combine(koin, world, currentObject) { koin, world, currentObject ->
            koin?.let {
                world?.let {
                    currentObject?.let { currentObject ->
                        Triple(koin, world, currentObject)
                    }
                }
            }
        }.filterNotNull()
            .flatMapLatest { (koin, world, currentObject) ->
                tickerFlow(propertiesPollingInterval).map {
                    with(koin) {
                        with(world) {
                            currentObject.properties
                        }
                    }
                }
            }

    fun navigateBack() {
        currentIndex.update { currentIndex ->
            currentIndex?.let { currentIndex ->
                if (currentIndex > 0) {
                    currentIndex - 1
                } else {
                    currentIndex
                }
            }
        }
    }

    fun navigateForward() {
        currentIndex.update { currentIndex ->
            currentIndex?.let { currentIndex ->
                if (currentIndex < objects.value.size - 1) {
                    currentIndex + 1
                } else {
                    currentIndex
                }
            }
        }
    }

    fun pushObject(item: Object<Any>) {
        objects.update { objects ->
            currentIndex.value?.let { currentIndex ->
                ArrayDeque(objects.take(currentIndex + 1) + item)
            } ?: ArrayDeque(listOf(item))
        }

        currentIndex.update { index ->
            index?.let { it + 1 } ?: 0
        }
    }

    context(koin: Koin, world: World)
    private val Object<*>.properties
        get() =
            type
                .members
                .filterIsInstance<KProperty<*>>()
                .map {
                    ObjectProperty(
                        it.name,
                        try {
                            with(logger) {
                                this@properties.instance?.tryGetPropertyValue<Any>(it)
                            }
                        } catch (_: Throwable) {
                            "error"
                        },
                    )
                }.let { objectProperties ->
                    objectProperties + (instance?.mapElementsToObjectProperties() ?: emptyList())
                }

    private fun Any.mapElementsToObjectProperties() =
        when (this) {
            is Iterable<*> ->
                mapIndexed { idx, value -> ObjectProperty(idx.toString(), value) }

            is Array<*> ->
                mapIndexed { idx, value -> ObjectProperty(idx.toString(), value) }

            else -> listOf()
        }

    data class ObjectProperty(
        val name: String,
        val value: Any?,
    )
}
