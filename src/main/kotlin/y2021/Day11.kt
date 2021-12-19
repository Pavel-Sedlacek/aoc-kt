package y2021

import utils.Day
import utils.helpers.y2021.Octopuses
import utils.helpers.y2021.sequenceOfFlashes
import utils.readers.asLines

class Day11 : Day<Int> {

    private val octopuses = file
        .asLines()
        .map { row -> row.toCharArray().map { it.digitToInt() }.toTypedArray() }
        .toTypedArray()
        .let { Octopuses(it) }

    override fun runAll() = super.run({ partOne(octopuses) }) { partTwo(octopuses) }


    fun partOne(octopuses: Octopuses): Int {
        return sequenceOfFlashes(octopuses)
            .take(100)
            .sum()
    }

    fun partTwo(octopuses: Octopuses): Int {
        return sequenceOfFlashes(octopuses)
            .takeWhile { octopuses.areNotSynchronized() }
            .count() + 101
    }
}