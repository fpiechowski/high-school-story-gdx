package pro.piechowski.highschoolstory.ecs

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.EntityCreateContext
import com.github.quillraven.fleks.EntityTag
import com.github.quillraven.fleks.EntityTags
import com.github.quillraven.fleks.UniqueId
import kotlin.reflect.KClass

class Archetype(
    val components: Map<KClass<out Component<*>>, Component<*>>,
    val entityTags: Map<KClass<out UniqueId<*>>, EntityTags>,
) {
    class Builder(
        val components: MutableMap<KClass<out Component<*>>, Component<*>> = mutableMapOf(),
        val entityTags: MutableMap<KClass<out UniqueId<*>>, EntityTags> = mutableMapOf(),
    ) {
        fun build() = Archetype(components, entityTags)

        inline operator fun <reified T : Component<*>> plusAssign(component: T) {
            components += component::class to component
        }

        operator fun plusAssign(entityTag: EntityTag) {
            entityTags += entityTag::class to entityTag
        }

        operator fun plusAssign(archetype: Archetype) {
            components += archetype.components
            entityTags += archetype.entityTags
        }
    }

    operator fun plus(archetype: Archetype) = Archetype(components + archetype.components, entityTags + archetype.entityTags)

    operator fun get(componentClass: KClass<out Component<*>>) = components[componentClass]

    operator fun get(entityTagClass: KClass<out UniqueId<*>>) = entityTags[entityTagClass]

    companion object {
        operator fun invoke(builderBlock: Builder.() -> Unit = {}) = Builder().also { it.builderBlock() }.build()
    }
}

context(ecc: EntityCreateContext)
operator fun Entity.plusAssign(archetype: Archetype) =
    with(ecc) {
        this@plusAssign.plusAssign(archetype.entityTags.values.toList())
        this@plusAssign.plusAssign(archetype.components.values.toList())
    }
