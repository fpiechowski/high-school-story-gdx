package pro.piechowski.highschoolstory.inspector

import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.KoinInternalApi
import pro.piechowski.highschoolstory.inspector.ecs.EcsInspectorViewModel
import pro.piechowski.highschoolstory.inspector.game.GameInspectorViewModel
import pro.piechowski.highschoolstory.inspector.koin.KoinInspectorViewModel

@KoinInternalApi
class InspectorViewModel(
    private val gameInspectorViewModel: GameInspectorViewModel,
    private val koinInspectorViewModel: KoinInspectorViewModel,
    private val ecsInspectorViewModel: EcsInspectorViewModel,
) {
    val anyFocused =
        combine(
            gameInspectorViewModel.focused,
            koinInspectorViewModel.focused,
            ecsInspectorViewModel.focused,
        ) { gameInspectorFocused, koinInspectorFocused, ecsInspectorFocused ->
            gameInspectorFocused || koinInspectorFocused || ecsInspectorFocused
        }.filter { it }
            .map {
                Unit
            }
}
