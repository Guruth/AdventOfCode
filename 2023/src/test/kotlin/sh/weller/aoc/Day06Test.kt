package sh.weller.aoc

class Day06Test : SomeDayTest<List<Long>, Long>(6, Day06) {
    override fun List<String>.mapData(): List<List<Long>> {
        val times = first().split(":").last().split(" ").filterNot { it.isBlank() }.map { it.toLong() }
        val distance = last().split(":").last().split(" ").filterNot { it.isBlank() }.map { it.toLong() }

        return listOf(times, distance)
    }


    override val resultTest1: Long = 288
    override val resultTest2: Long = 71503
}

