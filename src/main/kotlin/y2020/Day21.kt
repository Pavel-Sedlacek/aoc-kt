package y2020

import utils.Day
import utils.readers.asLines
import java.util.*

class Day21 : Day<String> {

    private val input = file.asLines().map { line ->
        val (lhs, rhs) = line.split(" (contains ", limit = 2)
        lhs.split(' ') to rhs.removeSuffix(")").split(", ")
    }
    val mapping = input
        .flatMap { (lhs, rhs) -> rhs.map { it to lhs } }
        .groupingBy { it.first }
        .aggregate { _, acc: MutableSet<String>?, (_, lhs), first ->
            if (first) lhs.toMutableSet() else acc!!.apply { retainAll(lhs) }
        }.toMap()

    override fun runAll() = super.run({ partOne(input, mapping) }, { partTwo(mapping) })

    private fun partOne(input: List<Pair<List<String>, List<String>>>, mapping: Map<String, Set<String>>): String {
        val exclude = mutableSetOf<String>().apply { mapping.values.forEach(::addAll) }
        return input.sumOf { (lhs, _) -> lhs.count { it !in exclude } }.toString()
    }

    private fun partTwo(mapping: Map<String, Set<String>>): String {
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

}