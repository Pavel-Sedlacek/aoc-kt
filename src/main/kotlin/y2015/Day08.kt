package y2015

import utils.Day
import utils.Files

class Day08 : Day {

    private val input = Files.readFileAsLines(2015, 8)

    override fun runAll() {
        println("Day 08: Character length x memory size")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(input: List<String>): Int = input.fold(0) { acc: Int, line: String ->
        acc + ((line.length + 2) - line.memorySize())
    }

    private fun partTwo(input: List<String>): Int = input.fold(0) { acc: Int, line: String ->
        acc + ((line.pureLength()) - (line.length + 2))
    }
}

fun String.pureLength(): Int {
    return StringBuilder("\\\"").also {
        for (i in this) it.append("${if (i == '\\' || i == '\"') '\\' else ""}$i")
        it.append("\\\"")
    }.length
}

fun String.memorySize(): Int {
    var x = this
    x = x.replace("\\\\", "*")
    x = x.replace("\\\"", "*")
    x = x.replace("\\\\x[0-9a-f]{2}".toRegex(), "*")
    return x.length
}