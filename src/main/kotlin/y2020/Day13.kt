package y2020

import utils.Day
import utils.readers.Reader
import utils.lcm
import utils.readers.asLines
import kotlin.math.ceil

class Day13: Day<Long> {

    private val input = file.asLines()

    override fun runAll() = super.run({ partOne(input)}, { partTwo(input[1].split(","))})

    private fun partTwo(s: List<String>): Long {
        val rem = mutableListOf<Int>();
        val bus = mutableListOf<Int>()
        for (i in s.indices) {
            if (s[i] != "x") {
                rem.add(i)
                bus.add(s[i].toInt())
            }
        }
        var step = bus[0].toLong()
        var nextBus = 1;
        var timeStamp = 0L
        do {
            timeStamp += step
            if ((timeStamp + rem[nextBus]) % bus[nextBus] == 0L)
                step = lcm(step, bus[nextBus++].toLong())
        } while (nextBus < bus.size)
        return timeStamp
    }

    private fun partOne(input: List<String>): Long {
        val time = input[0].toInt()
        val jes = input[1].split(",").toMutableList()
        jes.removeAll { it == "x" }
        val buses = jes.toMutableList()

        for (i in 0 until buses.size) {
            buses[i] = ((ceil(((time / (buses[i].toInt())).toDouble())) + 1) * buses[i].toInt()).toString()
        }
        return ((buses.minOrNull()!!.substringBefore(".").toLong() - time.toLong()) * jes[2].toLong())
    }
}