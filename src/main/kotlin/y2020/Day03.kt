package y2020

import utils.Day
import utils.Helpers.countDiagonalTrees
import utils.Utils

class Day03 : Day {
    private val input = Utils.readFileAsMutableList(2020, 3)

    override fun runAll() {
        println("Day 03 : count trees encountered while traversing a forest")
        println(this.partOne(input))
        println(this.partTwo(input))
    }

    private fun partOne(x: MutableList<String>): Long {
        return countDiagonalTrees(3, 1, x)
    }

    private fun partTwo(model: MutableList<String>): Long {
        return countDiagonalTrees(1, 1, model) *
                countDiagonalTrees(3, 1, model) *
                countDiagonalTrees(5, 1, model) *
                countDiagonalTrees(7, 1, model) *
                countDiagonalTrees(1, 2, model)
    }

}