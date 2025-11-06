package pro.piechowski.highschoolstory.inspector

import javafx.scene.Parent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.javafx.JavaFx

abstract class View {
    protected val coroutineScope = CoroutineScope(Dispatchers.JavaFx + SupervisorJob())

    abstract val root: Parent
}
