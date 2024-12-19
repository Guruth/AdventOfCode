package sh.weller.aoc

import sh.weller.aoc.util.*

object Day16 : SomeDay<Int> {

    override val day: Int = 16

    override fun partOne(input: List<String>): Int {
        val paths = input.toCharMap()
            .findPaths()

        return paths.minOf { it.score }
    }


    override fun partTwo(input: List<String>): Int {
        val paths = input.toCharMap()
            .findPaths()

        return paths.groupBy { it.score }.minBy { it.key }.value.flatMap { it.visitedPoints }.toSet().count() + 1
    }


    private fun CharMap.findPaths(): List<Path> {
        val startCoordinate = findPoint { it == 'S' }
        val endCoordinate = findPoint { it == 'E' }

        val startingPath = Path(startCoordinate)
        val paths = mutableListOf(startingPath)
        val finishedPaths = mutableListOf<Path>()

        val scoreAtPoint = mutableMapOf<Coordinate, Int>()

        while (paths.isNotEmpty()) {
            val currentPath = paths.removeFirst()

            var currentTile = getTile(currentPath.startingPoint)

            while (true) {
                if (currentTile.coordinate == endCoordinate) {
                    finishedPaths.add(currentPath)
                    break
                }

                if (currentTile is Tile.Wall || currentTile.coordinate in currentPath.visitedPoints) {
                    break
                }

                if ((scoreAtPoint[currentTile.coordinate] ?: Int.MAX_VALUE) < currentPath.score - 1000) {
                    break
                }
                currentPath.visitedPoints.add(currentTile.coordinate)

                val tileRight = getNextTile(currentTile.coordinate, currentPath.direction.rotateRight())
                if (tileRight !is Tile.Wall) {
                    paths.add(
                        currentPath.copy(
                            score = currentPath.score + 1001,
                            direction = currentPath.direction.rotateRight(),
                            visitedPoints = currentPath.visitedPoints.toMutableList(),
                            startingPoint = tileRight.coordinate
                        )
                    )
                }
                val tileLeft = getNextTile(currentTile.coordinate, currentPath.direction.rotateLeft())
                if (tileLeft !is Tile.Wall) {
                    paths.add(
                        Path(
                            score = currentPath.score + 1001,
                            direction = currentPath.direction.rotateLeft(),
                            visitedPoints = currentPath.visitedPoints.toMutableList(),
                            startingPoint = tileLeft.coordinate
                        )
                    )
                }

                currentPath.score += 1

                if ((scoreAtPoint[currentTile.coordinate] ?: Int.MAX_VALUE) > currentPath.score) {
                    scoreAtPoint[currentTile.coordinate] = currentPath.score
                }

                currentTile = getNextTile(currentTile.coordinate, currentPath.direction)
            }
        }

        return finishedPaths
    }

    private fun CharMap.findPoint(matcher: (Char) -> Boolean): Coordinate {
        for ((y, row) in this.withIndex()) {
            for ((x, c) in row.withIndex()) {
                if (matcher(c)) {
                    return y to x
                }
            }
        }
        throw IllegalArgumentException("No match found")
    }

    private fun CharMap.getNextTile(coordinate: Coordinate, direction: Direction) =
        when (direction) {
            Direction.North -> getTile(coordinate.y - 1 to coordinate.x)
            Direction.East -> getTile(coordinate.y to coordinate.x + 1)
            Direction.South -> getTile(coordinate.y + 1 to coordinate.x)
            Direction.West -> getTile(coordinate.y to coordinate.x - 1)
        }

    private fun CharMap.getTile(coordinate: Coordinate): Tile {
        if (coordinate.y < 0 || coordinate.y >= size || coordinate.x < 0 || coordinate.x >= first().size) {
            return Tile.Wall(coordinate)
        }

        return when (this[coordinate.y][coordinate.x]) {
            '.', 'S', 'E' -> Tile.Floor(coordinate)
            '#' -> Tile.Wall(coordinate)
            else -> throw IllegalArgumentException("Unknown Tile")
        }
    }

    private sealed interface Tile {
        val coordinate: Coordinate

        data class Floor(override val coordinate: Coordinate) : Tile
        data class Wall(override val coordinate: Coordinate) : Tile
    }

    private data class Path(
        val startingPoint: Coordinate,
        val visitedPoints: MutableList<Coordinate> = mutableListOf(),
        val direction: Direction = Direction.East,
        var score: Int = 0
    )

    private enum class Direction {
        North, East, South, West;

        fun rotateRight(): Direction =
            when (this) {
                North -> East
                East -> South
                South -> West
                West -> North
            }

        fun rotateLeft(): Direction =
            when (this) {
                North -> West
                East -> North
                South -> East
                West -> South
            }
    }

}

