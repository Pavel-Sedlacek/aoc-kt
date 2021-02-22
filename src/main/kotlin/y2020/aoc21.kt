package y2020

import java.io.File
import java.util.*

fun main() {
    val inputs = File("src/main/resources/Day21.txt").readLines().map { line ->
        val (lhs, rhs) = line.split(" (contains ", limit = 2)
        lhs.split(' ') to rhs.removeSuffix(")").split(", ")
    }
    val mapping = inputs
        .flatMap { (lhs, rhs) -> rhs.map { it to lhs } }
        .groupingBy { it.first }
        .aggregate { _, acc: MutableSet<String>?, (_, lhs), first ->
            if (first) lhs.toMutableSet() else acc!!.apply { retainAll(lhs) }
        }.toMap()

    println("Day TWENTY-ONE : chinese allergens")
    println("Part One ${partOneTwentyOne(inputs, mapping)}")
    println("Part Two ${partTwoTwentyOne(mapping)}")
}

class Day21 {
    fun run() {
        val inputs = File("src/main/resources/Day21.txt").readLines().map { line ->
            val (lhs, rhs) = line.split(" (contains ", limit = 2)
            lhs.split(' ') to rhs.removeSuffix(")").split(", ")
        }
        val mapping = inputs
            .flatMap { (lhs, rhs) -> rhs.map { it to lhs } }
            .groupingBy { it.first }
            .aggregate { _, acc: MutableSet<String>?, (_, lhs), first ->
                if (first) lhs.toMutableSet() else acc!!.apply { retainAll(lhs) }
            }.toMap()

        println("Day TWENTY-ONE : chinese allergens")
        println("Part One ${partOneTwentyOne(inputs, mapping)}")
        println("Part Two ${partTwoTwentyOne(mapping)}")
    }
}

fun partOneTwentyOne(inputs: List<Pair<List<String>, List<String>>>, mapping: Map<String, Set<String>>): Int {
    val exclude = mutableSetOf<String>().apply { mapping.values.forEach(::addAll) }
    return inputs.sumOf { (lhs, _) -> lhs.count { it !in exclude } }
}

fun partTwoTwentyOne(mapping: Map<String, Set<String>>): String {
    val ret = TreeMap<String, String>()
    val mapper = mapping.mapValuesTo(mutableMapOf()) { (_, vs) -> vs.toMutableSet() }
    while (mapper.isNotEmpty()) {
        val (k, vs) = mapper.entries.find { it.value.size == 1 }!!
        val v = vs.single()
        ret[k] = v
        mapper.remove(k)
        for (vsl in mapper.values) vsl.remove(v)
    }
    return ret.values.joinToString(",")
}