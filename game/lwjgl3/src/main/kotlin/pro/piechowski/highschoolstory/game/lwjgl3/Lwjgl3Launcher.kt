@file:JvmName("Lwjgl3Launcher")

package pro.piechowski.highschoolstory.game.lwjgl3

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.annotation.KoinInternalApi
import pro.piechowski.highschoolstory.gameModule
import pro.piechowski.highschoolstory.inspector.runtime.RuntimeLauncher
import pro.piechowski.highschoolstory.lwjgl3.Launcher

@ExperimentalCoroutinesApi
@ExperimentalContextParameters
@KoinInternalApi
@RuntimeLauncher
fun main() {
    Launcher.launch(
        listOf(
            gameModule,
        ),
    )
}
