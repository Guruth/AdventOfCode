package sh.weller.aoc

interface SomeDay<In, Out> {
    fun partOne(input: List<In>): Out
    fun partTwo(input: List<In>): Out
}