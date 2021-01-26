package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.copy
import sh.weller.adventofcode.util.multipliedSum
import kotlin.math.sqrt


fun List<List<Char>>.day20Part2(): Int {
    val tiles: Map<Int, List<List<Char>>> = this.mapToTiles()
    val edges: Map<Int, List<List<Char>>> = tiles.getEdges()
    val neighbours: Map<Int, List<Int>> = edges.findNeighbours()
    val tileNumberImage: List<List<Int>> = neighbours.assembleTileNumberImage().flipVertical().rotate()
    tileNumberImage.printImage()

    val tileImage: List<List<Char>> = tileNumberImage.toTileImage(tiles)
    tileImage.printImage()

    val (transformedImage, seaMonsterLocations) = tileImage.transformUntilSeaMonstersFound()
    return transformedImage.countRoughWaterWithOutSeaMonsters(seaMonsterLocations)
}

private fun List<List<Char>>.countRoughWaterWithOutSeaMonsters(seaMonsterLocation: List<Pair<Int, Int>>): Int {
    val imageWithoutMonsters = mutableListOf<MutableList<Char>>()

    repeat(this.size) { row ->
        imageWithoutMonsters.add(mutableListOf())
        repeat(this.size) { column ->
            if (seaMonsterLocation.isPartOfSeaMonster(row, column).not()) {
                imageWithoutMonsters[row].add(this[row][column])
            }
        }
    }

    imageWithoutMonsters.printImage()

    return imageWithoutMonsters.flatten().count { it == '#' }
}

private fun List<Pair<Int, Int>>.isPartOfSeaMonster(currentRow: Int, currentColumn: Int): Boolean {
    // 3x20
    this.forEach { (row, column) ->
        if (currentRow == (row + 1) && currentColumn == (column + 0))
            return true
        if (currentRow == (row + 2) && currentColumn == (column + 1))
            return true

        if (currentRow == (row + 2) && currentColumn == (column + 4))
            return true
        if (currentRow == (row + 1) && currentColumn == (column + 5))
            return true
        if (currentRow == (row + 1) && currentColumn == (column + 6))
            return true
        if (currentRow == (row + 2) && currentColumn == (column + 7))
            return true

        if (currentRow == (row + 2) && currentColumn == (column + 10))
            return true
        if (currentRow == (row + 1) && currentColumn == (column + 11))
            return true
        if (currentRow == (row + 1) && currentColumn == (column + 12))
            return true
        if (currentRow == (row + 2) && currentColumn == (column + 13))
            return true

        if (currentRow == (row + 2) && currentColumn == (column + 16))
            return true
        if (currentRow == (row + 1) && currentColumn == (column + 17))
            return true
        if (currentRow == (row + 1) && currentColumn == (column + 18))
            return true
        if (currentRow == (row + 0) && currentColumn == (column + 18))
            return true
        if (currentRow == (row + 1) && currentColumn == (column + 19))
            return true
    }
    return false
}

private fun List<List<Char>>.transformUntilSeaMonstersFound(): Pair<List<List<Char>>, List<Pair<Int, Int>>> {
    var tileImage = this
    var seaMonsterLocation: List<Pair<Int, Int>> = emptyList()
    repeat(4) {
        val tmpMonsters = tileImage.findSeaMonsters()
        if (tmpMonsters.isNotEmpty()) {
            return tileImage to tmpMonsters
        }
        tileImage = tileImage.rotate()
    }
    tileImage = tileImage.flipVertical()
    repeat(4) {
        val tmpMonsters = tileImage.findSeaMonsters()
        if (tmpMonsters.isNotEmpty()) {
            return tileImage to tmpMonsters
        }
        tileImage = tileImage.rotate()
    }
    return tileImage to emptyList()
}

private fun List<List<Char>>.findSeaMonsters(): List<Pair<Int, Int>> {
    val foundMonsters = mutableListOf<Pair<Int, Int>>()
    repeat(this.size) { row ->
        repeat(this.size) { column ->
            if (this.hasSeaMonsterAt(row, column)) {
                foundMonsters.add(row to column)
            }
        }
    }
    return foundMonsters
}

private fun List<List<Char>>.hasSeaMonsterAt(rowIndex: Int, columnIndex: Int): Boolean {
    // .#...#.###...#.##.O#
    // O.##.OO#.#.OO.##.OOO
    // #O.#O#.O##O..O.#O##.
    val seaMonsterShape = listOfNotNull(
        this.getOrNull(rowIndex + 1)?.getOrNull(columnIndex),
        this.getOrNull(rowIndex + 2)?.getOrNull(columnIndex + 1),

        this.getOrNull(rowIndex + 2)?.getOrNull(columnIndex + 4),
        this.getOrNull(rowIndex + 1)?.getOrNull(columnIndex + 5),
        this.getOrNull(rowIndex + 1)?.getOrNull(columnIndex + 6),
        this.getOrNull(rowIndex + 2)?.getOrNull(columnIndex + 7),

        this.getOrNull(rowIndex + 2)?.getOrNull(columnIndex + 10),
        this.getOrNull(rowIndex + 1)?.getOrNull(columnIndex + 11),
        this.getOrNull(rowIndex + 1)?.getOrNull(columnIndex + 12),
        this.getOrNull(rowIndex + 2)?.getOrNull(columnIndex + 13),

        this.getOrNull(rowIndex + 2)?.getOrNull(columnIndex + 16),
        this.getOrNull(rowIndex + 1)?.getOrNull(columnIndex + 17),
        this.getOrNull(rowIndex + 1)?.getOrNull(columnIndex + 18),
        this.getOrNull(rowIndex)?.getOrNull(columnIndex + 18),
        this.getOrNull(rowIndex + 1)?.getOrNull(columnIndex + 19),
    )

    return seaMonsterShape.all { it == '#' } && seaMonsterShape.size == 15
}


