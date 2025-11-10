package pro.piechowski.highschoolstory

import com.github.quillraven.fleks.Component
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuilder
import kotlinx.serialization.modules.contextual
import kotlinx.serialization.modules.polymorphic
import org.koin.core.Koin
import pro.piechowski.highschoolstory.facedirection.FaceDirection4
import pro.piechowski.highschoolstory.facedirection.FaceDirection8

typealias SerializersModuleBuilderAction = SerializersModuleBuilder.() -> Unit

context(koin: Koin)
fun Json() =
    Json {
        serializersModule =
            SerializersModule {
                contextual(Vector2Serializer)

                polymorphic(Component::class) {
                    subclass(FaceDirection4::class, FaceDirection4.Serializer)
                    subclass(FaceDirection8::class, FaceDirection8.Serializer)
                }

                val serializersModuleConfigs = koin.getAll<SerializersModuleBuilderAction>()

                serializersModuleConfigs.forEach { it.invoke(this) }
            }

        allowStructuredMapKeys = true
    }
