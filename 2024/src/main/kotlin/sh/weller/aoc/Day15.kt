package sh.weller.aoc

import sh.weller.aoc.util.print
import sh.weller.aoc.util.to2DList

object Day15 : SomeDay<Int> {

    override val day: Int = 15

    override fun partOne(input: List<String>): Int {
        val map = input.take(input.indexOf("")).toMap()
        val movementAttempts = input.takeLast(input.size - input.indexOf("")).flatMap { it.toCharArray().toList() }

        var position = map.getRobotPosition()
        for (attempt in movementAttempts) {
            position = when (attempt) {
                '^' -> map.moveUp(position)
                '>' -> map.moveRight(position)
                'v' -> map.moveDown(position)
                '<' -> map.moveLeft(position)
                else -> throw IllegalArgumentException("Unknown Direction")
            }
        }


        map.print()

        return map.mapIndexed { y, row ->
            row.mapIndexed { x, c ->
                if (c == 'O') {
                    ((y + 1) * 100) + (x + 1)
                } else {
                    0
                }
            }
        }.flatten().sum()
    }

    private fun List<String>.toMap(): MutableList<MutableList<Char>> =
        this.drop(1).dropLast(1)
            .map { it.substring(1, it.length - 1) }
            .to2DList()

    private fun MutableList<MutableList<Char>>.getRobotPosition(): Pair<Int, Int> {
        forEachIndexed { y, row ->
            row.forEachIndexed { x, point ->
                if (point == '@') {
                    return y to x
                }
            }
        }
        return -1 to -1
    }

    private fun MutableList<MutableList<Char>>.moveUp(position: Pair<Int, Int>): Pair<Int, Int> {
        if (position.first - 1 < 0) {
            return position
        }
        val thingAtPosition = this[position.first][position.second]
        val thingUp = this[position.first - 1][position.second]
        if (thingUp == '#') {
            return position
        } else if (thingUp == '.') {
            this[position.first - 1][position.second] = thingAtPosition
            this[position.first][position.second] = '.'
            return position.first - 1 to position.second
        } else {
            val thingAboveNewPosition = moveUp(position.first - 1 to position.second)
            if (thingAboveNewPosition == position.first - 1 to position.second) {
                return position
            } else {
                this[position.first - 1][position.second] = thingAtPosition
                this[position.first][position.second] = '.'
                return position.first - 1 to position.second
            }
        }
    }

    private fun MutableList<MutableList<Char>>.moveRight(position: Pair<Int, Int>): Pair<Int, Int> {
        if (position.second + 1 >= first().size) {
            return position
        }
        val thingAtPosition = this[position.first][position.second]
        val thingRight = this[position.first][position.second + 1]
        if (thingRight == '#') {
            return position
        } else if (thingRight == '.') {
            this[position.first][position.second + 1] = thingAtPosition
            this[position.first][position.second] = '.'
            return position.first to position.second + 1
        } else {
            val thingRightNewPosition = moveRight(position.first to position.second + 1)
            if (thingRightNewPosition == position.first to position.second + 1) {
                return position
            } else {
                this[position.first][position.second + 1] = thingAtPosition
                this[position.first][position.second] = '.'
                return position.first to position.second + 1
            }
        }
    }

    private fun MutableList<MutableList<Char>>.moveDown(position: Pair<Int, Int>): Pair<Int, Int> {
        if (position.first + 1 >= size) {
            return position
        }
        val thingAtPosition = this[position.first][position.second]
        val thingDown = this[position.first + 1][position.second]
        if (thingDown == '#') {
            return position
        } else if (thingDown == '.') {
            this[position.first + 1][position.second] = thingAtPosition
            this[position.first][position.second] = '.'
            return position.first + 1 to position.second
        } else {
            val thingDownNewPosition = moveDown(position.first + 1 to position.second)
            if (thingDownNewPosition == position.first + 1 to position.second) {
                return position
            } else {
                this[position.first + 1][position.second] = thingAtPosition
                this[position.first][position.second] = '.'
                return position.first + 1 to position.second
            }
        }
    }

    private fun MutableList<MutableList<Char>>.moveLeft(position: Pair<Int, Int>): Pair<Int, Int> {
        if (position.second - 1 < 0) {
            return position
        }
        val thingAtPosition = this[position.first][position.second]
        val thingLeft = this[position.first][position.second - 1]
        if (thingLeft == '#') {
            return position
        } else if (thingLeft == '.') {
            this[position.first][position.second - 1] = thingAtPosition
            this[position.first][position.second] = '.'
            return position.first to position.second - 1
        } else {
            val thingLeftNewPosition = moveLeft(position.first to position.second - 1)
            if (thingLeftNewPosition == position.first to position.second - 1) {
                return position
            } else {
                this[position.first][position.second - 1] = thingAtPosition
                this[position.first][position.second] = '.'
                return position.first to position.second - 1
            }
        }
    }

