package pro.piechowski.highschoolstory.physics.body

import com.github.quillraven.fleks.Fixed
import com.github.quillraven.fleks.IntervalSystem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.gdx.PhysicsWorld

const val PHYSICS_STEP = 1 / 60f

class PhysicsWorldStepSystem :
    IntervalSystem(Fixed(PHYSICS_STEP)),
    KoinComponent {
    private val physicsWorld: PhysicsWorld by inject()

    override fun onTick() {
        physicsWorld.step(PHYSICS_STEP, 6, 2)
    }
}
