package y2021

import utils.Day
import utils.helpers.y2021.Cuboid
import utils.helpers.y2021.solveCuboids
import utils.readers.asLines

class Day22 : Day<Long> {

    val input = file.asLines().map { Cuboid.from(it) }

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<Cuboid>): Long = solveCuboids(input.take(20))

    private fun partTwo(input: List<Cuboid>): Long = solveCuboids(input)
}
