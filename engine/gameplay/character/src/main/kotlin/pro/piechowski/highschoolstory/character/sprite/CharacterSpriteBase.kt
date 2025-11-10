package pro.piechowski.highschoolstory.character.sprite

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import pro.piechowski.highschoolstory.physics.px

open class CharacterSpriteBase(
    textureRegion: TextureRegion,
    originX: Float,
    originY: Float,
    width: Float,
    height: Float,
) : Sprite(textureRegion) {
    init {
        setOrigin(originX.px.toMeter().value, originY.px.toMeter().value)
        setOriginBasedPosition(0f, 0f)
        setSize(width.px.toMeter().value, height.px.toMeter().value)
    }
}
