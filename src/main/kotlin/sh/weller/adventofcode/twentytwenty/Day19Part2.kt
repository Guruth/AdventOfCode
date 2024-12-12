package sh.weller.adventofcode.twentytwenty

fun List<String>.day19Part2(): Int {
    val ruleMap = this.filter { it.contains(":") }
        .associate { rule -> rule.split(":").let { it.first().trim() to it.last().trim() } }
        .toMutableMap()
    val messages = this.filter { it.contains(":").not() }.filter { it.isNotBlank() }

    val rule42 = ruleMap.toRuleList("42")
    val rule31 = ruleMap.toRuleList("31")
    val chunkSize = rule42.first().length

    return messages
        .map { it.chunked(chunkSize) }
        .count {
            if (
                rule31.matchesAll(it.takeLast(1)) &&
                rule42.matchesAll(it.dropLast(1).takeLast(1)) &&
                rule42.matchesAll(it.dropLast(2))
            ) {
                return@count true
            } else if (
                rule31.matchesAll(it.takeLast(2)) &&
                rule42.matchesAll(it.dropLast(2).takeLast(2)) &&
                rule42.matchesAll(it.dropLast(4))
            ) {
                return@count true
            } else if (
                rule31.matchesAll(it.takeLast(3)) &&
                rule42.matchesAll(it.dropLast(3).takeLast(3)) &&
                rule42.matchesAll(it.dropLast(6))
            ) {
                return@count true
            } else if (
                rule31.matchesAll(it.takeLast(4)) &&
                rule42.matchesAll(it.dropLast(4).takeLast(4)) &&
                rule42.matchesAll(it.dropLast(8))
            ) {
                return@count true
            } else if (
                rule31.matchesAll(it.takeLast(5)) &&
                rule42.matchesAll(it.dropLast(5).takeLast(5)) &&
                rule42.matchesAll(it.dropLast(10))
            ) {
                return@count true
            } else if (
                rule31.matchesAll(it.takeLast(6)) &&
                rule42.matchesAll(it.dropLast(6).takeLast(6)) &&
                rule42.matchesAll(it.dropLast(12))
            ) {
                return@count true
            }
            return@count false
        }
}

private fun List<String>.matchesAll(messageChunks: List<String>): Boolean =
    if (messageChunks.isEmpty()) {
        false
    } else {
        messageChunks.all { this.matches(it) }
    }

private fun List<String>.matches(messageChunk: String): Boolean =
    this.contains(messageChunk)

private fun Map<String, String>.toRuleList(index: String): List<String> {
    val ruleToBuild = this[index]
    when {
        ruleToBuild == null -> throw IllegalArgumentException("Rule does not exist")
        ruleToBuild.contains("\"") -> {
            return listOf(ruleToBuild.replace("\"", "").trim())
        }
        ruleToBuild.contains("|") -> {
            return ruleToBuild.split("|")
                .map { orRule ->
                    orRule
                        .trim()
                        .split(" ").filter { it.isNotBlank() }
                        .map { this.toRuleList(it) }
                        .combineRules()
                }
                .flatten()
        }
        else -> {
            return ruleToBuild
                .split(" ").filter { it.isNotBlank() }
                .map { this.toRuleList(it) }
                .combineRules()
        }
    }
}

private fun List<List<String>>.combineRules(): List<String> {
    var combinations = this.first()
    this.drop(1).forEach { ruleToCombine ->
        val tmpCombinations = mutableListOf<String>()
        ruleToCombine.forEach { subRule ->
            combinations.forEach { combination ->
                tmpCombinations.add(combination + subRule)
            }
        }
        combinations = tmpCombinations
    }
    return combinations
}
