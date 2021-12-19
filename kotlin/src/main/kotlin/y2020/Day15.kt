package y2020

import utils.Day
import utils.readers.asIntsDividedBy

class Day15 : Day<Int> {

    private val input = file.asIntsDividedBy(",".toRegex())

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<Int>): Int {
        var x = input
        while (x.size < 2020) {
            val curr = x.last()
            x = x.dropLast(1).toMutableList()
            val indexed = x.lastIndexOf(curr)
            x.add(curr)
            if (indexed == -1) {
                x.add(0)
            } else {
                x.add(x.size - indexed - 1)
            }
        }
        return x.last()
    }

    private fun partTwo(input: List<Int>): Int {
        val nums = input.toMutableList()
        val last = mutableMapOf<Int, Int>()
        var curr = -1
        var turn = 1
        var prev = nums.last()
        for (n in nums.dropLast(1)) {
            last[n] = turn
            turn++
        }
        turn++
        while (turn < 30_000_001) {
            val lastSpokenTurn = last[prev]
            curr = if (lastSpokenTurn != null) turn - 1 - lastSpokenTurn else 0
            last[prev] = turn - 1
            prev = curr
            turn++
        }
        return curr
    }
}