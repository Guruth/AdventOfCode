package sh.weller.aoc

import sun.misc.Unsafe
import kotlin.math.abs

object Day02 : SomeDay<Int> {

    override val day: Int = 2

    private fun List<String>.parseData(): List<List<Int>> = map { report ->
        report.split(" ").map { it.toInt() }
    }

    override fun partOne(input: List<String>): Int {
        val reports = input.parseData()

        return reports.count {
            it.isSafe()
        }
    }

    private fun List<Int>.isSafe(): Boolean {
        return windowed(2)
            .map { (first, second) ->
                if (abs(first - second) !in 1..3) {
                    return@map Direction.Unsafe
                }
                if (first > second) {
                    return@map Direction.Dec
                }
                if (second > first()) {
                    return@map Direction.Inc
                }
                return@map null
            }
            .groupingBy { it }.eachCount().size == 1
    }

    override fun partTwo(input: List<String>): Int {

        return -1
    }

}

enum class Direction {
    Inc, Dec, Unsafe;
}