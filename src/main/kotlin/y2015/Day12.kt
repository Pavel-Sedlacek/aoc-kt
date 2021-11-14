package y2015

import utils.Day

class Day12 : Day<Int> {

    private val input = file

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })
    // TODO partTwo


    fun partOne(input: String) = Regex("-?[0-9]+").findAll(input).map { it.value.toInt() }.sum()

    fun partTwo(input: String): Int {
        return -1
    }
}