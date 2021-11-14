package y2015

import utils.Day
import utils.common.memorySize
import utils.common.pureLength
import utils.readers.asLines

class Day08 : Day<Int> {

    private val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<String>): Int = input.fold(0) { acc: Int, line: String ->
        acc + ((line.length + 2) - line.memorySize())
    }

    private fun partTwo(input: List<String>): Int = input.fold(0) { acc: Int, line: String ->
        acc + ((line.pureLength()) - (line.length + 2))
    }
}