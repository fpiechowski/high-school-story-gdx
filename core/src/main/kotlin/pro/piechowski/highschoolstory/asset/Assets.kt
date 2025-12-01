package pro.piechowski.highschoolstory.asset

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.maps.tiled.TiledMap
import ktx.assets.async.Identifier
import pro.piechowski.kge.asset.Asset
import pro.piechowski.kge.asset.invoke

object Assets {
    object Textures {
        fun characterTexture(name: String) = Asset(Identifier("textures/character/$name.png", Texture::class.java))

        fun texture(name: String) = Asset(Identifier("textures/$name.png", Texture::class.java))

        val PlayerCharacter = characterTexture("player_character")
        val Character = characterTexture("character")
        val Exteriors = texture("exteriors")
    }

    object Maps {
        fun mapIdentifier(name: String) = Identifier("maps/$name.tmx", TiledMap::class.java)

        val Town = Asset(mapIdentifier("town"))
        val Road = Asset(mapIdentifier("road"))
    }
}
