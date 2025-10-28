package pro.piechowski.highschoolstory.inspector

import javafx.scene.Parent
import javafx.scene.layout.BorderPane
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.annotation.KoinInternalApi
import pro.piechowski.highschoolstory.inspector.container.ObjectContainerView
import pro.piechowski.highschoolstory.inspector.ecs.ECSView
import pro.piechowski.highschoolstory.inspector.`object`.ObjectInspectorView
import pro.piechowski.highschoolstory.inspector.runtime.RuntimeView

@ExperimentalCoroutinesApi
@KoinInternalApi
class InspectorApplicationView(
    viewModel: InspectorApplicationViewModel,
    private val objectContainerView: ObjectContainerView,
    private val ecsView: ECSView,
    private val runtimeView: RuntimeView,
    private val objectInspectorView: ObjectInspectorView,
) : InspectorView<InspectorApplicationViewModel>(viewModel) {
    private val borderPane =
        BorderPane()
            .apply {
                top = runtimeView.root
                left = objectContainerView.root
                center = ecsView.root
                right = objectInspectorView.root
            }

    override val root: Parent = borderPane
}
