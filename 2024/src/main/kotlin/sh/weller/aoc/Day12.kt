package sh.weller.aoc

import sh.weller.aoc.Day12.getSides
import sh.weller.aoc.util.CharMap
import sh.weller.aoc.util.to2DList

typealias GardenMap = MutableList<MutableList<Pair<Boolean, Char>>>

object Day12 : SomeDay<Int> {

    override val day: Int = 12

    override fun partOne(input: List<String>): Int {
        return input.to2DList()
            .toGardenMap()
            .getClusters()
            .sumOf { spots ->
                val area = spots.size
                val perimeter = spots.getPerimeter()

                area * perimeter
            }
    }

    private fun CharMap.toGardenMap(): GardenMap =
        map { row -> row.map { Pair(false, it) }.toMutableList() }.toMutableList()

    private fun GardenMap.getClusters(): List<List<Pair<Int, Int>>> {
        val clusters = mutableListOf<List<Pair<Int, Int>>>()
        for ((y, row) in withIndex()) {
            for ((x, spot) in row.withIndex()) {
                val (visited, flower) = spot
                if (!visited) {
                    val cluster = this.getNotVisitedNeighborsOf(y, x, flower)
                    clusters.add(cluster)
                }
            }
        }
        return clusters
    }

    private fun GardenMap.getNotVisitedNeighborsOf(y: Int, x: Int, checkFlower: Char): List<Pair<Int, Int>> {
        val (visited, flower) = this.getOrNull(y)?.getOrNull(x)
            ?: return emptyList()
        if (!visited && flower == checkFlower) {
            this[y][x] = true to checkFlower

            return listOf(y to x) +
                    this.getNotVisitedNeighborsOf(y - 1, x, checkFlower) +
                    this.getNotVisitedNeighborsOf(y, x + 1, checkFlower) +
                    this.getNotVisitedNeighborsOf(y + 1, x, checkFlower) +
                    this.getNotVisitedNeighborsOf(y, x - 1, checkFlower)

        } else {
            return emptyList()
        }
    }


    private fun List<Pair<Int, Int>>.getPerimeter(): Int {
        var perimeter = 0
        for ((y, x) in this) {
            if (this.contains(y - 1 to x).not()) {
                perimeter += 1
            }
            if (this.contains(y to x + 1).not()) {
                perimeter += 1
            }
            if (this.contains(y + 1 to x).not()) {
                perimeter += 1
            }
            if (this.contains(y to x + 1).not()) {
                perimeter += 1
            }
        }
        return perimeter
    }

    override fun partTwo(input: List<String>): Int {
        return input.to2DList()
            .toGardenMap()
            .getClusters()
            .sumOf { spots ->
                val area = spots.size
                val sides = spots.getSides()

                area * sides
            }
    }

    private fun List<Pair<Int, Int>>.getSides(): Int {
        val upSides = this.groupBy { it.first }
            .values
            .sumOf { sameY ->
                var sides = 0
                var previousHadEdge = false
                sameY.sortedBy { it.second }
                    .forEach { (y, x) ->
                        if (this.contains(y - 1 to x).not()) {
                            previousHadEdge = true
                            if (this.contains(y to x + 1).not()) {
                                sides++
                                previousHadEdge = false
                            }
                        } else {
                            if (previousHadEdge) {
                                sides++
                            }
                            previousHadEdge = false
                        }
                    }
                sides
            }

        val downSides = this.groupBy { it.first }
            .values
            .sumOf { sameY ->
                var sides = 0
                var previousHadEdge = false
                val sorted = sameY.sortedBy { it.second }
                sorted.forEach { (y, x) ->
                    if (this.contains(y + 1 to x).not()) {
                        previousHadEdge = true
                        if (this.contains(y to x + 1).not()) {
                            sides++
                            previousHadEdge = false
                        }
                    } else {
                        if (previousHadEdge) {
                            sides++
                        }
                        previousHadEdge = false
                    }
                }
                sides
            }

        val leftSides = this.groupBy { it.second }
            .values
            .sumOf { sameX ->
                var sides = 0
                var previousHadEdge = false
                sameX.sortedBy { it.first }
                    .forEach { (y, x) ->
                        if (this.contains(y to x - 1).not()) {
                            previousHadEdge = true
                            if (this.contains(y + 1 to x).not()) {
                                sides++
                                previousHadEdge = false
                            }
                        } else {
                            if (previousHadEdge) {
                                sides++
                            }
                            previousHadEdge = false
                        }
                    }
                sides
            }

        val rightSides = this.groupBy { it.second }
            .values
            .sumOf { sameX ->
                var sides = 0
                var previousHadEdge = false

                sameX.sortedBy { it.first }
                    .forEach { (y, x) ->
                        if (this.contains(y to x + 1).not()) {
                            previousHadEdge = true
                            if (this.contains(y + 1 to x).not()) {
                                sides++
                                previousHadEdge = false
                            }
                        } else {
                            if (previousHadEdge) {
                                sides++
                            }
                            previousHadEdge = false
                        }
                    }
                sides
            }

        return upSides + downSides + leftSides + rightSides
    }
}
