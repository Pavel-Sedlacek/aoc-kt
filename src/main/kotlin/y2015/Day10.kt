package y2015

import utils.Day
import utils.Patterns.number
import utils.common.literalString

class Day10 : Day<String> {

    private val input = file

    override fun runAll() {
        println("Day 10: number of numbers")
        val x = solve(input, 40)
        super.run({ x }, { solve(x, 10) })
    }

    private fun solve(str: String, iterations: Int): String {
        var input = str
        for (i in 0 until iterations) input = input.literalString(number)
        return input
    }
}

