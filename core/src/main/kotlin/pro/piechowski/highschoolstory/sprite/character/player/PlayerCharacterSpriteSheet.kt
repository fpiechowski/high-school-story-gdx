package pro.piechowski.highschoolstory.sprite.character.player

import com.badlogic.gdx.graphics.Texture
import ktx.assets.async.AssetStorage
import pro.piechowski.highschoolstory.asset.Assets
import pro.piechowski.highschoolstory.sprite.character.CharacterSpriteSheet
import pro.piechowski.kge.koin

class PlayerCharacterSpriteSheet(texture: Texture) : CharacterSpriteSheet(texture) {
    companion object {
        suspend operator fun invoke() = PlayerCharacterSpriteSheet(Assets.Textures.PlayerCharacter.load().value)
    }
}
