package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileTo2DList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day11Test {
    private val day = 11
    private val testData = fileTo2DList("2020/Day${day}TestData.txt")
    private val testData2 = fileTo2DList("2020/Day${day}TestData2.txt")
    private val realData = fileTo2DList("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.gameOfSeats()
        assertEquals(37, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.gameOfSeats()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData.gameOfSeatsPartTwo()
        assertEquals(26, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.gameOfSeatsPartTwo()
        printResult(day, 2, result)
    }
}

