package y2015

import utils.Day

class Day01 : Day<Int> {

    private val input = file

    override fun runAll() = super.run(partOne(input), partTwo(input))

    private fun partOne(input: String): Int {
        return input.fold(0) { acc, c ->
            if (c == '(') acc + 1 else acc - 1
        }
    }

    private fun partTwo(input: String): Int {
        var x = 0
        for (i in input.indices) {
            if (input[i] == '(') x += 1
            else x - 1
            if (x < 0) return i
        }
        return x
    }

}