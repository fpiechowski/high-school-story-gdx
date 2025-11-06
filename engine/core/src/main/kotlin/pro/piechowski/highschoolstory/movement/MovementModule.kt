package pro.piechowski.highschoolstory.movement

import org.koin.dsl.module
import pro.piechowski.highschoolstory.animation.movement.MovementAnimationSystem
import pro.piechowski.highschoolstory.facedirection.FaceDirectionDebugSystem
import pro.piechowski.highschoolstory.facedirection.FaceDirectionSystem
import pro.piechowski.highschoolstory.input.movement.MovementControllerInputSystem
import pro.piechowski.highschoolstory.input.movement.MovementMultiplexInputSystem
import pro.piechowski.highschoolstory.movement.velocity.VelocitySystem

val MovementModule =
    module {
        single { MovementAnimationSystem() }
        single { MovementControllerInputSystem() }
        single { MovementMultiplexInputSystem() }
        single { VelocitySystem() }
        single { FaceDirectionSystem() }
        single { FaceDirectionDebugSystem() }
    }
