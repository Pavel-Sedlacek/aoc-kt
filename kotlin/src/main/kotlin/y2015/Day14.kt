package y2015

import utils.Day
import utils.helpers.y2015.Reindeer
import utils.readers.asLines


class Day14 : Day<Int> {

    private val input = file.asLines().map {
        it.split(" ").let { a ->
            Reindeer(a[3].toInt(), a[6].toInt(), a[13].toInt())
        }
    }

    override fun runAll() {
        repeat(2503) { _ ->
            this.input.forEach { it.move() }
            this.input.maxByOrNull { it.distanceTraveled }!!.score++
        }
        super.run({ partOne(input) }) { partTwo(input) }
    }

    private fun partOne(input: List<Reindeer>): Int = input.maxOf { it.distanceTraveled }

    private fun partTwo(input: List<Reindeer>): Int = input.maxOf { it.score }
}