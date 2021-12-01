package sh.weller.aoc

import sh.weller.aoc.util.parseTo

class Day01Test : SomeDayTest<Int, Int>(1, Day01) {
    override fun List<String>.mapData(): List<Int> = map(::parseTo)
}

