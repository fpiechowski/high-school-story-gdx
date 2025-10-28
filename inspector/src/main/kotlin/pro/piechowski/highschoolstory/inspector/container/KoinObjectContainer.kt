package pro.piechowski.highschoolstory.inspector.container

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.Koin
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.GlobalContext
import org.koin.core.instance.SingleInstanceFactory
import pro.piechowski.highschoolstory.inspector.tickerFlow
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.time.Duration.Companion.seconds

@ExperimentalCoroutinesApi
@KoinInternalApi
class KoinObjectContainer : ObjectContainer {
    private val logger = KotlinLogging.logger {}

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val koin: Flow<Koin?> =
        tickerFlow(2.seconds)
            .map {
                GlobalContext.getOrNull()
            }.stateIn(coroutineScope, SharingStarted.Eagerly, null)
            .also { logger.info { "Koin = ${it.value}" } }

    override val objects =
        koin.flatMapLatest { koin ->
            tickerFlow(2.seconds)
                .map {
                    koin
                        ?.instanceRegistry
                        ?.instances
                        ?.values
                        ?.filterIsInstance<SingleInstanceFactory<*>>()
                        ?.map { factory ->
                            val valueProp = factory::class.memberProperties.find { it.name == "value" }
                            valueProp?.isAccessible = true
                            factory to (valueProp as? KProperty1<Any, *>)?.get(factory)
                        }?.sortedBy { it.first.beanDefinition.primaryType.simpleName }
                        ?.map { Object(it.first.beanDefinition.primaryType as KClass<Any>, it.second) }
                        ?: emptyList()
                }
        }
}
