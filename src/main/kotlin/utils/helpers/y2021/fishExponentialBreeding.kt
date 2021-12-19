package utils.helpers.y2021

fun simulate(numbers: List<Int>, endDay: Int, cache: MutableMap<Int, Long>): Long =
    numbers.sumOf { n -> 1 + (n + 1..endDay step 7).sumOf { i -> 1 + created(i, endDay, cache) } }

fun created(startDay: Int, endDay: Int, cache: MutableMap<Int, Long>): Long =
    ((startDay + 8 + 1)..endDay step 7).sumOf { i -> cache.getOrPut(i) { 1 + created(i, endDay, cache) } }