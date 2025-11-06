package pro.piechowski.highschoolstory.engine.plugin

import com.intellij.icons.AllIcons
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.ui.JBSplitter
import com.intellij.ui.ToolbarDecorator
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.RowLayout
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.table.JBTable
import com.intellij.util.concurrency.AppExecutorUtil
import com.intellij.util.ui.JBUI
import pro.piechowski.highschoolstory.debug.server.KoinObjects
import java.awt.BorderLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.Timer
import javax.swing.table.DefaultTableModel

class InspectorToolWindowPanel(
    private val project: Project,
) : JPanel(BorderLayout()) {
    private val settings = InspectorSettings.getInstance()

    private val serverCombo = JComboBox<String>()
    private val autoRefresh = JBCheckBox("Auto-refresh", settings.state.autoRefresh)
    private val refreshButton = JButton("Refresh")
    private val addServerButton = JButton("Add server", AllIcons.General.Add)
    private val removeServerButton = JButton("Remove", AllIcons.General.Remove)

    private val koinTableModel =
        DefaultTableModel(arrayOf(arrayOf<Any>()), arrayOf("Type", "Instance"))

    private val koinTable = JBTable(koinTableModel)

    private var refreshTimer: Timer? = null

    init {
        border = JBUI.Borders.empty(8)

        // Top toolbar (servers + actions)
        val top =
            panel {
                row {
                    label("Server:")
                    cell(serverCombo)
                        .align(AlignX.FILL)
                        .resizableColumn()
                    button("Open Fleks Tab") {
                        openFleksIfSelected()
                    }.enabled(false) // enable when you implement it
                }.layout(RowLayout.PARENT_GRID)

                row {
                    cell(addServerButton)
                    cell(removeServerButton)
                    cell(refreshButton)
                    cell(autoRefresh)
                }
            }

        // Main content (table with decorator)
        val tablePanel =
            ToolbarDecorator
                .createDecorator(koinTable)
                .disableAddAction()
                .disableRemoveAction()
                .createPanel()

        val splitter =
            JBSplitter(false, 0.15f).apply {
                firstComponent = top
                secondComponent = JBScrollPane(tablePanel)
            }

        add(splitter, BorderLayout.CENTER)

        // Wire actions
        refreshButton.addActionListener { refresh() }
        addServerButton.addActionListener { onAddServer() }
        removeServerButton.addActionListener { onRemoveServer() }
        autoRefresh.addChangeListener {
            settings.state.autoRefresh = autoRefresh.isSelected
            updateAutoRefresh()
        }

        koinTable.addMouseListener(
            object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent) {
                    if (e.clickCount == 2) {
                        openRow(koinTable.selectedRow)
                    }
                }
            },
        )

        // Initialize servers in combo
        rebuildServerCombo()
        serverCombo.addActionListener {
            settings.state.selectedServerIndex = serverCombo.selectedIndex.coerceAtLeast(0)
            refresh()
        }

        updateAutoRefresh()
        refresh()
    }

    private fun rebuildServerCombo() {
        serverCombo.removeAllItems()
        settings.state.servers.forEach { serverCombo.addItem("${it.name}  (${it.baseUrl})") }
        val idx = settings.state.selectedServerIndex.coerceIn(0, (serverCombo.itemCount - 1).coerceAtLeast(0))
        if (serverCombo.itemCount > 0) serverCombo.selectedIndex = idx
    }

    private fun currentServer(): InspectorSettings.DebugServer? {
        val i = serverCombo.selectedIndex
        return if (i in settings.state.servers.indices) settings.state.servers[i] else null
    }

    private fun refresh() {
        val server = currentServer() ?: return
        refreshButton.isEnabled = false

        AppExecutorUtil.getAppExecutorService().submit {
            val result = runCatching { EngineClient.getKoinObjects(server.baseUrl) }
            ApplicationManager.getApplication().invokeLater {
                refreshButton.isEnabled = true
                result
                    .onSuccess { ko -> updateKoinTable(ko) }
                    .onFailure { ex -> showError(ex) }
            }
        }
    }

    private fun updateKoinTable(koin: KoinObjects) {
        koinTableModel.setRowCount(0)
        koin.definitions.forEach { def ->
            koinTableModel.addRow(arrayOf(def.type.name, def.value?.type?.name ?: "null"))
        }
    }

    private fun showError(ex: Throwable) {
        JOptionPane.showMessageDialog(
            this,
            "Failed to load Koin data:\n${ex.message}",
            "Inspector",
            JOptionPane.ERROR_MESSAGE,
        )
    }

    private fun onAddServer() {
        val dialog = AddServerDialog(this)
        if (dialog.showAndGet()) {
            settings.state.servers.add(
                InspectorSettings.DebugServer(
                    name = dialog.serverName,
                    baseUrl = dialog.serverUrl,
                ),
            )
            rebuildServerCombo()
        }
    }

    private fun onRemoveServer() {
        val idx = serverCombo.selectedIndex
        if (idx in settings.state.servers.indices) {
            settings.state.servers.removeAt(idx)
            rebuildServerCombo()
            refresh()
        }
    }

    private fun updateAutoRefresh() {
        refreshTimer?.stop()
        refreshTimer = null
        if (autoRefresh.isSelected) {
            refreshTimer = Timer(1500) { refresh() }.also { it.start() }
        }
    }

    private fun openRow(row: Int) {
        if (row < 0) return
        val type = koinTableModel.getValueAt(row, 0) as? String ?: return
        val isFleksWorld = type.endsWith("World") || type.contains("fleks", ignoreCase = true)
        if (isFleksWorld) {
            // TODO: open Fleks Explorer editor tab.
            JOptionPane.showMessageDialog(this, "Open Fleks Explorer (not implemented yet)")
        } else {
            // TODO: open Object Inspector editor tab backed by ObjectGraph.
            JOptionPane.showMessageDialog(this, "Open Object Inspector (not implemented yet)")
        }
    }

    private fun openFleksIfSelected() {
        val row = koinTable.selectedRow
        if (row >= 0) openRow(row)
    }
}
