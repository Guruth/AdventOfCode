package sh.weller.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sh.weller.aoc.util.readFile

abstract class SomeDayTest<In, Out>(
    private val day: Int,
    private val someDay: SomeDay<In, Out>
) {

    private val rawTestData = readFile("day$day/test_data")
    private val rawData = readFile("day$day/data")

    abstract fun List<String>.mapData(): List<In>

    abstract val resultTest1 :Out
    abstract val resultTest2 :Out

    @Test
    fun partOneTest() {
        val result = someDay.partOne(rawTestData.mapData())
        assertEquals(resultTest1, result)
    }

    @Test
    fun partOneReal() {
        val result = someDay.partOne(rawData.mapData())
        printResult(day, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = someDay.partTwo(rawTestData.mapData())
        assertEquals(resultTest2, result)
    }

    @Test
    fun partTwoReal() {
        val result = someDay.partTwo(rawData.mapData())
        printResult(day, 2, result)
    }

    private fun printResult(day: Int, part: Int, result: Out) {
        println("Result Day $day-$part: $result")
    }
}