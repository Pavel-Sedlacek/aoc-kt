package utils.helpers.y2015

import utils.collections.Coordinates
import kotlin.reflect.KFunction1

fun switchingLightSolver(
    input: List<List<String>>,
    off: KFunction1<Light, Unit>,
    on: KFunction1<Light, Unit>,
    toggle: KFunction1<Light, Unit>,
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
}.let { lights -> lights.sumOf { it.state } }

private fun initLights(): MutableList<Light> {
    val z = mutableListOf<Light>()
    for (x in 0 until 1000) {
        for (y in 0 until 1000) {
            z.add(Light(x, y, 0))
        }
    }
    return z
}

data class Light(val x: Int, val y: Int, var state: Int) {
    fun isInRange(xFrom: Int, xTo: Int, yFrom: Int, yTo: Int): Boolean = x in (xFrom)..xTo && y in (yFrom)..yTo

    fun off1() {
        state = 0
    }

    fun on1() {
        state = 1
    }

    fun toggle1() {
        state = if (state == 1) 0 else 1
    }

    fun off2() {
        if (state > 0) state--
    }

    fun on2() {
        state++
    }

    fun toggle2() {
        state += 2
    }
}