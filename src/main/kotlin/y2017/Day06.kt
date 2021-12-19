package y2017

import utils.Day
import utils.collections.mut
import utils.helpers.y2017.reallocate
import utils.readers.asIntsDividedBy

class Day06 : Day<Int> {

    private val input = file.asIntsDividedBy("\\s".toRegex())

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<Int>) = reallocate(input.mut()) { map, _ -> map.size }

    private fun partTwo(input: List<Int>) = reallocate(input.mut()) { map, key -> (map.size) - map.getValue(key) }

}