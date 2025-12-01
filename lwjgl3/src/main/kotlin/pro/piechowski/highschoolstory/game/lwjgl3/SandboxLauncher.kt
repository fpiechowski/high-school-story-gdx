@file:JvmName("SandboxLauncher")

package pro.piechowski.highschoolstory.game.lwjgl3

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.annotation.KoinInternalApi
import pro.piechowski.highschoolstory.character.player.PlayerCharacter
import pro.piechowski.highschoolstory.highSchoolStoryModule
import pro.piechowski.highschoolstory.scene.intro.IntroScene
import pro.piechowski.kge.DependencyInjection
import pro.piechowski.kge.DependencyInjection.Companion.get
import pro.piechowski.kge.KoinDependencyInjectionAdapter
import pro.piechowski.kge.Launcher
import pro.piechowski.kge.lwjgl3.Lwjgl3Launcher
import pro.piechowski.kge.lwjgl3.Lwjgl3Launcher.Companion.launch
import kotlin.time.ExperimentalTime

@ExperimentalUnsignedTypes
@ExperimentalTime
@KoinInternalApi
@ExperimentalCoroutinesApi
@ExperimentalContextParameters
fun main() {
    DependencyInjection.default = KoinDependencyInjectionAdapter

    Launcher.launch(listOf(highSchoolStoryModule)) {
        PlayerCharacter("Test", "Player")

        get<IntroScene>().play()
    }
}
