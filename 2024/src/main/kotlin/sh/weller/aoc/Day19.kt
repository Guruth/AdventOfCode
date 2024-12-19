package sh.weller.aoc

import sh.weller.aoc.Day06.setAt
import sh.weller.aoc.util.aStar
import sh.weller.aoc.util.print
import sh.weller.aoc.util.x
import sh.weller.aoc.util.y
import kotlin.streams.toList

object Day19 : SomeDay<Long> {
    override val day: Int = 19
    override fun partOne(input: List<String>): Long {
        val designs = input.first().split(",").map { it.trim() }
        return input
            .drop(2)
            .filter { patternPossible(it, designs) }
            .size.toLong()
    }

    private fun patternPossible(pattern: String, designs: List<String>): Boolean {
        if (pattern.isEmpty()) {
            return true
        }
        return designs
            .filter { pattern.startsWith(it) }
            .any { patternPossible(pattern.removePrefix(it), designs) }
    }

    override fun partTwo(input: List<String>): Long {
        val designs = input.first().split(",").map { it.trim() }

        val cache = mutableMapOf<Pair<String, String>, Long>()
        return input
            .drop(2)
            .sumOf { numberOfPossiblePatterns(cache, it, designs) }
    }


    private fun numberOfPossiblePatterns(
        cache: MutableMap<Pair<String, String>, Long>,
        pattern: String,
        designs: List<String>
    ): Long {
        if (pattern.isEmpty()) {
            return 1
        }
        return designs
            .filter { pattern.startsWith(it) }
            .sumOf {
                cache[pattern to it]
                    ?: run {
                        val combinations = numberOfPossiblePatterns(cache, pattern.removePrefix(it), designs)
                        cache[pattern to it] = combinations
                        combinations
                    }
            }
    }
}