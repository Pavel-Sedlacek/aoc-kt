package y2015

import utils.Day
import utils.Files
import utils.Helpers.Gate

class Day07 : Day {

    private val input = Files.readFileAsLines(2015, 7)

    override fun runAll() {
        println("Day 07: Gate inputs")
        val p1 = partOne(input)
        println(p1)
        println(partTwo(input, p1))
    }

    private fun partOne(input: List<String>): Int {
        val nonComputedGates = mutableMapOf<String, Gate>()
        val computedGates = mutableMapOf<String, Gate>()
        val move = mutableMapOf<String, Gate>()
        for (i in input) nonComputedGates[i.substringAfter("->").trim()] = Gate(i.substringBefore("->").trim())

        while (nonComputedGates.isNotEmpty()) {
            for ((_, value) in nonComputedGates) value.compute(computedGates)
            for ((key, value) in nonComputedGates) if (value.isComputed()) move[key] = value
            for ((key, value) in move) nonComputedGates.remove(key).also { computedGates[key] = value }
            move.clear()
        }
        return computedGates["a"]!!.retrieve()!!
    }

    private fun partTwo(input: List<String>, p1: Int): Int {
        val inputSubstB = input.toMutableList()
        inputSubstB[inputSubstB.indexOf(inputSubstB.find { it.matches("^.*->\\sb$".toRegex()) })] = "$p1 -> b"
        return partOne(inputSubstB)
    }
}


fun main() {
    Day07().runAll()
}


