package y2020

import utils.Day
import utils.collections.Quad
import utils.readers.asCharLines

class Day17 : Day<Int> {

    val input = file.asCharLines()

    override fun runAll() {

        val grid = mutableMapOf<Triple<Int, Int, Int>, Char>()
        val grid2 = mutableMapOf<Quad<Int, Int, Int, Int>, Char>()

        for (y in input.indices) {
            for (x in input[y].indices) {
                grid[Triple(x, y, 0)] = input[x][y]
                grid2[Quad(x, y, 0, 0)] = input[x][y]
            }
        }

        repeat(6) {
            partOne(grid)
            partTwo(grid2)
        }

        super.run({grid.values.count { it == '#' }}, {grid2.values.count { it == '#'}})
    }

    private fun partOne(grid: MutableMap<Triple<Int, Int, Int>, Char>) {
        val lastGrid = grid.toMap()

        val toEvaluate = mutableSetOf<Triple<Int, Int, Int>>()
        grid.keys.forEach {
            for (i in -1..1) {
                for (j in -1..1) {
                    for (k in -1..1) {
                        for (l in -1..1) {
                            toEvaluate.add(Triple(it.first + i, it.second + j, it.third + k))
                        }
                    }
                }
            }
        }
        grid.clear()

        toEvaluate.forEach {
            var active = 0
            for (i in -1..1) {
                for (j in -1..1) {
                    for (k in -1..1) {
                        if (i == 0 && j == 0 && k == 0) {
                            continue
                        }
                        if (lastGrid[Triple(it.first + i, it.second + j, it.third + k)] == '#') {
                            active++
                        }
                    }
                }
            }

            when (lastGrid[it]) {
                '#' -> grid[it] = when (active) {
                    2, 3 -> '#'
                    else -> '.'
                }
                else -> grid[it] = when (active) {
                    3 -> '#'
                    else -> '.'
                }
            }
        }
    }

    private fun partTwo(grid2: MutableMap<Quad<Int, Int, Int, Int>, Char>) {
        val lastGrid = grid2.toMap()

        val toEvaluate = mutableSetOf<Quad<Int, Int, Int, Int>>()
        grid2.keys.forEach {
            for (i in -1..1) {
                for (j in -1..1) {
                    for (k in -1..1) {
                        for (l in -1..1) {
                            toEvaluate.add(Quad(it.x + i, it.y + j, it.z + k, it.w + l))
                        }
                    }
                }
            }
        }
        grid2.clear()

        toEvaluate.forEach {
            var active = 0
            for (i in -1..1) {
                for (j in -1..1) {
                    for (k in -1..1) {
                        for (l in -1..1) {
                            if (i == 0 && j == 0 && k == 0 && l == 0) {
                                continue
                            }
                            if (lastGrid[Quad(it.x + i, it.y + j, it.z + k, it.w + l)] == '#') {
                                active++
                            }
                        }
                    }
                }
            }

            when (lastGrid[it]) {
                '#' -> grid2[it] = when (active) {
                    2, 3 -> '#'
                    else -> '.'
                }
                else -> grid2[it] = when (active) {
                    3 -> '#'
                    else -> '.'
                }
            }
        }
    }

}

