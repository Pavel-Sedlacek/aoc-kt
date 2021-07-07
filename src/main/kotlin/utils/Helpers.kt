package utils

import java.util.ArrayList

object Helpers {
    fun countDiagonalTrees(x: Int, y: Int, model: MutableList<String>): Long {
        var curX = 0
        var curY = 0
        var curSum: Long = 0

        while (true) {
            curX += x
            curY += y

            if (curY >= model.size) break

            while (curX >= model[curY].length) model[curY] += model[curY]

            if (model[curY][curX] == '#') {
                curSum++
            }
        }
        return curSum
    }

    fun listToBinaryList(StringList: MutableList<String>): MutableList<String> {
        var binList = StringList
        for (i in 0 until binList.size) {
            binList[i] = binList[i].replace("F", "0").replace("B", "1").replace("L", "0").replace("R", "1")
        }
        return binList
    }

    val passportExpectedFields = listOf("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")

    val passportFieldPatterns = listOf(
        """\bbyr:(19[2-9][0-9]|200[0-2])\b""",
        """\biyr:(201[0-9]|2020)\b""",
        """\beyr:(202[0-9]|2030)\b""",
        """\bhgt:((1([5-8][0-9]|9[0-3])cm)|((59|6[0-9]|7[0-6])in))\b""",
        """\bhcl:#[0-9a-f]{6}\b""",
        """\becl:(amb|blu|brn|gry|grn|hzl|oth)\b""",
        """\bpid:[0-9]{9}\b"""
    ).map { it.toRegex() }

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

    fun recursiveGo(x: List<String>) : Int {
        val visitedLines = (0..x.size).toMutableSet()
        var accumulator = 0
        var i = 0
        while (i < x.size) {
            if (!visitedLines.contains(i)) return -1
            else visitedLines.remove(i)
            val currentLine = x[i].split(" ")
            when (currentLine[0]) {
                "acc" -> {
                    accumulator += currentLine[1].toInt()
                }
                "jmp" -> {
                    i += currentLine[1].toInt()
                    continue
                }
            }
            i++
        }
        return accumulator
    }

    fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
    fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

    fun floatingValues(mask: String, address: String): MutableList<String> {
        val addresses = mutableListOf<String>()
        if (mask.isNotEmpty()) {
            val maskR = mask[mask.lastIndex]
            val addressR = address[address.lastIndex]

            val recurse = floatingValues(mask.substring(0, mask.lastIndex), address.substring(0, address.lastIndex))

            when (maskR) {
                '0' -> {
                    addresses.addAll(recurse.map { "$it$addressR" })
                }
                '1' -> {
                    addresses.addAll(recurse.map { "$it$maskR" })
                }
                'X' -> {
                    addresses.addAll(recurse.map { "${it}0" })
                    addresses.addAll(recurse.map { "${it}1" })
                }
            }
        } else {
            addresses.add("")
        }
        return addresses
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

    data class Quad<X, Y, Z, W>(val x: X, val y: Y, val z: Z, val w: W)

}