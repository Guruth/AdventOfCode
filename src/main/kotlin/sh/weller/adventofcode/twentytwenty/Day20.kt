package sh.weller.adventofcode.twentytwenty

import sh.weller.adventofcode.util.multipliedSum


fun List<List<Char>>.day20Part2() {
    val tiles: Map<Int, List<List<Char>>> = this.mapToTiles()
    val edges = tiles.getEdges()
    val neighbours = edges.findneighbours()
    val image = tiles.assembleImage(edges, neighbours)

}

fun Map<Int, List<List<Char>>>.assembleImage(
    edges: Map<Int, List<List<Char>>>,
    neighbours: Map<Int, List<Int>>
): List<List<Char>> {
    val image = mutableListOf<List<Char>>()

    val cornerId = neighbours.filter { it.value.size == 2 }.map { it.key }.first()
    val corner = this[cornerId]!!

    return emptyList()
}

fun List<List<Char>>.day20Part1(): Long {
    val tiles: Map<Int, List<List<Char>>> = this.mapToTiles()
    val edges = tiles.getEdges()
    val neighbours = edges.findneighbours()

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

private fun Map<Int, List<List<Char>>>.findneighbours(): Map<Int, List<Int>> {
    val neighborMap = mutableMapOf<Int, List<Int>>()

    this.forEach { tile ->
        val tileEdges = tile.value
        this.forEach { neighbor ->
            val neighborEdges = neighbor.value
            if (tile.key != neighbor.key) {
                val neighbours = neighborMap[tile.key]?.toMutableList() ?: mutableListOf()
                if (tileEdges.any { edge -> neighborEdges.contains(edge) } ||
                    tileEdges.flip().any { edge -> neighborEdges.contains(edge) }) {
                    neighbours.add(neighbor.key)
                }
                neighborMap[tile.key] = neighbours
            }
        }
    }

    return neighborMap
}

private fun List<List<Char>>.flip(): List<List<Char>> =
    this.map { it.reversed() }