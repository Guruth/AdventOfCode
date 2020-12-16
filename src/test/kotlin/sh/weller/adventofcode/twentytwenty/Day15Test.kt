package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Assertions.assertEquals
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class Day15Test {
    private val day = 15

    @Test
    fun partOneTest() {
        var result = listOf(0, 3, 6).day15()
        assertEquals(436, result)

        result = listOf(1, 3, 2).day15()
        assertEquals(1, result)

        result = listOf(2, 1, 3).day15()
        assertEquals(10, result)

        result = listOf(1, 2, 3).day15()
        assertEquals(27, result)

        result = listOf(2, 3, 1).day15()
        assertEquals(78, result)

        result = listOf(3, 2, 1).day15()
        assertEquals(438, result)

        result = listOf(3, 1, 2).day15()
        assertEquals(1836, result)

    }

    @Test
    fun partOneReal() {
        val result = listOf(0, 6, 1, 7, 2, 19, 20).day15()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        var result = listOf(0, 3, 6).day15(30000000)
        assertEquals(175594, result)

        result = listOf(1, 3, 2).day15(30000000)
        assertEquals(2578, result)

        result = listOf(2, 1, 3).day15(30000000)
        assertEquals(3544142, result)

        result = listOf(1, 2, 3).day15(30000000)
        assertEquals(261214, result)

        result = listOf(2, 3, 1).day15(30000000)
        assertEquals(6895259, result)

        result = listOf(3, 2, 1).day15(30000000)
        assertEquals(18, result)

        result = listOf(3, 1, 2).day15(30000000)
        assertEquals(362, result)
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun partTwoReal() {
        val elapsedTime = measureTime {
            val result = listOf(0, 6, 1, 7, 2, 19, 20).day15(30000000)
            printResult(day, 2, result)
        }

        println("Took $elapsedTime") // 14.4s
    }
}

