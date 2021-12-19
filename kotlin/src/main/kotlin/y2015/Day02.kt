package y2015

import utils.Day
import utils.collections.product
import utils.readers.asLines

class Day02 : Day<Int> {

    private val input = file.asLines().map { it.split("x").map { it.toInt() } }

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<List<Int>>): Int {
        var area = 0
        for (i in input) {
            area += listOf(
                i[0] * i[1],
                i[0] * i[2],
                i[1] * i[2]
            ).let {
                it.sum() * 2 + (it.minOrNull() ?: 0)
            }
        }
        return area
    }

    private fun partTwo(input: List<List<Int>>): Int {
        var area = 0
        for (i in input) {
            area += i.sorted().let {
                it[0] * 2 + it[1] * 2 + it.product()
            }
        }
        return area
    }
}