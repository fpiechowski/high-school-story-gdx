package pro.piechowski.highschoolstory.inspector.container

import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

data class Object<T : Any>(
    val type: KClass<T>,
    val instance: T?,
)

interface ObjectContainer {
    val objects: Flow<List<Object<Any>>>
}
