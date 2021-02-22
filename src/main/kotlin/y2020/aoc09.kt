package y2020

import java.io.File

fun main() {
    val x = File("src/main/resources/Day09.txt").useLines { it.toList() }.map { it.toLong() }.toMutableList()
    val targ = partOneNine(x)
    println("Day NINE : each number is sum of 2 of the previous 25 numbers")
    println("Part One $targ")
    println("Part Two ${partTwoNine(x, targ)}")
}

class Day9 {
    fun run() {
        val x = File("src/main/resources/Day09.txt").useLines { it.toList() }.map { it.toLong() }.toMutableList()
        val targ = partOneNine(x)
        println("Day NINE : each number is sum of 2 of the previous 25 numbers")
        println("Part One $targ")
        println("Part Two ${partTwoNine(x, targ)}")
    }
}

fun partOneNine(x: List<Long>): Long? {
    jes@ for (i in 25 until x.size) {
        for (j in i - 25..i) {
            for (z in i - 25..i) {
                if (x[j] + x[z] == x[i]) {
                    continue@jes
                }
            }
        }
        return x[i]
    }
    return null
}

fun partTwoNine(x: List<Long>, target: Long?): Long? {
    for (i in x.indices) {
        for (z in i until x.size) {
            if ((x.subList(i, z)).sum() > target!!) {
                break
            } else if ((x.subList(i, z)).sum() == target) {
                return ((x.subList(i, z)).minOrNull()?.plus((x.subList(i, z)).maxOrNull()!!))
            }
        }
    }
    return null
}
