package sh.weller.aoc

class Day10Test : SomeDayTest<String, Int>(10, Day10) {
    override fun List<String>.mapData() = this


    override val resultTest1: Int = 8
    override val resultTest2: Int = 10
}

