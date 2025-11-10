package pro.piechowski.highschoolstory.sprite.character.player

import ktx.assets.async.AssetStorage
import pro.piechowski.highschoolstory.asset.AssetIdentifiers
import pro.piechowski.highschoolstory.sprite.character.CharacterSpriteSheet
import pro.piechowski.kge.koin

class PlayerCharacterSpriteSheet : CharacterSpriteSheet(koin.get<AssetStorage>().loadSync(AssetIdentifiers.Textures.PlayerCharacter))
