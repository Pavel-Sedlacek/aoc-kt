package y2020

import utils.Day
import utils.Files

class Day09: Day {
    private val input = Files.readFileAsMutableLongs(2020, 9)

    override fun runAll() {
        println("Day 09 : each number is sum of 2 of the previous 25 numbers")

        val p1 = partOne(input)
        println(p1)
        println(this.partTwo(input, p1))
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