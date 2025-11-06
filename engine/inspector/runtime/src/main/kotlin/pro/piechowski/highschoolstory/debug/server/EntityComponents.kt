package pro.piechowski.highschoolstory.debug.server

import kotlinx.serialization.Serializable

@Serializable
data class EntityComponents(
    val objectGraph: ObjectGraph,
    val entityComponents: Map<Entity, List<Component>>,
)

@Serializable
data class Entity(
    val id: Int,
)

@Serializable
data class Component(
    val type: ComponentType,
    val value: Object,
)

@Serializable
data class ComponentType(
    val name: String,
)
