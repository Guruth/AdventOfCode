package sh.weller.aoc.util

fun List<String>.to2DList(): MutableList<MutableList<Char>> =
    map { it.toCharArray().toMutableList() }.toMutableList()

fun List<Number>.product(): Long =
    this.map { it.toLong() }
        .reduce { i, j -> i * j }

fun List<List<*>>.print() {
    for (row in this) {
        println(row.joinToString(""))
    }
}

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


fun <T> List<List<T>>.rotateRight(): List<List<T>> {
    val newList = mutableListOf<List<T>>()

    repeat(first().size) { index ->
        val column = this
            .map { it[index] }
            .reversed()
        newList.add(column)
    }

    return newList
}

fun <T> List<List<T>>.rotateLeft(): List<List<T>> {
    val flippedList = List(first().size) { mutableListOf<T>() }

    for (row in this) {
        for ((index, item) in row.reversed().withIndex()) {
            flippedList[index].add(item)
        }
    }

    return flippedList
}


fun <T> List<T>.getCycledElementAt(position: Int, windowSize: Int = 1): T {
    val windowedList = reversed().windowed(windowSize, windowSize).reversed()
    val randomElement = windowedList.takeLast(windowedList.size / 2).random()

    val firstOccurrence = windowedList.indexOfFirst { it == randomElement } + 1

    val secondOccurrence = windowedList
        .drop(firstOccurrence)
        .indexOfFirst { it == randomElement } + firstOccurrence + 1

    val cycleLength = secondOccurrence - firstOccurrence

    val offset = (position - (secondOccurrence * windowSize)) % cycleLength

    return this[(secondOccurrence * windowSize) + offset - 1]
}