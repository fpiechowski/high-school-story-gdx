package pro.piechowski.highschoolstory.physics.movement

import org.koin.dsl.module
import pro.piechowski.highschoolstory.physics.movement.animaiton.MovementAnimationSystem
import pro.piechowski.highschoolstory.physics.movement.facedirection.FaceDirectionDebugSystem
import pro.piechowski.highschoolstory.physics.movement.facedirection.FaceDirectionSystem
import pro.piechowski.highschoolstory.physics.movement.input.MovementControllerInputSystem
import pro.piechowski.highschoolstory.physics.movement.input.MovementMultiplexInputSystem
import pro.piechowski.highschoolstory.physics.movement.velocity.VelocitySystem

val MovementModule =
    module {
        single { MovementAnimationSystem() }
        single { MovementControllerInputSystem() }
        single { MovementMultiplexInputSystem() }
        single { VelocitySystem() }
        single { FaceDirectionSystem() }
        single { FaceDirectionDebugSystem() }
    }
