package y2021

import utils.Day
import utils.helpers.y2021.State
import utils.helpers.y2021.organizeAmphipods
import utils.readers.asLines


class Day23 : Day<Number> {

    val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<String>) = organizeAmphipods(State.from(input))
    private fun partTwo(input: List<String>) =
        organizeAmphipods(State.from(input.take(3) + "  #D#C#B#A#  " + "  #D#B#A#C#  " + input.takeLast(2)))
}