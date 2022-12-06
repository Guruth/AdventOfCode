package sh.weller.aoc

object Day06 : SomeDay<String, Int> {
    override fun partOne(input: List<String>): Int =
        input.first()
            .toCharArray().toList()
            .findFirstUniqueWindow(4)

    override fun partTwo(input: List<String>): Int =
        input.first()
            .toCharArray().toList()
            .findFirstUniqueWindow(14)

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