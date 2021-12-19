package y2021

import utils.Day
import utils.helpers.y2021.matching
import utils.helpers.y2021.scoresCompletion
import utils.helpers.y2021.scoresIllegal
import utils.readers.asLines
import java.util.*

class Day10 : Day<Number> {

    private val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<String>): Int {
        return input
            .sumOf { line ->
                with(ArrayDeque<Char>()) {
                    line.sumOf { c ->
                        when {
                            c in matching.keys -> (0).also { addLast(c) }
                            matching[removeLast()] != c -> scoresIllegal.getValue(c)
                            else -> 0
                        }
                    }
                }
            }
    }

    private fun partTwo(input: List<String>): Long {
        return input
            .map { line ->
                with(ArrayDeque<Char>()) {
                    line.forEach { c ->
                        when {
                            c in matching.keys -> addLast(c)
                            matching[removeLast()] != c -> return@map null
                        }
                    }
                    reversed()
                        .map { matching.getValue(it) }
                        .fold(0L) { acc, c -> (acc * 5 + scoresCompletion.getValue(c)) }
                }
            }
            .filterNotNull()
            .sorted()
            .let { it[it.size / 2] }
    }
}