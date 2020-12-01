package util

inline fun <reified T> fileToList(fileName: String): List<T> {
    val inputStream = object {}.javaClass.getResourceAsStream("/$fileName")
        ?: return emptyList()

    return inputStream.reader().readLines()
        .mapNotNull { it.parseTo() }
}

inline fun <reified T> String.parseTo(): T? =
    when (T::class) {
        Int::class -> this.toIntOrNull()
        Double::class -> this.toDoubleOrNull()
        String::class -> this
        else -> throw IllegalStateException("Unsupported Generic Type")
    } as T

