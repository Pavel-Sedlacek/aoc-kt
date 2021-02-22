package y2020

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val inputs = File("src/main/resources/Day12.txt").useLines { it.toMutableList() }
    println("Day TWELVE : Boat emergency guidance, waypoint")
    println("Part One ${partOneTwelve(inputs)}")
    println("Part Two ${partTwoTwelve(inputs)}")
}

class Day12 {
    fun run() {
        val inputs = File("src/main/resources/Day12.txt").useLines { it.toMutableList() }
        println("Day TWELVE : Boat emergency guidance, waypoint")
        println("Part One ${partOneTwelve(inputs)}")
        println("Part Two ${partTwoTwelve(inputs)}")
    }
}

fun partOneTwelve(inputs: List<String>): Int {
    var x = 0
    var y = 0
    var facing = 0

    for (i in inputs.indices) {
        val j = inputs[i].substring(1).toInt()
        when (inputs[i][0]) {
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

fun partTwoTwelve(inputs: List<String>): Int {
    var x = 0
    var y = 0
    var waypointX = 10
    var waypointY = 1
    for (j in inputs.indices) {
        val i = inputs[j].substring(1).toInt()
        when (inputs[j][0]) {
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