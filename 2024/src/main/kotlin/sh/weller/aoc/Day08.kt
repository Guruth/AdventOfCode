package sh.weller.aoc

import sh.weller.aoc.util.to2DList

typealias Coordinate = Pair<Int, Int>

val antennaChars = ('a'..'z') + ('A'..'Z') + ('0'..'9')

object Day08 : SomeDay<Long> {

    override val day: Int = 8

    override fun partOne(input: List<String>): Long {
        val map = input.to2DList()
        val antennas = map.getAntennaMap()

        for ((_, coordinates) in antennas) {
            for (coordinate in coordinates) {
                val otherCoordinates = coordinates.toMutableList()
                otherCoordinates.remove(coordinate)
                for (otherCoordinate in otherCoordinates) {
                    val distance = coordinate.getDistance(otherCoordinate)
                    map.markLocationAt(coordinate.first + distance.first to coordinate.second + distance.second)
                }
            }
        }

        return map.flatten().count { it == '#' }.toLong()
    }

    private fun List<List<Char>>.getAntennaMap(): Map<Char, List<Coordinate>> {
        val antennas: MutableMap<Char, List<Coordinate>> = mutableMapOf()

        repeat(size) { y ->
            repeat(first().size) { x ->
                val char = this[y][x]
                if (antennaChars.contains(char)) {
                    val antennasOfType = antennas.getOrDefault(char, emptyList())
                    antennas[char] = antennasOfType + (y to x)
                }
            }
        }

        return antennas
    }

    private fun Coordinate.getDistance(other: Coordinate): Coordinate =
        first - other.first to second - other.second

    private fun MutableList<MutableList<Char>>.markLocationAt(coordinate: Coordinate): Boolean {
        val (y, x) = coordinate
        if (y in 0..<size && (x in 0..<size)) {
            this[y][x] = '#'
            return true
        }
        return false
    }

    override fun partTwo(input: List<String>): Long {
        val map = input.to2DList()
        val antennas = map.getAntennaMap()

        for ((_, coordinates) in antennas) {
            for (coordinate in coordinates) {
                val otherCoordinates = coordinates.toMutableList()
                otherCoordinates.remove(coordinate)
                for (otherCoordinate in otherCoordinates) {
                    val distance = coordinate.getDistance(otherCoordinate)
                    var calcDistance = distance
                    var wasAddedA: Boolean
                    var wasAddedB: Boolean
                    do {
                        wasAddedA =
                            map.markLocationAt((coordinate.first + calcDistance.first) to (coordinate.second + calcDistance.second))
                        wasAddedB =
                            map.markLocationAt((coordinate.first - calcDistance.first) to (coordinate.second - calcDistance.second))

                        calcDistance = (calcDistance.first + distance.first) to (calcDistance.second + distance.second)
                    } while (wasAddedA || wasAddedB)
                }
            }
        }
        return map.flatten().count { it == '#' }.toLong()
    }
}
