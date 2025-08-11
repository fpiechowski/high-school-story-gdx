package pro.piechowski.highschoolstory.ecs

import com.github.quillraven.fleks.IntervalSystem
import com.github.quillraven.fleks.SystemConfiguration
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.configureWorld
import org.koin.core.scope.Scope
import pro.piechowski.highschoolstory.Config
import pro.piechowski.highschoolstory.ecs.debug.debugSystems
import pro.piechowski.highschoolstory.ecs.game.gameSystems
import pro.piechowski.highschoolstory.ecs.input.inputSystems
import pro.piechowski.highschoolstory.ecs.physics.physicsSystems
import pro.piechowski.highschoolstory.ecs.rendering.setupRenderingSystems

context(scope: Scope)
operator fun World.Companion.invoke() =
    with(scope) {
        configureWorld {
            systems {
                inputSystems.forEach { add(it) }

                gameSystems.forEach { add(it) }

                physicsSystems.forEach { add(it) }

                setupRenderingSystems()

                if (get<Config>().debug) {
                    debugSystems.forEach { add(it) }
                }
            }
        }
    }

context(scope: Scope, systemConfiguration: SystemConfiguration)
inline fun <reified BEGIN : IntervalSystem, reified END : IntervalSystem> systemWrapper(block: () -> Unit) =
    with(scope) {
        with(systemConfiguration) {
            add(get<BEGIN>())
            block()
            add(get<END>())
        }
    }
