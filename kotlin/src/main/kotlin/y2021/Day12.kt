package y2021

import utils.Day
import utils.helpers.y2021.countPaths
import utils.readers.asLines

class Day12 : Day<Int> {

    private val connections = file.asLines()
        .map { it.split("-") }
        .flatMap { (begin, end) -> listOf(begin to end, end to begin) }
        .filterNot { (_, end) -> end == "start" }
        .groupBy({ it.first }, { it.second })

    override fun runAll() {
        super.run({ partOne(connections) }) { partTwo(connections) }
    }

    fun partOne(connections: Map<String, List<String>>): Int =
        connections.countPaths { cave, currentPath ->
            cave in currentPath
        }

    fun partTwo(connections: Map<String, List<String>>): Int =
        connections.countPaths { cave, currentPath ->
            val counts = currentPath.filter { it.first().isLowerCase() }.groupingBy { it }.eachCount()
            cave in counts.keys && counts.any { it.value > 1 }
        }

}