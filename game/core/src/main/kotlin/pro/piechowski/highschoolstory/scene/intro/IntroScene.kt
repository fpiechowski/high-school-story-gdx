package pro.piechowski.highschoolstory.scene.intro

import com.github.quillraven.fleks.World
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.vehicle.bus.Bus
import pro.piechowski.highschoolstory.vehicle.bus.BusColor
import pro.piechowski.kge.camera.CameraManager
import pro.piechowski.kge.camera.MeterCamera
import pro.piechowski.kge.character.player.PlayerCharacterManager
import pro.piechowski.kge.character.says
import pro.piechowski.kge.dialogue.DialogueManager
import pro.piechowski.kge.dialogue.await
import pro.piechowski.kge.dialogue.dialogue
import pro.piechowski.kge.direction.Direction4
import pro.piechowski.kge.koin
import pro.piechowski.kge.map.Tile
import pro.piechowski.kge.physics.m
import pro.piechowski.kge.physics.mps
import pro.piechowski.kge.physics.px
import pro.piechowski.kge.physics.times
import pro.piechowski.kge.scene.Scene
import pro.piechowski.kge.time.calendar.Calendar
import pro.piechowski.kge.time.clock.Clock

class IntroScene :
    Scene(),
    KoinComponent {
    private val meterCamera by inject<MeterCamera>()

    private val world by inject<World>()
    private val dialogueManager by inject<DialogueManager>()
    private val cameraManager by inject<CameraManager>()
    private val clock by inject<Clock>()
    private val calendar by inject<Calendar>()
    private val playerCharacterManager: PlayerCharacterManager by inject()

    override suspend fun play() =
        with(world) {
            with(koin) {
                calendar.currentDate = LocalDate(2020, 8, 29)
                clock.currentTime = LocalTime(17, 0, 0)

                Bus
                    .Companion(Direction4.Right, BusColor.YELLOW, 10f.mps)
                    .atPosition(Tile.Position(15, 8).toPixel() * px.toMeter())

                meterCamera.moveTo((meterCamera.viewportWidth / 2).m, (meterCamera.viewportHeight / 2).m)

                delay(1000)

                delay(1000)

                val playerCharacter = playerCharacterManager.playerCharacter.value!!

                dialogueManager
                    .startDialogue(
                        dialogue {
                            playerCharacter.says("Ehhhh...")
                        },
                    ).await()

                delay(2000)

                dialogueManager
                    .startDialogue(
                        dialogue {
                            playerCharacter.says("Trzy kolejne lata bez starych.")
                        },
                    ).await()
            }
        }
}
