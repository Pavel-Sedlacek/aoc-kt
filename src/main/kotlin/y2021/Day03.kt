package y2021

import utils.Day
import utils.common.binaryInverse
import utils.common.filterByCommonDigit
import utils.common.mostCommonLettersAtIndices
import utils.common.toDecimal
import utils.readers.asLines

class Day03 : Day<Int> {

    val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<String>): Int =
        input.mostCommonLettersAtIndices().let { it.toDecimal() * it.binaryInverse().toDecimal() }

    private fun partTwo(input: List<String>): Int {
        var oxygen = input.toList()
        var co2 = input.toList()
        repeat(input.first().length) {
            oxygen = oxygen.filterByCommonDigit(it, false)
            co2 = co2.filterByCommonDigit(it, true)
        }
        return oxygen.first().toDecimal() * co2.first().toDecimal()
    }
}


