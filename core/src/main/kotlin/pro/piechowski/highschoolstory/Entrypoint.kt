package pro.piechowski.highschoolstory

import arrow.fx.coroutines.await.ExperimentalAwaitAllApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pro.piechowski.highschoolstory.asset.Assets
import pro.piechowski.kge.asset.AssetsLoader
import pro.piechowski.highschoolstory.character.player.PlayerCharacter
import pro.piechowski.kge.di.DependencyInjection.Global.get
import pro.piechowski.kge.Entrypoint
import pro.piechowski.kge.character.player.PlayerCharacterManager
import pro.piechowski.kge.di.DependencyInjection.Global.inject
import pro.piechowski.kge.input.InputManager

@ExperimentalCoroutinesApi
@ExperimentalAwaitAllApi
class SandboxEntrypoint : Entrypoint {
    private val inputManager by inject<InputManager>()
    private val playerCharacterManager by inject<PlayerCharacterManager>()

    override suspend fun run() {
        get<AssetsLoader<Assets>>().load()

        playerCharacterManager.playerCharacter.value = PlayerCharacter("Test", "Character")
        playerCharacterManager.playerCharacter.value?.let { inputManager.passOwnership(it) }

        //IntroScene().play()
    }
}

class GameEntrypoint : Entrypoint {
    override suspend fun run() = TODO()
}
