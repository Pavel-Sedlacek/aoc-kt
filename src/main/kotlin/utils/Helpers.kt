package utils

import utils.collections.Coordinates
import kotlin.math.ceil
import kotlin.math.sqrt
import kotlin.reflect.KFunction1

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
            }

            (if (fn == toggle) 1 else 2).also {
                line[it].split(",").also { ints -> from.x = ints[0].toInt(); from.y = ints[1].toInt() }
                line[it + 2].split(",").also { ints -> to.x = ints[0].toInt(); to.y = ints[1].toInt() }
            }
            lights.forEach { if (it.isInRange(from.x, to.x, from.y, to.y)) fn.invoke(it) }
        }
    }.let { lights -> lights.sumBy { it.state } }
}

