package y2021

import utils.Day
import utils.helpers.y2021.Parameters
import utils.helpers.y2021.findModelNumber
import utils.readers.asLines

class Day24 : Day<Number> {

    private val input = file.asLines().chunked(18).map {
        Parameters(
            it[5].substringAfterLast(" ").toInt(),
            it[15].substringAfterLast(" ").toInt()
        )
    }

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<Parameters>): Long = input.findModelNumber(true)

    private fun partTwo(input: List<Parameters>): Long = input.findModelNumber(false)


}