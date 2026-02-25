package pro.piechowski.highschoolstory.scene.intro

import com.github.quillraven.fleks.World
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import pro.piechowski.highschoolstory.map.Road
import pro.piechowski.highschoolstory.state.GameState
import pro.piechowski.highschoolstory.vehicle.bus.Bus
import pro.piechowski.highschoolstory.vehicle.bus.BusColor
import pro.piechowski.kge.camera.CameraManager
import pro.piechowski.kge.camera.MeterCamera
import pro.piechowski.kge.character.player.playerCharacter
import pro.piechowski.kge.character.says
import pro.piechowski.kge.di.DependencyInjection.Global.inject
import pro.piechowski.kge.dialogue.DialogueManager
import pro.piechowski.kge.dialogue.await
import pro.piechowski.kge.dialogue.dialogue
import pro.piechowski.kge.direction.Direction4
import pro.piechowski.kge.map.MapManager
import pro.piechowski.kge.map.Tile
import pro.piechowski.kge.map.TiledMapManagerAdapter
import pro.piechowski.kge.physics.mps
import pro.piechowski.kge.physics.px
import pro.piechowski.kge.physics.times
import pro.piechowski.kge.story.Story
import pro.piechowski.kge.time.calendar.Calendar
import pro.piechowski.kge.time.clock.Clock
import kotlin.time.Duration.Companion.seconds

class RoadToLakeview : Story.Beat<GameState> {
    override val definition: Story.Beat.Definition<GameState, *> = RoadToLakeview

    private val meterCamera by inject<MeterCamera>()
    private val world by inject<World>()
    private val dialogueManager by inject<DialogueManager>()
    private val clock by inject<Clock>()
    private val calendar by inject<Calendar>()
    private val mapManager by inject<TiledMapManagerAdapter>()
    private val cameraManager by inject<CameraManager>()

    override fun shouldBePlayed(state: GameState): Boolean = true

    override suspend fun play() =
        with(world) {
            calendar.currentDate = LocalDate(2020, 8, 29)
            clock.currentTime = LocalTime(17, 0, 0)

            mapManager.currentMap = Road()

            val bus =
                Bus(Direction4.Right, BusColor.YELLOW, 10f.mps)
                    .apply {
                        position = Tile.Position(15, 8).toPixel() * px.toMeter()
                        // aiMovementInput.movementInput = Direction4.Right.vector
                    }

            // cameraManager.currentStrategy = FollowingCameraStrategy(bus.body)

            delay(1000)

            val playerCharacter = playerCharacter.value!!

            dialogueManager
                .startDialogue(
                    dialogue {
                        playerCharacter.says("Ehhhh...")
                        wait(2.seconds)
                        playerCharacter.says("Trzy kolejne lata bez starych.")
                    },
                ).await()
        }

    companion object : Story.Beat.Definition<GameState, RoadToLakeview> {
        private val mapManager by inject<MapManager<*, *>>()

        override suspend fun invoke(state: GameState): RoadToLakeview {
            return RoadToLakeview()
        }

        override fun shouldBeSpawned(state: GameState): Boolean {
            return mapManager.currentMap is Road
        }
    }
}
