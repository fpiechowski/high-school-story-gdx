package pro.piechowski.highschoolstory.character

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.EntityCreateContext
import pro.piechowski.highschoolstory.animation.character.CharacterMovementAnimationSet
import pro.piechowski.highschoolstory.physics.body.character.CharacterBody
import pro.piechowski.highschoolstory.sprite.character.CharacterSpriteSheet
import pro.piechowski.kge.gameobject.Archetype
import pro.piechowski.kge.gameobject.BindableEntityGameObject
import pro.piechowski.kge.gameobject.EntityGameObjectCompanion
import pro.piechowski.kge.character.CharacterBase
import pro.piechowski.kge.gameobject.from
import pro.piechowski.kge.world

interface Character : CharacterBase {
    companion object : EntityGameObjectCompanion<Character>({ BindableCharacter(it) }) {

        context(ecc: EntityCreateContext)
        suspend operator fun invoke(
            firstName: String,
            lastName: String,
            spriteSheet: CharacterSpriteSheet,
        ) = invoke(
            world.entity {
                it.from(
                    CharacterBase.prototype(
                        firstName,
                        lastName,
                        CharacterBody(),
                        CharacterMovementAnimationSet.Idle(),
                        CharacterMovementAnimationSet.Walk(),
                    )
                )
            })

        override val archetype = Archetype {
            from(CharacterBase.archetype)
        }
    }
}

class BindableCharacter(_entity: Entity) : Character, BindableEntityGameObject<Character>(_entity)
