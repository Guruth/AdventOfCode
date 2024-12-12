package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day13Test {
    private val day = 13
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.day13Part1()
        assertEquals(295, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.day13Part1()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData.day13Part2()
        assertEquals(0, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.day13Part2()
        printResult(day, 2, result)
    }
}

