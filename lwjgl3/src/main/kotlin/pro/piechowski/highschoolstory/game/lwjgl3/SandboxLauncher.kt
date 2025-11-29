@file:JvmName("SandboxLauncher")

package pro.piechowski.highschoolstory.game.lwjgl3

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.annotation.KoinInternalApi
import pro.piechowski.highschoolstory.character.player.PlayerCharacter
import pro.piechowski.highschoolstory.highSchoolStoryModule
import pro.piechowski.highschoolstory.scene.intro.IntroScene
import pro.piechowski.kge.get
import pro.piechowski.kge.lwjgl3.DefaultLauncher
import kotlin.time.ExperimentalTime

@ExperimentalTime
@KoinInternalApi
@ExperimentalCoroutinesApi
@ExperimentalContextParameters
fun main() {
    DefaultLauncher.launch(listOf(highSchoolStoryModule)) {
        PlayerCharacter("Test", "Player")

        get<IntroScene>().play()
    }
}
