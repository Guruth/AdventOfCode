package sh.weller.aoc

class Day02Test : SomeDayTest<Pair<String, Int>, Int>(2, Day02) {
    override fun List<String>.mapData(): List<Pair<String, Int>> =
        map {
            val (first, second) = it.split(" ")
            return@map first to second.toInt()
        }

    override val resultTest1: Int = 150
    override val resultTest2: Int = 900
}

