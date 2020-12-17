package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.copy


fun List<String>.day17Part1(numCycles: Int = 6): Int {
    val initialGrid = this.map { it.toCharArray().toMutableList() }.toMutableList()
    val cube: MutableList<MutableList<MutableList<Char>>> = mutableListOf(initialGrid)
    repeat(initialGrid.size) {
        cube.expand()
    }

    repeat(numCycles) {
        cube.expand()
        cube.mutate()
    }

    return cube.countActive()
}


private fun List<List<List<Char>>>.countActive(): Int =
    this.flatten().flatten().count { it == '#' }

private fun List<List<List<Char>>>.printCube(round: Int) {
    println()
    println("Round $round")
    this.forEachIndexed { index, z ->
        println("Z: $index")
        z.forEach { y ->
            println(y.joinToString(""))
        }
    }
}

private fun MutableList<MutableList<MutableList<Char>>>.expand() {
    val newSize = this.size + 2
    this.forEach { zLayer ->
        zLayer.forEach { yLayer ->
            yLayer.add('.')
            yLayer.add(0, '.')
        }
        zLayer.add(newEmptyRow(newSize))
        zLayer.add(0, newEmptyRow(newSize))
    }
    this.add(newEmptySlice(newSize))
    this.add(0, newEmptySlice(newSize))
}

private fun newEmptyRow(size: Int): MutableList<Char> {
    val tmpList = mutableListOf<Char>()
    repeat(size) { tmpList.add('.') }
    return tmpList
}

private fun newEmptySlice(size: Int): MutableList<MutableList<Char>> {
    val tmpY = mutableListOf<MutableList<Char>>()
    repeat(size) {
        tmpY.add(newEmptyRow(size))
    }
    return tmpY
}

private fun MutableList<MutableList<MutableList<Char>>>.mutate() {
    val currentCube: List<List<List<Char>>> = this.copy()
    this.forEachIndexed { zIndex, zLayer ->
        zLayer.forEachIndexed { yIndex, yLayer ->
            yLayer.forEachIndexed { xIndex, _ ->
                this[zIndex][yIndex][xIndex] = currentCube.mutateAtPoint(zIndex, yIndex, xIndex)
            }
        }
    }
}

private fun List<List<List<Char>>>.mutateAtPoint(z: Int, y: Int, x: Int): Char {
    val point = this[z][y][x]
    val neighbors = listOfNotNull(
        this.getOrNull(z)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(z)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x + 1),

        this.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x),
        this.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x + 1),

        this.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x),
        this.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x + 1),
    )
    val activeNeighbors = neighbors.count { it == '#' }
    return if (point == '#') {
        if (activeNeighbors == 2 || activeNeighbors == 3) {
            '#' // Stays active
        } else {
            '.' // Becomes inactive
        }
    } else {
        if (activeNeighbors == 3) {
            '#' // Becomes active
        } else {
            '.' // Remains inactive
        }
    }
}