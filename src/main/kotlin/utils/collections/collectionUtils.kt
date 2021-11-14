package utils.collections

import utils.common.toBinary

fun <T> Set<T>.allPermutations(): Set<List<T>> {
    if (this.isEmpty()) return emptySet()

    fun <T> List<T>.innerAllPermutations(): Set<List<T>> {
        if (this.isEmpty()) return setOf(emptyList())

        val result: MutableSet<List<T>> = mutableSetOf()
        for (i in this.indices) {
            (this - this[i]).innerAllPermutations().forEach { item ->
                result.add(item + this[i])
            }
        }
        return result
    }

    return this.toList().innerAllPermutations()
}

fun List<String>.toBinaryList(): List<String> {
    return this.map { it.toBinary(listOf("F", "L"), listOf("B", "R")) }
}

fun List<Int>.product(): Int {
    var x = 1
    for (i in this) x *= i
    return x
}

fun <Z> List<Z>.mut(): MutableList<Z> {
    return this.toMutableList()
}


/*
    mutate immutable list without explicit converting
 */
fun <Z> List<Z>.modify(modify: (it: MutableList<Z>) -> Unit): List<Z> {
    val x = this.mut()
    modify(x)
    return x
}

fun List<String>.asCharLines(): List<List<Char>> {
    return this.map { it.toCharArray().toList() }
}