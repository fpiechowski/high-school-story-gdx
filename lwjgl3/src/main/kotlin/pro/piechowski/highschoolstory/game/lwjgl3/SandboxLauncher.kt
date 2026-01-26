@file:JvmName("SandboxLauncher")

package pro.piechowski.highschoolstory.game.lwjgl3

import arrow.fx.coroutines.await.ExperimentalAwaitAllApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.annotation.KoinInternalApi
import pro.piechowski.highschoolstory.SandboxEntrypoint
import pro.piechowski.highschoolstory.highSchoolStoryModule
import pro.piechowski.kge.Launcher
import pro.piechowski.kge.lwjgl3.Lwjgl3Launcher.Companion.launch
import kotlin.time.ExperimentalTime

@ExperimentalAwaitAllApi
@ExperimentalUnsignedTypes
@ExperimentalTime
@KoinInternalApi
@ExperimentalCoroutinesApi
@ExperimentalContextParameters
fun main() {
    Launcher.launch(listOf(highSchoolStoryModule), SandboxEntrypoint())
}
