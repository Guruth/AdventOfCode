package sh.weller.aoc

import sh.weller.aoc.SomeDay

object Day04 : SomeDay<Pair<List<Int>, List<Int>>, Int> {
    override fun partOne(input: List<Pair<List<Int>, List<Int>>>): Int =
        input
            .map { (winningNumbers, havingNumbers) ->
                val numberOfWinningNumbers = havingNumbers
                    .filter { it in winningNumbers }
                    .size

                if (numberOfWinningNumbers > 0) {
                    var result = 1
                    repeat(numberOfWinningNumbers - 1) {
                        result *= 2
                    }
                    return@map result
                }
                return@map 0
            }
            .sum()

    override fun partTwo(input: List<Pair<List<Int>, List<Int>>>): Int {
        TODO("Not yet implemented")
    }


}