package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.fileToList
import sh.weller.adventofcode.util.printResult
import kotlin.test.Test
import kotlin.test.assertEquals

class Day4Test {
    val testData = fileToList<String>("2020/Day4TestData.txt")
    val realData = fileToList<String>("2020/Day4Data.txt")

    @Test
    fun partOneTest() {
        val result = testData.validatePassports(expectedFieldKeys)
        assertEquals(2, result)
    }

    @Test
    fun partOneReal() {
        val result = realData.validatePassports(expectedFieldKeys)
        printResult(4, 1, result)
    }

    @Test
    fun partTwoTest() {
        val result = testData.validatePassportsPartTwo(expectedFieldKeys)
        assertEquals(0, result)
    }

    @Test
    fun partTwoReal() {
        val result = realData.validatePassportsPartTwo(expectedFieldKeys)
        printResult(4, 2, result)
    }
}

val expectedFields = listOf(
    "byr (Birth Year)",
    "iyr (Issue Year)",
    "eyr (Expiration Year)",
    "hgt (Height)",
    "hcl (Hair Color)",
    "ecl (Eye Color)",
    "pid (Passport ID)",
    "cid (Country ID)"
)

val expectedFieldKeys = listOf(
    "byr",
    "iyr",
    "eyr",
    "hgt",
    "hcl",
    "ecl",
    "pid",
//    "cid"
)
