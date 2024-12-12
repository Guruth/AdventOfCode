package sh.weller.adventofcode.twentytwenty

import org.junit.jupiter.api.Test
import util.fileToList
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

        println("Result Part One: $result")
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

        print("Result Part Two: $result")
    }
}