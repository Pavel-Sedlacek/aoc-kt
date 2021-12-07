package y2021

import utils.Day
import utils.common.incrementalCount
import utils.crabFuelUsageSolver
import utils.readers.asIntsDividedBy

class Day07 : Day<Long> {

    val input = file.asIntsDividedBy(",".toRegex())

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<Int>): Long = crabFuelUsageSolver(input)

    private fun partTwo(input: List<Int>): Long = crabFuelUsageSolver(input) { x -> x.incrementalCount() }
}