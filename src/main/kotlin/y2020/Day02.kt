package y2020

import utils.Day
import utils.Files

class Day02: Day {

    private val input = Files.readFileAsLines(2020, 2)

    override fun runAll() {
        println("Day 02 : check if letter occurrences in string matches range")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(x: List<String>): Int =
        x.count { i ->
            val min = i.substringBefore("-").toInt()
            val max = i.substringAfter("-").substringBefore(" ").toInt()
            val letter = i.substringAfter(" ").dropLast(1)
            val password = i.substringAfter(": ")
            (password.count { letter.contains(it) } in min..max)
        }

    private fun partTwo(x: List<String>): Int =
        x.count { i ->
            val min = i.substringBefore("-").toInt()
            val max = i.substringAfter("-").substringBefore(" ").toInt()
            val letter = i.substringAfter(" ").dropLast(1)
            val password = i.substringAfter(": ")
            (password[min - 1] == letter[0] && password[max - 1] != letter[0]) || (password[min - 1] != letter[0] && password[max - 1] == letter[0])
        }



}