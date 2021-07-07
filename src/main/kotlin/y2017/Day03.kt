package y2017

import utils.Day
import utils.Files
import utils.Helpers
import utils.Helpers.lengthOfSideWith
import utils.Helpers.midpointsForSideLength
import kotlin.math.abs

class Day03 : Day {

    private val input = Files.readFileAsInts(2017, 3).singleOrNull()!!

    override fun runAll() {
        println("Day 03 : Memory distance")
        println(partOne(input))
        println(partTwo(input))
    }

    fun partOne(input: Int): Int {
        val sideLength = lengthOfSideWith(input)
        val stepsToRingFromCenter = (sideLength - 1) / 2
        val midpoints = midpointsForSideLength(sideLength)
        return stepsToRingFromCenter + midpoints.minOf { abs(input - it) }
    }

    private fun partTwo(input: Int) =
        Helpers.Grid(lengthOfSideWith(input))
            .generateSpots()
            .first { it > input }
}