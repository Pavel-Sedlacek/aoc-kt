package y2021

import utils.Day
import utils.readers.asLines

class Day08 : Day<Int> {

    private val entries = file.asLines()
        .map { it.split(" | ") }
        .map { (patterns, output) ->
            patterns.split(" ").map { it.toSet() } to output.split(" ").map { it.toSet() }
        }

    override fun runAll() = super.run({ partOne(entries) }) { partTwo(entries) }

    private fun partOne(outputInput: List<Pair<List<Set<Char>>, List<Set<Char>>>>): Int =
        outputInput.flatMap { it.second }.count { it.size in arrayOf(2, 3, 4, 7) }

    private fun partTwo(inputInput: List<Pair<List<Set<Char>>, List<Set<Char>>>>): Int =
        inputInput.sumOf { (patterns, output) ->
            val mappedDigits = mutableMapOf(
                1 to patterns.first { it.size == 2 },
                4 to patterns.first { it.size == 4 },
                7 to patterns.first { it.size == 3 },
                8 to patterns.first { it.size == 7 },
            )

            with(mappedDigits) {
                put(3, patterns.filter { it.size == 5 }.first { it.intersect(getValue(1)).size == 2 })
                put(2, patterns.filter { it.size == 5 }.first { it.intersect(getValue(4)).size == 2 })
                put(5, patterns.filter { it.size == 5 }.first { it !in values })
                put(6, patterns.filter { it.size == 6 }.first { it.intersect(getValue(1)).size == 1 })
                put(9, patterns.filter { it.size == 6 }.first { it.intersect(getValue(4)).size == 4 })
                put(0, patterns.filter { it.size == 6 }.first { it !in values })
            }

            output.joinToString("") {
                mappedDigits.entries.associateBy({ it.value }) { it.key }.getValue(it).toString()
            }.toInt()
        }
}