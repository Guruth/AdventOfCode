package sh.weller.adventofcode.twentytwenty

import kotlin.math.pow


fun List<String>.day14Part2(): Long {
    val memory: MutableMap<Long, Long> = mutableMapOf()
    var mask: List<Char> = "".toCharArray().toList()

    this.forEach { instructionLine ->
        if (instructionLine.isMask()) {
            mask = instructionLine.getMask()
        } else {
            val instruction = instructionLine.getInstruction()
            val binaryAddress =
                Integer.toBinaryString(instruction.first).padStart(36, '0').toCharArray().toMutableList()

            mask.forEachIndexed { index, c ->
                if (c == 'X') {
                    binaryAddress[index] = 'X'
                } else if (c == '1') {
                    binaryAddress[index] = '1'
                }
            }

            val numOfVariations = binaryAddress.count { it == 'X' }
            val memoryLocations = mutableListOf<Long>()
            val binaryCombinations = numOfVariations.getBinaryCombinations()

            binaryCombinations.forEach { binaryCombination ->
                var currentIndex = 0
                val changedAddress = binaryAddress.map {
                    if (it == 'X') {
                        currentIndex++
                        return@map binaryCombination[currentIndex - 1]
                    }
                    return@map it
                }.fold("") { acc, c -> "$acc$c" }
                memoryLocations.add(java.lang.Long.parseLong(changedAddress, 2))
            }

            memoryLocations.forEach {
                memory[it] = instruction.second.toLong()
            }
        }
    }

    return memory.values.sum()
}

private fun Int.getBinaryCombinations(): List<List<Char>> {
    val combinations = mutableListOf<List<Char>>()
    val numCombinations = (2.0).pow(this).toInt()

    for (i in (0 until numCombinations)) {
        combinations.add(Integer.toBinaryString(i).padStart(this, '0').toCharArray().toList())
    }

    return combinations
}


fun List<String>.day14Part1(): Long {
    val memory: MutableMap<Int, List<Char>> = mutableMapOf()
    var mask: List<Char> = "".toCharArray().toList()

    this.forEach {
        if (it.isMask()) {
            mask = it.getMask()
        } else {
            val instruction = it.getInstruction()
            val binaryValue = Integer.toBinaryString(instruction.second).padStart(36, '0').toCharArray().toMutableList()
            mask.forEachIndexed { index, i ->
                if (i != 'X') {
                    binaryValue[index] = i
                }
            }
            memory[instruction.first] = binaryValue
        }
    }

    val integerValues = memory
        .mapValues { it.value.fold("") { acc, c -> "$acc$c" } }
        .mapValues { java.lang.Long.parseLong(it.value, 2) }

    return integerValues.values.sum()
}

private fun String.isMask(): Boolean =
    this.startsWith("mask")

private fun String.getMask(): List<Char> =
    this.split("=")[1].trim().toCharArray().toList()

private fun String.getInstruction(): Pair<Int, Int> {
    val memoryAddress = this.split("=")[0].trim().removePrefix("mem[").removeSuffix("]").toInt()
    val value = this.split("=")[1].trim().toInt()
    return Pair(memoryAddress, value)
}