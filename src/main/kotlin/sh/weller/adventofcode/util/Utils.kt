package sh.weller.adventofcode.util

fun List<Int>.multipliedSum(): Long =
    this
        .map { it.toLong() }
        .reduce { i, j -> i * j }

fun List<Long>.multipliedSumLong(): Long =
    this
        .map { it }
        .reduce { i, j -> i * j }

fun printResult(day: Int, part: Int, result: Any) {
    println("Result Day $day-$part: $result")
}