package y2021

import utils.Day
import utils.common.limitBy
import utils.readers.asInts

class Day01 : Day<Int> {

    val input = file.asInts()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<Int>): Int {
        var acc = 0
        for (i in 1 until input.size) if (input[i] > input[i - 1]) acc++
        return acc
    }

    private fun partTwo(input: List<Int>): Int {
        return partOne(input.mapIndexed { index, _ ->
            input.slice(index..(index + 2).limitBy(0, input.size - 1)).sum()
        })
    }
}