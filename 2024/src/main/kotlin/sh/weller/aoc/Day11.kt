package sh.weller.aoc

object Day11 : SomeDay<Long> {

    override val day: Int = 11

    override fun partOne(input: List<String>): Long {
        val stones = input.first().split(" ").map { it.toLong() }
        val cache = mutableMapOf<Pair<Int, Long>, Long>()

        var numberOfStones = 0L
        for (stone in stones) {
            numberOfStones += blinkCached(25, stone, cache)
        }

        return numberOfStones
    }

    private fun blinkCached(iteration: Int, stone: Long, cache: MutableMap<Pair<Int, Long>, Long>): Long {
        if (iteration == 0) {
            return 1
        }
        val cachedValue = cache[iteration to stone]
        if (cachedValue != null) {
            return cachedValue
        }
        val newValue = if (stone == 0L) {
            blinkCached(iteration - 1, 1L, cache)
        } else if ((stone.toString().toCharArray().size) % 2 == 0) {
            val stoneAsString = stone.toString().toCharArray()
            val firstHalf = stoneAsString.take(stoneAsString.size / 2).joinToString("").toLong()
            val secondHalf = stoneAsString.takeLast(stoneAsString.size / 2).joinToString("").toLong()

            blinkCached(iteration - 1, firstHalf, cache) +
                    blinkCached(iteration - 1, secondHalf, cache)
        } else {
            blinkCached(iteration - 1, stone * 2024, cache)
        }

        cache[iteration to stone] = newValue
        return newValue
    }

    override fun partTwo(input: List<String>): Long {
        val stones = input.first().split(" ").map { it.toLong() }
        val cache = mutableMapOf<Pair<Int, Long>, Long>()

        var numberOfStones = 0L
        for (stone in stones) {
            numberOfStones += blinkCached(75, stone, cache)
        }

        return numberOfStones
    }
}
