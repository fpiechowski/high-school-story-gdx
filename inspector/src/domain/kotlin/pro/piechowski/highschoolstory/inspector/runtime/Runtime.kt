package pro.piechowski.highschoolstory.inspector.runtime

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow

interface Runtime {
    val gameScope: CoroutineScope

    val launchedGameJob: StateFlow<Job?>

    val runtimeLauncher: RuntimeLauncher

    fun start()

    fun stop()

    fun pause()
}
