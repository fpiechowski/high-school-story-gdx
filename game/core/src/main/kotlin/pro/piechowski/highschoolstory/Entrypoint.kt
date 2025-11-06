package pro.piechowski.highschoolstory

import pro.piechowski.highschoolstory.character.player.PlayerCharacter
import pro.piechowski.highschoolstory.input.InputManager

class SandboxEntrypoint : Entrypoint {
    override suspend fun run(): Unit =
        with(getKoin()) {
            val playerCharacter = PlayerCharacter("Test", "Character")

            get<InputManager>().passOwnership(playerCharacter)
        }
}

class GameEntrypoint : Entrypoint {
    override suspend fun run() = TODO()
}
