package y2015

import utils.Day
import utils.Files

class Day05 : Day {

    private val input = Files.readFileAsLines(2015, 5)

    override fun runAll() {
        println("Day 05: ")
        println(partOne(input))
        println(partTwo(input))
    }

    private fun partOne(input: List<String>) = input.fold(0) { acc, s ->
        if (s.matches("^.*[aeiou].*[aeiou].*[aeiou].*\$".toRegex()) &&
            s.matches("^.*(.)\\1+.*\$".toRegex()) &&
            !s.contains("(ab)|(cd)|(pq)|(xy)".toRegex())
        ) acc + 1 else acc
    }

    private fun partTwo(input: List<String>) = input.fold(0) { acc, s ->
        if (s.matches("^.*([a-z][a-z]).*\\1+.*\$".toRegex()) &&
            s.matches("^.*([a-z])[a-z]\\1+.*\$".toRegex())
        ) acc + 1 else acc
    }
}