package pro.piechowski.highschoolstory.interaction

import org.koin.dsl.module
import pro.piechowski.highschoolstory.interaction.input.InteractionInputProcessor

val InteractionModule =
    module {
        single { InteractionInputProcessor() }
        single { InteractionSystem() }
        single { InteractorDebugSystem() }
        single { InteractableDebugSystem() }
    }
