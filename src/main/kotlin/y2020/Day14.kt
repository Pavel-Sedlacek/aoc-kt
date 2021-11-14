package y2020

import utils.Day
import utils.Helpers.floatingValues
import utils.readers.asLines


class Day14 : Day<Long> {

    private val input = file.asLines()

    override fun runAll() = super.run({ partOneFourteen(input) }, { partTwoFourteen(input) })

    private fun partTwoFourteen(f: List<String>): Long {
        val mem = mutableMapOf<Long, Long>()
        var mask = ""
        f.forEach {
            if (it.startsWith("mask")) {
                mask = it.substringAfter("= ")
            } else {
                val values = it.split("[", "] = ")
                val location = values[1].toInt().toString(2).padStart(36, '0')
                val value = values[2].toLong()

                for (loc in floatingValues(mask, location)) {
                    mem[loc.toLong(2)] = value
                }
            }
        }
        return mem.map { it.value }.sum()
    }

    private fun partOneFourteen(f: List<String>): Long {
        val mem = LongArray(100000)
        var mask = 0L
        var muskox = 0L

        f.forEach {
            if (it.startsWith("mask")) {
                val m = it.substringAfter("= ")
                mask = m.replace("X", "0").toLong(2)
                muskox = m.replace("1", "0").replace("X", "1").toLong(2)
            } else {
                val values = it.split("[", "] = ")
                val location = values[1].toInt()
                val value = values[2].toLong()

                mem[location] = (muskox and value) + mask
            }
        }
        return mem.sum()
    }

}
