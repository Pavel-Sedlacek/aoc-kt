package y2021

import utils.Day
import utils.jellyFishSolver
import utils.readers.asIntsDividedBy

@ExperimentalUnsignedTypes
class Day06 : Day<ULong> {

    val input = file.asIntsDividedBy(",".toRegex()).groupBy { it }.map { it.key to it.value.size.toULong() }

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<Pair<Int, ULong>>): ULong = jellyFishSolver(input, 80)

    private fun partTwo(input: List<Pair<Int, ULong>>): ULong = jellyFishSolver(input, 256)
}