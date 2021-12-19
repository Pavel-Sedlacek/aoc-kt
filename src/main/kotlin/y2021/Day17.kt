package y2021

import utils.Day
import utils.helpers.y2021.TargetArea
import utils.helpers.y2021.Velocity
import utils.helpers.y2021.sequenceSum

class Day17 : Day<Int> {

    private val input = Regex("""target area: x=(\d+)\.\.(\d+), y=(-\d+)\.\.(-\d+)""")
        .matchEntire(file)!!
        .destructured.let { (x1, x2, y1, y2) -> TargetArea(x1.toInt(), x2.toInt(), y1.toInt(), y2.toInt()) }

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: TargetArea): Int = (-input.y1 - 1).sequenceSum()

    private fun partTwo(input: TargetArea): Int =
        (((1..input.x1).first { it.sequenceSum() >= input.x1 })..input.x2)
            .sumOf { x -> (input.y1 until -input.y1).count { y -> Velocity(x, y).willBeWithin(input) } }
}