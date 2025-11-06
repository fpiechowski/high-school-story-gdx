package pro.piechowski.highschoolstory.input

import org.koin.dsl.module

val MainInputModule =
    module {
        single { InputManager() }
        single { GameInputMultiplexer() }
        single { InputMapping() }
    }

val GameInputModule =
    module {
        single { GameInputMultiplexer() }
    }
