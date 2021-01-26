package sh.weller.adventofcode.twentytwenty

import kotlin.math.absoluteValue


fun List<String>.day12Part2(): Int {
    var shipCoordinates = Coordinates(0, 0)
    var waypointCoordinates = Coordinates(10, 1)

    this.map { it.toInstruction() }
        .forEach { instruction ->
            when (instruction.getOperation()) {
                'E' -> waypointCoordinates = waypointCoordinates.addX(instruction.getValue())
                'S' -> waypointCoordinates = waypointCoordinates.subtractY(instruction.getValue())
                'W' -> waypointCoordinates = waypointCoordinates.subtractX(instruction.getValue())
                'N' -> waypointCoordinates = waypointCoordinates.addY(instruction.getValue())
                'L', 'R' -> waypointCoordinates = waypointCoordinates.part2Rotate(instruction)
                'F' -> shipCoordinates = shipCoordinates.moveInDirection(waypointCoordinates, instruction.getValue())
            }
        }

    return shipCoordinates.getX().absoluteValue + shipCoordinates.getY().absoluteValue
}

private fun Coordinates.part2Rotate(instruction: Instruction): Coordinates =
    when ("${instruction.first}${instruction.second}") {
        "L90", "R270" -> Coordinates(-second, first)
        "L180", "R180" -> Coordinates(-first, -second)
        "R90", "L270" -> Coordinates(second, -first)
        else -> {
            throw IllegalArgumentException()
        }
    }

private fun Coordinates.moveInDirection(coordinates: Coordinates, multiplier: Int): Coordinates =
    this.copy(this.getX() + (coordinates.getX() * multiplier), this.getY() + (coordinates.getY() * multiplier))


fun List<String>.day12Part1(): Int {
    var shipCoordinates = Coordinates(0, 0)
    var direction = 'E'

    this.map { it.toInstruction() }
        .forEach { instruction ->
            when (instruction.getOperation()) {
                'E' -> shipCoordinates = shipCoordinates.addX(instruction.getValue())
                'S' -> shipCoordinates = shipCoordinates.subtractY(instruction.getValue())
                'W' -> shipCoordinates = shipCoordinates.subtractX(instruction.getValue())
                'N' -> shipCoordinates = shipCoordinates.addY(instruction.getValue())
                'L', 'R' -> direction = direction.rotate(instruction)
                'F' -> {
                    when (direction) {
                        'E' -> shipCoordinates = shipCoordinates.addX(instruction.getValue())
                        'S' -> shipCoordinates = shipCoordinates.subtractY(instruction.getValue())
                        'W' -> shipCoordinates = shipCoordinates.subtractX(instruction.getValue())
                        'N' -> shipCoordinates = shipCoordinates.addY(instruction.getValue())
                    }
                }
            }
        }
    return shipCoordinates.getX().absoluteValue + shipCoordinates.getY().absoluteValue
}

private fun Char.rotate(instruction: Instruction): Char =
    when ("${instruction.getOperation()}${instruction.getValue()}") {
        "L90", "R270" -> when (this) {
            'E' -> 'N'
            'S' -> 'E'
            'W' -> 'S'
            'N' -> 'W'
            else -> throw IllegalArgumentException()
        }
        "L180", "R180" -> when (this) {
            'E' -> 'W'
            'S' -> 'N'
            'W' -> 'E'
            'N' -> 'S'
            else -> throw IllegalArgumentException()
        }
        "R90", "L270" -> when (this) {
            'E' -> 'S'
            'S' -> 'W'
            'W' -> 'N'
            'N' -> 'E'
            else -> throw IllegalArgumentException()
        }
        else -> throw IllegalArgumentException()
    }


private typealias Coordinates = Pair<Int, Int>

private fun Coordinates.addX(value: Int): Coordinates = this.copy(first = this.first + value)
private fun Coordinates.subtractX(value: Int): Coordinates = this.copy(first = this.first - value)
private fun Coordinates.addY(value: Int): Coordinates = this.copy(second = this.second + value)
private fun Coordinates.subtractY(value: Int): Coordinates = this.copy(second = this.second - value)
private fun Coordinates.getX() = this.first
private fun Coordinates.getY() = this.second

private typealias Instruction = Pair<Char, Int>

private fun String.toInstruction(): Instruction = Instruction(this[0], this.drop(1).toInt())
private fun Instruction.getOperation() = this.first
private fun Instruction.getValue() = this.second
