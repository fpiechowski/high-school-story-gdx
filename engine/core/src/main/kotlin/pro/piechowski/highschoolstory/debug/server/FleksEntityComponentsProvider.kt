package pro.piechowski.highschoolstory.debug.server

import com.github.quillraven.fleks.ComponentService
import com.github.quillraven.fleks.ComponentsHolder
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.collection.Bag
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.github.quillraven.fleks.Component as FleksComponent

@ExperimentalContextParameters
@ExperimentalCoroutinesApi
class FleksEntityComponentsProvider : KoinComponent {
    private val world by inject<World>()

    private val logger = KotlinLogging.logger { }

    private val World.componentService
        get() = propertyValue<World, ComponentService>("componentService")

    private val ComponentService.holdersBag: Bag<ComponentsHolder<FleksComponent<Any>>?>
        get() = propertyValue("holdersBag")

    private val <T> Bag<T>.values
        get() = propertyValue<Bag<T>, Array<T>>("values")

    val entityComponents
        get() =
            with(world) {
                ObjectGraph.Builder().let { graphBuilder ->
                    val entityComponents =
                        componentService
                            .holdersBag
                            .values
                            .filterNotNull()
                            .flatMap { holder ->
                                asEntityBag().map { entity ->
                                    entity to holder.getOrNull(entity)
                                }
                            }.filter { it.second != null }
                            .map { it.first to it.second!! }
                            .map {
                                with(getKoin()) {
                                    with(graphBuilder) {
                                        with(this@FleksEntityComponentsProvider.logger) {
                                            Entity(it.first.id) to
                                                Component(
                                                    ComponentType(it.second::class.fullTypeName),
                                                    Object.from(it.second),
                                                )
                                        }
                                    }
                                }
                            }.groupBy(keySelector = { it.first }) {
                                it.second
                            }

                    EntityComponents(
                        graphBuilder.build(),
                        entityComponents,
                    )
                }
            }
}
