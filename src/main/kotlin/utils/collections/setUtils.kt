package utils.collections

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
