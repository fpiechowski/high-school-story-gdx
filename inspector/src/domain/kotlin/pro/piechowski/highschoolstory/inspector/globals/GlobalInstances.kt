package pro.piechowski.highschoolstory.inspector.globals

import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

data class GlobalInstance<T : Any>(
    val type: KClass<T>,
    val value: T?,
)

interface GlobalInstances {
    val instances: Flow<List<GlobalInstance<Any>>>
}
