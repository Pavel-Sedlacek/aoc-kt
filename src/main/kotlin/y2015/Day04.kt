package y2015

import utils.Day
import utils.readers.Reader
import utils.common.md5
import utils.toHex

class Day04 : Day<Int> {

    private val input = file

    override fun runAll() = super.run({partOne(input)}, {partTwo(input)})

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