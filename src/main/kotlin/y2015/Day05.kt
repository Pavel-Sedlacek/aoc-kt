package y2015

import utils.Day
import utils.readers.asLines

class Day05 : Day<Int> {

    private val input = file.asLines()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

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