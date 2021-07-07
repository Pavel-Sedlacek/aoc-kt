package y2017

import utils.Day
import utils.Files

class Day02 : Day {

    private val input = Files.readFileAsLines(2017, 2).map { it.split("\\s".toRegex()).map { it.toInt() } }

    override fun runAll() {
        println("Day 02 : spreadsheet folding")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(input: List<List<Int>>) = input.fold(0) { acc, c ->
        acc + c.maxOrNull()?.minus(c.minOrNull()!!)!!
    }

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