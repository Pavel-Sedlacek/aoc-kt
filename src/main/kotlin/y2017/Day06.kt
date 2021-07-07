package y2017

import utils.Day
import utils.Files
import utils.Helpers.reallocate

class Day06 : Day {

    private val input = Files.readFileAsIntsDividedBy(2017, 6, "\\s".toRegex())

    override fun runAll() {
        println("Day 06 : Memory redistribution")
        println(partOne(input.toMutableList()))
        println(partTwo(input.toMutableList()))
    }

    private fun partOne(input: MutableList<Int>) = reallocate(input) { map, _ -> map.size }

    private fun partTwo(input: MutableList<Int>) = reallocate(input) { map, key -> (map.size) - map.getValue(key) }

}