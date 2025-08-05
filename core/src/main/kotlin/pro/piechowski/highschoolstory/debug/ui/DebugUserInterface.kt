package pro.piechowski.highschoolstory.debug.ui

import ktx.scene2d.label
import ktx.scene2d.scene2d
import org.koin.core.component.KoinComponent

class DebugUserInterface : KoinComponent {
    val debugLabel = scene2d.label("")
}
