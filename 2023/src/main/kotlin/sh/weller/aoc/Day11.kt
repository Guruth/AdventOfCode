package sh.weller.aoc

import sh.weller.aoc.util.to2DList
import kotlin.math.max
import kotlin.math.min

object Day11 : SomeDay<String, Long> {
    override fun partOne(input: List<String>): Long =
        calculateDistances(2, input.to2DList())

    override fun partTwo(input: List<String>): Long =
        calculateDistances(1000000, input.to2DList())

    private fun calculateDistances(expandFactor: Long, grid: List<List<Char>>): Long {
        val emptyRows = grid.mapIndexedNotNull { index, chars ->
            if (chars.all { it == '.' }) {
                index
            } else {
                null
            }
        }

        val emptyColumns = mutableListOf<Int>()
        for (i in 0..<grid.first().size) {
            val column = grid.map { it[i] }
            if (column.all { it == '.' }) {
                emptyColumns.add(i)
            }
        }


        val stars: MutableList<Pair<Int, Int>> = mutableListOf()
        for ((rowIndex, row) in grid.withIndex()) {
            for ((colIndex, c) in row.withIndex()) {
                if (c == '#') {
                    stars.add(rowIndex to colIndex)
                }
            }
        }

        val distances = mutableListOf<Long>()
        for ((index, star) in stars.withIndex()) {
            val otherStars = stars.drop(index + 1)
            for (other in otherStars) {
                val minRow = min(star.first, other.first)
                val maxRow = max(star.first, other.first)

                val minCol = min(star.second, other.second)
                val maxCol = max(star.second, other.second)

                val emptyRowsBetween = emptyRows.count { it in minRow..maxRow }
                val expandedRows = emptyRowsBetween * (expandFactor - 1)
                val emptyColsBetween = emptyColumns.count { it in minCol..maxCol }
                val expandedCols = emptyColsBetween * (expandFactor - 1)

                val rowDistance = maxRow + expandedRows - minRow
                val colDistance = maxCol + expandedCols - minCol

                distances.add(rowDistance + colDistance)
            }
        }

        return distances.sum()
    }
}