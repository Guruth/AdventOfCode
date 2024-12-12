package sh.weller.aoc.util

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UtilTest {

    @Test
    fun fileToCharListList() {
        val expectedData = listOf(
            listOf('*', '#', '*'),
            listOf('*', '*', '*'),
            listOf('*', '*', '#')
        )
        val result = readFile("util/2dList.txt").to2DList()
        assertEquals(expectedData, result)
    }
}