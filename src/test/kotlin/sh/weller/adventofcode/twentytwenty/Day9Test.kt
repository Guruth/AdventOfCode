package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day9Test {
    private val day = 9
    private val testData = fileToList<Int>("2020/Day${day}TestData.txt")
    private val realData = fileToList<Int>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.findInvalidNumber(5)
        assertEquals(127, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.findInvalidNumber(25)
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData. findInvalidNumber2(127)
        assertEquals(62, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.findInvalidNumber2(69316178)
        printResult(day, 2, result)
    }
}

