package pro.piechowski.highschoolstory.camera

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.FitViewport

class PixelCamera : OrthographicCamera()

class PixelViewport(
    pixelCamera: PixelCamera,
) : FitViewport(1280f, 720f, pixelCamera)
