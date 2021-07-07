package utils

import kotlin.math.ceil
import kotlin.math.sqrt

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

    fun recursiveGo(x: List<String>): Int {
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

    private fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
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

    private fun Int.isOdd(): Boolean = this % 2 != 0

    fun lengthOfSideWith(n: Int): Int =
        ceil(sqrt(n.toDouble())).toInt().let {
            if (it.isOdd()) it
            else it + 1
        }

    fun midpointsForSideLength(sideLength: Int): List<Int> {
        val highestOnSide = sideLength * sideLength
        val offset = ((sideLength - 1) / 2.0).toInt()
        return (0..3).map {
            highestOnSide - (offset + (it * sideLength.dec()))
        }
    }

    sealed class Direction {
        abstract fun move(point: Pair<Int, Int>): Pair<Int, Int>
        abstract val turn: Direction

        object East : Direction() {
            override fun move(point: Pair<Int, Int>): Pair<Int, Int> = Pair(point.first + 1, point.second)
            override val turn = North
        }

        object North : Direction() {
            override fun move(point: Pair<Int, Int>): Pair<Int, Int> = Pair(point.first, point.second - 1)
            override val turn = West
        }

        object West : Direction() {
            override fun move(point: Pair<Int, Int>): Pair<Int, Int> = Pair(point.first - 1, point.second)
            override val turn = South
        }

        object South : Direction() {
            override fun move(point: Pair<Int, Int>): Pair<Int, Int> = Pair(point.first, point.second + 1)
            override val turn = East
        }
    }

    class Grid(size: Int) {
        private var pointer: Pair<Int, Int> = Pair(size / 2, size / 2)
        private var direction: Direction = Direction.East
        private val grid: List<IntArray> = (0 until size).map { IntArray(size) }.apply {
            this[pointer.first][pointer.second] = 1
        }

        fun generateSpots(): Sequence<Int> =
            generateSequence(1) {
                pointer = direction.move(pointer)
                grid[pointer.first][pointer.second] = sumNeighbors()
                // Turn if we can.
                direction = if (atSpot(direction.turn.move(pointer)) == 0) direction.turn else direction
                atSpot(pointer)
            }


        private fun sumNeighbors(): Int =
            (pointer.first - 1..pointer.first + 1).map { x ->
                (pointer.second - 1..pointer.second + 1).map { y ->
                    atSpot(x, y)
                }
            }.flatten()
                .filterNotNull()
                .sum()


        private fun atSpot(spot: Pair<Int, Int>): Int? =
            atSpot(spot.first, spot.second)

        private fun atSpot(x: Int, y: Int): Int? =
            if (x in (grid.indices) && y in (grid.indices)) grid[x][y]
            else null
    }

    tailrec fun jump(offsets: MutableList<Int>, mutator: (Int) -> Int, pc: Int = 0, steps: Int = 0): Int =
        if (pc < 0 || pc >= offsets.size) steps
        else {
            val nextPc = pc + offsets[pc]
            offsets[pc] += mutator(offsets[pc])
            jump(offsets, mutator, nextPc, steps.inc())
        }

    tailrec fun reallocate(
        memory: MutableList<Int>,
        seen: Map<String, Int> = mutableMapOf(),
        answer: (Map<String, Int>, String) -> Int
    ): Int {
        val hash = memory.joinToString()
        return if (hash in seen) answer(seen, hash)
        else {
            val (index, amount) = memory.withIndex().maxByOrNull { it.value }!!
            memory[index] = 0
            repeat(amount) { i ->
                val idx = (index + i + 1) % memory.size
                memory[idx] += 1
            }
            reallocate(memory, seen + (hash to seen.size), answer)
        }
    }


}
