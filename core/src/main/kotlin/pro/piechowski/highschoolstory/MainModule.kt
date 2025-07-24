package pro.piechowski.highschoolstory

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.assets.async.AssetStorage
import org.koin.dsl.module
import pro.piechowski.highschoolstory.debug.DebugTextSystem
import pro.piechowski.highschoolstory.rendering.BeginRenderingBatchSystem
import pro.piechowski.highschoolstory.rendering.EndRenderingBatchSystem

fun mainModule() =
    module {
        single { Config.load() }
        single { AssetStorage() }
        single { SpriteBatch() }
        single { BitmapFont() }
        single { BeginRenderingBatchSystem() }
        single { EndRenderingBatchSystem() }
        single { DebugTextSystem() }
        single<InputProcessor> { InputAdapter() }
        single { InputMultiplexer(get<InputProcessor>()) }
        single { Json() }
        single(gameModuleQualifier) { gameModule() }
    }