private fun List<List<Int>>.toTileImage(tiles: Map<Int, List<List<Char>>>): List<List<Char>> {
    // Find the correct rotation for the first tile
    var cornerTile = tiles[this[0][0]]!!
    val rightOfCornerTile = tiles[this[0][1]]!!
    val belowOfCornerTile = tiles[this[1][0]]!!

    var edge = cornerTile.getRightEdge()
    repeat(3) {
        if (rightOfCornerTile.hasEdgeMapping(edge).not()) {
            cornerTile = cornerTile.rotate()
            edge = cornerTile.getRightEdge()
        }
    }

    edge = cornerTile.getBottomEdge()
    if (belowOfCornerTile.hasEdgeMapping(edge).not()) {
        cornerTile = cornerTile.flipHorizontal()
    }


    val tileImage: MutableList<MutableList<Char>> = cornerTile.removeEdges().toMutable()
    val tileLength = tileImage.size

    var firstTilePreviousRow = cornerTile
    var previousTile = cornerTile
    this.forEachIndexed { rowIndex, tileNumberRow ->
        tileNumberRow.forEachIndexed { columnIndex, tileNumber ->
            if (rowIndex == 0 && columnIndex == 0) {
                // Do nothing
            } else {
                val currentTile = tiles[tileNumber]!!
                if (columnIndex == 0) {
                    // Attach the Image below the first tile of the previous row
                    edge = firstTilePreviousRow.getBottomEdge()
                    val transformedTileWithOutEdges = currentTile.transformUntilMatchesBelowOf(edge)

                    tileImage.addAll(transformedTileWithOutEdges.removeEdges().toMutable())

                    firstTilePreviousRow = transformedTileWithOutEdges
                    previousTile = transformedTileWithOutEdges
                } else {
                    // Attach the image right to the previous tile
                    edge = previousTile.map { it.last() }
                    val transformedTileWithOutEdges = currentTile.transformUntilMatchesRightOf(edge)

                    transformedTileWithOutEdges.removeEdges()
                        .forEachIndexed { tileToAddRowIndex, rowToAdd ->
                            tileImage[tileToAddRowIndex + (rowIndex * tileLength)].addAll(rowToAdd)
                        }

                    previousTile = transformedTileWithOutEdges
                }
            }
        }
    }

    return tileImage
}

private fun List<List<Char>>.hasEdgeMapping(edge: List<Char>): Boolean =
    listOf(
        this.getTopEdge(),
        this.getBottomEdge(),
        this.getLeftEdge(),
        this.getRightEdge(),
        this.flipVertical().getTopEdge(),
        this.flipVertical().getBottomEdge(),
        this.flipVertical().getLeftEdge(),
        this.flipVertical().getRightEdge()
    ).any { edge.contentEquals(it) }

private fun List<List<Char>>.transformUntilMatchesRightOf(edge: List<Char>): List<List<Char>> {
    var tile = this.copy()

    repeat(4) {
        tile = tile.rotate()
        val leftEdge = tile.getLeftEdge()
        if (leftEdge.contentEquals(edge)) {
            return tile
        }
    }
    tile = tile.flipVertical()
    repeat(4) {
        tile = tile.rotate()
        val leftEdge = tile.getLeftEdge()
        if (leftEdge.contentEquals(edge)) {
            return tile
        }
    }

    return emptyList()
}


private fun List<List<Char>>.transformUntilMatchesBelowOf(edge: List<Char>): List<List<Char>> {
    var tile = this

    repeat(4) {
        tile = tile.rotate()
        val topEdge = tile.getTopEdge()
        if (topEdge.contentEquals(edge)) {
            return tile
        }
    }
    tile = tile.flipHorizontal()
    repeat(4) {
        tile = tile.rotate()
        val leftEdge = tile.getTopEdge()
        if (leftEdge.contentEquals(edge)) {
            return tile
        }
    }

    return emptyList()
}

private inline fun <reified T> List<T>.contentEquals(compareList: List<T>) =
    this.toTypedArray().contentEquals(compareList.toTypedArray())

private fun List<List<Char>>.toMutable(): MutableList<MutableList<Char>> =
    this.map { it.toMutableList() }.toMutableList()

private fun List<List<Char>>.removeEdges(): List<List<Char>> =
    this.drop(1).dropLast(1)
        .map { it.drop(1).dropLast(1) }


