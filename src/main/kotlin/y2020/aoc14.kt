package y2020

import java.io.File

fun main() {
    val x = File("src/main/resources/Day14.txt")
    println("Day FOURTEEN : memory masking")
    println("Part One ${partOneFourteen(x)}")
    println("Part Two ${partTwoFourteen(x)}")
}

class Day14 {
    fun run() {
        val x = File("src/main/resources/Day14.txt")
        println("Day FOURTEEN : memory masking")
        println("Part One ${partOneFourteen(x)}")
        println("Part Two ${partTwoFourteen(x)}")
    }
}

fun partTwoFourteen(f : File) : Long {
    val mem = mutableMapOf<Long, Long>()
    var mask = ""
    f.forEachLine {
        if (it.startsWith("mask")) {
            mask = it.substringAfter("= ")
        } else {
            val values = it.split("[", "] = ")
            val location = values[1].toInt().toString(2).padStart(36, '0')
            val value = values[2].toLong()

            for (loc in floatingValues(mask, location)) {
                mem[loc.toLong(2)] = value
            }
        }
    }
    return mem.map { it.value }.sum()
}

fun partOneFourteen(f : File) : Long {
    val mem = LongArray(100000)
    var mask = 0L
    var muskox = 0L

    f.forEachLine {
        if (it.startsWith("mask")) {
            val m = it.substringAfter("= ")
            mask = m.replace("X", "0").toLong(2)
            muskox = m.replace("1", "0").replace("X", "1").toLong(2)
        } else {
            val values = it.split("[", "] = ")
            val location = values[1].toInt()
            val value = values[2].toLong()

            mem[location] = (muskox and value) + mask
        }
    }
    return mem.sum()
}

fun floatingValues(mask: String, address: String): MutableList<String> {
    val addresses = mutableListOf<String>()
    if (mask.isNotEmpty()) {
        val maskR = mask[mask.lastIndex]
        val addressR = address[address.lastIndex]

        val recurse = floatingValues(mask.substring(0, mask.lastIndex), address.substring(0, address.lastIndex))

        when (maskR) {
            '0' -> {
                addresses.addAll(recurse.map { "$it$addressR" })
            }
            '1' -> {
                addresses.addAll(recurse.map { "$it$maskR" })
            }
            'X' -> {
                addresses.addAll(recurse.map { "${it}0" })
                addresses.addAll(recurse.map { "${it}1" })
            }
        }
    } else {
        addresses.add("")
    }
    return addresses
}