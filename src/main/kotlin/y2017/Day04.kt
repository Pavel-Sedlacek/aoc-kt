package y2017

import utils.Day
import utils.Files

class Day04 : Day {

    private val input = Files.readFileAsLines(2017, 4)

    override fun runAll() {
        println("Day 04 : passphrases")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(values: List<String>) =
        values.map { it.split("\\s".toRegex()) }
            .count { it.size == it.toSet().size }

    private fun partTwo(values: List<String>) =
        values.map { it.split("\\s".toRegex()).map { it.toCharArray().sorted().joinToString("") } }
            .count {it.size == it.toSet().size}
}