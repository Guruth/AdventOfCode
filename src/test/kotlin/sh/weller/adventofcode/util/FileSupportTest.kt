package sh.weller.adventofcode.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import util.fileToList

internal class FileSupportTest {
    @Test
    fun fileToIntList() {
        val expectedData = listOf(
            123,
            1123,
            12313,
            123
        )

        val result = fileToList<Int>("util/intList.txt")
        Assertions.assertEquals(expectedData, result)
    }

    @Test
    fun fileToDoubleList() {
        val expectedData = listOf(
            123.0,
            1123.0,
            12313.34,
            123.0
        )

        val result = fileToList<Double>("util/doubleList.txt")
        Assertions.assertEquals(expectedData, result)
    }


    @Test
    fun fileToInvalidList() {
        val expectedData = listOf(
            123,
        )

        val result = fileToList<Int>("util/invalidList.txt")
        Assertions.assertEquals(expectedData, result)
    }

    @Test
    fun notFoundList() {
        val expectedData = emptyList<Any>()
        val result = fileToList<Int>("util/foo.txt")
        Assertions.assertEquals(expectedData, result)
    }
}