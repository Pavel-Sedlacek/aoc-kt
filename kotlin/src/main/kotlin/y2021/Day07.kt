package y2021

import utils.Day
import utils.readers.asIntsDividedBy
import kotlin.math.abs

class Day07 : Day<Int> {

    val input = file.asIntsDividedBy(",".toRegex()).sorted()

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    fun partOne(input: List<Int>): Int {
        val median = input[input.size / 2]
        return input.sumOf { i -> abs(median - i) }
    }

    fun partTwo(input: List<Int>): Int {
        val average = input.average().toInt()
        return (average..average + 1).minOf { pos ->
            input.sumOf { i -> ((1 + abs(pos - i)) * abs(pos - i) / 2) }
        }
    }
}
