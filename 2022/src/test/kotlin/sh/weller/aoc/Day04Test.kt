package sh.weller.aoc

class Day04Test : SomeDayTest<String, Int>(4, Day04) {
    override fun List<String>.mapData(): List<String> = map { it }

    override val resultTest1: Int = 2
    override val resultTest2: Int = 4
}

