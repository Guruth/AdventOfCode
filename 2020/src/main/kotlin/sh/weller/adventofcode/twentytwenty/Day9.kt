package sh.weller.adventofcode.twentytwenty

fun List<Int>.findInvalidNumber2(invalidNumber: Int): Int {
    this.forEachIndexed { index, _ ->
        val testList = this.drop(index)
        val resultPair = testList.findSequenceEquals(invalidNumber)
        if (resultPair != null) {
            return resultPair.first + resultPair.second
        }
    }

    return 0
}

fun List<Int>.findSequenceEquals(numberToFind: Int): Pair<Int, Int>? {
    var sum = 0
    this.forEachIndexed { index, i ->
        sum += i
        if (sum == numberToFind) {
            return Pair(this.take(index).minOf { it }, this.take(index).maxOf { it })
        }

    }
    return null
}

fun List<Int>.findInvalidNumber(preambleLength: Int): Int {
    var preamble = this.take(preambleLength).toMutableList()

    this.drop(preambleLength).forEach {
        if (preamble.checkValidity(it)) {
            preamble = preamble.drop(1).toMutableList()
            preamble.add(it)
        } else {
            return it
        }

    }

    return 0
}

fun List<Int>.checkValidity(numToCheck: Int): Boolean {
    var isValid = false
    this.forEach { outer ->
        this.forEach { inner ->
            if (outer + inner == numToCheck) {
                isValid = true
            }
        }
    }
    return isValid
}