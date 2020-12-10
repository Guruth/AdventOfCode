package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day10Test {
    private val day = 10
    private val testData = fileToList<Int>("2020/Day${day}TestData.txt")
    private val realData = fileToList<Int>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.countJoltDiffereneces()
        assertEquals(22*10, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.countJoltDiffereneces()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
//        val result = testData
//        assertEquals(8, result)
    }

    @Test
    fun partTwoReal() {
//        val result = realData.
//        printResult(day, 2, result)
    }
}

