package pro.piechowski.highschoolstory.state

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.Snapshot
import kotlinx.serialization.Serializable
import pro.piechowski.highschoolstory.character.player.PlayerCharacter
import pro.piechowski.highschoolstory.place.Place

@Serializable
class GameState(
    val playerCharacter: PlayerCharacter,
    val currentPlace: Place? = null,
    val worldSnapshot: Map<Entity, Snapshot> = emptyMap(),
)
