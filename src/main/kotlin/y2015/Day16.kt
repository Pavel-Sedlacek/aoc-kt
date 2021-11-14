package y2015

import utils.Day
import utils.Helpers.MatchResult
import utils.Helpers.Matches
import utils.readers.asLines


// FIXME
class Day16 : Day<Int> {

    private val aunts = file.asLines().associate { line ->
        line.substringAfter(" ").substringBefore(" ").let {
            it.replace(":", "").toInt() to Matches.from(line.let { args ->
                args.substringAfter(" ").substringAfter(" ").split(",").associate { arg ->
                    arg.split(": ").let { th -> th[0].trim() to th[1].trim().toInt() }
                }
            })
        }
    }
    private val scan = Matches(3, 7, 2, 3, 0, 0, 5, 3, 2, 1)

    override fun runAll() {
//        super.run({ partOne(aunts, scan) }, { partTwo() })
    }

    private fun partOne(aunts: Map<Int, Matches>, scan: Matches) {
        aunts.filter { (aunt, aScan) ->
            aScan.match(scan) != MatchResult.NO_MATCH
        }
    }

    private fun partTwo(): Int {
        return -1
    }
}