package pro.piechowski.highschoolstory.engine.plugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class InspectorToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(
        project: Project,
        toolWindow: ToolWindow,
    ) {
        val panel = ConnectionsPanel(project)

        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel.component, "", false)

        toolWindow.contentManager.addContent(content)
    }
}
