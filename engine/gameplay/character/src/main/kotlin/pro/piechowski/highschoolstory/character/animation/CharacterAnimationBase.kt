package pro.piechowski.highschoolstory.character.animation

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import ktx.collections.GdxArray
import pro.piechowski.highschoolstory.character.sprite.CharacterSpriteBase
import pro.piechowski.highschoolstory.physics.Second

open class CharacterAnimationBase<T : CharacterSpriteBase>(
    duration: Second,
    keyFrames: GdxArray<T>,
    playMode: PlayMode,
) : Animation<Sprite>(duration.value, keyFrames, playMode)
