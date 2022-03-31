package utils.collections

import utils.common.coordinates.Coordinates2D
import utils.common.toBinary

fun List<String>.toBinaryList(): List<String> {
    return this.map { it.toBinary(listOf("F", "L"), listOf("B", "R")) }
}

fun <Z> List<Z>.mut(): MutableList<Z> {
    return this.toMutableList()
}

fun <Z> List<Z>.modify(modify: (it: MutableList<Z>) -> Unit): List<Z> {
    val x = this.mut()
    modify(x)
    return x
}

fun List<String>.asCharLines(): List<List<Char>> {
    return this.map { it.toCharArray().toList() }
}

fun <T> List<List<T>>.copy(mut: Boolean = false): List<List<T>> =
    this.map { if (mut) it.toMutableList() else it.toList() }

fun <T> List<List<T>>.applyCoordinates(): Map<Coordinates2D, T> {
    val m = mutableMapOf<Coordinates2D, T>()
    for (y in this.indices) for (x in this[y].indices) m[Coordinates2D(x, y)] = this[y][x]
    return m
}