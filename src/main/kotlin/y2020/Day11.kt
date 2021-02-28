package y2020

import utils.Day
import utils.Files

class Day11: Day {

    private val input = Files.readFileAsMutableList(2020, 11)
    val pairs = arrayListOf(Pair(1,0), Pair(-1,0), Pair(0,-1), Pair(0,1), Pair(-1,1), Pair(1,-1,), Pair(-1,-1), Pair(1,1))
    var x = Array(5) {CharArray(5) {'.'} }

    override fun runAll() {
        println("Day 11 : Game Of Life (boat : game of seats)")

        println(this.partOne())
        println(this.partTwo())
    }

    //TODO
    private fun partOne() : Int = 0
    private fun partTwo(): Int = 0

//        realHard@while (true) {
//            val previous = x
//            val temp = Array(x.size) {CharArray(x[0].size) {'.'} }
//            for (i in x.indices) {
//                for (z in x[i].indices) {
//                    var empty = 0
//                    var occupied = 0
//                    if (x[i][z] != '.') {
//                        for (pair in pairs) {
//                            try {
//                                when (x[i + pair.first][z + pair.second]) {
//                                    'L' -> {
//                                        empty++
//                                    }
//                                    '#' -> {
//                                        occupied++
//                                    }
//                                }
//                            } catch (e: Exception) {
//                                continue
//                            }
//                        }
//                        if (x[i][z] == '#' && occupied >= 4) {
//                            temp[i][z] = 'L'
//                        } else if (x[i][z] == 'L' && occupied == 0) {
//                            temp[i][z] = '#'
//                        } else {
//                            temp[i][z] = x[i][z]
//                        }
//                    }
//                }
//            }
//
//            x = temp
//
//            for (i in previous.indices) {
//                for (j in previous[i].indices) {
//                    if (previous[i][j] != x[i][j]) continue@realHard
//                }
//            }
//
//            return (x.fold(0) {acc, chars ->
//                acc + chars.count { i ->
//                    i == '#'
//                }
//            })
//        }
//    }

}