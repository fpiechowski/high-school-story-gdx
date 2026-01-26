package pro.piechowski.highschoolstory.character

import com.github.quillraven.fleks.Entity
import pro.piechowski.highschoolstory.animation.character.CharacterAnimation
import pro.piechowski.highschoolstory.animation.character.CharacterMovementAnimationSet
import pro.piechowski.highschoolstory.physics.body.character.CharacterBody
import pro.piechowski.highschoolstory.sprite.character.CharacterSpriteSheet
import pro.piechowski.kge.animation.Direction4AnimationSet
import pro.piechowski.kge.movement.MovementAnimationSet
import pro.piechowski.kge.character.CharacterBase
import pro.piechowski.kge.ecs.plusAssign
import pro.piechowski.kge.world

class Character(
    entity: Entity,
) : CharacterBase(entity) {
    companion object {
        suspend operator fun invoke(
            firstName: String,
            lastName: String,
            spriteSheet: CharacterSpriteSheet,
        ) = world.entity {
            it +=
                prototype(
                    it,
                    firstName,
                    lastName,
                    CharacterBody(),
                    CharacterMovementAnimationSet.Idle(),
                    CharacterMovementAnimationSet.Walk(),
                )
        }
    }
}
