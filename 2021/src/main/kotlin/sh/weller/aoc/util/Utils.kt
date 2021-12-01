package sh.weller.aoc.util

fun List<String>.to2DList(): List<List<Char>> =
    map { it.toCharArray().toList() }

fun List<Number>.product(): Long =
    this.map { it.toLong() }
        .reduce { i, j -> i * j }

fun <T> copy(input: List<T>): List<T> = input.toList()

@JvmName("copyList")
fun <T> List<T>.copy(): List<T> =
    copy(this)

@JvmName("copyListDepth2")
fun <T> List<List<T>>.copy(): List<List<T>> =
    this.map(::copy)

@JvmName("copyListDepth3")
fun <T> List<List<List<T>>>.copy(): List<List<List<T>>> =
    this.map(::copy)

@JvmName("copyListDepth4")
fun <T> List<List<List<List<T>>>>.copy(): List<List<List<List<T>>>> =
    this.map(::copy)