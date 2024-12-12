package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.multipliedSum


fun List<String>.day16Part2(): Long {
    val splitInput = mutableListOf<List<String>>()
    var lastIndex = 0
    this.forEachIndexed { index, s ->
        if (s == "") {
            splitInput.add(this.subList(lastIndex, index))
            lastIndex = index + 1
        }
        if (index == this.size - 1) {
            splitInput.add(this.subList(lastIndex, this.size))
        }
    }

    val rules = splitInput[0].createRules()
    val myTicket = splitInput[1].drop(1).first().split(",").map { it.toInt() }
    val otherTickets = splitInput[2].drop(1)

    val validTickets = otherTickets.filter { ticket ->
        val parsedTicket = ticket.split(",").map { it.toInt() }
        return@filter parsedTicket.map { rules.validNumber(it) }.contains(false).not()
    }

    val rulesMap = mutableMapOf<Int, MutableList<Int>>()

    validTickets
        .forEach { ticket ->
            val parsedTicket = ticket.split(",").map { it.toInt() }
            rules.forEachIndexed { ruleIndex, rule ->
                parsedTicket.forEachIndexed { index, i ->
                    if (rule.validNumber(i)) {
                        val ruleLIst: MutableList<Int> = rulesMap.getOrDefault(ruleIndex, mutableListOf())
                        ruleLIst.add(index)
                        rulesMap[ruleIndex] = ruleLIst
                    }
                }
            }
        }

    val possibleColumnsPerRule = rulesMap.map { ruleMap ->
        val ruleColumns = ruleMap.value
        val groupedColumns = ruleColumns.groupBy { it }
        val sortedColumns = groupedColumns.values.sortedBy { it.size }
        val possibleColumns = sortedColumns.filter { it.size == sortedColumns.last().size }.map { it.first() }

        val ruleName = rules[ruleMap.key].name
        return@map Pair(ruleName, possibleColumns)
    }

    val determinedRules = mutableListOf<Pair<String, Int>>()

    while (determinedRules.size != rules.size) {
        possibleColumnsPerRule
            .forEach { rule ->
                val filteredColumns = rule.second
                    .filter { possibleColumn ->
                        if (determinedRules.map { it.second }.contains(possibleColumn)) {
                            return@filter false
                        }
                        return@filter true
                    }
                if (filteredColumns.size == 1) {
                    determinedRules.add(Pair(rule.first, filteredColumns.single()))
                }
            }
    }

    return determinedRules.filter { it.first.startsWith("departure") }
        .map { myTicket[it.second] }
        .multipliedSum()
}


fun List<String>.day16Part1(): Int {
    val splitInput = mutableListOf<List<String>>()
    var lastIndex = 0
    this.forEachIndexed { index, s ->
        if (s == "") {
            splitInput.add(this.subList(lastIndex, index))
            lastIndex = index + 1
        }
        if (index == this.size - 1) {
            splitInput.add(this.subList(lastIndex, this.size))
        }
    }

    val rules = splitInput[0].createRules()
    val otherTickets = splitInput[2].drop(1)

    val invalidFields = otherTickets.flatMap { line -> line.split(",").map { it.toInt() } }
        .mapNotNull {
            if (!rules.validNumber(it)) {
                return@mapNotNull it
            }
            return@mapNotNull null
        }

    return invalidFields.sum()
}

private fun Rule.validNumber(number: Int): Boolean {
    if (number in (this.rule1.first..this.rule1.second) || number in (this.rule2.first..this.rule2.second)) {
        return true
    }
    return false
}


private fun List<Rule>.validNumber(number: Int): Boolean =
    this.map {
        if (number in (it.rule1.first..it.rule1.second) || number in (it.rule2.first..it.rule2.second)) {
            return@map true
        }
        return@map false
    }.contains(true)


private fun List<String>.createRules(): List<Rule> =
    this.map {
        val splitLine = it.split(":")
        val className = splitLine[0].trim()
        val rule1 = splitLine[1].split("or")[0].trim().createRule()
        val rule2 = splitLine[1].split("or")[1].trim().createRule()
        return@map Rule(className, rule1, rule2)
    }

private fun String.createRule(): Pair<Int, Int> =
    Pair(this.split("-")[0].toInt(), this.split("-")[1].toInt())


private data class Rule(
    val name: String,
    val rule1: Pair<Int, Int>,
    val rule2: Pair<Int, Int>
)