package y2020

import java.io.File
import java.util.*


fun main() {
    val x = File("src/main/resources/Day18.txt").useLines { it.toList() }.toMutableList()

    var sum1 = 0L
    var sum2 = 0L
    for (i in x.indices) {
        sum1 += evaluate(x[i])
        sum2 += evaluate2(x[i])
    }
    println("Day EIGHTEEN : Math Eval")
    println("Part One $sum1")
    println("Part Two $sum2")
}

class Day18 {
    fun run() {
        val x = File("src/main/resources/Day18.txt").useLines { it.toList() }.toMutableList()

        var sum1 = 0L
        var sum2 = 0L
        for (i in x.indices) {
            sum1 += evaluate(x[i])
            sum2 += evaluate2(x[i])
        }
        println("Day EIGHTEEN : Math Eval")
        println("Part One $sum1")
        println("Part Two $sum2")
    }
}

fun evaluate(s: String): Long {
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
                val repl: Long = evaluate(s.substring(start + 1, end).trim())
                val upIn: String = s.substring(0, start) + repl + s.substring(end + 1)
                return evaluate(upIn)
            }
        }
    }

    return finalEval(s)
}

fun finalEval(input: String): Long {
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

fun evaluate2(input: String): Long {
    var depth = 0
    var start = -1
    var end: Int
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
                val repl = evaluate2(input.substring(start + 1, end).trim { it <= ' ' })
                val upIn = input.substring(0, start) + repl + input.substring(end + 1)
                return evaluate2(upIn)
            }
        }
    }
    return finalEval2(input)
}

fun finalEval2(input: String): Long {
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
