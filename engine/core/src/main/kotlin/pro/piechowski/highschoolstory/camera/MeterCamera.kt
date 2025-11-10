package pro.piechowski.highschoolstory.camera

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.graphics.moveTo
import pro.piechowski.highschoolstory.Config
import pro.piechowski.highschoolstory.physics.Meter

class MeterCamera(
    viewportWidth: Meter,
    viewportHeight: Meter,
) : OrthographicCamera(viewportWidth.value, viewportHeight.value) {
    companion object {
        operator fun invoke(config: Config.MeterCamera) = MeterCamera(config.viewportWidth, config.viewportHeight)
    }

    fun moveTo(
        x: Meter,
        y: Meter,
    ) {
        moveTo(Vector2(x.value, y.value))
    }

    fun zoom(amount: Float) {
        zoom *= amount

        update()
    }
}

class MeterViewport(
    meterCamera: MeterCamera,
) : FitViewport(
        meterCamera.viewportWidth,
        meterCamera.viewportHeight,
        meterCamera,
    )
