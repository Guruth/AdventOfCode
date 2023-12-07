package sh.weller.aoc

class Day07Test : SomeDayTest<Pair<String, Int>, Int>(7, Day07) {
    override fun List<String>.mapData(): List<Pair<String, Int>> = map {
        val foo = it.split(" ").filterNot { it.isBlank() }

        foo.first() to foo.last().toInt()
    }


    override val resultTest1: Int = 6440
    override val resultTest2: Int = 5905
}

