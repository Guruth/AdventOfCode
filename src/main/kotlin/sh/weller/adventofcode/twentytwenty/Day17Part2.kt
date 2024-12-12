package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.copy


fun List<String>.day17Part2(numCycles: Int = 6): Int {
    val initialGrid = this.map { it.toCharArray().toMutableList() }.toMutableList()
    val cube: MutableList<MutableList<MutableList<MutableList<Char>>>> = mutableListOf(mutableListOf(initialGrid))
    repeat(initialGrid.size) {
        cube.expand()
    }

    repeat(numCycles) {
        cube.expand()
        cube.mutate()
    }

    return cube.countActive()
}


private fun List<List<List<List<Char>>>>.countActive(): Int =
    this.flatten().flatten().flatten().count { it == '#' }


private fun MutableList<MutableList<MutableList<MutableList<Char>>>>.expand() {
    val newSize = this.size + 2
    this.forEach { wLayer ->
        wLayer.forEach { zLayer ->
            zLayer.forEach { yLayer ->
                yLayer.add('.')
                yLayer.add(0, '.')
            }
            zLayer.add(newEmptyRow(newSize))
            zLayer.add(0, newEmptyRow(newSize))
        }
        wLayer.add(newEmptySlice(newSize))
        wLayer.add(0, newEmptySlice(newSize))
    }
    this.add(newEmptyDimension(newSize))
    this.add(0, newEmptyDimension(newSize))
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

private fun newEmptyDimension(size: Int): MutableList<MutableList<MutableList<Char>>> {
    val tmpW = mutableListOf<MutableList<MutableList<Char>>>()
    repeat(size) {
        tmpW.add(newEmptySlice(size))
    }
    return tmpW
}

private fun MutableList<MutableList<MutableList<MutableList<Char>>>>.mutate() {
    val currentCube: List<List<List<List<Char>>>> = this.copy()
    this.forEachIndexed { wIndex, wLayer ->
        wLayer.forEachIndexed { zIndex, zLayer ->
            zLayer.forEachIndexed { yIndex, yLayer ->
                yLayer.forEachIndexed { xIndex, _ ->
                    this[wIndex][zIndex][yIndex][xIndex] = currentCube.mutateAtPoint(wIndex, zIndex, yIndex, xIndex)
                }
            }
        }
    }
}

private fun List<List<List<List<Char>>>>.mutateAtPoint(w: Int, z: Int, y: Int, x: Int): Char {
    val point = this[w][z][y][x]
    val neighbors = listOfNotNull(
        // W0
        this.getOrNull(w)?.getOrNull(z)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(w)?.getOrNull(z)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(w)?.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(w)?.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(w)?.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(w)?.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(w)?.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(w)?.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x + 1),

        this.getOrNull(w)?.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x),
        this.getOrNull(w)?.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(w)?.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(w)?.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(w)?.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(w)?.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(w)?.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(w)?.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(w)?.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x + 1),

        this.getOrNull(w)?.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x),
        this.getOrNull(w)?.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(w)?.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(w)?.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(w)?.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(w)?.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(w)?.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(w)?.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(w)?.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x + 1),


        // W - 1
        this.getOrNull(w - 1)?.getOrNull(z)?.getOrNull(y)?.getOrNull(x),
        this.getOrNull(w - 1)?.getOrNull(z)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(w - 1)?.getOrNull(z)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(w - 1)?.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(w - 1)?.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(w - 1)?.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(w - 1)?.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(w - 1)?.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(w - 1)?.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x + 1),

        this.getOrNull(w - 1)?.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x),
        this.getOrNull(w - 1)?.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(w - 1)?.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(w - 1)?.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(w - 1)?.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(w - 1)?.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(w - 1)?.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(w - 1)?.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(w - 1)?.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x + 1),

        this.getOrNull(w - 1)?.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x),
        this.getOrNull(w - 1)?.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(w - 1)?.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(w - 1)?.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(w - 1)?.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(w - 1)?.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(w - 1)?.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(w - 1)?.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(w - 1)?.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x + 1),


        // W + 1
        this.getOrNull(w + 1)?.getOrNull(z)?.getOrNull(y)?.getOrNull(x),
        this.getOrNull(w + 1)?.getOrNull(z)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(w + 1)?.getOrNull(z)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(w + 1)?.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(w + 1)?.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(w + 1)?.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(w + 1)?.getOrNull(z)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(w + 1)?.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(w + 1)?.getOrNull(z)?.getOrNull(y + 1)?.getOrNull(x + 1),

        this.getOrNull(w + 1)?.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x),
        this.getOrNull(w + 1)?.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(w + 1)?.getOrNull(z + 1)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(w + 1)?.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(w + 1)?.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(w + 1)?.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(w + 1)?.getOrNull(z + 1)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(w + 1)?.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(w + 1)?.getOrNull(z + 1)?.getOrNull(y + 1)?.getOrNull(x + 1),

        this.getOrNull(w + 1)?.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x),
        this.getOrNull(w + 1)?.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x - 1),
        this.getOrNull(w + 1)?.getOrNull(z - 1)?.getOrNull(y)?.getOrNull(x + 1),
        this.getOrNull(w + 1)?.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x),
        this.getOrNull(w + 1)?.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x),
        this.getOrNull(w + 1)?.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x - 1),
        this.getOrNull(w + 1)?.getOrNull(z - 1)?.getOrNull(y - 1)?.getOrNull(x + 1),
        this.getOrNull(w + 1)?.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x - 1),
        this.getOrNull(w + 1)?.getOrNull(z - 1)?.getOrNull(y + 1)?.getOrNull(x + 1),

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