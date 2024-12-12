package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test

class Day8Test {
    private val day = 8
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.executeInstructions()
        assertEquals(5, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.executeInstructions()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData.executeMutatedInstructions()
        assertEquals(8, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.executeMutatedInstructions()
        printResult(day, 2, result)
    }
}

