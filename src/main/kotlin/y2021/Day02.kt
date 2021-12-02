package y2021

import utils.Day
import utils.readers.asLines

class Day02 : Day<Any> {

    val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<String>): Int {
        return input.fold(Pair(0, 0)) { acc, i ->
            i.split(" ").let {
                when (it[0]) {
                    "forward" -> Pair(acc.first + it[1].toInt(), acc.second)
                    "up" -> Pair(acc.first, acc.second - it[1].toInt())
                    "down" -> Pair(acc.first, acc.second + it[1].toInt())
                    else -> acc
                }
            }
        }.let { it.first * it.second }
    }

    private fun partTwo(input: List<String>): Int {
        return input.fold(Triple(0, 0, 0)) { acc, i ->
            i.split(" ").let {
                when (it[0]) {
                    "forward" -> Triple(acc.first + it[1].toInt(), acc.second + (it[1].toInt() * acc.third), acc.third)
                    "up" -> Triple(acc.first, acc.second, acc.third - it[1].toInt())
                    "down" -> Triple(acc.first, acc.second, acc.third + it[1].toInt())
                    else -> acc
                }
            }
        }.let { it.first * it.second }
    }
}