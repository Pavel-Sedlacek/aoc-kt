package y2020

import utils.Day
import utils.Helpers
import utils.Files

class Day05 : Day {
    private val input = Helpers.listToBinaryList(Files.readFileAsMutableList(2020, 5))

    override fun runAll() {
        println("Day 05 : plane seats binary location")

        println(this.partOne(input))
        println(this.partTwo(input))
    }

    private fun partOne(x: MutableList<String>): Int =
        x.fold(0) { max, element ->
            val iNumber = Integer.parseInt(element.substring(0, 7), 2) * 8 + Integer.parseInt(element.substring(7), 2)
            if (iNumber > max) iNumber
            else max
        }

    private fun partTwo(x: MutableList<String>): Int {
        val z = (55..890).toMutableList()
        x.forEach { i ->
            z.remove(
                Integer.parseInt((i.substring(0, 7)), 2) * 8 + Integer.parseInt(i.substring(7, i.length), 2)
            )
        }
        return z[0]
    }
}