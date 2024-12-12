package sh.weller.aoc

import sh.weller.aoc.util.to2DList

object Day11 : SomeDay<Long> {

    override val day: Int = 11

    override fun partOne(input: List<String>): Long {
        val stones = input.first().split(" ").map { it.toLong() }

        val newStones = mutableListOf<Long>()
        for (stone in stones) {
            newStones += stone.blink(1)
        }

        return newStones.size.toLong()
    }

    var iterations = 25

    private fun Long.blink(count: Int): List<Long> {
        val stones = mutableListOf<Long>()
        if (this == 0L) {
            stones.add(1L)
        } else if ((this.toString().toCharArray().size) % 2 == 0) {
            val number = this.toString().toCharArray()
            stones.add(number.take(number.size / 2).joinToString("").toLong())
            stones.add(number.takeLast(number.size / 2).joinToString("").toLong())
        } else {
            stones.add(this * 2024)
        }

        if (count < iterations) {
            return stones.flatMap { it.blink(count + 1) }
        } else {
            return stones
        }
    }


    override fun partTwo(input: List<String>): Long {
        TODO()
    }

}
