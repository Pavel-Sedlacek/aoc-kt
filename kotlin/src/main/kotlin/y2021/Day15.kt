package y2021

import utils.Day
import utils.helpers.y2021.expand
import utils.helpers.y2021.lowestRiskPath
import utils.readers.asLines

class Day15 : Day<Int> {

    val input = file.asLines().map { row -> row.toCharArray().map { it.digitToInt() } }

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<List<Int>>): Int = lowestRiskPath(input)

    private fun partTwo(input: List<List<Int>>): Int = lowestRiskPath(input.expand())
}