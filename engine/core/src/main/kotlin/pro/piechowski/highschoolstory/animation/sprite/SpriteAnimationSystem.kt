package pro.piechowski.highschoolstory.animation.sprite

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import com.github.quillraven.fleks.World
import org.koin.core.component.KoinComponent
import pro.piechowski.highschoolstory.animation.CurrentAnimation
import pro.piechowski.highschoolstory.ecs.ReadOnly
import pro.piechowski.highschoolstory.ecs.Write
import pro.piechowski.highschoolstory.sprite.CurrentSprite

class SpriteAnimationSystem :
    IteratingSystem(
        World.Companion.family {
            all(
                @ReadOnly CurrentAnimation.Companion,
                @Write CurrentSprite.Companion,
            )
        },
    ),
    KoinComponent {
    override fun onTickEntity(entity: Entity) {
        val currentAnimation = entity[CurrentAnimation.Companion]

        currentAnimation.time += deltaTime

        entity[CurrentSprite.Companion].sprite =
            currentAnimation.let {
                it.animation.getKeyFrame(it.time)
            }
    }
}
