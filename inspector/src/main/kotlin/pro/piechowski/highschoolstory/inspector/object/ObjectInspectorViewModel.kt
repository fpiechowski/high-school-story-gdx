package pro.piechowski.highschoolstory.inspector.`object`

import io.github.oshai.kotlinlogging.KotlinLogging
import javafx.collections.FXCollections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pro.piechowski.highschoolstory.inspector.InspectorViewModel
import pro.piechowski.highschoolstory.inspector.tickerFlow
import kotlin.collections.plus
import kotlin.math.PI
import kotlin.reflect.KProperty1
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.typeOf
import kotlin.time.Duration.Companion.seconds

@ExperimentalCoroutinesApi
class ObjectInspectorViewModel(
    initialObject: Any?,
) : InspectorViewModel() {
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
        currentObject
            .flatMapLatest { currentObject ->
                currentObject?.let { currentObject ->
                    tickerFlow(2.seconds).map {
                        currentObject::class
                            .memberProperties
                            .mapNotNull { it as? KProperty1<Any, Any?> }
                            .map { ObjectProperty(it.name, tryGetPropertyValue(it)) }
                            .let {
                                when {
                                    currentObject is Iterable<*> ->
                                        it +
                                            currentObject.mapIndexed { idx, value -> ObjectProperty(idx.toString(), value) }
                                    currentObject is Array<*> ->
                                        it +
                                            currentObject.mapIndexed { idx, value -> ObjectProperty(idx.toString(), value) }
                                    else -> it
                                }
                            }
                    }
                } ?: flowOf(FXCollections.emptyObservableList())
            }.map { FXCollections.observableList(it) }

    fun tryGetPropertyValue(property: KProperty1<Any, Any?>): Any? =
        currentObject.value?.let { currentObject ->
            try {
                if (currentObject is Array<*> && property.name == "size") {
                    currentObject.size
                } else {
                    property.isAccessible = true

                    when {
                        property.returnType.isSubtypeOf(typeOf<StateFlow<*>>()) -> {
                            (property.get(currentObject) as StateFlow<*>).value
                        }

                        else -> property.get(currentObject)
                    }
                }
            } catch (e: Throwable) {
                logger.error(e) { "Error while getting value for property $property" }
                "error"
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

    fun pushObject(item: Any) {
        objects.update { objects ->
            currentIndex.value?.let { currentIndex ->
                ArrayDeque(objects.take(currentIndex + 1) + item)
            } ?: ArrayDeque(listOf(item))
        }

        currentIndex.update { index ->
            index?.let { it + 1 } ?: 0
        }
    }

    data class ObjectProperty(
        val name: String,
        val value: Any?,
    )
}
