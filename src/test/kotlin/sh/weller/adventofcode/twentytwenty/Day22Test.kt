package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Test {
    private val day = 22
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.day22Part1()
        assertEquals(306, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.day22Part1()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData.day22Part2()
        assertEquals(291, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.day22Part2()
        printResult(day, 2, result)
    }
}

