package pro.piechowski.highschoolstory

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.assets.async.AssetStorage
import org.koin.dsl.module

val mainModule =
    module {
        single { Config.load() }
        single { AssetStorage() }
        single { SpriteBatch() }
        single<InputProcessor> { InputAdapter() }
        single { InputMultiplexer(get<InputProcessor>()) }
        single { Json() }
    }
