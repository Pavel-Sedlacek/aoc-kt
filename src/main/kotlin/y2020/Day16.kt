package y2020

import utils.Day
import utils.Files



class Day16: Day {

    private val input = Files.readFileAsLinesSplitBy(2020, 16, "\r\n\r\n")
    private var validTicks = mutableListOf<String>()

    override fun runAll() {
        println("Day 16 : Tickets")
        println(partOne(input.toMutableList()))
        println(partTwo(input.toMutableList()))
    }

    private fun partTwo(toMutableList: MutableList<String>): Any? {
        return null
    }

    private fun partOne(toMutableList: MutableList<String>): Any? {
        return null
    }


}
