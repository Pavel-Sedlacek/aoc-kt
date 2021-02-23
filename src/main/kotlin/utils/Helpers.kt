package utils

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
}