@file:OptIn(ExperimentalAwaitAllApi::class)

package pro.piechowski.highschoolstory

import arrow.fx.coroutines.await.ExperimentalAwaitAllApi
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.sksamuel.hoplite.PropertySource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module
import pro.piechowski.highschoolstory.asset.Assets
import pro.piechowski.highschoolstory.exterior.ExteriorTexture
import pro.piechowski.highschoolstory.scene.intro.RoadToLakeview
import pro.piechowski.highschoolstory.sprite.character.player.PlayerCharacterSpriteSheet
import pro.piechowski.highschoolstory.state.GameState
import pro.piechowski.highschoolstory.ui.UserInterface
import pro.piechowski.kge.Entrypoint
import pro.piechowski.kge.asset.AssetsLoader
import pro.piechowski.kge.character.CharacterModule
import pro.piechowski.kge.dialogue.DialogueModule
import pro.piechowski.kge.interaction.InteractionModule
import pro.piechowski.kge.story.StoryModule
import pro.piechowski.kge.time.TimeModule
import pro.piechowski.kge.weather.WeatherModule
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
val highSchoolStoryModule =
    module {
        single<PropertySource> { PropertySource.resource("/config.yml") }
        single { RoadToLakeview() }
        single<Entrypoint> { GameEntrypoint() }
        single { systemComposer }
        single { UserInterface() }
        single { Skin(Gdx.files.internal("ui/skin/uiskin.json")) }
        single { AssetsLoader { Assets() } }
        single { get<AssetsLoader<Assets>>().assets }
        single { PlayerCharacterSpriteSheet() }
        single { ExteriorTexture() }
        single { GameState() }

        includes(CharacterModule)
        includes(DialogueModule)
        includes(TimeModule)
        includes(InteractionModule)
        includes(StoryModule)
        includes(WeatherModule)
    }
