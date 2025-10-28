package pro.piechowski.highschoolstory.inspector.runtime

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow

interface Runtime {
    val gameScope: CoroutineScope

    val launchedGameJob: StateFlow<Job?>

    val launcher: Launcher

    fun start()

    fun stop()

    fun pause()

    fun interface Launcher {
        fun launch()
    }
}

class RuntimeLauncherNotFoundException : Exception("RuntimeLauncher not found")
