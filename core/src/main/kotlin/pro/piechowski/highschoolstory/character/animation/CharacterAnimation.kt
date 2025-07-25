package pro.piechowski.highschoolstory.character.animation

import pro.piechowski.highschoolstory.physics.s

enum class CharacterAnimation {
    IDLE,
    WALK,
    ;

    companion object {
        val duration = (1f / 5f).s
    }
}
