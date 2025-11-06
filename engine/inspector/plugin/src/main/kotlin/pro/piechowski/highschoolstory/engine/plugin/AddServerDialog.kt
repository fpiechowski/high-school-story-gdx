package pro.piechowski.highschoolstory.engine.plugin

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

class AddServerDialog(
    parent: JComponent,
) : DialogWrapper(parent, true) {
    private val nameField = JBTextField("Local Game")
    private val urlField = JBTextField("http://localhost:8080")

    var serverName: String = ""
        private set
    var serverUrl: String = ""
        private set

    init {
        title = "Add Debug Server"
        init()
    }

    override fun createCenterPanel(): JComponent =
        panel {
            row("Name:") { cell(nameField).resizableColumn() }
            row("Base URL:") { cell(urlField).resizableColumn() }
        }

    override fun doOKAction() {
        serverName = nameField.text.trim()
        serverUrl = urlField.text.trim()
        super.doOKAction()
    }
}
