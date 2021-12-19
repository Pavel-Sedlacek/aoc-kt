package y2015

import utils.Day
import utils.helpers.y2015.Reindeer
import utils.readers.asLines


class Day14 : Day<Int> {

    private val input = file.asLines().map {
        it.split(" ").let { a ->
            a[0] to Reindeer(a[3].toInt(), a[6].toInt(), a[13].toInt())
        }
    }

    override fun runAll() {
        repeat(2503) { _ ->
            this.input.forEach { it.second.move() }
            this.input.maxByOrNull { it.second.distanceTraveled }!!.second.score++
        }
        super.run({ partOne(input) }) { partTwo(input) }
    }

    private fun partOne(input: List<Pair<String, Reindeer>>): Int = input.maxOf { it.second.distanceTraveled }

    private fun partTwo(input: List<Pair<String, Reindeer>>): Int = input.maxOf { it.second.score }
}