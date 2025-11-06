package pro.piechowski.highschoolstory

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.scenes.scene2d.Stage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ktx.assets.async.AssetStorage
import org.koin.core.annotation.KoinInternalApi
import org.koin.dsl.module
import pro.piechowski.highschoolstory.camera.MainCameraModule
import pro.piechowski.highschoolstory.debug.server.DebugServer
import pro.piechowski.highschoolstory.debug.server.FleksEntityComponentsProvider
import pro.piechowski.highschoolstory.debug.server.KoinObjectsProvider
import pro.piechowski.highschoolstory.debug.text.DebugTextSystem
import pro.piechowski.highschoolstory.ecs.SystemComposer
import pro.piechowski.highschoolstory.input.MainInputModule
import pro.piechowski.highschoolstory.ui.UserInterfaceViewport

@ExperimentalCoroutinesApi
@ExperimentalContextParameters
@KoinInternalApi
fun mainModule() =
    module {
        includes(MainInputModule)
        includes(MainCameraModule)

        single(gameModuleQualifier) { gameModule() }

        single { with(getKoin()) { Config.load() } }
        single {
            AssetStorage().apply {
                setLoader { TmxMapLoader(fileResolver) }
            }
        }
        single { SpriteBatch() }
        single { BitmapFont() }
        single { DebugTextSystem() }
        single { Json() }
        single { Stage(get<UserInterfaceViewport>()) }

        single { DebugServer() }
        single { KoinObjectsProvider() }
        single { FleksEntityComponentsProvider() }
    }
