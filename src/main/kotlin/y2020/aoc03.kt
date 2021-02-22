package y2020

import java.io.File

fun main() {
    val input = File("src/main/resources/Day03.txt").useLines { it.toList() }.toMutableList()
    println("Day THREE : count trees encountered while traversing a forest")
    println("Part ONE : ${partOneThree(input)}")
    println("Part TWO : ${partTwoThree(input)}")
}

class Day3 {
    fun run() {
        val input = File("src/main/resources/Day03.txt").useLines { it.toList() }.toMutableList()
        println("Day THREE : count trees encountered while traversing a forest")
        println("Part ONE ${partOneThree(input)}")
        println("Part TWO ${partTwoThree(input)}")
    }
}

fun partOneThree(x: MutableList<String>): Long {
    return countDiagonalTrees(3, 1, x)
}

fun partTwoThree(x: MutableList<String>): Long {
    return countDiagonalTrees(1, 1, x) *
            countDiagonalTrees(3, 1, x) *
            countDiagonalTrees(5, 1, x) *
            countDiagonalTrees(7, 1, x) *
            countDiagonalTrees(1, 2, x)
}

fun countDiagonalTrees(x: Int, y: Int, model: MutableList<String>): Long {
    var curX = 0
    var curY = 0
    var curSum: Long = 0

    while (true) {
        curX += x
        curY += y

        if (curY >= model.size) break

        while (curX >= model[curY].length) model[curY] += model[curY]

        if (model[curY][curX] == '#') {
            curSum++
        }
    }
    return curSum
}