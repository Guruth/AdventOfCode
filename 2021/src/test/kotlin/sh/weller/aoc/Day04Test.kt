package sh.weller.aoc

class Day04Test : SomeDayTest<String, Int>(4, Day04) {

    override val resultTest1: Int = 4512
    override val resultTest2: Int = 1924
    override fun List<String>.mapData(): List<String> = this
}

