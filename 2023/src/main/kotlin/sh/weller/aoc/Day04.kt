package sh.weller.aoc

object Day04 : SomeDay<Pair<List<Int>, List<Int>>, Int> {
    override fun partOne(input: List<Pair<List<Int>, List<Int>>>): Int =
        input
            .map { (winningNumbers, havingNumbers) ->
                val numberOfWinningNumbers = havingNumbers
                    .filter { it in winningNumbers }
                    .size

                if (numberOfWinningNumbers > 0) {
                    var result = 1
                    repeat(numberOfWinningNumbers - 1) {
                        result *= 2
                    }
                    return@map result
                }
                return@map 0
            }
            .sum()

    override fun partTwo(input: List<Pair<List<Int>, List<Int>>>): Int {
        val cards = input
            .map { (winningNumbers, havingNumbers) -> Triple(1, winningNumbers, havingNumbers) }
            .toMutableList()

        for (i in 0..<cards.size) {
            val (numberOfCopies, winningNumbers, havingNumbers) = cards[i]
            val numberOfMatches = getNumberOfMatches(winningNumbers, havingNumbers)


            for (j in i + 1..i + numberOfMatches) {
                val card = cards
                    .getOrNull(j) ?: break

                cards[j] = card.copy(first = card.first + numberOfCopies)
            }
        }
        return cards
            .sumOf { it.first }
    }

    private fun getNumberOfMatches(winningNumbers: List<Int>, havingNumbers: List<Int>): Int =
        havingNumbers
            .filter { it in winningNumbers }
            .size
}