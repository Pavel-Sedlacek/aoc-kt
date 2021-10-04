package y2015

import utils.Day
import utils.Files
import utils.Helpers.allPermutations

fun main() {
    Day13().runAll()
}

class Day13 : Day {

    private val input: Map<Pair<String, String>, Int> =
        Files.readFileAsLines(2015, 13).associate { line ->
            line.split(" ")
                .let { (it[0] to it[10].replace(".", "")) to if (it[2] == "gain") +it[3].toInt() else -it[3].toInt() }
        }

    override fun runAll() {
        println(partOne(input, input.keys.flatMap { listOf(it.first, it.second) }.toSet().allPermutations()))
    }

    private fun partOne(input: Map<Pair<String, String>, Int>, permutations: Set<List<String>>): Int {
        return permutations.map {
            var count = 0
            for (i in it.indices) {
                count += input[Pair(it[i], it[(i + 1) % it.size])]!!
                count += input[Pair(it[i], it[(i - 1).let { v -> if (v < 0) it.size + v else v }])]!!
            }
            count
        }.maxOrNull()!!
    }

}