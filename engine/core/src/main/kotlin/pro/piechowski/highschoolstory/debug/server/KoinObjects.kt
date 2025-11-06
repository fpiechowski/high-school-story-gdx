package pro.piechowski.highschoolstory.debug.server

import com.github.quillraven.fleks.World
import io.github.oshai.kotlinlogging.KLogger
import org.koin.core.Koin
import kotlin.reflect.KClass

@ExperimentalContextParameters
context(koin: Koin, world: World, graph: ObjectGraph.Builder, logger: KLogger)
fun KoinInstanceDefinition.Companion.from(
    type: KClass<*>,
    value: Any?,
) = KoinInstanceDefinition(
    Object.Type(type.qualifiedName ?: "Unknown"),
    value?.let { Object.from(it) },
)
