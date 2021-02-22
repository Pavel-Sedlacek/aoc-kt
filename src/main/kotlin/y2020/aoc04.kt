package y2020

import java.io.File

fun main() {
    val input = Passport(File("src/main/resources/Day04.txt").readText(Charsets.UTF_8))
    println("Day FOUR : passport validation")
    println("Part One ${input.partFourOne()}")
    println("Part Two ${input.partFourTwo()}")
}

class Day4 {
    fun run() {
        val input = Passport(File("src/main/resources/Day04.txt").readText(Charsets.UTF_8))
        println("Day FOUR : passport validation")
        println("Part One ${input.partFourOne()}")
        println("Part Two ${input.partFourTwo()}")
    }
}

class Passport(input: String) {

    private val passports: List<String> = input.split("\n\n")

    fun partFourOne(): Int =
        passports.count { passport -> expectedFields.all { passport.contains(it)} }

    fun partFourTwo(): Int =
        passports.count { passport -> fieldPatterns.all { it.containsMatchIn(passport) } }

    companion object {
        private val expectedFields = listOf("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")

        private val fieldPatterns = listOf(
            """\bbyr:(19[2-9][0-9]|200[0-2])\b""",
            """\biyr:(201[0-9]|2020)\b""",
            """\beyr:(202[0-9]|2030)\b""",
            """\bhgt:((1([5-8][0-9]|9[0-3])cm)|((59|6[0-9]|7[0-6])in))\b""",
            """\bhcl:#[0-9a-f]{6}\b""",
            """\becl:(amb|blu|brn|gry|grn|hzl|oth)\b""",
            """\bpid:[0-9]{9}\b"""
        ).map { it.toRegex() }
    }

}