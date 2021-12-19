package y2020

import utils.Day
import utils.readers.asLines

// FIXME pls
class Day18 : Day<Long> {

    private val input = file.asLines()

    override fun runAll() {
        var sum1 = 0L
        var sum2 = 0L
        repeat(input.size) {
            sum1 += partOne(input[it])
            sum2 += partTwo(input[it])
        }

        super.run({ sum1 }) { sum2 }
    }

    private fun partOne(s: String): Long {
        var depth = 0
        var start = -1
        val end: Int
        for (i in s.indices) {
            if (s[i] == '(') {
                if (depth == 0) {
                    start = i
                    depth++
                } else {
                    depth++
                }
            } else if (s[i] == ')') {
                depth--
                if (depth == 0) {
                    end = i
                    val repl: Long = partOne(s.substring(start + 1, end).trim())
                    val upIn: String = s.substring(0, start) + repl + s.substring(end + 1)
                    return partOne(upIn)
                }
            }
        }

        return refineEvalOne(s)
    }

    private fun partTwo(input: String): Long {
        var depth = 0
        var start = -1
        val end: Int
        for (i in input.indices) {
            if (input[i] == '(') {
                if (depth == 0) {
                    start = i
                    depth++
                } else {
                    depth++
                }
            } else if (input[i] == ')') {
                depth--
                if (depth == 0) {
                    end = i
                    val repl = partTwo(input.substring(start + 1, end).trim { it <= ' ' })
                    val upIn = input.substring(0, start) + repl + input.substring(end + 1)
                    return partTwo(upIn)
                }
            }
        }
        return refineEvalTwo(input)
    }
}

fun refineEvalOne(input: String): Long {
    val parts = input.split(" ").toTypedArray()
    var rv: Long = parts[0].toLong()
    var i = 1
    while (i < parts.size) {
        when (parts[i]) {
            "+" -> rv += (parts[i + 1]).toLong()
            "*" -> rv *= (parts[i + 1]).toLong()
            else -> throw IllegalStateException(parts[i + 1])
        }
        i += 2
    }
    return rv
}

fun refineEvalTwo(input: String): Long {
    val parts: MutableList<String> = ArrayList(listOf(*input.split(" ").toTypedArray()))
    run {
        var i = 0
        while (i < parts.size) {
            if (parts[i] == "+") {
                parts[i - 1] = (parts[i - 1].toLong() + parts[i + 1].toLong()).toString()
                parts.removeAt(i)
                parts.removeAt(i)
                i--
            }
            i++
        }
    }
    var i = 0
    while (i < parts.size) {
        if (parts[i] == "*") {
            parts[i - 1] = ((parts[i - 1]).toLong() * (parts[i + 1]).toLong()).toString()
            parts.removeAt(i)
            parts.removeAt(i)
            i--
        }
        i++
    }
    return (parts[0]).toLong()
}