package pro.piechowski.highschoolstory.character

import com.badlogic.gdx.graphics.Texture
import com.github.quillraven.fleks.EntityTag
import kotlinx.serialization.Serializable
import ktx.assets.async.AssetStorage
import ktx.assets.async.Identifier
import pro.piechowski.highschoolstory.animation.CurrentAnimation
import pro.piechowski.highschoolstory.animation.Direction4AnimationSet
import pro.piechowski.highschoolstory.character.animation.CharacterAnimation
import pro.piechowski.highschoolstory.character.body.CharacterBody
import pro.piechowski.highschoolstory.dialogue.Dialogue
import pro.piechowski.highschoolstory.direction.Direction8
import pro.piechowski.highschoolstory.ecs.Archetype
import pro.piechowski.highschoolstory.gdx.PhysicsWorld
import pro.piechowski.highschoolstory.interaction.input.InteractionInput
import pro.piechowski.highschoolstory.interaction.interactor.Interactor
import pro.piechowski.highschoolstory.physics.body.PhysicsBody
import pro.piechowski.highschoolstory.physics.movement.Speed
import pro.piechowski.highschoolstory.physics.movement.animation.MovementAnimationSet
import pro.piechowski.highschoolstory.physics.movement.facedirection.FaceDirection
import pro.piechowski.highschoolstory.physics.movement.input.MovementInput
import pro.piechowski.highschoolstory.rendering.sprite.CurrentSprite

@Serializable
data object CharacterTag : EntityTag()
