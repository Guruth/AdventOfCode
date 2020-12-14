package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day14Test {
    private val day = 14
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val testData2 = fileToList<String>("2020/Day${day}TestData2.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.day14Part1()
        assertEquals(165, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.day14Part1()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData2.day14Part2()
        assertEquals(208, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.day14Part2()
        printResult(day, 2, result)
    }
}

