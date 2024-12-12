package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {
    val testData = fileToList<String>("2020/Day5TestData.txt")
    val realData = fileToList<String>("2020/Day5Data.txt")

    @Test
    fun partOneTest() {
        val testData = listOf(
            "BFFFBBFRRR",
            "FFFBBBFRRR",
            "BBFFBBFRLL"
        )
        val expectedResult = listOf(
            567,
            119,
            820
        )

        val result = testData.toSeatId()
        assertEquals(expectedResult, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.findHighestSeatId()
        printResult(5, 1, result)
    }

    @Test
    fun partTwoTest() {
//        val result =
//        assertEquals(0, result)
    }

    @Test
    fun partTwoReal() {
        realData.printEmptySeats()
    }
}

