package sh.weller.aoc

import sh.weller.aoc.util.to2DList

object Day13 : SomeDay<String, Int> {
    override fun partOne(input: List<String>): Int {
        val patterns = getPatterns(input)

        val columns = patterns
            .map(::flip)
            .sumOf(::getReflectedRows)
        val rows = patterns
            .sumOf(::getReflectedRows)

        return columns + (100 * rows)
    }

    private fun getPatterns(input: List<String>): List<List<String>> {
        val patterns = mutableListOf<List<String>>()
        var tmpPattern = mutableListOf<String>()

        for ((index, line) in input.withIndex()) {
            if (line.isBlank()) {
                patterns.add(tmpPattern)
                tmpPattern = mutableListOf()
            } else if ((index + 1) == input.size) {
                tmpPattern.add(line)
                patterns.add(tmpPattern)
            } else {
                tmpPattern.add(line)
            }
        }
        return patterns
    }

    private fun flip(strings: List<String>): List<String> {
        val charList = strings.to2DList()
        val flippedList = mutableListOf<String>()

        repeat(charList.first().size) { index ->
            val column = charList
                .map { it[index] }
                .reversed()
                .joinToString("")
            flippedList.add(column)
        }

        return flippedList
    }

    private fun getReflectedRows(pattern: List<String>): Int {
        val reflections = mutableListOf<Int>()

        repeat(pattern.size) { index ->
            val previous = pattern.getOrNull(index - 1)
            val current = pattern.getOrNull(index)

            if (previous == current) {
                reflections.add(index)
            }
        }
        println(reflections.size)

        for (reflection in reflections) {
            val patternBefore = pattern.subList(0, reflection).reversed()
            val patternAfter = pattern.subList(reflection, pattern.size)

            val isPerfectReflection = patternBefore
                .zip(patternAfter)
                .all { (first, second) -> first == second }

            if (isPerfectReflection) {
                return reflection
            }
        }

        return 0
    }


    override fun partTwo(input: List<String>): Int {
        val patterns = getPatterns(input)

        val columns = patterns
            .map(::flip)
            .sumOf(::getReflectedRowsWithSmudge)
        val rows = patterns
            .sumOf(::getReflectedRowsWithSmudge)

        return columns + (100 * rows)
    }


    private fun getReflectedRowsWithSmudge(pattern: List<String>): Int {
        val reflections = mutableListOf<Int>()

        repeat(pattern.size) { index ->
            val previous = pattern.getOrNull(index - 1)
            val current = pattern.getOrNull(index)

            if (previous == current || distance(previous, current) == 1) {
                reflections.add(index)
            }
        }

        for (reflection in reflections) {
            val patternBefore = pattern.subList(0, reflection).reversed()
            val patternAfter = pattern.subList(reflection, pattern.size)

            val reflectedDistance = patternBefore
                .zip(patternAfter)
                .sumOf(::distance)

            if (reflectedDistance == 1) {
                return reflection
            }
        }

        return 0
    }

    private fun distance(pair: Pair<String?, String?>): Int =
        distance(pair.first, pair.second)

    private fun distance(first: String?, second: String?): Int {
        if (first == null || second == null) {
            return first?.length ?: second?.length ?: 0
        }

        val firstArray = first.toCharArray()
        val secondArray = second.toCharArray()

        return firstArray.zip(secondArray)
            .count { (first, second) -> first != second }
    }
}