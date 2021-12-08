package y2021

import utils.Day
import utils.readers.asLines

class Day08 : Day<Int> {

    private val input: List<List<String>> = file.asLines().map { it.split(" | ") }
    private val inputDigits = input.map { it[0].split(" ") }
    private val outputDigits = input.map { it[1].split(" ") }

    override fun runAll() = super.run({ partOne(outputDigits) }, { partTwo(inputDigits, outputDigits) })

    private fun partOne(outputInput: List<List<String>>): Int =
        outputInput.sumBy { list -> list.sumBy { if (it.length in listOf(2, 3, 4, 7)) 1 else 0 } }

    private fun partTwo(inputInput: List<List<String>>, outputInput: List<List<String>>): Int =
        outputInput.foldIndexed(0) { index, acc, list ->
            acc + list.fold("") { acc: String, s: String ->
                acc + createMappings(inputInput[index]).indexOf(s.toList().sorted().joinToString(""))
            }.toInt()
        }
}

private fun createMappings(list: List<String>): List<String> = MutableList(10) { "" }.also { mixMapping ->
    list.map { it.toList().sorted().joinToString("") }.sortedBy { it.length }.forEach {
        when (it.length) {
            2 -> mixMapping[1] = it
            3 -> mixMapping[7] = it
            4 -> mixMapping[4] = it
            7 -> mixMapping[8] = it
            5 -> if (it.toList().intersect(mixMapping[1].toSet()).size == 2) mixMapping[3] = it
            else if (it.toList().intersect(mixMapping[4].toSet()).size == 3) mixMapping[5] = it
            else mixMapping[2] = it
            6 -> if (it.toList().intersect(mixMapping[1].toSet()).size < 2) mixMapping[6] = it
            else if (it.toList().intersect(mixMapping[4].toSet()).size == 4) mixMapping[9] = it
            else mixMapping[0] = it
        }
    }
}