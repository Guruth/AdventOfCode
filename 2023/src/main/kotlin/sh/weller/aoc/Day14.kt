package sh.weller.aoc

import sh.weller.aoc.util.getCycledElementAt
import sh.weller.aoc.util.rotateLeft
import sh.weller.aoc.util.rotateRight
import sh.weller.aoc.util.to2DList

object Day14 : SomeDay<String, Int> {
    override fun partOne(input: List<String>): Int {
        return input
            .to2DList()
            .tilt()
            .mapIndexed { index, chars ->
                chars.count { it == 'O' } * (input.size - index)
            }
            .sum()
    }

    private fun List<List<Char>>.tilt(): List<List<Char>> {
        val tiltedPlatform = mutableListOf<List<Char>>()
        for (row in this.rotateLeft()) {
            val tiltedRow = row.toMutableList()
            for ((index, char) in tiltedRow.withIndex()) {
                val reducedRow = tiltedRow.drop(index + 1)
                if (reducedRow.contains('O').not()) {
                    break
                }
                if (char == '.') {

                    val indexOfCube = reducedRow.indexOfFirst { it == '#' }
                    if (indexOfCube == -1) {
                        val indexOfRound = reducedRow.indexOfFirst { it == 'O' }

                        if (indexOfRound != -1) {
                            tiltedRow[index] = 'O'
                            tiltedRow[index + indexOfRound + 1] = '.'
                        }
                    } else {
                        val rowTillNextCube = reducedRow.dropLast(reducedRow.size - indexOfCube)

                        val indexOfRound = rowTillNextCube.indexOfFirst { it == 'O' }

                        if (indexOfRound != -1) {
                            tiltedRow[index] = 'O'
                            tiltedRow[index + indexOfRound + 1] = '.'
                        }
                    }
                }
            }
            tiltedPlatform.add(tiltedRow)
        }
        return tiltedPlatform.rotateRight()
    }


    override fun partTwo(input: List<String>): Int {
        var platform = input
            .to2DList()

        val loads = mutableListOf<Int>()

        repeat(500) { cycle ->
            platform =
                platform
                    .tilt()
                    .rotateRight()
                    .tilt()
                    .rotateRight()
                    .tilt()
                    .rotateRight()
                    .tilt()
                    .rotateRight()

            val load = platform
                .mapIndexed { index, chars ->
                    chars.count { it == 'O' } * (platform.size - index)
                }
                .sum()

            loads.add(load)
        }

        return loads.getCycledElementAt(1_000_000_000, 2)
    }
}