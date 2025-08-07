package pro.piechowski.highschoolstory.map

import com.badlogic.gdx.maps.tiled.TiledMap
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import ktx.assets.async.Identifier
import pro.piechowski.highschoolstory.asset.TiledMapIdentifierSerializer

@Serializable
class Map(
    @Serializable(with = TiledMapIdentifierSerializer::class)
    val assetIdentifier: Identifier<TiledMap>,
    val scrolling: Scrolling? = null,
) {
    @Transient
    val tiledMap: CompletableDeferred<TiledMap> = CompletableDeferred()

    @Serializable
    sealed class Scrolling(
        val speed: Float,
    ) {
        class Horizontal(
            speed: Float,
        ) : Scrolling(speed)

        class Vertical(
            speed: Float,
        ) : Scrolling(speed)
    }
}
