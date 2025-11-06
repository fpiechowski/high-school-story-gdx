package pro.piechowski.highschoolstory.debug.server

import com.github.quillraven.fleks.World
import io.github.oshai.kotlinlogging.KLogger
import kotlinx.serialization.Serializable
import org.koin.core.Koin
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KProperty

@Serializable
data class ObjectGraph(
    val objects: List<Object>,
) {
    class Builder(
        val objects: MutableList<Object> = mutableListOf(),
    ) {
        fun build() = ObjectGraph(objects)
    }
}

@Serializable
data class Object(
    val type: Type,
    val properties: List<Property>,
) {
    @ExperimentalContextParameters
    companion object {
        context(koin: Koin, world: World, graph: ObjectGraph.Builder, logger: KLogger)
        fun from(any: Any) =
            Object(
                Type(any::class.qualifiedName ?: "Unknown"),
                any.properties,
            )
    }

    @Serializable
    data class Type(
        val name: String,
    )

    @Serializable
    data class Property(
        val name: String,
        val value: Value?,
    ) {
        @Serializable
        sealed class Value {
            data class Primitive(
                val value: String,
            ) : Value()

            @Serializable
            data class Collection(
                val items: List<Value?>,
            ) : Value()

            @Serializable
            data class ObjectReference(
                val id: String,
            ) : Value()

            @ExperimentalContextParameters
            companion object {
                context(koin: Koin, world: World, graph: ObjectGraph.Builder, logger: KLogger)
                fun from(any: Any?): Value? =
                    any?.let {
                        when (any) {
                            is Boolean, is Int, is Float, is Double, is String, is BigDecimal, is BigInteger ->
                                Primitive(
                                    any.toString(),
                                )

                            is Iterable<*> ->
                                any
                                    .map {
                                        from(it)
                                    }.let { Collection(it) }

                            else ->
                                ObjectReference(any.hashCode().toHexString())
                                    .also { graph.objects.add(Object.from(any)) }
                        }
                    }
            }
        }
    }
}

@ExperimentalContextParameters
context(koin: Koin, world: World, graph: ObjectGraph.Builder, logger: KLogger)
private val Any.properties
    get() =
        this::class
            .members
            .filterIsInstance<KProperty<*>>()
            .map { property ->
                Object.Property(
                    property.name,
                    try {
                        this@properties.tryGetPropertyValue<Any>(property)
                    } catch (_: Throwable) {
                        "error"
                    }.let {
                        Object.Property.Value.Companion
                            .from(it)
                    },
                )
            }
