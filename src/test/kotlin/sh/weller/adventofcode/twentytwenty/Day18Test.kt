package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day18Test {
    private val day = 18
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        var result = listOf("1 + 2 * 3 + 4 * 5 + 6").day18Part1()
        assertEquals(71, result)
        result = listOf("1 + (2 * 3) + (4 * (5 + 6))").day18Part1()
        assertEquals(51, result)
        result = listOf("2 * 3 + (4 * 5)").day18Part1()
        assertEquals(26, result)
        result = listOf("5 + (8 * 3 + 9 + 3 * 4 * 3)").day18Part1()
        assertEquals(437, result)
        result = listOf("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))").day18Part1()
        assertEquals(12240, result)
        result = listOf("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2 ").day18Part1()
        assertEquals(13632, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.day18Part1()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        var result = listOf("1 + 2 * 3 + 4 * 5 + 6").day18Part2()
        assertEquals(231, result)
        result = listOf("1 + (2 * 3) + (4 * (5 + 6))").day18Part2()
        assertEquals(51, result)
        result = listOf("2 * 3 + (4 * 5)").day18Part2()
        assertEquals(46, result)
        result = listOf("5 + (8 * 3 + 9 + 3 * 4 * 3)").day18Part2()
        assertEquals(1445, result)
        result = listOf("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))").day18Part2()
        assertEquals(669060, result)
        result = listOf("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2 ").day18Part2()
        assertEquals(23340, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.day18Part2()
        printResult(day, 2, result)
    }
}

