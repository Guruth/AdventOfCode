package sh.weller.adventofcode.twentytwenty


fun List<String>.countBagsThatAreContained(color: String): Long {
    val parsedInput = this.parseBaggageInput()

    val rootBag = ContainedBag(color).appendLevel(parsedInput)

    return rootBag!!.getNumOfContainedBags()
}

private fun ContainedBag.getNumOfContainedBags(): Long {
    if (this.containedBags.isEmpty()) {
        return 0
    }
    return this.containedBags.map { it.getNumOfContainedBags() + 1 }.sum()
}

private fun ContainedBag.appendLevel(parsedInput: List<List<Pair<String, Int>>>): ContainedBag? {
    if (this.color == "other") {
        return null
    }
    val containedBagsLine = parsedInput.find { it.first().first == this.color }!!.drop(1)

    containedBagsLine
        .forEach {
            val tmpBag = ContainedBag(it.first).appendLevel(parsedInput)
            if (tmpBag != null) {
                repeat((1..it.second).count()) { this.containedBags.add(tmpBag) }
            }
        }
    return this
}

private data class ContainedBag(
    val color: String,
    var containedBags: MutableList<ContainedBag> = mutableListOf()
)


fun List<String>.countBagsWhichContain(color: String): Int {
    val parsedInput = this.parseBaggageInput()

    val foundColors = mutableListOf<String>()
    var searchColors = listOf(color)
    var foundOther = true
    do {
        val tmpColors = parsedInput.findWhichContain(searchColors)
        if (tmpColors.isEmpty()) {
            foundOther = false
        } else {
            searchColors = tmpColors
            foundColors.addAll(tmpColors)
        }
    } while (foundOther)

    return foundColors.distinct().size
}


private fun List<List<Pair<String, Int>>>.findWhichContain(colors: List<String>): List<String> {
    val canContain = mutableListOf<String>()
    this.forEach {
        if (it.drop(1).map { bag -> bag.first }.containsOneOf(colors)) {
            canContain.add(it.first().first)
        }
    }
    return canContain
}

private fun List<String>.containsOneOf(others: List<String>): Boolean {
    var contains = false
    others.forEach { other ->
        this.forEach {
            if (it.contains(other)) {
                contains = true
            }
        }
    }
    return contains
}


private fun List<String>.parseBaggageInput(): List<List<Pair<String, Int>>> {
    val parsedBags = mutableListOf<List<Pair<String, Int>>>()
    var currentLine = mutableListOf<Pair<String, Int>>()

    this.forEach {
        val bagsList = it.removeSuffix(".").split(Regex("contain|,"))
        bagsList.forEach { bag ->
            val splitBag = bag.trim().split(" ")
            val number: Int
            val color: String
            when {
                splitBag[0].toIntOrNull() != null -> {
                    number = splitBag[0].toInt()
                    color = splitBag[1] + " " + splitBag[2]
                }
                splitBag[0] == "no" -> {
                    number = 0
                    color = "other"
                }
                else -> {
                    number = 1
                    color = splitBag[0] + " " + splitBag[1]
                }
            }
            currentLine.add(Pair(color, number))

        }
        parsedBags.add(currentLine)
        currentLine = mutableListOf()
    }

    return parsedBags
}
