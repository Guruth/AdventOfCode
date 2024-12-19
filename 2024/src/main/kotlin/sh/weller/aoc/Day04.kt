package sh.weller.aoc

import sh.weller.aoc.util.rotateRight
import sh.weller.aoc.util.toCharMap

object Day04 : SomeDay<Int> {

    override val day: Int = 4


    override fun partOne(input: List<String>): Int {
        val grid = input.toCharMap()

        return grid.countHorizontal() +
                grid.countHorizontalReversed() +
                grid.rotateRight().countHorizontal() +
                grid.rotateRight().countHorizontalReversed() +
                grid.countDiagonal()
    }

    private fun List<List<Char>>.countHorizontal(): Int {
        return map { line ->
            line.windowed(4).count { it.joinToString("") == "XMAS" }
        }
            .sum()
    }

    private fun List<List<Char>>.countHorizontalReversed(): Int {
        return map { it.reversed() }
            .countHorizontal()
    }

    private fun List<List<Char>>.countDiagonal(): Int {
        var count = 0

        for ((lineIndex, line) in this.withIndex()) {
            for ((charIndex, char) in line.withIndex()) {
                if (char == 'X') {
                    if (atEquals(lineIndex + 1, charIndex + 1, 'M')
                        && atEquals(lineIndex + 2, charIndex + 2, 'A')
                        && atEquals(lineIndex + 3, charIndex + 3, 'S')
                    ) {
                        count++
                    }
                    if (atEquals(lineIndex + 1, charIndex - 1, 'M')
                        && atEquals(lineIndex + 2, charIndex - 2, 'A')
                        && atEquals(lineIndex + 3, charIndex - 3, 'S')
                    ) {
                        count++
                    }
                    if (atEquals(lineIndex - 1, charIndex - 1, 'M')
                        && atEquals(lineIndex - 2, charIndex - 2, 'A')
                        && atEquals(lineIndex - 3, charIndex - 3, 'S')
                    ) {
                        count++
                    }
                    if (atEquals(lineIndex - 1, charIndex + 1, 'M')
                        && atEquals(lineIndex - 2, charIndex + 2, 'A')
                        && atEquals(lineIndex - 3, charIndex + 3, 'S')
                    ) {
                        count++
                    }
                }
            }
        }

        return count
    }

    private fun List<List<Char>>.atEquals(x: Int, y: Int, char: Char): Boolean {
        return getOrNull(x)?.getOrNull(y) == char
    }


    //    M.S
    //    .A.
    //    M.S
    override fun partTwo(input: List<String>): Int {
        val grid = input.toCharMap()

        return grid.countXMAS() +
                grid.rotateRight().countXMAS() +
                grid.rotateRight().rotateRight().countXMAS() +
                grid.rotateRight().rotateRight().rotateRight().countXMAS()

    }

    private fun List<List<Char>>.countXMAS(): Int {
        var count = 0

        for ((lineIndex, line) in withIndex()) {
            for ((charIndex, char) in line.withIndex()) {
                if (char == 'A') {
                    if (atEquals(lineIndex - 1, charIndex - 1, 'M')
                        && atEquals(lineIndex + 1, charIndex - 1, 'M')
                        && atEquals(lineIndex - 1, charIndex + 1, 'S')
                        && atEquals(lineIndex + 1, charIndex + 1, 'S')
                    ) {
                        count++
                    }
                }
            }
        }
        return count
    }
}