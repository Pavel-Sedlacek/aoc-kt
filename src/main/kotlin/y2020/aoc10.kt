package y2020

import java.io.File

fun main() {
    val nums = ArrayList<Long>()
    nums.add(0)
    File("src/main/resources/Day10.txt").bufferedReader().readLines().forEach { nums.add(it.toLong()) }
    println("Day TEN : JOLTS, 3 range limit")
    println("Part One ${partOneTen(nums)}")
    nums.add(nums.maxOrNull()!! + 3)
    nums.sort()
    println("Part Two ${partTwoTen(nums)}")
}

class Day10 {
    fun run() {
        val nums = ArrayList<Long>()
        nums.add(0)
        File("src/main/resources/Day10.txt").bufferedReader().readLines().forEach { nums.add(it.toLong()) }
        println("Day TEN : JOLTS, 3 range limit")
        println("Part One ${partOneTen(nums)}")
        nums.add(nums.maxOrNull()!! + 3)
        nums.sort()
        println("Part Two ${partTwoTen(nums)}")
    }
}

fun partOneTen(x: ArrayList<Long>) : Int {
    x.sortBy { it.toInt() }

    var three = 0
    var one = 0

    for (i in 1 until x.size) {
        if (x[i] - (one + (3 * three)) == 3L)
            three++
        else
            one++
    }
    return (three + 1) * one
}

fun partTwoTen(nums: ArrayList<Long>): Long {
    val f = LongArray(nums.size) { 0L }
    f[nums.size - 1] = 1
    for (i in nums.size - 2 downTo 0) {
        f[i] = f[i + 1]
        if (i + 3 < nums.size && nums[i + 3] <= nums[i] + 3) {
            f[i] += f[i + 3]
        }
        if (i + 2 < nums.size && nums[i + 2] <= nums[i] + 3) {
            f[i] += f[i + 2]
        }
    }
    return f[0]
}