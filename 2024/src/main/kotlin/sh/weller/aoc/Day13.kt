package sh.weller.aoc

object Day13 : SomeDay<Long> {

    override val day: Int = 13

    override fun partOne(input: List<String>): Long {
        return input
            .filter { it.isNotBlank() }
            .windowed(3, step = 3)
            .map { (a, b, p) ->
                val aButton = a.parseButton()
                val bButton = b.parseButton()
                val prize = p.parsePrize()

                repeat(100) { a ->
                    repeat(100) { b ->
                        val targetX = a * aButton.first + b * bButton.first
                        val targetY = a * aButton.second + b * bButton.second

                        if (targetX == prize.first && targetY == prize.second) {
                            return@map (a * 3) + b.toLong()
                        }
                    }
                }
                return@map 0
            }
            .sum()
    }

    private fun String.parseButton(): Pair<Long, Long> {
        val x = this.substring(this.indexOf("X+") + 2, this.indexOf(",")).toLong()
        val y = this.substring(this.indexOf("Y+") + 2).toLong()
        return x to y
    }

    private fun String.parsePrize(): Pair<Long, Long> {
        val x = this.substring(this.indexOf("X=") + 2, this.indexOf(",")).toLong()
        val y = this.substring(this.indexOf("Y=") + 2).toLong()
        return x to y
    }

    override fun partTwo(input: List<String>): Long {
        return input
            .filter { it.isNotBlank() }
            .windowed(3, step = 3)
            .map { (a, b, p) ->
                val aButton = a.parseButton()
                val bButton = b.parseButton()
                val prize = p.parsePrizePart2()

                val determinant = ((aButton.first * bButton.second) - (aButton.second * bButton.first))

                val a = ((prize.first * bButton.second) - (prize.second * bButton.first)) / determinant
                val b = ((aButton.first * prize.second) - (aButton.second * prize.first)) / determinant


                if (a * aButton.first + b * bButton.first == prize.first && a * aButton.second + b * bButton.second == prize.second) {
                    a * 3 + b
                } else {
                    0
                }
            }
            .sum()
    }

    private fun String.parsePrizePart2(): Pair<Long, Long> {
        val x = this.substring(this.indexOf("X=") + 2, this.indexOf(",")).toLong()
        val y = this.substring(this.indexOf("Y=") + 2).toLong()
        return x + 10000000000000 to y + 10000000000000
    }

}
