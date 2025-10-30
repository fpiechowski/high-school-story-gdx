package pro.piechowski.highschoolstory.map

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import org.koin.core.Koin
import pro.piechowski.highschoolstory.physics.Meter
import pro.piechowski.highschoolstory.physics.px

/**
 * EndlessMapRenderer draws a repeating tiled map using projection-matrix translation,
 * without moving the camera.
 *
 * It renders three segments (head, middle, tail) along the chosen axis.
 * When the camera crosses a segment boundary, positions wrap seamlessly.
 */
class RepeatingMapRenderer(
    tiledMap: TiledMap,
    unitScale: Float = 1f,
    private val orientation: RepeatingMap.Orientation = RepeatingMap.Orientation.HORIZONTAL,
) : OrthogonalTiledMapRenderer(tiledMap, unitScale) {
    companion object {
        context(koin: Koin)
        suspend operator fun invoke(
            repeatingMap: RepeatingMap,
            unitScale: Float = 1f,
            orientation: RepeatingMap.Orientation = RepeatingMap.Orientation.HORIZONTAL,
        ) = RepeatingMapRenderer(repeatingMap.tiledMap.await(), unitScale, orientation)
    }

    private val mapWidth: Meter
    private val mapHeight: Meter

    // Positions of the three active map tiles in world space
    private val headPos = Vector2()
    private val midPos = Vector2()
    private val tailPos = Vector2()

    init {
        val widthInTiles = tiledMap.properties.get("width", Int::class.java)
        val heightInTiles = tiledMap.properties.get("height", Int::class.java)
        val tileWidth = tiledMap.properties.get("tilewidth", Int::class.java)
        val tileHeight = tiledMap.properties.get("tileheight", Int::class.java)

        mapWidth = (widthInTiles * tileWidth).px.toMeter()
        mapHeight = (heightInTiles * tileHeight).px.toMeter()

        when (orientation) {
            RepeatingMap.Orientation.HORIZONTAL -> {
                midPos.set(0f, 0f)
                headPos.set(-mapWidth.value, 0f)
                tailPos.set(mapWidth.value, 0f)
            }
            RepeatingMap.Orientation.VERTICAL -> {
                midPos.set(0f, 0f)
                headPos.set(0f, mapHeight.value)
                tailPos.set(0f, -mapHeight.value)
            }
        }
    }

    /**
     * Updates which map chunks are considered head/middle/tail
     * depending on camera movement.
     */
    fun update(camera: OrthographicCamera) {
        when (orientation) {
            RepeatingMap.Orientation.HORIZONTAL -> updateHorizontal(camera)
            RepeatingMap.Orientation.VERTICAL -> updateVertical(camera)
        }
    }

    private fun updateHorizontal(camera: OrthographicCamera) {
        if (camera.position.x > tailPos.x - mapWidth.value / 2f) {
            val oldHead = headPos.cpy()
            headPos.set(midPos)
            midPos.set(tailPos)
            tailPos.set(oldHead.add(mapWidth.value * 2f, 0f))
        } else if (camera.position.x < headPos.x + mapWidth.value / 2f) {
            val oldTail = tailPos.cpy()
            tailPos.set(midPos)
            midPos.set(headPos)
            headPos.set(oldTail.sub(mapWidth.value * 2f, 0f))
        }
    }

    private fun updateVertical(camera: OrthographicCamera) {
        if (camera.position.y > headPos.y - mapHeight.value / 2f) {
            val oldTail = tailPos.cpy()
            tailPos.set(midPos)
            midPos.set(headPos)
            headPos.set(oldTail.add(0f, mapHeight.value * 2f))
        } else if (camera.position.y < tailPos.y + mapHeight.value / 2f) {
            val oldHead = headPos.cpy()
            headPos.set(midPos)
            midPos.set(tailPos)
            tailPos.set(oldHead.sub(0f, mapHeight.value * 2f))
        }
    }

    /**
     * Renders the endless map without moving the camera,
     * by temporarily modifying the projection matrix.
     */
    fun renderEndless(
        camera: OrthographicCamera,
        layers: IntArray? = null,
    ) {
        update(camera)

        val baseProjection = camera.combined.cpy()
        val positions = listOf(headPos, midPos, tailPos)

        for (pos in positions) {
            // Apply translation to the projection matrix
            val translatedProjection = Matrix4(baseProjection).translate(pos.x, pos.y, 0f)
            batch.projectionMatrix = translatedProjection
            this.viewBounds.set(
                pos.x,
                pos.y,
                mapWidth.value,
                mapHeight.value,
            )

            if (layers == null) render() else render(layers)
        }

        // restore original matrix
        batch.projectionMatrix = baseProjection
    }
}
