package pro.piechowski.highschoolstory.character

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.BodyDef
import com.github.quillraven.fleks.EntityTag
import kotlinx.serialization.Serializable
import ktx.assets.async.AssetStorage
import ktx.assets.async.Identifier
import ktx.box2d.body
import ktx.box2d.box
import ktx.collections.toGdxArray
import pro.piechowski.highschoolstory.animation.CurrentAnimation
import pro.piechowski.highschoolstory.animation.Direction4Animations
import pro.piechowski.highschoolstory.direction.Direction8
import pro.piechowski.highschoolstory.ecs.Archetype
import pro.piechowski.highschoolstory.gdx.GdxSprite
import pro.piechowski.highschoolstory.gdx.PhysicsWorld
import pro.piechowski.highschoolstory.interaction.Interactor
import pro.piechowski.highschoolstory.interaction.input.InteractionInput
import pro.piechowski.highschoolstory.physics.body.PhysicsBody
import pro.piechowski.highschoolstory.physics.movement.Speed
import pro.piechowski.highschoolstory.physics.movement.animaiton.MovementAnimation
import pro.piechowski.highschoolstory.physics.movement.facedirection.FaceDirection
import pro.piechowski.highschoolstory.physics.movement.input.MovementInput
import pro.piechowski.highschoolstory.physics.px
import pro.piechowski.highschoolstory.rendering.sprite.CharacterSprite
import pro.piechowski.highschoolstory.rendering.sprite.CurrentSprite

const val CHARACTER_ANIMATION_DURATION = 1f / 5f

@Serializable
data object Character : EntityTag() {
    context(assetStorage: AssetStorage, physicsWorld: PhysicsWorld)
    fun archetype(spriteSheetIdentifier: Identifier<Texture>) =
        Archetype {
            this += Character

            this +=
                PhysicsBody(
                    physicsWorld.body(BodyDef.BodyType.DynamicBody) {
                        box(
                            CharacterSprite.WIDTH.px
                                .toMeter()
                                .value,
                            CharacterSprite.HEIGHT.px
                                .toMeter()
                                .value / 4,
                        )
                    },
                )

            val characterTexture = assetStorage[spriteSheetIdentifier]
            val characterTextureRegions = TextureRegion.split(characterTexture, 48, 96)
            val downIdleAnimation =
                Animation(
                    CHARACTER_ANIMATION_DURATION,
                    characterTextureRegions[1]
                        .slice(18..23)
                        .map { CharacterSprite(it) }
                        .toGdxArray(),
                    Animation.PlayMode.LOOP,
                )
            this +=
                MovementAnimation.Idle(
                    animations =
                        Direction4Animations(
                            right =
                                Animation(
                                    CHARACTER_ANIMATION_DURATION,
                                    characterTextureRegions[1]
                                        .slice(0..5)
                                        .map { region -> CharacterSprite(region) }
                                        .toGdxArray(),
                                    Animation.PlayMode.LOOP,
                                ),
                            up =
                                Animation(
                                    CHARACTER_ANIMATION_DURATION,
                                    characterTextureRegions[1]
                                        .slice(6..11)
                                        .map { region -> CharacterSprite(region) }
                                        .toGdxArray(),
                                    Animation.PlayMode.LOOP,
                                ),
                            left =
                                Animation(
                                    CHARACTER_ANIMATION_DURATION,
                                    characterTextureRegions[1]
                                        .slice(12..17)
                                        .map { region -> CharacterSprite(region) }
                                        .toGdxArray(),
                                    Animation.PlayMode.LOOP,
                                ),
                            down = downIdleAnimation,
                        ),
                )
            this +=
                MovementAnimation.Walk(
                    animations =
                        Direction4Animations(
                            right =
                                Animation(
                                    CHARACTER_ANIMATION_DURATION,
                                    characterTextureRegions[2]
                                        .slice(0..5)
                                        .map { region -> CharacterSprite(region) }
                                        .toGdxArray(),
                                    Animation.PlayMode.LOOP,
                                ),
                            up =
                                Animation(
                                    CHARACTER_ANIMATION_DURATION,
                                    characterTextureRegions[2]
                                        .slice(6..11)
                                        .map { region -> CharacterSprite(region) }
                                        .toGdxArray(),
                                    Animation.PlayMode.LOOP,
                                ),
                            left =
                                Animation(
                                    CHARACTER_ANIMATION_DURATION,
                                    characterTextureRegions[2]
                                        .slice(12..17)
                                        .map { region -> CharacterSprite(region) }
                                        .toGdxArray(),
                                    Animation.PlayMode.LOOP,
                                ),
                            down =
                                Animation(
                                    CHARACTER_ANIMATION_DURATION,
                                    characterTextureRegions[2]
                                        .slice(18..23)
                                        .map { region -> CharacterSprite(region) }
                                        .toGdxArray(),
                                    Animation.PlayMode.LOOP,
                                ),
                        ),
                )
            this += CurrentSprite(GdxSprite(characterTextureRegions[0][3]))
            this += CurrentAnimation(downIdleAnimation)
            this += MovementInput.Multiplex()
            this += Speed.walk
            this += FaceDirection(Direction8.Down)
            this += Interactor
            this += InteractionInput()
        }
}
