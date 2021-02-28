package y2020

import utils.*

class Day01: Day {

    private val input = Files.readFileAsInts(2020, 1)

    override fun runAll() {
        println("Day 01 : find product of two/three numbers that sums to 2020")
        println(this.partOne(input))
        println(this.partTwo(input))
    }

    private fun partOne(x: List<Int>): Int {
        for (n1 in x) {
            if (n1 > 2020) continue
            for (n2 in x)
                if (n1 + n2 == 2020)
                    return n1 * n2
        }
        return 0
    }

    private fun partTwo(x: List<Int>): Int {
        for (n1 in x) {
            if (n1 > 2020) continue
            for (n2 in x) {
                if (n1 + n2 > 2020) continue
                for (n3 in x)
                    if (n1 + n2 + n3 == 2020)
                        return n1 * n2 * n3
            }
        }
        return 0
    }

}

