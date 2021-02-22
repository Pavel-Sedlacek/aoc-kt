package y2020

import java.io.File

fun main() {
    val input = File("src/main/resources/Day01.txt").useLines { it.toList() }.map { i -> i.toInt() }
    println("Day ONE : find product of two/three numbers that sums to 2020")
    println("Part One ${partOneOne(input)}")
    println("Part Two ${partTwoOne(input)}")
}

class Day1 {
    fun run() {
        val input = File("src/main/resources/Day01.txt").useLines { it.toList() }.map { i -> i.toInt() }
        println("Day ONE : find product of two/three numbers that sums to 2020")
        println("Part One ${partOneOne(input)}")
        println("Part Two ${partTwoOne(input)}")
    }
}

fun partOneOne(x: List<Int>): Int {
    for (n1 in x) {
        if (n1 > 2020) continue
        for (n2 in x)
            if (n1 + n2 == 2020)
                return n1 * n2
    }
    return 0
}

fun partTwoOne(x: List<Int>): Int {
    for (n1 in x) {
        if (n1 > 2020) continue
        for (n2 in x) {
            if (n1 + n2 > 2020) continue
            for (n3 in x)
                if (n1 + n2 + n3 == 2020)
                    return n1 * n2 * n3
        }
    }
    return 0
}