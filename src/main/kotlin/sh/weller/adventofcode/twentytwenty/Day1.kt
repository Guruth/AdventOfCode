fun List<Int>.getMultipliedForSumOfDepth(sum: Int, depth: Int): Int? {
    val explodedList = this.permutations(depth)
    explodedList.forEach {
        if (it.sum() == sum) {
            return it.multipliedSum()
        }
    }
    return null
}

fun List<Int>.multipliedSum(): Int =
    this.reduce { i, j -> i * j }

fun List<Int>.permutations(depth: Int = 2): List<List<Int>> =
    this.preparePermutations().permutationsRec(depth)

private fun List<Int>.preparePermutations(): List<List<Int>> =
    this.map {
        listOf(it)
    }

private fun List<List<Int>>.permutationsRec(depth: Int): List<List<Int>> =
    if (depth == 1) {
        this
    } else {
        val retList = mutableListOf<List<Int>>()
        val explodedList = this.permutationsRec(depth - 1)
        this.forEach { outer ->
            explodedList
                .forEach { inner ->
                    retList.add(outer + inner)
                }
        }
        retList
    }
