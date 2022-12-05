package sh.weller.aoc

class Day05Test : SomeDayTest<String, String>(5, Day05) {
    override fun List<String>.mapData(): List<String> = map { it }

    override val resultTest1: String = "CMZ"
    override val resultTest2: String = "MCD"
}

