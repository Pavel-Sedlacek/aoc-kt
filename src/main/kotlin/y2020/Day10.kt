package y2020

import utils.Day
import utils.collections.modify
import utils.readers.asLongs

class Day10 : Day<Long> {

    private val input = file.asLongs().modify { it.add(0, 0) }

    override fun runAll() = super.run({ partOne(input) }) { partTwo(input) }

    private fun partOne(input: List<Long>): Long {
        val x = input.sortedBy { it.toInt() }

        var three = 0L
        var one = 0L

        for (i in 1 until x.size) {
            if (x[i] - (one + (3 * three)) == 3L)
                three++
            else
                one++
        }
        return (three + 1) * one
    }

    private fun partTwo(nums: List<Long>): Long {
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