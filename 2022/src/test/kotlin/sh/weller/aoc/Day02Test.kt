package sh.weller.aoc

import sh.weller.aoc.util.to2DList

class Day02Test : SomeDayTest<Pair<Char, Char>, Int>(2, Day02) {
    override fun List<String>.mapData(): List<Pair<Char, Char>> = to2DList().map { it.first() to it.last() }

    override val resultTest1: Int = 15
    override val resultTest2: Int = 12
}

