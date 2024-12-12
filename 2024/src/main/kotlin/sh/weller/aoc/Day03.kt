package sh.weller.aoc

object Day03 : SomeDay<Int> {

    override val day: Int = 3

    override fun partOne(input: List<String>): Int {
        val mulRegex = "mul\\(\\d\\d?\\d?,\\d\\d?\\d?\\)".toRegex()
        return mulRegex.findAll(input.joinToString(""))
            .map { it.value.drop(4).dropLast(1).split(",").map { it.toInt() } }
            .map { (a, b) -> a * b }
            .sum()
    }

    override fun partTwo(input: List<String>): Int {
        val mulRegex = "mul\\(\\d\\d?\\d?,\\d\\d?\\d?\\)|do\\(\\)|don't\\(\\)".toRegex()
        val matches = mulRegex.findAll(input.joinToString(""))
            .map { it.value }


        var result = 0
        var doMul: Boolean = true
        for (match in matches) {
            if (match == "don't()") {
                doMul = false
            }
            if (match == "do()") {
                doMul = true
            }
            if (doMul && match.startsWith("mul")) {
                val (a, b) = match.drop(4).dropLast(1).split(",").map { it.toInt() }
                result += (a * b)
            }
        }

        return result
    }
}