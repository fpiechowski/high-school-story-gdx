package pro.piechowski.highschoolstory

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.assets.async.AssetStorage
import org.koin.dsl.module
import pro.piechowski.highschoolstory.debug.DebugTextSystem
import pro.piechowski.highschoolstory.input.InputModule
import pro.piechowski.highschoolstory.ui.uiViewportQualifier

fun mainModule() =
    module {
        includes(InputModule)

        single { Config.load() }
        single { AssetStorage() }
        single { SpriteBatch() }
        single { BitmapFont() }
        single { DebugTextSystem() }
        single { Json() }
        single(gameModuleQualifier) { gameModule() }
        single<Viewport>(uiViewportQualifier) { FitViewport(1280f, 720f) }
        single { Stage(get(uiViewportQualifier)) }
    }
