package pro.piechowski.highschoolstory.movement

import org.koin.dsl.module
import pro.piechowski.highschoolstory.movement.animaiton.MovementAnimationSystem
import pro.piechowski.highschoolstory.movement.facedirection.FaceDirectionDebugSystem
import pro.piechowski.highschoolstory.movement.facedirection.FaceDirectionSystem
import pro.piechowski.highschoolstory.movement.input.MovementControllerInputSystem
import pro.piechowski.highschoolstory.movement.input.MovementMultiplexInputSystem
import pro.piechowski.highschoolstory.movement.position.PositionChangeSystem
import pro.piechowski.highschoolstory.movement.velocity.VelocitySystem

val MovementModule =
    module {
        single { MovementAnimationSystem() }
        single { MovementControllerInputSystem() }
        single { MovementMultiplexInputSystem() }
        single { VelocitySystem() }
        single { PositionChangeSystem() }
        single { FaceDirectionSystem() }
        single { FaceDirectionDebugSystem() }
    }
