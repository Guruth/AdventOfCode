package sh.weller.aoc

object Day08 : SomeDay<String, Long> {
    override fun partOne(input: List<String>): Long {
        val directions = input.first().toCharArray()

        val nodes = input.drop(2)
            .associate { line ->
                val splitLine = line.split("=").map { it.trim() }
                val key = splitLine.first()
                val nextNodes = splitLine.last().drop(1).dropLast(1)
                    .split(",").map { it.trim() }

                val left = nextNodes.first()
                val right = nextNodes.last()

                return@associate key to (left to right)
            }

        var currentNode = "AAA"
        var currentDirectionPointer = 0
        var stepCounter = 0L

        do {
            val (left, right) = nodes[currentNode]!!
            val direction = directions[currentDirectionPointer]

            currentNode = when (direction) {
                'L' -> left
                'R' -> right
                else -> throw IllegalArgumentException("Unknown Direction")
            }

            currentDirectionPointer += 1
            if (currentDirectionPointer == directions.size) {
                currentDirectionPointer = 0
            }

            stepCounter += 1
        } while (currentNode != "ZZZ")

        return stepCounter
    }


    override fun partTwo(input: List<String>): Long {
        val directions = input.first().toCharArray()

        val nodes = input.drop(2)
            .associate { line ->
                val splitLine = line.split("=").map { it.trim() }
                val key = splitLine.first()
                val nextNodes = splitLine.last().drop(1).dropLast(1)
                    .split(",").map { it.trim() }

                val left = nextNodes.first()
                val right = nextNodes.last()

                return@associate key to (left to right)
            }

        return nodes.keys
            .filter { it.endsWith("A") }
            .map {
                var currentNode = it
                var currentDirectionPointer = 0
                var stepCounter = 0L

                do {
                    val (left, right) = nodes[currentNode]!!
                    val direction = directions[currentDirectionPointer]

                    currentNode = when (direction) {
                        'L' -> left
                        'R' -> right
                        else -> throw IllegalArgumentException("Unknown Direction")
                    }

                    currentDirectionPointer += 1
                    if (currentDirectionPointer == directions.size) {
                        currentDirectionPointer = 0
                    }

                    stepCounter += 1
                } while (currentNode.endsWith("Z").not())

                return@map stepCounter
            }.reduce { acc, i -> findLCM(acc, i) }
    }

    // https://www.baeldung.com/kotlin/lcm
    private fun findLCM(a: Long, b: Long): Long {
        val larger = if (a > b) a else b
        val maxLcm = a * b
        var lcm = larger
        while (lcm <= maxLcm) {
            if (lcm % a == 0L && lcm % b == 0L) {
                return lcm
            }
            lcm += larger
        }
        return maxLcm
    }
}