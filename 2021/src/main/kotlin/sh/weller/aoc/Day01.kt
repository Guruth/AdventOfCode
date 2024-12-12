package sh.weller.aoc

object Day01 {

    fun first(numbers: List<Int>): Int {
        return numbers.windowed(2)
            .map { (first, second) ->
                second > first
            }.count { it }

    }

    fun second(numbers: List<Int>): Int {
       return numbers.windowed(3)
            .map { it.sum() }
            .windowed(2)
            .map { (first, second) ->
                second > first
            }
            .count { it }
    }
}