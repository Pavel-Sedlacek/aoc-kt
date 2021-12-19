package y2021

import utils.Day
import utils.helpers.y2021.Line
import utils.readers.asLines

class Day05 : Day<Int> {

    private val lines = file.asLines()
        .map { it.split(" -> ") }
        .map { (start, end) -> Pair(start.split(","), end.split(",")) }
        .map { (start, end) ->
            Line(
                start.first().toInt(),
                start.last().toInt(),
                end.first().toInt(),
                end.last().toInt()
            )
        }

    override fun runAll() = super.run({ partOne(lines) }) { partTwo(lines) }

    fun partOne(lines: List<Line>): Int {
        return lines.filter { line -> line.x1 == line.x2 || line.y1 == line.y2 }
            .flatMap { it.points() }
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }
    }

    fun partTwo(lines: List<Line>): Int {
        return lines.flatMap { it.points() }
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1 }
    }
}
