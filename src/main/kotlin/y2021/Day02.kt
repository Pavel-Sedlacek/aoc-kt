package y2021

import utils.Day
import utils.readers.asLines

class Day02 : Day<Int> {

    val input = file.asLines().map { it.split(" ") }

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<List<String>>): Int {
        var horizontal = 0
        var depth = 0

        input.forEach { (command, value) ->
            when (command) {
                "forward" -> horizontal += value.toInt()
                "down" -> depth += value.toInt()
                "up" -> depth -= value.toInt()
            }
        }

        return horizontal * depth
    }

    private fun partTwo(input: List<List<String>>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0

        input.forEach { (command, value) ->
            when (command) {
                "forward" -> {
                    horizontal += value.toInt()
                    depth += aim * value.toInt()
                }
                "down" -> aim += value.toInt()
                "up" -> aim -= value.toInt()
            }
        }

        return horizontal * depth
    }
}