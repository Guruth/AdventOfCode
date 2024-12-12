package sh.weller.aoc

class Day06Test : SomeDayTest<String, Int>(6, Day06) {
    override fun List<String>.mapData(): List<String> = this

    override val resultTest1: Int = 7
    override val resultTest2: Int = 19
}

