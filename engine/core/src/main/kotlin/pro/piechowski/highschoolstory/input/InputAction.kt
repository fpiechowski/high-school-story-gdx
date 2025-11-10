package pro.piechowski.highschoolstory.input

import com.badlogic.gdx.Input
import org.koin.core.component.KoinComponent
import pro.piechowski.highschoolstory.input.movement.MovementInputMappings

data class InputAction(
    val name: String,
) {
    context(inputMapping: InputMapping)
    fun mapsToKey(key: KeyCode) = key in inputMapping.keysForAction(this)

    companion object
}

typealias KeyCode = Int

typealias InputMap = Map<InputAction, List<KeyCode>>

class InputMapping : KoinComponent {
    companion object {
        private val defaultInputMap: InputMap =
            MovementInputMappings
    }

    private val composer: Composer? = getKoin().getOrNull<Composer>()

    private val inputMap: InputMap = composer?.compose(defaultInputMap) ?: defaultInputMap

    fun keysForAction(action: InputAction): List<Int> = inputMap[action] ?: emptyList()

    fun interface Composer : KoinComponent {
        fun compose(mapping: InputMap): InputMap
    }
}

context(inputMapping: InputMapping)
fun Input.isActionPressed(action: InputAction): Boolean = inputMapping.keysForAction(action).any { isKeyPressed(it) }
