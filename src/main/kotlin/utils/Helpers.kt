package utils

import utils.collections.Coordinates
import utils.collections.clone
import utils.collections.cloneMutable
import utils.common.not
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
        answer: (Map<String, Int>, String) -> Int,
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

    private fun initLights(): MutableList<Light> {
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
                        else if (computedGates[components[1]]?.isComputed() == true) computedGates[components[1]]!!.retrieve()!!
                            .not()
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

    fun solver(
        input: List<List<String>>,
        off: KFunction1<Light, Unit>,
        on: KFunction1<Light, Unit>,
        toggle: KFunction1<Light, Unit>,
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

    enum class State {
        RESTING,
        SPEEDING
    }

    data class Stats(
        val speed: Int,
        val speedDuration: Int,
        val restDuration: Int,
        var distanceTraveled: Int = 0,
        var restRemaining: Int = 0,
        var speedRemaining: Int = speedDuration,
        var state: State = State.SPEEDING,
        var score: Int = 0,
    ) {
        fun move() {
            if (state == State.SPEEDING) {
                go()
            } else {
                stop()
            }
        }

        private fun go() {
            if (speedRemaining <= 0) {
                restRemaining = restDuration
                state = State.RESTING
                stop()
                return
            }
            distanceTraveled += speed
            speedRemaining--
        }

        private fun stop() {
            if (restRemaining <= 0) {
                speedRemaining = speedDuration
                state = State.SPEEDING
                go()
                return
            }
            restRemaining--
        }
    }

    data class Matches(
        val children: Int,
        val cats: Int,
        val samoyeds: Int,
        val pomeranians: Int,
        val akitas: Int,
        val vizslas: Int,
        val goldfish: Int,
        val trees: Int,
        val cars: Int,
        val perfumes: Int,
    ) {
        fun match(other: Matches): MatchResult {

            return MatchResult.NO_MATCH
        }

        companion object {
            fun from(let: Map<String, Int>): Matches {
                return Matches(
                    children = let["children"] ?: -1,
                    cats = let["cats"] ?: -1,
                    samoyeds = let["samoyeds"] ?: -1,
                    pomeranians = let["pomeranians"] ?: -1,
                    akitas = let["akitas"] ?: -1,
                    vizslas = let["vizslas"] ?: -1,
                    goldfish = let["goldfish"] ?: -1,
                    trees = let["trees"] ?: -1,
                    cars = let["cars"] ?: -1,
                    perfumes = let["perfumes"] ?: -1
                )
            }
        }
    }

    enum class MatchResult {
        NO_MATCH,
        CAN_MATCH,
        PERFECT_MATCH
    }

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

    fun calcWaterRoughness(image: Tile): Int {
        val monsters = findAllSeaMonsters(image)
        return if (monsters != 0) {
            image.grid.sumOf { r -> r.count { it } } - monsters * 15
        } else {
            -1
        }
    }

    private fun findAllSeaMonsters(image: Tile): Int {
        repeat(4) {
            countSeaMonsters(image).let { if (it != 0) return it }

            image.flipVertical()
            countSeaMonsters(image).let { if (it != 0) return it }

            image.flipHorizontal()
            countSeaMonsters(image).let { if (it != 0) return it }

            image.flipVertical()
            countSeaMonsters(image).let { if (it != 0) return it }

            image.flipHorizontal()

            image.rotateRight()
        }

        return 0
    }

    private fun countSeaMonsters(image: Tile): Int {
        var count = 0
        for (y in 1 until image.grid.size - 1) {
            for (x in 0 until image.grid.size - 19) {
                if (isSeaMonster(image, x, y)) count++
            }
        }

        return count
    }

    private fun isSeaMonster(image: Tile, x: Int, y: Int): Boolean {
        return if (image[x, y]) {
            (image[x + 18, y - 1] && image[x + 5, y] && image[x + 6, y] && image[x + 11, y] && image[x + 12, y] && image[x + 17, y] && image[x + 18, y] && image[x + 19, y] && image[x + 1, y + 1] && image[x + 4, y + 1] && image[x + 7, y + 1] && image[x + 10, y + 1] && image[x + 13, y + 1] && image[x + 16, y + 1])
        } else false
    }

    class Tile(val id: Long, var grid: List<List<Boolean>>) {

        // tile connections
        var top: Long? = null
        var right: Long? = null
        var bottom: Long? = null
        var left: Long? = null

        operator fun get(x: Int, y: Int): Boolean = grid[y][x]

        fun removeBorder() {
            grid = grid.drop(1).dropLast(1).map { it.drop(1).dropLast(1) }
        }

        fun getAllBorders(): List<List<Boolean>> {
            val borders = mutableListOf<List<Boolean>>()

            val top = grid.first()
            borders.add(top)
            // borders.add(top.reversed())

            val right = grid.map { it.last() }
            borders.add(right)
            // borders.add(right.reversed())

            val bottom = grid.last().reversed()
            borders.add(bottom)
            // borders.add(bottom.reversed())

            val left = grid.map { it.first() }.reversed()
            borders.add(left)
            // borders.add(left.reversed())

            return borders
        }

        fun rotateRight(times: Int = 1) {   // rotate in place
            repeat(times) {
                grid =
                    grid.flatMap { it.withIndex() }.groupBy({ (i, _) -> i }, { (_, v) -> v })
                        .map { (_, v) -> v.reversed() }
            }
        }

        fun flipHorizontal() {
            grid = grid.map { it.reversed() }
        }

        fun flipVertical() {
            grid = grid.reversed()
        }

        fun setConnection(i: Int, tileID: Long) {
            when (i) {
                0 -> top = tileID
                1 -> right = tileID
                2 -> bottom = tileID
                3 -> left = tileID
            }
        }

        fun getConnection(i: Int): Long? {
            return when (i) {
                0 -> top
                1 -> right
                2 -> bottom
                3 -> left
                else -> error("")
            }
        }

        fun print() {
            grid.forEach { it.map { b -> if (b) '#' else '.' }.joinToString("").also { s -> println(s) } }
        }
    }
}

@ExperimentalUnsignedTypes
fun jellyFishSolver(jellyFish: List<Pair<Int, ULong>>, iterations: Int): ULong {
    var fish = jellyFish.toMutableList()
    repeat(iterations) {
        val new = mutableListOf<Pair<Int, ULong>>()
        for (i in fish) {
            if (i.first == 0) {
                new.add(Pair(8, i.second))

                var t: ULong = 0u
                if (fish.any { it.first == 6 }) {
                    t = new.filter { it.first == 6 }.sumOf { it.second }
                    new.removeAll { it.first == 6 }
                }
                new.add(Pair(6, i.second + t))

            } else {
                var t: ULong = 0u
                if (fish.any { it.first == i.first - 1 }) {
                    t = new.filter { it.first == i.first - 1 }.sumOf { it.second }
                    new.removeAll { it.first == i.first - 1 }
                }
                new.add(Pair(i.first - 1, i.second + t))
            }
        }
        fish = new
    }
    return fish.sumOf { it.second }
}
class BingoBoard(private var board: List<MutableList<Int?>>) {

    private val original = board.clone()

    fun reset() {
        board = original.cloneMutable()
    }

    fun isWinning(): Boolean {
        next@ for (j in board[0].indices) {
            for (row in board) {
                if (row[j] != null) continue@next
            }
            return true
        }
        return board.any { it.all { it == null } }
    }

    fun flip(number: Int) = board.forEach { l -> l.indexOf(number).also { if (it != -1) l[it] = null } }

    fun sum(): Int = board.sumBy { it.sumBy { it ?: 0 } }
}

data class Line(val from: Coordinates, val to: Coordinates) {
    fun isDiagonal() = (from.x != to.x) and (from.y != to.y)
}

fun overlappingVentsSolver(input: List<Line>): Int {
    val ventMap = HashMap<Coordinates, Int>()
    input.forEach {
        ventMap[it.from] = ventMap[it.from]?.plus(1) ?: 1
        var lastCoordinate = it.from
        while (lastCoordinate != it.to) {
            val nextCoordinate = lastCoordinate.moveTowards(it.to)
            ventMap[nextCoordinate] = ventMap[nextCoordinate]?.plus(1) ?: 1
            lastCoordinate = nextCoordinate
        }
    }
    return ventMap.values.count { it > 1 }
}
