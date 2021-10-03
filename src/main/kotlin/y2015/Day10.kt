package y2015

import utils.Day
import utils.Files
import java.util.regex.Pattern

fun main() {
    Day10().runAll()
}

class Day10 : Day {

    private val input = Files.readFileAsString(2015, 10)
    private val p: Pattern = Pattern.compile("(\\d)\\1*")

    override fun runAll() {
        println("Day 10: number of numbers")
        partOne(input).also {
            println(it.length)
            println(partTwo(it).length)
        }
    }

    private fun partOne(str: String): String {
        var input = str
        for (i in 0 until 40) input = input.lookAndSay(p)
        return input
    }

    private fun partTwo(str: String): String {
        var input = str
        for (i in 0 until 10) input = input.lookAndSay(p)
        return input
    }
}

fun String.lookAndSay(p: Pattern): String {
    var input = this
    p.matcher(input).also {
        input = StringBuilder().also { builder ->
            while (it.find()) {
                builder.append(it.group().length)
                builder.append(it.group(1))
            }
        }.toString()
    }
    return input
}