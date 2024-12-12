package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.fileTo2DList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Test {
    private val day = 20
    private val testData = fileTo2DList("2020/Day${day}TestData.txt")
    private val realData = fileTo2DList("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.day20Part1()
        assertEquals(20899048083289L, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.day20Part1()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
//        val result = testData.
//        assertEquals(0, result)
    }

    @Test
    fun partTwoReal() {
//        val result = realData.
//        printResult(day, 2, result)
    }
}

