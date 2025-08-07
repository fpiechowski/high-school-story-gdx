package pro.piechowski.highschoolstory.transition

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlin.time.Duration

sealed class Transition(
    val duration: Duration,
    var progress: Float = 0f,
    val job: CompletableJob = Job(),
) {
    sealed class FadeBlack(
        duration: Duration,
    ) : Transition(duration) {
        class In(
            duration: Duration,
            progress: Float = 0f,
        ) : FadeBlack(duration)

        class Out(
            duration: Duration,
            progress: Float = 0f,
        ) : FadeBlack(duration)
    }
}
