package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day10Test {
    private val day = 10
    private val testData = fileToList<Int>("2020/Day${day}TestData.txt")
    private val testData2 = fileToList<Int>("2020/Day${day}TestData2.txt")
    private val realData = fileToList<Int>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.countJoltDiffereneces()
        assertEquals(22 * 10, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.countJoltDiffereneces()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        var result = listOf(1, 2, 3, 4).findPossibleVariations()
        assertEquals(7, result)

        result = testData.findPossibleVariations()
        assertEquals(8, result)

        result = testData2.findPossibleVariations()
        assertEquals(19208, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.findPossibleVariations()
        printResult(day, 2, result)
    }
}

