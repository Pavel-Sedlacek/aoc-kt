package y2015

import utils.Day
import utils.Files
import utils.Helpers.allPermutations

class Day13 : Day {

    private val input: Map<Pair<String, String>, Int> =
        Files.readFileAsLines(2015, 13).associate { line ->
            line.split(" ")
                .let { (it[0] to it[10].replace(".", "")) to if (it[2] == "gain") +it[3].toInt() else -it[3].toInt() }
        }

    override fun runAll() {
        println("Day 13: Table sitting order")
        println(partOne(input, input.keys.flatMap { listOf(it.first, it.second) }.toSet().allPermutations()))
        println(partTwo(input))
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

    private fun partTwo(input: Map<Pair<String, String>, Int>): Int {
        return input.toMutableMap().let { inp ->
            val x = input.keys.map { it.first }.toSet()
            for (i in x) {
                inp.put(Pair("I", i), 0)
                inp.put(Pair(i, "I"), 0)
            }
            inp.keys.flatMap { listOf(it.first, it.second) }.toSet().allPermutations().map {
                var count = 0
                for (i in it.indices) {
                    count += inp[Pair(it[i], it[(i + 1) % it.size])]!!
                    count += inp[Pair(it[i], it[(i - 1).let { v -> if (v < 0) it.size + v else v }])]!!
                }
                count
            }
        }.maxOrNull()!!
    }

}