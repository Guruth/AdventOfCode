package sh.weller.aoc

import sh.weller.aoc.util.to2DList

object Day10 : SomeDay<Int> {

    override val day: Int = 10

    override fun partOne(input: List<String>): Int {
        val map = input.to2DList()

        var trailHeads = 0

        repeat(map.size) { y ->
            repeat(map.first().size) { x ->
                if (map[y][x] == '0') {
                    trailHeads += map.searchTrailHeads(y, x)
                }
            }
        }
        return trailHeads
    }

    private fun List<List<Char>>.searchTrailHeads(y: Int, x: Int): Int {
        val allTrailHeads = searchTrailHeads(y - 1, x, 0) +
                searchTrailHeads(y, x + 1, 0) +
                searchTrailHeads(y + 1, x, 0) +
                searchTrailHeads(y, x - 1, 0)

        val uniqueTrailHeads = allTrailHeads.filterNotNull().toSet()

        return uniqueTrailHeads.size
    }

    private fun List<List<Char>>.searchTrailHeads(
        y: Int,
        x: Int,
        lastHeight: Int
    ): List<Pair<Int, Int>?> {
        val currentHeight = getValue(y, x)
        return if (currentHeight == lastHeight + 1) {
            if (currentHeight == 9) {
                listOf(y to x)
            } else {
                searchTrailHeads(y - 1, x, currentHeight) +
                        searchTrailHeads(y, x + 1, currentHeight) +
                        searchTrailHeads(y + 1, x, currentHeight) +
                        searchTrailHeads(y, x - 1, currentHeight)
            }
        } else {
            listOf(null)
        }
    }

    private fun List<List<Char>>.getValue(y: Int, x: Int): Int? =
        this.getOrNull(y)?.getOrNull(x)?.let {
            if (it != '.') {
                it.digitToInt()
            } else {
                null
            }
        }


    override fun partTwo(input: List<String>): Int {
        val map = input.to2DList()

        var trailHeads = 0

        repeat(map.size) { y ->
            repeat(map.first().size) { x ->
                if (map[y][x] == '0') {
                    trailHeads += map.searchDistinctTrailHeads(y, x)
                }
            }
        }
        return trailHeads
    }


    private fun List<List<Char>>.searchDistinctTrailHeads(y: Int, x: Int): Int {
        val allTrailHeads = searchDistinctTrailHeads(y - 1, x, 0) +
                searchDistinctTrailHeads(y, x + 1, 0) +
                searchDistinctTrailHeads(y + 1, x, 0) +
                searchDistinctTrailHeads(y, x - 1, 0)

        return allTrailHeads.sum()
    }

    private fun List<List<Char>>.searchDistinctTrailHeads(
        y: Int,
        x: Int,
        lastHeight: Int
    ): List<Int> {
        val currentHeight = getValue(y, x)
        return if (currentHeight == lastHeight + 1) {
            if (currentHeight == 9) {
                listOf(1)
            } else {
                searchDistinctTrailHeads(y - 1, x, currentHeight) +
                        searchDistinctTrailHeads(y, x + 1, currentHeight) +
                        searchDistinctTrailHeads(y + 1, x, currentHeight) +
                        searchDistinctTrailHeads(y, x - 1, currentHeight)
            }
        } else {
            listOf(0)
        }
    }
}
