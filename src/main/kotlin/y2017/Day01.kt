package y2017

import utils.Day
import utils.Files

class Day01 : Day {

    private val input = Files.readFileAsString(2017, 1)

    override fun runAll() {
        println("Day 01 : Sum consecutive matching digits")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(x: String) =
        x.foldIndexed(0) { i, acc, c ->
            acc + if (x[i] == x[(i + 1) % x.length]) x[i].toString().toInt() else 0
        }

    private fun partTwo(x: String) =
        x.foldIndexed(0) { i, acc, c ->
            acc + if (x[i] == x[(i + x.length / 2) % x.length]) x[i].toString().toInt() else 0
        }
}