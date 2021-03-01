package y2020

import utils.Day
import utils.Files
import utils.Helpers.floatingValues


class Day14 : Day {

    private val input = Files.readFileAsMutableList(2020, 14)

    override fun runAll() {
        println("Day FOURTEEN : memory masking")
        println("Part One ${partOneFourteen(input)}")
        println("Part Two ${partTwoFourteen(input)}")
    }

    private fun partTwoFourteen(f: MutableList<String>): Long {
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

    private fun partOneFourteen(f: MutableList<String>): Long {
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