package pro.piechowski.highschoolstory.ecs.game

import org.koin.core.scope.Scope
import pro.piechowski.highschoolstory.animation.movement.MovementAnimationSystem
import pro.piechowski.highschoolstory.animation.sprite.SpriteAnimationSystem
import pro.piechowski.highschoolstory.camera.CameraFollowingPlayerCharacterSystem
import pro.piechowski.highschoolstory.facedirection.FaceDirectionSystem
import pro.piechowski.highschoolstory.interaction.InteractionSystem
import pro.piechowski.highschoolstory.movement.velocity.VelocitySystem
import pro.piechowski.highschoolstory.sprite.CurrentSpritePositionSystem

context(scope: Scope)
val gameSystems
    get() =
        with(scope) {
            listOf(
                get<FaceDirectionSystem>(),
                get<VelocitySystem>(),
                get<InteractionSystem>(),
                get<CameraFollowingPlayerCharacterSystem>(),
                get<MovementAnimationSystem>(),
                get<SpriteAnimationSystem>(),
                get<CurrentSpritePositionSystem>(),
            )
        }