    override fun partTwo(input: List<String>): Int {
        val map = input.take(input.indexOf("")).to2DList().widen()
        val movementAttempts = input.takeLast(input.size - input.indexOf("")).flatMap { it.toCharArray().toList() }
            .map {
                when (it) {
                    '^' -> Direction.UP
                    '>' -> Direction.RIGHT
                    'v' -> Direction.DOWN
                    '<' -> Direction.LEFT
                    else -> throw IllegalArgumentException("Unknown Direction")
                }
            }

        val robot = map.first { it is Robot }

        for (attempt in movementAttempts) {
            if (robot.canMove(map, attempt)) {
                robot.move(map, attempt)
            }
        }

        map.print()

        return map.filterIsInstance<Box>().map { it.pos.y * 100 + it.pos.x }.sum()
    }

    private fun MutableList<MutableList<Char>>.widen(): List<MapObject> =
        map { row ->
            row.map { char ->
                when (char) {
                    '#' -> listOf('#', '#')
                    'O' -> listOf('[', ']')
                    '@' -> listOf('@', '.')
                    '.' -> listOf('.', '.')
                    else -> emptyList()
                }
            }.flatten()
        }
            .mapIndexed { y, row ->
                row.mapIndexed { x, char ->
                    when (char) {
                        '#' -> listOf(Wall(y to x))
                        '[' -> listOf(Box(y to x))
                        '@' -> listOf(Robot(y to x))
                        else -> emptyList()
                    }
                }
            }.flatten().flatten()

    private fun List<MapObject>.print() {
        val map = MutableList(this.maxOf { it.pos.y + 1 }) { MutableList(this.maxOf { it.pos.x + 1 }) { '.' } }

        forEach {
            when (it) {
                is Box -> {
                    map[it.pos.y][it.pos.x] = '['
                    map[it.pos.y][it.pos.x + 1] = ']'
                }

                is Robot -> map[it.pos.y][it.pos.x] = '@'
                is Wall -> map[it.pos.y][it.pos.x] = '#'
            }
        }

        map.print()
    }


    private sealed interface MapObject {
        var pos: Coordinate

        fun canMove(map: List<MapObject>, direction: Direction): Boolean

        fun move(map: List<MapObject>, direction: Direction)

        fun isAt(pos: Coordinate): Boolean
    }

    private data class Robot(override var pos: Coordinate) : MapObject {
        override fun isAt(pos: Coordinate): Boolean =
            this.pos == pos

        override fun canMove(map: List<MapObject>, direction: Direction): Boolean {
            val newPosition = pos.move(direction)

            return map.filter { it.isAt(newPosition) }
                .filter { it !== this }
                .all { it.canMove(map, direction) }
        }

        override fun move(map: List<MapObject>, direction: Direction) {
            val newPosition = pos.move(direction)

            map.filter { it.isAt(newPosition) }
                .filter { it !== this }
                .forEach { it.move(map, direction) }

            pos = newPosition
        }
    }

    private data class Box(override var pos: Coordinate) : MapObject {
        override fun isAt(pos: Coordinate): Boolean =
            this.pos == pos || (this.pos.y == pos.y && this.pos.x + 1 == pos.x)

        override fun canMove(map: List<MapObject>, direction: Direction): Boolean {
            val newPosition = pos.move(direction)
            val newPosition2 = newPosition.y to newPosition.x + 1

            return map
                .filter { it !== this }
                .filter { it.isAt(newPosition) || it.isAt(newPosition2) }
                .all { it.canMove(map, direction) }
        }

        override fun move(map: List<MapObject>, direction: Direction) {
            val newPosition = pos.move(direction)
            val newPosition2 = newPosition.y to newPosition.x + 1

            map.filter { it.isAt(newPosition) || it.isAt(newPosition2) }
                .filter { it !== this }
                .forEach { it.move(map, direction) }

            pos = newPosition
        }
    }


    private data class Wall(override var pos: Coordinate) : MapObject {
        override fun canMove(map: List<MapObject>, direction: Direction): Boolean = false

        override fun isAt(pos: Coordinate): Boolean =
            this.pos == pos

        override fun move(map: List<MapObject>, direction: Direction) {
            throw IllegalArgumentException("Walls can not move")
        }
    }

    enum class Direction {
        UP, RIGHT, DOWN, LEFT
    }

    private val Coordinate.y
        get() = first
    private val Coordinate.x
        get() = second

    private fun Coordinate.move(direction: Direction): Coordinate =
        when (direction) {
            Direction.UP -> y - 1 to x
            Direction.RIGHT -> y to x + 1
            Direction.DOWN -> y + 1 to x
            Direction.LEFT -> y to x - 1
        }
}
