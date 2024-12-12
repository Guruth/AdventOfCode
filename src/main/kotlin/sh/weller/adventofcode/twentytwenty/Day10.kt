package sh.weller.adventofcode.twentytwenty

import org.jgrapht.Graph
import org.jgrapht.alg.shortestpath.AllDirectedPaths
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import sh.weller.adventofcode.util.multipliedSumLong


fun List<Int>.findPathsInGraph(): Int {
    val sorted = (listOf(0) + sorted() + listOf(maxOf { it } + 3))

    val graph: Graph<Int, DefaultEdge> = DefaultDirectedGraph(DefaultEdge::class.java)

    sorted.forEach { graph.addVertex(it) }
    sorted.forEachIndexed { index, i ->
        sorted.take(index).takeLast(3).filter { j -> (i - 3 <= j) }.forEach { j -> graph.addEdge(j, i) }
    }

    val allDirectedPaths = AllDirectedPaths(graph)
    return allDirectedPaths.getAllPaths(sorted.first(), sorted.last(), true, Int.MAX_VALUE).count()
}

fun List<Int>.findPathsInSplitGraph(): Long {
    val sorted = (listOf(0) + sorted() + listOf(maxOf { it } + 3))

    val subGraphList = mutableListOf<List<Int>>()
    var tmpList = mutableListOf<Int>()
    sorted.forEachIndexed { index, i ->
        tmpList.add(i)
        if (i - sorted.getOrElse(index - 1) { 0 } == 3) {
            subGraphList.add(tmpList)
            tmpList = mutableListOf()
            tmpList.add(i)
        }
    }

    val subGraphResults = subGraphList.map { it.findPathsInSubGraph().toLong() }
    return subGraphResults.multipliedSumLong()
}

private fun List<Int>.findPathsInSubGraph(): Int {
    val graph: Graph<Int, DefaultEdge> = DefaultDirectedGraph(DefaultEdge::class.java)

    this.forEach { graph.addVertex(it) }
    this.forEachIndexed { index, i ->
        this.take(index).takeLast(3).filter { j -> (i - 3 <= j) }.forEach { j -> graph.addEdge(j, i) }
    }

    val allDirectedPaths = AllDirectedPaths(graph)
    return allDirectedPaths.getAllPaths(this.first(), this.last(), true, Int.MAX_VALUE).count()
}

fun List<Int>.findPathsBruteForce(): Long {
    val sorted = (listOf(0) + this.sorted() + listOf(this.maxOf { it } + 3))
    return sorted.rec().size.toLong()
}

private fun List<Int>.rec(): List<Int> {
    if (this.size == 1) {
        return this
    }
    val withoutFirst = this.drop(1)
    val filtered = withoutFirst.filter { it - 3 <= this.first() }
    return filtered.flatMap { drop(indexOf(it)).rec() }
}


fun List<Int>.findPathsSmart(): Long {
    val sorted = (listOf(0) + this.sorted() + listOf(this.maxOf { it } + 3))
    val depthList = mutableListOf<Long>(1)

    sorted.forEachIndexed { index, i ->
        var counter = 0L
        sorted.take(index).takeLast(3).forEachIndexed { innerIndex, j ->
            if (i - 3 <= j) {
                val atPoint = depthList.getOrElse(index - innerIndex) { 0L }
                counter += atPoint
            }
        }
        depthList.add(maxOf(counter, depthList[index]))
    }
    return depthList.last()
}

fun List<Int>.countJoltDifferences(startJoltage: Int = 0): Long {
    var currentJoltage = startJoltage
    var oneDifference: Long = 0
    var threeDifference: Long = 1

    this.sorted().forEach { value ->
        val difference = value - currentJoltage
        if (difference >= 1 || difference <= 3) {
            when (difference) {
                1 -> oneDifference++
                3 -> threeDifference++
            }
            currentJoltage += difference
        }
    }
    return oneDifference * threeDifference
}