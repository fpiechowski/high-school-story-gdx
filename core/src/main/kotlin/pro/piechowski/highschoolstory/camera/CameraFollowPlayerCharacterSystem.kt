package pro.piechowski.highschoolstory.camera

import com.github.quillraven.fleks.IntervalSystem
import io.github.oshai.kotlinlogging.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.physics.m
import pro.piechowski.highschoolstory.state.GameStateManager

class CameraFollowPlayerCharacterSystem :
    IntervalSystem(),
    KoinComponent {
    private val logger = KotlinLogging.logger { }

    private val cameraSet by inject<CameraSet>()
    private val cameraManager by inject<CameraManager>()
    private val gameStateManager by inject<GameStateManager>()

    override fun onTick() {
        if (cameraManager.followingPlayerCharacterValue) {
            gameStateManager.currentGameState.let { gameState ->
                cameraSet.moveTo(
                    gameState.playerCharacter.body.body.position.x.m,
                    gameState.playerCharacter.body.body.position.y.m,
                )
            }
        }
    }
}
