package y2021

import utils.Day
import utils.helpers.y2021.findCommonAtPosition
import utils.readers.asLines

class Day03 : Day<Int> {

    val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    fun partOne(input: List<String>): Int {
        val gamma = input.map { number -> number.toList().map { it.toString() } }
            .reduce { first, second -> first.zip(second).map { it.first + it.second } }
            .joinToString("") { bitRow -> if (bitRow.count { it == '1' } > bitRow.length / 2) "1" else "0" }

        val epsilon = gamma.map { (1 - it.digitToInt()) }.joinToString("")

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun partTwo(input: List<String>): Int {
        val oxygen = (0 until input.first().length).fold(input) { filtered, position ->
            val mostCommon = filtered.findCommonAtPosition(leastCommon = false, position)

            if (filtered.size == 1) filtered else filtered.filter { it[position] == mostCommon }
        }.single()

        val co2 = (0 until input.first().length).fold(input) { filtered, position ->
            val leastCommon = filtered.findCommonAtPosition(leastCommon = true, position)

            if (filtered.size == 1) filtered else filtered.filter { it[position] == leastCommon }
        }.single()

        return oxygen.toInt(2) * co2.toInt(2)
    }


}


