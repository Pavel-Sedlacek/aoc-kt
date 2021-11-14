package y2015

import utils.Day
import utils.Files

fun main() {
    Day12().runAll()
}

class Day12 : Day {

    private val input = Files.readFileAsString(2015, 12)

    override fun runAll() {
        println("Day 12: json parsing")
        println(partOne(input))
        // TODO partTwo
    }


    fun partOne(input: String) = Regex("-?[0-9]+").findAll(input).map { it.value.toInt() }.sum()

    fun partTwo(input: String) {

    }
}