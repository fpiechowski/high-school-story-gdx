package pro.piechowski.highschoolstory.input

import com.badlogic.gdx.Input
import org.koin.core.component.KoinComponent
import pro.piechowski.highschoolstory.input.interaction.Interaction
import pro.piechowski.highschoolstory.input.interaction.InteractionInputMapping
import pro.piechowski.highschoolstory.input.movement.MovementInputMappings

data class InputAction(
    val name: String,
) {
    context(inputMapping: InputMapping)
    fun mapsToKey(key: KeyCode) = key in inputMapping.keysForAction(InputAction.Interaction)

    companion object
}

typealias KeyCode = Int

class InputMapping : KoinComponent {
    companion object {
        private val defaultInputMap: Map<InputAction, List<KeyCode>> =
            mapOf(InteractionInputMapping) + MovementInputMappings
    }

    private val composer: Composer? = getKoin().getOrNull<Composer>()

    private val inputMap: Map<InputAction, List<KeyCode>> = composer?.compose(defaultInputMap) ?: defaultInputMap

    fun keysForAction(action: InputAction): List<Int> = inputMap[action] ?: emptyList()

    fun interface Composer : KoinComponent {
        fun compose(mapping: Map<InputAction, List<KeyCode>>): Map<InputAction, List<KeyCode>>
    }
}

context(inputMapping: InputMapping)
fun Input.isActionPressed(action: InputAction): Boolean = inputMapping.keysForAction(action).any { isKeyPressed(it) }