private fun Map<Int, List<Int>>.assembleTileNumberImage(): List<List<Int>> {
    val edgeLength = sqrt(this.size.toDouble()).toInt()

    val cornerTiles = this.filter { it.value.size == 2 }
    val upperLeftCorner = cornerTiles.keys.first()
    val edgesFromStartCorner = cornerTiles.mapNotNull { this.findLine(upperLeftCorner, it.key, edgeLength) }
    val topEdge = edgesFromStartCorner.first()
    val upperRightCorner = topEdge.last()
    val leftEdge = edgesFromStartCorner.last()
    val lowerLeftCorner = leftEdge.last()
    val lowerRightCorner =
        cornerTiles.keys.single { it != upperLeftCorner && it != upperRightCorner && it != lowerLeftCorner }

    val rightEdge = this.findLine(upperRightCorner, lowerRightCorner, edgeLength)!!
    val lowerEdge = this.findLine(lowerLeftCorner, lowerRightCorner, edgeLength)!!

    val image = mutableListOf(topEdge)
    leftEdge.drop(1).dropLast(1)
        .forEachIndexed { index, leftEdgeTile ->
            val rightEdgeTile = rightEdge.drop(1).dropLast(1)[index]
            image.add(this.findLine(leftEdgeTile, rightEdgeTile, edgeLength)!!)
        }
    image.add(lowerEdge)

    return image
}

private fun Map<Int, List<Int>>.findLine(from: Int, to: Int, length: Int): List<Int>? {
    if (from == to) {
        return null
    }
    return this.explodeNeighbours(from, length - 1).firstOrNull { it.first() == from && it.last() == to }
}

private fun Map<Int, List<Int>>.explodeNeighbours(id: Int, depth: Int): List<List<Int>> {
    if (depth == 0) {
        return listOf(listOf(id))
    }
    val neighbours = this[id] ?: return emptyList()

    return neighbours
        .flatMap { this.explodeNeighbours(it, depth - 1) }
        .map { listOf(id) + it }
}

fun List<List<Char>>.day20Part1(): Long {
    val tiles: Map<Int, List<List<Char>>> = this.mapToTiles()
    val edges = tiles.getEdges()
    val neighbours = edges.findNeighbours()

    return neighbours
        .filter { it.value.size == 2 }
        .map { it.key }
        .multipliedSum()
}

private fun List<List<Char>>.mapToTiles(): Map<Int, List<List<Char>>> {
    val tileMap = mutableMapOf<Int, List<List<Char>>>()

    var tmpTile = mutableListOf<List<Char>>()
    var tmpTileNumber = 0
    this.forEach {
        when {
            it.isEmpty() -> {
                tileMap[tmpTileNumber] = tmpTile
                tmpTile = mutableListOf()
                tmpTileNumber = 0
            }
            it.first() == 'T' -> {
                tmpTileNumber = it.drop(5).dropLast(1).joinToString("").toInt()
            }
            else -> {
                tmpTile.add(it)
            }
        }
    }
    tileMap[tmpTileNumber] = tmpTile
    return tileMap
}

private fun Map<Int, List<List<Char>>>.getEdges(): Map<Int, List<List<Char>>> =
    this.mapValues {
        val edgeList = mutableListOf<List<Char>>()
        edgeList.add(it.value.first())
        edgeList.add(it.value.last())
        edgeList.add(it.value.map { row -> row.first() })
        edgeList.add(it.value.map { row -> row.last() })
        return@mapValues edgeList
    }

private fun Map<Int, List<List<Char>>>.findNeighbours(): Map<Int, List<Int>> {
    val neighborMap = mutableMapOf<Int, List<Int>>()

    this.forEach { tile ->
        val tileEdges = tile.value
        this.forEach { neighbor ->
            val neighborEdges = neighbor.value
            if (tile.key != neighbor.key) {
                val neighbours = neighborMap[tile.key]?.toMutableList() ?: mutableListOf()
                if (tileEdges.any { edge -> neighborEdges.contains(edge) } ||
                    tileEdges.flipVertical().any { edge -> neighborEdges.contains(edge) }) {
                    neighbours.add(neighbor.key)
                }
                neighborMap[tile.key] = neighbours
            }
        }
    }

    return neighborMap
}


private fun <T> List<List<T>>.flipVertical(): List<List<T>> = this.map { it.reversed() }
private fun <T> List<List<T>>.flipHorizontal(): List<List<T>> = this.flipVertical().rotate().rotate()

private fun <T> List<List<T>>.rotate(): List<List<T>> {
    val rotatedTile = mutableListOf<List<T>>()
    repeat(this.size) { index ->
        rotatedTile.add(this.map { it.reversed()[index] })
    }
    return rotatedTile
}

private fun <T> List<List<T>>.getLeftEdge(): List<T> =
    this.map { it.first() }

private fun <T> List<List<T>>.getRightEdge(): List<T> =
    this.map { it.last() }

private fun <T> List<List<T>>.getBottomEdge(): List<T> =
    this.last()

private fun <T> List<List<T>>.getTopEdge(): List<T> =
    this.first()

private fun <T> List<List<T>>.printImage() {
    println()
    this.forEach { println(it.joinToString(" ")) }
    println()
}
