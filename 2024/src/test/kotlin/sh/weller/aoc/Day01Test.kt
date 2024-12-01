package sh.weller.aoc

class Day01Test : SomeDayTest<List<Long>, Long>(1, Day01) {
    override fun List<String>.mapData(): List<List<Long>> {
        val leftList = map { it.split(" ").first().trim().toLong() }
        val rightList = map { it.split(" ").last().trim().toLong() }

        return listOf(leftList,rightList)
    }


    override val resultTest1: Long = 11
    override val resultTest2: Long = 31
}

