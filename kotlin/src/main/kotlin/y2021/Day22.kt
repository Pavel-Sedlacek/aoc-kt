package y2021

import utils.Day
import utils.helpers.y2021.Command
import utils.helpers.y2021.Cuboid
import utils.helpers.y2021.point3i
import utils.helpers.y2021.solveCuboids
import utils.readers.asLines

class Day22 : Day<Long> {

    val input = file.asLines().map {
        val (on, v) = it.split(" ")
        Command(on == "on", Cuboid.fromString(v))
    }

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<Command>): Long {
        val pt1Input = input.map { (cmd, cuboid) ->
            Command(
                cmd, Cuboid(
                    cuboid.start.coerceAtLeast(point3i(-50, -50, -50)),
                    cuboid.end.coerceAtMost(point3i(50, 50, 50))
                )
            )
        }.filter { (_, cuboid) -> cuboid.valid }

        return solveCuboids(pt1Input)
    }

    private fun partTwo(input: List<Command>): Long {
        return solveCuboids(input)
    }
}
