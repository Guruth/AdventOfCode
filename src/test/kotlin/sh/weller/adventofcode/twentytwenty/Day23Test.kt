package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.printResult
import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {
    private val day = 23

    @Test
    fun partOneTest() {
        var result = listOf(3, 8, 9, 1, 2, 5, 4, 6, 7).day23Part1(10)
        assertEquals("92658374", result)

        result = listOf(3, 8, 9, 1, 2, 5, 4, 6, 7).day23Part1()
        assertEquals("67384529", result)
    }

    @Test
    fun partOneReal() {
        val result = listOf(2, 4, 7, 8, 1, 9, 3, 5, 6).day23Part1()
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = listOf(3, 8, 9, 1, 2, 5, 4, 6, 7).day23Part2(10)
        assertEquals(149245887792, result)
    }

    @Test
    fun partTwoReal() {
        val result = listOf(2, 4, 7, 8, 1, 9, 3, 5, 6).day23Part2()
        printResult(day, 2, result)
    }
}

