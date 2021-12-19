package y2017

import utils.Day
import utils.readers.asLines

class Day02 : Day<Int> {

    private val input = file.asLines().map { it.split("\\s".toRegex()).map { it.toInt() } }

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<List<Int>>) = input.fold(0) { acc, c ->
        acc + c.maxOrNull()?.minus(c.minOrNull()!!)!!
    }

    // FIXME PLS
    private fun partTwo(input: List<List<Int>>): Int {
        var sum = 0
        for (i in input) {
            skip@ for (x in i) {
                for (y in i) {
                    if (x != y && x % y == 0) {
                        sum += x / y
                        break@skip
                    }
                }
            }
        }
        return sum
    }
}