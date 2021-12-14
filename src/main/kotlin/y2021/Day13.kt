package y2021

import utils.Day
import utils.collections.Coordinates
import utils.collections.stringify
import utils.readers.asLines
import kotlin.math.abs

class Day13 : Day<String> {

    private val input = file.split("\n\n")

    private val points: Set<Coordinates> =
        input[0].asLines().asSequence().map { it.split(",").let { Coordinates(it[0].toInt(), it[1].toInt()) } }.toSet()

    private val folds: List<Instruction> = input[1].asLines().asSequence()
        .map {
            it.substringAfterLast(" ").let {
                Instruction(if (it.substringBefore("=") == "x") Axis.X else Axis.Y, it.substringAfter("=").toInt())
            }
        }.toList()

    override fun runAll() =
        super.run({ partOne(points, folds.take(1)) }, { partTwo(points, folds.subList(1, folds.size)) })

    private fun partOne(input: Set<Coordinates>, instruction: List<Instruction>): String =
        foldPaper(input, instruction).count().toString()

    private fun partTwo(input: Set<Coordinates>, instructions: List<Instruction>): String =
        "\n${foldPaper(input, instructions).stringify()}"
}

private fun foldPaper(points: Set<Coordinates>, instructions: List<Instruction>): Set<Coordinates> {
    return instructions.fold(points) { points, instruction ->
        val (axis, position) = instruction
        when (axis) {
            Axis.Y -> points.filter { it.y > position }.map { Coordinates(it.x, 2 * position - it.y) }
                .toSet() + points.filter { it.y < position }
            else -> points.filter { it.x > position }.map { Coordinates(2 * position - it.x, it.y) }
                .toSet() + points.filter { it.x < position }
        }
    }
}

data class Instruction(val axis: Axis, val foldLine: Int)

enum class Axis {
    X, Y
}