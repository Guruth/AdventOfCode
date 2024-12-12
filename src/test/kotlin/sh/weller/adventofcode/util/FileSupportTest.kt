package sh.weller.adventofcode.util

import org.junit.jupiter.api.Test
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
        val expectedData = listOf(
            123,
        )

        val result = fileToList<Int>("util/invalidList.txt")
        assertEquals(expectedData, result)
    }

    @Test
    fun notFoundList() {
        val expectedData = emptyList<Any>()
        val result = fileToList<Int>("util/foo.txt")
        assertEquals(expectedData, result)
    }

    @Test
    fun fileTo2dListTest() {
        val expectedData = listOf(
            listOf('*', '#', '*'),
            listOf('*', '*', '*'),
            listOf('*', '*', '#')
        )
        val result = fileTo2DList("util/2dList.txt")
        assertEquals(expectedData, result)
    }
}