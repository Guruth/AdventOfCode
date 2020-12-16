package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {
    private val day = 16
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val testData2 = fileToList<String>("2020/Day${day}TestData2.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.day16Part1()
        assertEquals(71, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.day16Part1()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData2.day16Part2()
        assertEquals(0, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.day16Part2()
        printResult(day, 2, result)
    }
}

