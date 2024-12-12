package sh.weller.aoc

object Day05 : SomeDay<String, String> {
    override fun partOne(input: List<String>): String {
        val stacks = getStacks(input)
        val operations = getOperations(input)

        for ((amount, from, to) in operations) {
            val fromStack = stacks[from - 1]
            val toStack = stacks[to - 1]
            repeat(amount) {
                if (fromStack.isNotEmpty()) {
                    val crate = fromStack.removeLast()
                    toStack.add(crate)
                }
            }
        }

        return stacks.map { it.last() }.joinToString("")
    }

    override fun partTwo(input: List<String>): String {
        val stacks = getStacks(input)
        val operations = getOperations(input)

        for ((amount, from, to) in operations) {
            val fromStack = stacks[from - 1]
            val toStack = stacks[to - 1]

            val toMove = fromStack.takeLast(amount)
            repeat(amount) {
                if (fromStack.isNotEmpty()) {
                    fromStack.removeLast()
                }
            }
            toStack.addAll(toMove)
        }

        return stacks.map { it.last() }.joinToString("")
    }


    private fun getStacks(input: List<String>): List<MutableList<Char>> {
        val lines = mutableListOf<String>()
        for (line in input) {
            if (line.isBlank()) {
                break
            }
            lines.add(line)
        }

        val charLines = mutableListOf<List<Char>>()
        for (line in lines.dropLast(1)) {
            val chars = line
                .toCharArray()
                .toList()
                .chunked(4)
                .map { it[1] }
            charLines.add(chars)
        }
        charLines.reverse()

        val stacks = mutableListOf<MutableList<Char>>()
        for (line in charLines) {
            for ((i, char) in line.withIndex()) {
                if (char.isLetter()) {
                    val stack = stacks.getOrNull(i)
                    if (stack == null) {
                        val newStack = mutableListOf<Char>()
                        newStack.add(char)
                        stacks.add(newStack)
                    } else {
                        stack.add(char)
                    }
                }
            }
        }
        return stacks
    }

    private fun getOperations(input: List<String>): List<Triple<Int, Int, Int>> {
        val lines = mutableListOf<String>()
        for (line in input.asReversed()) {
            if (line.isBlank()) {
                break
            }
            lines.add(line)
        }
        lines.reverse()

        val operations = mutableListOf<Triple<Int, Int, Int>>()
        for (line in lines) {
            val numbers = line
                .filter { it.isDigit() || it == ' ' }
                .split(" ")
                .filter { it.isNotBlank() }

            val amount = numbers[0].toInt()
            val from = numbers[1].toInt()
            val to = numbers[2].toInt()

            operations.add(Triple(amount, from, to))
        }
        return operations
    }
}