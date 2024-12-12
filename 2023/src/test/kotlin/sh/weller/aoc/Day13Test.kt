package sh.weller.aoc

class Day13Test : SomeDayTest<String, Int>(13, Day13) {
    override fun List<String>.mapData(): List<String> = this
    
    override val resultTest1: Int = 405
    override val resultTest2: Int = 400
}