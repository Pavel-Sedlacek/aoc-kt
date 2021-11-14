package y2020

import utils.Day
import utils.collections.toBinaryList
import utils.readers.asLines

class Day05 : Day<Int> {
    private val input = file.asLines().toBinaryList()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(x: List<String>): Int {
        return x.fold(0) { max, element ->
            val iNumber = Integer.parseInt(element.substring(0, 7), 2) * 8 + Integer.parseInt(element.substring(7), 2)
            if (iNumber > max) iNumber
            else max
        }
    }

    private fun partTwo(x: List<String>): Int {
        val z = (55..890).toMutableList()
        x.forEach { i ->
            z.remove(
                Integer.parseInt((i.substring(0, 7)), 2) * 8 + Integer.parseInt(i.substring(7, i.length), 2)
            )
        }
        return z[0]
    }
}