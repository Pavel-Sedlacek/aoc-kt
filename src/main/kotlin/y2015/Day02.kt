package y2015

import utils.Day
import utils.Files

class Day02 : Day {

    private val input = Files.readFileAsLines(2015, 2)

    override fun runAll() {
        println("Day 02 : wrapping paper")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(input: List<String>) =
        input.fold(0) { acc, it ->
            val x = it.split("x").map { it.toInt() }
            val z = listOf(
                x[0] * x[1],
                x[1] * x[2],
                x[0] * x[2]
            )
            z.sum() * 2 + (z.minOrNull() ?: 0)
        }

    private fun partTwo(input: List<String>) =
        input.fold(0) { acc, it ->
            val x = it.split("x").map { it.toInt() }.sorted()
            x[0] * 2 + x[1] * 2 + x.reduce { acc1, i -> acc1 * i }
        }
}