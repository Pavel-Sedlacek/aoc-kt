package y2020

import utils.Day
import utils.Helpers.countDiagonalTrees
import utils.collections.mut
import utils.readers.asLines

class Day03 : Day<Long> {
    private val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(x: List<String>): Long {
        return countDiagonalTrees(3, 1, x.mut())
    }

    private fun partTwo(x: List<String>): Long {
        val model = x.mut()
        return countDiagonalTrees(1, 1, model) *
                countDiagonalTrees(3, 1, model) *
                countDiagonalTrees(5, 1, model) *
                countDiagonalTrees(7, 1, model) *
                countDiagonalTrees(1, 2, model)
    }

}