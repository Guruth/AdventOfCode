package sh.weller.adventofcode.twentytwenty


fun List<Int>.day15(until: Int = 2020): Long {
    val memoryMap: MutableMap<Long, Pair<Long?, Long>> = mutableMapOf()
    var lastNumber = 0L

    this.forEachIndexed { index, i ->
        memoryMap[i.toLong()] = Pair(null, index + 1L)
        lastNumber = i.toLong()
    }

    ((this.size + 1)..until).forEach { i ->
        var currentNumber = 0L
        if (memoryMap[lastNumber]!!.first == null) {
            if (memoryMap[currentNumber] == null) {
                memoryMap[currentNumber] = Pair(null, i.toLong())
            } else {
                memoryMap[currentNumber] = Pair(memoryMap[currentNumber]!!.second, i.toLong())
            }
        } else {
            currentNumber = memoryMap[lastNumber]!!.second - memoryMap[lastNumber]!!.first!!
            memoryMap[currentNumber] = Pair(memoryMap[currentNumber]?.second, i.toLong())
        }
        lastNumber = currentNumber
    }
    return lastNumber
}
