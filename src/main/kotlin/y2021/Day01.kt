package y2021

import utils.Day
import utils.helpers.y2021.countIncreased
import utils.readers.asInts

class Day01 : Day<Int> {

    val input = file.asInts()

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<Int>): Int = countIncreased(input, 2)

    private fun partTwo(input: List<Int>): Int = countIncreased(input, 4)
}

