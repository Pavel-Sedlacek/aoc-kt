package y2020

import utils.Day
import utils.Files



class Day16: Day {

    private val input = Files.readFileAsLinesSplitBy(2020, 16, "\r\n\r\n")
    private var validTicks = mutableListOf<String>()

    override fun runAll() {

        for (i: String in input) {
            println("Part: $i ")
        }

        println("Day 16 : Tickets")
        println(partOne(input.toMutableList()))
        println(partTwo(input.toMutableList()))
    }

    private fun partOne(input: MutableList<String>): Int {
        val rule = input[0].split("\n")
        val rules = mutableMapOf<String, Array<IntRange>>()
        val tickets = input[2].split("\n")


        for (element in rule) {
            val line = element.split(": ")
            val nums = line[1].split(" or ")
            rules[line[0]] = arrayOf(
                nums[0].substringBefore("-").toInt()..nums[0].substringAfter("-").toInt(),
                nums[1].substringBefore("-").toInt()..nums[1].substringAfter("-").toInt()
            )
        }

        val erate = mutableListOf<Int>()

        no@ for (i in 1 until tickets.size) {
            val curLine = tickets[i].split(",")
            val x = erate.size
            jes@ for (j in curLine.indices) {
                for (element in rules) {
                    if (curLine[j].toInt() in element.value[0] || curLine[j].toInt() in element.value[1]) {
                        continue@jes
                    }
                }
                erate.add(curLine[j].toInt())
            }
            if (x == erate.size) {
                validTicks.add(curLine.joinToString(""))
            }
        }

        return erate.sum()
    }

    private fun partTwo(input: MutableList<String>): Long {
        val (ranges, your, nearby) = input.map { it.split("\n") }

        val fields = MutableList(1001) { 0 }
        var departure = 0
        for (i in ranges.indices) {
            val (range1_1, range1_2, range2_1, range2_2) = ranges[i].split(": ", "-", " or ")
                .mapNotNull { it.toIntOrNull() }
            fields[range1_1] += (1 shl i)
            fields[range2_1] += (1 shl i)
            fields[range1_2 + 1] -= (1 shl i)
            fields[range2_2 + 1] -= (1 shl i)

            if (ranges[i].startsWith("departure")) departure += (1 shl i)
        }

        var sum = 0
        for (i in fields.indices) {
            sum += fields[i]
            fields[i] = sum
        }

        val validTickets = nearby.drop(1).mapNotNull { e ->
            if (e.split(",").map { it.toInt() }.all { v -> fields[v] > 0 }) e.split(",").map { it.toInt() } else null
        }

        val params = MutableList(ranges.size) { 0.inv() }
        validTickets.forEach {
            it.forEachIndexed { idx, e ->
                params[idx] = params[idx] and fields[e]
            }
        }

        val q = ArrayDeque<Int>()
        params.forEach {
            if (it.countOneBits() == 1) {
                q.add(it)
            }
        }

        while (!q.isEmpty()) {
            val n = q.removeFirst()
            params.forEachIndexed { idx, e ->
                if (params[idx].countOneBits() > 1) {
                    params[idx] = e and n.inv()
                    if (params[idx].countOneBits() == 1) {
                        q.add(params[idx])
                    }
                }
            }
        }

        return your[1].split(",").map { it.toInt() }
            .foldIndexed(1L) { idx, acc, e -> if ((params[idx] and departure) > 0) acc * e else acc }
    }
}