package sh.weller.aoc

import sh.weller.aoc.util.copy

object Day10 : SomeDay<String, Int> {
    override fun partOne(input: List<String>): Int {
        val maze: Maze = input.map { it.toCharArray().toList() }

        var currentCoordinate = maze.startingCoordinate()
        var previousCoordinate = -1 to -1

        var stepCounter = 0

        do {
            val tmpCurrent = currentCoordinate
            currentCoordinate = maze.moveForward(currentCoordinate, previousCoordinate)
            previousCoordinate = tmpCurrent

            stepCounter++
        } while (maze.getPipe(currentCoordinate) != 'S')

        return stepCounter / 2
    }


    override fun partTwo(input: List<String>): Int {
        val maze: Maze = input.map { it.toCharArray().toList() }

        val currentMaze = maze.expand().toMutable()
        var previousMaze: MutableMaze

        do {
            previousMaze = currentMaze.copy().toMutable()

            for ((rowIndex, row) in currentMaze.withIndex()) {
                for ((colIndex, c) in row.withIndex()) {
                    if (c == '0') {
                        val surroundings = currentMaze.getAllSurroundingPipes(rowIndex to colIndex)
                        if (surroundings.contains(null)) {
                            currentMaze[rowIndex][colIndex] = '*'
                        }
                        if (surroundings.contains('*')) {
                            currentMaze[rowIndex][colIndex] = '*'
                        }
                    }
                }
            }
        } while (currentMaze != previousMaze)

        val reducedMaze = currentMaze.reduce()

        return reducedMaze.flatten().count { it == '0' }
    }

    private fun Maze.reduce(): Maze {
        val rows = mutableListOf<List<Char>>()

        for ((rowIndex, row) in withIndex()) {
            if (rowIndex % 2 == 0) {
                val newRow = mutableListOf<Char>()

                for ((colIndex, c) in row.withIndex()) {
                    if (colIndex % 2 == 0) {
                        newRow.add(c)
                    }
                }

                rows.add(newRow)
            }
        }

        return rows

    }

    private fun Maze.expand(): Maze {
        var currentCoordinate = startingCoordinate()
        var previousCoordinate = -1 to -1

        val pipeCoordinates = mutableListOf<Coordinate>()

        do {
            val tmpCurrent = currentCoordinate
            pipeCoordinates.add(tmpCurrent)

            currentCoordinate = moveForward(currentCoordinate, previousCoordinate)
            previousCoordinate = tmpCurrent

        } while (getPipe(currentCoordinate) != 'S')

        val nulledMaze = this.mapIndexed { rowIndex: Int, row: List<Char> ->
            row.mapIndexed { colIndex: Int, c: Char ->
                if (pipeCoordinates.contains(rowIndex to colIndex)) {
                    c
                } else {
                    '0'
                }
            }
        }

        val expandedMaze = nulledMaze.flatMap { row ->
            val expandedRow = row.flatMap { listOf(it, '*') }
            val nextRow = List(expandedRow.size) { '*' }
            listOf(expandedRow, nextRow)
        }

        val expandedConnectedMaze = expandedMaze
            .mapIndexed { rowIndex, row ->
                row.mapIndexed { colIndex, c ->
                    val surroundings = expandedMaze.getDirectSurroundingPipes(rowIndex to colIndex)
                    val (left, top, right, bottom) = surroundings
                    var char = c

                    if (c == '*') {
                        val countOfNotPipes = surroundings.count { it == null || it == '.' || it == '0' }
                        if (countOfNotPipes >= 3) {
                            char = '0'
                        } else if ((left == '-' || left == 'F' || left == 'L' || left == 'S') && (right == '-' || right == '7' || right == 'J' || right == 'S')) {
                            char = '-'
                        } else if ((top == '|' || top == 'F' || top == '7' || top == 'S') && (bottom == '|' || bottom == 'L' || bottom == 'J' || bottom == 'S')) {
                            char = '|'
                        } else {
                            char = '0'
                        }
                    }
                    char
                }
            }

        return expandedConnectedMaze
    }

    private fun Maze.print() {
        for (row in this) {
            println(row.joinToString(""))
        }
    }


    private fun Maze.moveForward(currentCoordinate: Coordinate, previousCoordinate: Coordinate): Coordinate {
        return connectingPipes(currentCoordinate)
            .filterNot { previousCoordinate == it }
            .first()
    }

