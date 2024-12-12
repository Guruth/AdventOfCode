package sh.weller.adventofcode.twentytwenty


fun List<String>.countBagsThatareContained(color: String): Long {
    val parsedInput = this.parseBaggageInput()


//    var foundOther = true
//    var searchColors = listOf(color)
//    val rootBag =
//        ContainedBag(
//            color,
//            parsedInput.getContained(listOf(color)).map { Pair(ContainedBag(it.first, emptyList()), it.second) })
//    do {
//        val searchColors = rootBag.containedBags
//    } while (foundOther)

    return 0
}

data class ContainedBag(
    val color: String,
    var containedBags: List<Pair<ContainedBag, Int>>?
)

fun List<List<Pair<String, Int>>>.getContained(colors: List<String>): List<Pair<String, Int>> =
    this
        .filter { colors.contains(it.first().first) }
        .map { it.drop(1) }
        .flatten()


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


fun List<List<Pair<String, Int>>>.findWhichContain(colors: List<String>): List<String> {
    val canContain = mutableListOf<String>()
    this.forEach {
        if (it.drop(1).map { bag -> bag.first }.containsOneOf(colors)) {
            canContain.add(it.first().first)
        }
    }
    return canContain
}

fun List<String>.containsOneOf(others: List<String>): Boolean {
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

fun List<List<Pair<String, Int>>>.toBagNode(): List<List<BagNode>> =
    this.map {
        it.map { bag ->
            BagNode(bag.first, bag.second, emptyList())
        }
    }

data class BagNode(
    val color: String,
    val number: Int,
    val bagNodes: List<BagNode>
)


fun List<String>.parseBaggageInput(): List<List<Pair<String, Int>>> {
    val parsedBags = mutableListOf<List<Pair<String, Int>>>()
    var currentLine = mutableListOf<Pair<String, Int>>()

    this.forEach {
        val bagsList = it.removeSuffix(".").split(Regex("contain|,"))
        bagsList.forEach { bag ->
            val splitBag = bag.trim().split(" ")
            var number = 0
            var color = ""
            if (splitBag[0].toIntOrNull() != null) {
                number = splitBag[0].toInt()
                color = splitBag[1] + " " + splitBag[2]
            } else if (splitBag[0] == "no") {
                number = 1
                color = "other"
            } else {
                number = 1
                color = splitBag[0] + " " + splitBag[1]
            }
            currentLine.add(Pair(color, number))

        }
        parsedBags.add(currentLine)
        currentLine = mutableListOf()
    }

    return parsedBags
}
