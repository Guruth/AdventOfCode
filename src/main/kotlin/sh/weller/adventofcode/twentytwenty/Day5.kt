package sh.weller.adventofcode.twentytwenty

import kotlin.math.pow

fun List<String>.findHighestSeatId(): Int =
    this.toSeatId().maxOf { it }

fun List<String>.printEmptySeats() =
    this.toSeatMappingList()
        .groupBy { it.first }
        .mapValues { it.value.map { it.second }.sorted() }
        .forEach {
            if (it.value.containsAll((0..7).toList()).not()) {
                println("Empty Seat in Row: ${it.key}: ${it.value}")
            }
        }

fun List<String>.toSeatId(): List<Int> =
    this.toSeatMappingList()
        .map { (it.first * 8) + it.second }

private fun List<String>.toSeatMappingList(): List<Pair<Int, Int>> =
    this.map {
        it.toSeatMapping()
    }

private fun String.toSeatMapping(): Pair<Int, Int> {
    val chars = this.toCharArray()
    val rowMapping = chars.take(7)
    val columnMapping = chars.takeLast(3)
    return Pair(rowMapping.partition(), columnMapping.partition())
}

private fun List<Char>.partition(): Int {
    var part = 2.toDouble().pow(this.size).toInt()
    var lower = 0
    for (char in this) {
        part /= 2
        when (char) {
            'B', 'R' -> lower += part
        }
    }
    return lower
}