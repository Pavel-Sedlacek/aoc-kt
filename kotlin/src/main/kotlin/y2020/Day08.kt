package y2020

import utils.Day
import utils.helpers.y2020.recursiveMiniIntCode
import utils.readers.asLines

class Day08 : Day<Int> {
    private val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(x: List<String>): Int {
        val visitedLines = (0..x.size).toMutableSet()
        var accumulator = 0
        var i = 0
        while (i < x.size) {
            if (!visitedLines.contains(i)) return accumulator
            else visitedLines.remove(i)
            val currentLine = x[i].split(" ")
            when (currentLine[0]) {
                "acc" -> {
                    accumulator += currentLine[1].toInt()
                }
                "jmp" -> {
                    i += currentLine[1].toInt()
                    continue
                }
            }
            i++
        }
        return 0
    }

    private fun partTwo(x: List<String>): Int {
        x.forEachIndexed { index, i ->
            if (i.contains("jmp") or i.contains("nop")) {
                val y = x.toMutableList()
                if (i.contains("jmp"))
                    y[index] = "nop ${x[index].split(" ")[1]}"
                else
                    y[index] = "jmp ${x[index].split(" ")[1]}"
                val j = recursiveMiniIntCode(y)
                if (j != -1) return j
            }
        }
        return 0
    }
}