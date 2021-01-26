import sh.weller.adventofcode.util.multipliedSum

fun List<Int>.getMultipliedForSumOfDepth(sum: Int, depth: Int): Long {
    var result: Long = 0
    val explodedList = this.permutations(depth)
    explodedList.forEach {
        if (it.sum() == sum) {
            result = it.multipliedSum()
        }
    }
    return result
}

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
