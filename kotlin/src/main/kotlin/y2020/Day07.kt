package y2020

import utils.Day
import utils.helpers.y2020.Bags
import utils.readers.asLines

class Day07 : Day<Int> {
    private val input = file.asLines()

    override fun runAll() {
        val bags = Bags(input)

        super.run({ partOne(bags) }) { partTwo(bags) }
    }

    private fun partOne(bags: Bags): Int {
        val isGold = mutableMapOf<String, Lazy<Boolean>>()
        for ((key, items) in bags.bags) {
            isGold[key] = lazy(LazyThreadSafetyMode.NONE) {
                items.any { (_, item) -> item == Bags.GOAL || isGold[item]?.value == true }
            }
        }
        return isGold.values.count { it.value }
    }

    private fun partTwo(bags: Bags): Int {
        var sum = 0
        val deque = ArrayDeque(bags.bags[Bags.GOAL].orEmpty())
        while (deque.isNotEmpty()) {
            val (count, item) = deque.removeFirst()
            sum += count
            bags.bags[item]?.forEach { (subCount, subItem) ->
                deque.add(count * subCount to subItem)
            }
        }
        return sum
    }

}