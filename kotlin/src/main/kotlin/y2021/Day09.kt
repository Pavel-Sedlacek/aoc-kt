package y2021

import utils.Day
import utils.helpers.y2021.basin
import utils.helpers.y2021.lowPoints
import utils.readers.asLines

class Day09 : Day<Int> {

    private val input = file.asLines().map { row -> row.map { it.digitToInt() } }

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    fun partOne(input: List<List<Int>>): Int = input.lowPoints().sumOf { (x, y) -> input[x][y] + 1 }

    fun partTwo(input: List<List<Int>>): Int = input
        .lowPoints()
        .map { (rowIdx, colIdx) -> input.basin(rowIdx, colIdx).toSet().size }
        .sortedDescending()
        .take(3)
        .reduce { acc, i -> acc * i }
}