package sh.weller.aoc

import kotlin.math.abs

object Day01 : SomeDay<List<Long>, Long> {

    override fun partOne(input: List<List<Long>>): Long {
        val leftList = input.first().sorted()
        val rightList = input.last().sorted()

        return leftList.zip(rightList)
            .sumOf { (first, second) ->
                abs(first - second)
            }
    }

    override fun partTwo(input: List<List<Long>>): Long {
        val leftList = input.first().sorted()
        val rightList = input.last().sorted()

        return leftList.sumOf { number ->
            rightList.count { it == number } * number
        }
    }
}