package pro.piechowski.highschoolstory.map

import com.badlogic.gdx.maps.tiled.TiledMap
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import ktx.assets.async.AssetStorage
import ktx.assets.async.Identifier
import org.koin.core.Koin
import pro.piechowski.highschoolstory.asset.TiledMapIdentifierSerializer

@Serializable
open class Map(
    @Serializable(with = TiledMapIdentifierSerializer::class)
    open val assetIdentifier: Identifier<TiledMap>,
) {
    @Transient
    context(koin: Koin)
    val tiledMap: Deferred<TiledMap> get() = koin.get<AssetStorage>().loadAsync(assetIdentifier)
}

class EndlessMap(
    @Serializable(with = TiledMapIdentifierSerializer::class)
    override val assetIdentifier: Identifier<TiledMap>,
    val orientation: Orientation = Orientation.HORIZONTAL,
) : Map(assetIdentifier) {
    enum class Orientation { HORIZONTAL, VERTICAL }
}
