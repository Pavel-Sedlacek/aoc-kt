package y2017

import utils.Day
import utils.helpers.y2017.GridOfExpandingNumbers
import utils.helpers.y2017.lengthOfSideWith
import utils.helpers.y2017.midpointsForSideLength
import utils.readers.asInt
import kotlin.math.abs

class Day03 : Day<Int> {

    private val input = file.asInt()

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    fun partOne(input: Int): Int {
        val sideLength = lengthOfSideWith(input)
        val stepsToRingFromCenter = (sideLength - 1) / 2
        val midpoints = midpointsForSideLength(sideLength)
        return stepsToRingFromCenter + midpoints.minOf { abs(input - it) }
    }

    private fun partTwo(input: Int) =
        GridOfExpandingNumbers(lengthOfSideWith(input))
            .generateSpots()
            .first { it > input }
}