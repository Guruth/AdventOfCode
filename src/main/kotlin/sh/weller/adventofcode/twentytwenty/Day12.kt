package sh.weller.adventofcode.twentytwenty

import kotlin.math.absoluteValue


fun List<String>.day12Part2(): Int {
    var xShipPosition = 0
    var yShipPosition = 0

    var xWaypointPosition = 10
    var yWaypointPosition = 1

    this.map { it.toInstruction() }
        .forEach {
            when (it.first) {
                'E' -> xWaypointPosition += it.second
                'S' -> yWaypointPosition -= it.second
                'W' -> xWaypointPosition -= it.second
                'N' -> yWaypointPosition += it.second

                'L' -> {
                    val (x, y) = (xWaypointPosition to yWaypointPosition).part2Rotate(it)
                    xWaypointPosition = x
                    yWaypointPosition = y
                }
                'R' -> {
                    val (x, y) = (xWaypointPosition to yWaypointPosition).part2Rotate(it)
                    xWaypointPosition = x
                    yWaypointPosition = y

                }
                'F' -> {
                    xShipPosition += (xWaypointPosition * it.second)
                    yShipPosition += (yWaypointPosition * it.second)
                }
            }
        }

    return xShipPosition.absoluteValue + yShipPosition.absoluteValue
}

fun Pair<Int, Int>.part2Rotate(instruction: Pair<Char, Int>): Pair<Int, Int> =
    when ("${instruction.first}${instruction.second}") {
        "L90", "R270" -> Pair(-second, first)
        "L180", "R180" -> Pair(-first, -second)
        "R90", "L270" -> Pair(second, -first)
        else -> {
            throw IllegalArgumentException("${instruction.first}${instruction.second} not known")
        }
    }


fun List<String>.day12Part1(): Int {
    var direction = 'E'
    var xPosition = 0
    var yPosition = 0

    this.map { it.toInstruction() }
        .forEach {
//            println("Next Instruction: $it")
            when (it.first) {
                'E' -> xPosition += it.second
                'S' -> yPosition -= it.second
                'W' -> xPosition -= it.second
                'N' -> yPosition += it.second

                'L' -> when (it.second) {
                    90 -> when (direction) {
                        'E' -> direction = 'N'
                        'S' -> direction = 'E'
                        'W' -> direction = 'S'
                        'N' -> direction = 'W'
                    }
                    180 -> when (direction) {
                        'E' -> direction = 'W'
                        'S' -> direction = 'N'
                        'W' -> direction = 'E'
                        'N' -> direction = 'S'
                    }
                    270 -> when (direction) {
                        'E' -> direction = 'S'
                        'S' -> direction = 'W'
                        'W' -> direction = 'N'
                        'N' -> direction = 'E'
                    }
                }

                'R' -> when (it.second) {
                    90 -> when (direction) {

                        'E' -> direction = 'S'
                        'S' -> direction = 'W'
                        'W' -> direction = 'N'
                        'N' -> direction = 'E'
                    }
                    180 -> when (direction) {
                        'E' -> direction = 'W'
                        'S' -> direction = 'N'
                        'W' -> direction = 'E'
                        'N' -> direction = 'S'
                    }
                    270 -> when (direction) {
                        'E' -> direction = 'N'
                        'S' -> direction = 'E'
                        'W' -> direction = 'S'
                        'N' -> direction = 'W'
                    }
                }
                'F' -> {
                    when (direction) {
                        'E' -> xPosition += it.second
                        'S' -> yPosition -= it.second
                        'W' -> xPosition -= it.second
                        'N' -> yPosition += it.second
                    }
                }
            }
        }

    return xPosition.absoluteValue + yPosition.absoluteValue
}


private fun String.toInstruction(): Pair<Char, Int> = Pair(this[0], this.drop(1).toInt())