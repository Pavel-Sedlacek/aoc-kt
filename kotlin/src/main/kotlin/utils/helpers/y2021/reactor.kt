package utils.helpers.y2021

import utils.common.flipped


fun solveCuboids(input: List<Command>): Long {
    var cuboids = listOf<Cuboid>()

    input.forEachIndexed { _, cmd ->
        val (on, cuboid) = cmd
        val new = mutableListOf<Cuboid>()
        cuboids.forEach {
            new.addAll(it - cuboid)
        }
        if (on) {
            new.add(cuboid)
        }
        cuboids = new
    }

    return cuboids.sumOf { it.volume() }
}

data class Cuboid(val start: Vec4i, val end: Vec4i) {
    val valid = end.x > start.x && end.y > start.y && end.z > start.z

    fun volume(): Long {
        return (end.x.toLong() - start.x) * (end.y - start.y) * (end.z - start.z)
    }

    fun cut(component: Component4, value: Int): Pair<Cuboid?, Cuboid?> {
        return Cuboid(start, end.copy(component, minOf(value, end[component]))).takeIf { it.valid } to
                Cuboid(start.copy(component, maxOf(value, start[component])), end).takeIf { it.valid }
    }

    operator fun minus(right: Cuboid): List<Cuboid> {
        if ((this intersect right) == null) return listOf(this)

        val finalCut = ArrayList<Cuboid>(6)
        var cut = this

        for (c in listOf(Component4.X, Component4.Y, Component4.Z)) {
            for (start in listOf(true, false)) {
                val (l, r) = if (start) cut.cut(c, right.start[c]) else cut.cut(c, right.end[c]).flipped
                if (l != null) {
                    finalCut.add(l)
                }
                if (r == null) {
                    return finalCut
                }
                cut = r
            }
        }

        return finalCut
    }

    infix fun intersect(right: Cuboid): Cuboid? {
        return Cuboid(
            point3i(maxOf(start.x, right.start.x), maxOf(start.y, right.start.y), maxOf(start.z, right.start.z)),
            point3i(minOf(end.x, right.end.x), minOf(end.y, right.end.y), minOf(end.z, right.end.z)),
        ).takeIf { it.valid }
    }

    companion object {
        fun fromString(str: String): Cuboid {
            val components = str.split(",").map { component ->
                val (name, value) = component.split("=")
                val (from, to) = value.split("..")
                name to (from to to)
            }.toMap()
            return Cuboid(
                point3i(
                    components["x"]!!.first.toInt(),
                    components["y"]!!.first.toInt(),
                    components["z"]!!.first.toInt()
                ),
                point3i(
                    components["x"]!!.second.toInt() + 1,
                    components["y"]!!.second.toInt() + 1,
                    components["z"]!!.second.toInt() + 1
                ),
            )
        }
    }
}

data class Command(val on: Boolean, val cuboid: Cuboid)

fun point3i(x: Int, y: Int, z: Int) = Vec4i(x, y, z, 1)

enum class Component4 { X, Y, Z, W }

data class Vec4i(val x: Int, val y: Int, val z: Int, val w: Int) {
    operator fun minus(o: Vec4i) = Vec4i(x - o.x, y - o.y, z - o.z, w - o.w)

    operator fun get(c: Component4): Int {
        return when (c) {
            Component4.X -> x
            Component4.Y -> y
            Component4.Z -> z
            Component4.W -> w
        }
    }

    fun copy(c: Component4, v: Int): Vec4i {
        return when (c) {
            Component4.X -> copy(x = v)
            Component4.Y -> copy(y = v)
            Component4.Z -> copy(z = v)
            Component4.W -> copy(w = v)
        }
    }

    fun coerceAtLeast(value: Vec4i) = Vec4i(
        x.coerceAtLeast(value.x),
        y.coerceAtLeast(value.y),
        z.coerceAtLeast(value.z),
        w.coerceAtLeast(value.w)
    )

    fun coerceAtMost(value: Vec4i) = Vec4i(
        x.coerceAtMost(value.x),
        y.coerceAtMost(value.y),
        z.coerceAtMost(value.z),
        w.coerceAtMost(value.w)
    )
}