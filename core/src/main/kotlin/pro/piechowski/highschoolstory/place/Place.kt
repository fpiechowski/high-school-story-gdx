package pro.piechowski.highschoolstory.place

import com.badlogic.gdx.maps.tiled.TiledMap
import kotlinx.coroutines.Deferred
import kotlinx.serialization.Serializable
import ktx.assets.async.Identifier
import pro.piechowski.highschoolstory.map.Map

@Serializable
open class Place(
    val name: String,
    val map: Map,
)
