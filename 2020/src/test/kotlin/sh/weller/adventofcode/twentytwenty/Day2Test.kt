package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Test
import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.assertEquals

internal class Day2Test {

    @Test
    fun partOneTest() {
        val data = listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )
        val result = data.validatePasswords(ValidationType.COUNT)
        assertEquals(2, result)
    }

    @Test
    fun partOneReal() {
        val data = fileToList<String>("2020/Day2Data.txt")
        val result = data.validatePasswords(ValidationType.COUNT)

        printResult(2, 1, result)
    }


    @Test
    fun partTwoTest() {
        val data = listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )
        val result = data.validatePasswords(ValidationType.POSITION)
        assertEquals(1, result)
    }

    @Test
    fun partTwoReal() {
        val data = fileToList<String>("2020/Day2Data.txt")
        val result = data.validatePasswords(ValidationType.POSITION)

        printResult(2, 2, result)
    }
}