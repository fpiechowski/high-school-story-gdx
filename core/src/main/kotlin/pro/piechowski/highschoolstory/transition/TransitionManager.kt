package pro.piechowski.highschoolstory.transition

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class TransitionManager : KoinComponent {
    private val _transition = MutableStateFlow<Transition?>(null)

    val transition get() = _transition.asStateFlow()

    var currentTransition
        get() = _transition.value
        set(value) {
            _transition.value = value
        }

    fun update(deltaTime: Float) {
        currentTransition?.let { currentTransition ->
            val progress =
                if (currentTransition.duration != Duration.ZERO) {
                    (deltaTime.toDouble().seconds / currentTransition.duration).toFloat()
                } else {
                    1f
                }

            currentTransition.progress += progress
            currentTransition.progress = currentTransition.progress.coerceIn(0f, 1f)

            if (currentTransition.progress >= 1f) {
                currentTransition.job.complete()
            }
        }
    }

    fun play(transition: Transition): Transition {
        currentTransition = transition
        transition.job.start()
        return transition
    }
}