    private fun Maze.connectingPipes(coordinate: Coordinate): List<Coordinate> {
        val (row, col) = coordinate
        val currentPipe = getPipe(coordinate)

        val leftCoordinate = row to col - 1
        val left = getPipeOrNull(leftCoordinate)

        val topCoordinate = row - 1 to col
        val top = getPipeOrNull(topCoordinate)

        val rightCoordinate = row to col + 1
        val right = getPipeOrNull(rightCoordinate)

        val bottomCoordinate = row + 1 to col
        val bottom = getPipeOrNull(bottomCoordinate)

        val connectingPipes = mutableListOf<Coordinate>()

        when (currentPipe) {
            '|' -> {
                if (top == 'F' || top == '7' || top == '|' || top == 'S') {
                    connectingPipes.add(topCoordinate)
                }
                if (bottom == 'L' || bottom == 'J' || bottom == '|' || bottom == 'S') {
                    connectingPipes.add(bottomCoordinate)
                }
            }

            '-' -> {
                if (left == 'F' || left == 'L' || left == '-' || left == 'S') {
                    connectingPipes.add(leftCoordinate)
                }

                if (right == '7' || right == 'J' || right == '-' || left == 'S') {
                    connectingPipes.add(rightCoordinate)
                }
            }

            'L' -> {
                if (top == 'F' || top == '|' || top == '7' || top == 'S') {
                    connectingPipes.add(topCoordinate)
                }

                if (right == '-' || right == 'J' || right == '7' || right == 'S') {
                    connectingPipes.add(rightCoordinate)
                }
            }

            'J' -> {
                if (left == '-' || left == 'L' || left == 'F' || left == 'S') {
                    connectingPipes.add(leftCoordinate)
                }

                if (top == '|' || top == '7' || top == 'F' || top == 'S') {
                    connectingPipes.add(topCoordinate)
                }
            }

            '7' -> {
                if (left == '-' || left == 'L' || left == 'F' || left == 'S') {
                    connectingPipes.add(leftCoordinate)
                }

                if (bottom == '|' || bottom == 'L' || bottom == 'J' || bottom == 'S') {
                    connectingPipes.add(bottomCoordinate)
                }
            }

            'F' -> {
                if (right == '-' || right == '7' || right == 'J' || right == 'S') {
                    connectingPipes.add(rightCoordinate)
                }

                if (bottom == '|' || bottom == 'L' || bottom == 'J' || bottom == 'S') {
                    connectingPipes.add(bottomCoordinate)
                }
            }

            'S' -> {
                if (left == '-' || left == 'F' || left == 'L' || left == 'S') {
                    connectingPipes.add(leftCoordinate)
                }

                if (top == '|' || top == 'F' || top == '7' || top == 'S') {
                    connectingPipes.add(topCoordinate)
                }

                if (right == '-' || right == 'J' || right == '7' || right == 'S') {
                    connectingPipes.add(rightCoordinate)
                }

                if (bottom == '|' || bottom == 'J' || bottom == 'L' || bottom == 'S') {
                    connectingPipes.add(bottomCoordinate)
                }
            }

            '.' -> {
                throw IllegalArgumentException("Standing on ground")
            }

            else -> {
                throw IllegalArgumentException("Unknown Pipe")
            }
        }

        return connectingPipes
    }

}

typealias Maze = List<List<Char>>
typealias MutableMaze = MutableList<MutableList<Char>>

private fun Maze.toMutable(): MutableMaze =
    map { it.toMutableList() }
        .toMutableList()

private fun Maze.startingCoordinate(): Coordinate {
    forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, c ->
            if (c == 'S') {
                return rowIndex to colIndex
            }
        }
    }
    throw IllegalArgumentException("Could not find start coordinates")
}

private fun Maze.getDirectSurroundingPipes(coordinate: Coordinate): List<Char?> {
    val (row, col) = coordinate
    val leftCoordinate = row to col - 1
    val left = getPipeOrNull(leftCoordinate)

    val topCoordinate = row - 1 to col
    val top = getPipeOrNull(topCoordinate)

    val rightCoordinate = row to col + 1
    val right = getPipeOrNull(rightCoordinate)

    val bottomCoordinate = row + 1 to col
    val bottom = getPipeOrNull(bottomCoordinate)

    return listOf(left, top, right, bottom)
}


private fun Maze.getAllSurroundingPipes(coordinate: Coordinate): List<Char?> {
    val (row, col) = coordinate
    val leftCoordinate = row to col - 1
    val left = getPipeOrNull(leftCoordinate)

    val topLeftCoordinate = row - 1 to col - 1
    val topLeft = getPipeOrNull(topLeftCoordinate)

    val topCoordinate = row - 1 to col
    val top = getPipeOrNull(topCoordinate)

    val topRightCoordinate = row - 1 to col + 1
    val topRight = getPipeOrNull(topRightCoordinate)

    val rightCoordinate = row to col + 1
    val right = getPipeOrNull(rightCoordinate)

    val rightBottomCoordinate = row + 1 to col + 1
    val rightBottom = getPipeOrNull(rightBottomCoordinate)

    val bottomCoordinate = row + 1 to col
    val bottom = getPipeOrNull(bottomCoordinate)

    val bottomLeftCoordinate = row + 1 to col - 1
    val bottomLeft = getPipeOrNull(bottomLeftCoordinate)

    return listOf(left, topLeft, top, topRight, right, rightBottom, bottom, bottomLeft)
}

private fun Maze.getPipe(coordinate: Coordinate): Char {
    val (row, col) = coordinate
    return get(row).get(col)
}

private fun Maze.getPipeOrNull(coordinate: Coordinate): Char? {
    val (row, col) = coordinate
    return getOrNull(row)?.getOrNull(col)
}

typealias Coordinate = Pair<Int, Int>