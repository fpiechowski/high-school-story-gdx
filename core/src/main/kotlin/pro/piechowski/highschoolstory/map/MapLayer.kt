package pro.piechowski.highschoolstory.map

sealed class MapLayer(
    val name: String,
) {
    data object Background : MapLayer("Background")

    data object Ground : MapLayer("Ground")

    data object Foreground : MapLayer("Foreground")

    data object Walls : MapLayer("Walls")
}
