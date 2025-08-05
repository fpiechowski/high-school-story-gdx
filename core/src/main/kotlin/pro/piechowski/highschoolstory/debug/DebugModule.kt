package pro.piechowski.highschoolstory.debug

import org.koin.dsl.module
import pro.piechowski.highschoolstory.debug.selection.DebugSelectionIndicatorRenderingSystem
import pro.piechowski.highschoolstory.debug.selection.DebugSelectionInputProcessor
import pro.piechowski.highschoolstory.debug.selection.DebugSelectionManager

val DebugModule =
    module {
        single { DebugSelectionManager() }
        single { DebugSelectionInputProcessor() }
        single { DebugSelectionIndicatorRenderingSystem() }
    }
