package pro.piechowski.highschoolstory.inspector.ecs

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentService
import com.github.quillraven.fleks.ComponentType
import com.github.quillraven.fleks.ComponentsHolder
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.collection.Bag
import io.github.classgraph.ClassGraph
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import org.koin.core.Koin
import pro.piechowski.highschoolstory.ecs.WorldManager
import pro.piechowski.highschoolstory.inspector.propertyValue

@ExperimentalCoroutinesApi
class EcsInspectorModel(
    koin: StateFlow<Koin?>,
) {
    private val logger = KotlinLogging.logger { }

    val world =
        koin.flatMapLatest { koin ->
            koin?.let { koin ->
                koin
                    .get<WorldManager>()
                    .worldInitialized
                    .map { worldInitialized ->
                        if (worldInitialized) koin.get<World>() else null
                    }
            } ?: emptyFlow()
        }

    val World.componentService
        get() = propertyValue<World, ComponentService>("componentService")

    val ComponentService.holdersBag: Bag<ComponentsHolder<Component<Any>>?>
        get() = propertyValue("holdersBag")

    val <T> Bag<T>.values
        get() = propertyValue<Bag<T>, Array<T>>("values")

    val <T : Component<*>> ComponentsHolder<T>.type
        get() = propertyValue<ComponentsHolder<T>, ComponentType<*>>("type")

    val componentTypes: List<Class<ComponentType<Any>>> =
        ClassGraph()
            .enableAllInfo()
            .enableClassInfo()
            .enableExternalClasses()
            .ignoreClassVisibility()
            .enableSystemJarsAndModules()
            .scan()
            .use { scan ->
                scan.allClasses
                    .filter { it.superclasses.any { superclass -> superclass.name == ComponentType::class.qualifiedName } }
                    .filter { it.packageName != "com.github.quillraven.fleks" }
                    .mapNotNull { classInfo ->
                        classInfo.loadClass() as Class<ComponentType<Any>>
                    }
            }.also {
                logger.info { "Found ${it.size} component types" }
            }

    fun entityComponents(): Flow<List<Pair<Entity, Component<Any>>>> =
        world.map { world ->
            world?.let { world ->
                world.componentService
                    .holdersBag
                    .values
                    .filterNotNull()
                    .flatMap { holder ->
                        world.asEntityBag().map { entity ->
                            entity to holder.getOrNull(entity)
                        }
                    }.filter { it.second != null }
                    .map { it.first to it.second!! }
            } ?: emptyList()
        }

    fun componentsByEntity(): Flow<Map<Entity, List<Component<Any>>>> =
        entityComponents()
            .map { entityComponents ->
                entityComponents.groupBy(keySelector = { it.first }) {
                    it.second
                }
            }
}
