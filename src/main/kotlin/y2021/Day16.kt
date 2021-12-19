package y2021

import utils.Day
import utils.helpers.y2021.Packet
import utils.helpers.y2021.parse

class Day16 : Day<Number> {

    val input = parse(file.map { it.digitToInt(16).toString(2).padStart(4, '0') }.joinToString(""))

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: Packet): Int = input.sumOfVersions()

    private fun partTwo(input: Packet): Long = input.value()
}
