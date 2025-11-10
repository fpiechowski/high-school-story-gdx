package pro.piechowski.highschoolstory.character.physics.body

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import ktx.box2d.body
import ktx.box2d.box
import pro.piechowski.highschoolstory.character.CharacterBase
import pro.piechowski.highschoolstory.character.sprite.CharacterSpriteBase
import pro.piechowski.highschoolstory.physics.PhysicsWorld

object CharacterBody {
    context(physicsWorld: PhysicsWorld)
    operator fun invoke(): Body =
        physicsWorld.body(BodyDef.BodyType.DynamicBody) {
            box(
                CharacterSpriteBase.Companion.WIDTH.px
                    .toMeter()
                    .value,
                CharacterSpriteBase.Companion.HEIGHT.px
                    .toMeter()
                    .value / CharacterBase.Companion.HEIGHT_TO_DEPTH_RATIO,
                bodyFixturePositionOffset,
            )
        }

    val bodyFixturePositionOffset =
        Vector2(
            0f,
            -(CharacterSpriteBase.Companion.HEIGHT / CharacterBase.Companion.HEIGHT_TO_DEPTH_RATIO).px.toMeter().value,
        )
}
