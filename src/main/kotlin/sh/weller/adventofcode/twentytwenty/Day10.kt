package sh.weller.adventofcode.twentytwenty


fun List<Int>.findPossibleVariationsBruteForce(): Int {
    val sorted = (listOf(0) + this.sorted() + listOf(this.maxOf { it } + 3))
    return sorted.rec().size
}

private fun List<Int>.rec(): List<Int> {
    if (this.size == 1) {
        return this
    }
    val withoutFirst = this.drop(1)
    val filtered = withoutFirst.filter { it - 3 <= this.first() }
    return filtered.flatMap { drop(indexOf(it)).rec() }
}


fun List<Int>.findPossibleVariations(): Long {
    val sorted = (listOf(0) + this.sorted() + listOf(this.maxOf { it } + 3))
    val depthList = mutableListOf<Long>(1)

    sorted.forEachIndexed { index, i ->
        var counter = 0L
        sorted.take(index).takeLast(3).forEachIndexed { innerIndex, j ->
            if (i - 3 <= j) {
                val atPoint = depthList.getOrElse(index - innerIndex) { 0L }
                counter += atPoint
            }
        }
        depthList.add(maxOf(counter, depthList[index]))
        println(sorted.take(index).takeLast(3))
    }
    return depthList.last()
}


fun List<Int>.countJoltDifferences(startJoltage: Int = 0): Long {
    var currentJoltage = startJoltage
    var oneDifference: Long = 0
    var threeDifference: Long = 1

    this.sorted().forEach { value ->
        val difference = value - currentJoltage
        if (difference >= 1 || difference <= 3) {
            when (difference) {
                1 -> {
                    oneDifference++
                }
                3 -> {
                    threeDifference++
                }
            }
            currentJoltage += difference
        }
    }
    return oneDifference * threeDifference
}