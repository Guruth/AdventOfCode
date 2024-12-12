package sh.weller.adventofcode.twentytwenty


fun List<String>.day13Part1(): Int {
    val earliestTime = this[0].toInt()
    val busLines = this[1].split(',').filter { it != "x" }.map { it.toInt() }

    val earliestPerLine: List<Pair<Int, Int>> = busLines.map {
        var counter = 1
        while (true) {
            val tmpTime = it * counter
            if (tmpTime >= earliestTime) {
                return@map Pair(it, tmpTime)
            }
            counter++
        }
        return@map Pair(0, 0)
    }

    val fastestBus = earliestPerLine.minByOrNull { it.second }!!
    return fastestBus.first * (fastestBus.second - earliestTime)
}


fun List<String>.day13Part2(): String {
    val busLines = this[1].split(',')

    var wolframAlphaFormula = ""
    var char = 'a'
    busLines.forEachIndexed { index, s ->
        if (s != "x") {
            wolframAlphaFormula += ", t+$index = $s*$char"
            char++
        }
    }

    return "Paste this into Wolfram Alpha: ${wolframAlphaFormula.drop(2)}"
}