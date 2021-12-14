package utils.collections

import kotlin.math.abs

data class Coordinates(var x: Int, var y: Int)

fun Coordinates.moveTowards(other: Coordinates): Coordinates {
    val x = when {
        this.x == other.x -> this.x
        this.x > other.x -> this.x - 1
        else -> this.x + 1
    }
    val y = when {
        this.y == other.y -> this.y
        this.y > other.y -> this.y - 1
        else -> this.y + 1
    }
    return Coordinates(x, y)
}

fun Coordinates.surroundingPoints(): List<Coordinates> {
    return setOf(
        Coordinates(this.x + 1, this.y),
        Coordinates(this.x - 1, this.y),
        Coordinates(this.x, this.y + 1),
        Coordinates(this.x, this.y - 1),
        //diagonals
        Coordinates(this.x + 1, this.y + 1),
        Coordinates(this.x + 1, this.y - 1),
        Coordinates(this.x - 1, this.y + 1),
        Coordinates(this.x - 1, this.y - 1)
    ).toList()
}

fun Set<Coordinates>.stringify(): String {

    val xOffset = abs(this.minOf { it.x })
    val yOffset = abs(this.minOf { it.y })

    return List(this.maxOf { it.y } + yOffset + 1) { MutableList(this.maxOf { it.x } + xOffset + 1) { ' ' } }.also { card ->
        this.forEach { card[it.y + yOffset][it.x + xOffset] = '#' }
    }.let { list ->
        StringBuilder().let { builder ->
            list.forEach { builder.appendLine(it) }
            builder.toString()
        }
    }
}
