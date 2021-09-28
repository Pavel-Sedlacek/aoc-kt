package y2015

import utils.Day
import utils.Files
import utils.Helpers.md5
import utils.Helpers.toHex

class Day04 : Day {

    private val input = Files.readFileAsString(2015, 4)

    override fun runAll() {
        println("Day 04: crypto hashing")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(input: String): Int {
        var x = 0
        while (true) {
            x++
            if ("$input$x".md5().toHex().startsWith("00000")) break
        }
        return x
    }

    private fun partTwo(input: String): Int {
        var x = 0
        while (true) {
            x++
            if ("$input$x".md5().toHex().startsWith("000000")) break
        }
        return x
    }
}