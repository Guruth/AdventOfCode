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

    @Test
    fun rotateRight() {
        val list = listOf(
            listOf(1, 2, 3),
            listOf(1, 2, 3),
            listOf(1, 2, 3)
        )

        val rotatedList = list.rotateRight()

        for (line in rotatedList) {
            assert(line.all { it == line.first() })
        }
    }

    @Test
    fun rotateLeft() {
        val list = listOf(
            listOf(1, 2, 3),
            listOf(1, 2, 3),
            listOf(1, 2, 3)
        )

        val rotatedList = list.rotateLeft()

        for (line in rotatedList) {
            assert(line.all { it == line.first() })
        }
    }


    @Test
    fun getCycledElementAt() {
        // Given
        val listWithCycle = listOf(3, 3, 3, 1, 2, 4, 1, 2, 4, 1, 2, 4, 1, 2, 4, 1, 2, 4)

        // When
        val elementAtPositionNine = listWithCycle.getCycledElementAt(100, 2)

        // Then
        assertEquals(1, elementAtPositionNine)
    }
}