package pro.piechowski.highschoolstory.state

import com.github.quillraven.fleks.World
import ktx.collections.GdxArray
import pro.piechowski.kge.character.player.PlayerCharacterManager
import pro.piechowski.kge.di.DependencyInjection.Global.inject
import pro.piechowski.kge.map.MapManager
import pro.piechowski.kge.story.Story
import pro.piechowski.kge.story.StoryManager
import kotlin.getValue

class GameState : Story.State {
    private val world: World by inject()
    private val playerCharacterManager: PlayerCharacterManager by inject()
    private val mapManager: MapManager<*, *> by inject()
    private val storyManager: StoryManager<GameState> by inject()

    val playerCharacter get() = playerCharacterManager.playerCharacter.value
    val currentMap get() = mapManager.currentMap
    val worldSnapshot get() = world.snapshot()
    override val facts: GdxArray<Story.Fact> get() = storyManager.facts.value
}
