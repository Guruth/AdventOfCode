package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {
    private val day = 7
    private val testData = fileToList<String>("2020/Day${day}TestData.txt")
    private val testData2 = fileToList<String>("2020/Day${day}TestData2.txt")
    private val realData = fileToList<String>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val test = testData.countBagsWhichContain("shiny gold")
        assertEquals(4, test)
    }

    @Test
    fun partOneReal() {
        val result = realData.countBagsWhichContain("shiny gold")
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData2.countBagsThatareContained("shiny gold")
        assertEquals(126, result)
    }

    @Test
    fun partTwoReal() {
//        val result = realData.
//        printResult(day, 2, result)
    }
}

