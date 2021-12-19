package y2017

import utils.Day

class Day01 : Day<Int> {

    private val input = file

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(x: String) =
        x.foldIndexed(0) { i, acc, c ->
            acc + if (x[i] == x[(i + 1) % x.length]) x[i].toString().toInt() else 0
        }

    private fun partTwo(x: String) =
        x.foldIndexed(0) { i, acc, c ->
            acc + if (x[i] == x[(i + x.length / 2) % x.length]) x[i].toString().toInt() else 0
        }
}