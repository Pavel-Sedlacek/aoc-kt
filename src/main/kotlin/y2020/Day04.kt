package y2020

import utils.Day
import utils.Helpers
import utils.Utils

class Day04: Day {
    private val input = Utils.readFileAsLines(2020, 4)

    override fun runAll() {
        println("Day 04 : passport validation")
        println(this.partOne(input))
        println(this.partTwo(input))
    }

    private fun partOne(passports: List<String>): Int =
        passports.count { passport -> Helpers.passportExpectedFields.all { passport.contains(it)} }

    private fun partTwo(passports: List<String>): Int =
        passports.count { passport -> Helpers.passportFieldPatterns.all { it.containsMatchIn(passport) } }

}