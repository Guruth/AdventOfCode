package sh.weller.aoc

class Day05Test : SomeDayTest<Pair<Pair<Int, Int>, Pair<Int, Int>>, Int>(5, Day05) {

    override val resultTest1: Int = 5
    override val resultTest2: Int = 12
    override fun List<String>.mapData(): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> = map {
        val (start, end) = it.split("->")
        val (startX, startY) = start.trim().split(",").map { it.toInt() }
        val (endX, endY) = end.trim().split(",").map { it.toInt() }
        return@map (startX to startY) to (endX to endY)
    }
}

