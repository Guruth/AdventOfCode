package sh.weller.aoc

import sh.weller.aoc.util.print
import kotlin.math.abs

object Day14 : SomeDay<Int> {

    val ySize = 103
    val xSize = 101

    override val day: Int = 14

    override fun partOne(input: List<String>): Int {
        val robots = input.map(::toRobot)

        var map = (0..<ySize).map { (0..<xSize).map { mutableListOf<Pair<Int, Int>>() } }
        map.placeRobots(robots)

        repeat(100) {
//            map.printRobots()
            map = map.moveRobots()
        }

        return map.countRobotsInQuadrants()
    }

    private fun List<List<MutableList<Pair<Int, Int>>>>.printRobots() {
        val foo = map { row ->
            row.map { point ->
                if (point.size > 0) {
                    "${point.size}"
                } else {
                    "."
                }
            }
        }
    }

    private fun toRobot(line: String): Robot {
        val (pX, pY) = line.substring(line.indexOf("p=") + 2, line.indexOf("v=")).trim().split(",").map { it.toInt() }
        val (vX, vY) = line.substring(line.indexOf("v=") + 2).trim().split(",").map { it.toInt() }

        return Robot(pY to pX, vY to vX)
    }

    private fun List<List<MutableList<Pair<Int, Int>>>>.placeRobots(robots: List<Robot>) {
        for (robot in robots) {
            val (y, x) = robot.position
            this[y][x].add(robot.velocity)
        }
    }

    private fun List<List<MutableList<Pair<Int, Int>>>>.moveRobots(): List<List<MutableList<Pair<Int, Int>>>> {
        val newMap = (0..<ySize).map { (0..<xSize).map { mutableListOf<Pair<Int, Int>>() } }
        forEachIndexed { y, line ->
            line.forEachIndexed { x, robots ->
                for (robot in robots) {
                    var newY = y + robot.first
                    if (newY < 0) {
                        newY = ySize + newY
                    }
                    if (newY >= ySize) {
                        newY = abs(ySize - newY)
                    }

                    var newX = x + robot.second
                    if (newX < 0) {
                        newX = xSize + newX
                    }
                    if (newX >= xSize) {
                        newX = abs(xSize - newX)
                    }
                    newMap[newY][newX].add(robot)
                }
            }
        }
        return newMap
    }

    private fun List<List<MutableList<Pair<Int, Int>>>>.countRobotsInQuadrants(): Int {
        val mapWithSize = this.map { row -> row.map { point -> point.size } }

        val firstQuadrant = mapWithSize.take(ySize / 2).map { row -> row.take(xSize / 2) }.flatten().sum()
        val secondQuadrant = mapWithSize.take(ySize / 2).map { row -> row.takeLast(xSize / 2) }.flatten().sum()
        val thirdQuadrant = mapWithSize.takeLast(ySize / 2).map { row -> row.take(xSize / 2) }.flatten().sum()
        val fourthQuadrant = mapWithSize.takeLast(ySize / 2).map { row -> row.takeLast(xSize / 2) }.flatten().sum()

        return (firstQuadrant * secondQuadrant * thirdQuadrant * fourthQuadrant)

    }

    override fun partTwo(input: List<String>): Int {
        val robots = input.map(::toRobot)

        var map = (0..<ySize).map { (0..<xSize).map { mutableListOf<Pair<Int, Int>>() } }
        map.placeRobots(robots)

        repeat(10_000) { num ->
            if (robots.size == map.map { row -> row.map { point -> point.size } }.flatten().count { it > 0 }) {
                map.printRobots()
                return num
            }

            map = map.moveRobots()
        }

        return 0
    }
}

private data class Robot(val position: Pair<Int, Int>, val velocity: Pair<Int, Int>)