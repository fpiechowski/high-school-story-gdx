package pro.piechowski.highschoolstory.movement

import com.github.quillraven.fleks.Component
import com.github.quillraven.fleks.ComponentType
import pro.piechowski.highschoolstory.physics.MetersPerSeconds
import pro.piechowski.highschoolstory.physics.mps

data class Speed(
    var speed: MetersPerSeconds = 0f.mps,
) : Component<Speed> {
    override fun type() = Speed

    companion object : ComponentType<Speed>() {
        val walk = Speed(1.4f.mps)
        val jog = Speed(2.5f.mps)
        val run = Speed(5f.mps)
    }
}
