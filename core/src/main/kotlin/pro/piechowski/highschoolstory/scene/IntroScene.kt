package pro.piechowski.highschoolstory.scene

import com.github.quillraven.fleks.World
import kotlinx.coroutines.launch
import ktx.async.KtxAsync
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.camera.CameraSet
import pro.piechowski.highschoolstory.ecs.plusAssign
import pro.piechowski.highschoolstory.exterior.ExteriorTexture
import pro.piechowski.highschoolstory.gdx.PhysicsWorld
import pro.piechowski.highschoolstory.map.Tile
import pro.piechowski.highschoolstory.physics.px
import pro.piechowski.highschoolstory.physics.times
import pro.piechowski.highschoolstory.place.PlaceManager
import pro.piechowski.highschoolstory.place.Road
import pro.piechowski.highschoolstory.vehicle.bus.Bus

class IntroScene :
    Scene(),
    KoinComponent {
    private val placeManager by inject<PlaceManager>()
    private val cameraSet by inject<CameraSet>()

    private val world by inject<World>()

    override suspend fun play() =
        KtxAsync.launch {
            placeManager.openPlace(Road)
            cameraSet.moveTo(720f.px, 480f.px)

            with(getKoin()) {
                with(world) {
                    Bus().apply { position = Tile.Position(15, 8).toPixel() * px.toMeter() }
                }
            }
        }
}
