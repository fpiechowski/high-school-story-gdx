package pro.piechowski.highschoolstory.character

import org.koin.dsl.module
import pro.piechowski.highschoolstory.character.camera.CameraFollowingPlayerCharacterSystem
import pro.piechowski.highschoolstory.character.player.PlayerCharacterManager

val CharacterModule =
    module {
        single { PlayerCharacterManager() }

        single { CameraFollowingPlayerCharacterSystem() }
    }
