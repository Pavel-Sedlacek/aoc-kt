package y2020

import java.io.File

var values = File("src/main/resources/Day19.txt").readText().split("\n\n")
var rules1 = values[0].split("\n").associate { it.split(":").let { (id, rule) -> id.toInt() to rule.trim() } }
var rules2 = rules1 + listOf(8 to "42 | 42 8", 11 to "42 31 | 42 11 31").toMap()
var text = values[1].split("\n")

fun main() {
    println("Day NINETEEN : rules validation")
    println("Part One ${text.count{isMatch(rules1, it, listOf(0))}}")
    println("Part Two ${text.count{isMatch(rules2, it, listOf(0))}}")
}

class Day19 {
    fun run() {
        println("Day NINETEEN : rules validation")
        println("Part One ${text.count{isMatch(rules1, it, listOf(0))}}")
        println("Part Two ${text.count{isMatch(rules2, it, listOf(0))}}")
    }
}

fun isMatch(ruleMap: Map<Int, String>, line: CharSequence, rules: List<Int>): Boolean {
    if (line.isEmpty()) {
        return rules.isEmpty()
    } else if (rules.isEmpty()) {
        return false
    }
    return ruleMap.getValue(rules[0]).let { rule ->
        if (rule[1] in 'a'..'z') {
            if (line.startsWith(rule[1])) {
                isMatch(ruleMap, line.drop(1), rules.drop(1))
            } else false
        } else rule.split(" | ").any {
            isMatch(ruleMap, line, it.split(" ").map(String::toInt) + rules.drop(1))
        }
    }
}