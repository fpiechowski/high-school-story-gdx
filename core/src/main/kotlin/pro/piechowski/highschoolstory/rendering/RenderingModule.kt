package pro.piechowski.highschoolstory.rendering

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import org.koin.dsl.module
import pro.piechowski.highschoolstory.animation.SpriteAnimationSystem
import pro.piechowski.highschoolstory.rendering.sprite.CurrentSpritePositionSystem
import pro.piechowski.highschoolstory.rendering.sprite.SpriteRenderingSystem

val RenderingModule =
    module {
        single<Camera> { OrthographicCamera() }
        single<Viewport> { FitViewport(1280f, 720f, get()) }
        single { SpriteRenderingSystem() }
        single { CurrentSpritePositionSystem() }
        single { SpriteAnimationSystem() }
        single { ShapeRenderer() }
    }
