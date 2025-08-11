package pro.piechowski.highschoolstory.light

import box2dLight.RayHandler
import com.github.quillraven.fleks.IntervalSystem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.camera.MeterCamera

class LightRenderingSystem :
    IntervalSystem(),
    KoinComponent {
    private val rayHandler by inject<RayHandler>()
    private val meterCamera by inject<MeterCamera>()

    override fun onTick() {
        rayHandler.setCombinedMatrix(meterCamera)
        rayHandler.setAmbientLight(0f, 0f, 0f, 1f)
        rayHandler.updateAndRender()
    }
}
