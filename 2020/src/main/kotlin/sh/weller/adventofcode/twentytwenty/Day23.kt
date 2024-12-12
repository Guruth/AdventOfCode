package sh.weller.adventofcode.twentytwenty

import org.magicwerk.brownies.collections.primitive.IntGapList
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds


fun List<Int>.day23Part2(rounds: Int = 10000000): Long {
    val cups = playCrabCups(rounds, (this + ((this.maxOf { it }+1) .. 1000000)))

    val oneIndex = cups.indexOf(1)
    val cupAfterOne = cups[oneIndex + 1]
    val cupTwoAfterOne = cups[oneIndex + 2]
    println("$cupAfterOne - $cupTwoAfterOne")
    return cupAfterOne.toLong() * cupTwoAfterOne.toLong()
}

fun List<Int>.day23Part1(rounds: Int = 100): String {
    val cups = playCrabCups(rounds, this)

    val cupsBeforeOne = cups.subList(0, cups.indexOf(1))
    val cupsAfterOne = cups.subList(cups.indexOf(1) + 1, cups.size)

    return (cupsAfterOne + cupsBeforeOne).joinToString("")
}


@OptIn(ExperimentalTime::class)
private fun playCrabCups(rounds: Int, cupsList: List<Int>): List<Int> {
    val cups = IntGapList.create(cupsList)

    val maxCupValue = cupsList.maxOf { it }
    val minCupValue = cupsList.minOf { it }
    val start = System.currentTimeMillis().milliseconds

    repeat(rounds) { round ->
        if (round % 10000 == 0) {
            val now = System.currentTimeMillis().milliseconds
            println("Round $round: ${now - start}")
        }

        val currentCup: Int = cups.removeFirst()
        val pickedUpCups: List<Int> = listOf(cups.removeFirst(), cups.removeFirst(), cups.removeFirst())
        var destinationCup: Int
        destinationCup = currentCup - 1
        if (destinationCup < minCupValue) {
            destinationCup = maxCupValue
        }
        while (pickedUpCups.contains(destinationCup)) {
            destinationCup -= 1
            if (destinationCup < minCupValue) {
                destinationCup = maxCupValue
            }
        }
        val indexOfDestination = cups.indexOf(destinationCup)
        cups.addAll(indexOfDestination + 1, pickedUpCups)
        cups.add(currentCup)
    }
    return cups.toArray().toList()
}