package y2020

import java.io.File

fun main() {
    val input = File("src/main/resources/Day25.txt").readLines()
    println("Day TWENTY-FIVE : handshake encryption key")
    println("Part One ${solvePart1(input[0], input[1])}")
    println("Part Two Ha dabumts...")
}

class Day25 {
    fun run() {
        val input = File("src/main/resources/Day25.txt").readLines()
        println("Day TWENTY-FIVE : handshake encryption key")
        println("Part One ${solvePart1(input[0], input[1])}")
        println("Part Two Ha dabumts...")
    }
}

fun solvePart1(s: String, s1: String): Long {
    val cardKey = s.toLong()
    val doorKey = s1.toLong()
    var loopSize = 0L
    var value = 1L
    while (value != cardKey) {
        value = 7 * value % 20201227
        loopSize++
    }
    var key = 1L
    for (i in 0 until loopSize) {
        key = doorKey * key % 20201227
    }
    return key
}