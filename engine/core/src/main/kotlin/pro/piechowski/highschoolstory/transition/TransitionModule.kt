package pro.piechowski.highschoolstory.transition

import org.koin.dsl.module

val TransitionModule =
    module {
        single { TransitionManager() }
        single { FadeTransitionSystem() }
    }
