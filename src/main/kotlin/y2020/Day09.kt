package y2020

import utils.Day
import utils.readers.asLongs

class Day09 : Day<Long> {
    private val input = file.asLongs()

    override fun runAll() {
        val p1 = partOne(input)

        super.run({ p1 }, { partTwo(input, p1) })
    }

    private fun partOne(x: List<Long>): Long? {
        jes@ for (i in 25 until x.size) {
            for (j in i - 25..i) {
                for (z in i - 25..i) {
                    if (x[j] + x[z] == x[i]) {
                        continue@jes
                    }
                }
            }
            return x[i]
        }
        return null
    }

    private fun partTwo(x: List<Long>, target: Long?): Long? {
        for (i in x.indices) {
            for (z in i until x.size) {
                if ((x.subList(i, z)).sum() > target!!) {
                    break
                } else if ((x.subList(i, z)).sum() == target) {
                    return ((x.subList(i, z)).minOrNull()?.plus((x.subList(i, z)).maxOrNull()!!))
                }
            }
        }
        return null
    }
}