package pro.piechowski.highschoolstory.character.player

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.EntityComponentContext
import com.github.quillraven.fleks.EntityCreateContext
import ktx.assets.async.AssetStorage
import pro.piechowski.highschoolstory.asset.AssetIdentifiers
import pro.piechowski.highschoolstory.character.Character
import pro.piechowski.highschoolstory.ecs.Archetype
import pro.piechowski.highschoolstory.gdx.PhysicsWorld
import pro.piechowski.highschoolstory.physics.body.PhysicsBody
import pro.piechowski.highschoolstory.physics.movement.Speed
import pro.piechowski.highschoolstory.physics.movement.input.MovementInput
import pro.piechowski.highschoolstory.rendering.sprite.CurrentSprite
import pro.piechowski.highschoolstory.spatial.Spatial

class PlayerCharacter private constructor(
    entity: Entity,
) : Spatial(entity) {
    companion object {
        context(ecc: EntityComponentContext)
        operator fun invoke(entity: Entity) =
            PlayerCharacter(entity).apply {
                with(ecc) {
                    requireNotNull(body)
                    requireNotNull(sprite)
                }
            }

        context(ecc: EntityCreateContext, assetStorage: AssetStorage, physicsWorld: PhysicsWorld)
        suspend fun archetype(
            firstName: String,
            lastName: String,
        ) = Archetype {
            this += Character.archetype(firstName, lastName, AssetIdentifiers.Textures.PlayerCharacter)
            this += Speed.run
            this += PlayerCharacterTag
            this += MovementInput.Controller()
        }
    }
}
