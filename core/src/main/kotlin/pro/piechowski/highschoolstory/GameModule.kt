package pro.piechowski.highschoolstory

import com.github.quillraven.fleks.World
import org.koin.dsl.module
import pro.piechowski.highschoolstory.ecs.invoke
import pro.piechowski.highschoolstory.input.GameInputMultiplexer
import pro.piechowski.highschoolstory.interaction.InteractionModule
import pro.piechowski.highschoolstory.movement.MovementModule
import pro.piechowski.highschoolstory.rendering.RenderingModule

val gameModule =
    module {
        includes(InteractionModule)
        includes(MovementModule)
        includes(RenderingModule)

        single { GameScreen() }
        single { GameInputMultiplexer() }

        single<World> { World() }
    }
