package y2021

import utils.Day
import utils.readers.asLines

class Day14 : Day<Long> {

    private val input = file.split("\n\n")
    private val polymer = input[0].trim()
    private val insertions = input[1].asLines().map { line -> line.split(" -> ") }.associate { it[0] to it[1] }

    override fun runAll() = super.run({ partOne(polymer, insertions) }, { partTwo(polymer, insertions) })

    private fun partOne(input: String, insertions: Map<String, String>): Long = polymerize(input, insertions, 10)

    private fun partTwo(input: String, insertions: Map<String, String>): Long = polymerize(input, insertions, 40)
}

private fun insert(adjacentPairs: Map<String, Long>, rules: Map<String, String>) =
    mutableMapOf<String, Long>().withDefault { 0 }.apply {
        adjacentPairs.forEach { (adjacentPair, count) ->
            val middle = rules.getValue(adjacentPair)
            val first = "${adjacentPair[0]}$middle"
            val second = "$middle${adjacentPair[1]}"
            put(first, getValue(first) + count)
            put(second, getValue(second) + count)
        }
    }

private fun elementCount(adjacentPairs: Map<String, Long>, first: Char, last: Char) =
    mutableMapOf<Char, Long>().withDefault { 0 }.apply {
        adjacentPairs.entries.forEach { (adjacentPair, count) ->
            put(adjacentPair[0], getValue(adjacentPair[0]) + count)
            put(adjacentPair[1], getValue(adjacentPair[1]) + count)
        }
        put(first, getValue(first) + 1)
        put(last, getValue(last) + 1)
    }.mapValues { it.value / 2 }


private fun polymerize(polymer: String, insertions: Map<String, String>, iterations: Int): Long {
    var adjacentPairs = polymer.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }
    repeat(iterations) { adjacentPairs = insert(adjacentPairs, insertions) }
    return elementCount(adjacentPairs, polymer.first(), polymer.last())
        .values.let { counts -> counts.maxOf { it } - counts.minOf { it } }
}