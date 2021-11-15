package y2015

import utils.Day
import utils.readers.asLines

class Day18 : Day<Int> {

    val input = file.asLines().map { it.toList().map { it != '.' } }

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<List<Boolean>>): Int {
        var x = input.toList()
        for (i in 0 until 100) {
            x = x.gameOfLife()
        }
        return x.sumBy { it.sumBy { if (it) 1 else 0 } }
    }

    private fun partTwo(input: List<List<Boolean>>): Int {
        var x = input.toList().map { it.toMutableList() }
        for (i in 0 until 100) {

            x[0][0] = true
            x[x.size - 1][x[0].size - 1] = true
            x[x.size - 1][0] = true
            x[0][x[0].size - 1] = true


            x = x.gameOfLife()
        }
        x[0][0] = true
        x[x.size - 1][x[0].size - 1] = true
        x[x.size - 1][0] = true
        x[0][x[0].size - 1] = true
        return x.sumBy { it.sumBy { if (it) 1 else 0 } }
    }
}


fun List<List<Boolean>>.gameOfLife(): List<MutableList<Boolean>> {
    val res = this.toList().map { it.toMutableList() }

    for (x in this.indices) {
        for (y in this[x].indices) {
            val neighboursAlive =
                this.subList(
                    (x - 1).let { if (it <= 0) 0 else it },
                    (x + 2).let { if (it >= this.size) this.size else it })
                    .map { subL ->
                        subL.subList(
                            (y - 1).let { if (it <= 0) 0 else it },
                            (y + 2).let { if (it >= subL.size) subL.size else it })
                    }.sumBy { it.sumBy { if (it) 1 else 0 } } - if (this[x][y]) 1 else 0
            when {
                this[x][y] && neighboursAlive !in 2..3 -> res[x][y] = false
                neighboursAlive == 3 -> res[x][y] = true
            }
        }
    }
    return res
}