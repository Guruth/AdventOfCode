package sh.weller.aoc

object Day07 : SomeDay<Long> {

    override val day: Int = 7

    override fun partOne(input: List<String>): Long {
        return input
            .map { line ->
                val (testValueString, valueString) = line.split(":")
                val testValue = testValueString.toLong()

                val values = valueString
                    .split(" ")
                    .map { it.trim() }
                    .filter { it.isBlank().not() }
                    .map { it.toLong() }

                testValue to values
            }
            .filter { (testValue, values) ->
                getCombinations(values).contains(testValue)
            }.sumOf { it.first }
    }

    private fun getCombinations(values: List<Long>): List<Long> {
        if (values.size >= 2) {
            val (a, b) = values.take(2)
            val rest = values.drop(2)

            return getCombinations(listOf(a + b) + rest) +
                    getCombinations(listOf(a * b) + rest)
        } else {
            return values
        }
    }


    override fun partTwo(input: List<String>): Long {
        return input
            .map { line ->
                val (testValueString, valueString) = line.split(":")
                val testValue = testValueString.toLong()

                val values = valueString
                    .split(" ")
                    .map { it.trim() }
                    .filter { it.isBlank().not() }
                    .map { it.toLong() }

                testValue to values
            }
            .filter { (testValue, values) ->
                getCombinationsWithConcat(values).contains(testValue)
            }.sumOf { it.first }
    }


    private fun getCombinationsWithConcat(values: List<Long>): List<Long> {
        if (values.size >= 2) {
            val (a, b) = values.take(2)
            val rest = values.drop(2)

            return getCombinationsWithConcat(listOf(a + b) + rest) +
                    getCombinationsWithConcat(listOf(a * b) + rest) +
                    getCombinationsWithConcat(listOf("$a$b".toLong()) + rest)
        } else {
            return values
        }
    }
}