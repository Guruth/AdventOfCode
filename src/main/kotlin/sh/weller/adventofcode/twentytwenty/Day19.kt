package sh.weller.adventofcode.twentytwenty


fun List<String>.day19Part1(): Int {
    val ruleMap = this.filter { it.contains(":") }
        .associate { rule -> rule.split(":").let { it.first().trim() to it.last().trim() } }
        .toMutableMap()

    val ruleList = ruleMap.toRuleList("0")

    val messages = this.filter { it.contains(":").not() }
    return messages.count { message ->
        ruleList.contains(message)
    }
}


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
