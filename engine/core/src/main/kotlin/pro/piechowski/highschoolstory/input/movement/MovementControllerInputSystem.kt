package pro.piechowski.highschoolstory.input.movement

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import io.github.oshai.kotlinlogging.KotlinLogging
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.debug
import pro.piechowski.highschoolstory.ecs.Write
import pro.piechowski.highschoolstory.input.InputAction
import pro.piechowski.highschoolstory.input.InputManager
import pro.piechowski.highschoolstory.input.InputMapping
import pro.piechowski.highschoolstory.input.isActionPressed
import pro.piechowski.highschoolstory.`object`.EntityGameObject

class MovementControllerInputSystem :
    IteratingSystem(
        World.family {
            all(
                @Write MovementInput.Controller,
            )
        },
    ),
    KoinComponent {
    private val logger = KotlinLogging.logger { }

    private val inputManager: InputManager by inject()
    private val inputMapping: InputMapping by inject()

    override fun onTickEntity(entity: Entity) {
        val controllerMovementInput = entity[MovementInput.Controller]

        controllerMovementInput.movementInput = Vector2.Zero.cpy()

        val inputOwner = inputManager.owner.value

        if (inputOwner is EntityGameObject && inputOwner.entity == entity) {
            with(inputMapping) {
                if (Gdx.input.isActionPressed(InputAction.MoveUp)) {
                    controllerMovementInput.movementInput.y += 1f
                }

                if (Gdx.input.isActionPressed(InputAction.MoveDown)) {
                    controllerMovementInput.movementInput.y -= 1f
                }

                if (Gdx.input.isActionPressed(InputAction.MoveLeft)) {
                    controllerMovementInput.movementInput.x -= 1f
                }

                if (Gdx.input.isActionPressed(InputAction.MoveRight)) {
                    controllerMovementInput.movementInput.x += 1f
                }
            }

            controllerMovementInput.movementInput = controllerMovementInput.movementInput.nor()
        }

        if (controllerMovementInput.movementInput != Vector2.Zero.cpy()) {
            logger.debug(controllerMovementInput, entity)
        }
    }
}
