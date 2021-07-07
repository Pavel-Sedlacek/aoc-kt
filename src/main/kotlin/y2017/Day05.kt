package y2017

import utils.Day
import utils.Files
import utils.Helpers.jump

class Day05 : Day {

    private val input = Files.readFileAsInts(2017, 5)

    override fun runAll() {
        println("Day 05 : Jump maze")
        println(partOne(input.toMutableList()))
        println(partTwo(input.toMutableList()))
    }

    private fun partOne(input: MutableList<Int>) = jump(input, { 1 })

    private fun partTwo(input: MutableList<Int>) = jump(input, { if (it >= 3) -1 else 1 })
}