package sh.weller.adventofcode.twentytwenty

import multipliedSum


fun List<List<Char>>.countTreesWhileNavigatingForMultiple(patternList: List<List<Pair<Direction, Int>>>): Long =
    patternList
        .map {
            this.countTreesWhileNavigating(it)
        }
        .multipliedSum()


fun List<List<Char>>.countTreesWhileNavigating(pattern: List<Pair<Direction, Int>>): Int {
    var x = 0
    var y = 0

    var lastEncounter: Char?
    var foundTrees = 0
    do {
        pattern.forEach {
            when (it.first) {
                Direction.DOWN -> y += it.second
                Direction.RIGHT -> x += it.second
            }
        }
        lastEncounter = this.getCharAtPosition(x, y)
        if (lastEncounter != null) {
            if (lastEncounter == '#') {
                foundTrees++
            }
        }

    } while (lastEncounter != null)

    return foundTrees
}

private fun List<List<Char>>.getCharAtPosition(x: Int, y: Int): Char? {
    var tmpX = x

    if (y >= this.size) {
        return null
    }
    while (tmpX >= this[0].size) {
        tmpX -= this[0].size
    }
    return this[y][tmpX]
}


enum class Direction {
    RIGHT, DOWN;
}