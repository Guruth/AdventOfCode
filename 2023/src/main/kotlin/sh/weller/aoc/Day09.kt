package sh.weller.aoc

import kotlin.math.abs

object Day09 : SomeDay<List<Int>, Int> {
    override fun partOne(input: List<List<Int>>): Int {
        return input
            .map(::extrapolate)
            .sum()
    }


    override fun partTwo(input: List<List<Int>>): Int {
        return input
            .map { it.reversed() }
            .map(::extrapolate)
            .sum()
    }


    private fun extrapolate(list: List<Int>): Int =
        generateSequence(list) { it.windowed(2) { (a, b) -> b - a } }
            .takeWhile { it.all { it == 0 }.not() }
            .toList()
            .sumOf { it.last() }

}