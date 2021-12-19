package utils.helpers.y2021

import kotlin.math.abs

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    fun points() = horizontalRange().zip(verticalRange())

    private fun horizontalRange() = when {
        x2 > x1 -> (x1..x2)
        x1 > x2 -> (x1 downTo x2)
        else -> MutableList(abs(y2 - y1) + 1) { x1 }
    }.toList()

    private fun verticalRange() = when {
        y2 > y1 -> (y1..y2)
        y1 > y2 -> (y1 downTo y2)
        else -> MutableList(abs(x2 - x1) + 1) { y1 }
    }.toList()
}