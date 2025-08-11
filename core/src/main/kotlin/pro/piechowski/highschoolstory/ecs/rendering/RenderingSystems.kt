package pro.piechowski.highschoolstory.ecs.rendering

import com.github.quillraven.fleks.IntervalSystem
import com.github.quillraven.fleks.SystemConfiguration
import org.koin.core.scope.Scope
import pro.piechowski.highschoolstory.ecs.systemWrapper
import pro.piechowski.highschoolstory.light.LightRenderingSystem
import pro.piechowski.highschoolstory.light.SunlightPostProcessingRenderingSystem
import pro.piechowski.highschoolstory.light.framebuffer.BeginLightFrameBufferSystem
import pro.piechowski.highschoolstory.light.framebuffer.EndLightFrameBufferSystem
import pro.piechowski.highschoolstory.map.MapRenderingSystem
import pro.piechowski.highschoolstory.sprite.SpriteRenderingSystem
import pro.piechowski.highschoolstory.sprite.framebuffer.BeginSpriteFrameBufferSystem
import pro.piechowski.highschoolstory.sprite.framebuffer.EndSpriteFrameBufferSystem
import pro.piechowski.highschoolstory.transition.FadeTransitionSystem

context(scope: Scope, systemConfiguration: SystemConfiguration)
fun setupRenderingSystems() =
    with(scope) {
        with(systemConfiguration) {
            systemWrapper<BeginSpriteFrameBufferSystem, EndSpriteFrameBufferSystem> {
                spriteRenderingSystems.forEach { add(it) }
            }

            systemWrapper<BeginLightFrameBufferSystem, EndLightFrameBufferSystem> {
                add(get<LightRenderingSystem>())
            }

            add(get<SunlightPostProcessingRenderingSystem>())

            add(get<FadeTransitionSystem>())
        }
    }

context(scope: Scope)
private val spriteRenderingSystems: List<IntervalSystem>
    get() =
        with(scope) {
            listOf(
                get<MapRenderingSystem.Background>(),
                get<SpriteRenderingSystem>(),
                get<MapRenderingSystem.Foreground>(),
            )
        }
