package sh.weller.adventofcode.twentytwenty

import getMultipliedForSumOfDepth
import org.junit.jupiter.api.Test
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.assertEquals

internal class Day1Test {
    @Test
    fun firstPartTest() {
        val testData = listOf(
            1721,
            979,
            366,
            299,
            675,
            1456
        )
        val testSum = 2020
        val expectedResult = 514579L

        val result = testData.getMultipliedForSumOfDepth(testSum, 2)

        assertEquals(expectedResult, result)
    }

    @Test
    fun firstPartWithRealData() {
        val data = fileToList<Int>("2020/Day1Data.txt")
        val result = data.getMultipliedForSumOfDepth(2020, 2)

        printResult(1,1,result)
    }


    @Test
    fun secondPartTest() {
        val testData = listOf(
            1721,
            979,
            366,
            299,
            675,
            1456
        )
        val testSum = 2020
        val expectedResult = 241861950L

        val result = testData.getMultipliedForSumOfDepth(testSum, 3)

        assertEquals(expectedResult, result)
    }

    @Test
    fun secondPartWithRealData() {
        val data = fileToList<Int>("2020/Day1Data.txt")
        val result = data.getMultipliedForSumOfDepth(2020, 3)

        printResult(1,2,result)
    }

}