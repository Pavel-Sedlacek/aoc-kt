package utils.helpers.y2021

import utils.common.coordinates.Coordinates2D
import kotlin.math.abs

fun foldPaper(folds: List<Pair<String, Int>>, points: Set<Coordinates2D>): Set<Coordinates2D> {
    return folds.fold(points) { points, instruction ->
        val (axis, position) = instruction
        when (axis) {
            "y" -> points
                .filter { it.y > position }
                .map { Coordinates2D(it.x, 2 * position - it.y) }
                .toSet() + points.filter { it.y < position }
            else -> points
                .filter { it.x > position }
                .map { Coordinates2D(2 * position - it.x, it.y) }
                .toSet() + points.filter { it.x < position }
        }
    }
}

fun Set<Coordinates2D>.asString(): String {
    val xOffset = abs(this.minOf { it.x })
    val yOffset = abs(this.minOf { it.y })

    return List(this.maxOf { it.y } + yOffset + 1) { MutableList(this.maxOf { it.x } + xOffset + 1) { ' ' } }.also { card ->
        this.forEach { card[it.y + yOffset][it.x + xOffset] = '#' }
    }.let { list ->
        StringBuilder().let { builder ->
            builder.appendLine()
            list.forEach { builder.appendLine(it) }
            builder.toString()
        }
    }
}