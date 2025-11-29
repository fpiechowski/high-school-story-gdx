package pro.piechowski.highschoolstory.animation.character.player

import pro.piechowski.highschoolstory.animation.character.CharacterAnimation
import pro.piechowski.highschoolstory.sprite.character.player.PlayerCharacterSpriteSheet
import pro.piechowski.kge.animation.Direction4AnimationSet
import pro.piechowski.kge.movement.MovementAnimationSet
import pro.piechowski.kge.koin

object PlayerCharacterMovementAnimationSet {
    class Idle :
        MovementAnimationSet.Idle(
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
