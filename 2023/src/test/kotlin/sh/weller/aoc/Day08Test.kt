package sh.weller.aoc

class Day08Test : SomeDayTest<String, Long>(8, Day08) {
    override fun List<String>.mapData() = this

    override val resultTest1: Long = 6
    override val resultTest2: Long = 6
}

