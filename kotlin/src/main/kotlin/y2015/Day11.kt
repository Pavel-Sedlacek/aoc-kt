package y2015

import utils.Day
import utils.helpers.y2015.increment

class Day11 : Day<String> {

    private val input = file

    override fun runAll() {
        val p1 = input.nextPassword()
        val p2 = p1.nextPassword()
        super.run({ p1 }) { p2 }
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

