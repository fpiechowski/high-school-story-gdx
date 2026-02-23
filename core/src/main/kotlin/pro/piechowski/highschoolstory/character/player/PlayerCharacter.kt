package pro.piechowski.highschoolstory.character.player

import pro.piechowski.highschoolstory.animation.character.player.PlayerCharacterMovementAnimationSet
import pro.piechowski.highschoolstory.physics.body.character.CharacterBody
import pro.piechowski.kge.GameObject
import pro.piechowski.kge.character.player.PlayerCharacterBase
import pro.piechowski.kge.gameobject.Prototype
import pro.piechowski.kge.gameobject.from
import pro.piechowski.kge.world

@GameObject
interface PlayerCharacter : PlayerCharacterBase {

    companion object : PlayerCharacterCompanion() {
        suspend operator fun invoke(
            firstName: String,
            lastName: String,
        ): PlayerCharacter = invoke(
            world.entity {
                it.from(prototype(firstName, lastName))
            })

        fun prototype(
            firstName: String,
            lastName: String,
        ) = Prototype {
            it.from(
                PlayerCharacterBase.prototype(
                    firstName,
                    lastName,
                    CharacterBody(),
                    PlayerCharacterMovementAnimationSet.Idle(),
                    PlayerCharacterMovementAnimationSet.Walk(),
                )
            )
        }
    }
}
