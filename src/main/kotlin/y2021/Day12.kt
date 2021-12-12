package y2021

import utils.Day
import utils.readers.asLines

class Day12 : Day<Int> {

    val input = mutableMapOf<String, Set<String>>().withDefault { setOf() }.apply {
        file.trim().asLines().map { it.trim().split("-") }.forEach { (a, b) ->
            put(a, getValue(a) + b)
            put(b, getValue(b) + a)
        }
    }

    override fun runAll() {
        super.run({ partOne(input) }, { partTwo(input) })
    }

    private fun partOne(input: MutableMap<String, Set<String>>): Int = Route(input, false).allPaths.size

    private fun partTwo(input: MutableMap<String, Set<String>>): Int = Route(input, true).allPaths.size
}

class Route(private val connections: MutableMap<String, Set<String>>, private val canVisitSmallTwice: Boolean) {
    val allPaths = search("start", listOf())

    private fun search(curr: String, path: List<String>): List<List<String>> {
        val updatedPath = path + curr
        if (curr == "end") return listOf(updatedPath)
        return connections.getValue(curr).filterNot { next ->
            next == "start" ||
                    next.isSmallCave() && next in updatedPath &&
                    if (canVisitSmallTwice) {
                        updatedPath.filter { it.isSmallCave() }.groupingBy { it }.eachCount().values.any { it >= 2 }
                    } else true
        }.flatMap { search(it, updatedPath) }
    }
}

fun String.isSmallCave() = this.all { it.isLowerCase() }