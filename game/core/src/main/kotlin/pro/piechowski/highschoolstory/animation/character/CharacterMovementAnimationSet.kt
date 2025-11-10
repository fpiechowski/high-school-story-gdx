package pro.piechowski.highschoolstory.animation.character

import pro.piechowski.highschoolstory.sprite.character.CharacterSpriteSheet
import pro.piechowski.highschoolstory.sprite.character.player.PlayerCharacterSpriteSheet
import pro.piechowski.kge.animation.Direction4AnimationSet
import pro.piechowski.kge.animation.movement.MovementAnimationSet
import pro.piechowski.kge.koin

object CharacterMovementAnimationSet {
    class Idle(
        spriteSheet: CharacterSpriteSheet,
    ) : MovementAnimationSet.Idle(
            Direction4AnimationSet(
                CharacterAnimation.Idle.Up(koin.get<PlayerCharacterSpriteSheet>()),
                CharacterAnimation.Idle.Down(koin.get<PlayerCharacterSpriteSheet>()),
                CharacterAnimation.Idle.Left(koin.get<PlayerCharacterSpriteSheet>()),
                CharacterAnimation.Idle.Right(koin.get<PlayerCharacterSpriteSheet>()),
            ),
        )

    class Walk :
        MovementAnimationSet.Walk(
            Direction4AnimationSet(
                CharacterAnimation.Walk.Up(koin.get<PlayerCharacterSpriteSheet>()),
                CharacterAnimation.Walk.Down(koin.get<PlayerCharacterSpriteSheet>()),
                CharacterAnimation.Walk.Left(koin.get<PlayerCharacterSpriteSheet>()),
                CharacterAnimation.Walk.Right(koin.get<PlayerCharacterSpriteSheet>()),
            ),
        )
}
