package sh.weller.aoc

object Day06 : SomeDay<Char, Int> {
    override fun partOne(input: List<Char>): Int =
        input.findFirstUniqueWindow(4)

    override fun partTwo(input: List<Char>): Int =
        input.findFirstUniqueWindow(14)

    private fun List<Char>.findFirstUniqueWindow(windowSize: Int): Int {
        this.windowed(windowSize)
            .forEachIndexed { index, chars ->
                if (chars.toSet().size == windowSize) {
                    return index + windowSize
                }
            }
        return 0
    }

}