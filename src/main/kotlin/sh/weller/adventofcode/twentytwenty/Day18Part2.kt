package sh.weller.adventofcode.twentytwenty


fun List<String>.day18Part2(): Long =
    this.map { it.split(" ").filter { it.isNotBlank() } }
        .map { it.calculateAdvanced() }
        .map { it.toLong() }
        .sum()

private fun List<String>.calculateAdvanced(): String {
    when {
        this.size == 1 -> {
            return this[0]
        }
        this.size == 3 -> {
            return calculateNumbers(this[0], this[1], this[2])
        }
        this[0].startsWith("(") -> {
            val subExpression = this.getSubExpression()
            val subExpressionResult = subExpression.calculateAdvanced()
            val restExpression = this.drop(this.getSubExpression().size)
            return (listOf(subExpressionResult) + restExpression).calculateAdvanced()
        }
        this[2].startsWith("(") -> {
            val subExpression = this.drop(2).getSubExpression()
            val subExpressionResult = subExpression.calculateAdvanced()
            val restExpression = this.drop(subExpression.size + 2)
            return (listOf(this[0], this[1], subExpressionResult) + restExpression).calculateAdvanced()
        }
        else -> {
            return when {
                this[1] == "+" -> {
                    val tmpResult = calculateNumbers(this[0], this[1], this[2])
                    (listOf(tmpResult) + this.drop(3)).calculateAdvanced()
                }
                else -> {
                    (listOf(this[0], this[1]) + this.drop(2).calculateAdvanced()).calculateAdvanced()
                }
            }

        }
    }
}

private fun List<String>.getSubExpression(): List<String> {
    val closingIndex = this.indexOfClosingParenthesis()
    val firstValue = this[0].drop(1)
    val lastValue = this[closingIndex].dropLast(1)
    return listOf(firstValue) + this.subList(1, closingIndex) + listOf(lastValue)
}


private fun List<String>.indexOfClosingParenthesis(): Int {
    var index = 0
    var openParenthesis = 0
    do {
        if (this[index].contains("(")) {
            openParenthesis += this[index].count { it == '(' }
        } else if (this[index].contains(")")) {
            openParenthesis -= this[index].count { it == ')' }
        }
        index++
    } while (openParenthesis != 0)
    return index - 1
}


private fun calculateNumbers(firstValue: String, operator: String, secondValue: String): String =
    when (operator) {
        "+" -> firstValue.toLong() + secondValue.toLong()
        "*" -> firstValue.toLong() * secondValue.toLong()
        else -> throw IllegalArgumentException("Unknown Operator")
    }.toString()

