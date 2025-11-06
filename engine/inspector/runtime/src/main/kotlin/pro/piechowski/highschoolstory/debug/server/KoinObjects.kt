package pro.piechowski.highschoolstory.debug.server

import kotlinx.serialization.Serializable

@Serializable
data class KoinObjects(
    val objectGraph: ObjectGraph,
    val definitions: List<KoinInstanceDefinition>,
) {
    companion object
}

@Serializable
data class KoinInstanceDefinition(
    val type: Object.Type,
    val value: Object?,
) {
    companion object
}
