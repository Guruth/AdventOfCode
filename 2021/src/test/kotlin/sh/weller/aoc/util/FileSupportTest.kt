package sh.weller.aoc.util

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

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
        assertEquals(expectedData, result)
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
        assertEquals(expectedData, result)
    }


    @Test
    fun fileToInvalidList() {
        assertThrows<NumberFormatException> {
            fileToList<Int>("util/invalidList.txt")
        }
    }

    @Test
    fun notFoundList() {
        assertThrows<NullPointerException> {
            fileToList<Int>("util/foo.txt")
        }
    }


}