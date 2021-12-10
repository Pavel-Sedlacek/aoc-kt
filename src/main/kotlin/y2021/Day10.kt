package y2021

import utils.Day
import utils.common.middle
import utils.readers.asLines
import java.util.*

class Day10 : Day<Number> {

    val input = file.asLines()

    override fun runAll() = super.run(
        { partOne(input, mapOf(")" to 3, "]" to 57, "}" to 1197, ">" to 25137)) },
        { partTwo(input, mapOf("(" to 1, "[" to 2, "{" to 3, "<" to 4)) }
    )

    fun partOne(lines: List<String>, scoreMap: Map<String, Int>): Long = lines.fold(0) { score, line ->
        checkInvalid(line).let {
            if (it != null) score + (scoreMap[it] ?: 0)
            else score
        }
    }

    fun partTwo(lines: List<String>, scoreMap: Map<String, Long>): Long =
        lines.fold(mutableListOf()) { acc: MutableList<Long>, s: String ->
            val rem = checkIncomplete(s).reversed()
            acc.also {
                if (rem.isNotEmpty()) {
                    acc.add(rem.fold(0L) { c, i -> (5 * c) + (scoreMap[i] ?: 0) })
                }
            }
        }.sorted().middle() ?: 0
}

fun checkInvalid(line: String): String? {
    val stack = Stack<String>()

    line.forEach {
        if (listOf("{", "[", "<", "(").contains(it.toString())) {
            stack.push(it.toString())
        } else if (listOf("}", "]", ">", ")").contains(it.toString())) {
            if (isNotMatch(stack.pop(), it.toString())) return it.toString()
        }
    }

    if (stack.peek() != "") return stack.peek()
    return null
}

fun checkIncomplete(line: String): List<String> {
    val stack = Stack<String>()
    line.forEach {
        if (listOf("{", "[", "<", "(").contains(it.toString())) {
            stack.push(it.toString())
        } else if (listOf("}", "]", ">", ")").contains(it.toString())) {
            if (isNotMatch(stack.pop(), it.toString())) {
                return listOf()
            }
        }
    }
    if (stack.peek() != "") return stack.toList()
    return listOf()
}

fun isNotMatch(openingBrace: String, closingBrace: String): Boolean =
    closingBrace != (mapOf("(" to ")", "[" to "]", "{" to "}", "<" to ">")[openingBrace])