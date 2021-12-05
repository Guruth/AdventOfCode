package sh.weller.aoc

import kotlin.math.abs

object Day05 : SomeDay<CoordinatePair, Int> {
    override fun partOne(input: List<CoordinatePair>): Int {
        val size = input.maxOf { it.maxOf() }
        val map: HeightMap = (0..size).map { (0..size).map { 0 }.toMutableList() }.toMutableList()

        for (coordinatePair in input) {
            if (coordinatePair.isHorizontalLine() || coordinatePair.isVerticalLine()) {
                map.markLinePartOne(coordinatePair)
            }
        }

        return map.flatten().count { it >= 2 }
    }

    @JvmName("maxOfIntInt")
    private fun Coordinates.maxOf(): Int = maxOf(first, second)

    @JvmName("maxOfIntIntIntInt")
    private fun CoordinatePair.maxOf(): Int = maxOf(first.maxOf(), second.maxOf())

    private fun HeightMap.markLinePartOne(coordinatePair: CoordinatePair) {
        if (coordinatePair.isHorizontalLine()) {
            val startX = minOf(coordinatePair.first.first, coordinatePair.second.first)
            val endX = maxOf(coordinatePair.first.first, coordinatePair.second.first)
            repeat(endX - startX + 1) {
                this[coordinatePair.first.second][startX + it] = this[coordinatePair.first.second][startX + it] + 1
            }
        } else {
            val startY = minOf(coordinatePair.first.second, coordinatePair.second.second)
            val endY = maxOf(coordinatePair.first.second, coordinatePair.second.second)
            repeat(endY - startY + 1) {
                this[startY + it][coordinatePair.first.first] = this[startY + it][coordinatePair.first.first] + 1
            }
        }
    }

    private fun CoordinatePair.isHorizontalLine() =
        first.second == second.second

    private fun CoordinatePair.isVerticalLine() =
        first.first == second.first

    override fun partTwo(input: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>): Int {
        val size = input.maxOf { it.maxOf() }
        val map: HeightMap = (0..size).map { (0..size).map { 0 }.toMutableList() }.toMutableList()

        for (coordinatePair in input) {
            if (coordinatePair.isHorizontalLine() || coordinatePair.isVerticalLine() || coordinatePair.isDiagonalLine()) {
                map.markLinePartTwo(coordinatePair)
            }
        }
        return map.flatten().count { it >= 2 }
    }

    private fun HeightMap.markLinePartTwo(coordinatePair: CoordinatePair) {
        if (coordinatePair.isDiagonalLine()) {
            val steps = abs(coordinatePair.second.first - coordinatePair.first.first) + 1
            if (coordinatePair.first.first > coordinatePair.second.first && coordinatePair.first.second > coordinatePair.second.second) {
//            ((3,3),(1,1))
                repeat(steps) {
                    this[coordinatePair.first.second - it][coordinatePair.first.first - it] =
                        this[coordinatePair.first.second - it][coordinatePair.first.first - it] + 1
                }
            } else if (coordinatePair.first.first < coordinatePair.second.first && coordinatePair.first.second < coordinatePair.second.second) {
//            ((1,1),(3,3))
                repeat(steps) {
                    this[coordinatePair.first.second + it][coordinatePair.first.first + it] =
                        this[coordinatePair.first.second + it][coordinatePair.first.first + it] + 1
                }
            } else if (coordinatePair.first.first > coordinatePair.second.first && coordinatePair.first.second < coordinatePair.second.second) {
//            ((9,7),(7,9))
                repeat(steps) {
                    this[coordinatePair.first.second + it][coordinatePair.first.first - it] =
                        this[coordinatePair.first.second + it][coordinatePair.first.first - it] + 1
                }
            } else {
//            ((7,9),(9,7))
                repeat(steps) {
                    this[coordinatePair.first.second - it][coordinatePair.first.first + it] =
                        this[coordinatePair.first.second - it][coordinatePair.first.first + it] + 1
                }
            }
        } else {
            this.markLinePartOne(coordinatePair)
        }
    }

    private fun CoordinatePair.isDiagonalLine(): Boolean =
        isHorizontalLine().not() && isVerticalLine().not() && (abs(first.first - second.first) == abs(first.second - second.second))

}

private typealias CoordinatePair = Pair<Coordinates, Coordinates>
private typealias Coordinates = Pair<Int, Int>
private typealias HeightMap = MutableList<MutableList<Int>>