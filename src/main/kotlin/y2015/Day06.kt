package y2015

import utils.Day
import utils.Files
import utils.Helpers.Coordinates
import utils.Helpers.Light
import utils.Helpers.initLights
import kotlin.reflect.KFunction1

class Day06 : Day {

    private val input = Files.readFileAsLines(2015, 6).map { it.split(" ") }


    override fun runAll() {
        println("Day 06: light switching")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(input: List<List<String>>): Int =
        solver(input, Light::off1, Light::on1, Light::toggle1)

    private fun partTwo(input: List<List<String>>): Int =
        solver(input, Light::off2, Light::on2, Light::toggle2)

    private fun solver(
        input: List<List<String>>,
        off: KFunction1<Light, Unit>,
        on: KFunction1<Light, Unit>,
        toggle: KFunction1<Light, Unit>
    ): Int = initLights().also { lights ->
        input.onEach { line ->
            val from = Coordinates(0, 0)
            val to = Coordinates(0, 0)
            val fn: KFunction1<Light, Unit> = when {
                line[0] == "toggle" -> toggle
                else -> if (line[1] == "on") on else off
            }

            (if (fn == toggle) 1 else 2).also {
                line[it].split(",").also { ints -> from.x = ints[0].toInt(); from.y = ints[1].toInt() }
                line[it + 2].split(",").also { ints -> to.x = ints[0].toInt(); to.y = ints[1].toInt() }
            }
            lights.forEach { if (it.isInRange(from.x, to.x, from.y, to.y)) fn.invoke(it) }
        }
    }.let { lights -> lights.sumBy { it.state } }
}

