package y2021

import utils.Day
import utils.readers.asLines

@ExperimentalStdlibApi
class Day09 : Day<Int> {

    val input = file.asLines().map { it.trim().toList().map { it.digitToInt() } }

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(points: List<List<Int>>): Int {
        var lows = 0
        for (i in points.indices) {
            for (j in points[i].indices) {
                if (points.isLower(j, i)) {
                    lows += points[i][j] + 1
                }
            }
        }
        return lows
    }

    private fun partTwo(points: List<List<Int>>): Int {
        val basins = mutableListOf<Int>()
        for (i in points.indices) {
            for (j in points[i].indices) {
                if (points.isLower(j, i)) {
                    basins.add(points.nonAdjacent(j, i))
                }
            }
        }
        return basins.sortedDescending().take(3).reduce { x, y -> x * y }
    }
}

private fun List<List<Int>>.isLower(j: Int, i: Int): Boolean {
    val point = this[i][j]
    if ((this.getOrNull(i - 1)?.getOrNull(j) ?: -1) in 0..point) return false
    if ((this.getOrNull(i + 1)?.getOrNull(j) ?: -1) in 0..point) return false
    if ((this.getOrNull(i)?.getOrNull(j + 1) ?: -1) in 0..point) return false
    if ((this.getOrNull(i)?.getOrNull(j - 1) ?: -1) in 0..point) return false
    return true
}

private fun List<List<Int>>.nonAdjacent(
    j: Int, i: Int, map: MutableMap<Pair<Int, Int>, Boolean> = mutableMapOf()
): Int {
    val up = this.getOrNull(i - 1)?.getOrNull(j) ?: -1
    val down = this.getOrNull(i + 1)?.getOrNull(j) ?: -1
    val right = this.getOrNull(i)?.getOrNull(j + 1) ?: -1
    val left = this.getOrNull(i)?.getOrNull(j - 1) ?: -1
    mapOf(Pair(j, i - 1) to up, Pair(j, i + 1) to down, Pair(j + 1, i) to right, Pair(j - 1, i) to left).forEach {
        if (it.value in 0..8) {
            if (map[it.key] == null) {
                map[it.key] = true
                nonAdjacent(it.key.first, it.key.second, map)
            }
        }
    }
    return map.size
}
