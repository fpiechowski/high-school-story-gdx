package pro.piechowski.highschoolstory.ui

import com.badlogic.gdx.scenes.scene2d.Stage
import ktx.scene2d.actors
import ktx.scene2d.table
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.kge.Config
import pro.piechowski.kge.ui.dialogue.DialogueUserInterface

class UserInterface : KoinComponent {
    private val stage by inject<Stage>()
    private val dialogueUserInterface by inject<DialogueUserInterface>()

    init {
        stage.actors {
            table { _ ->
                setFillParent(true)
                defaults().pad(10f)

                row()

                bottom()

                add(dialogueUserInterface.dialogueBox)
                    .height(stage.viewport.worldHeight * 0.25f)
                    .expandX()
                    .fill()
            }
        }
    }
}
