package utils.helpers.y2021

fun String.polymerInsertionProcess(steps: Int, rules: Map<String, String>): Long {
    val polymer = this

    val initial = polymer.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }

    val pairsCount = (0 until steps).fold(initial) { current, _ ->
        buildMap {
            current.forEach { (pair, count) ->
                val first = pair[0] + rules.getValue(pair)
                val second = rules.getValue(pair) + pair[1]
                put(first, getOrDefault(first, 0) + count)
                put(second, getOrDefault(second, 0) + count)
            }
        }
    }

    val charsCount = buildMap<Char, Long> {
        put(polymer[0], 1)
        pairsCount.forEach { (pair, count) ->
            put(pair[1], getOrDefault(pair[1], 0) + count)
        }
    }

    return charsCount.maxOf { it.value } - charsCount.minOf { it.value }
}