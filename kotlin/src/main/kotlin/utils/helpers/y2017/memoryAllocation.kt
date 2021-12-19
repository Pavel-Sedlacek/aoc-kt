package utils.helpers.y2017

tailrec fun reallocate(
    memory: MutableList<Int>,
    seen: Map<String, Int> = mutableMapOf(),
    answer: (Map<String, Int>, String) -> Int,
): Int {
    val hash = memory.joinToString()
    return if (hash in seen) answer(seen, hash)
    else {
        val (index, amount) = memory.withIndex().maxByOrNull { it.value }!!
        memory[index] = 0
        repeat(amount) { i ->
            val idx = (index + i + 1) % memory.size
            memory[idx] += 1
        }
        reallocate(memory, seen + (hash to seen.size), answer)
    }
}