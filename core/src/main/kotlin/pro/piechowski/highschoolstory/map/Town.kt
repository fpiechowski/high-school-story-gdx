package pro.piechowski.highschoolstory.map

import com.badlogic.gdx.maps.tiled.TiledMap
import pro.piechowski.highschoolstory.asset.Assets
import pro.piechowski.kge.asset.Asset
import pro.piechowski.kge.asset.invoke
import pro.piechowski.kge.map.TiledMapAdapter

class Town(tiledMap: TiledMap) : TiledMapAdapter("Town", tiledMap) {
    companion object {
        suspend operator fun invoke() = Town(Assets.Maps.Town.load().value)
    }
}
