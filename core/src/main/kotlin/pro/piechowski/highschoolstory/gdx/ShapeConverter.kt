package pro.piechowski.highschoolstory.gdx

import com.badlogic.gdx.math.Shape2D
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun GdxRectangle.toPolygon(): GdxPolygon =
    GdxPolygon(
        floatArrayOf(
            x,
            y,
            x + width,
            y,
            x + width,
            y + height,
            x,
            y + height,
        ),
    )

fun GdxCircle.toPolygon(segments: Int = 12): GdxPolygon {
    val angleStep = (2 * PI) / segments
    return GdxPolygon(
        (0 until segments)
            .flatMap { i ->
                val angle = i * angleStep
                listOf(
                    (x + cos(angle) * radius).toFloat(),
                    (y + sin(angle) * radius).toFloat(),
                )
            }.toFloatArray(),
    )
}

fun GdxEllipse.toPolygon(segments: Int = 20): GdxPolygon {
    val angleStep = (2 * PI) / segments
    val cx = x + width / 2f
    val cy = y + height / 2f
    val rx = width / 2f
    val ry = height / 2f

    return GdxPolygon(
        (0 until segments)
            .flatMap { i ->
                val angle = i * angleStep
                listOf(
                    (cx + cos(angle) * rx).toFloat(),
                    (cy + sin(angle) * ry).toFloat(),
                )
            }.toFloatArray(),
    )
}

fun Shape2D.toPolygon() =
    when (this) {
        is GdxRectangle -> toPolygon()
        is GdxCircle -> toPolygon()
        is GdxEllipse -> toPolygon()
        is GdxPolygon -> this
        else -> throw IllegalArgumentException("Unsupported shape type: ${this::class.simpleName}")
    }
