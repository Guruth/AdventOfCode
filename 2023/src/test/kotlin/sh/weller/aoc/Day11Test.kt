package sh.weller.aoc

class Day11Test : SomeDayTest<String, Long>(11, Day11) {
    override fun List<String>.mapData(): List<String> = this


    override val resultTest1: Long = 374
    override val resultTest2: Long = 1030
}