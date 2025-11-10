package pro.piechowski.highschoolstory.input

import org.koin.dsl.module

val CoreInputModule =
    module {
        single { InputManager() }
        single { GameInputMultiplexer() }
        single { InputMapping() }
    }

val GameInputModule =
    module {
        single { GameInputMultiplexer() }
    }
