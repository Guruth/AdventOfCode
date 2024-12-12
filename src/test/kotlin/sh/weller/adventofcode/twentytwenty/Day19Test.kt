package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day19Test {
    private val day = 19
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val testData2 = fileToList<String>("2020/Day${day}TestData2.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")
    private val realData2 = fileToList<String>("2020/Day${day}DataPart2.txt")

    @Test
    fun partOneTest() {
        val result = testData.day19Part1()
        assertEquals(2, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.day19Part1()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData2.day19Part2()
        assertEquals(12, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData2.day19Part2()
        printResult(day, 2, result)
    }
}

