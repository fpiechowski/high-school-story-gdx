package pro.piechowski.highschoolstory

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.UniqueId
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import kotlinx.serialization.modules.polymorphic
import pro.piechowski.highschoolstory.character.PlayerCharacter
import pro.piechowski.highschoolstory.physics.movement.facedirection.FaceDirection

fun Json() =
    Json {
        serializersModule =
            SerializersModule {
                contextual(Vector2Serializer)

                polymorphic(Component::class) {
                    subclass(FaceDirection::class, FaceDirection.serializer())
                }

                polymorphic(UniqueId::class) {
                    subclass(PlayerCharacter::class, PlayerCharacter.serializer())
                }
            }

        allowStructuredMapKeys = true
    }
