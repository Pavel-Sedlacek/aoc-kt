package y2020

import utils.Day
import utils.readers.asLines
import kotlin.math.absoluteValue

class Day12 : Day<Int> {

    private val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partTwo(input: List<String>): Int {
        var x = 0
        var y = 0
        var waypointX = 10
        var waypointY = 1
        for (j in input.indices) {
            val i = input[j].substring(1).toInt()
            when (input[j][0]) {
                'N' -> waypointY += i
                'S' -> waypointY -= i
                'E' -> waypointX += i
                'W' -> waypointX -= i
                'R' -> when (i) {
                    90 -> waypointY = -waypointX.also { waypointX = waypointY }
                    180 -> {
                        waypointX = -waypointX; waypointY = -waypointY
                    }
                    270 -> waypointY = waypointX.also { waypointX = -waypointY }
                }
                'L' -> when (i) {
                    270 -> waypointY = -waypointX.also { waypointX = waypointY }
                    180 -> {
                        waypointX = -waypointX; waypointY = -waypointY
                    }
                    90 -> waypointY = waypointX.also { waypointX = -waypointY }
                }
                'F' -> {
                    x += waypointX * i
                    y += waypointY * i
                }
            }
        }
        return x.absoluteValue + y.absoluteValue
    }

    private fun partOne(input: List<String>): Int {
        var x = 0
        var y = 0
        var facing = 0

        for (i in input.indices) {
            val j = input[i].substring(1).toInt()
            when (input[i][0]) {
                'N' -> y += j
                'S' -> y -= j
                'E' -> x += j
                'W' -> x -= j
                'L' -> facing = (360 + facing - j) % 360
                'R' -> facing = (facing + j) % 360
                'F' -> when (facing) {
                    0 -> x += j
                    90 -> y -= j
                    180 -> x -= j
                    270 -> y += j
                }
            }
        }
        return x.absoluteValue + y.absoluteValue
    }
}