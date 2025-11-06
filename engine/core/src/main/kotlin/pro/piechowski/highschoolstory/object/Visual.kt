package pro.piechowski.highschoolstory.`object`

import com.github.quillraven.fleks.EntityComponentContext
import pro.piechowski.highschoolstory.ecs.get
import pro.piechowski.highschoolstory.sprite.CurrentSprite

interface Visual : EntityGameObject {
    context(_: EntityComponentContext)
    val sprite: CurrentSprite get() = entity[CurrentSprite.Companion]
}
