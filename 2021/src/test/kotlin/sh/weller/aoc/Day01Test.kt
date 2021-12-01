package sh.weller.aoc

import sh.weller.aoc.util.fileToList
import sh.weller.aoc.util.printResult
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {
    private val day = 1
    private val testData = fileToList<Int>("Day${day}TestData.txt")
    private val realData = fileToList<Int>("Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = Day01.first(testData)
        assertEquals(7, result)
    }

    @Test
    fun partOneReal() {
        val result = Day01.first(realData)
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = Day01.second(testData)
        assertEquals(5, result)
    }

    @Test
    fun partTwoReal() {
        val result = Day01.second(realData)
        printResult(day, 2, result)
    }
}

