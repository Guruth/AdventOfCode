package sh.weller.aoc.util

fun readFile(fileName: String): List<String> =
    object {}.javaClass.getResourceAsStream("/$fileName").bufferedReader().readLines()

inline fun <reified T> fileToList(fileName: String): List<T> =
    readFile(fileName)
        .map(::parseTo)

inline fun <reified T> parseTo(value: String): T =
    when (T::class) {
        Int::class -> value.toInt()
        Double::class -> value.toDouble()
        String::class -> value
        else -> throw IllegalStateException("Unsupported Generic Type")
    } as T
