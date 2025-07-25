package pro.piechowski.highschoolstory.character.rendering

import pro.piechowski.highschoolstory.character.animation.CharacterAnimation
import pro.piechowski.highschoolstory.direction.Direction4

object CharacterTexture {
    fun getLocomotionAnimationRegionsColumnRange(direction: Direction4) =
        when (direction) {
            Direction4.Down -> 18..23
            Direction4.Left -> 12..17
            Direction4.Right -> 0..5
            Direction4.Up -> 6..11
        }

    fun getAnimationRegionsRow(animation: CharacterAnimation) =
        when (animation) {
            CharacterAnimation.IDLE -> 1
            CharacterAnimation.WALK -> 2
        }
}
