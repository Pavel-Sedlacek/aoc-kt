package y2015

import utils.Day
import utils.collections.allPermutations
import utils.readers.asLines

class Day09 : Day<Int> {

    private val input =
        file.asLines().associate { line -> line.split(" ").let { (it[0] to it[2]) to it[4].toInt() } }

    private val permutations = input.keys.flatMap { listOf(it.first, it.second) }.toSet().allPermutations().map {
        it.foldIndexed(0) { index: Int, acc: Int, s: String ->
            if (index + 1 < it.size)
                acc + (input[Pair(s, it[index + 1])] ?: input[Pair(it[index + 1], s)] ?: throw Error())
            else
                acc
        }
    }

    override fun runAll() = super.run({ permutations.minOrNull() }, { permutations.maxOrNull() })
}

