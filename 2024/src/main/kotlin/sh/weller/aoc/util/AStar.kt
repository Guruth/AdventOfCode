package sh.weller.aoc.util


import java.util.*


fun CharMap.aStar(start: Coordinate, end: Coordinate): List<Coordinate>? {
    fun heuristic(a: Coordinate, b: Coordinate): Int =
        (b.y - a.y) + (b.x - a.x)

    fun traceBack(history: Map<Coordinate, Coordinate>, current: Coordinate): List<Coordinate> {
        val path = mutableListOf<Coordinate>()
        var cur = current
        while (cur in history) {
            path.add(cur)
            cur = history[cur]!!
        }
        path.add(cur) // Add the start
        return path.reversed()
    }

    operator fun CharMap.get(coordinate: Coordinate): Char = this[coordinate.y][coordinate.x]
    operator fun CharMap.contains(coordinate: Coordinate): Boolean =
        coordinate.y in 0..<size && coordinate.x in 0..<first().size

    val history = mutableMapOf<Coordinate, Coordinate>()
    val pathScore = mutableMapOf<Coordinate, Int>()
        .withDefault { Int.MAX_VALUE }
        .also { it[start] = 0 }

    val estimatedScore = mutableMapOf<Coordinate, Int>()
        .withDefault { Int.MAX_VALUE }
        .also { it[end] = heuristic(start, end) }

    val paths = PriorityQueue(compareBy<Coordinate> { estimatedScore[it] })
        .also { it.add(start) }

    while (paths.isNotEmpty()) {
        val current = paths.poll()

        if (current == end) {
            return traceBack(history, current)
        }

        current.neighbors()
            .filter { it in this && this[it] != '#' }
            .forEach { neighbor ->
                val weight = pathScore.getValue(current) + 1
                if (weight < pathScore.getValue(neighbor)) {
                    history[neighbor] = current
                    pathScore[neighbor] = weight
                    estimatedScore[neighbor] = weight + heuristic(neighbor, end)
                    if (!paths.contains(neighbor)) {
                        paths.add(neighbor)
                    }
                }
            }
    }

    return null
}