package y2017

import utils.Day
import utils.readers.Reader
import utils.Helpers.jump
import utils.collections.mut
import utils.readers.asInts

class Day05 : Day<Int> {

    private val input = file.asInts()

    override fun runAll() = super.run({partOne(input)}, {partTwo(input)})

    private fun partOne(input: List<Int>) = jump(input.mut(), { 1 })

    private fun partTwo(input: List<Int>) = jump(input.mut(), { if (it >= 3) -1 else 1 })
}