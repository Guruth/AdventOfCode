package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day17Test {
    private val day = 17
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.day17Part1()
        assertEquals(112, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.day17Part1()
        printResult(day, 1, result) //298
    }

    @Test
    fun partTwoTest() {
        val result = testData.day17Part2()
        assertEquals(848, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.day17Part2()
        printResult(day, 2, result) //1792
    }
}

