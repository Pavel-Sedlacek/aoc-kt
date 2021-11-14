package y2015

import utils.Day
import utils.Files
import utils.Helpers.increment

class Day11 : Day {

    private val input = Files.readFileAsString(2015, 11)

    override fun runAll() {
        println("Day 11: Password find")
        input.nextPassword().also {
            println(it)
            println(it.nextPassword())
        }
    }

    private fun String.nextPassword(): String {
        var password = this
        while (true) {
            password = password.increment()
            if (password.contains("[loi]".toRegex())) continue
            if (!password.matches(".*([a-z])\\1.*([a-z])\\2.*".toRegex())) continue
            if (!password.contains("abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz".toRegex())) continue
            return password
        }
    }
}

