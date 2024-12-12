package sh.weller.adventofcode.twentytwenty


fun List<String>.countAllYesAnswersInGroup(): Int =
    this.parseInput().map { it.countAllYesAnswers() }.sum()

private fun List<List<Char>>.countAllYesAnswers(): Int =
    this.flatten()
        .groupBy { it }
        .mapValues { it.value.size }
        .filter { it.value == this.size }
        .count()


fun List<String>.countCommonAnswersInGroup(): Int =
    this.parseInput().map { it.countCommonAnswers() }.sum()

private fun List<List<Char>>.countCommonAnswers(): Int =
    this.flatten()
        .groupBy { it }
        .count()

private fun List<String>.parseInput(): List<List<List<Char>>> {
    val allGroups = mutableListOf<List<List<Char>>>()
    var group = mutableListOf<List<Char>>()

    this.forEachIndexed { i, line ->
        if (line.isBlank()) {
            allGroups.add(group)
            group = mutableListOf()
        } else {
            group.add(line.toCharArray().toList())
        }
        if ((i + 1) == this.size) {
            allGroups.add(group)
        }
    }
    return allGroups
}