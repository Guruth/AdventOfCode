package sh.weller.aoc


object Day03 : SomeDay<String, Int> {
    override fun partOne(input: List<String>): Int =
        input
            .map { it.toCharArray().toList() }
            .map {
                val firstHalf = it.dropLast(it.size / 2).toSet()
                val secondHalf = it.drop(it.size / 2).toSet()

                firstHalf.intersect(secondHalf).first()
            }
            .sumOf {
                priorityList.indexOf(it) + 1
            }

    private val priorityList = ('a'..'z') + ('A'..'Z')

    override fun partTwo(input: List<String>): Int =
        input
            .map { it.toCharArray().toSet() }
            .chunked(3)
            .map {
                val firstElv = it[0]
                val secondElv = it[1]
                val thirdElv = it[2]

                firstElv.intersect(secondElv).intersect(thirdElv).first()
            }
            .sumOf {
                priorityList.indexOf(it) + 1
            }
}