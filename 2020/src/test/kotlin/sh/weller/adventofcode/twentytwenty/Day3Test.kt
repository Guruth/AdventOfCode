package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.fileTo2DList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun partOneTest() {
        val testData = fileTo2DList("2020/Day3TestData.txt")

        val testPattern = listOf(Pair(Direction.RIGHT, 3), Pair(Direction.DOWN, 1))
        val result = testData.countTreesWhileNavigating(testPattern)

        assertEquals(7, result)
    }

    @Test
    fun partOneReal() {
        val data = fileTo2DList("2020/Day3Data.txt")
        val pattern = listOf(Pair(Direction.RIGHT, 3), Pair(Direction.DOWN, 1))

        val result = data.countTreesWhileNavigating(pattern)

        printResult(3, 1, result)
    }

    @Test
    fun partTwoTest() {
        val testData = fileTo2DList("2020/Day3TestData.txt")

        val patterns = listOf(
            listOf(Pair(Direction.RIGHT, 1), Pair(Direction.DOWN, 1)),
            listOf(Pair(Direction.RIGHT, 3), Pair(Direction.DOWN, 1)),
            listOf(Pair(Direction.RIGHT, 5), Pair(Direction.DOWN, 1)),
            listOf(Pair(Direction.RIGHT, 7), Pair(Direction.DOWN, 1)),
            listOf(Pair(Direction.RIGHT, 1), Pair(Direction.DOWN, 2))
        )

        val resultOfAll = testData.countTreesWhileNavigatingForMultiple(patterns)

        assertEquals(336, resultOfAll)
    }

    @Test
    fun partTwoReal() {
        val data = fileTo2DList("2020/Day3Data.txt")

        val patterns = listOf(
            listOf(Pair(Direction.RIGHT, 1), Pair(Direction.DOWN, 1)),
            listOf(Pair(Direction.RIGHT, 3), Pair(Direction.DOWN, 1)),
            listOf(Pair(Direction.RIGHT, 5), Pair(Direction.DOWN, 1)),
            listOf(Pair(Direction.RIGHT, 7), Pair(Direction.DOWN, 1)),
            listOf(Pair(Direction.RIGHT, 1), Pair(Direction.DOWN, 2))
        )

        val resultOfAll = data.countTreesWhileNavigatingForMultiple(patterns)

        printResult(3, 2, resultOfAll)
    }
}