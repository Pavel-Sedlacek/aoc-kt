package y2020

import utils.Day
import utils.readers.asLinesSplitBy

class Day06 : Day<Int> {
    private val input = file.asLinesSplitBy("\\n\\n".toRegex())

    override fun runAll() = super.run({ partOne(input) }, { partTwo(input) })

    private fun partOne(input: List<String>): Int {
        var sum = 0
        for (i in input) {
            val j = mutableSetOf<Char>()
            for (o in i) if (o in 'a'..'z') j += o
            sum += j.size
        }
        return sum
    }

    private fun partTwo(input: List<String>): Int {
        var sum = 0

        for (i in input) {
            val j = mutableListOf<String>()
            var indexer = 0
            var adding = ""
            for (z in i) {
                if (z in 'a'..'z') {
                    adding += z
                } else {
                    j.add(adding)
                    indexer++
                    adding = ""
                }
            }
            j.add(adding)
            val letters = ('a'..'z').toMutableSet()

            for (h in j) {
                for (b in 'a'..'z') {
                    if (!h.contains(b)) {
                        letters.remove(b)
                    }
                }
            }
            sum += letters.size
        }
        return (sum)
    }


}