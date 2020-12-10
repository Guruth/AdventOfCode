package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class Day10Test {
    private val day = 10
    private val testData = fileToList<Int>("2020/Day${day}TestData.txt")
    private val testData2 = fileToList<Int>("2020/Day${day}TestData2.txt")
    private val realData = fileToList<Int>("2020/Day${day}Data.txt")

    @Test
    fun partOneTest() {
        val result = testData2.countJoltDifferences()
        assertEquals(22 * 10, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.countJoltDifferences()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        var result = listOf(1, 2, 3, 4).findPathsSmart()
        assertEquals(7, result)
        // adapters     [0, 1, 2, 3, 4, 7]
        // leafCounter  [1, 1, 2, 4, 7, 7]
        // 0:       0 - []        =>          => Push 1
        // 1:       1 - [0]       =>  1 Edge  => Push 1
        // 2:       2 - [0, 1]    =>  2 Edge  => Push 1 + 1
        // 3:       3 - [0, 1, 2] =>  3 Edges => Push 1 + 1 + 2
        // 4:       4 - [1, 2, 3] =>  3 Edges => Push 1 + 2 + 4
        // 5:       7 - [2, 3, 4] =>  1 Edge  => Push 7

        result = testData.findPathsSmart()
        assertEquals(8, result)

        result = testData2.findPathsSmart()
        assertEquals(19208, result)

        // One Graph
        result = listOf(1, 2, 3, 4).findPathsInGraph().toLong()
        assertEquals(7, result)

        result = testData.findPathsInGraph().toLong()
        assertEquals(8, result)

        result = testData2.findPathsInGraph().toLong()
        assertEquals(19208, result)

        // Split Graphs
        result = listOf(1, 2, 3, 4).findPathsInSplitGraph()
        assertEquals(7, result)

        result = testData.findPathsInSplitGraph()
        assertEquals(8, result)

        result = testData2.findPathsInSplitGraph()
        assertEquals(19208, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.findPathsSmart()
        printResult(day, 2, result)
    }


    @ExperimentalTime
    @Test
    fun partTwoTimeMeasure() {
        val expectedResult = 3543369523456L

        var elapsedTime = measureTime {
            assertEquals(expectedResult, realData.findPathsSmart())
        }
        println("Smart takes $elapsedTime")


        elapsedTime = measureTime {
            assertEquals(expectedResult, realData.findPathsInSplitGraph())
        }
        println("Split Graph takes $elapsedTime")

    }
}

