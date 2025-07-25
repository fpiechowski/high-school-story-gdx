package pro.piechowski.highschoolstory.character.rendering

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2

class CharacterSprite(
    textureRegion: TextureRegion,
) : Sprite(textureRegion) {
    companion object {
        const val ORIGIN_X = 24f
        const val ORIGIN_Y = 24f
        const val WIDTH = 48f
        const val HEIGHT = 96f
        val size = Vector2(WIDTH, HEIGHT)
    }

    init {
        setOrigin(ORIGIN_X, ORIGIN_Y)
        setOriginBasedPosition(0f, 0f)
    }
}
