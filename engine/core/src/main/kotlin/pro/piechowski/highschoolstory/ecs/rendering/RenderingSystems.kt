package pro.piechowski.highschoolstory.ecs.rendering

import com.github.quillraven.fleks.IntervalSystem
import org.koin.core.scope.Scope
import pro.piechowski.highschoolstory.rendering.light.LightRenderingSystem
import pro.piechowski.highschoolstory.rendering.map.MapRenderingSystem
import pro.piechowski.highschoolstory.rendering.sprite.SpriteRenderingSystem
import pro.piechowski.highschoolstory.transition.FadeTransitionSystem
import pro.piechowski.highschoolstory.ui.UserInterfaceSystem

context(scope: Scope)
val renderingSystems: List<IntervalSystem>
    get() =
        with(scope) {
            listOf(
                get<MapRenderingSystem.Background>(),
                get<SpriteRenderingSystem>(),
                get<MapRenderingSystem.Foreground>(),
                get<LightRenderingSystem>(),
                get<FadeTransitionSystem>(),
                get<UserInterfaceSystem>(),
            )
        }
