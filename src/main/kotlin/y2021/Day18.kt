package y2021

import utils.Day
import utils.helpers.y2021.Node
import utils.readers.asLines


class Day18 : Day<Int> {

    val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<String>): Int {
        return input.reduce { acc, s -> Node.of("[$acc,$s]").apply { reduce() }.toString() }
            .let { Node.of(it).magnitude() }
    }

    private fun partTwo(input: List<String>): Int {
        return input.maxOf { first ->
            input.filterNot { it == first }.maxOf { second ->
                Node.of("[$first,$second]").apply { reduce() }.magnitude()
            }
        }
    }
}