package pro.piechowski.highschoolstory.character.player

import kotlinx.serialization.Serializable
import org.koin.core.Koin
import pro.piechowski.highschoolstory.asset.AssetIdentifiers

@Serializable(with = PlayerCharacterBase.Serializer::class)
object PlayerCharacter {
    context(koin: Koin)
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
    ) = PlayerCharacterBase(
        firstName,
        lastName,
        AssetIdentifiers.Textures.PlayerCharacter,
    )
}
