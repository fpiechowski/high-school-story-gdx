package pro.piechowski.highschoolstory.input

import org.koin.dsl.module

val InputModule =
    module {
        single { InputState() }
        single { GameInputMultiplexer() }
    }
