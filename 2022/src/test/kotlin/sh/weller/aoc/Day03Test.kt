package sh.weller.aoc

class Day03Test : SomeDayTest<String, Int>(3, Day03) {
    override fun List<String>.mapData(): List<String> = map { it }

    override val resultTest1: Int = 157
    override val resultTest2: Int = 70
}

