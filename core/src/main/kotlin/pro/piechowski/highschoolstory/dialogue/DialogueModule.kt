package pro.piechowski.highschoolstory.dialogue

import org.koin.dsl.module

val DialogueModule =
    module {
        single(createdAtStart = true) { DialogueManager() }
        single { DialogueUserInterface() }
        single { DialogueUserInterfaceUpdater() }
        single { DialogueInputProcessor() }
    }
