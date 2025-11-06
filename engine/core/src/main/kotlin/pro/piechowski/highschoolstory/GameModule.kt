package pro.piechowski.highschoolstory

import com.github.quillraven.fleks.World
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import pro.piechowski.highschoolstory.camera.GameCameraModule
import pro.piechowski.highschoolstory.character.player.PlayerCharacterModule
import pro.piechowski.highschoolstory.debug.DebugModule
import pro.piechowski.highschoolstory.dialogue.DialogueModule
import pro.piechowski.highschoolstory.ecs.invoke
import pro.piechowski.highschoolstory.input.GameInputModule
import pro.piechowski.highschoolstory.input.GameInputMultiplexer
import pro.piechowski.highschoolstory.interaction.InteractionModule
import pro.piechowski.highschoolstory.light.LightModule
import pro.piechowski.highschoolstory.map.MapModule
import pro.piechowski.highschoolstory.movement.MovementModule
import pro.piechowski.highschoolstory.physics.PhysicsModule
import pro.piechowski.highschoolstory.place.PlaceModule
import pro.piechowski.highschoolstory.rendering.RenderingModule
import pro.piechowski.highschoolstory.time.TimeModule
import pro.piechowski.highschoolstory.transition.TransitionModule
import pro.piechowski.highschoolstory.weather.WeatherModule

val gameModuleQualifier = StringQualifier("gameModule")

fun gameModule() =
    module {
        includes(GameInputModule)
        includes(PhysicsModule)
        includes(InteractionModule)
        includes(MovementModule)
        includes(DialogueModule)
        includes(RenderingModule)
        includes(MapModule)
        includes(GameCameraModule)
        includes(DebugModule)
        includes(PlaceModule)
        includes(PlayerCharacterModule)
        includes(TransitionModule)
        includes(WeatherModule)
        includes(TimeModule)
        includes(LightModule)
        single { GameScreen() }

        single { GameInputMultiplexer() }

        single<World> {
            World()
        }
    }
