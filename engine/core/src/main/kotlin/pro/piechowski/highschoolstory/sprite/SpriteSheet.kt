package pro.piechowski.highschoolstory.sprite

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion

open class SpriteSheet<T : Sprite>(
    val sprites: List<List<T>>,
) {
    companion object {
        inline fun <reified T : Sprite> split(
            texture: Texture,
            tileWidth: Int,
            tileHeight: Int,
            spriteFactory: (TextureRegion) -> T,
        ) = TextureRegion
            .split(texture, tileWidth, tileHeight)
            .map { it.map { spriteFactory(it) } }
    }
}
