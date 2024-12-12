package sh.weller.aoc

import sh.weller.aoc.util.to2DList

object Day06 : SomeDay<Int> {

    override val day: Int = 6

    override fun partOne(input: List<String>): Int {
        val map = input.to2DList().map { it.toMutableList() }.toMutableList()
        map.patrol()
        return map.flatten().count { it == 'X' }
    }

    private fun List<List<Char>>.getCurrent(): Triple<Int, Int, Char> {
        this.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { index, c ->
                if (c == '^' || c == '>' || c == '<' || c == 'v') {
                    return Triple(lineIndex, index, c)
                }
            }
        }
        throw IllegalArgumentException("Position not found")
    }

    private fun List<List<Char>>.getAt(position: Pair<Int, Int>): Char? =
        this.getOrNull(position.first)?.getOrNull(position.second)

    private fun MutableList<MutableList<Char>>.setAt(position: Pair<Int, Int>, newChar: Char) {
        this[position.first][position.second] = newChar
    }

    private fun MutableList<MutableList<Char>>.rotate(current: Triple<Int, Int, Char>) {
        when (current.third) {
            '^' -> setAt(current.first to current.second, '>')
            '>' -> setAt(current.first to current.second, 'v')
            'v' -> setAt(current.first to current.second, '<')
            '<' -> setAt(current.first to current.second, '^')
        }
    }

    private fun MutableList<MutableList<Char>>.move(): Boolean {
        val current = this.getCurrent()

        val nextCoordinates = current.getNext()
        val nextTile = getAt(nextCoordinates)

        when (nextTile) {
            null -> {
                setAt(current.first to current.second, 'X')
                return true
            }

            '#', 'O' -> {
                rotate(current)
                return false
            }

            else -> {
                setAt(nextCoordinates, current.third)
                setAt(current.first to current.second, 'X')

                return false
            }
        }
    }

    private fun Triple<Int, Int, Char>.getNext(): Pair<Int, Int> {
        return when (third) {
            '^' -> first - 1 to second
            '>' -> first to second + 1
            'v' -> first + 1 to second
            '<' -> first to second - 1
            else -> throw IllegalArgumentException("Could not move")
        }
    }

    override fun partTwo(input: List<String>): Int {
        val map = input.to2DList().map { it.toMutableList() }.toMutableList()
        val initialPosition = map.getCurrent()
        map.patrol()

        var totalNumber = map.size * map.size
        var possibleObstructions = map.flatten().count { it == 'X' }

        var count = 0
        repeat(map.size) { y ->
            repeat(map.size) { x ->
                if (map.getAt(y to x) == 'X') {
                    if ((initialPosition.first == y && initialPosition.second == x).not()) {
                        val mapCopy = input.to2DList().map { it.toMutableList() }.toMutableList()
                        mapCopy.setAt(y to x, 'O')
                        if (mapCopy.containsLoop()) {
                            count++
                        }
                    }
                    possibleObstructions--
                }
                totalNumber--
                println("Total to go: $totalNumber - Possible to go: $possibleObstructions")
            }
        }

        return count
    }

    private fun MutableList<MutableList<Char>>.patrol() {
        var leftMap: Boolean
        do {
            leftMap = move()
        } while (!leftMap)
    }

    private fun MutableList<MutableList<Char>>.containsLoop(): Boolean {
        val history = mutableSetOf<Triple<Int, Int, Char>>()

        var leftMap: Boolean
        do {
            val current = getCurrent()

            if (history.contains(current)) {
                return true
            }
            history.add(current)
            leftMap = move()
        } while (!leftMap)

        return false
    }

}