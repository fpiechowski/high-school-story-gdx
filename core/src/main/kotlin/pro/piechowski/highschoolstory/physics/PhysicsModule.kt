package pro.piechowski.highschoolstory.physics

import org.koin.dsl.module
import pro.piechowski.highschoolstory.physics.collision.CollisionShapeDebugSystem
import pro.piechowski.highschoolstory.physics.collision.CollisionShapePositionSystem

val PhysicsModule =
    module {
        single { CollisionShapePositionSystem() }
        single { CollisionShapeDebugSystem() }
    }
