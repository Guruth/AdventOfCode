package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {
    private val day = 21
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.day21Part1()
        assertEquals(5, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.day21Part1()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData.day21Part2()
        assertEquals("mxmxvkd,sqjhc,fvjkl", result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.day21Part2()
        printResult(day, 2, result)
    }
}

