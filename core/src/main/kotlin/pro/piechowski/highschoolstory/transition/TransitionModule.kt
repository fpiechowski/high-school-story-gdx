package pro.piechowski.highschoolstory.transition

import org.koin.dsl.module

val FadeModule =
    module {
        single { TransitionManager() }
        single { FadeTransitionSystem() }
    }
