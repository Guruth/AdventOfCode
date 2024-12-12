package sh.weller.adventofcode.twentytwenty


fun List<String>.executeMutatedInstructions(): Int {
    var accValue = 0
    this.forEachIndexed { index, _ ->
        val tmpList = this.toMutableList()
        if (tmpList[index].startsWith("jmp")) {
            tmpList[index] = tmpList[index].replace("jmp", "nop")
        } else if (tmpList[index].startsWith("nop")) {
            tmpList[index] = tmpList[index].replace("nop", "jmp")
        } else {
            return@forEachIndexed
        }
        val result = tmpList.debugInstructions()

        if (result.first >= this.size) {
            accValue = result.second
            println("Found solution ${result.second}")
        }
    }
    return accValue
}

fun List<String>.debugInstructions(): Pair<Int, Int> {
    val instructionCounter = mutableListOf<Int>()
    var acc = 0
    var stackPointer = 0

    while (instructionCounter.contains(stackPointer).not() && stackPointer < this.size) {
        instructionCounter.add(stackPointer)
        val (instruction, argument) = this.getInstructionAt(stackPointer)

        when (instruction) {
            "nop" -> {
                stackPointer++
            }
            "jmp" -> {
                val jumpLocation = argument.toInt()
                stackPointer += jumpLocation
            }
            "acc" -> {
                acc += argument.toInt()
                stackPointer++
            }
            else -> {
                throw IllegalArgumentException("Operation $instruction not know")
            }
        }

    }

    return Pair(stackPointer, acc)
}

 fun List<String>.executeInstructions(): Int {
    val instructionCounter = mutableListOf<Int>()
    var acc = 0
    var stackPointer = 0

    var lastInstruction = false

    while (instructionCounter.contains(stackPointer).not() && !lastInstruction) {
        instructionCounter.add(stackPointer)
        val (instruction, argument) = this.getInstructionAt(stackPointer)
//        println("Next Instruction $instruction $argument - Acc: $acc Pointer:$stackPointer")
        when (instruction) {
            "nop" -> {
                stackPointer++
            }
            "jmp" -> {
                val jumpLocation = argument.toInt()
                stackPointer += jumpLocation
            }
            "acc" -> {
                acc += argument.toInt()
                stackPointer++
            }
            else -> {
                throw IllegalArgumentException("Operation $instruction not know")
            }
        }

        if (stackPointer == this.size) {
            println("Last Line reached!")
            lastInstruction = true
        }
    }

    return acc
}


private fun List<String>.getInstructionAt(position: Int): Pair<String, String> =
    this[position].parseInstruction()

private fun String.parseInstruction(): Pair<String, String> =
    this.split(" ").let { Pair(it[0], it[1]) }