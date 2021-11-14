package y2020

import utils.Day
import utils.readers.asInts

class Day01 : Day<Int> {

    private val input = file.asInts()

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

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

