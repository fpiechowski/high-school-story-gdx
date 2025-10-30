package pro.piechowski.highschoolstory.ui

import com.badlogic.gdx.scenes.scene2d.Stage
import ktx.scene2d.actors
import ktx.scene2d.table
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.Config
import pro.piechowski.highschoolstory.debug.ui.DebugUserInterface
import pro.piechowski.highschoolstory.dialogue.ui.DialogueUserInterface

class UserInterface : KoinComponent {
    private val stage by inject<Stage>()
    private val dialogueUserInterface by inject<DialogueUserInterface>()
    private val debugUserInterface by inject<DebugUserInterface>()
    private val config by inject<Config>()

    fun addActors() =
        stage.actors {
            table { tableActor ->
                setFillParent(true)
                defaults().pad(10f)

                row()

                bottom()

                add(dialogueUserInterface.dialogueBox)
                    .height(stage.viewport.worldHeight * 0.25f)
                    .expandX()
                    .fill()
            }

            if (config.debug) {
                table {
                    setFillParent(true)
                    top().left().pad(10f)

                    add(debugUserInterface.debugLabel)
                }
            }
        }
}
