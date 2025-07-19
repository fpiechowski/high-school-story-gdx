package pro.piechowski.highschoolstory.movement.facedirection

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import kotlinx.serialization.Serializable
import pro.piechowski.highschoolstory.direction.Direction

@Serializable
data class FaceDirection(
    var faceDirection: Direction,
) : Component<FaceDirection> {
    override fun type() = FaceDirection

    companion object : ComponentType<FaceDirection>()
}
