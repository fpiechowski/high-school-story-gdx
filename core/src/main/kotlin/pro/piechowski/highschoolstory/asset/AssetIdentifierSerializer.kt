package pro.piechowski.highschoolstory.asset

import com.badlogic.gdx.maps.tiled.TiledMap
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import ktx.assets.async.Identifier

sealed class AssetIdentifierSerializer<T>(
    private val clazz: Class<T>,
) : KSerializer<Identifier<T>> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Identifier", PrimitiveKind.STRING)

    override fun serialize(
        encoder: Encoder,
        value: Identifier<T>,
    ) {
        encoder.encodeString(value.path)
    }

    override fun deserialize(decoder: Decoder): Identifier<T> {
        val path = decoder.decodeString()
        return Identifier(path, clazz)
    }
}

object TiledMapIdentifierSerializer : AssetIdentifierSerializer<TiledMap>(TiledMap::class.java)
