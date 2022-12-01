package sh.weller.aoc

class Day01Test : SomeDayTest<String?, Int>(1, Day01) {
    override fun List<String>.mapData(): List<String?> = map { calories ->
        if (calories.isNullOrBlank()) {
            return@map null
        } else {
            return@map calories
        }
    }

    override val resultTest1: Int = 24000
    override val resultTest2: Int = 45000
}

