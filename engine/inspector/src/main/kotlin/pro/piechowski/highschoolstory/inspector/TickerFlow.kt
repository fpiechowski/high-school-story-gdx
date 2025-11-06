package pro.piechowski.highschoolstory.inspector

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration

fun tickerFlow(interval: Duration) =
    flow {
        while (true) {
            emit(Unit)
            delay(interval)
        }
    }
