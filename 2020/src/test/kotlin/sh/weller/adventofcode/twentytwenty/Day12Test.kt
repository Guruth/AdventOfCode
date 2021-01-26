package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day12Test {
    private val day = 12
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.day12Part1()
        assertEquals(25, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.day12Part1()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData.day12Part2()
        assertEquals(286, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.day12Part2()
        printResult(day, 2, result)
    }
}

