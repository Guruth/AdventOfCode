package sh.weller.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import sh.weller.aoc.util.readFile

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
abstract class SomeDayTest<Out : Number>(
    private val someDay: SomeDay<Out>
) {

    private val rawTestData = readFile("day${someDay.day}/test_data")
    private val rawData = readFile("day${someDay.day}/data")

    abstract val resultTest1: Out
    abstract val resultTest2: Out

    @Test
    @Order(0)
    fun partOneTest() {
        val result = someDay.partOne(rawTestData)
        assertEquals(resultTest1, result)
    }

    @Test
    @Order(1)
    fun partOneReal() {
        val result = someDay.partOne(rawData)
        printResult(1, result)
    }

    @Test
    @Order(2)
    fun partTwoTest() {
        val result = someDay.partTwo(rawTestData)
        assertEquals(resultTest2, result)
    }

    @Test
    @Order(3)
    fun partTwoReal() {
        val result = someDay.partTwo(rawData)
        printResult(2, result)
    }

    private fun printResult(part: Int, result: Out) {
        println("Result Day ${someDay.day}-$part: $result")
    }
}