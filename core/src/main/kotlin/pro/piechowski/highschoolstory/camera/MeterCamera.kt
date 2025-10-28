package pro.piechowski.highschoolstory.camera

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.FitViewport
import pro.piechowski.highschoolstory.physics.px

class MeterCamera : OrthographicCamera()

class MeterViewport(
    meterCamera: MeterCamera,
) : FitViewport(
        1280f
            .px
            .toMeter()
            .value,
        720f
            .px
            .toMeter()
            .value,
        meterCamera,
    )
