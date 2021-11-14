package y2020

import utils.Day
import utils.common.Patterns.passportExpectedFields
import utils.common.Patterns.passportFieldPatterns
import utils.readers.asLines

class Day04 : Day<Int> {
    private val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(passports: List<String>): Int =
        passports.count { passport -> passportExpectedFields.all { passport.contains(it) } }

    private fun partTwo(passports: List<String>): Int =
        passports.count { passport -> passportFieldPatterns.all { it.containsMatchIn(passport) } }

}