package y2020

import utils.Day
import utils.Helpers.refineEvalOne
import utils.Helpers.refineEvalTwo
import utils.readers.asLines

class Day18 : Day<Long> {

    private val input = file.asLines()

    override fun runAll() {
        var sum1 = 0L
        var sum2 = 0L
        repeat(input.size) {
            sum1 += partOne(input[it])
            sum2 += partTwo(input[it])
        }

        super.run({ sum1 }, { sum2 })
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
