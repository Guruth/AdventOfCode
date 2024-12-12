package sh.weller.aoc

object Day04 : SomeDay<String, Int> {
    override fun partOne(input: List<String>): Int =
        input
            .map { inputString ->
                val sections = inputString.split(",")
                val firstValues = sections.first().split("-").map { it.toInt() }
                val secondValues = sections.last().split("-").map { it.toInt() }
                firstValues to secondValues
            }
            .map { (firstValues, secondValues) ->
                firstValues.first() <= secondValues.first() && firstValues.last() >= secondValues.last() ||
                        secondValues.first() <= firstValues.first() && secondValues.last() >= firstValues.last()
            }
            .count { it }

    override fun partTwo(input: List<String>): Int =
        input
            .map { inputString ->
                val sections = inputString.split(",")
                val firstValues = sections.first().split("-").map { it.toInt() }
                val secondValues = sections.last().split("-").map { it.toInt() }
                firstValues to secondValues
            }
            .map { (firstValues, secondValues) ->
                val firstRange = IntRange(firstValues.first(), firstValues.last()).toSet()
                val secondRange = IntRange(secondValues.first(), secondValues.last()).toSet()

                firstRange.intersect(secondRange).isNotEmpty()
            }
            .count { it }
}