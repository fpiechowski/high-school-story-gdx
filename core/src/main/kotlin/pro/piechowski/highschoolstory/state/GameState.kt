package pro.piechowski.highschoolstory.state

import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.Snapshot
import kotlinx.serialization.Serializable
import pro.piechowski.kge.character.player.PlayerCharacterBase
import pro.piechowski.kge.map.TiledMapAdapter

class GameState(
    val playerCharacter: PlayerCharacterBase,
    val currentMap: TiledMapAdapter? = null,
    val worldSnapshot: Map<Entity, Snapshot> = emptyMap(),
)
