package y2020

import utils.Day
import utils.Helpers.isMatch
import utils.readers.asLines
import utils.readers.asLinesSplitBy

class Day19 : Day<Int> {

    private val input = file.asLinesSplitBy("\n\n")

    private val ruleP1 =
        input[0].split("\n").associate { it.split(":").let { (id, rule) -> id.toInt() to rule.trim() } }
    private val ruleP2 = ruleP1 + mapOf(8 to "42 | 42 8", 11 to "42 31 | 42 11 31")

    override fun runAll() = super.run({ solve(input, ruleP1) }, { solve(input, ruleP2) })

    private fun solve(input: List<String>, rule: Map<Int, String>): Int {
        return input[1].asLines().count { isMatch(rule, it) }
    }
}

fun main() {
    Day19().runAll()
}

