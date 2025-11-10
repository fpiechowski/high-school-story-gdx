package pro.piechowski.highschoolstory

import com.sksamuel.hoplite.PropertySource
import org.koin.dsl.module
import pro.piechowski.highschoolstory.exterior.ExteriorTexture
import pro.piechowski.highschoolstory.scene.intro.IntroScene
import pro.piechowski.highschoolstory.sprite.character.player.PlayerCharacterSpriteSheet
import pro.piechowski.highschoolstory.ui.UserInterface
import pro.piechowski.kge.Entrypoint

val highSchoolStoryModule =
    module {
        single<PropertySource> { PropertySource.resource("/config.yml") }
        single { IntroScene() }
        single<Entrypoint> { GameEntrypoint() }
        single { systemComposer }
        single { ExteriorTexture() }
        single { UserInterface() }
        single { PlayerCharacterSpriteSheet() }
    }
