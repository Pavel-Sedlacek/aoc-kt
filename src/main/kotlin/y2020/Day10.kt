package y2020

import utils.Day
import utils.Files

class Day10: Day {

    private val input = Files.readFileAsMutableLongs(2020, 10)

    override fun runAll() {
        println("Day 10 : Jolts in three range limit")

        input.add(0, 0)

        println(this.partOne(input))
        println(this.partTwo(input))
    }

    private fun partOne(x: MutableList<Long>) : Int {
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

    private fun partTwo(nums: MutableList<Long>): Long {
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

}