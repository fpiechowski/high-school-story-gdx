package pro.piechowski.highschoolstory.inspector

import com.github.mouse0w0.darculafx.DarculaFX
import io.github.oshai.kotlinlogging.KotlinLogging
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.annotation.KoinInternalApi
import pro.piechowski.highschoolstory.inspector.container.ObjectContainerView
import pro.piechowski.highschoolstory.inspector.container.ObjectContainerViewModel
import pro.piechowski.highschoolstory.inspector.ecs.ECSView
import pro.piechowski.highschoolstory.inspector.ecs.ECSViewModel
import pro.piechowski.highschoolstory.inspector.`object`.ObjectInspectorView
import pro.piechowski.highschoolstory.inspector.`object`.ObjectInspectorViewModel
import pro.piechowski.highschoolstory.inspector.runtime.RuntimeView
import pro.piechowski.highschoolstory.inspector.runtime.RuntimeViewModel

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
@KoinInternalApi
class InspectorApplication : Application() {
    private val logger = KotlinLogging.logger { }

    private val adapters = Adapters()

    private val objectInspectorViewModel by lazy { ObjectInspectorViewModel(null) }
    private val objectInspectorView by lazy { ObjectInspectorView(objectInspectorViewModel) }

    private val objectContainerViewModel by lazy { ObjectContainerViewModel(adapters.globalInstances) }
    private val objectContainerView by lazy { ObjectContainerView(objectContainerViewModel, objectInspectorViewModel) }

    private val ecsViewModel by lazy { ECSViewModel(adapters.ecs) }
    private val ecsView by lazy { ECSView(ecsViewModel, objectInspectorViewModel) }

    private val runtimeViewModel by lazy { RuntimeViewModel(adapters.runtime) }
    private val runtimeView by lazy { RuntimeView(runtimeViewModel) }

    private val inspectorApplicationView =
        InspectorApplicationView(
            objectContainerView,
            ecsView,
            runtimeView,
            objectInspectorView,
        )

    override fun start(primaryStage: Stage) {
        primaryStage.scene = Scene(inspectorApplicationView.root)
        DarculaFX.applyDarculaStyle(primaryStage.scene)
        primaryStage.isMaximized = true
        primaryStage.show()
    }

    companion object {
        fun launch() = launch(InspectorApplication::class.java)
    }
}
