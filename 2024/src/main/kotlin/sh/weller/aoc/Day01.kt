package sh.weller.aoc

import kotlin.math.abs

object Day01 : SomeDay<Long> {

    override val day: Int = 1

    private fun List<String>.parseData(): Pair<List<Long>, List<Long>> {
        val leftList = map { it.split(" ").first().trim().toLong() }.sorted()
        val rightList = map { it.split(" ").last().trim().toLong() }.sorted()

        return leftList to rightList
    }

    override fun partOne(input: List<String>): Long {
        val (leftList, rightList) = input.parseData()

        return leftList.zip(rightList)
            .sumOf { (first, second) ->
                abs(first - second)
            }
    }

    override fun partTwo(input: List<String>): Long {
        val (leftList, rightList) = input.parseData()

        return leftList.sumOf { number ->
            rightList.count { it == number } * number
        }
    }
}