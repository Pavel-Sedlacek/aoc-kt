package y2017

import utils.Day
import utils.readers.asLines

class Day04 : Day<Int> {

    private val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(values: List<String>) =
        values.map { it.split("\\s".toRegex()) }
            .count { it.size == it.toSet().size }

    private fun partTwo(values: List<String>) =
        values.map { it.split("\\s".toRegex()).map { it.toCharArray().sorted().joinToString("") } }
            .count { it.size == it.toSet().size }
}