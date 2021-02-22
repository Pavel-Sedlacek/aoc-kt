package y2020

import java.io.File

fun main() {
    val x = File("src/main/resources/Day08.txt").readText().split("\n")
    println("Day EIGHT : text-code machine")
    println("Part One ${partOneEight(x)}")
    println("Part Two ${partTwoEight(x)}")
}

class Day8 {
    fun run() {
        val x = File("src/main/resources/Day08.txt").readText().split("\n")
        println("Day EIGHT : text-code machine")
        println("Part One ${partOneEight(x)}")
        println("Part Two ${partTwoEight(x)}")
    }
}

fun partOneEight(x: List<String>): Int {
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

fun partTwoEight(x: List<String>) : Int {
    x.forEachIndexed { index, i ->
        if (i.contains("jmp") or i.contains("nop")) {
            val y = x.toMutableList()
            if (i.contains("jmp"))
                y[index] = "nop ${x[index].split(" ")[1]}"
            else
                y[index] = "jmp ${x[index].split(" ")[1]}"
            val j = recursiveGo(y)
            if (j != -1) return j
        }
    }
    return 0
}

fun recursiveGo(x: List<String>) : Int {
    val visitedLines = (0..x.size).toMutableSet()
    var accumulator = 0
    var i = 0
    while (i < x.size) {
        if (!visitedLines.contains(i)) return -1
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
    return accumulator
}