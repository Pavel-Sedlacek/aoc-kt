package utils

import java.security.MessageDigest
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

    fun listToBinaryList(stringList: MutableList<String>): MutableList<String> {
        val binList = stringList.toMutableList()
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

    fun String.md5(): ByteArray = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

    fun initLights(): MutableList<Light> {
        val z = mutableListOf<Light>()
        for (x in 0 until 1000) {
            for (y in 0 until 1000) {
                z.add(Light(x, y, 0))
            }
        }
        return z
    }

    data class Light(val x: Int, val y: Int, var state: Int) {
        fun isInRange(xFrom: Int, xTo: Int, yFrom: Int, yTo: Int): Boolean = x in (xFrom)..xTo && y in (yFrom)..yTo

        fun off1() {
            state = 0
        }

        fun on1() {
            state = 1
        }

        fun toggle1() {
            state = if (state == 1) 0 else 1
        }

        fun off2() {
            if (state > 0) state--
        }

        fun on2() {
            state++
        }

        fun toggle2() {
            state += 2
        }
    }
    fun solver(
        input: List<List<String>>,
        off: KFunction1<Light, Unit>,
        on: KFunction1<Light, Unit>,
        toggle: KFunction1<Light, Unit>
    ): Int = initLights().also { lights ->
        input.onEach { line ->
            val from = Coordinates(0, 0)
            val to = Coordinates(0, 0)
            val fn: KFunction1<Light, Unit> = when {
                line[0] == "toggle" -> toggle
                else -> if (line[1] == "on") on else off

    data class Coordinates(var x: Int, var y: Int)

    fun String.toDecimal(): Int {
        var sum = 0
        this.reversed().forEachIndexed { k, v ->
            sum += v.toString().toInt() * pow(2, k)
        }
        return sum
    }

    fun Int.toBinary(binaryString: String = ""): String {
        while (this > 0) {
            val temp = "${binaryString}${this % 2}"
            return (this / 2).toBinary(temp)
        }
        return binaryString.reversed()
    }

    fun pow(base: Int, exponent: Int) = Math.pow(base.toDouble(), exponent.toDouble()).toInt()

    fun Int.not(): Int =
        this.toBinary().padStart(16, '0').let { it.map { if (it == '0') 1 else 0 }.joinToString("").toDecimal() }

    data class Gate(val expression: String) {

        private var cached: Int? = null

        fun isComputed(): Boolean = cached != null

        fun retrieve(): Int? {
            return if (cached == null) null
            else if (cached!! < 0) 0
            else if (cached!! > 65535) 65535
            else cached!!
        }

        fun compute(computedGates: MutableMap<String, Gate>) {
            val components = expression.split(" ").map { it.trim() }
            when (components.size) {
                1 -> {
                    // Number or gate copy
                    cached = if (components[0].matches("[0-9]*".toRegex()))
                        components[0].toInt()
                    else if (computedGates[components[0]]?.isComputed() == true)
                        computedGates[components[0]]!!.retrieve()
                    else return
                }
                2 -> {
                    // not + gate or number
                    if (components[0] == "NOT")
                        cached = if (components[1].matches("[0-9]*".toRegex())) components[1].toInt().not()
                        else if (computedGates[components[1]]?.isComputed() == true) computedGates[components[1]]!!.retrieve()!!.not()
                        else return

                }
                3 -> {
                    // AND, OR , RSHIFT, LSHIFT

                    val left: Int = if (components[0].matches("[0-9]*".toRegex())) components[0].toInt()
                    else if (computedGates[components[0]]?.isComputed() == true) computedGates[components[0]]!!.retrieve()!!
                    else return


                    val right = if (components[2].matches("[0-9]*".toRegex())) components[2].toInt()
                    else if (computedGates[components[2]]?.isComputed() == true) computedGates[components[2]]!!.retrieve()!!
                    else return

                    cached = when (components[1]) {
                        "AND" -> left.and(right)
                        "OR" -> left.or(right)
                        "RSHIFT" -> left.shr(right)
                        "LSHIFT" -> left.shl(right)
                        else -> return
                    }
                }
            }
            return
        }
    }


    fun <T> Set<T>.allPermutations(): Set<List<T>> {
        if (this.isEmpty()) return emptySet()

        fun <T> List<T>._allPermutations(): Set<List<T>> {
            if (this.isEmpty()) return setOf(emptyList())

            val result: MutableSet<List<T>> = mutableSetOf()
            for (i in this.indices) {
                (this - this[i])._allPermutations().forEach { item ->
                    result.add(item + this[i])
                }
            }

            (if (fn == toggle) 1 else 2).also {
                line[it].split(",").also { ints -> from.x = ints[0].toInt(); from.y = ints[1].toInt() }
                line[it + 2].split(",").also { ints -> to.x = ints[0].toInt(); to.y = ints[1].toInt() }
            }
            lights.forEach { if (it.isInRange(from.x, to.x, from.y, to.y)) fn.invoke(it) }
        }
    }.let { lights -> lights.sumBy { it.state } }
        return this.toList()._allPermutations()
    }

    fun Any.log() {
        println(this)
    }

    fun String.increment(): String = (this.last() + 1).let {
        if (it > 'z') this.substring(0 until this.length - 1).increment() + 'a'
        else this.substring(0 until this.length - 1) + it
    }
}

