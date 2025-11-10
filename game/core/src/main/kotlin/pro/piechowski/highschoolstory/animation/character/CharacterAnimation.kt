package pro.piechowski.highschoolstory.animation.character

import ktx.collections.toGdxArray
import pro.piechowski.highschoolstory.sprite.character.CharacterSprite
import pro.piechowski.highschoolstory.sprite.character.CharacterSpriteSheet
import pro.piechowski.kge.character.animation.CharacterAnimationBase
import pro.piechowski.kge.direction.Direction4
import pro.piechowski.kge.physics.s

sealed class CharacterAnimation(
    keyFrames: List<CharacterSprite>,
    playMode: PlayMode,
) : CharacterAnimationBase<CharacterSprite>(
        duration,
        keyFrames.toGdxArray(),
        playMode,
    ) {
    companion object {
        val duration = (1f / 5f).s
    }

    enum class Kind {
        IDLE,
        WALK,
    }

    sealed class Idle(
        keyFrames: List<CharacterSprite>,
    ) : CharacterAnimation(keyFrames, PlayMode.LOOP) {
        companion object {
            val kind: Kind = Kind.IDLE
        }

        class Up(
            spriteSheet: CharacterSpriteSheet,
        ) : Idle(spriteSheet.getAnimationFrames(kind, Direction4.Up))

        class Down(
            spriteSheet: CharacterSpriteSheet,
        ) : Idle(
                spriteSheet.getAnimationFrames(kind, Direction4.Down),
            )

        class Left(
            spriteSheet: CharacterSpriteSheet,
        ) : Idle(
                spriteSheet.getAnimationFrames(kind, Direction4.Left),
            )

        class Right(
            spriteSheet: CharacterSpriteSheet,
        ) : Idle(
                spriteSheet.getAnimationFrames(kind, Direction4.Right),
            )
    }

    sealed class Walk(
        keyFrames: List<CharacterSprite>,
    ) : CharacterAnimation(keyFrames, PlayMode.LOOP) {
        companion object {
            val kind: Kind = Kind.WALK
        }

        class Up(
            spriteSheet: CharacterSpriteSheet,
        ) : Walk(
                spriteSheet.getAnimationFrames(Idle.Companion.kind, Direction4.Right),
            )

        class Down(
            spriteSheet: CharacterSpriteSheet,
        ) : Walk(
                spriteSheet.getAnimationFrames(Idle.Companion.kind, Direction4.Right),
            )

        class Left(
            spriteSheet: CharacterSpriteSheet,
        ) : Walk(
                spriteSheet.getAnimationFrames(Idle.Companion.kind, Direction4.Right),
            )

        class Right(
            spriteSheet: CharacterSpriteSheet,
        ) : Walk(
                spriteSheet.getAnimationFrames(Idle.Companion.kind, Direction4.Right),
            )
    }
}
