package pro.piechowski.highschoolstory.engine.plugin

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(
    name = "HighSchoolStoryInspectorSettings",
    storages = [Storage("highschoolstory-inspector.xml")],
)
class InspectorSettings : PersistentStateComponent<InspectorSettings.State> {
    data class State(
        var servers: MutableList<DebugServer> =
            mutableListOf(
                DebugServer(name = "Local Game", baseUrl = "http://localhost:8080"),
            ),
        var selectedServerIndex: Int = 0,
        var autoRefresh: Boolean = false,
    )

    data class DebugServer(
        var name: String = "",
        var baseUrl: String = "", // e.g. http://localhost:8080
    )

    private var state: State = State()

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = state
    }

    companion object {
        fun getInstance(): InspectorSettings = ApplicationManager.getApplication().getService(InspectorSettings::class.java)
    }
}
