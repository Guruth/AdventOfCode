package sh.weller.adventofcode.twentytwenty


fun List<String>.day18Part1(): Long =
    this.map { it.split(" ").filter { it.isNotBlank() } }
        .map { it.calculateBasic() }
        .sum()


private fun List<String>.calculateBasic(): Long {
    when {
        this.size == 1 -> {
            return this.first().toLong()
        }
        this[0].startsWith("(") -> {
            val closingExpressionIndex = this.indexOfClosingParenthesis()
            val subExpression = this.subList(1, closingExpressionIndex - 1).toMutableList()
            subExpression.add(0, this[0].drop(1))
            subExpression.add(this[closingExpressionIndex - 1].dropLast(1))
            val subExpressionResult = subExpression.calculateBasic().toString()
            return (listOf(subExpressionResult) + this.drop(closingExpressionIndex)).calculateBasic()
        }
        this[2].startsWith("(") -> {
            val closingExpressionIndex = this.drop(2).indexOfClosingParenthesis() + 1
            val subExpression = this.subList(3, closingExpressionIndex).toMutableList()
            subExpression.add(0, this[2].drop(1))
            subExpression.add(this[closingExpressionIndex].dropLast(1))
            val subExpressionResult = subExpression.calculateBasic().toString()
            val expressionResult = calculateNumbers(this[0], this[1], subExpressionResult)
            return (listOf(expressionResult) + this.drop(closingExpressionIndex + 1)).calculateBasic()
        }
        else -> {
            val tmpResult = calculateNumbers(this[0], this[1], this[2])
            return (listOf(tmpResult) + this.drop(3)).calculateBasic()
        }
    }
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
    return index
}


private fun calculateNumbers(firstValue: String, operator: String, secondValue: String): String =
    when (operator) {
        "+" -> firstValue.toLong() + secondValue.toLong()
        "-" -> firstValue.toLong() - secondValue.toLong()
        "*" -> firstValue.toLong() * secondValue.toLong()
        "/" -> firstValue.toLong() / secondValue.toLong()
        else -> throw IllegalArgumentException("Unknown Operator")
    }.toString()

