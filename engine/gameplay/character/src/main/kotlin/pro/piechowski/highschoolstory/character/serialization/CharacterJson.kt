package pro.piechowski.highschoolstory.character.serialization

import com.github.quillraven.fleks.UniqueId
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.polymorphic
import pro.piechowski.highschoolstory.character.player.PlayerCharacterTag

val CharacterSerializerModuleBuilder: SerializersModuleBuilder.() -> Unit = {
    polymorphic(UniqueId::class) {
        subclass(PlayerCharacterTag::class, PlayerCharacterTag.serializer())
    }
}
