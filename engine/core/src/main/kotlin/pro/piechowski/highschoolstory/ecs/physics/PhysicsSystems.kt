package pro.piechowski.highschoolstory.ecs.physics

import org.koin.core.scope.Scope
import pro.piechowski.highschoolstory.physics.body.PhysicsWorldStepSystem

context(scope: Scope)
val physicsSystems
    get() =
        with(scope) {
            listOf(get<PhysicsWorldStepSystem>())
        }
