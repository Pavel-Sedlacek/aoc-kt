package utils.helpers.y2020

class Bags(lines: List<String>) {

    val bags = lines.associate { line ->
        val (key, items) = linePattern.matchEntire(line)!!.destructured
        key to itemPattern.findAll(items).map { match ->
            val (count, item) = match.destructured
            count.toInt() to item
        }.toList()
    }

    companion object {
        const val GOAL = "shiny gold"
        val linePattern = """(\w+ \w+) bags contain (.*)""".toRegex()
        val itemPattern = """(\d+) (\w+ \w+) bags?""".toRegex()
    }
}