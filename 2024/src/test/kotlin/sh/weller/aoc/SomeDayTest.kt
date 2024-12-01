package sh.weller.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import sh.weller.aoc.util.readFile

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
abstract class SomeDayTest<In, Out>(
    private val day: Int,
    private val someDay: SomeDay<In, Out>
) {

    private val rawTestData = readFile("day$day/test_data")
    private val rawData = readFile("day$day/data")

    abstract fun List<String>.mapData(): List<In>

    abstract val resultTest1: Out
    abstract val resultTest2: Out

    @Test
    @Order(0)
    fun partOneTest() {
        val result = someDay.partOne(rawTestData.mapData())
        assertEquals(resultTest1, result)
    }

    @Test
    @Order(1)
    fun partOneReal() {
        val result = someDay.partOne(rawData.mapData())
        printResult(day, 1, result)
    }

    @Test
    @Order(2)
    fun partTwoTest() {
        val result = someDay.partTwo(rawTestData.mapData())
        assertEquals(resultTest2, result)
    }

    @Test
    @Order(3)
    fun partTwoReal() {
        val result = someDay.partTwo(rawData.mapData())
        printResult(day, 2, result)
    }

    private fun printResult(day: Int, part: Int, result: Out) {
        println("Result Day $day-$part: $result")
    }
}