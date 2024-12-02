package sh.weller.aoc

import kotlin.math.abs

object Day02 : SomeDay<Int> {

    override val day: Int = 2

    private fun List<String>.parseData(): List<List<Int>> = map { report ->
        report.split(" ").map { it.toInt() }
    }

    override fun partOne(input: List<String>): Int {
        val reports = input.parseData()

        return reports
            .count {
                it.isSafe()
            }
    }

    private fun List<Int>.isSafe(): Boolean {
        val distanceIsSafe = windowed(2)
            .all { (a, b) -> abs(a - b) in 1..3 }

        val sortedMatch = sorted() == this
        val reverseSortedMatch = sorted().reversed() == this

        return distanceIsSafe && (sortedMatch || reverseSortedMatch)
    }

    override fun partTwo(input: List<String>): Int {
        val reports = input.parseData()

        return reports.count {
            val reportIsSafe = it.isSafe()

            val anyReportWithOneMissingIsSafe = (0..it.size)
                .any { index ->
                    (it.take(index) + it.drop(index + 1)).isSafe()
                }

            reportIsSafe || anyReportWithOneMissingIsSafe
        }
    }
}
