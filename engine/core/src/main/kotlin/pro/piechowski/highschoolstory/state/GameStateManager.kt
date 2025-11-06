package pro.piechowski.highschoolstory.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class GameStateManager<T> : KoinComponent {
    private val gameState = MutableStateFlow<T?>(null)

    val currentGameState get() = gameState.value ?: throw GameStateNotFound()

    fun loadState(state: T) {
        gameState.value = state
    }

    fun saveState() {
        TODO()
    }
}

class GameStateNotFound : NullPointerException()
