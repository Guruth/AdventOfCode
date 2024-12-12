package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day6Test {
    private val day = 6
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.countCommonAnswersInGroup()
        assertEquals(11, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.countCommonAnswersInGroup()
        printResult(6, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData.countAllYesAnswersInGroup()
        assertEquals(6, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.countAllYesAnswersInGroup()
        printResult(6, 2, result)
    }
}

