package sh.weller.adventofcode.util

fun List<Number>.product(): Long =
    this.map { it.toLong() }
        .reduce { i, j -> i * j }

fun printResult(day: Int, part: Int, result: Any) {
    println("Result Day $day-$part: $result")
}

fun <T> List<T>.copy(): List<T> =
    this.toList()

@JvmName("copyListDepth2")
fun <T> List<List<T>>.copy(): List<List<T>> =
    this.map { it.copy() }

@JvmName("copyListDepth3")
fun <T> List<List<List<T>>>.copy(): List<List<List<T>>> =
    this.map { it.copy() }

@JvmName("copyListDepth4")
fun <T> List<List<List<List<T>>>>.copy(): List<List<List<List<T>>>> =
    this.map { it.copy() }