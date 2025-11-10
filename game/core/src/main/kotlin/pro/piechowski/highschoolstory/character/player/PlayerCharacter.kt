package pro.piechowski.highschoolstory.character.player

import com.github.quillraven.fleks.Entity
import kotlinx.serialization.Serializable
import org.koin.core.Koin
import pro.piechowski.highschoolstory.animation.character.CharacterAnimation
import pro.piechowski.highschoolstory.animation.character.player.PlayerCharacterMovementAnimationSet
import pro.piechowski.highschoolstory.asset.AssetIdentifiers
import pro.piechowski.highschoolstory.physics.body.character.CharacterBody
import pro.piechowski.highschoolstory.sprite.character.player.PlayerCharacterSpriteSheet
import pro.piechowski.kge.animation.Direction4AnimationSet
import pro.piechowski.kge.animation.movement.MovementAnimationSet
import pro.piechowski.kge.character.player.PlayerCharacterBase
import pro.piechowski.kge.koin

@Serializable(with = PlayerCharacterBase.Serializer::class)
class PlayerCharacter(
    entity: Entity,
) : PlayerCharacterBase(entity) {
    companion object {
        suspend operator fun invoke(
            firstName: String,
            lastName: String,
        ) = PlayerCharacterBase(
            firstName,
            lastName,
            CharacterBody(),
            PlayerCharacterMovementAnimationSet.Idle(),
            PlayerCharacterMovementAnimationSet.Walk(),
        )
    }
}
