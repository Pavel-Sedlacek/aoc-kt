package y2021

import utils.Day
import utils.common.coordinates.Coordinates2D
import utils.helpers.y2021.asString
import utils.helpers.y2021.foldPaper
import utils.readers.asLines

class Day13 : Day<String> {

    private val input = file.asLines()

    private val points = input
        .takeWhile { it.isNotEmpty() }
        .map { Coordinates2D(it.substringBefore(",").toInt(), it.substringAfter(",").toInt()) }
        .toSet()

    private val folds = input
        .takeLastWhile { it.isNotEmpty() }
        .map { it.substringAfterLast(" ") }
        .map { it.substringBefore("=") to it.substringAfter("=").toInt() }

    override fun runAll() =
        super.run({ partOne(folds, points) }) { partTwo(folds, points) }

    fun partOne(folds: List<Pair<String, Int>>, points: Set<Coordinates2D>): String =
        foldPaper(folds.take(1), points).count().toString()

    fun partTwo(folds: List<Pair<String, Int>>, points: Set<Coordinates2D>): String =
        foldPaper(folds, points).asString()
}
