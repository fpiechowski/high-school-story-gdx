package pro.piechowski.highschoolstory.ecs.debug

import com.github.quillraven.fleks.IntervalSystem
import org.koin.core.scope.Scope
import pro.piechowski.highschoolstory.debug.physics.PhysicsDebugRenderingSystem
import pro.piechowski.highschoolstory.debug.selection.DebugSelectionIndicatorRenderingSystem
import pro.piechowski.highschoolstory.debug.text.DebugTextSystem
import pro.piechowski.highschoolstory.facedirection.FaceDirectionDebugSystem

context(scope: Scope)
val debugSystems: List<IntervalSystem>
    get() =
        with(scope) {
            listOf(
                get<FaceDirectionDebugSystem>(),
                get<DebugTextSystem>(),
                get<PhysicsDebugRenderingSystem>(),
                get<DebugSelectionIndicatorRenderingSystem>(),
            )
        }
