package pro.piechowski.highschoolstory.map

import com.badlogic.gdx.maps.tiled.TiledMap
import ktx.assets.async.AssetStorage
import pro.piechowski.highschoolstory.asset.Assets
import pro.piechowski.kge.DependencyInjection.Companion.get
import pro.piechowski.kge.map.RepeatingTiledMapAdapter

class Road(tiledMap: TiledMap) : RepeatingTiledMapAdapter("Road", tiledMap, repeatX = true) {
    companion object {
        suspend operator fun invoke() = Road(Assets.Maps.Road.load().value)
    }
}
