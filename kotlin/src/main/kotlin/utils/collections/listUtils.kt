package utils.collections

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