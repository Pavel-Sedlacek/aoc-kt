package utils.helpers.y2017

import utils.common.isOdd
import kotlin.math.ceil
import kotlin.math.sqrt

class GridOfExpandingNumbers(size: Int) {
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