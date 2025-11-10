package pro.piechowski.highschoolstory.input

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import pro.piechowski.highschoolstory.CoroutineContexts
import pro.piechowski.highschoolstory.`object`.GameObject

class InputManager {
    private val coroutineScope = CoroutineScope(CoroutineContexts.Logic)

    private val owners: MutableStateFlow<ArrayDeque<InputOwner>> = MutableStateFlow(ArrayDeque())

    val owner =
        owners.map { it.lastOrNull() }.stateIn(coroutineScope, SharingStarted.Eagerly, owners.value.firstOrNull())

    fun passOwnership(owner: InputOwner) {
        if (owner != this.owner.value) {
            owners.update { it.apply { addLast(owner) } }
        }
    }

    fun revokeOwnership() {
        owners.update { it.apply { removeLastOrNull() ?: this } }
    }
}

interface InputOwner : GameObject
