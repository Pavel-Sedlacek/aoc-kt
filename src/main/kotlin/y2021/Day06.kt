package y2021

import utils.Day
import utils.helpers.y2021.simulate
import utils.readers.asIntsDividedBy

class Day06 : Day<Long> {

    private val numbers = file.asIntsDividedBy(",")
    private val cache = mutableMapOf<Int, Long>()

    private fun partOne(numbers: List<Int>) = simulate(numbers, 80, cache)
    private fun partTwo(numbers: List<Int>) = simulate(numbers, 256, cache)

    override fun runAll() = super.run({ partOne(numbers) }) { partTwo(numbers) }
}