package sh.weller.aoc

import sh.weller.aoc.Day06.move
import sh.weller.aoc.Day06.setAt
import sh.weller.aoc.util.print
import sh.weller.aoc.util.to2DList

object Day06 : SomeDay<Int> {

    override val day: Int = 6

    override fun partOne(input: List<String>): Int {
        val map = input.to2DList().map { it.toMutableList() }.toMutableList()
        var leftMap: Boolean
        do {
            leftMap = map.move()
        } while (!leftMap)

        return map.flatten().count { it == 'X' }
    }

    private fun List<List<Char>>.getPosition(): Triple<Int, Int, Char> {
        this.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { index, c ->
                if (c == '^' || c == '>' || c == '<' || c == 'v') {
                    return Triple(lineIndex, index, c)
                }
            }
        }
        throw IllegalArgumentException("Position not found")
    }

    private fun List<List<Char>>.getAt(position: Pair<Int, Int>): Char? =
        this.getOrNull(position.first)?.getOrNull(position.second)

    private fun MutableList<MutableList<Char>>.setAt(position: Pair<Int, Int>, newChar: Char) {
        this[position.first][position.second] = newChar
    }

    private fun MutableList<MutableList<Char>>.move(): Boolean {
        val (currentY, currentX, direction) = this.getPosition()
        when (direction) {
            '^' -> {
                val nextStep = this.getAt(currentY - 1 to currentX)
                this.setAt(currentY to currentX, 'X')

                when (nextStep) {
                    '#' -> {
                        this.setAt(currentY to currentX + 1, '>')
                    }

                    '.', 'X' -> {
                        this.setAt(currentY - 1 to currentX, '^')
                    }

                    null -> {
                        return true
                    }
                }
            }

            '>' -> {
                val nextStep = this.getAt(currentY to currentX + 1)
                this.setAt(currentY to currentX, 'X')

                when (nextStep) {
                    '#' -> {
                        this.setAt(currentY + 1 to currentX, 'v')
                    }

                    '.', 'X' -> {
                        this.setAt(currentY to currentX + 1, '>')
                    }

                    null -> {
                        return true
                    }
                }
            }

            'v' -> {
                val nextStep = this.getAt(currentY + 1 to currentX)
                this.setAt(currentY to currentX, 'X')

                when (nextStep) {
                    '#' -> {
                        this.setAt(currentY to currentX - 1, '<')
                    }

                    '.', 'X' -> {
                        this.setAt(currentY + 1 to currentX, 'v')
                    }

                    null -> {
                        return true
                    }
                }
            }

            '<' -> {
                val nextStep = this.getAt(currentY to currentX - 1)
                this.setAt(currentY to currentX, 'X')

                when (nextStep) {
                    '#' -> {
                        this.setAt(currentY - 1 to currentX, '^')
                    }

                    '.', 'X' -> {
                        this.setAt(currentY to currentX - 1, '<')
                    }

                    null -> {
                        return true
                    }
                }
            }
        }
        return false
    }


    override fun partTwo(input: List<String>): Int {
        val map = input.to2DList().map { it.toMutableList() }.toMutableList()
        var leftMap: Boolean
        do {
            leftMap = map.move()
        } while (!leftMap)



        return -1
    }
}