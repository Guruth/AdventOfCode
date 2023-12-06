package sh.weller.aoc

object Day06 : SomeDay<List<Long>, Long> {
    override fun partOne(input: List<List<Long>>): Long {
        val time = input.first()
        val distance = input.last()

        return time.zip(distance)
            .map { (time, distance) ->
                var reachedDistance = 0L
                var minHoldTime = 0L
                while (reachedDistance <= distance) {
                    minHoldTime += 1
                    reachedDistance = minHoldTime * (time - minHoldTime)
                }

                reachedDistance = 0L
                var maxHoldTime = time - 1
                while (reachedDistance <= distance) {
                    maxHoldTime -= 1
                    reachedDistance = maxHoldTime * (time - maxHoldTime)
                }

                return@map maxHoldTime - minHoldTime + 1
            }
            .reduce { sum, possibilities -> sum * possibilities }

    }

    override fun partTwo(input: List<List<Long>>): Long {
        val time = input.first().joinToString("").toLong()
        val distance = input.last().joinToString("").toLong()

        var reachedDistance = 0L
        var minHoldTime = 0L
        while (reachedDistance <= distance) {
            minHoldTime += 1
            reachedDistance = minHoldTime * (time - minHoldTime)
        }

        reachedDistance = 0L
        var maxHoldTime = time - 1L
        while (reachedDistance <= distance) {
            maxHoldTime -= 1
            reachedDistance = maxHoldTime * (time - maxHoldTime)
        }

        return maxHoldTime - minHoldTime + 1
    }

}