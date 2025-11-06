package pro.piechowski.highschoolstory.ui

import com.badlogic.gdx.scenes.scene2d.Stage
import com.github.quillraven.fleks.IntervalSystem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserInterfaceSystem :
    IntervalSystem(),
    KoinComponent {
    private val stage: Stage by inject()

    override fun onTick() {
        stage.act()
        stage.draw()
    }
}
