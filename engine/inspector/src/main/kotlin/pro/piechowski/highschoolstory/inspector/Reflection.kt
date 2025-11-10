package pro.piechowski.highschoolstory.inspector

import com.github.quillraven.fleks.World
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.Koin
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.typeOf

private val logger = KotlinLogging.logger { }

fun <T : Any, R> T.propertyValue(name: String): R =
    (
        this::class.memberProperties.find {
            it.name == name
        } as KProperty1<T, R>
    ).also { it.isAccessible = true }.get(this)

fun KClass<*>.overridesToString() = java.overridesToString()

fun Class<*>.overridesToString(): Boolean =
    try {
        val method = getMethod("toString")
        method.declaringClass != Any::class.java
    } catch (_: NoSuchMethodException) {
        false
    }

@ExperimentalContextParameters
context(koin: Koin, world: World, logger: KLogger)
inline fun <reified T> T.tryGetPropertyValue(property: KProperty<*>): Any? =
    try {
        if (this is Array<*> && property.name == "size") {
            size
        } else {
            property.isAccessible = true

            val arguments =
                property.parameters.map {
                    val kclass = it.type.classifier as? KClass<*>
                    kclass?.let {
                        when {
                            kclass.isSuperclassOf(this::class) -> this@tryGetPropertyValue
                            kclass.isSuperclassOf(World::class) -> world
                            kclass.isSuperclassOf(Koin::class) -> koin
                            else -> null
                        }
                    }
                }

            when {
                property.returnType.isSubtypeOf(typeOf<StateFlow<*>>()) -> {
                    (property.call(this) as StateFlow<*>).value
                }

                else -> property.call(*arguments.toTypedArray())
            }
        }
    } catch (e: Throwable) {
        logger.error(e) { "Error while getting value for property $property of object $this" }
        throw e
    }
