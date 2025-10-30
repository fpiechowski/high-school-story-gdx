package pro.piechowski.highschoolstory

import com.github.quillraven.fleks.World
import ktx.assets.async.AssetStorage
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.character.player.PlayerCharacter
import pro.piechowski.highschoolstory.dialogue.DialogueManager
import pro.piechowski.highschoolstory.dialogue.ui.DialogueUserInterfaceUpdater
import pro.piechowski.highschoolstory.light.SunLightManager
import pro.piechowski.highschoolstory.place.PlaceManager
import pro.piechowski.highschoolstory.scene.IntroScene
import pro.piechowski.highschoolstory.state.GameState
import pro.piechowski.highschoolstory.state.GameStateManager

class GameInitializer : KoinComponent {
    private val world: World by inject()
    private val assetStorage: AssetStorage by inject()
    private val placeManager: PlaceManager by inject()
    private val gameStateManager by inject<GameStateManager>()

    suspend fun initialize(gameState: GameState) {
        initializeCollectors()

        with(world) {
            with(assetStorage) {
                gameState.currentPlace?.let { placeManager.travelTo(it) }
                world.loadSnapshot(gameState.worldSnapshot)
            }
        }
    }

    private val introScene by inject<IntroScene>()

    suspend fun initializeTestGame() =
        with(getKoin()) {
            initializeCollectors()
            val testGameState = GameState(PlayerCharacter("Test", "Player"))
            gameStateManager.loadState(testGameState)
            introScene.play()
        }

    private fun initializeCollectors() {
        get<DialogueUserInterfaceUpdater>()
        get<DialogueManager>()
        get<SunLightManager>()
    }
}
