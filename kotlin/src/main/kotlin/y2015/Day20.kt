package y2015

import utils.Day
import utils.common.divisors
import utils.readers.asInt

class Day20 : Day<Int> {

    private val input = file.asInt()

    override fun runAll() = super.run({ partOne(input) }) { -1 }


    private fun partOne(input: Int): Int {
        houseSequence().indexOf(input)
        return 1
    }
}


fun houseSequence() = sequence {
    for (i in 1 until Int.MAX_VALUE) {
        yield(i.divisors().sumOf { it * 10 })
    }
}