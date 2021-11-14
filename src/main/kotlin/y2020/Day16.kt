package y2020

import utils.Day
import utils.readers.asLinesSplitBy


// TODO
class Day16 : Day<Int> {

    private val input = file.asLinesSplitBy("\r\n\r\n")
    private var validTicks = mutableListOf<String>()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partTwo(toMutableList: List<String>): Int {
        return 0
    }

    private fun partOne(toMutableList: List<String>): Int {
        return 0
    }


}
