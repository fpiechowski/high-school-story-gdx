package pro.piechowski.highschoolstory

import com.sksamuel.hoplite.PropertySource
import org.koin.dsl.module
import pro.piechowski.highschoolstory.exterior.ExteriorTexture
import pro.piechowski.highschoolstory.scene.IntroScene
import pro.piechowski.highschoolstory.state.GameState
import pro.piechowski.highschoolstory.state.GameStateManager
import pro.piechowski.highschoolstory.ui.UserInterface

val gameModule =
    module {
        single<PropertySource> { PropertySource.resource("/config.yml") }
        single { IntroScene() }
        single<Entrypoint> { GameEntrypoint() }
        single { systemComposer }
        single { ExteriorTexture() }
        single { UserInterface() }
        single { GameStateManager<GameState>() }
    }
