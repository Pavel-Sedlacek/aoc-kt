package y2015

import utils.Day
import utils.helpers.y2015.Light
import utils.helpers.y2015.switchingLightSolver
import utils.readers.asLines

class Day06 : Day<Int> {

    private val input = file.asLines().map { it.split(" ") }

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<List<String>>): Int =
        switchingLightSolver(input, Light::off1, Light::on1, Light::toggle1)

    private fun partTwo(input: List<List<String>>): Int =
        switchingLightSolver(input, Light::off2, Light::on2, Light::toggle2)
}

