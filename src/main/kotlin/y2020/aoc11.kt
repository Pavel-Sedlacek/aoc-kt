package y2020

import java.io.File

val pairs = arrayListOf(Pair(1,0), Pair(-1,0), Pair(0,-1), Pair(0,1), Pair(-1,1), Pair(1,-1,), Pair(-1,-1), Pair(1,1))
var x = Array(5) {CharArray(5) {'.'} }

fun main() {
    val k = File("src/main/resources/Day11.txt").useLines {it.toMutableList() }
    x = Array(k.size) {CharArray(k[0].length) {'.'} }
    for (i in 0 until k.size) {
        for (z in k[i].indices) {
            x[i][z] = k[i][z]
        }
    }

    println("Day ELEVEN : Game Of Life (boat : game of seats)")

    println("Part One ${partOneEleven()}")

    x = Array(k.size) {CharArray(k[0].length) {'.'} }
    for (i in 0 until k.size) {
        for (z in k[i].indices) {
            x[i][z] = k[i][z]
        }
    }
    println("Part Two ${partTwoEleven()}")
}

class Day11 {
    fun run() {
        val k = File("src/main/resources/Day11.txt").useLines {it.toMutableList() }
        x = Array(k.size) {CharArray(k[0].length) {'.'} }
        for (i in 0 until k.size) {
            for (z in k[i].indices) {
                x[i][z] = k[i][z]
            }
        }

        println("Day ELEVEN : Game Of Life (boat : game of seats)")

        println("Part One ${partOneEleven()}")

        x = Array(k.size) {CharArray(k[0].length) {'.'} }
        for (i in 0 until k.size) {
            for (z in k[i].indices) {
                x[i][z] = k[i][z]
            }
        }
        println("Part Two ${partTwoEleven()}")
    }
}

fun partOneEleven() : Int{
    realHard@while (true) {
        val previous = x
        val temp = Array(x.size) {CharArray(x[0].size) {'.'} }
        for (i in x.indices) {
            for (z in x[i].indices) {
                var empty = 0
                var occupied = 0
                if (x[i][z] != '.') {
                    for (pair in pairs) {
                        try {
                            when (x[i + pair.first][z + pair.second]) {
                                'L' -> {
                                    empty++
                                }
                                '#' -> {
                                    occupied++
                                }
                            }
                        } catch (e: Exception) {
                            continue
                        }
                    }
                    if (x[i][z] == '#' && occupied >= 4) {
                        temp[i][z] = 'L'
                    } else if (x[i][z] == 'L' && occupied == 0) {
                        temp[i][z] = '#'
                    } else {
                        temp[i][z] = x[i][z]
                    }
                }
            }
        }

        x = temp

        for (i in previous.indices) {
            for (j in previous[i].indices) {
                if (previous[i][j] != x[i][j]) continue@realHard
            }
        }

        return (x.fold(0) {acc, chars ->
            acc + chars.count { i ->
                i == '#'
            }
        })
    }
}

fun partTwoEleven() : Int {

    realHard@while (true) {
        val previous = x
        val temp = Array(x.size) {CharArray(x[0].size) {'.'} }

        for (i in x.indices) {
            for (z in x[i].indices) {
                var empty = 0
                var occupied = 0

                if (x[i][z] != '.') {
                    for (pair in pairs) {
                        var cur = 1
                        jes@while (true) {
                            try {
                                if (x[i + pair.first * cur][z + pair.second * cur] != '.') {
                                    if (x[i + pair.first * cur][z + pair.second * cur] == '#') {
                                        occupied++
                                    } else empty++
                                    break@jes
                                } else {
                                    cur++
                                    continue@jes
                                }

                            } catch(e : Exception) {
                                break@jes
                            }
                        }
                    }
                    if (x[i][z] == '#' && occupied >= 5) {
                        temp[i][z] = 'L'
                    } else if (x[i][z] == 'L' && occupied == 0) {
                        temp[i][z] = '#'
                    } else {
                        temp[i][z] = x[i][z]
                    }
                }

            }
        }

        x = temp

        for (i in previous.indices) {
            for (j in previous[i].indices) {
                if (previous[i][j] != x[i][j]) continue@realHard
            }
        }

        return (x.fold(0) {acc, chars ->
            acc + chars.count { i ->
                i == '#'
            }
        })
    }
}