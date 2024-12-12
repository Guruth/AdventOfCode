package sh.weller.aoc

object Day07 : SomeDay<Pair<String, Int>, Int> {
    override fun partOne(input: List<Pair<String, Int>>): Int {
        return input
            .map { (handString, bet) ->
                Hand.fromString(handString) to bet
            }
            .sortedBy { it.first.handValue }
            .mapIndexed { index, (_, bet) -> (index + 1) * bet }
            .sum()
    }


    override fun partTwo(input: List<Pair<String, Int>>): Int {
        return input
            .map { (handString, bet) ->
                JokerHand(handString) to bet
            }
            .sortedBy { it.first.valueOf() }
            .mapIndexed { index, (hand, bet) ->
                println("${hand.rawHand} - ${hand.valueOf()} - ${hand.handType()} ")

                (index + 1) * bet
            }
            .sum()
    }

}

enum class HandType(val value: Int) {
    FiveOfAKind(7), FourOfAKind(6), FullHouse(5), ThreeOfAKind(4), TwoPair(3), OnePair(2), HighCard(1)
}


data class Hand(
    val type: HandType,
    val handValue: Long,
    val rawHand: String,
) {
    companion object {
        fun fromString(value: String): Hand {
            val chars = value.toCharArray()
            val groupedChars = chars.groupBy { it }

            val type = when {
                groupedChars.size == 1 -> HandType.FiveOfAKind
                groupedChars.size == 2 && groupedChars.any { it.value.size == 4 } -> HandType.FourOfAKind
                groupedChars.size == 2 && groupedChars.any { it.value.size == 3 } && groupedChars.any { it.value.size == 2 } -> HandType.FullHouse
                groupedChars.size == 3 && groupedChars.any { it.value.size == 3 } && groupedChars.any { it.value.size == 3 } -> HandType.ThreeOfAKind
                groupedChars.size == 3 && groupedChars.count { it.value.size == 2 } == 2 -> HandType.TwoPair
                groupedChars.size == 4 && groupedChars.count { it.value.size == 2 } == 1 -> HandType.OnePair
                groupedChars.size == 5 -> HandType.HighCard
                else -> throw IllegalArgumentException("Unknown Hand Type")
            }

            val stringValue = "${type.value}" + chars.map {
                when (it) {
                    'A' -> "13"
                    'K' -> "12"
                    'Q' -> "11"
                    'J' -> "10"
                    'T' -> "09"
                    '9' -> "08"
                    '8' -> "07"
                    '7' -> "06"
                    '6' -> "05"
                    '5' -> "04"
                    '4' -> "03"
                    '3' -> "02"
                    '2' -> "01"
                    else -> throw IllegalArgumentException("Unknown Card Type")
                }
            }.joinToString("")

            return Hand(type, stringValue.toLong(), value)
        }
    }
}


data class JokerHand(
    val rawHand: String,
) {

    fun valueOf(): Long = "${handType().value}${handValue()}".toLong()

    fun handType(): HandType {
        val chars = rawHand.toCharArray()
        val groupedChars = chars.groupBy { it }.mapValues { it.value.size }
        val numberOfJokers = groupedChars['J'] ?: 0

        return when {
            numberOfJokers == 0 -> {
                when {
                    groupedChars.size == 1 -> HandType.FiveOfAKind
                    groupedChars.size == 2 && groupedChars.any { it.value == 4 } -> HandType.FourOfAKind
                    groupedChars.size == 2 && groupedChars.any { it.value == 3 } && groupedChars.any { it.value == 2 } -> HandType.FullHouse
                    groupedChars.size == 3 && groupedChars.any { it.value == 3 } && groupedChars.any { it.value == 3 } -> HandType.ThreeOfAKind
                    groupedChars.size == 3 && groupedChars.count { it.value == 2 } == 2 -> HandType.TwoPair
                    groupedChars.size == 4 && groupedChars.count { it.value == 2 } == 1 -> HandType.OnePair
                    groupedChars.size == 5 -> HandType.HighCard

                    else -> throw IllegalArgumentException("Unknown Hand Type: $rawHand")
                }
            }

            numberOfJokers == 5 -> HandType.FiveOfAKind
            numberOfJokers == 4 -> HandType.FiveOfAKind
            numberOfJokers == 3 -> {
                when {
                    // JJJAA
                    groupedChars.size == 2 -> HandType.FiveOfAKind
                    // JJJAB
                    groupedChars.size == 3 -> HandType.FourOfAKind

                    else -> throw IllegalArgumentException("Unknown Hand Type: $rawHand")
                }
            }


            numberOfJokers == 2 -> {
                when {
                    // JJABC
                    groupedChars.size == 4 -> HandType.ThreeOfAKind
                    // JJAAB
                    groupedChars.size == 3 -> HandType.FourOfAKind
                    // JJAAA
                    groupedChars.size == 2 -> HandType.FiveOfAKind

                    else -> throw IllegalArgumentException("Unknown Hand Type: $rawHand")
                }
            }

            numberOfJokers == 1 -> {
                when {
                    // JABCD
                    groupedChars.size == 5 -> HandType.OnePair
                    // JAABC
                    groupedChars.size == 4 -> HandType.ThreeOfAKind
                    // JAAAB
                    groupedChars.size == 3 && groupedChars.count { it.value == 3 } == 1 -> HandType.FourOfAKind
                    // JAABB
                    groupedChars.size == 3 && groupedChars.count { it.value == 2 } == 2 -> HandType.FullHouse
                    // JAAAA
                    groupedChars.size == 2 -> HandType.FiveOfAKind

                    else -> throw IllegalArgumentException("Unknown Hand Type: $rawHand")
                }
            }

            else -> throw IllegalArgumentException("Unknown Hand Type: $rawHand")
        }
    }

    fun handValue(): String =
        rawHand.toCharArray().joinToString("") {
            when (it) {
                'A' -> "13"
                'K' -> "12"
                'Q' -> "11"
                'T' -> "10"
                '9' -> "09"
                '8' -> "08"
                '7' -> "07"
                '6' -> "06"
                '5' -> "05"
                '4' -> "04"
                '3' -> "03"
                '2' -> "02"
                'J' -> "01"
                else -> throw IllegalArgumentException("Unknown Card Type")
            }
        }
}