package pro.piechowski.highschoolstory.animation

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import org.koin.core.component.KoinComponent
import pro.piechowski.highschoolstory.ecs.ReadOnly
import pro.piechowski.highschoolstory.ecs.Write
import pro.piechowski.highschoolstory.sprite.CurrentSprite

class SpriteAnimationSystem :
    IteratingSystem(
        World.family {
            all(
                @ReadOnly CurrentAnimation,
                @Write CurrentSprite,
            )
        },
    ),
    KoinComponent {
    override fun onTickEntity(entity: Entity) {
        val currentAnimation = entity[CurrentAnimation]

        currentAnimation.time += deltaTime

        entity[CurrentSprite].sprite =
            currentAnimation.let {
                it.animation.getKeyFrame(it.time)
            }
    }
}
