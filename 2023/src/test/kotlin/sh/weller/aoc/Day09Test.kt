package sh.weller.aoc

class Day09Test : SomeDayTest<List<Int>, Int>(9, Day09) {
    override fun List<String>.mapData(): List<List<Int>> =
        map { line ->
            line.split(" ").filterNot { it.isBlank() }.map { it.toInt() }
        }


    override val resultTest1: Int = 114
    override val resultTest2: Int = 2
}

