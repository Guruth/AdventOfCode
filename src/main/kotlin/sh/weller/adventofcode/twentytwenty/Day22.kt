package sh.weller.adventofcode.twentytwenty


fun List<String>.day22Part2(): Long {
    val player1Deck = this.getPlayer1Deck()
    val player2Deck = this.getPlayer2Deck()

    val wasRecursive = playRecursiveCombat(player1Deck, player2Deck)

    if (wasRecursive) {
        return player1Deck.calculateScore()
    }

    return player1Deck.calculateScore() + player2Deck.calculateScore()
}

private fun playRecursiveCombat(
    player1Deck: MutableList<Int>,
    player2Deck: MutableList<Int>
): Boolean {
    if (player1Deck.isEmpty() || player2Deck.isEmpty()) {
        return false
    }

    val player1RecursionCheck: MutableList<List<Int>> = mutableListOf()
    val player2RecursionCheck: MutableList<List<Int>> = mutableListOf()

    do {
        if (player1RecursionCheck.contains(player1Deck) && player2RecursionCheck.contains(player2Deck)) {
            return true
        }
        player1RecursionCheck.add(player1Deck.toList())
        player2RecursionCheck.add(player2Deck.toList())

        val player1Card = player1Deck.getTopCard()
        val player2Card = player2Deck.getTopCard()

        if (player1Deck.size >= player1Card && player2Deck.size >= player2Card) {
            // Sub-game
            val player1DeckCopy = player1Deck.take(player1Card).toMutableList()
            val player2DeckCopy = player2Deck.take(player2Card).toMutableList()

            val wasRecursive = playRecursiveCombat(player1DeckCopy, player2DeckCopy)
            if (wasRecursive) {
                // Player 1 wins
                player1Deck.add(player1Card)
                player1Deck.add(player2Card)

            } else {
                if (player2DeckCopy.isEmpty()) {
                    player1Deck.add(player1Card)
                    player1Deck.add(player2Card)
                } else {
                    player2Deck.add(player2Card)
                    player2Deck.add(player1Card)
                }
            }
        } else {
            higherCardRule(player1Card, player1Deck, player2Card, player2Deck)
        }
    } while (player1Deck.isNotEmpty() && player2Deck.isNotEmpty())
    return false
}

fun List<String>.day22Part1(): Long {
    val player1Deck = this.getPlayer1Deck()
    val player2Deck = this.getPlayer2Deck()

    playCombat(player1Deck, player2Deck)

    return player1Deck.calculateScore() + player2Deck.calculateScore()
}

private fun playCombat(player1Deck: MutableList<Int>, player2Deck: MutableList<Int>) {
    do {
        val player1Card = player1Deck.getTopCard()
        val player2Card = player2Deck.getTopCard()
        higherCardRule(player1Card, player1Deck, player2Card, player2Deck)
    } while (player1Deck.isNotEmpty() && player2Deck.isNotEmpty())
}

private fun higherCardRule(
    player1Card: Int,
    player1Deck: MutableList<Int>,
    player2Card: Int,
    player2Deck: MutableList<Int>
) {
    if (player1Card > player2Card) {
        player1Deck.add(player1Card)
        player1Deck.add(player2Card)
    } else {
        player2Deck.add(player2Card)
        player2Deck.add(player1Card)
    }
}

private fun MutableList<Int>.getTopCard(): Int {
    val card = this.first()
    this.removeAt(0)
    return card
}

private fun List<Int>.calculateScore(): Long =
    this.reversed().mapIndexed { index, i -> i * (index + 1L) }.sum()

private fun List<String>.getPlayer1Deck(): MutableList<Int> =
    this.subList(0, this.indexOfFirst { it.isEmpty() }).drop(1).map { it.toInt() }.toMutableList()

private fun List<String>.getPlayer2Deck(): MutableList<Int> =
    this.subList(this.indexOfFirst { it.isEmpty() } + 1, this.size).drop(1).map { it.toInt() }.toMutableList()
