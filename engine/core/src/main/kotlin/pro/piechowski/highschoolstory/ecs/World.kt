package pro.piechowski.highschoolstory.ecs

import com.github.quillraven.fleks.IntervalSystem
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.configureWorld
import org.koin.core.scope.Scope
import pro.piechowski.highschoolstory.Config
import pro.piechowski.highschoolstory.ecs.debug.debugSystems
import pro.piechowski.highschoolstory.ecs.game.gameSystems
import pro.piechowski.highschoolstory.ecs.physics.physicsSystems
import pro.piechowski.highschoolstory.ecs.rendering.renderingSystems
import pro.piechowski.highschoolstory.input.inputSystems

context(scope: Scope)
operator fun World.Companion.invoke() =
    with(scope) {
        configureWorld {
            systems {
                val engineSystems =
                    inputSystems + gameSystems + physicsSystems + renderingSystems +
                        if (get<Config>().debug.enabled) {
                            debugSystems
                        } else {
                            emptyList()
                        }

                val allSystems =
                    getOrNull<SystemComposer>()
                        ?.compose(engineSystems)
                        ?: engineSystems

                allSystems.forEach { add(it) }
            }
        }
    }

fun interface SystemComposer {
    fun compose(systems: List<IntervalSystem>): List<IntervalSystem>

    companion object {
        inline fun <reified T : IntervalSystem> List<IntervalSystem>.withAddedAfter(system: IntervalSystem): List<IntervalSystem> {
            val index = indexOfFirst { it is T }

            return if (index == -1) {
                this
            } else {
                buildList(size + 1) {
                    addAll(subList(0, index + 1))
                    add(system)
                    addAll(subList(index + 1, size))
                }
            }
        }

        inline fun <reified T : IntervalSystem> List<IntervalSystem>.withAddedBefore(newSystem: IntervalSystem): List<IntervalSystem> {
            val index = indexOfFirst { it is T }
            if (index == -1) return this

            return buildList(size + 1) {
                addAll(subList(0, index))
                add(newSystem)
                addAll(subList(index, size))
            }
        }
    }
}
