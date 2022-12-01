package sh.weller.aoc

object Day01 : SomeDay<String, Int> {
    override fun partOne(input: List<String>): Int =
        input
            .caloriesPerElv()
            .max()

    override fun partTwo(input: List<String>): Int =
        input
            .caloriesPerElv()
            .sortedDescending()
            .take(3)
            .sum()

    private fun List<String>.caloriesPerElv() = fold(mutableListOf(0)) { acc, s ->
        if (s.isBlank()) {
            acc.add(0)
        } else {
            acc.add(acc.removeLast() + s.toInt())
        }
        acc
    }

}