fun List<Int>.getMultipliedForSumOfDepth(sum: Int, depth: Int): Int? {
    val explodedList = this.explode(depth)
    explodedList.forEach {
        if (it.sum() == sum) {
            return it.multipliedSum()
        }
    }
    return null
}

fun List<Int>.multipliedSum(): Int =
    this.reduce { i, j -> i * j }

fun List<Int>.explode(depth: Int = 2): List<List<Int>> =
    this.prepareExplode().explodeRec(depth)

private fun List<Int>.prepareExplode(): List<List<Int>> =
    this.map {
        listOf(it)
    }

private fun List<List<Int>>.explodeRec(depth: Int): List<List<Int>> =
    if (depth == 1) {
        this
    } else {
        val retList = mutableListOf<List<Int>>()
        val explodedList = this.explodeRec(depth - 1)
        this.forEach { outer ->
            explodedList
                .forEach { inner ->
                    retList.add(outer + inner)
                }
        }
        retList
    }
