package pro.piechowski.highschoolstory.map

import com.badlogic.gdx.graphics.OrthographicCamera
import com.github.quillraven.fleks.IntervalSystem
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

sealed class MapRenderingSystem(
    val layers: List<MapLayer> = emptyList(),
) : IntervalSystem(),
    KoinComponent {
    private val mapManager by inject<MapManager>()
    private val camera by inject<OrthographicCamera>()

    override fun onTick() {
        mapManager.mapRenderer?.let { renderer ->
            renderer.setView(camera)
            mapManager.currentMap.value?.let { map ->
                val layerIndices =
                    map.layers
                        .mapIndexed { idx, layer -> idx to layer }
                        .toMap()
                        .filterValues { layer -> layer.name in layers.map { it.name } }
                        .map { it.key }

                renderer.render(layerIndices.toIntArray())
            }
        }
    }

    class Background : MapRenderingSystem(layerNames) {
        companion object {
            val layerNames = listOf(MapLayer.Background, MapLayer.Ground)
        }
    }

    class Foreground : MapRenderingSystem(layerNames) {
        companion object {
            val layerNames = listOf(MapLayer.Foreground)
        }
    }
}
