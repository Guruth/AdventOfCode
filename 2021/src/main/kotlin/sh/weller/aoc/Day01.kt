package sh.weller.aoc

object Day01 : SomeDay<Int, Int> {
    override fun partOne(input: List<Int>): Int {
        return input
            .windowed(2)
            .count { (first, second) ->
                second > first
            }
    }

    override fun partTwo(input: List<Int>): Int {
        return input
            .windowed(3) { it.sum() }
            .windowed(2)
            .count { (first, second) ->
                second > first
            }
    }
}