package utils.helpers.y2020

fun isMatch(ruleMap: Map<Int, String>, line: CharSequence, rules: List<Int> = listOf(0)): Boolean {
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