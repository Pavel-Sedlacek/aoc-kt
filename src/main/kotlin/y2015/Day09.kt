package y2015

import utils.Day
import utils.Files
import utils.Helpers.allPermutations
import java.lang.Error

fun main() {
    Day09().runAll()
}

class Day09 : Day {

    private val input =
        Files.readFileAsLines(2015, 9).associate { line -> line.split(" ").let { (it[0] to it[2]) to it[4].toInt() } }
    private val permutations = input.keys.flatMap { listOf(it.first, it.second) }.toSet().allPermutations().map {
        it.foldIndexed(0) { index: Int, acc: Int, s: String ->
            if (index + 1 < it.size)
                acc + (input[Pair(s, it[index + 1])] ?: input[Pair(it[index + 1], s)] ?: throw Error())
            else
                acc
        }
    }

    override fun runAll() {
        println("Day 09: Shortest and Longest graph route")
        println(permutations.minOrNull())
        println(permutations.maxOrNull())
    }
}

