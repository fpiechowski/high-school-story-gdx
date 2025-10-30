package pro.piechowski.highschoolstory.character.player

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.EntityComponentContext
import com.github.quillraven.fleks.EntityCreateContext
import com.github.quillraven.fleks.World
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ktx.assets.async.AssetStorage
import org.koin.core.Koin
import pro.piechowski.highschoolstory.asset.AssetIdentifiers
import pro.piechowski.highschoolstory.character.Character
import pro.piechowski.highschoolstory.ecs.Archetype
import pro.piechowski.highschoolstory.ecs.plusAssign
import pro.piechowski.highschoolstory.gdx.PhysicsWorld
import pro.piechowski.highschoolstory.physics.movement.Speed
import pro.piechowski.highschoolstory.physics.movement.input.MovementInput
import pro.piechowski.highschoolstory.vehicle.bus.Bus

@Serializable(with = PlayerCharacter.Serializer::class)
class PlayerCharacter(
    entity: Entity,
) : Character(entity) {
    companion object {
        context(koin: Koin)
        suspend operator fun invoke(
            firstName: String,
            lastName: String,
        ) = PlayerCharacter(
            koin.get<World>().entity {
                it += archetype(firstName, lastName)
            },
        )

        context(koin: Koin)
        suspend fun archetype(
            firstName: String,
            lastName: String,
        ) = Archetype {
            this += archetype(firstName, lastName, AssetIdentifiers.Textures.PlayerCharacter)
            this += Speed.run
            this += PlayerCharacterTag
            this += MovementInput.Controller()
        }
    }

    object Serializer : KSerializer<PlayerCharacter> {
        override val descriptor = Character.serializer().descriptor

        override fun serialize(
            encoder: Encoder,
            value: PlayerCharacter,
        ) {
            Character.serializer().serialize(encoder, value)
        }

        override fun deserialize(decoder: Decoder): PlayerCharacter {
            val character = Character.serializer().deserialize(decoder)
            return PlayerCharacter(character.entity)
        }
    }
}
