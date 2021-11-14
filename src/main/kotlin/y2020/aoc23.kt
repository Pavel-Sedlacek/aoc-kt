package y2020

import java.io.File

data class LLNode(val value: Int, var next: LLNode?)

fun main() {
    println("Day TWENTY-THREE : Crab cup castling")
    println("Part One ${partOneTwentyThree(load())}")
    println("Part Two ${partTwoTwentyThree(load())}")
}

class Day23 {
    fun run() {
        println("Day TWENTY-THREE : Crab cup castling")
        println("Part One ${partOneTwentyThree(load())}")
        println("Part Two ${partTwoTwentyThree(load())}")
    }
}

fun load(): List<LLNode> {
    val cups = File("src/main/resources/Day23.txt").readText().chunked(1).map { LLNode(it.toInt(), null) }
    for (i in cups.indices) {
        cups[i].next = cups[(i + 1) % cups.size]
    }
    return cups
}

fun partOneTwentyThree(cups: List<LLNode>): String {
    var curr: LLNode? = cups[0]
    for (i in 1..100) {
        val move = curr?.next
        curr?.next = curr?.next?.next?.next?.next

        var destination = if (curr?.value == 1) 9 else curr!!.value - 1
        while (destination in listOf(move?.value, move?.next?.value, move?.next?.next?.value)) {
            destination -= 1
            if (destination < 1) {
                destination = 9
            }
        }

        var dest = curr.next
        while (dest?.value != destination) {
            dest = dest?.next
        }

        move?.next?.next?.next = dest.next
        dest.next = move
        curr = curr.next
    }

    var one = curr
    while (one?.value != 1) {
        one = one?.next
    }
    var res = ""
    while (one?.next?.value != 1) {
        res += one?.next?.value
        one = one?.next
    }
    return res
}

fun partTwoTwentyThree(cups: List<LLNode>): Long {
    var curr: LLNode = cups[8]
    for (i in 10..1000000) {
        curr.next = LLNode(i, null)
        curr = curr.next!!
    }
    curr.next = cups[0]
    curr = curr.next!!
    val cupMap = mutableMapOf(curr.value to curr)
    var cc = curr.next!!
    while (cc != curr) {
        cupMap[cc.value] = cc
        cc = cc.next!!
    }

    for (i in 1..10000000) {
        val move = curr.next
        curr.next = curr.next?.next?.next?.next

        var destination = if (curr.value == 1) 1000000 else curr.value - 1
        while (destination in listOf(move?.value, move?.next?.value, move?.next?.next?.value)) {
            destination -= 1
            if (destination < 1) {
                destination = 1000000
            }
        }

        val dest = cupMap[destination]

        move?.next?.next?.next = dest!!.next
        dest.next = move
        curr = curr.next!!
    }

    val one = cupMap[1]!!
    return one.next!!.value.toLong() * one.next!!.next!!.value.toLong()
}