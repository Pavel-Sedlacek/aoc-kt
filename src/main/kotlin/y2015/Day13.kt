package y2015

import utils.Day
import utils.collections.allPermutations
import utils.readers.asLines

class Day13 : Day<Int> {

    private val input: Map<Pair<String, String>, Int> =
        file.asLines().associate { line ->
            line.split(" ")
                .let { (it[0] to it[10].replace(".", "")) to if (it[2] == "gain") +it[3].toInt() else -it[3].toInt() }
        }

    override fun runAll() = super.run(
        { partOne(input, input.keys.flatMap { listOf(it.first, it.second) }.toSet().allPermutations()) },
        { partTwo(input) }
    )

    private fun partOne(input: Map<Pair<String, String>, Int>, permutations: Set<List<String>>): Int {
        return permutations.maxOf {
            var count = 0
            for (i in it.indices) {
                count += input[Pair(it[i], it[(i + 1) % it.size])]!!
                count += input[Pair(it[i], it[(i - 1).let { v -> if (v < 0) it.size + v else v }])]!!
            }
            count
        }
    }

    private fun partTwo(input: Map<Pair<String, String>, Int>): Int {
        return input.toMutableMap().let { inp ->
            val x = input.keys.map { it.first }.toSet()
            for (i in x) {
                inp[Pair("I", i)] = 0
                inp[Pair(i, "I")] = 0
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