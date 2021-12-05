package y2021

import utils.Day
import utils.Line
import utils.collections.Coordinates
import utils.overlappingVentsSolver
import utils.readers.asLines

class Day05 : Day<Int> {

    val input = file.asLines().map { lines ->
        val x = lines.split(" -> ")
        x[0].split(",").let { f ->
            x[1].split(",").let { t ->
                Line(Coordinates(f[0].toInt(), f[1].toInt()), Coordinates(t[0].toInt(), t[1].toInt()))
            }
        }
    }

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<Line>): Int = overlappingVentsSolver(input.filter { !it.isDiagonal() })

    private fun partTwo(input: List<Line>): Int = overlappingVentsSolver(input)
}


