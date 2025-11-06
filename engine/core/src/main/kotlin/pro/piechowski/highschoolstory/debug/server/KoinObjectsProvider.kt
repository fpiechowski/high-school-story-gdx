package pro.piechowski.highschoolstory.debug.server

import com.github.quillraven.fleks.World
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.Koin
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.component.KoinComponent
import org.koin.core.instance.SingleInstanceFactory

@ExperimentalContextParameters
@ExperimentalCoroutinesApi
@KoinInternalApi
class KoinObjectsProvider : KoinComponent {
    private val logger = KotlinLogging.logger {}

    val objects: KoinObjects
        get() =
            ObjectGraph
                .Builder()
                .let { graphBuilder ->
                    getKoin().let { koin ->
                        val world = koin.get<World>()

                        val definitions =
                            with(koin) {
                                with(world) {
                                    with(graphBuilder) {
                                        koinDefinitions
                                    }
                                }
                            }

                        KoinObjects(
                            graphBuilder.build(),
                            definitions,
                        )
                    }
                }

    context(koin: Koin, world: World, graphBuilder: ObjectGraph.Builder)
    private val koinDefinitions
        get() =
            koin.instanceRegistry
                .instances
                .values
                .filterIsInstance<SingleInstanceFactory<*>>()
                .map { factory ->
                    factory to factory.propertyValue<SingleInstanceFactory<*>, Any?>("value")
                }.sortedBy { it.first.beanDefinition.primaryType.simpleName }
                .map {
                    with(this@KoinObjectsProvider.logger) {
                        KoinInstanceDefinition.from(
                            it.first.beanDefinition.primaryType,
                            it.second,
                        )
                    }
                }
}
