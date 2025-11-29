package pro.piechowski.highschoolstory.state

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.Snapshot
import kotlinx.serialization.Serializable
import pro.piechowski.kge.character.player.PlayerCharacterBase

@Serializable
class GameState(
    val playerCharacter: PlayerCharacterBase,
    val currentMap: pro.piechowski.kge.map.Map? = null,
    val worldSnapshot: Map<Entity, Snapshot> = emptyMap(),
)
