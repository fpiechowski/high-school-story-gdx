package pro.piechowski.highschoolstory.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent

class GameStateManager : KoinComponent {
    private val gameState = MutableStateFlow<GameState?>(null)

    val currentGameState get() = gameState.value ?: throw GameStateNotFound()

    fun loadState(state: GameState) {
        gameState.value = state
    }

    fun saveState() {
        TODO()
    }
}

class GameStateNotFound : NullPointerException()
