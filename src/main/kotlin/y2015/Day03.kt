package y2015

import utils.Day
import utils.Files

class Day03 : Day {

    private val input = Files.readFileAsString(2015, 3)

    override fun runAll() {
        println("Day 03: ")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(input: String): Int {
        var x = 0
        var y = 0
        val locations = mutableSetOf(Pair(x, y))
        for (c in input) {
            when (c) {
                '>' -> x++
                '<' -> x--
                'v' -> y--
                '^' -> y++
            }
            locations.add(Pair(x, y))
        }
        return locations.size
    }

    private fun partTwo(input: String): Int {
        var robotX = 0
        var robotY = 0
        var santaX = 0
        var santaY = 0

        val locations = mutableSetOf(Pair(robotX, robotY), Pair(santaX, santaY))

        input.forEachIndexed { index, c ->
            if (index % 2 == 0) {
                when (c) {
                    '>' -> robotX++
                    '<' -> robotX--
                    'v' -> robotY--
                    '^' -> robotY++
                }
                locations.add(Pair(robotX, robotY))
            } else {
                when (c) {
                    '>' -> santaX++
                    '<' -> santaX--
                    'v' -> santaY--
                    '^' -> santaY++
                }
                locations.add(Pair(santaX, santaY))
            }
        }
        return locations.size
    }

}