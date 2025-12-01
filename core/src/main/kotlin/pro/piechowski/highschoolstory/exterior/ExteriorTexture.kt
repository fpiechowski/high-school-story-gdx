package pro.piechowski.highschoolstory.exterior

import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.assets.async.AssetStorage
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.asset.Assets
import pro.piechowski.kge.DependencyInjection.Companion.inject

class ExteriorTexture {
    val texture = Assets.Textures.Exteriors

    suspend fun region(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
    ) = TextureRegion(texture.load().value, x, y, width, height)
}
