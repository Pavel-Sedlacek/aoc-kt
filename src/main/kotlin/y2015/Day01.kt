package y2015

import utils.Day
import utils.Files

class Day01: Day{

    private val input = Files.readFileAsString(2015, 1)

    override fun runAll() {
        println("Day 01 : () floors")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(input: String) = input.fold(0) { acc, c -> if (c == '(') acc + 1 else acc - 1 }
    private fun partTwo(input: String) = input.foldIndexed(0) { acc, i, c -> if (acc < 0) {println(i + 1)}; return@foldIndexed if (c == '(') acc + 1 else acc - 1 }

}