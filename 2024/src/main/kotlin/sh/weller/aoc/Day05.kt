package sh.weller.aoc

object Day05 : SomeDay<Int> {

    override val day: Int = 5

    override fun partOne(input: List<String>): Int {
        val rules = input.getRules()

        return input.getPages()
            .filter { it.isOrdered(rules) }
            .sumOf { pages ->
                pages.entries
                    .sortedBy { it.value }
                    .map { it.key }[pages.size / 2]
            }
    }

    private fun List<String>.getRules(): Map<Int, List<Int>> =
        take(indexOf(""))
            .map {
                val (first, second) = it.split("|")
                first.toInt() to second.toInt()
            }
            .groupBy { it.first }
            .mapValues { it.value.map { it.second } }

    private fun List<String>.getPages(): List<Map<Int, Int>> =
        drop(indexOf("") + 1)
            .map { it.split(",").map { it.toInt() } }
            .map { update -> update.associateWith { update.indexOf(it) } }

    private fun Map<Int, Int>.isOrdered(rules: Map<Int, List<Int>>) =
        all { (page, index) ->
            val mustBeBeforePages = rules[page]
                ?: return@all true // No rule found for page

            // rules found
            mustBeBeforePages.all {
                val indexOfPageAfter = this[it]
                if (indexOfPageAfter == null) {
                    true
                } else {
                    index < indexOfPageAfter
                }
            }
        }

    override fun partTwo(input: List<String>): Int {
        val rules = input.getRules()

        return input.getPages()
            .filter { it.isOrdered(rules).not() }
            .map {
                it.keys
                    .sortedWith { a, b ->
                        val aBeforePages = rules[a]
                        if (aBeforePages == null) {
                            0
                        } else {
                            if (aBeforePages.contains(b)) {
                                -1
                            } else {
                                1
                            }
                        }
                    }
            }
            .sumOf { it[it.size / 2] }
    }
}