package sh.weller.aoc

interface SomeDay<Out : Number> {
    val day: Int

    fun partOne(input: List<String>): Out
    fun partTwo(input: List<String>): Out
}