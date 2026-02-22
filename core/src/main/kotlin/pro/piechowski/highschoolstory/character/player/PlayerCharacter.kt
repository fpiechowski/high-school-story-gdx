package pro.piechowski.highschoolstory.character.player

import com.github.quillraven.fleks.Entity
import pro.piechowski.highschoolstory.animation.character.player.PlayerCharacterMovementAnimationSet
import pro.piechowski.highschoolstory.physics.body.character.CharacterBody
import pro.piechowski.kge.gameobject.Archetype
import pro.piechowski.kge.gameobject.BindableEntityGameObject
import pro.piechowski.kge.gameobject.EntityGameObjectCompanion
import pro.piechowski.kge.character.player.PlayerCharacterBase
import pro.piechowski.kge.gameobject.Prototype
import pro.piechowski.kge.gameobject.from
import pro.piechowski.kge.world

interface PlayerCharacter : PlayerCharacterBase {

    companion object : EntityGameObjectCompanion<PlayerCharacter>({ BindablePlayerCharacter(it) }) {
        override val archetype = Archetype {
            from(PlayerCharacterBase.archetype)
        }

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

class BindablePlayerCharacter(entity: Entity) : PlayerCharacter, BindableEntityGameObject<PlayerCharacter>(entity)
