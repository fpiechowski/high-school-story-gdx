package pro.piechowski.highschoolstory.direction

open class Direction4Mapping<T>(
    open val up: T,
    open val down: T,
    open val left: T,
    open val right: T,
) {
    operator fun get(direction: Direction4) =
        when (direction) {
            Direction4.Up -> up
            Direction4.Right -> right
            Direction4.Down -> down
            Direction4.Left -> left
        }
}
