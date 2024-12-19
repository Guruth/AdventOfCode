package sh.weller.aoc

import sh.weller.aoc.Day06.setAt
import sh.weller.aoc.util.aStar
import sh.weller.aoc.util.print
import sh.weller.aoc.util.x
import sh.weller.aoc.util.y

object Day18 : SomeDay<Int> {
    override val day: Int = 18

    private val gridSize = 71
    private val numBytes = 1024

    override fun partOne(input: List<String>): Int {
        val map = MutableList(gridSize) { MutableList(gridSize) { '.' } }
        val memory = input.map { it.split(",").map { it.toInt() } }.map { (x, y) -> y to x }

        for (coordinate in memory.take(numBytes)) {
            map.setAt(coordinate, '#')
        }

        val path = map.aStar(0 to 0, gridSize - 1 to gridSize - 1)

        return (path?.size ?: 0) - 1
    }

    override fun partTwo(input: List<String>): Int {
        val map = MutableList(gridSize) { MutableList(gridSize) { '.' } }
        val memory = input.map { it.split(",").map { it.toInt() } }.map { (x, y) -> y to x }

        for (coordinate in memory) {
            map.setAt(coordinate, '#')
            val path = map.aStar(0 to 0, gridSize - 1 to gridSize - 1)
            if (path == null) {
                println(coordinate.x to coordinate.y)
                return 0
            }
        }

        return -1
    }
}