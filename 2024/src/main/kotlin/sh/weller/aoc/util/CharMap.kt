package sh.weller.aoc.util


typealias CharMap = MutableList<MutableList<Char>>


fun List<String>.toCharMap(): CharMap =
    map { it.toCharArray().toMutableList() }.toMutableList()

fun List<List<*>>.print() {
    for (row in this) {
        println(row.map { it.toString() }.joinToString(""))
    }
}

typealias Coordinate = Pair<Int, Int>

val Coordinate.y
    get() = first
val Coordinate.x
    get() = second


fun Coordinate.neighbors(): List<Coordinate> = listOf(
    y - 1 to x,
    y to x + 1,
    y + 1 to x,
    y to x - 1
)