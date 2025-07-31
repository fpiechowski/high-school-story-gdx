package pro.piechowski.highschoolstory.map

import com.badlogic.gdx.maps.objects.CircleMapObject
import com.badlogic.gdx.maps.objects.PolygonMapObject
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import kotlinx.coroutines.flow.MutableStateFlow
import ktx.box2d.body
import ktx.box2d.box
import ktx.box2d.circle
import ktx.box2d.loop
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pro.piechowski.highschoolstory.gdx.PhysicsWorld
import pro.piechowski.highschoolstory.physics.px

class MapManager : KoinComponent {
    val currentMap = MutableStateFlow<TiledMap?>(null)
    val physicsWorld by inject<PhysicsWorld>()
    val currentMapBodies = MutableStateFlow(emptyList<Body>())

    val mapRenderer get() = currentMap.value?.let(::OrthogonalTiledMapRenderer)

    fun openMap(map: TiledMap) {
        currentMapBodies.value = emptyList()
        currentMap.value = map

        currentMap.value?.let { map ->
            map.layers
                .find { it.name == MapLayer.Walls.name }
                ?.objects
                ?.map { wall ->
                    physicsWorld.body {
                        when (wall) {
                            is PolygonMapObject -> {
                                loop(
                                    wall.polygon.vertices
                                        .map { it.px.toMeter().value }
                                        .toFloatArray(),
                                )

                                position.set(
                                    wall.polygon.let {
                                        Vector2(
                                            it.x.px
                                                .toMeter()
                                                .value,
                                            it.y.px
                                                .toMeter()
                                                .value,
                                        )
                                    },
                                )
                            }

                            is RectangleMapObject ->
                                box(
                                    wall.rectangle.width.px
                                        .toMeter()
                                        .value,
                                    wall.rectangle.height.px
                                        .toMeter()
                                        .value,
                                )

                            is CircleMapObject ->
                                circle(
                                    wall.circle.radius.px
                                        .toMeter()
                                        .value,
                                )
                        }
                    }
                }?.also {
                    currentMapBodies.value = it
                }
        }
    }
}
