package sh.weller.aoc

class Day04Test : SomeDayTest<Pair<List<Int>, List<Int>>, Int>(4, Day04) {
    override fun List<String>.mapData(): List<Pair<List<Int>, List<Int>>> =
        map { it.split(":").last() }
            .map {numberString ->
                val numbers = numberString.split("|")

                val winningNumbers = numbers.first().trim().split(" ").filterNot { it.isBlank() }.map { it.toInt() }
                val havingNumbers = numbers.last().trim().split(" ").filterNot { it.isBlank() }.map { it.toInt() }

                winningNumbers to havingNumbers
            }


    override val resultTest1: Int = 13
    override val resultTest2: Int
        get() = TODO("Not yet implemented")

}

