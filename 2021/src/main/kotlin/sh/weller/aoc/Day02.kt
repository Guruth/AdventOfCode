package sh.weller.aoc

object Day02 : SomeDay<Pair<String, Int>, Int> {
    override fun partOne(input: List<Pair<String, Int>>): Int {
        var posX = 0
        var posY = 0

        for ((direction, amount) in input) {
            when {
                direction == "forward" -> posX += amount
                direction == "down" -> posY += amount
                direction == "up" -> posY -= amount
            }
        }
        return posX * posY
    }

    override fun partTwo(input: List<Pair<String, Int>>): Int {
        var posX = 0
        var posY = 0
        var aim = 0

        for ((direction, amount) in input) {
            when {
                direction == "forward" -> {
                    posX += amount
                    posY += (aim * amount)
                }
                direction == "down" -> aim += amount
                direction == "up" -> aim -= amount
            }
        }

        return posX * posY
    }
}