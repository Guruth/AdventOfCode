package sh.weller.aoc

class Day03Test : SomeDayTest<List<Char>, Int>(3, Day03) {

    override val resultTest1: Int = 198
    override val resultTest2: Int = 230
    override fun List<String>.mapData(): List<List<Char>> =
        map { it.toCharArray().toList() }
}

